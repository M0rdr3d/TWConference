package org.thoughtworks.conference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.thoughtworks.conference.core.OrganizerCore;
import org.thoughtworks.conference.data.Talk;
import org.thoughtworks.conference.util.Constants;

public class OrganizerCoreImpl implements OrganizerCore {
	
	
	List<List<Talk>> tracks = new ArrayList<List<Talk>>();

	/**
	 * Get the total time of all talks
	 */
	public int getTotalConferenceTime(List<Talk> conference){
		
		int totalConferenceTime = 0;
		for (Talk talk : conference){
			 int talkDurationTime = talk.getTalkDurationTime();
			 totalConferenceTime += talkDurationTime;
		}
		
		return totalConferenceTime;
	}
	
	/**
	 * A simple algorithm for organize the talks in tracks 
	 * 
	 */
	public void organizeConference(List<Talk> conference){
	
		if(conference.size() == 0){
			return;
		}
		
		int morningTime = 0;
		int afternoonTime = 0;
		String morningTalkTime = Constants.MORNING_SESSION_START_HOUR;
		String afternoonTalkTime = Constants.AFTERNOON_SESSION_START_HOUR;
	
		List<Talk> morningSession = new ArrayList<Talk>();
		List<Talk> afternoonSession = new ArrayList<Talk>();
		List<Talk> talksForOrganize = new ArrayList<Talk>();
		
		for(Talk talk : conference){
			
			int durationTime = talk.getTalkDurationTime();
			int time = morningTime + durationTime;
			boolean conferenceAdded = false;
			
			if(time > Constants.MORNING_SESSION_MAX_MINUTES){
				
				time = afternoonTime + durationTime;
				if(time <= Constants.AFTERNOON_SESSION_MAX_MINUTES){
					afternoonTime = afternoonTime + durationTime;
					talk.setTalkPresentationHour(afternoonTalkTime);
					afternoonTalkTime = calculateHour(afternoonTalkTime, durationTime);
					conferenceAdded = true;
					afternoonSession.add(talk);
				}
			}else{
				morningTime = morningTime + durationTime;
				talk.setTalkPresentationHour(morningTalkTime);
				morningTalkTime = calculateHour(morningTalkTime, durationTime);
				conferenceAdded = true;
				morningSession.add(talk);
			}
			
			if(!conferenceAdded){
				talksForOrganize.add(talk);
			}
		}
	    
	    establishTrack(morningSession, afternoonSession, afternoonTalkTime);	
		organizeConference(talksForOrganize);
		
	}
	
	/**
	 * Establish a Track with the data of a morningSession, afternoonSession and
	 * sets the LunchEvent and the NetworkingEvent
	 * @param morningSession session with the talks for the morning
	 * @param afternoonSession session with the talks for the afternoon
	 * @param afternoonTalkTime Last time to calculate the hour of NetworkingEvent
	 */
	public void establishTrack(List<Talk> morningSession, List<Talk> afternoonSession, String afternoonTalkTime){
		
		String networkingEventTime = afternoonTalkTime.substring(0, 2);
		int networkingEventTimeInteger = Integer.parseInt(networkingEventTime);
		if(networkingEventTimeInteger < 16){
			afternoonTalkTime = Constants.NETWORKING_EVENT_MIN_HOUR;
		}
		
		List<Talk> organizedConferences = new ArrayList<Talk>();
		organizedConferences.addAll(morningSession);
		Talk lunch = new Talk(Constants.LUNCH_EVENT, 60, Constants.LUNCH_HOUR);
		organizedConferences.add(lunch);
		organizedConferences.addAll(afternoonSession);
		Talk networkingEvent = new Talk(Constants.NETWORKING_EVENT, 60, afternoonTalkTime);
		organizedConferences.add(networkingEvent);
		
		tracks.add( organizedConferences);
	}
	
	
	/**
	 * 
	 * Find the hour
	 * @param ultimateHour Hour 
	 * @param minutes minutes
	 * @return Hour plus minutes
	 */
	public String calculateHour(String ultimateHour, int minutes){
		
		 SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.HOUR_FORMAT);
		 Date date = null;
		 try {
			date = dateFormat.parse(ultimateHour);
		 } catch (ParseException e) {
		
			System.out.println("ERROR - hour format"); 
			e.printStackTrace();
		} 
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(date);
		 calendar.add(Calendar.MINUTE, minutes);
		 String conferenceTime = dateFormat.format(calendar.getTime());
		 
		 return conferenceTime;
		
	}

	/**
	 * Get the tracks
	 */
	public List<List<Talk>> getTracks() {
		return tracks;
	}

	
	public void setTracks(List<List<Talk>> tracks) {
		this.tracks = tracks;
	}
	
}
