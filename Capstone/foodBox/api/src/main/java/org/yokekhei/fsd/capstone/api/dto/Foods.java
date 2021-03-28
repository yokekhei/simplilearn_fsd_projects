package org.yokekhei.fsd.capstone.api.dto;

import java.util.List;

public class Foods {

	private List<Food> list;
	private PageInfo pageInfo;

	public Foods() {
	}

	public List<Food> getList() {
		return list;
	}

	public void setList(List<Food> list) {
		this.list = list;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	@Override
	public String toString() {
		return "Foods [list=" + list + ", pageInfo=" + pageInfo + "]";
	}

}
