package com.EyEmilyKim.dao;

import org.apache.ibatis.annotations.Mapper;

import com.EyEmilyKim.entity.User;

@Mapper
public interface UserDao {

	User findByLid(String lid);

}
