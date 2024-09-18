package com.EyEmilyKim.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.EyEmilyKim.dao.TransactionDao;
import com.EyEmilyKim.dto.TransactionDto;
import com.EyEmilyKim.dto.request.tran.TranListRequestDto;
import com.EyEmilyKim.entity.Transaction;

@Repository
public class MybatisTransactionDao implements TransactionDao {
	
	private TransactionDao mapper;
	
	public MybatisTransactionDao(SqlSession sqlSession) {
		mapper= sqlSession.getMapper(TransactionDao.class);
	}

	@Override
	public List<TransactionDto> getList(TranListRequestDto dto) {
		return mapper.getList(dto);
	}

	@Override
	public Integer getCount(TranListRequestDto dto) {
		return mapper.getCount(dto);
	}

	@Override
	public TransactionDto getOne(String tran_id) {
		return mapper.getOne(tran_id);
	}

	@Override
	public int delete(String tran_id) {
		return mapper.delete(tran_id);
	}

	@Override
	public Integer getMaxMySqn(int user_id) {
		return mapper.getMaxMySqn(user_id);
	}

	@Override
	public int insert(Transaction tran) {
		return mapper.insert(tran);
	}

	@Override
	public int update(Transaction tran) {
		return mapper.update(tran);
	}

}
