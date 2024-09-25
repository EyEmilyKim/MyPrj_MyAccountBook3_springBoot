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
	
	private final String path = "src/main/resources/";
	private final String suffix = ".txt";

	public Map<String, String> loadMessages(String fileName) throws IOException {
		Map<String, String> messages = new HashMap<>();
		String target = path + fileName + suffix;
		
		try(Stream<String> lines = Files.lines(Paths.get(target))){
			lines.forEach(line -> {
				String [] keyValue = line.split("=", 2);
				if(keyValue.length == 2) {
					messages.put(keyValue[0].trim(), keyValue[1].trim());
				}
			});
		}
		return messages;
	}
	
}
