package com.EyEmilyKim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.EyEmilyKim.entity.Transaction;

@Mapper
public interface TransactionDao {

	List<Transaction> getList(int user_id);

	int getCount(int user_id);

	Transaction getOne(String tran_id);

	int delete(String tran_id);

	int getMaxMySqn(int user_id);

	int insert(Transaction tran);

	int update(Transaction tran);

}
