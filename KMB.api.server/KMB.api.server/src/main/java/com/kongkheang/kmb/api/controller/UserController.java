package com.kongkheang.kmb.api.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kongkheang.kmb.api.domain.User;
import com.kongkheang.kmb.api.domain.request.PageableRequestMessage;
import com.kongkheang.kmb.api.domain.response.PageableResponseMessage;
import com.kongkheang.kmb.api.domain.response.ResponseMessage;
import com.kongkheang.kmb.api.domain.response.ResponseMessageBuilder;
import com.kongkheang.kmb.api.service.UserService;

/**
 * User Rest controller
 * @author Mr.SAY SEAK LENG
 *
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	
	/**
	 * Find users list with pagination support
	 * @param pageable
	 * @return
	 */
	@GetMapping
	public ResponseMessage<List<User>> findUserList(PageableRequestMessage pageable) {
		Page<User> findUsers = userService.findUsers(pageable);
		
		PageableResponseMessage<User> pageableResponseMessage = new PageableResponseMessage<>(findUsers);
		
		return ResponseMessageBuilder.success()
				.addBody(pageableResponseMessage)
				.build();
	}
}
