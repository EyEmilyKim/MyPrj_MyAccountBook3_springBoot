package com.EyEmilyKim.service;

import java.text.ParseException;
import java.util.Map;

import com.EyEmilyKim.dto.TranPageDto;
import com.EyEmilyKim.dto.TranSearchDto;
import com.EyEmilyKim.entity.Transaction;

public interface TransactionService {

	TranPageDto getListAll(TranSearchDto searchDto, int user_id) throws ParseException;
	TranPageDto getListIn(TranSearchDto searchDto, int user_id) throws ParseException;

	Transaction getOne(String tran_id);

	int delete(String tran_id);

	Integer getMaxMySqn(int user_id);
	
	int insert(Map<String,String> fm, int user_id) throws Exception;

	int update(Map<String,String> fm);
	
}
