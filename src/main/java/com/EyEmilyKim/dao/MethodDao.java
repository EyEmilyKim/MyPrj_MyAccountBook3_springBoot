package com.EyEmilyKim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.EyEmilyKim.entity.Method;

@Mapper
public interface MethodDao {

	List<Method> getList(int userId);

	int getCount(int userId);

	Method getOne(String mcode);

	int delete(String mcode);

	int getMaxSqn();

	List<String> getNameList(int userId);

	int insert(Method meth);

	int update(Method meth);

}
