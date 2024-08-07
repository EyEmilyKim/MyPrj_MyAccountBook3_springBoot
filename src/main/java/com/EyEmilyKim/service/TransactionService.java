package com.EyEmilyKim.service;

import java.text.ParseException;
import java.util.Map;

import com.EyEmilyKim.dto.TranPageDto;
import com.EyEmilyKim.dto.TranSearchDto;
import com.EyEmilyKim.dto.TransactionDto;

public interface TransactionService {

	TranPageDto getList(TranSearchDto searchDto, int user_id, String inex) throws ParseException;

	TransactionDto getOne(String tran_id);

	int delete(String tran_id);

	Integer getMaxMySqn(int user_id);
	
	int insert(Map<String,String> fm, int user_id) throws Exception;

	int update(Map<String,String> fm) throws ParseException;
	
}
