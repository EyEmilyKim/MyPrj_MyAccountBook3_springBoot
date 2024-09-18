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
public class CategoryCreateRequestDto {
	
	@JsonProperty("SEQNO")
	private Integer SEQNO;
	
	@JsonProperty("INEX")
	private String INEX;
	
	@JsonProperty("CNAME")
	private String CNAME;
	
}
