package com.EyEmilyKim.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("My 가계부 Ver3. API")
						.version("1.0")
						.description("스프링Boot로 만들어보는 나만의 가계부 어플리케이션")
						.contact(new io.swagger.v3.oas.models.info.Contact()
								.name("EyEmilyKim")
								.email("eunyoung-kim@naver.com")
								.url("https://github.com/EyEmilyKim/MyPrj_MyAccountBook3_springBoot")
							)
					);
	}
	
}
