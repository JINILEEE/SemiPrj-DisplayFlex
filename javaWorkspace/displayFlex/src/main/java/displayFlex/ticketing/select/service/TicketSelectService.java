package displayFlex.ticketing.select.service;

import java.sql.Connection;
import java.util.List;

import displayFlex.ticketing.select.dao.TicketSelectDao;
import displayFlex.ticketing.select.vo.ScreeningDateVo;
import displayFlex.ticketing.select.vo.SelectMovieVo;
import test.JDBCTemplate;

public class TicketSelectService {

	public List<SelectMovieVo> getMovieList() throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		TicketSelectDao dao = new TicketSelectDao();
		
		List<SelectMovieVo> movieList = (List<SelectMovieVo>) dao.getMovieList(conn); 
		
		JDBCTemplate.close(conn);
		
		return movieList;
	}

	public String getMovieImageUrl(String movieNo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		TicketSelectDao dao = new TicketSelectDao();
		
		String movieImgUrl = dao.getMovieImageUrl(conn, movieNo);
		
		JDBCTemplate.close(conn);
		
		return movieImgUrl;
	}

	public List<ScreeningDateVo> getScreeningList(String movieNo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		TicketSelectDao dao = new TicketSelectDao();
		
		List<ScreeningDateVo> screeningList = (List<ScreeningDateVo>) dao.getScreeningList(conn, movieNo); 
		
		JDBCTemplate.close(conn);
		
		return screeningList;
	}

	public List<ScreeningDateVo> getScreeningTimeList(String movieNo, String selectedDate) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		TicketSelectDao dao = new TicketSelectDao();
		
		List<ScreeningDateVo> screeningList = (List<ScreeningDateVo>) dao.getScreeningTimeList(conn, movieNo, selectedDate); 
		
		JDBCTemplate.close(conn);
		
		return screeningList;
	}

	public List<String> getSeatInfo(String theaterNo, String screeningTimeNo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		TicketSelectDao dao = new TicketSelectDao();
		
		List<String> reservedSeatList = dao.getSeatInfo(theaterNo, screeningTimeNo, conn);

		JDBCTemplate.close(conn);
		
		return reservedSeatList;
	}

	
	
}
