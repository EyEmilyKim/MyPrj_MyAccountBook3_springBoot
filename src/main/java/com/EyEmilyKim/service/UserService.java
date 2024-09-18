package com.EyEmilyKim.service;

import com.EyEmilyKim.dto.response.LoginResponseDto;

public interface UserService {

	LoginResponseDto login(String lid, String pwd) throws Exception;

}
