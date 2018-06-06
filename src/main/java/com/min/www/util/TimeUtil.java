package com.min.www.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.www.Service.BoardService;
import com.min.www.dao.BoardDao;
import com.min.www.dto.BoardDto;
import com.min.www.dto.BoardReplyDto;
import com.mysql.fabric.xmlrpc.base.Data;

/*
 * 
 * 시간에 관련된 클래스 
 * 
 * 
 * 		
 		System.out.println("연도만 :" +currentTime.substring(0, 4));
		System.out.println("월만  :" +currentTime.substring(5, 7));
		System.out.println("일만 :" +currentTime.substring(8, 10));
		System.out.println("시간만 :" +currentTime.substring(11, 13));
		System.out.println("분만 :" +currentTime.substring(14, 16));
		System.out.println("초만 :" +currentTime.substring(17, 19));
		
 */
@Service
public class TimeUtil {


	/*
	 * 게시물 시간 출력 함수 만약 오늘 날짜면 시간만 출력 된다. 오늘 날짜가 아니면 날짜만 출력 되기.
	 * 
	 */
	public static List<BoardDto> TimeUtilChanger(List<BoardDto> resultDto) {

		System.out.println("시간 씻기기.");
		// 시간 가져오기.
		Data data = new Data();
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

		String today = getToday();

		// mysql 게시물 오늘날짜이면 시간으로 표시, 아니면 날짜로만 표시.
		for (int i = 0; i < resultDto.size(); i++) {
			if (resultDto.get(i).getWritertime().substring(0, 10).equals(today)) {
				System.out.println("시간 변환.");
				resultDto.get(i).setWritertime(resultDto.get(i).getWritertime().substring(11, 16));

			} else {
				resultDto.get(i).setWritertime(resultDto.get(i).getWritertime().substring(5, 10));
			}

		}
		/*
		 * while(itrresultDto.hasNext()){
		 * System.out.println(itrresultDto.next().getWritertime().substring(0,
		 * 10).equals(today)); if(itrresultDto.next().getWritertime().substring(0,
		 * 10).equals(today)) { //만약 게시글이 오늘 날짜에 쓰어진거라면.
		 * itrresultDto.next().setWritertime(
		 * itrresultDto.next().getWritertime().substring(11, 16));
		 * System.out.println("오늘 날자의 게시물 날짜 " + itrresultDto.next().getWritertime());
		 * 
		 * System.out.println(itrresultDto.next().getWritertime());
		 * System.out.println("시간 변환작업완료!!"); }else { itrresultDto.next().setWritertime(
		 * itrresultDto.next().getWritertime().substring(5, 10)); }
		 * 
		 * }
		 */

		return resultDto;

	}

	// 현재날짜 가져오기.
	public static String getToday() {

		Date today = new Date();

		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		String currentTime = time.format(today);

		System.out.println("현재 시간 : " + currentTime);

		System.out.println(" DB 조회를 위해 자를 시간 :" + currentTime.substring(0, 10));

		currentTime = currentTime.substring(0, 10);

		return currentTime;
	}
	
	
/*
 * 현재 년도,월,일,시간,분,초 가져옴.
 */
	public static String getCurrentTime() {

		Date today = new Date();

		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		String currentTime = time.format(today);

		return currentTime;

	}
	/*
	 * http://blog.naver.com/PostView.nhn?blogId=minilove717&logNo=220686303157&
	 * parentCategoryNo=&categoryNo=28&viewDate=&isShowPopularPosts=false&from=
	 * postView
	 */

