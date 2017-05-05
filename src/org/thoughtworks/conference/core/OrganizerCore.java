package org.thoughtworks.conference.core;

import java.util.List;

import org.thoughtworks.conference.data.Talk;

public interface OrganizerCore {
	
	public void organizeConference(List<Talk> conference);
	
	public int getTotalConferenceTime(List<Talk> conference);
	
	public List<List<Talk>> getTracks();
}
