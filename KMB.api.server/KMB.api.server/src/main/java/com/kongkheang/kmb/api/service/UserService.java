package com.kongkheang.kmb.api.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.kongkheang.kmb.api.domain.User;
import com.kongkheang.kmb.api.domain.request.PageableRequestMessage;

public interface UserService {
	User save(User user);
	User findOne(String username);
	Page<User> findUsers(PageableRequestMessage pageable);
	List<User> findUsersByUserName(String username);
	User modifyUserId(User user);
}
