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
public class MysqlColumnDefault {
	
	@Value("${mysql.column.default_cate_code}")
	private String default_cate_code;
	
	@Value("${mysql.column.default_mncrd}")
	private String default_mncrd;
	
	@Value("${mysql.column.default_meth_code}")
	private String default_meth_code;
	
}
