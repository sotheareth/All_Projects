package com.kongkheang.kmb.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kongkheang.kmb.api.domain.Company;
import com.kongkheang.kmb.api.domain.User;
import com.kongkheang.kmb.api.domain.request.PageableRequestMessage;
import com.kongkheang.kmb.api.domain.response.PageableResponseMessage;
import com.kongkheang.kmb.api.domain.response.ResponseMessage;
import com.kongkheang.kmb.api.domain.response.ResponseMessageBuilder;
import com.kongkheang.kmb.api.service.CompanyService;
import com.kongkheang.kmb.api.util.EntityUtils;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	
	@GetMapping
	public ResponseMessage<List<Company>> findCompanyList(PageableRequestMessage pageable) {
		Page<Company> findCompanys = companyService.findCompanys(pageable);
		PageableResponseMessage<Company> pageableResponseMessage = new PageableResponseMessage<>(findCompanys);
		
		return ResponseMessageBuilder.instance(pageableResponseMessage)
		.build();
	}
	
	@GetMapping("/{companyId}")
	public ResponseMessage<List<Company>> findCompanyList(String companyName) {
		Company company = companyService.findOne(companyName);
		
		return ResponseMessageBuilder.instance(company)
		.build();
	}
}
