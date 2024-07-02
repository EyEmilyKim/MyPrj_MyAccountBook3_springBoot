package com.EyEmilyKim.service;

import com.EyEmilyKim.dto.UserSessionDto;

public interface UserService {

	UserSessionDto login(String lid, String pwd) throws Exception;

}
