package com.kongkheang.kmb.api.dao;

import java.util.List;

import com.kongkheang.kmb.api.domain.User;

public interface UserDaoExt {
	
	List<User> findUsersByUserName(String username);
	
	User modifyUserId(User user);
}
