package com.EyEmilyKim.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EyEmilyKim.config.messages.MessageFileReader;

@Service
public class MessageService {
	
	@Autowired
	private MessageFileReader messageFileReader;
	
	public String getMessage(String fileName, String key) {
		try {
			Map<String, String> messages = messageFileReader.loadMessages(fileName);
			return messages.getOrDefault(key, "Message not found");
		} catch (IOException e) {
			 e.printStackTrace();
			 return "Error loading message file";
		}
	}
}
