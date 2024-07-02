package com.EyEmilyKim.dao.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.EyEmilyKim.dao.UserDao;
import com.EyEmilyKim.entity.User;

@Repository
public class MybatisUserDao implements UserDao {

	private UserDao mapper;
	
	public MybatisUserDao(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(UserDao.class);
	}
	
	@Override
	public User findByLid(String lid) {
		return mapper.findByLid(lid);
	}

}
