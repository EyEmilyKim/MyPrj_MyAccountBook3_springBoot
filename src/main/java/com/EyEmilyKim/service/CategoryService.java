package com.EyEmilyKim.service;

import java.util.List;

import com.EyEmilyKim.entity.Category;

public interface CategoryService {

	List<Category> getList(String id);

	int getCount(String id);
	
}
