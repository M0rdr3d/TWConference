package org.thoughtworks.conference;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.conference.data.Talk;

public class OrganizerCoreImplTest {

	List<Talk> conference;
	OrganizerCoreImpl core = new OrganizerCoreImpl();
	
	@Before
	public void setUp() throws Exception {
		conference = new ArrayList<Talk>();
		conference.add(new Talk("Pontus Euxinus 30min",30));
		conference.add(new Talk("Dacia 45min",45));
		conference.add(new Talk("Heraclea 60min",60));
		conference.add(new Talk("Sinope 90min",90));
		conference.add(new Talk("Amisus 60min",60));
		conference.add(new Talk("Ilium Pergamun 40min",40));
		conference.add(new Talk("Bizantium 45min",45));
		conference.add(new Talk("Leptis Magna 60min",60));
		conference.add(new Talk("Carthago Nova lightning",5));
		conference.add(new Talk("Numidia 117min",117));
		
	}
	
	@Test
	public void testGetTotalConferenceTime() {
		
		int totalTime = core.getTotalConferenceTime(conference);
	    System.out.println("Total Conference Time: " + totalTime);
		assertEquals(552, totalTime);
		
	}

	@Test
	public void testOrganizeConference() {
		core.organizeConference(conference);
		List<List<Talk>> tracks = core.getTracks();
		int counter = 0;
		for(List<Talk> track : tracks){
			for(Talk talk : track ){
				System.out.println(talk.getTalkPresentationHour() + " " +talk.getTalkName());
				switch (counter){
					case 0: assertEquals("09:00 Pontus Euxinus 30min", talk.getTalkPresentationHour() + " " +talk.getTalkName());
					        break;
					case 1: assertEquals("09:30 Dacia 45min", talk.getTalkPresentationHour() + " " +talk.getTalkName());
			        		break;
					case 2: assertEquals("10:15 Heraclea 60min", talk.getTalkPresentationHour() + " " +talk.getTalkName());
			        		break;
					case 3: assertEquals("11:15 Ilium Pergamun 40min", talk.getTalkPresentationHour() + " " +talk.getTalkName());
			        		break;
					case 4: assertEquals("11:55 Carthago Nova lightning", talk.getTalkPresentationHour() + " " +talk.getTalkName());
	        				break;		
					case 5: assertEquals("12:00 Lunch", talk.getTalkPresentationHour() + " " +talk.getTalkName());
			        		break;
					case 6: assertEquals("13:00 Sinope 90min", talk.getTalkPresentationHour() + " " +talk.getTalkName());
			        		break;
					case 7: assertEquals("14:30 Amisus 60min", talk.getTalkPresentationHour() + " " +talk.getTalkName());
			        		break;
					case 8: assertEquals("15:30 Bizantium 45min", talk.getTalkPresentationHour() + " " +talk.getTalkName());
	        				break;
					case 9: assertEquals("16:15 Networking Event", talk.getTalkPresentationHour() + " " +talk.getTalkName());
	        				break;
					case 10: assertEquals("09:00 Leptis Magna 60min", talk.getTalkPresentationHour() + " " +talk.getTalkName());
	        				 break;
					case 11: assertEquals("10:00 Numidia 117min", talk.getTalkPresentationHour() + " " +talk.getTalkName());
    						 break;
					case 12: assertEquals("12:00 Lunch", talk.getTalkPresentationHour() + " " +talk.getTalkName());
	        		break;
					case 13: assertEquals("16:00 Networking Event", talk.getTalkPresentationHour() + " " +talk.getTalkName());
    				break;
	
					
				}
				
				counter++; 
				
			}
		}
	}

	@Test
	public void testCalculateHour() {
		String newHour = core.calculateHour("22:00", 75);
		assertEquals("23:15", newHour);
	}

}
