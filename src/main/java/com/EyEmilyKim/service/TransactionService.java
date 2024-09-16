package com.EyEmilyKim.service;

import java.text.ParseException;

import com.EyEmilyKim.dto.TransactionDto;
import com.EyEmilyKim.dto.request.tran.TranCreateRequestDto;
import com.EyEmilyKim.dto.request.tran.TranListRequestDto;
import com.EyEmilyKim.dto.request.tran.TranUpdateRequestDto;
import com.EyEmilyKim.dto.response.tran.TranListResponseDto;

public interface TransactionService {

	TranListResponseDto getList(TranListRequestDto requestDto, int user_id, String inex) throws ParseException;

	TransactionDto getOne(String tran_id);

	int delete(String tran_id);

	Integer getMaxMySqn(int user_id);
	
	int insert(TranCreateRequestDto requestDto, int user_id) throws Exception;

	int update(TranUpdateRequestDto requestDto) throws ParseException;
	
}
