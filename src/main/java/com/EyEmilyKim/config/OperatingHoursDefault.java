package com.EyEmilyKim.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Component
public class OperatingHoursDefault {

	@Value("${operating.hours.start}")
	private String operatingHoursStart;
	
	@Value("${operating.hours.end}")
	private String operatingHoursEnd;
	
}
