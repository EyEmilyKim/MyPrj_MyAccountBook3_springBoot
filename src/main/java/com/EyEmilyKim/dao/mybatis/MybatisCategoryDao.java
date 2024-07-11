package com.EyEmilyKim.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.EyEmilyKim.dao.CategoryDao;
import com.EyEmilyKim.entity.Category;

@Repository
public class MybatisCategoryDao implements CategoryDao {

	private CategoryDao mapper;
	
	public MybatisCategoryDao(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(CategoryDao.class);
	}
	
	@Override
	public List<Category> getList(int userId) {
		return mapper.getList(userId);
	}

	@Override
	public Integer getCount(int userId) {
		return mapper.getCount(userId);
	}

	@Override
	public Category getOne(String ccode) {
		return mapper.getOne(ccode);
	}

	@Override
	public int delete(String ccode) {
		return mapper.delete(ccode);
	}

	@Override
	public Integer getMaxSqn() {
		return mapper.getMaxSqn();
	}
	
	@Override
	public List<String> getNameList(int userId, String inex) {
		return mapper.getNameList(userId, inex);
	}

	@Override
	public int insert(Category cate) {
		return mapper.insert(cate);
	}

	@Override
	public int update(Category cate) {
		return mapper.update(cate);
	}
	
}
