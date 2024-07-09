package com.EyEmilyKim.dto;

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
public class TranSearchDto {
	private String D_FROM;
	private String D_TO;
	private String INEX;
	private String ITEM;
	private Integer user_id;
	private Timestamp TS_FROM;
	private Timestamp TS_TO;
	private Integer RC;
	private Integer PG;
	private Integer start;
}
