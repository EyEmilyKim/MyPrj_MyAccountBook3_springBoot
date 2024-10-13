package com.EyEmilyKim.config.properties;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiPropertiesTest {

	@Autowired
	private ApiProperties props;
	
	@Test
	@DisplayName("api.email_to 공백 아님")
	void testPropertyEmailTo() {
		assertThat(props.getEmail_to()).isNotEmpty();
	}
	
	@Test
	@DisplayName("api.email_subject 공백 아님")
	void testPropertyEmailSubject() {
		assertThat(props.getEmail_subject()).isNotEmpty();
	}

}
