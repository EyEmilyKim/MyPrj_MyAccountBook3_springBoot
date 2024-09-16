package com.EyEmilyKim.dto.request.tran;

import java.sql.Timestamp;

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
public class TranListRequestDto {

	// 클라이언트로부터 전달 - 사용자 입력
	private String D_FROM;
	private String D_TO;
	private String ITEM;
	private String CATE_NAME;
	private String METH_NAME;
	// 클라이언트로부터 전달 - 자동 입력
	private String INEX;
	private Integer RC;
	private Integer PG;
	
	// 백엔드에서 처리
	private Integer user_id;
	private Timestamp ts_from;
	private Timestamp ts_to;
	private Integer start;

}
