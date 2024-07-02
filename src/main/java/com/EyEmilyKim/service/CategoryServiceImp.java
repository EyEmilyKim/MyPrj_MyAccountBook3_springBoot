package com.EyEmilyKim.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EyEmilyKim.dao.CategoryDao;
import com.EyEmilyKim.entity.Category;

@Service
public class CategoryServiceImp implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public List<Category> getList(int userId) {
		System.out.println("CateService > getList() called");
		
		return categoryDao.getList(userId);
	}

	@Override
	public int getCount(int userId) {
		System.out.println("CateService > getCount() called");
		
		return categoryDao.getCount(userId);
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

	@Override
	public int getMaxSqn() {
		System.out.println("CateService > getMaxSqn() called");
		
		return categoryDao.getMaxSqn();
	}
	
	@Override
	public List<String> getNameList(int userId) {
		System.out.println("CateService > getNameList() called");

		return categoryDao.getNameList(userId);
	}

	@Override
	public int insert(Map<String,String> fm, int userId) {
		System.out.println("CateService > insert() called");
		
		Category cate = new Category();
		cate.setSeqno(Integer.parseInt(fm.get("SEQNO")));
		cate.setInex(fm.get("INEX"));
		cate.setCate_code(fm.get("INEX")+fm.get("SEQNO")+"_"+userId);
		cate.setCate_name(fm.get("CNAME"));
		cate.setUser_id(userId);
		
		return categoryDao.insert(cate);
	}

	@Override
	public int update(Map<String,String> fm) {
		System.out.println("CateService > update() called");

		Category cate = new Category();
		cate.setCate_code(fm.get("CCODE"));
		cate.setCate_name(fm.get("N_CNAME"));
		
		return categoryDao.update(cate);
	}

}
