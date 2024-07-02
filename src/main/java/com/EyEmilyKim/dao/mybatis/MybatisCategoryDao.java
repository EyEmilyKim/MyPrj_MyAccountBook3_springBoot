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
	public int getCount(int userId) {
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
	public int getMaxSqn() {
		return mapper.getMaxSqn();
	}
	
	@Override
	public List<String> getNameList(int userId) {
		return mapper.getNameList(userId);
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
