package com.EyEmilyKim.service;

import java.util.List;

import com.EyEmilyKim.entity.Category;

public interface CategoryService {

	List<Category> getList(String id);

	int getCount(String id);

	Category getOne(String ccode);

	int delete(String ccode);

	int getMaxSqn();
	
	List<String> getNameList(String id);

	int insert(Category cate);

	int update(Category cate);
	
}
