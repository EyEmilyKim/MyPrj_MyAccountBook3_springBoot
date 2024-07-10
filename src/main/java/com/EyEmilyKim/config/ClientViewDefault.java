package com.EyEmilyKim.config;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Component
public class ClientViewDefault {
	private Integer default_userId = 1;
	private Integer default_rowCount = 5;
	private Integer final_pagesPerSet = 2;
}
