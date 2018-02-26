package com.acsm.training.model.basic;

import java.util.List;

/**
 * 
 * @author liuxiaobing
 *
 * @param <T>
 */
public class PageHelper<T> {

	private List<T> list;

	private int currentPage = 1;
	
	private int pageSize = 8;
	
	private int totalRow;
	
	private int start;
	
	private int end;
	
	private int totalPage;

	private int nowPageIndex;

	public void setNowPageIndex(int nowPageIndex) {
		this.nowPageIndex = nowPageIndex;
	}

	public int getNowPageIndex() {
		return nowPageIndex;
	}

	public PageHelper(){
		
	}
	
	public PageHelper(List<T> list,long totalRow,int currentPage,int pageSize){
		this.setList(list);
		this.totalRow=(int)totalRow;
		this.pageSize=pageSize;
		this.setCurrentPage(currentPage);
	}
	
	public PageHelper(List<T> list,int totalRow,int currentPage,int pageSize){
		this.setList(list);
		this.totalRow=totalRow;
		this.pageSize=pageSize;
		this.setCurrentPage(currentPage);
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {

		return end;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
		totalPage = (totalRow + pageSize - 1) / pageSize;
		if (totalPage < currentPage) {
			currentPage = totalPage;
		}
		//如果为第0页，则重置为第1页
		if(currentPage==0){
			currentPage=1;
		}
		start = pageSize * (currentPage - 1)+1;
		end = start + pageSize-1 > totalRow ? totalRow : start + pageSize-1;
	}

	public int getTotalPage() {

		return this.totalPage;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public List<T> getList() {
		return list;
	}

}
