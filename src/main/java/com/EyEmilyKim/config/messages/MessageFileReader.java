package com.EyEmilyKim.config.messages;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component
public class MessageFileReader {
	
	private final String path = "src/main/resources/message-error.txt";

	private Map<String, String> messages = new HashMap<>();
	
	public MessageFileReader() throws IOException {
		loadMessages();
	}
	
	private void loadMessages() {
		try {
			Stream<String> lines = Files.lines(Paths.get(path));
			lines.forEach(line -> {
				String [] keyValue = line.split("=", 2);
				if(keyValue.length == 2) {
					messages.put(keyValue[0].trim(), keyValue[1].trim());
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getMessage(String key) {
		return messages.getOrDefault(key, "Message not found");
	}
}
