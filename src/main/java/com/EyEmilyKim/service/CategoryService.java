package com.EyEmilyKim.service;

import java.util.List;
import java.util.Map;

import com.EyEmilyKim.dto.request.category.CategoryCreateRequestDto;
import com.EyEmilyKim.dto.request.category.CategoryUpdateRequestDto;
import com.EyEmilyKim.entity.Category;

public interface CategoryService {

	List<Category> getList(int user_id);

	Integer getCount(int user_id);

	Category getOne(String ccode);

	int delete(String ccode);

	Integer getMaxSqn();
	
	List<String> getNameList(int user_id, String inex);

	int insert(CategoryCreateRequestDto requestDto, int user_id);

	int update(CategoryUpdateRequestDto requestDto);
	
}
