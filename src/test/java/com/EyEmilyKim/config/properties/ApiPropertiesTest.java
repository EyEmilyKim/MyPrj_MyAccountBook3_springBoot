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
	@DisplayName("api.server-path 일치 : 3.39.39.244")
	void testPropertyValue1() {
		assertThat(props.getServer_path()).isEqualTo("3.39.39.244");
	}
	
	@Test
	@DisplayName("api.context-path 공백 아님")
	void testPropertyValue4_notBlank() {
		assertThat(props.getContext_path()).isNotBlank();
	}	
	
	@Test
	@DisplayName("api.context-path 일치 : /")
	void testPropertyValue4_equals() {
		assertThat(props.getContext_path()).isEqualTo("/");
	}	
	
	@Test
	@DisplayName("api.email_to 공백 아님")
	void testPropertyValue2() {
		assertThat(props.getEmail_to()).isNotEmpty();
	}
	
	@Test
	@DisplayName("api.email_subject 공백 아님")
	void testPropertyValue3() {
		assertThat(props.getEmail_subject()).isNotEmpty();
	}

}
