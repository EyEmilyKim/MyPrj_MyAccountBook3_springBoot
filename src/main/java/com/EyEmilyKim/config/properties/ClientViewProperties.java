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
@ConfigurationProperties(prefix = "client.view")
public class ClientViewProperties {
	
	private Integer default_userId;
	private String default_rowCount_optionString;
	private Integer default_rowCount;
	private Integer final_pagesPerSet;
	
}
