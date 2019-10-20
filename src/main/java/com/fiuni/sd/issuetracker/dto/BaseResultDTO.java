package com.fiuni.sd.issuetracker.dto;



import java.io.Serializable;
import java.util.List;

public abstract class BaseResultDTO<DTO extends BaseDTO> implements Serializable {

	private List<DTO> dtos;
	private Integer lastPage;
	private Integer currentPage;
	private Integer currentPageTotalItems;
	private Long totalItems;

	protected List<DTO> getList() {
		return dtos;
	}

	protected void setList(List<DTO> dtos) {
		this.dtos = dtos;
	}

	public Integer getLastPage() {
		return lastPage;
	}

	public void setLastPage(Integer lastPage) {
		this.lastPage = lastPage;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Long totalItems) {
		this.totalItems = totalItems;
	}

	public Integer getCurrentPageTotalItems() {
		return currentPageTotalItems;
	}

	public void setCurrentPageTotalItems(Integer currentPageTotalItems) {
		this.currentPageTotalItems = currentPageTotalItems;
	}

	private static final long serialVersionUID = 1L;
}
