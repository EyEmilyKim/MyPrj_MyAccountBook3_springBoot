package com.EyEmilyKim.dao.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.EyEmilyKim.dao.UserDao;

@Repository
public class MybatisUserDao implements UserDao {

	private UserDao mapper;
	
	@Autowired
	public MybatisUserDao(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(UserDao.class);
	}
	
	@Override
	public String getPwd(String id) {
		return mapper.getPwd(id);
	}

}
