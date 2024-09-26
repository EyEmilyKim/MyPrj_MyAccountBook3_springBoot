package com.EyEmilyKim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EyEmilyKim.dao.CategoryDao;
import com.EyEmilyKim.dto.request.category.CategoryCreateRequestDto;
import com.EyEmilyKim.dto.request.category.CategoryUpdateRequestDto;
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
	public Integer getCount(int userId) {
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
	public Integer getMaxSqn() {
		System.out.println("CateService > getMaxSqn() called");
		
		return categoryDao.getMaxSqn();
	}
	
	@Override
	public List<String> getNameList(int userId, String inex) {
		System.out.println("CateService > getNameList() called");

		return categoryDao.getNameList(userId, inex);
	}

	@Override
	public int insert(CategoryCreateRequestDto requestDto, int userId) {
		System.out.println("CateService > insert() called");
		
		Category cate = new Category();
		cate.setSeqno(requestDto.getSEQNO());
		cate.setInex(requestDto.getINEX());
		cate.setCate_code(requestDto.getINEX()+requestDto.getSEQNO()+"_"+userId);
		cate.setCate_name(requestDto.getCNAME());
		cate.setUser_id(userId);
		
		return categoryDao.insert(cate);
	}

	@Override
	public int update(CategoryUpdateRequestDto requestDto) {
		System.out.println("CateService > update() called");

		Category cate = new Category();
		cate.setCate_code(requestDto.getCCODE());
		cate.setCate_name(requestDto.getN_CNAME());
		
		return categoryDao.update(cate);
	}

}
