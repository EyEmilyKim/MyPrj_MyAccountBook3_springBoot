package com.EyEmilyKim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.EyEmilyKim.entity.Method;

@Mapper
public interface MethodDao {

	List<Method> getList(String id);

	int getCount(String id);

	Method getOne(String mcode);

	int delete(String mcode);

	int getMaxSqn();

	List<String> getNameList(String id);

	int insert(Method meth);

	int update(Method meth);

}
