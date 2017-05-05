package org.thoughtworks.conference;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.conference.data.Talk;

/**
 * Testing
 *
 */
public class FileManagerImplTest {

	FileManagerImpl fileManager = new FileManagerImpl();
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testLoadConferencesFromTextFile() {
		
		//System.out.println(new File(".").getAbsolutePath());
		//System.out.println(getClass().getProtectionDomain().getCodeSource().getLocation());
		try {
			List<Talk> conference = fileManager.loadConferencesFromTextFile("testingFiles/twinput.txt");
			
			int count = 0;
			for(Talk talk : conference){
				System.out.println(talk.getTalkName());
				switch(count){
					case 0: assertEquals("Robotic Vision 70min", talk.getTalkName());
							assertEquals(70 , talk.getTalkDurationTime());
							break;
					case 1: assertEquals("Graphic Engine Architecture 15min", talk.getTalkName());
							assertEquals(15 , talk.getTalkDurationTime());
							break;
					case 2: assertEquals("Semantic Web  40min", talk.getTalkName());
							assertEquals(40 , talk.getTalkDurationTime());
							break;
				}
				count++;
			}
		} catch (IOException e) {
			
			e.printStackTrace();
			fail("Error");
		}
		
	}

	@Test
	public void testGetTalkMinutes() {
		String line = "Hipo Regius 75min";
		int minutes = fileManager.getTalkMinutes(line);
		assertEquals(75, minutes);
		
		String line1 = "Thapsus... min";
		int minutes1 = fileManager.getTalkMinutes(line1);
		assertEquals(0, minutes1);
		
	}

}
