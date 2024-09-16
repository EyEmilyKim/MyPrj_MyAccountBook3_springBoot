package com.EyEmilyKim.service;

import java.text.ParseException;
import java.util.Map;

import com.EyEmilyKim.dto.TransactionDto;
import com.EyEmilyKim.dto.request.tran.TranListRequestDto;
import com.EyEmilyKim.dto.response.tran.TranListResponseDto;

public interface TransactionService {

	TranListResponseDto getList(TranListRequestDto requestDto, int user_id, String inex) throws ParseException;

	TransactionDto getOne(String tran_id);

	int delete(String tran_id);

	Integer getMaxMySqn(int user_id);
	
	int insert(Map<String,String> fm, int user_id) throws Exception;

	int update(Map<String,String> fm) throws ParseException;
	
}
