package com.min.www.util;

import java.awt.List;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.min.www.Service.IFBDao;
import com.min.www.dao.PagingUtilDAo;
import com.min.www.dto.PagingDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


public class PagingUtil {
	
	@Autowired
	PagingDto paiging;
	
	@Autowired
	SqlSession sqlSession;
	
	private int changedPage;
	private int changedTotalcount;
	
	
	
		
	
	
	public Map<String, Object> getBoardDetailList(HttpServletRequest request) {
		
		
		int totalCnt = 0;
		totalCnt = changedTotalcount; //전체페이지.
		
		int curPage = 1;
		if(changedPage != 0) {
			curPage = changedPage;
		}
		
		
		int countPerPage = 10; //  한 페이지에 몇개씩 보여줄건지
		paiging.setCountPerPage(countPerPage);
		
		int totalPage = totalCnt / countPerPage ; //총페이지 = totalCnt / 10
		paiging.setTotalPage(totalPage);
		
		int CountList = 10; // 목록 갯수 ◀[1],[2],[3],[4] ..... ▶
		paiging.setCountList(CountList);
		
		paiging.setTotalCnt(totalCnt); // 전체 게시글 수 
		
		int startPage = ((curPage) -1 /10 ) * 10 + 1;
		paiging.setStartPage(startPage);
		int endPage = startPage + CountList -1;
		paiging.setEndPage(endPage);
		
		
		
		if(totalCnt % countPerPage > 0) { 
			//게시물이 한페이지에 딱 안떨어지고 10개이하로 남았을땐
			//페이지수를 하나 늘려준다.
		totalPage++;
		paiging.setTotalPage(totalPage);
			
		}
		
		if(totalPage < curPage) {
			// 누른 페이지가 전체페이지보다 많았을 경우
			
			curPage =totalPage;
			paiging.setCurPage(curPage);
		}
	
		if(endPage > totalPage ) {
			//마지페이지가 토탈페이지보다 높아버리면 안됨.
			endPage = totalPage;
			paiging.setEndPage(endPage);
		}
//		
//		if(startPage > 1) {
//			//콘솔에 찍기.
//		System.out.println("<a href="\"?curpage=1\"">처음<);
//		}
		
		
	
		
		
		
		
		
		return null;
	}
	
	



	private static Object getPageObject(int totalCnt, int parseInt) {
		// TODO Auto-generated method stub
		return null;
	}





	private static void setPageInfo(HttpServletRequest request, int i) {
		// TODO Auto-generated method stub
		
	}
	



	

}
