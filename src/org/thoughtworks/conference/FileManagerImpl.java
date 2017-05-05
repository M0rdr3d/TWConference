package org.thoughtworks.conference;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.thoughtworks.conference.core.FileManager;
import org.thoughtworks.conference.data.Talk;
import org.thoughtworks.conference.util.Constants;


/**
 * 
 * For the management of the input data file.
 *
 */
public class FileManagerImpl implements FileManager {

	/**
	 * Loads and parse a input file to get data for ordering the talks in a conference.
	 */
	public List<Talk> loadConferencesFromTextFile(String pathFile) throws IOException{
		
		List<String> textLines = null; 
		textLines = Files.readAllLines(Paths.get(pathFile));
			
		List<Talk> conference = new ArrayList<Talk>() ;
		for(String line : textLines){
			//System.out.println(line);
			int minutes = getTalkMinutes(line);
			if(minutes != 0){
				if(minutes > Constants.AFTERNOON_SESSION_MAX_MINUTES){
					System.out.println("The talk can not be higher than 240 minutes :" +  line);
					continue;
				}
				if(minutes < 5){
					System.out.println("The talk can not be lower than 5 : " + line);
				}
				Talk talk = new Talk(line.trim(), minutes);
				conference.add(talk);
			}
		}
		
		return conference;
	}
	
	/**
	 * Gets the duration minutes (integer) parsing the input line of a text file
	 * searching for the pattern <number>min  e.g  33min
	 * @param line line of text file to be analyzed
	 * @return duration minutes of the talk
	 */
	public int getTalkMinutes(String line){
		
		Pattern pattern = Pattern.compile(Constants.MINUTES_REGEXP);
		Matcher matcher = pattern.matcher(line.toLowerCase());
		int minutes = 0;
		if (matcher.find())
		{
			String value = matcher.group(0).trim();
		    minutes = Integer.valueOf(value.substring(0, value.toLowerCase().indexOf(Constants.MIN)));
		}else{
			if(line.toLowerCase().contains(Constants.LIGHTNING)){
				return 5;
			}
		}
		
		return minutes;
	}
	
}
