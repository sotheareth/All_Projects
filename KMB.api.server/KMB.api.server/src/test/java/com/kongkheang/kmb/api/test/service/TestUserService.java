package com.kongkheang.kmb.api.test.service;


import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.kongkheang.kmb.api.domain.User;
import com.kongkheang.kmb.api.domain.request.PageableRequestMessage;
import com.kongkheang.kmb.api.domain.response.PageableResponseMessage;
import com.kongkheang.kmb.api.service.UserService;
import com.kongkheang.kmb.api.test.AbstractTestCase;
import com.kongkheang.kmb.api.util.Gender;

public class TestUserService extends AbstractTestCase {
	@Autowired
	private UserService userService;
	
	@Test
	public void testSave() {
		User user = new User();
		user.setUsername("សួស្ដី");
		user.setGender(Gender.M);
		User save = userService.save(user);
		assertNotNull(save);
		prettyPrint(save);
	}
	
	@Test
	public void testFindUsers() {
		PageableRequestMessage pageable = new PageableRequestMessage(PageableRequestMessage.DEFAULT_OFFSET, 1);
		
		Page<User> findUsers = userService.findUsers(pageable);
		assertNotSame(findUsers.getTotalElements(), 0);
		
		PageableResponseMessage<User> pageableResponseMessage = new PageableResponseMessage<>(findUsers);
		prettyPrint(pageableResponseMessage);
	}
	
	@Test
	public void testFindUsersByUserName() {
		List<User> findUsersByUserName = userService.findUsersByUserName("សួ");
		prettyPrint(findUsersByUserName);
	}
	
	@Test
	public void testModifyUserId() {
		User user = new User();
		user.setUsername("សួdfxa123");
		user.setId(1L);
		User modifyUserId = userService.modifyUserId(user);
		assertNotNull(modifyUserId);
		prettyPrint(modifyUserId);
	}

}