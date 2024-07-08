package com.EyEmilyKim.service;

import java.util.List;
import java.util.Map;

import com.EyEmilyKim.entity.Transaction;

public interface TransactionService {

	List<Transaction> getList(int user_id);

	Integer getCount(int user_id);

	Transaction getOne(String tran_id);

	int delete(String tran_id);

	Integer getMaxMySqn(int user_id);
	
	int insert(Map<String,String> fm, int user_id) throws Exception;

	int update(Map<String,String> fm);
	
}
