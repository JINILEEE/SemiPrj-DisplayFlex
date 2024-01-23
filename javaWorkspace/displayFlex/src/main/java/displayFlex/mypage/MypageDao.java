package displayFlex.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import displayFlex.event.dto.EventDto;
import displayFlex.mypage.vo.PageVo;
import displayFlex.serviceCenter.inquiry.vo.InquiryVo;
import test.JDBCTemplate;

public class MypageDao {

	public int selectMypageCount(Connection conn) throws Exception {
		
		//sql
		String sql = "SELECT COUNT(*) as cnt FROM EVENT WHERE EVENT_PROGRESS = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cnt;
	}

	public List<EventDto> selectEventList(Connection conn, PageVo pvo, String memberNo) throws Exception {
		
		//sql
		String sql = "SELECT * FROM (SELECT ROWNUM AS RNUM, T.* FROM (SELECT E.EVENT_NO, M.MEMBER_NO, E.EVENT_TITLE, E.EVENT_PROGRESS, E.EVENT_STARTDATE, E.EVENT_ENDDATE, E.EVENT_HIT FROM EVENT E JOIN MEMBER M ON E.MEMBER_NO = M.MEMBER_NO JOIN EVENT_TYPE T ON E.EVENTTYPE_NO = T.EVENTTYPE_NO WHERE M.MEMBER_NO = ? AND E.EVENT_PROGRESS = 'N') T) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberNo);
		pstmt.setInt(2, pvo.getStartRow());
		pstmt.setInt(3, pvo.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<EventDto> eventDtoList = new ArrayList<EventDto>();
		while(rs.next()) {
			String eventNo = rs.getString("EVENT_NO");
			String eventTitle = rs.getString("EVENT_TITLE");
			String eventProgress = rs.getString("EVENT_PROGRESS");
			String eventStartdate = rs.getString("EVENT_STARTDATE");
			String eventEnddate = rs.getString("EVENT_ENDDATE");
			String eventHit = rs.getString("EVENT_HIT");
			
			EventDto dto = new EventDto();
			dto.setEventNo(eventNo);
			dto.setMemberNo(memberNo);
			dto.setEventTitle(eventTitle);
			dto.setEventProgress(eventProgress);
			dto.setEventStartdate(eventStartdate);
			dto.setEventEnddate(eventEnddate);
			dto.setEventHit(eventHit);
			
			eventDtoList.add(dto);

		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return eventDtoList;
	}
	
	//조회수 증가
	public int increaseHit(Connection conn, String eventNo) throws Exception {
		
		//sql
		String sql = "UPDATE EVENT SET EVENT_HIT = EVENT_HIT + 1 WHERE EVENT_NO = ? AND EVENT_PROGRESS = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, eventNo);
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
	
	}
	
	//이벤트 상세조회
	public EventDto selectEventByNo(Connection conn, String eventNo) throws Exception {
		//sql
		String sql = "SELECT E.EVENT_NO , E.EVENT_TITLE , E.EVENT_CONTENTS , E.EVENT_STARTDATE , E.EVENT_ENDDATE , E.EVENT_HIT FROM EVENT E WHERE E.EVENT_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, eventNo);
		ResultSet rs = pstmt.executeQuery();
		
		EventDto dto = null;
		if(rs.next()) {
			String eventNum = rs.getString("EVENT_NO");
			String eventTitle = rs.getString("EVENT_TITLE");
			String eventContents = rs.getString("EVENT_CONTENTS");
			String eventStartdate = rs.getString("EVENT_STARTDATE");
			String eventEnddate = rs.getString("EVENT_ENDDATE");
			String eventHit = rs.getString("EVENT_HIT");
			
			dto = new EventDto();
			dto.setEventNo(eventNum);
			dto.setEventTitle(eventTitle);
			dto.setEventContents(eventContents);
			dto.setEventStartdate(eventStartdate);
			dto.setEventEnddate(eventEnddate);
			dto.setEventHit(eventHit);
			
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return dto;
	}

	public List<InquiryVo> selectInquiryList(Connection conn, PageVo pvo, String memberNo) throws Exception {
		

		
		String sql = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT I.ONETOONE_NO , I.MEMBER_NO , I.TITLE , I.RE_TITLE , I.ENROLL_DATE , I.RE_ENROLL_DATE FROM INQUIRY I JOIN MEMBER M ON I.MEMBER_NO = M.MEMBER_NO WHERE I.MEMBER_NO = ? AND I.DELETE_YN = 'N' AND M.ADMIN_YN = 'N' ) T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberNo);
		pstmt.setInt(2, pvo.getStartRow());
		pstmt.setInt(3, pvo.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		List<InquiryVo> inquiryVoList = new ArrayList<InquiryVo>();
		while(rs.next()) {
			InquiryVo vo = new InquiryVo();

			String onetooneNo = rs.getString("ONETOONE_NO");
			String title = rs.getString("TITLE");
			String reTitle = rs.getString("RE_TITLE");
			String enrollDate = rs.getString("ENROLL_DATE");
			String reEnrollDate = rs.getString("RE_ENROLL_DATE");
			
			
			vo.setOnetooneNo(onetooneNo);
			vo.setMemberNo(memberNo);
			vo.setTitle(title);
			vo.setReTitle(reTitle);
			vo.setEnrollDate(reEnrollDate);
			vo.setReEnrollDate(reEnrollDate);
			
			inquiryVoList.add(vo);
			
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return inquiryVoList;
	}

	public int getInquiryCountBySearch(Connection conn, Map<String, String> m) throws Exception {
		
		InquiryVo vo = new InquiryVo();
		
		//sql
		String sql = "SELECT COUNT(*) FROM INQUIRY WHERE MEMBER_NO = ? AND DELETE_YN = 'N' AND " + m.get("searchType") + " LIKE '%' || ? || '%'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemberNo());
		pstmt.setString(2, m.get("searchType"));
		ResultSet rs = pstmt.executeQuery();
		
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cnt;
		
	}

	public List<InquiryVo> search(Connection conn, Map<String, String> m, PageVo pvo) throws Exception {
		
		String searchType = m.get("searchType");
		InquiryVo vo = new InquiryVo();
		
		//sql
		String sql = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT I.ONETOONE_NO , I.MEMBER_NO , I.TITLE , I.CONTENT , I.ENROLL_DATE , I.DELETE_YN , I.RE_TITLE , I.RE_CONTENT , I.RE_ENROLL_DATE FROM INQUIRY I JOIN MEMBER M ON I.MEMBER_NO = M.MEMBER_NO WHERE I.MEMBER_NO = ? AND I.DELETE_YN = 'N' AND I."+ searchType +" LIKE '%' || ? || '%' ) T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemberNo());
		pstmt.setString(2, m.get("searchValue"));
		pstmt.setString(3, "1");
		pstmt.setString(4, "10");
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<InquiryVo> inquiryVoList = new ArrayList<InquiryVo>();
			while(rs.next()) {
				
				String onetooneNo = rs.getString("ONETOONE_NO");
				String memberNo = rs.getString("MEMBER_NO");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String enrollDate = rs.getString("ENROLL_DATE");
				String deleteYn = rs.getString("DELETE_YN");
				String reTitle = rs.getString("RE_TITLE");
				String reContent = rs.getString("RE_CONTENT");
				String reEnrollDate = rs.getString("RE_ENROLL_DATE");
				
				
				vo.setOnetooneNo(onetooneNo);
				vo.setMemberNo(memberNo);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setEnrollDate(enrollDate);
				vo.setDeleteYn(deleteYn);
				vo.setReTitle(reTitle);
				vo.setReContent(reContent);
				vo.setReEnrollDate(reEnrollDate);
				
				inquiryVoList.add(vo);
			}
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			
			return inquiryVoList;
	}

public List<MoviePaymentVo> selectMoviePaymentList(Connection conn, PageVo pvo, String memberNo) throws Exception {
		
		//sql
				String sql = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT P.PAYMENTS_NO, P.MEMBER_NO, P.PAYMENT_DATE, P.PRICE, M.MOVIE_NAME FROM MOVIE_PAYMENT P JOIN TICKET T ON P.PAYMENTS_NO = T.PAYMENTS_NO JOIN SCREENING_TIME S ON T.SCREENING_TIME_NO = S.SCREENING_TIME_NO JOIN SCREENING_INFO I ON S.SCREENING_INFO_NO = I.SCREENING_INFO_NO JOIN MOVIE M ON I.MOVIE_NO = M.MOVIE_NO ) T ) WHERE RNUM BETWEEN ? AND ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pvo.getStartRow());
				pstmt.setInt(2, pvo.getLastRow());
				ResultSet rs = pstmt.executeQuery();
				
				//rs
				List<MoviePaymentVo> moviePaymentVoList = new ArrayList<MoviePaymentVo>();
				while(rs.next()) {
					String paymentsNo = rs.getString("PAYMENTS_NO");
					String movieName = rs.getString("MOVIE_NAME");
					String paymentDate = rs.getString("PAYMENT_DATE");
					String price = rs.getString("PRICE");
					
					MoviePaymentVo vo = new MoviePaymentVo();
					vo.setPaymentsNo(paymentsNo);
					vo.setMemberNo(memberNo);
					vo.setMovieName(movieName);
					vo.setPaymentDate(paymentDate);
					vo.setPrice(price);
					
					moviePaymentVoList.add(vo);

				}
				
				JDBCTemplate.close(rs);
				JDBCTemplate.close(pstmt);
				
				return moviePaymentVoList;
		
	}

          
	public List<CouponVo> selectCouponList(Connection conn, PageVo pvo, String memberNo) throws Exception {
		
		
		
		//sql
		String sql = "SELECT * FROM RETAINED_COUPON WHERE MEMBER_NO = ? AND ROWNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberNo);
		pstmt.setInt(2, pvo.getStartRow());
		pstmt.setInt(3, pvo.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<CouponVo> couponVoList = new ArrayList<CouponVo>();
		while(rs.next()) {
			
			CouponVo vo = new CouponVo();
			
			String retainedNo = rs.getString("RETAINED_NO");
			String couponNo = rs.getString("COUPON_NO");
			String retainedDate = rs.getString("RETAINED_DATE");
			String couponEnddate = rs.getString("COUPON_ENDDATE");
		
			
			vo.setRetainedNo(retainedNo);
			vo.setCouponNo(couponNo);
			vo.setRetainedDate(retainedDate);
			vo.setCouponEnddate(couponEnddate);
			vo.setMemberNo(memberNo);
			
			couponVoList.add(vo);

		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return couponVoList;
		
	}

	public int getCouponCountBySearch(Connection conn, Map<String, String> m) throws Exception {
		
		//sql
		String sql = "SELECT COUNT(*) FROM COUPON WHERE " + m.get("searchType") + " LIKE '%' || ? || '%'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, m.get("searchType"));
		ResultSet rs = pstmt.executeQuery();
		
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cnt;
	}

	public List<CouponVo> couponSearch(Connection conn, Map<String, String> m, PageVo pvo) throws Exception {
		String searchType = m.get("searchType");
		
		//sql
		String sql = "";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, m.get("searchValue"));
		pstmt.setString(2, "1");
		pstmt.setString(3, "10");
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<CouponVo> couponVoList = new ArrayList<CouponVo>();
			while(rs.next()) {
				
				String retainedNo = rs.getString("RETAINED_NO");
				String couponNo = rs.getString("COUPON_NO");
				String name = rs.getString("NAME");
				String retainedDate = rs.getString("RETAINED_DATE");
				String couponEnddate = rs.getString("COUPON_ENDDATE");
				String memberNo = rs.getString("MEMBER_NO");
				String couponStatus = rs.getString("COUPON_STATUS");
				
				CouponVo vo = new CouponVo();
				vo.setRetainedNo(retainedNo);
				vo.setCouponNo(couponNo);
				vo.setName(name);
				vo.setRetainedDate(retainedDate);
				vo.setCouponEnddate(couponEnddate);
				vo.setMemberNo(memberNo);
				vo.setCouponStatus(couponStatus);
				
				couponVoList.add(vo);
			}
			
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			
			return couponVoList;
		
     }

}
