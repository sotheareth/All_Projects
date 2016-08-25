package com.kongkheang.kmb.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kongkheang.kmb.api.domain.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>, UserDaoExt {

}
