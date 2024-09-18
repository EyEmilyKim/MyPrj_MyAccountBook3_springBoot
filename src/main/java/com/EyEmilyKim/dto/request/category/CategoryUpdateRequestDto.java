package com.EyEmilyKim.dto.request.category;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString()
public class CategoryUpdateRequestDto {
	
	@JsonProperty("CCODE")
	private String CCODE;
	
	@JsonProperty("CNAME")
	private String CNAME;
	
	@JsonProperty("INEX")
	private String INEX;
	
	@JsonProperty("N_CNAME")
	private String N_CNAME;
	
}
