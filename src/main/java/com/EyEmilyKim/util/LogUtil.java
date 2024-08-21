package com.EyEmilyKim.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogUtil {

	private static final String DateTimeFormat= "yyyy-MM-dd HH:mm:ss";
	
	public static void printWithTimestamp(String message) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeFormat);
		String timestamp = LocalDateTime.now().format(formatter);
		System.out.println("["+timestamp+"] "+message);
	}
	
}
