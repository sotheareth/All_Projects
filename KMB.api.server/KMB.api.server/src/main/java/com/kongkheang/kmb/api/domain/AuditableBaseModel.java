package com.kongkheang.kmb.api.domain;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFilter;

/**
 * Base Entity which supports SpringData's Auditing
 * @author sayseakleng
 *
 * @param <ID>
 * @see Auditable
 */

@JsonFilter("SystemInfoFilter")
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableBaseModel<ID extends Serializable> extends BaseModel<ID> {
	
	private static final long serialVersionUID = -5299677512739983548L;
	
	@CreatedBy
	private String createdBy;
	
	@CreatedDate
	private Date creationDate;
	
	@LastModifiedBy
	String lastModifiedBy;
	
	@LastModifiedDate
	private Date lastModifiedDate;
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return creationDate;
	}

	public void setCreatedDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
		
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}
