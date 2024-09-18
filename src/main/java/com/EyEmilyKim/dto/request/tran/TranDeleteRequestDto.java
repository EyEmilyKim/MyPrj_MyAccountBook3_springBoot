package com.EyEmilyKim.dto.request.tran;

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
@ToString
public class TranDeleteRequestDto {
	
	@JsonProperty("TRAN_ID")
	private String TRAN_ID;
	
	@JsonProperty("PREV_URL")
	private String PREV_URL;
	
}
