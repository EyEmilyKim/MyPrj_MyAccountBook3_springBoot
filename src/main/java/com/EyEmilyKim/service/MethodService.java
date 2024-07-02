package com.EyEmilyKim.service;

import java.util.List;
import java.util.Map;

import com.EyEmilyKim.entity.Method;

public interface MethodService {
	
	List<Method> getList(int userId);

	int getCount(int userId);

	Method getOne(String mcode);
	
	int delete(String mcode);

	int getMaxSqn();

	List<String> getNameList(String id);

	int insert(Map<String, String> fm, String id);

	int update(Map<String,String> fm);
}
