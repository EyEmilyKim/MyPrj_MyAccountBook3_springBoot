package com.EyEmilyKim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EyEmilyKim.dao.MethodDao;
import com.EyEmilyKim.entity.Method;

@Service
public class MethodServiceImp implements MethodService {

	@Autowired
	private MethodDao methodDao;
	
	@Override
	public List<Method> getList(String id) {
		System.out.println("MethodService > list() called");
		
		return methodDao.getList(id);
	}

	@Override
	public int getCount(String id) {
		System.out.println("MethodService > getCount() called");
		return methodDao.getCount(id);
	}

}
