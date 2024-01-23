package displayFlex.admincoupon.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import displayFlex.admincoupon.dao.AdminCouponDao;
import displayFlex.coupon.vo.CouponVo;
import test.JDBCTemplate;

public class AdminCouponService {

	public int couponCreate(CouponVo vo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		AdminCouponDao dao = new AdminCouponDao();
		int result = dao.create(conn, vo);

		// tx
		if (result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		// close
		JDBCTemplate.close(conn);

		return result;
	}

	public List<CouponVo> selectCouponList() throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		AdminCouponDao dao = new AdminCouponDao();
		List<CouponVo> couponVoList = dao.selectCouponList(conn);

		// close
		JDBCTemplate.close(conn);

		return couponVoList;

	}

//	public int selectBoardCount() throws SQLException {
//		Connection con = JDBCTemplate.getConnection();
//
//		int count =  AdminCouponDao.selectBoardCount(con);
//
//		JDBCTemplate.close(con);
//		return count;
//	}

}// class
