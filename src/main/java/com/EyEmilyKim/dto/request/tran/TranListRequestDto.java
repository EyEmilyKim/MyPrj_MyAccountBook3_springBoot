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
	private String D_FROM;
	private String D_TO;
	private String ITEM;
	private String CATE_NAME;
	private String METH_NAME;
	private String inex;
	private Integer user_id;
	private Timestamp ts_from;
	private Timestamp ts_to;
	private Integer RC;
	private Integer PG;
	private Integer start;
}
