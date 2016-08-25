package com.kongkheang.kmb.api.test.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.kongkheang.kmb.api.domain.Company;
import com.kongkheang.kmb.api.domain.User;
import com.kongkheang.kmb.api.service.CompanyService;
import com.kongkheang.kmb.api.service.UserService;
import com.kongkheang.kmb.api.test.AbstractTestCase;
import com.kongkheang.kmb.api.util.Gender;

public class TestCompanyService extends AbstractTestCase {
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Test
	@Transactional
	@Rollback(false)
	public void testSave() {
		Company company = new Company("Dara", 10);

		User user1 = new User("test2", Gender.F);
		user1.setCompany(company);
		
		User user2 = new User("test1", Gender.M);
		user2.setCompany(company);
		

		List<User> users = Arrays.asList(user1, user2);
		company.setUsers(users);
		
		Company save = companyService.save(company);
		
		
		////////////////////save company2
		Company company2 = new Company("test2");
		User user3 = new User("test3", Gender.F);
		user3.setCompany(company2);
		
		User user4 = new User("test4", Gender.M);
		user4.setCompany(company2);

		List<User> users2 = Arrays.asList(user3, user4);
		company2.setUsers(users2);
		
		companyService.save(company2);
		
//		assertNotNull(save);
//		prettyPrint(save);
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void testUser(){
		// dara test 2 final
		User user = (User) userService.findOne("test1");
		prettyPrint(user);
	}
	
	@Test
	@Transactional
	public void testFindCompanyByInnerJoin() {
		List<Company> company = companyService.getCompanyByInnerJoin(new Company("Dara"));
		Hibernate.initialize(company);
		prettyPrint(company);
		
//		Object companys = companyService.findByAge(10);
//		prettyPrint(companys);
	}
	
	@Test
	@Transactional
	public void testFindCompanyBySQL(){
		
		List<Company> result = companyService.getDataListBySQL(1L);
		prettyPrint(result);
		
	}

	@Test
	@Transactional
	public void testFindCompanys() {
		//Company company = companyService.findOne("dara");
		Collection<Company> companys = companyService.getAll();
		
		for(Company company : companys){
			Collection<User> users = company.getUsers();
			prettyPrint(users);
		}
		
		//prettyPrint(company);
		
//		Company company2 = companyService.findCompany(new Company("ra"));
//		prettyPrint(company2);
		
		
//		Company company = companyService.findOneByCriteria(new Company("Dara"));
//		prettyPrint(company);
		
//		List<Company> result = (List<Company>) companyService.findByAge(20);
//		prettyPrint(result);
//		System.out.println(1);
	}

}