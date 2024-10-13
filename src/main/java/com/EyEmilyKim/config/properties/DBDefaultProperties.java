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
@ConfigurationProperties(prefix = "db.default")
public class DBDefaultProperties {
	
	private String cate_code;
	private String in_meth;
	private String mncrd;
	private String meth_code;
	
}
