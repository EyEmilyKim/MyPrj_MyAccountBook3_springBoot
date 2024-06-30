package com.EyEmilyKim.service;

import java.util.List;
import java.util.Map;

import com.EyEmilyKim.entity.Category;

public interface CategoryService {

	List<Category> getList(String id);

	int getCount(String id);

	Category getOne(String ccode);

	int delete(String ccode);

	int getMaxSqn();
	
	List<String> getNameList(String id);

	int insert(Map<String,String> fm, String id);

	int update(Map<String,String> fm);
	
}
