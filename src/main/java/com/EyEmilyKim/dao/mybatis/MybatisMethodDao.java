package com.EyEmilyKim.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.EyEmilyKim.dao.MethodDao;
import com.EyEmilyKim.entity.Method;

@Repository
public class MybatisMethodDao implements MethodDao {
	
	private MethodDao mapper;
	
	public MybatisMethodDao(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(MethodDao.class);
	}

	@Override
	public List<Method> getList(int userId) {
		return mapper.getList(userId);
	}

	@Override
	public int getCount(int userId) {
		return mapper.getCount(userId);
	}

	@Override
	public Method getOne(String mcode) {
		return mapper.getOne(mcode);
	}

	@Override
	public int delete(String mcode) {
		return mapper.delete(mcode);
	}
	
	@Override
	public int getMaxSqn() {
		return mapper.getMaxSqn();
	}
	
	@Override
	public List<String> getNameList(int userId) {
		return mapper.getNameList(userId);
	}

	@Override
	public int insert(Method meth) {
		return mapper.insert(meth);
	}

	@Override
	public int update(Method meth) {
		return mapper.update(meth);
	}

}
