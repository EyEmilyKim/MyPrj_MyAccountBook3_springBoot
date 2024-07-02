package com.EyEmilyKim.service;

import java.util.List;
import java.util.Map;

import com.EyEmilyKim.entity.Category;

public interface CategoryService {

	List<Category> getList(int user_id);

	int getCount(int user_id);

	Category getOne(String ccode);

	int delete(String ccode);

	int getMaxSqn();
	
	List<String> getNameList(String id);

	int insert(Map<String,String> fm, String id);

	int update(Map<String,String> fm);
	
}
