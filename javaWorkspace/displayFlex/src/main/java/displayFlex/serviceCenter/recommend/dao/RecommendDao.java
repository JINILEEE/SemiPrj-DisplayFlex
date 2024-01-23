package displayFlex.serviceCenter.recommend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import displayFlex.serviceCenter.recommend.vo.RecommendVo;
import displayFlex.util.page.vo.PageVo;
import test.JDBCTemplate;

public class RecommendDao {

   //상영요청 목록 조회
   public List<RecommendVo> selectRecommendList(Connection conn, PageVo pvo) throws Exception {

      //sql
      String sql = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT R.RECOMMEND_MV_NO, R.YEAR, R.TITLE ,R.CONTENT ,R.MEMBER_NO ,R.ENROLL_DATE ,R.MODIFY_DATE, R.RECOMMEND_COUNT ,R.HIT , M.MEMBER_ID AS WRITER_NICK FROM RECOMMEND_MV R JOIN MEMBER M ON R.MEMBER_NO = M.MEMBER_NO WHERE R.DELETE_YN = 'N' ORDER BY RECOMMEND_MV_NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, pvo.getStartRow());
       pstmt.setInt(2, pvo.getLastRow());
      ResultSet rs = pstmt.executeQuery();
      
      //rs
      List<RecommendVo> recommendVoList = new ArrayList<RecommendVo>();
      while(rs.next()) {
         String recommendMvNo = rs.getString("RECOMMEND_MV_NO");
         String year = rs.getString("YEAR");
         String title = rs.getString("TITLE");
         String content = rs.getString("CONTENT");
         String memberNo = rs.getString("MEMBER_NO");
         String enrollDate = rs.getString("ENROLL_DATE");
         String modifyDate = rs.getString("MODIFY_DATE");
         String recommendCount = rs.getString("RECOMMEND_COUNT");
         String hit = rs.getString("HIT");
         String writerNick = rs.getString("WRITER_NICK");
         
         RecommendVo vo = new RecommendVo();
         vo.setRecommendMvNo(recommendMvNo);
         vo.setYear(year);
         vo.setTitle(title);
         vo.setContent(content);
         vo.getMemberNo();
         vo.setEnrollDate(enrollDate);
         vo.setModifyDate(modifyDate);
         vo.setRecommendCount(recommendCount);
         vo.setHit(hit);
         vo.setWriterNick(writerNick);
         
         recommendVoList.add(vo);
      }
      
      //result
      JDBCTemplate.close(rs);
      JDBCTemplate.close(pstmt);
      
