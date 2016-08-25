package com.kongkheang.kmb.api.audit;

import org.springframework.data.domain.AuditorAware;

/**
 * Spring Data Auditor to track user session
 * 
 * @author Mr.SAY SEAK LENG
 *
 */
public class UserSessionAuditorAware implements AuditorAware<String> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.AuditorAware#getCurrentAuditor()
	 */
	@Override
	public String getCurrentAuditor() {
		return "system";
	}

}
