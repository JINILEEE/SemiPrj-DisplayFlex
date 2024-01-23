package displayFlex.ticketing.select.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import displayFlex.ticketing.select.vo.ScreeningDateVo;
import displayFlex.ticketing.select.vo.SelectMovieVo;
import test.JDBCTemplate;

public class TicketSelectDao {

	// 상영중인 영화 리스트 받아오기
	public List<SelectMovieVo> getMovieList(Connection conn) throws Exception {
		
		String sql = "SELECT DISTINCT M.MOVIE_NO , M.MOVIE_NAME , M.MOVIE_IMAGE , M.SCREEN_GRADE_NO FROM SCREENING_INFO SI JOIN MOVIE M ON SI.MOVIE_NO = M.MOVIE_NO JOIN SCREENING_TIME ST ON SI.SCREENING_INFO_NO = ST.SCREENING_INFO_NO WHERE ST.START_TIME >= LOCALTIMESTAMP ORDER BY M.MOVIE_NO";
		String x = "SELECT SI.* , M.MOVIE_NAME, M.SCREEN_GRADE_NO, M.MOVIE_IMAGE , T.THEATER_NO , ST.SCREENING_TIME_NO, ST.START_TIME, ST.END_TIME FROM SCREENING_INFO SI JOIN SCREENING_TIME ST ON SI.SCREENING_INFO_NO = ST.SCREENING_INFO_NO JOIN MOVIE M ON M.MOVIE_NO = SI.MOVIE_NO JOIN THEATER T ON SI.THEATER_NO = T.THEATER_NO WHERE SI.START_DATE >= SYSDATE";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<SelectMovieVo> movieList = new ArrayList<SelectMovieVo>();
		
		while(rs.next()) {
			SelectMovieVo vo = new SelectMovieVo();
			vo.setMovieNo(rs.getString("MOVIE_NO"));
			vo.setMovieName(rs.getString("MOVIE_NAME"));
			vo.setMovieImage(rs.getString("MOVIE_IMAGE"));
			vo.setScreenGradeNo(rs.getString("SCREEN_GRADE_NO"));
			
			movieList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return movieList;
	}

	// 포스터이미지 URL 받아오기
	public String getMovieImageUrl(Connection conn, String movieNo) throws Exception {
		
		String sql = "SELECT MOVIE_IMAGE FROM MOVIE WHERE MOVIE_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, movieNo);
		ResultSet rs = pstmt.executeQuery();
		
		String movieImgUrl = "";
		if(rs.next()) {
			movieImgUrl = rs.getString("MOVIE_IMAGE");
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return movieImgUrl;
	}
	
	// 상영날짜 받아오기
	public List<ScreeningDateVo> getScreeningList(Connection conn, String movieNo) throws Exception {
		System.out.println("movieNo : " + movieNo);
		String sql = "SELECT DISTINCT TO_CHAR(T.START_TIME, 'YYYY-MM-DD') AS START_TIME, I.SCREENING_INFO_NO  FROM SCREENING_TIME T JOIN SCREENING_INFO I ON T.SCREENING_INFO_NO = I.SCREENING_INFO_NO WHERE I.MOVIE_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, movieNo);
		ResultSet rs = pstmt.executeQuery();
		
		List<ScreeningDateVo> screeningList = new ArrayList<ScreeningDateVo>();
		while(rs.next()) {
			String screeningInfoNo = rs.getString("SCREENING_INFO_NO");
//			String theaterNo = rs.getString("THEATER_NO");
//			String screeningTimeNo = rs.getString("SCREENING_TIME_NO");			
			String date = rs.getString("START_TIME");
			System.out.println("date : " + date);
			ScreeningDateVo vo = new ScreeningDateVo();
			
			vo.setScreeningInfoNo(screeningInfoNo);
			vo.setStartTime(date);
			
			screeningList.add(vo);
		}
//		System.out.println("screeningList" + screeningList);
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return screeningList;
	}

	// 상영시간(상영관) 받아오기
	public List<ScreeningDateVo> getScreeningTimeList(Connection conn, String movieNo, String selectedDate) throws Exception {

		String sql = "SELECT TO_CHAR(T.START_TIME, 'HH24:MI') AS TIME, I.THEATER_NO, T.SCREENING_TIME_NO FROM SCREENING_TIME T JOIN SCREENING_INFO I ON T.SCREENING_INFO_NO = I.SCREENING_INFO_NO WHERE I.MOVIE_NO = ? AND TO_CHAR(T.START_TIME, 'YYYY-MM-DD') = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, movieNo);
		pstmt.setString(2, selectedDate);
		System.out.println("selectedDate>>>>>>>>>>>> : " +  selectedDate );
		ResultSet rs = pstmt.executeQuery();
	
		List<ScreeningDateVo> screeningTimeList = new ArrayList<ScreeningDateVo>();
		while(rs.next()) {
			System.out.println("와일문 들어감ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
			String time = rs.getString("TIME");
			String theaterNo = rs.getString("THEATER_NO");
			String screeningTimeNo = rs.getString("SCREENING_TIME_NO");
			System.out.println("time : " + time);
			System.out.println("screeningTimeNo : " + screeningTimeNo);
			
			ScreeningDateVo vo = new ScreeningDateVo(time, theaterNo, screeningTimeNo);
			System.out.println("rsnext >>>>>>>>>" + time);
			screeningTimeList.add(vo);
		}
		
		
		return screeningTimeList;
	}

	// 좌석정보 받아오기
	public List<String> getSeatInfo(String theaterNo, String screeningTimeNo, Connection conn) throws Exception {
		
		String sql = "SELECT S.SEAT_NO FROM SEAT S INNER JOIN TICKET T ON S.SEAT_UNIQUE_NO = T.SEAT_UNIQUE_NO WHERE S.THEATER_NO = ? AND T.SCREENING_TIME_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, theaterNo);
		pstmt.setString(2, screeningTimeNo);

		ResultSet rs = pstmt.executeQuery();
		
		List<String> reservedSeatInfo = new ArrayList<String>();
		
		while(rs.next()) {
			String x = rs.getString("SEAT_NO");
			reservedSeatInfo.add(x);
		}
		
		
		
		
		
		return reservedSeatInfo;
	}

	
}
