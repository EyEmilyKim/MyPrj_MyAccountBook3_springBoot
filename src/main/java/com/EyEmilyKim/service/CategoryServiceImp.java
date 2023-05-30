package com.EyEmilyKim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EyEmilyKim.dao.CategoryDao;
import com.EyEmilyKim.entity.Category;

@Service
public class CategoryServiceImp implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public List<Category> getList(String id) {
		System.out.println("CateService > getList() called");
		
		return categoryDao.getList(id);
	}

	@Override
	public int getCount(String id) {
		System.out.println("CateService > getCount() called");
		
		return categoryDao.getCount(id);
	}

	@Override
	public Category getOne(String ccode) {
		System.out.println("CateService > getOne() called");
		
		return categoryDao.getOne(ccode);
	}

	@Override
	public int delete(String ccode) {
		System.out.println("CateService > delete() called");
		
		return categoryDao.delete(ccode);
	}

}
