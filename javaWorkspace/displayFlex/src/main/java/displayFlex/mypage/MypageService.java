package displayFlex.mypage;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import displayFlex.event.dto.EventDto;
import displayFlex.mypage.vo.PageVo;
import displayFlex.serviceCenter.inquiry.vo.InquiryVo;
import test.JDBCTemplate;

public class MypageService {

	public int selectMypageCount() throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		MypageDao dao = new MypageDao();
		int cnt = dao.selectMypageCount(conn);
		
		//close
		JDBCTemplate.close(conn);
		
		return cnt;
	}

	public List<EventDto> selectEventList(PageVo pvo, String memberNo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		MypageDao dao = new MypageDao();
		List<EventDto> eventDtoList = dao.selectEventList(conn, pvo, memberNo);
		
		//close
		JDBCTemplate.close(conn);
		
		return eventDtoList;
	}

	public EventDto selectEventByNo(String eventNo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		MypageDao dao = new MypageDao();
		int result = dao.increaseHit(conn, eventNo);
		EventDto dto = null;
		if(result == 1) {
			JDBCTemplate.commit(conn);
			dto = dao.selectEventByNo(conn, eventNo);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return dto;
	}

	public List<InquiryVo> selectInquiryList(PageVo pvo, String memberNo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		MypageDao dao = new MypageDao();
		List<InquiryVo> inquiryVoList = dao.selectInquiryList(conn, pvo, memberNo);
		
		//close
		JDBCTemplate.close(conn);
		
		return inquiryVoList;
	}

	public int selectSearchInquiryCount(Map<String, String> m) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		MypageDao dao = new MypageDao();
		int cnt = dao.getInquiryCountBySearch(conn, m);
		
		//close
		JDBCTemplate.close(conn);
		
		return cnt;
		
	}

	public List<InquiryVo> search(Map<String, String> m, PageVo pvo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		MypageDao dao = new MypageDao();
		List<InquiryVo> inquiryVoList = dao.search(conn, m, pvo);
		
		JDBCTemplate.close(conn);
		
		return inquiryVoList;
		
		
	}

	public List<MoviePaymentVo> selectMoviePaymentList(PageVo pvo, String memberNo) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		MypageDao dao = new MypageDao();
		List<MoviePaymentVo> moviePaymentVoList = dao.selectMoviePaymentList(conn, pvo, memberNo);
		
		//close
		JDBCTemplate.close(conn);
		
		return moviePaymentVoList;
	}

	public List<CouponVo> selectCouponList(PageVo pvo, String memberNo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		MypageDao dao = new MypageDao();
		List<CouponVo> couponVoList = dao.selectCouponList(conn, pvo, memberNo);
		
		//close
		JDBCTemplate.close(conn);
		
		return couponVoList;
	}

	public int selectSearchCouponCount(Map<String, String> m) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		MypageDao dao = new MypageDao();
		int cnt = dao.getCouponCountBySearch(conn, m);
		
		//close
		JDBCTemplate.close(conn);
		
		return cnt;
	}

	public List<CouponVo> couponSearch(Map<String, String> m, PageVo pvo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		MypageDao dao = new MypageDao();
		List<CouponVo> couponVoList = dao.couponSearch(conn, m, pvo);
		
		JDBCTemplate.close(conn);
		
		return couponVoList;
	}

}
