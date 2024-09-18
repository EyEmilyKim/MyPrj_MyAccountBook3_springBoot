package com.EyEmilyKim.dto.request.tran;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class TranPostRequestDto {
	
	@JsonProperty("DATE")
	private String DATE;
	
	@JsonProperty("INEX")
	private String INEX;
	
	@JsonProperty("CCODE")
	private String CCODE;
	
	@JsonProperty("ITEM")
	private String ITEM;
	
	@JsonProperty("AMOUNT")
	private Integer AMOUNT;
	
	@JsonProperty("MNCRD")
	private String MNCRD;
	
	@JsonProperty("MCODE")
	private String MCODE;

}
