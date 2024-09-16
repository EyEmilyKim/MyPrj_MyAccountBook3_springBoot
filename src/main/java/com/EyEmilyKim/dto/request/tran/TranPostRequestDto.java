package com.EyEmilyKim.dto.request.tran;

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
	
	private String DATE;
	private String INEX;
	private String CCODE;
	private String ITEM;
	private Integer AMOUNT;
	private String MNCRD;
	private String MCODE;

}
