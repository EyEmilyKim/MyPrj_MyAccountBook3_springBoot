package com.EyEmilyKim.dto.request.tran;

import java.sql.Timestamp;

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
public class TranListRequestDto {

	// 클라이언트로부터 전달 - 사용자 입력
	
	@JsonProperty("D_FROM")
	private String D_FROM;
	
	@JsonProperty("D_TO")
	private String D_TO;
	
	@JsonProperty("ITEM")
	private String ITEM;
	
	@JsonProperty("CATE_NAME")
	private String CATE_NAME;
	
	@JsonProperty("METH_NAME")
	private String METH_NAME;
	
	
	// 클라이언트로부터 전달 - 자동 입력
	
	@JsonProperty("INEX")
	private String INEX;
	
	@JsonProperty("RC")
	private Integer RC;
	
	@JsonProperty("PG")
	private Integer PG;

}
