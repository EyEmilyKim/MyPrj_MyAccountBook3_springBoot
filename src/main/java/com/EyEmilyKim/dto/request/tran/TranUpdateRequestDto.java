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
@ToString(callSuper = true)
@SuperBuilder
public class TranUpdateRequestDto extends TranPostRequestDto {
	
	private String TRAN_ID;
	private String PREV_URL;

}
