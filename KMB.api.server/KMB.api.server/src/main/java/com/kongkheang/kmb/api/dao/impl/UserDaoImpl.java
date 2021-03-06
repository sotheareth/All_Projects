package com.kongkheang.kmb.api.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.kongkheang.kmb.api.dao.UserDaoExt;
import com.kongkheang.kmb.api.domain.User;
import com.kongkheang.kmb.api.util.EntityUtils;

@Transactional(readOnly = true)
public class UserDaoImpl implements UserDaoExt {
	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findUsersByUserName(String username) {
		Session session = sessionFactory.getCurrentSession();
		List<User> userList = session.createCriteria(User.class)
			.add( Restrictions.like("username", "%" + username + "%") )
			.list();
		
		return userList;
	}

	@Override
	public User modifyUserId(User user) {
		Session session = sessionFactory.getCurrentSession();
		User userRecord = session.get(User.class, user.getId());
		EntityUtils.copyNotNullProperties(user, userRecord);
		session.flush();
		return userRecord;
	}

}