	/*
	 * 댓글 실시간 알림을 위한 시간메소드 .
	 * 
	 */
	public static int timeComparison(Map<String, Object> paramMap,List<BoardReplyDto> boardReplyDtos) {

//		boolean yearFlag = false;
//		boolean monthFlag = false;
//		boolean dateFlag = false;
//		boolean timeFlag = false;
//		boolean minuteFlag = false;
//		boolean secondFlag = false;
		try {
		System.out.println("###### timeComparision() #######");
		//로그아웃한 시간이 들어갈것임.
		String currentTIme = getCurrentTime();
		
		// 게시물 댓글 dto 가쟈오고 ,
	
		int count =boardReplyDtos.size();
	
		System.out.println("까운트 ? :" +count);
		
	
	
		
		// 로그아웃한 시점의시간을 가져오기 지금은 테스트용 변수 넣음.
		String logoutDateTime = "2018-02-03 13:01:01";

		// 시간을 잘라서 비교하기 .
		//현재 시간
		int year =  Integer.parseInt(currentTIme.substring(0, 4));
		int month =  Integer.parseInt(currentTIme.substring(5, 7));
		int date =  Integer.parseInt(currentTIme.substring(8, 10));
		int time =  Integer.parseInt(currentTIme.substring(11, 13));
		int minute =  Integer.parseInt(currentTIme.substring(14, 16));
		int second =  Integer.parseInt(currentTIme.substring(17, 19));
		
		// 로그아웃한 시간 
		int pYear =  Integer.parseInt(logoutDateTime.substring(0, 4));
		int pMonth =  Integer.parseInt(logoutDateTime.substring(5, 7));
		int pDate =  Integer.parseInt(logoutDateTime.substring(8, 10));
		int pTime =  Integer.parseInt(logoutDateTime.substring(11, 13));
		int pMinute =  Integer.parseInt(logoutDateTime.substring(14, 16));
		int pSecond =  Integer.parseInt(logoutDateTime.substring(17, 19));
		
		/*
		 * 
		 * 
		 * for문을 통해서 List에서 한개씩 지우는 것보다
		 * 새로운 List에서 넣어주는게 더 빠를듯 하다.
		 * 
		 */
		
		List<BoardReplyDto> newReplyDto = new ArrayList<>();
		
		
	
		
		
		for(int i=0; i<count; i++) {
			System.out.println("for문 동작");
			Date regDate = boardReplyDtos.get(i).getRegister_datetime();
			System.out.println("######### regDate출력 ##########");
			System.out.println(boardReplyDtos.get(i).getRegister_datetime());
			
			System.out.println("######### regDate  끝 ##########");
			SimpleDateFormat time1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String dbRegDate = time1.format(regDate);
			
			
			year =  Integer.parseInt(dbRegDate.substring(0, 4));
			month =  Integer.parseInt(dbRegDate.substring(5, 7));
			date =  Integer.parseInt(dbRegDate.substring(8, 10));
			time =  Integer.parseInt(dbRegDate.substring(11, 13));
			minute =  Integer.parseInt(dbRegDate.substring(14, 16));
			second =  Integer.parseInt(dbRegDate.substring(17, 19));
			System.out.println("ㅇㄷ?");
			
			if(year>pYear || month>pMonth || date>pDate || time>pTime || minute>pMinute || second>pSecond  ) {
				newReplyDto.add(boardReplyDtos.get(i));
				
			}
//			if(month>pMonth) {
//				newReplyDto.add(boardReplyDtos.get(i));
//				break;
//			}
//			if(date>pDate) {
//				newReplyDto.add(boardReplyDtos.get(i));
//				break;
//			}
//			if(time>pTime) {
//				newReplyDto.add(boardReplyDtos.get(i));
//				break;
//			}
//			if(minute>pMinute) {
//				newReplyDto.add(boardReplyDtos.get(i));
//				break;
//			}
//			if(second>pSecond) {
//				newReplyDto.add(boardReplyDtos.get(i));
//				break;
//			}
			

			
		}
		
		int result = newReplyDto.size(); // 새로운 알림 갯수.
		
		System.out.println("새로운 알림 갯수 : " + result);
		
		for(int i=0; i<newReplyDto.size(); i++) {
			System.out.print(newReplyDto.get(i).getRegister_datetime());
		}
		
		
		
		return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
		
		
		
				

		 

		
	}
}
