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
public class ClientViewDefault {
	
	@Value("${client.view.default_userId}")
	private Integer default_userId;
	
	@Value("${client.view.default_rowCount_optionString}")
	private String default_rowCount_optionString;
	
	@Value("${client.view.default_rowCount}")
	private Integer default_rowCount;
	
	@Value("${client.view.final_pagesPerSet}")
	private Integer final_pagesPerSet;
}
