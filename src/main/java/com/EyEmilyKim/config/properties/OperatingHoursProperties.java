package com.EyEmilyKim.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Component
@ConfigurationProperties(prefix = "operating.hours")
public class OperatingHoursProperties {
	
	private String Start;
	private String End;
	
}
