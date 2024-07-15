package com.EyEmilyKim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.EyEmilyKim.dto.TranSearchDto;
import com.EyEmilyKim.dto.TransactionDto;
import com.EyEmilyKim.entity.Transaction;

@Mapper
public interface TransactionDao {

	List<TransactionDto> getList(TranSearchDto dto);

	Integer getCount(TranSearchDto dto);

	TransactionDto getOne(String tran_id);

	int delete(String tran_id);

	Integer getMaxMySqn(int user_id);

	int insert(Transaction tran);

	int update(Transaction tran);

}
