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
	public List<Method> getList(String id) {
		return mapper.getList(id);
	}

	@Override
	public int getCount(String id) {
		return mapper.getCount(id);
	}

}
