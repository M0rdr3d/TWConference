package org.thoughtworks.conference.data;

/**
 * Class with the data for a talk.
 *
 *
 */
public class Talk {
	
	private String talkName;
	private int talkDurationTime;
    private String talkPresentationHour;
	

	public Talk(String talkName, int talkDurationTime){
		this.talkName = talkName;
		this.talkDurationTime = talkDurationTime;
	}

	public Talk(String talkName, int talkDurationTime, String talkPresentationHour){
		this.talkName = talkName;
		this.talkDurationTime = talkDurationTime;
		this.talkPresentationHour = talkPresentationHour;
	}

	public String getTalkName() {
		return talkName;
	}

	public void setTalkName(String talkName) {
		this.talkName = talkName;
	}

	public int getTalkDurationTime() {
		return talkDurationTime;
	}

	public void setTalkDurationTime(int talkDurationTime) {
		this.talkDurationTime = talkDurationTime;
	}

	public String getTalkPresentationHour() {
		return talkPresentationHour;
	}

	public void setTalkPresentationHour(String talkPresentationHour) {
		this.talkPresentationHour = talkPresentationHour;
	}

	
	

}


