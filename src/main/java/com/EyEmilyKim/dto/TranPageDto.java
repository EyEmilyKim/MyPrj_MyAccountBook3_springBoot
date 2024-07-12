package com.EyEmilyKim.dto;

import java.util.List;

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
public class TranPageDto {
	private List<TransactionDto> list;
	private long totalCount;
	
	private int totalPages;
	private int currentPage;
	private int rowCount;
	private String rowCount_option;
	
	private int currentSet;
	private int startPage;
	private int endPage;
	
}
