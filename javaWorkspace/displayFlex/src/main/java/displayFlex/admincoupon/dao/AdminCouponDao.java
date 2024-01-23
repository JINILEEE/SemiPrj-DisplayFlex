package displayFlex.admincoupon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import displayFlex.coupon.vo.CouponVo;
import test.JDBCTemplate;

public class AdminCouponDao {
	
	public int create(Connection conn, CouponVo vo) throws Exception {
	      
	      //SQL
	      String sql = "INSERT INTO COUPON\r\n"
	      		+ "                (\r\n"
	      		+ "                 NO --1\r\n"
	      		+ "                , TYPE --2\r\n"
	      		+ "                , DISCOUNT\r\n"
	      		+ "                , NAME\r\n"
	      		+ "                , CREATIONDATE\r\n"
	      		+ "                , VALIDPERIOD\r\n"
	      		+ "                , COUPON_INFO\r\n"
	      		+ "                )\r\n"
	      		+ "                \r\n"
	      		+ "        VALUES(\r\n"
	      		+ "                (SELECT MAX(NO) + 1 FROM COUPON)--1\r\n"
	      		+ "                ,?--2 type\r\n"
	      		+ "                ,?--3 discoupon\r\n"
	      		+ "                ,?--4 name\r\n"
	      		+ "                ,?--5 creationdate\r\n"
	      		+ "                ,?--6 validperiod\r\n"
	      		+ "                ,?--7 info\r\n"
	      		+ "                )";
	      PreparedStatement pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1, vo.getCouponType());
	      pstmt.setString(2, vo.getCouponDiscount());
	      pstmt.setString(3, vo.getCouponName());
	      pstmt.setString(4, vo.getCouponCreationDate());
	      pstmt.setString(5, vo.getCouponValidePeriod());
	      pstmt.setString(6, vo.getCouponInfo());
	      
	      //
	      int result = pstmt.executeUpdate();	
	      
	      //close
	      JDBCTemplate.close(pstmt);
	      
	      return result;
	      
	      
	   }
	//쿠폰 조회
	public List<CouponVo> selectCouponList(Connection conn) throws Exception{
	      
	      //SQL
	      String sql = "select\r\n"
	      		+ "     a.NO\r\n"
	      		+ "    ,a.TYPE\r\n"
	      		+ "    ,a.DISCOUNT\r\n"
	      		+ "    ,a.NAME\r\n"
	      		+ "    ,a.CREATIONDATE\r\n"
	      		+ "    ,a.VALIDPERIOD\r\n"
	      		+ "    ,a.COUPON_INFO\r\n"
	      		+ " from coupon a \r\n"
	      		+ " order by no";
	      PreparedStatement pstmt = conn.prepareStatement(sql);
	      
	      
	      
//	      pstmt.setInt(1, pvo.getStartRow());
//	      pstmt.setInt(2, pvo.getLastRow());
	      
	      
	      
	      
	      ResultSet rs = pstmt.executeQuery();
	   
	      //rs
	      List<CouponVo> couponVoList = new ArrayList<CouponVo>();
	      while(rs.next()) {
	         
	         String no = rs.getString("NO");
	         String type= rs.getString("TYPE");
	         String discount = rs.getString("DISCOUNT");
	         String name = rs.getString("NAME");
	         String creationDate = rs.getString("CREATIONDATE");
	         String validPeriod = rs.getString("VALIDPERIOD");
	         String info = rs.getString("COUPON_INFO");
	         
	         
	         CouponVo vo = new CouponVo();
	         vo.setCouponNo(no);
	         vo.setCouponType(type);
	         vo.setCouponDiscount(discount);
	         vo.setCouponName(name);
	         vo.setCouponCreationDate(creationDate);
	         vo.setCouponValidePeriod(validPeriod);
	         vo.setCouponInfo(info);
	         
	         
	         couponVoList.add(vo);
	         
	      }
	      
	      
	      
//	      public int selectBoardCount(Connection con) throws SQLException {
//	  		query = "SELECT COUNT(*) FROM COUPON";
//	  		
//	  		PreparedStatement pstmt = con.prepareStatement(query);
//	  		ResultSet rs = pstmt.executeQuery();
//	  		int result = 0;
//	  		
//	  		if(rs.next()) {
//	  			result = rs.getInt(1);
//	  		}
//	  		
//	  		JDBCTemplate.close(rs);
//	  		JDBCTemplate.close(pstmt);
//	  		return result;
//	  	}
	      
	      
	      //close
	      JDBCTemplate.close(pstmt);
	      JDBCTemplate.close(rs);
	      
	      return couponVoList;
	   }

}
