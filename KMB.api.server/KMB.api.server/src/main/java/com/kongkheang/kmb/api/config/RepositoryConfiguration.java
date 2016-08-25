package com.kongkheang.kmb.api.config;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.kongkheang.kmb.api.audit.UserSessionAuditorAware;

/**
 * Spring Data JPA repository configuration
 * @author Mr.SAY SEAK LENG
 *
 */

@Configuration
@EnableJpaRepositories(
	basePackages = "com.kongkheang.kmb.api.dao", 
	repositoryImplementationPostfix = "Impl"
)
@EntityScan(basePackages = "com.kongkheang.kmb.api.domain")

@EnableJpaAuditing(
	modifyOnCreate = true,
	setDates = true,
	auditorAwareRef = "auditor"
)
public class RepositoryConfiguration {

	@Bean  
	public SessionFactory sessionFactory(HibernateEntityManagerFactory entityManagerFactory){  
	    return entityManagerFactory.getSessionFactory();  
	}
	
	@Bean(name = "auditor")
	public AuditorAware<String> auditor() {
		return new UserSessionAuditorAware();
	}
}
