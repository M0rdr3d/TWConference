package org.thoughtworks.conference.main;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.thoughtworks.conference.FileManagerImpl;
import org.thoughtworks.conference.OrganizerCoreImpl;
import org.thoughtworks.conference.core.FileManager;
import org.thoughtworks.conference.core.OrganizerCore;
import org.thoughtworks.conference.data.Talk;


/**
 * Main class let the user interact with the application, call to other class and presents 
 * the result.
 * @author Santiago
 *
 */
public class Main {

	public static void main(String[] args) {
		
		  OrganizerCore core = new OrganizerCoreImpl();
		  FileManager fileManager = new FileManagerImpl();
		  List<Talk> talks = null;
		  
		  Scanner scanner = new Scanner(System.in);
		  String consoleInput = "";
		   
		   while(consoleInput.equals("")){ 
			   System.out.println("\n========= CONFERENCE TOOL -Beta- =========");
		 	   System.out.println("Write the path and name of the input file: ");
			   consoleInput = scanner.nextLine();
		   } 
		
		  scanner.close();
		  System.out.println("\nProcessing Input File: " + consoleInput);
		  System.out.println("Please wait...");
		  
		  	try {
				talks = fileManager.loadConferencesFromTextFile(consoleInput);
		  	} catch (IOException e) {
				System.err.println("ERROR - problem with file: " + consoleInput);
				e.printStackTrace();
				System.exit(1);
		   }
		  
	      int totalTime = core.getTotalConferenceTime(talks);
	      System.out.println("\nOrganizing Talks...");
	      System.out.println("TOTAL CONFERENCE TIME: " + totalTime + " min" );
	      core.organizeConference(talks);
	      
	      List<List<Talk>>  tracks = core.getTracks();
	      int counter = 1;
	      for(List<Talk> confs : tracks ){
	    	  System.out.println("\n>>>>> TRACK " + counter + " <<<<<");
	    	  counter++;
	    	  for(Talk conf : confs){
	    		  System.out.println(conf.getTalkPresentationHour() + " " + conf.getTalkName());
	    	  }
	     }
	      
	      System.out.println("\n");
	      

	}

}
