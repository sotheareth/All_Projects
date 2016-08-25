package com.kongkheang.kmb.api.domain.request;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 * Represent Pageable Data to filter on Database
 * @author sayseakleng
 *
 */
public class PageableRequestMessage {
	/**
	 * First index will start by 0
	 */
	public static final int DEFAULT_OFFSET 					= 0;
	public static final int DEFAULT_LIMIT 					= 10;
	public static final Direction DEFAULT_SORT_Direction 	= Direction.ASC;
	
	private int offset = DEFAULT_OFFSET;
	private int limit = DEFAULT_LIMIT;
	private Direction sortDirection = DEFAULT_SORT_Direction;
	private String sortProperty;
	
	public PageableRequestMessage() {
		
	}
	
	public PageableRequestMessage(int offset, int limit) {
		this.offset = offset;
		this.limit = limit;
	}
	
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public void setSort(String sort) {
		if("-".equalsIgnoreCase(sort.substring(0, 1))) {
			sortDirection = Direction.DESC;
			sortProperty = sort.substring(1);
		}
		else {
			sortProperty = sort;
		}
	}
	public Direction getSortDirection() {
		return sortDirection;
	}
	public void setSortDirection(Direction sortDirection) {
		this.sortDirection = sortDirection;
	}
	public String getSortProperty() {
		return sortProperty;
	}
	public void setSortProperty(String sortProperty) {
		this.sortProperty = sortProperty;
	}
	
	/**
	 * Generate Spring Data Pageable
	 * 
	 * @see PageRequest
	 * @return
	 * 
	 */
	public Pageable generatePageable() {
		Pageable Pageable = null;
		
		if(getSortProperty() != null && getSortDirection() != null) {
			Pageable = new PageRequest(getOffset(), getLimit(), new Sort(getSortDirection(), getSortProperty()));
		}
		else {
			
			Pageable = new PageRequest(getOffset(), getLimit());
		}
		
		return Pageable;
	}
	
	@Override
	public String toString() {
		return "PageableRequestMessage [offset=" + offset + ", limit=" + limit + ", sortDirection=" + sortDirection
				+ ", sortProperty=" + sortProperty + "]";
	}
} 
