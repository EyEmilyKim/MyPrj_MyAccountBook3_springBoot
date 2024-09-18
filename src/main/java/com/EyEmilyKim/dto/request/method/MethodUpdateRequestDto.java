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
public class MethodUpdateRequestDto {

	@JsonProperty("MCODE")
	private String MCODE;
	
	@JsonProperty("MNAME")
	private String MNAME;
	
	@JsonProperty("MNCRD")
	private String MNCRD;
	
	@JsonProperty("N_MNAME")
	private String N_MNAME;

}
