package org.yokekhei.fsd.capstone.api.dto;

public class PageInfo {

	int page;
	int size;
	int numberOfElements;
	long totalElements;
	int totalPages;
	String sortBy;

	public PageInfo() {
	}

	public PageInfo(int page, int size) {
		this.page = page;
		this.size = size;
	}

	public PageInfo(int page, int size, String sortBy) {
		this.page = page;
		this.size = size;
		this.sortBy = sortBy;
	}

	public PageInfo(int page, int size, int numberOfElements, long totalElements, int totalPages, String sortBy) {
		this.page = page;
		this.size = size;
		this.numberOfElements = numberOfElements;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.sortBy = sortBy;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	@Override
	public String toString() {
		return "PageInfo [page=" + page + ", size=" + size + ", numberOfElements=" + numberOfElements
				+ ", totalElements=" + totalElements + ", totalPages=" + totalPages + ", sortBy=" + sortBy + "]";
	}

}
