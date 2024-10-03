package com.EyEmilyKim.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class TextFileReader {
	
	private final String suffix = ".txt";

	public Map<String, String> loadData(String path, String fileName) throws IOException {
		Map<String, String> data = new HashMap<>();
		String target = path + fileName + suffix;
		
		ClassPathResource resource = new ClassPathResource(target);
		try(BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
				br.lines().forEach(line -> {
				String [] keyValue = line.split("=", 2);
				if(keyValue.length == 2) {
					data.put(keyValue[0].trim(), keyValue[1].trim());
				}
			});
		}
		return data;
	}
	
}
