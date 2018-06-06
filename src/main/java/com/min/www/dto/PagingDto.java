package com.min.www.dto;

public class PagingDto {

	
	private int totalCnt; // 총 게시 갯수.
	private int totalPage; // 총 페이지
	private int countPerPage;//한 페이지에 몇개씩 보여줄건지 정하는 변수.
	private int countList; // 목록 갯수 ◀[1],[2],[3],[4] ..... ▶
	private int startPage; //시작페이지.
	private int endPage; //마지막페이지.
	private int curPage; // 현재페이지.

	
	
	
	public PagingDto() {
		// TODO Auto-generated constructor stub
	}
	
	public PagingDto(int totalCnt,int countPerPage, int countList, int startPage,int endPage,
	int curPage, int totalPage) {
		// TODO Auto-generated constructor stub
		this.totalCnt=totalCnt;
		this.countPerPage=countPerPage;
		this.countList = countList;
		this.startPage = startPage;
		this.endPage = endPage;
		this.curPage = curPage;
		this.totalPage = totalPage;
	
		
	}
	
	
	
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	public int getCountPerPage() {
		return countPerPage;
	}
	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}
	public int getCountList() {
		return countList;
	}
	public void setCountList(int countList) {
		this.countList = countList;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Paing [totalCnt = " + totalCnt + "countPerPage" + countPerPage + "countList" + countList +
				"startPage" + startPage +
				"endPage" + endPage +
				"curPage" + curPage +
				"TotalPage" + totalPage + "]";
				
	}
	
}
