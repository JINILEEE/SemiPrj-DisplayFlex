package displayFlex.serviceCenter.inquiry.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import displayFlex.serviceCenter.inquiry.dto.UpdateDto;
import displayFlex.serviceCenter.inquiry.vo.InquiryVo;
import displayFlex.serviceCenter.notice.vo.NoticeVo;
import displayFlex.util.page.vo.PageVo;
import test.JDBCTemplate;

public class InquiryDao {

	//상영요청 목록 조회
	public List<InquiryVo> selectInquiryList(Connection conn, PageVo pvo) throws Exception {

		//sql
		String sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT I.ONETOONE_NO , I.TITLE , I.CONTENT , I.ENROLL_DATE , M.MEMBER_ID AS WRITER_NICK , CASE WHEN RE_CONTENT = '답변을 남겨주세요.' THEN '대기' ELSE '완료' END AS STATE FROM INQUIRY I JOIN MEMBER M ON I.MEMBER_NO = M.MEMBER_NO WHERE I.DELETE_YN = 'N' ORDER BY ONETOONE_NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pvo.getStartRow());
	    pstmt.setInt(2, pvo.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<InquiryVo> inquiryVoList = new ArrayList<InquiryVo>();
		while(rs.next()) {
			String onetooneNo = rs.getString("ONETOONE_NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String enrollDate = rs.getString("ENROLL_DATE");
			String writerNick = rs.getString("WRITER_NICK");
			String state = rs.getString("STATE");
			
			InquiryVo vo = new InquiryVo();
			vo.setOnetooneNo(onetooneNo);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setEnrollDate(enrollDate);
			vo.setWriterNick(writerNick);
			vo.setState(state);
			
			inquiryVoList.add(vo);
	
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return inquiryVoList;
	}

	//전체 게시글 갯수 조회
	public int selectInquiryCount(Connection conn) throws Exception {

		//SQL
		String sql = "SELECT COUNT(*) AS CNT FROM INQUIRY WHERE DELETE_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
	      
		//rs
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt(1); //첫번째 열을 가져오겠다 라는 뜻, 계산에 써야되기 때문에 String이 아닌 Int로 받아줌
		}
		  
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		  
		return cnt;

	
	}

	//게시글 번호로 게시글 1개 조회
	public InquiryVo selectInquiryByNo(Connection conn, String onetooneNo) throws Exception {

	      //SQL
	      String sql = "SELECT I.ONETOONE_NO ,I.MEMBER_NO , M.MEMBER_ID AS WRITER_NICK , I.TITLE ,I.CONTENT , I.ENROLL_DATE , I.RE_TITLE , I.RE_CONTENT , I.RE_ENROLL_DATE , CASE WHEN I.RE_CONTENT = '답변을 남겨주세요.' THEN '대기' ELSE '완료' END AS STATE FROM INQUIRY I JOIN MEMBER M ON I.MEMBER_NO = M.MEMBER_NO WHERE I.ONETOONE_NO = ? AND I.DELETE_YN = 'N'";
	      PreparedStatement pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1, onetooneNo);
	      ResultSet rs = pstmt.executeQuery();
	      
	      //rs
	      InquiryVo vo = null;
	      if(rs.next()) {
	         String memberNo = rs.getString("MEMBER_NO");
	         String writerNick = rs.getString("WRITER_NICK");
	         String title = rs.getString("TITLE");
	         String content = rs.getString("CONTENT");
	         String enrollDate = rs.getString("ENROLL_DATE");
	         String reTitle = rs.getString("RE_TITLE");
	         String reContent = rs.getString("RE_CONTENT");
	         String reEnrollDate = rs.getString("RE_ENROLL_DATE");
	         String state = rs.getString("STATE");
	         
	         vo = new InquiryVo();
	         vo.setOnetooneNo(onetooneNo);
	         vo.setMemberNo(memberNo);
	         vo.setWriterNick(writerNick);
	         vo.setTitle(title);
	         vo.setContent(content);
	         vo.setEnrollDate(enrollDate);
	         vo.setReTitle(reTitle);
	         vo.setReContent(reContent);
	         vo.setReEnrollDate(reEnrollDate);
	         vo.setState(state);
	         
	      }
	      //close
	      JDBCTemplate.close(rs);
	      JDBCTemplate.close(pstmt);
	      
	      return vo;

	
	}

	//일대일 문의 작성
	public int write(Connection conn, InquiryVo vo) throws Exception {
		
		System.out.println("dao 's vo ::: " + vo);
		
		//SQL
		String sql = "INSERT INTO INQUIRY (ONETOONE_NO, MEMBER_NO, TITLE, CONTENT) VALUES (SEQ_NOTICE.NEXTVAL, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemberNo());
		pstmt.setString(2, vo.getTitle());
		pstmt.setString(3, vo.getContent());
		int result = pstmt.executeUpdate();
		  
		//close
		JDBCTemplate.close(pstmt);
		return result;
		
	}
	/**
	 * 답글 수정하기
	 * @param updateInquiry
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public int updateInquiry(UpdateDto updateInquiry, Connection con) throws SQLException {
		String query = "UPDATE INQUIRY SET RE_CONTENT = ?, RE_ENROLL_DATE= LOCALTIMESTAMP WHERE ONETOONE_NO = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, updateInquiry.getRecontent());
		pstmt.setString(2, updateInquiry.getNo());
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		return result;
	}

}
