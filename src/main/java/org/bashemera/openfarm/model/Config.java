package org.bashemera.openfarm.model;

import java.nio.file.Path;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config")
public class Config {
	@Id
	private String id;
	public static final long INSTALL_TIME = System.currentTimeMillis() / 1000L;
	public static final String INSTALL_UUID = UUID.randomUUID().toString(); 
	private Path logFilePath;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Path getLogFilePath() {
		return logFilePath;
	}
	public void setLogFilePath(Path logFilePath) {
		this.logFilePath = logFilePath;
	}
}
