package displayFlex.serviceCenter.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import displayFlex.serviceCenter.faq.vo.FaqVo;
import displayFlex.serviceCenter.notice.vo.NoticeVo;
import displayFlex.util.page.vo.PageVo;
import test.JDBCTemplate;

public class NoticeDao {
	
	//공지사항 목록 조회
	public List<NoticeVo> selectNoticeList(Connection conn , PageVo pvo) throws Exception {
		
		//sql
		String sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM (SELECT NOTICE_NO ,TITLE ,CONTENT ,ENROLL_DATE ,MODIFY_DATE ,HIT FROM NOTICE WHERE DELETE_YN = 'N' ORDER BY NOTICE_NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pvo.getStartRow());
	    pstmt.setInt(2, pvo.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<NoticeVo> noticeVoList = new ArrayList<NoticeVo>();
		while(rs.next()) {
			String noticeNo = rs.getString("NOTICE_NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String enrollDate = rs.getString("ENROLL_DATE");
			String modifyDate = rs.getString("MODIFY_DATE");
			String hit = rs.getString("HIT");
			
			NoticeVo vo = new NoticeVo();
			vo.setNoticeNo(noticeNo);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setEnrollDate(enrollDate);
			vo.setModifyDate(modifyDate);
			vo.setHit(hit);
			
			noticeVoList.add(vo);
		}
		
		//result
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return noticeVoList;
	}

	//전체 게시글 갯수 조회
	public int selectNoticeCount(Connection conn) throws Exception {
		
		//SQL
		String sql = "SELECT COUNT(*) AS CNT FROM NOTICE WHERE DELETE_YN = 'N'";
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
	
	//공지사항 검색
	public List<NoticeVo> search(Connection conn, Map<String, String> m, PageVo pvo)  throws Exception {


	    // SQL
	    String sql = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT NOTICE_NO, TITLE, CONTENT, ENROLL_DATE, MODIFY_DATE, HIT FROM NOTICE WHERE DELETE_YN = 'N' AND (TITLE LIKE '%' || ? || '%' OR CONTENT LIKE '%' || ? || '%') ORDER BY NOTICE_NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";

	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, m.get("searchValue"));
	    pstmt.setString(2, m.get("searchValue"));
	    pstmt.setInt(3, pvo.getStartRow());
	    pstmt.setInt(4, pvo.getLastRow());
	    ResultSet rs = pstmt.executeQuery();

	    // rs
	    List<NoticeVo> noticeVoList = new ArrayList<NoticeVo>();
	    while (rs.next()) {
	        String noticeNo = rs.getString("NOTICE_NO");
	        String title = rs.getString("TITLE");
	        String content = rs.getString("CONTENT");
	        String enrollDate = rs.getString("ENROLL_DATE");
	        String modifyDate = rs.getString("MODIFY_DATE");
	        String hit = rs.getString("HIT");

	        NoticeVo vo = new NoticeVo();
	        vo.setNoticeNo(noticeNo);
	        vo.setTitle(title);
	        vo.setContent(content);
	        vo.setEnrollDate(enrollDate);
	        vo.setModifyDate(modifyDate);
	        vo.setHit(hit);

	        noticeVoList.add(vo);
	    }

	    // close
	    JDBCTemplate.close(rs);
	    JDBCTemplate.close(pstmt);

	    return noticeVoList;
	
	}
	
	//공지사항 검색 (제목+내용)
	public List<NoticeVo> searchByTitleAndContent(Connection conn, Map<String, String> m, PageVo pvo) throws Exception {
		
		String sql = "SELECT * FROM (SELECT ROWNUM RNUM, T.* FROM (SELECT NOTICE_NO, TITLE, CONTENT, ENROLL_DATE, MODIFY_DATE, HIT FROM NOTICE WHERE DELETE_YN = 'N' AND (TITLE LIKE '%' || ? || '%' OR CONTENT LIKE '%' || ? || '%') ORDER BY NOTICE_NO DESC) T) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "searchValue");
		pstmt.setString(2, "searchValue");
		pstmt.setInt(3, pvo.getStartRow());
		pstmt.setInt(4, pvo.getLastRow());
		ResultSet rs = pstmt.executeQuery();

        List<NoticeVo> noticeVoList = new ArrayList<NoticeVo>();
        while (rs.next()) {
            String noticeNo = rs.getString("NOTICE_NO");
            String title = rs.getString("TITLE");
            String content = rs.getString("CONTENT");
            String enrollDate = rs.getString("ENROLL_DATE");
            String modifyDate = rs.getString("MODIFY_DATE");
            String hit = rs.getString("HIT");

            NoticeVo vo = new NoticeVo();
            vo.setNoticeNo(noticeNo);
            vo.setTitle(title);
            vo.setContent(content);
            vo.setEnrollDate(enrollDate);
            vo.setModifyDate(modifyDate);
            vo.setHit(hit);

            noticeVoList.add(vo);
        }

        JDBCTemplate.close(rs);
        JDBCTemplate.close(pstmt);

        return noticeVoList;
    }
	

	// 게시글 갯수 조회 (검색값에 따라)
	public int getNoticeCountBySearch(Connection conn, Map<String, String> m) throws Exception {
		
		 // SQL
	    String sql = "SELECT COUNT(*) FROM NOTICE WHERE DELETE_YN = 'N' AND (TITLE LIKE '%' || ? || '%' OR CONTENT LIKE '%' || ? || '%')";

	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, m.get("searchValue"));
	    pstmt.setString(2, m.get("searchValue"));
	    ResultSet rs = pstmt.executeQuery();

	    // rs
	    int cnt = 0;
	    if (rs.next()) {
	        cnt = rs.getInt(1);
	    }

	    // close
	    JDBCTemplate.close(rs);
	    JDBCTemplate.close(pstmt);

	    return cnt;
	
	}

	//공지사항 작성
	public int write(Connection conn, NoticeVo vo) throws Exception {
		
		System.out.println("dao 's vo ::: " + vo);
		
		//SQL
		String sql = "INSERT INTO NOTICE (NOTICE_NO, TITLE, CONTENT) VALUES (SEQ_NOTICE.NEXTVAL, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		int result = pstmt.executeUpdate();
		  
		//close
		JDBCTemplate.close(pstmt);
		return result;
	}

	//게시글 번호로 게시글 1개 조회
	public NoticeVo selectNoticeByNo(Connection conn, String noticeNo) throws Exception {
		
		//SQL
	    String sql = "SELECT NOTICE_NO, TITLE , CONTENT , ENROLL_DATE , MODIFY_DATE, HIT FROM NOTICE WHERE NOTICE_NO = ? AND DELETE_YN = 'N'";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, noticeNo);
	    ResultSet rs = pstmt.executeQuery();
	      
	    //rs
	    NoticeVo vo = null;
	    if(rs.next()) {
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String enrollDate = rs.getString("ENROLL_DATE");
			String modifyDate = rs.getString("MODIFY_DATE");
			String hit = rs.getString("HIT");
	         
			vo = new NoticeVo();
			vo.setNoticeNo(noticeNo);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setEnrollDate(enrollDate);
			vo.setModifyDate(modifyDate);
			vo.setHit(hit);
		         
			System.out.println("dao 's vo ::: " + vo);
	    }
	    
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
		
	}

