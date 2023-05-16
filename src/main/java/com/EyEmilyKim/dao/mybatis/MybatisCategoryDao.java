package com.EyEmilyKim.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.EyEmilyKim.dao.CategoryDao;
import com.EyEmilyKim.entity.Category;

//@Repository
public class MybatisCategoryDao implements CategoryDao {

	private CategoryDao mapper;
	
	@Autowired
	public MybatisCategoryDao(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(CategoryDao.class);
	}
	
	@Override
	public List<Category> getList(String id) {
		return mapper.getList(id);
	}

	@Override
	public int getCount(String id) {
		return mapper.getCount(id);
	}
	
}
