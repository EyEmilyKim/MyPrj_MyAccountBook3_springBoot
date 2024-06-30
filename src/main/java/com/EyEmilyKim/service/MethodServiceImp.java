package com.EyEmilyKim.service;

import java.util.List;
import java.util.Map;

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

	@Override
	public Method getOne(String mcode) {
		System.out.println("MethodService > getOne() called");
		return methodDao.getOne(mcode);
	}

	@Override
	public List<String> getNameList(String id) {
		System.out.println("MethodService > getNameList() called");
		return methodDao.getNameList(id);
	}

	@Override
	public int update(Map<String,String> fm) {
		System.out.println("MethodService > update() called");

		Method meth = new Method();
		meth.setMeth_code(fm.get("MCODE"));
		meth.setMeth_name(fm.get("N_MNAME"));
		
		return methodDao.update(meth);
	}

}
