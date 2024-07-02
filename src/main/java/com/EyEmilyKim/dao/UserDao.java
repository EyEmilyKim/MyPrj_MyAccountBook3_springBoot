package com.EyEmilyKim.dao;

import com.EyEmilyKim.entity.User;

public interface UserDao {

	User findByLid(String lid);

}
