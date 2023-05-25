package com.EyEmilyKim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EyEmilyKim.dao.UserDao;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public int login(String id, String pwd) {
		int result;
		//PWD 일치 -> 1 : 로그인 성공
		//PWD 불일치-> 0 : 계정 또는 비밀번호를 확인해주세요 
		String dbPwd = userDao.getPwd(id);
		if(pwd.equals(dbPwd)) 
			result = 1;
		else result = 0;		
		return result;
	}

}
