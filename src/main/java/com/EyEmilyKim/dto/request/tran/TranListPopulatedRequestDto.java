package com.EyEmilyKim.dto.request.tran;

import java.sql.Timestamp;

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
public class TranListPopulatedRequestDto extends TranListRequestDto {
	
	// 백엔드에서 처리
	private Integer user_id;
	private Timestamp ts_from;
	private Timestamp ts_to;
	private Integer start;

}
