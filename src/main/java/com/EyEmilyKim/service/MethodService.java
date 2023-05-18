package com.EyEmilyKim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.EyEmilyKim.dao.MethodDao;
import com.EyEmilyKim.entity.Method;

public interface MethodService {
	
	List<Method> getList(String id);

	int getCount(String id);

}
