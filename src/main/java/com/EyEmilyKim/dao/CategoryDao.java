package com.EyEmilyKim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.EyEmilyKim.entity.Category;

@Mapper
public interface CategoryDao {

	List<Category> getList(int userId);

	Integer getCount(int userId);

	Category getOne(String ccode);

	int delete(String ccode);

	Integer getMaxSqn();

	List<String> getNameList(int userId);
	
	int insert(Category cate);

	int update(Category cate);

}
