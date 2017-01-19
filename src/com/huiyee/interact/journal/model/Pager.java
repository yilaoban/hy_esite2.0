package com.huiyee.interact.journal.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pager implements Serializable {
	private static final long serialVersionUID = 2875790409419632498L;

	private int sizePerPage = 20;// 每页的个数

	private int PAGE_BAR_SIZE = 6;// 一共显示多少页

	private int currentPage; // 当前页

	private int totalCount; // 总的个数

	private int totalPage; // 总共多少页

	private int pageLimit; // 显示多少页的限制

	private boolean hasNextPage; // 有没有下一页

	private boolean hasPrevPage; // 有没有上一页

	private List<String> pageBarList = new ArrayList<String>();

	public Pager(int currentPage, int totalCount, int sizePerPage) {
		this.sizePerPage = sizePerPage;
		this.currentPage = currentPage;
		setTotalCount(totalCount);
	}

	public Pager() {

	}

	private void setTotalCount(int totalCount) {
		if (totalCount <= 0) {
			return;
		}
		this.totalCount = totalCount;

		if (this.pageLimit <= 0) {
			this.pageLimit = sizePerPage;
		}

		if (this.totalCount % this.pageLimit == 0) {
			this.totalPage = this.totalCount / this.pageLimit;
		} else {
			this.totalPage = this.totalCount / this.pageLimit + 1;
		}

		if (this.currentPage > this.totalPage) {
			this.currentPage = this.totalPage;
		} else if (this.currentPage <= 0) {
			this.currentPage = 1;
		}

		this.setPageBar();

		this.hasPrevPage = this.currentPage != 1;
		this.hasNextPage = this.currentPage != this.totalPage;

	}

	private void setPageBar() {
		if (this.totalPage <= PAGE_BAR_SIZE) {
			for (int i = 1; i <= this.totalPage; i++) {
				pageBarList.add(Integer.toString(i));
			}
		} else if (this.currentPage <= PAGE_BAR_SIZE / 2) {
			for (int i = 1; i <= PAGE_BAR_SIZE; i++) {
				pageBarList.add(Integer.toString(i));
			}
		} else if (this.currentPage >= this.totalPage - (PAGE_BAR_SIZE - 1) / 2) {
			for (int i = this.totalPage - PAGE_BAR_SIZE + 1; i <= this.totalPage; i++) {
				pageBarList.add(Integer.toString(i));
			}
		} else {
			for (int i = this.currentPage - PAGE_BAR_SIZE / 2; i < this.currentPage
					- PAGE_BAR_SIZE / 2 + PAGE_BAR_SIZE; i++) {
				pageBarList.add(Integer.toString(i));
			}
		}
	}

	public int getNextPage() {
		return this.currentPage + 1;
	}

	public int getPrevPage() {
		return this.currentPage - 1;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public boolean isHasPrevPage() {
		return hasPrevPage;
	}

	public void setHasPrevPage(boolean hasPrevPage) {
		this.hasPrevPage = hasPrevPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public List<String> getPageBarList() {
		return pageBarList;
	}

	public void setPageBarList(List<String> pageBarList) {
		this.pageBarList = pageBarList;
	}

	public int getPAGE_DEFAULT_LIMIT() {
		return sizePerPage;
	}

	public void setPAGE_DEFAULT_LIMIT(int page_default_limit) {
		sizePerPage = page_default_limit;
	}

	public int getPAGE_BAR_SIZE() {
		return PAGE_BAR_SIZE;
	}

	public void setPAGE_BAR_SIZE(int page_bar_size) {
		PAGE_BAR_SIZE = page_bar_size;
	}
}
