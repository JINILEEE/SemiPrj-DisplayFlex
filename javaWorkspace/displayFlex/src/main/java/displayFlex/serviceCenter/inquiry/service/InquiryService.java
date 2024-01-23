package displayFlex.serviceCenter.inquiry.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import displayFlex.serviceCenter.inquiry.dao.InquiryDao;
import displayFlex.serviceCenter.inquiry.dto.UpdateDto;
import displayFlex.serviceCenter.inquiry.vo.InquiryVo;
import displayFlex.serviceCenter.notice.dao.NoticeDao;
import displayFlex.serviceCenter.notice.vo.NoticeVo;
import displayFlex.util.page.vo.PageVo;
import test.JDBCTemplate;

public class InquiryService {

	//상영요청 목록 조회
	public List<InquiryVo> selectInquiryList(PageVo pvo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		InquiryDao dao = new InquiryDao();
		List<InquiryVo> inquiryVoList = dao.selectInquiryList(conn, pvo);
		
		// close
		JDBCTemplate.close(conn);
		
		return inquiryVoList;
	
	}

	//전체 게시글 갯수 조회
	public int selectInquiryCount() throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		InquiryDao dao = new InquiryDao();
		int cnt = dao.selectInquiryCount(conn);
		
		// close
		JDBCTemplate.close(conn);
		
		return cnt;
				
	}

	//게시글 상세조회
	public InquiryVo selectInquiryByNo(String onetooneNo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		InquiryDao dao = new InquiryDao();
		InquiryVo vo = dao.selectInquiryByNo(conn , onetooneNo);
		
//		List<ReplyVo> replyVoList = dao.getReplyList(conn, no);
		
		// close
		JDBCTemplate.close(conn);

		return vo;
	
	}

	//일대일 문의 작성
	public int write(InquiryVo vo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		InquiryDao dao = new InquiryDao();
		int result = dao.write(conn, vo);
		
		// tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		// close
		JDBCTemplate.close(conn);
		
		return result;
	
	}

	/**
	 * 답글 수정하기
	 * @param updateInquiry
	 * @return
	 * @throws SQLException 
	 */
	public int updateInquiry(UpdateDto updateInquiry) throws SQLException {
		Connection con = JDBCTemplate.getConnection();
		
		InquiryDao dao = new InquiryDao();
		int result = dao.updateInquiry(updateInquiry, con);
		
		if(result == 1) JDBCTemplate.commit(con);
		else JDBCTemplate.rollback(con);
		JDBCTemplate.close(con);
		return result;
	}

}
