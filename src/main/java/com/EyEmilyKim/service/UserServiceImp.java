package com.EyEmilyKim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EyEmilyKim.dao.UserDao;
import com.EyEmilyKim.dto.UserSessionDto;
import com.EyEmilyKim.entity.User;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserSessionDto login(String lid, String pwd) throws Exception {
		User user = userDao.findByLid(lid);
		if (user == null)
			throw new Exception("사용자를 찾을 수 없습니다.");
		if (!user.getPwd().equals(pwd)) {
			throw new Exception("비밀번호가 일치하지 않습니다.");
		}
		return new UserSessionDto(user.getUser_id(), user.getNickname(), user.getBirthday());
	}

}
