package com.EyEmilyKim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EyEmilyKim.dao.CategoryDao;
import com.EyEmilyKim.entity.Category;

@Service
public class CategoryServiceImp implements CategoryService {

//	@Autowired
//	private CategoryDao cateDao;
	
	@Override
	public List<Category> getList(String id) {
		System.out.println("CateService > getList() called");
		
//		return cateDao.getList(id);
		return null;
	}

	@Override
	public int getCount(String id) {
		System.out.println("CateService > getCount() called");
		
//		return cateDao.getCount(id);
		return 0;
	}

}
