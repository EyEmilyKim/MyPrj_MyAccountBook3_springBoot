package com.EyEmilyKim.service;

import java.util.List;

import com.EyEmilyKim.dto.request.method.MethodCreateRequestDto;
import com.EyEmilyKim.dto.request.method.MethodUpdateRequestDto;
import com.EyEmilyKim.entity.Method;

public interface MethodService {
	
	List<Method> getList(int userId);

	Integer getCount(int userId);

	Method getOne(String mcode);
	
	int delete(String mcode);

	Integer getMaxSqn();

	List<String> getNameList(int userId);

	int insert(MethodCreateRequestDto requestDto, int userId);

	int update(MethodUpdateRequestDto requestDto);
}
