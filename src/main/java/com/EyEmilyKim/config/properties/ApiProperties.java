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
@ConfigurationProperties(prefix = "api")
public class ApiProperties {
	
	private String email_to;
	private String email_subject;
	
}
