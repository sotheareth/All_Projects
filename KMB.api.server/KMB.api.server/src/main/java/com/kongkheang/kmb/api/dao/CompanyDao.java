package com.kongkheang.kmb.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kongkheang.kmb.api.domain.Company;

public interface CompanyDao extends JpaRepository<Company, Long> {

}
