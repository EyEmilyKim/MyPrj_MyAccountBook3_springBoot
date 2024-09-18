package com.EyEmilyKim.dto.response.tran;

import java.util.List;

import com.EyEmilyKim.dto.TransactionDto;

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
public class TranListResponseDto {
	private String inex;
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