      return recommendVoList;
            
   }

   //전체 게시글 갯수 조회
   public int selectRecommendCount(Connection conn) throws Exception  {
      
      //SQL
      String sql = "SELECT COUNT(*) AS CNT FROM RECOMMEND_MV WHERE DELETE_YN = 'N'";
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

   //검색
   public List<RecommendVo> search(Connection conn, Map<String, String> m, PageVo pvo) throws Exception {
      
	   // 예시 코드
	   String searchType = m.get("searchType");

	   String sql = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT R.RECOMMEND_MV_NO, R.MEMBER_NO, M.MEMBER_ID AS WRITER_NICK, R.YEAR, R.TITLE , R.CONTENT , R.ENROLL_DATE , R.MODIFY_DATE , R.RECOMMEND_COUNT, R.HIT FROM RECOMMEND_MV R JOIN MEMBER M ON R.MEMBER_NO = M.MEMBER_NO WHERE R.DELETE_YN = 'N' AND " + m.get("searchType") + " LIKE '%' || ? || '%' ORDER BY R.RECOMMEND_MV_NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
	   PreparedStatement pstmt = conn.prepareStatement(sql);
	   pstmt.setString(1, m.get("searchValue"));
	   pstmt.setInt(2, pvo.getStartRow());
	   pstmt.setInt(3, pvo.getLastRow());
	   ResultSet rs = pstmt.executeQuery();
      
      // rs
       List<RecommendVo> recommendVoList = new ArrayList<RecommendVo>();
       while(rs.next()) {
          String recommendMvNo = rs.getString("RECOMMEND_MV_NO");
          String memberNo = rs.getString("MEMBER_NO");
          String writerNick = rs.getString("WRITER_NICK");
          String year = rs.getString("YEAR");
          String title = rs.getString("TITLE");
          String content = rs.getString("CONTENT");
          String enrollDate = rs.getString("ENROLL_DATE");
          String modifyDate = rs.getString("MODIFY_DATE");
          String recommendCount = rs.getString("RECOMMEND_COUNT");
          String hit = rs.getString("HIT");
            
          RecommendVo vo = new RecommendVo();
          vo.setRecommendMvNo(recommendMvNo);
          vo.setMemberNo(memberNo);
          vo.setWriterNick(writerNick);
          vo.setYear(year);
          vo.setTitle(title);
          vo.setContent(content);
          vo.setEnrollDate(enrollDate);
          vo.setModifyDate(modifyDate);
          vo.setRecommendCount(recommendCount);
          vo.setHit(hit);
            
          recommendVoList.add(vo);
       }
   
      // close
       JDBCTemplate.close(rs);
       JDBCTemplate.close(pstmt);
         
       return recommendVoList;
   
   }

   //공지사항 검색 (제목+내용)
   public List<RecommendVo> searchByTitleAndContent(Connection conn, Map<String, String> m, PageVo pvo) throws Exception {
      
      //sql
      String sql = "SELECT * FROM (SELECT ROWNUM RNUM, T.* FROM (SELECT RECOMMEND_MV_NO, YEAR, TITLE, CONTENT, ENROLL_DATE, MODIFY_DATE, RECOMMEND_COUNT, HIT FROM RECOMMEND_MV WHERE DELETE_YN = 'N' AND (TITLE LIKE '%' || ? || '%' OR CONTENT LIKE '%' || ? || '%') ORDER BY RECOMMEND_MV_NO DESC) T) WHERE RNUM BETWEEN ? AND ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, m.get("searchValue"));
        pstmt.setString(2, m.get("searchValue"));
        pstmt.setInt(3, pvo.getStartRow());
        pstmt.setInt(4, pvo.getLastRow());
        ResultSet rs = pstmt.executeQuery();

        List<RecommendVo> recommendVoList = new ArrayList<RecommendVo>();
        while (rs.next()) {
            String recommendMvNo = rs.getString("RECOMMEND_MV_NO");
//            String writerNick = rs.getString("WRITER_NICK");
            String year = rs.getString("YEAR");
            String title = rs.getString("TITLE");
            String content = rs.getString("CONTENT");
            String enrollDate = rs.getString("ENROLL_DATE");
            String modifyDate = rs.getString("MODIFY_DATE");
            String recommendCount = rs.getString("RECOMMEND_COUNT");
            String hit = rs.getString("HIT");

            RecommendVo vo = new RecommendVo();
            vo.setRecommendMvNo(recommendMvNo);
//            vo.setWriterNick(writerNick);
            vo.setYear(year);
            vo.setTitle(title);
            vo.setContent(content);
            vo.setEnrollDate(enrollDate);
            vo.setModifyDate(modifyDate);
            vo.setRecommendCount(recommendCount);
            vo.setHit(hit);

            recommendVoList.add(vo);
        }

        JDBCTemplate.close(rs);
        JDBCTemplate.close(pstmt);

        return recommendVoList;
      
   }

   // 게시글 갯수 조회 (검색값에 따라)
   public int getRecommendCountBySearch(Connection conn, Map<String, String> m) throws Exception {
      
      // SQL
      String sql = "SELECT COUNT(*) FROM RECOMMEND_MV WHERE DELETE_YN = 'N' AND (TITLE LIKE '%' || ? || '%' OR CONTENT LIKE '%' || ? || '%')";
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, m.get("searchValue"));
      pstmt.setString(2, m.get("searchValue"));
      ResultSet rs = pstmt.executeQuery();
      
      // rs
      int cnt = 0;
      if(rs.next()) {
         cnt = rs.getInt(1);
      }
      
      // close
      JDBCTemplate.close(rs);
      JDBCTemplate.close(pstmt);
      
      return cnt;
   }

   
	// 상영요청 작성
	public int write(Connection conn, RecommendVo vo) throws Exception  {
	    // SQL
	    String sql = "INSERT INTO RECOMMEND_MV (RECOMMEND_MV_NO, MEMBER_NO, ENROLL_DATE, MODIFY_DATE, DELETE_YN, YEAR, TITLE, CONTENT, RECOMMEND_COUNT, HIT) VALUES (SEQ_RECOMMEND_MV.NEXTVAL, ?, SYSDATE, SYSDATE, 'N', ?, ?, ?, 0, 0)";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, vo.getMemberNo());
	    pstmt.setString(2, vo.getYear());
	    pstmt.setString(3, vo.getTitle());
	    pstmt.setString(4, vo.getContent());
	    
	    int result = pstmt.executeUpdate(); // 실행 결과 반환
	    
	    // close
	    JDBCTemplate.close(pstmt);
	    
	    return result;
	}


	//게시글 번호로 게시글 1개 조회
	public RecommendVo selectRecommendByNo(Connection conn, String recommendMvNo) throws Exception {

		//SQL
		String sql = "SELECT R.RECOMMEND_MV_NO, R.MEMBER_NO, M.MEMBER_ID AS WRITER_NICK ,R.TITLE ,R.CONTENT ,R.ENROLL_DATE, R.MODIFY_DATE, R.YEAR, R.RECOMMEND_COUNT, R.HIT FROM RECOMMEND_MV R JOIN MEMBER M ON R.MEMBER_NO = M.MEMBER_NO WHERE R.RECOMMEND_MV_NO = ? AND R.DELETE_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, recommendMvNo);
		ResultSet rs = pstmt.executeQuery();
		  
		//rs
		RecommendVo vo = null;
		if(rs.next()) {
			String memberNo = rs.getString("MEMBER_NO");
			String writerNick = rs.getString("WRITER_NICK");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String enrollDate = rs.getString("ENROLL_DATE");
			String modifyDate = rs.getString("MODIFY_DATE");
			String year = rs.getString("YEAR");
			String recommendCount = rs.getString("RECOMMEND_COUNT");
			String hit = rs.getString("HIT");
			 
			vo = new RecommendVo();
			vo.setRecommendMvNo(recommendMvNo);
			vo.setMemberNo(memberNo);
			vo.setWriterNick(writerNick);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setEnrollDate(enrollDate);
			vo.setModifyDate(modifyDate);
			vo.setYear(year);
			vo.setRecommendCount(recommendCount);
			vo.setHit(hit);
			 
			System.out.println("dao 's vo ::: " + vo);
			
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		  
		return vo;
	
	}

	//조회수 증가
	public int increaseHit(Connection conn, String recommendMvNo) throws Exception {
		
		//SQL
		String sql = "UPDATE RECOMMEND_MV SET HIT = HIT + 1 WHERE RECOMMEND_MV_NO = ? AND DELETE_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, recommendMvNo);
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
			      
		return result;
				
	}

	//상영요청 수정
	public int updateRecommendByNo(Connection conn, RecommendVo vo) throws Exception {

		// SQL
	    String sql = "UPDATE RECOMMEND_MV SET YEAR = ? , TITLE = ? , CONTENT = ? , MODIFY_DATE = SYSDATE , RECOMMEND_COUNT = ? , HIT = ? WHERE RECOMMEND_MV_NO = ? AND DELETE_YN = 'N'";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, vo.getYear());
	    pstmt.setString(2, vo.getTitle());
	    pstmt.setString(3, vo.getContent());
	    pstmt.setString(4, vo.getRecommendCount());
	    pstmt.setString(5, vo.getHit());
	    pstmt.setString(6, vo.getRecommendMvNo());
	    int result = pstmt.executeUpdate();
	   
	    // close
	    JDBCTemplate.close(pstmt);
	   
	    return result;
	
	}
   

	//삭제
	public int delete(Connection conn, String recommendMvNo, String memberNo) throws Exception {

		//SQL
	    String sql = "UPDATE RECOMMEND_MV SET DELETE_YN = 'Y' WHERE RECOMMEND_MV_NO = ?";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, recommendMvNo);
	    int result = pstmt.executeUpdate();
	      
	    //close
	    JDBCTemplate.close(pstmt);
	      
	    return result;
	    
	}
   
   

}