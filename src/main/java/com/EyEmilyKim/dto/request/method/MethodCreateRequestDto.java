package com.EyEmilyKim.dto.request.method;

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
public class MethodCreateRequestDto {

	@JsonProperty("SEQNO")
	private Integer SEQNO;
	
	@JsonProperty("MNCRD")
	private String MNCRD;
	
	@JsonProperty("MNAME")
	private String MNAME;

}
