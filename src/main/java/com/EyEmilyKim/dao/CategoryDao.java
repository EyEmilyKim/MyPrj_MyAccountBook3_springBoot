package com.EyEmilyKim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.EyEmilyKim.entity.Category;

@Mapper
public interface CategoryDao {

	List<Category> getList(String id);

	int getCount(String id);

}
