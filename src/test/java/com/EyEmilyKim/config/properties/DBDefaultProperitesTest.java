package com.EyEmilyKim.config.properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DBDefaultProperitesTest {

	@Autowired
	private DBDefaultProperties props;
	
	@Test
	@DisplayName("db.default.cate_code 공백 아님")
	void testPropertyValue1_notBlank() {
		assertThat(props.getCate_code()).isNotBlank();
	}
	
	@Test
	@DisplayName("db.default.cate_code 값 일치")
	void testPropertyValue1_equals() {
		assertEquals(props.getCate_code(), "caNN0");
	}
	

}
