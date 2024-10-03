package com.EyEmilyKim.util;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageUtil {
	
	@Autowired
	private TextFileReader textFileReader;
	
	private static final String path = "/"; 
	
	public String getMessage(String fileName, String key) {
		try {
			Map<String, String> messages = textFileReader.loadData(path, fileName);
			return messages.getOrDefault(key, "Message not found");
		} catch (IOException e) {
			 e.printStackTrace();
			 return "Error loading message file";
		}
	}
}
