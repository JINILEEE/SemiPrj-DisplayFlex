package displayFlex.ticketing.payment.service;

import java.sql.Connection;
import java.util.List;

import displayFlex.ticketing.payment.dao.PaymentDao;
import displayFlex.ticketing.payment.vo.PaymentVo;
import displayFlex.ticketing.payment.vo.SelectCouponVo;
import displayFlex.ticketing.payment.vo.UserGradeVo;
import test.JDBCTemplate;

public class PaymentService {

	public List<SelectCouponVo> getCouponList(String memberNo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		PaymentDao dao = new PaymentDao();
		List<SelectCouponVo> couponList = dao.getCouponList(conn, memberNo);
		
		JDBCTemplate.close(conn);
		
		return couponList;
	}

	public SelectCouponVo getSelectCouponInfo(String selectedRetainedNo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		PaymentDao dao = new PaymentDao();
		
		SelectCouponVo vo = dao.getSelectCouponInfo(selectedRetainedNo, conn);
		
		JDBCTemplate.close(conn);
		
		return vo;
	}

	public UserGradeVo getUserGrade(String memberNo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		PaymentDao dao = new PaymentDao();
		
		UserGradeVo vo = dao.getUserGrade(memberNo, conn);
		
		JDBCTemplate.close(conn);
		
		return vo;
	}

	public int setMoviePayment(PaymentVo paymentVo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		PaymentDao dao = new PaymentDao();
		
		String[] selectedSeat = paymentVo.getSelectedSeat();
		
		int result = 0;
		for(String seat : selectedSeat) {
					
			int payResult = dao.setMoviePayment(paymentVo, conn);
					
			if(payResult == 1) {
				int couponResult = 0;
				
				if(paymentVo.getRetainedNo() != null) {
					couponResult = dao.setCouponStatus(paymentVo.getRetainedNo(), conn);					
				} else {
					couponResult = 1;
				}
				
				JDBCTemplate.commit(conn);
				
				if(couponResult == 1) {
					JDBCTemplate.commit(conn);
					String foreignKey = dao.getForeignKey(paymentVo, conn);
					String seatNo = dao.getSeatNo(paymentVo.getSelectedTheater(), seat, conn);
					
					int ticketResult = dao.setTicket(seatNo, foreignKey, paymentVo, conn);
					
					if(ticketResult == 1) {
						JDBCTemplate.commit(conn);
						result = 1;
					} else {
						JDBCTemplate.rollback(conn);
					} 
				} else {
					JDBCTemplate.rollback(conn);
				} 
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

}
