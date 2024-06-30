package com.EyEmilyKim.service;

import java.util.List;
import java.util.Map;

import com.EyEmilyKim.entity.Method;

public interface MethodService {
	
	List<Method> getList(String id);

	int getCount(String id);

	Method getOne(String mcode);

	List<String> getNameList(String id);

	int update(Map<String,String> fm);

}
