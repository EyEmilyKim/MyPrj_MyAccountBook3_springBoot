package com.EyEmilyKim.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.EyEmilyKim.dao.TransactionDao;
import com.EyEmilyKim.entity.Transaction;

@Repository
public class MybatisTransactionDao implements TransactionDao {
	
	private TransactionDao mapper;
	
	public MybatisTransactionDao(SqlSession sqlSession) {
		mapper= sqlSession.getMapper(TransactionDao.class);
	}

	@Override
	public List<Transaction> getList(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount(int user_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Transaction getOne(String tran_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(String tran_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxMySqn(int user_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Transaction tran) {
		return mapper.insert(tran);
	}

	@Override
	public int update(Transaction tran) {
		// TODO Auto-generated method stub
		return 0;
	}

}
