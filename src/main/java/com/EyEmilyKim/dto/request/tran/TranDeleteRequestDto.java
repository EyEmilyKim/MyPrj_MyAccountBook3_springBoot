package com.EyEmilyKim.dto.request.tran;

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
	private String TRAN_ID;
	private String PREV_URL;
}
