package displayFlex.ticketing.payment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import displayFlex.ticketing.payment.vo.PaymentVo;
import displayFlex.ticketing.payment.vo.SelectCouponVo;
import displayFlex.ticketing.payment.vo.UserGradeVo;
import test.JDBCTemplate;

public class PaymentDao {

	public List<SelectCouponVo> getCouponList(Connection conn, String memberNo) throws Exception {

		String sql ="SELECT RC.RETAINED_NO, RC.COUPON_NO, C.NAME, C.DISCOUNT, TO_CHAR(RC.COUPON_ENDDATE, 'YY-MM-DD') COUPON_ENDDATE FROM RETAINED_COUPON RC JOIN COUPON C ON C.NO = RC.COUPON_NO JOIN MEMBER M ON M.MEMBER_NO = RC.MEMBER_NO WHERE M.MEMBER_NO = ? AND RC.COUPON_STATUS = 'N' AND COUPON_DELETE = 'N' AND SYSDATE < RC.COUPON_ENDDATE";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberNo);
		
		ResultSet rs = pstmt.executeQuery();
		
		List<SelectCouponVo> couponList = new ArrayList<SelectCouponVo>();
		while(rs.next()) {
			String retainedNo = rs.getString("RETAINED_NO"); 
			String memberNo2 = memberNo;
			String couponNo = rs.getString("COUPON_NO");
			String name = rs.getString("NAME");
			String discount = rs.getString("DISCOUNT");
			String couponEnddate = rs.getString("COUPON_ENDDATE");
			
			SelectCouponVo vo = new SelectCouponVo(retainedNo, memberNo2, couponNo, name, discount, couponEnddate);
			
			couponList.add(vo);
		}

		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return couponList;
	}

	public SelectCouponVo getSelectCouponInfo(String selectedRetainedNo, Connection conn) throws Exception {

		String sql = "SELECT RC.COUPON_NO, C.NAME, C.DISCOUNT FROM RETAINED_COUPON RC JOIN COUPON C ON RC.COUPON_NO = C.NO WHERE RC.RETAINED_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, selectedRetainedNo);
		ResultSet rs = pstmt.executeQuery();
		
		SelectCouponVo vo = null;
		
		if(rs.next()) {
			String retainedNo = selectedRetainedNo;
			String couponNo = rs.getString("COUPON_NO");
			String name = rs.getString("NAME");
			String discount = rs.getString("DISCOUNT");

			vo = new SelectCouponVo(retainedNo, couponNo, name, discount);
		}
		
		
		System.out.println(vo);
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
	}

	public UserGradeVo getUserGrade(String memberNo, Connection conn) throws Exception {
		
		String sql = "SELECT GRADE_NO , PREVILEGED_YN , CASE WHEN TO_NUMBER(SUBSTR(REGISTER_NO, 8, 1)) IN (3, 4) THEN TRUNC((SYSDATE - TO_DATE('20' || SUBSTR(REGISTER_NO, 1, 6), 'YYYYMMDD')) / 365) ELSE TRUNC((SYSDATE - TO_DATE('19' || SUBSTR(REGISTER_NO, 1, 6), 'YYYYMMDD')) / 365) END AS AGE FROM MEMBER WHERE MEMBER_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberNo);
		
		ResultSet rs = pstmt.executeQuery();
		
		UserGradeVo vo = null;
		if(rs.next()) {
			String gradeNo = rs.getString("GRADE_NO");
			String previlegedYn = rs.getString("PREVILEGED_YN");
			String age = rs.getString("AGE");
			
			vo = new UserGradeVo(gradeNo, previlegedYn, age);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
	}

	public int setMoviePayment(PaymentVo paymentVo, Connection conn) throws Exception {
		
		String sql = "INSERT INTO MOVIE_PAYMENT(PAYMENTS_NO, MEMBER_NO, COUPON_NO, PRICE) VALUES(SEQ_MOVIE_PAYMENT.NEXTVAL, ?, ?, ?)";

		String[] selectedSeat = paymentVo.getSelectedSeat();
		
		int amount = Integer.parseInt(paymentVo.getTotalAmount())/selectedSeat.length;
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, paymentVo.getMemberNo());
		pstmt.setString(2, paymentVo.getRetainedNo());
		pstmt.setInt(3, amount);
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		
		return result;
	}

	public int setCouponStatus(String retainedNo, Connection conn) throws Exception {
		
		String sql = "UPDATE RETAINED_COUPON SET COUPON_STATUS = 'Y' WHERE RETAINED_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, retainedNo);
		
		int couponResult = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return couponResult;
	}

	public int setTicket(String seatNo, String foreignKey, PaymentVo paymentVo, Connection conn) throws Exception {
		
		String sql ="INSERT INTO TICKET(TICKET_NO, SEAT_UNIQUE_NO, PAYMENTS_NO, SCREENING_TIME_NO) VALUES(SEQ_TICKET.NEXTVAL, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, seatNo);
		pstmt.setString(2, foreignKey);
		pstmt.setString(3, paymentVo.getSelectedTimeNo());
	
		int ticketResult = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return ticketResult;
	}
	
	
	public String getForeignKey(PaymentVo paymentVo, Connection conn) throws Exception {
		
		String sql ="SELECT PAYMENTS_NO FROM MOVIE_PAYMENT ORDER BY PAYMENTS_NO DESC FETCH FIRST 1 ROW ONLY";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		String foreignKey = null;
		if(rs.next()) {
			foreignKey = rs.getString("PAYMENTS_NO");
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return foreignKey;
	}

	public String getSeatNo(String theater, String seat, Connection conn) throws Exception {
		
		String sql = "SELECT SEAT_UNIQUE_NO FROM SEAT WHERE THEATER_NO = ? AND SEAT_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, theater);
		pstmt.setString(2, seat);
		ResultSet rs = pstmt.executeQuery();
		
		String seatNo = null;
		if(rs.next()) {
			seatNo = rs.getString("SEAT_UNIQUE_NO");
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		
		return seatNo;
	}

}
