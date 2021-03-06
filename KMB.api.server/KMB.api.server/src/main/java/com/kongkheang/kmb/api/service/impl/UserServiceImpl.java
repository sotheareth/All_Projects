package com.kongkheang.kmb.api.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kongkheang.kmb.api.dao.UserDao;
import com.kongkheang.kmb.api.domain.User;
import com.kongkheang.kmb.api.domain.request.PageableRequestMessage;
import com.kongkheang.kmb.api.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User save(User user) {
		return userDao.save(user);
	}
	
	public User findOne(String username){
		User user = new User(username);
		Example<User> example = Example.of(user );
		return userDao.findOne(example);
	}
	
	
	@Override
	public Page<User> findUsers(PageableRequestMessage pageable) {
		Pageable param = pageable.generatePageable();
		return userDao.findAll(param);
	}

	@Override
	public List<User> findUsersByUserName(String username) {
		return userDao.findUsersByUserName(username);
	}

	@Override
	public User modifyUserId(User user) {
		return userDao.modifyUserId(user);
	}
	
}