	//조회수 증가
	public int increaseHit(Connection conn, String noticeNo) throws Exception {
		
		//SQL
		String sql = "UPDATE NOTICE SET HIT = HIT + 1 WHERE NOTICE_NO = ? AND DELETE_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, noticeNo);
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
			      
		return result;
				
	}

	//공지사항 삭제
	public int delete(Connection conn, String noticeNo, String memberNo) throws Exception {
		
		//SQL
	    String sql = "UPDATE NOTICE SET DELETE_YN = 'Y' WHERE NOTICE_NO = ?";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, noticeNo);
	    int result = pstmt.executeUpdate();
	      
	    //close
	    JDBCTemplate.close(pstmt);
	      
	    return result;
		
	}

	//공지사항 수정
	public int updateNoticeByNo(Connection conn, NoticeVo vo) throws Exception {
		
		// SQL
	    String sql = "UPDATE NOTICE SET TITLE = ? , CONTENT = ? , MODIFY_DATE = LOCALTIMESTAMP , HIT = ? WHERE NOTICE_NO = ? AND DELETE_YN = 'N'";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, vo.getTitle());
	    pstmt.setString(2, vo.getContent());
	    pstmt.setString(3, vo.getHit());
	    pstmt.setString(4, vo.getNoticeNo());
	    int result = pstmt.executeUpdate();
	   
	    // close
	    JDBCTemplate.close(pstmt);
	   
	    return result;
	
	}

	

	

	

}
