package com.EyEmilyKim.config.properties;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiPropertiesTest {

	@Autowired
	private ApiProperties props;
	
	@Test
	void testPropertyValue() {
		assertThat(props.getServer_path()).isEqualTo("3.39.39.244/index");
	}

}
