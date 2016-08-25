package com.kongkheang.kmb.api.domain.response;

import java.util.List;

import org.springframework.data.domain.Page;

import com.kongkheang.kmb.api.domain.request.PageableRequestMessage;

/**
 * Response Message with pagination support
 * @author sayseakleng
 *
 * @param <T>
 */
public class PageableResponseMessage <T> {
	/**
	 * Record count
	 */
	private long records;
	/**
	 * Page count
	 */
	private int pages;
	/**
	 * Item list
	 */
	private List<T> items;
	
	public PageableResponseMessage(PageableRequestMessage pageable, long records, List<T> items) {
		this.records = records;
		this.items = items;
		this.pages = (int) Math.ceil((double)records/ pageable.getLimit());
	}
	
	public PageableResponseMessage(Page<T> page) {
		this.records = page.getTotalElements();
		this.items = page.getContent();
		this.pages = page.getTotalPages();
	}
	
	public long getRecords() {
		return records;
	}
	public void setRecords(long records) {
		this.records = records;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
}
