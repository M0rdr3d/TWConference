package org.thoughtworks.conference.core;

import java.io.IOException;
import java.util.List;

import org.thoughtworks.conference.data.Talk;

public interface FileManager {

	public List<Talk> loadConferencesFromTextFile(String pathFile) throws IOException;
	
}
