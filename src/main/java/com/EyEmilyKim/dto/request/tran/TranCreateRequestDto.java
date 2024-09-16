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
public class TranCreateRequestDto extends TranPostRequestDto {
	
	private Integer MY_SEQNO;

}
