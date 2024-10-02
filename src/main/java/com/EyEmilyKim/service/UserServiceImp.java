package com.EyEmilyKim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EyEmilyKim.dao.UserDao;
import com.EyEmilyKim.dto.response.LoginResponseDto;
import com.EyEmilyKim.entity.User;
import com.EyEmilyKim.util.MessageUtil;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@Override
	public LoginResponseDto login(String lid, String pwd) throws Exception {
		User user = userDao.findByLid(lid);
		if (user == null)
			throw new Exception(messageUtil.getMessage("message-error", "msg.error.login.userNotFound"));
		if (!user.getPwd().equals(pwd)) {
			throw new Exception(messageUtil.getMessage("message-error", "msg.error.login.wrongPassword"));
		}
		return new LoginResponseDto(user.getUser_id(), user.getNickname(), user.getBirthday());
	}

}
