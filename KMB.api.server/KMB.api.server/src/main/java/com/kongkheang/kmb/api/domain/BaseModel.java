package com.kongkheang.kmb.api.domain;
import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Base Document with filter some properties in serialization 
 * 
 * @author Mr.SAY SEAK LENG
 *
 * @param <ID>
 * @see Persistable
 */
@MappedSuperclass
public  class BaseModel<ID extends Serializable> implements Persistable<ID> {
	private static final long serialVersionUID = -749929199015919328L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected ID id;

	public void setId(ID id) {
		this.id = id;
	}
	
	@Override
	public ID getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Persistable#isNew()
	 */
	@Transient
	@JsonIgnore
	@Override
	public boolean isNew() {
		return getId() == null;
	}
}
