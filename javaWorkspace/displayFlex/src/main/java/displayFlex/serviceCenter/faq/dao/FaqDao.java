package displayFlex.serviceCenter.faq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import displayFlex.serviceCenter.faq.vo.CategoryVo;
import displayFlex.serviceCenter.faq.vo.FaqVo;
import displayFlex.serviceCenter.notice.vo.NoticeVo;
import displayFlex.util.page.vo.PageVo;
import test.JDBCTemplate;


public class FaqDao {

	//faq 목록 조회
	public List<FaqVo> selectFaqList(Connection conn , PageVo pvo) throws Exception {

		//sql
		String sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM (SELECT F.FAQ_NO ,F.FAQ_CATEGORY_NO ,F.TITLE ,F.CONTENT ,F.HIT ,F.ENROLL_DATE ,F.MODIFY_DATE , FC.DIVISION AS CATEGORY_NAME FROM FAQ F JOIN FAQ_CATEGORY FC ON F.FAQ_CATEGORY_NO = FC.FAQ_CATEGORY_NO WHERE FC.DIVISION = ? AND F.DELETE_YN = 'N' ORDER BY FAQ_NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pvo.getStartRow());
	    pstmt.setInt(2, pvo.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<FaqVo> faqVoList = new ArrayList<FaqVo>();
		while(rs.next()) {
			String faqNo = rs.getString("FAQ_NO");
			String faqCategoryNo = rs.getString("FAQ_CATEGORY_NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String hit = rs.getString("HIT");
			String enrollDate = rs.getString("ENROLL_DATE");
			String modifyDate = rs.getString("MODIFY_DATE");
			String category_name = rs.getString("CATEGORY_NAME");
//			String deleteYn = rs.getString("DELETE_YN");
			
			FaqVo vo = new FaqVo();
			vo.setFaqNo(faqNo);
			vo.setFaqCategoryNo(faqCategoryNo);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setContent(hit);
			vo.setEnrollDate(enrollDate);
			vo.setModifyDate(modifyDate);
			vo.setCategoryName(faqCategoryNo);
			
			faqVoList.add(vo);
			
		}
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return faqVoList;
		
		
	}
	
	
	// 카테고리에 따른 FAQ 목록 조회
    public List<FaqVo> selectFaqListByCategory(Connection conn, String categoryNo, PageVo pvo) throws Exception {
        String sql = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT F.FAQ_NO , F.FAQ_CATEGORY_NO , FC.DIVISION AS CATEGORY_NAME , F.TITLE , F.ENROLL_DATE FROM FAQ F JOIN FAQ_CATEGORY FC ON F.FAQ_CATEGORY_NO = FC.FAQ_CATEGORY_NO WHERE FC.DIVISION = ? AND F.DELETE_YN = 'N' ORDER BY F.FAQ_NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoryNo);
            pstmt.setInt(2, pvo.getStartRow());
            pstmt.setInt(3, pvo.getLastRow());

            try (ResultSet rs = pstmt.executeQuery()) {
                List<FaqVo> faqVoList = new ArrayList<>();
                while (rs.next()) {
                	String faqNo = rs.getString("FAQ_NO");
        			String faqCategoryNo = rs.getString("FAQ_CATEGORY_NO");
        			String categoryName = rs.getString("CATEGORY_NAME");
        			String title = rs.getString("TITLE");
        			String enrollDate = rs.getString("ENROLL_DATE");
        			
        			FaqVo vo = new FaqVo();
        			vo.setFaqNo(faqNo);
        			vo.setFaqCategoryNo(faqCategoryNo);
        			vo.setCategoryName(categoryName);
        			vo.setTitle(title);
        			vo.setEnrollDate(enrollDate);
        			
                    faqVoList.add(vo);
                }
                return faqVoList;
            }
        }
    }
    

    // 카테고리에 따른 전체 FAQ 갯수 조회
    public int selectFaqCountByCategory(Connection conn, String categoryNo) throws Exception {
        String sql = "SELECT COUNT(F.FAQ_NO) FROM FAQ F INNER JOIN FAQ_CATEGORY FC ON F.FAQ_CATEGORY_NO = FC.FAQ_CATEGORY_NO WHERE FC.DIVISION = ? AND F.DELETE_YN = 'N'";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoryNo);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                return 0;
            }
        }
    }
    
    //카테고리 목록 조회
	public List<CategoryVo> selectAllCategoryList(Connection conn) throws Exception {
	
		//sql
		String sql = "SELECT FAQ_CATEGORY_NO, DIVISION AS CATEGORY_NAME FROM FAQ_CATEGORY";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    ResultSet rs = pstmt.executeQuery();
	
	    List<CategoryVo> categoryList = new ArrayList<CategoryVo>();
	    while (rs.next()) {
	        String faqCategoryNo = rs.getString("FAQ_CATEGORY_NO");
	        String categoryName = rs.getString("CATEGORY_NAME");
	
	        CategoryVo vo = new CategoryVo();
	        vo.setFaqCategoryNo(faqCategoryNo);
	        vo.setCategoryName(categoryName);
	
	        categoryList.add(vo);
	    }
	
	    JDBCTemplate.close(rs);
	    JDBCTemplate.close(pstmt);
	
	    return categoryList;
	  
		
	}


	public int selectFaqCount(Connection conn) throws Exception {
		
		//SQL
		String sql = "SELECT COUNT(*) as cnt FROM FAQ WHERE DELETE_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
	      
		ResultSet rs = pstmt.executeQuery();
	      
		//rs
		int cnt = 0;
		if(rs.next()) {
//			int cnt = rs.getInt("cnt");
			cnt = rs.getInt(1); //첫번째 열을 가져오겠다 라는 뜻, 계산에 써야되기 때문에 String이 아닌 Int로 받아줌
		}
		  
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		  
		return cnt;
	
	}


	//게시글 번호로 게시글 1개 조회
	public FaqVo selectFaqByNo(Connection conn, String faqNo) throws Exception {

		//SQL
	    String sql = "SELECT F.FAQ_NO ,F.FAQ_CATEGORY_NO ,F.TITLE ,F.CONTENT ,F.HIT ,F.ENROLL_DATE ,F.MODIFY_DATE ,FC.DIVISION AS CATEGORY_NAME FROM FAQ F JOIN FAQ_CATEGORY FC ON F.FAQ_CATEGORY_NO = FC.FAQ_CATEGORY_NO WHERE F.FAQ_NO = ? AND F.DELETE_YN = 'N'";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, faqNo);
	    ResultSet rs = pstmt.executeQuery();
	      
	    //rs
	    FaqVo vo = null;
	    if(rs.next()) {
			String title = rs.getString("TITLE");
			String faqCateforyNo = rs.getString("FAQ_CATEGORY_NO");
			String content = rs.getString("CONTENT");
			String hit = rs.getString("HIT");
			String enrollDate = rs.getString("ENROLL_DATE");
			String modifyDate = rs.getString("MODIFY_DATE");
			String categoryName = rs.getString("CATEGORY_NAME");
	         
			vo = new FaqVo();
			vo.setFaqNo(faqNo);
			vo.setTitle(title);
			vo.setFaqCategoryNo(faqCateforyNo);
			vo.setContent(content);
			vo.setHit(hit);
			vo.setEnrollDate(enrollDate);
			vo.setModifyDate(modifyDate);
			vo.setCategoryName(categoryName);
			
			System.out.println("dao 's vo ::: " + vo);
		         
	    }
	    
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
		
	}


	//조회수 증가
	public int increaseHit(Connection conn, String faqNo) throws Exception {
		
		//SQL
		String sql = "UPDATE FAQ SET HIT = HIT + 1 WHERE FAQ_NO = ? AND DELETE_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, faqNo);
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
			      
		return result;
		
	}


	//faq 검색
	public List<FaqVo> search(Connection conn, Map<String, String> m, PageVo pvo) throws Exception {
		
//		String searchType = m.get("searchType");
		
		// SQL
		String sql = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT FAQ_NO , FAQ_CATEGORY_NO , TITLE , CONTENT , ENROLL_DATE , MODIFY_DATE , HIT FROM FAQ WHERE DELETE_YN = 'N' AND (TITLE LIKE '%' || ? || '%' OR CONTENT LIKE '%' || ? || '%') ORDER BY FAQ_NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, m.get("searchValue"));
		pstmt.setString(2, m.get("searchValue"));
		pstmt.setInt(3, pvo.getStartRow());
		pstmt.setInt(4, pvo.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		// rs
	      List<FaqVo> faqVoList = new ArrayList<FaqVo>();
	      while(rs.next()) {
	         String faqNo = rs.getString("FAQ_NO");
	         String faqCategoryNo = rs.getString("FAQ_CATEGORY_NO");
	         String title = rs.getString("TITLE");
	         String content = rs.getString("CONTENT");
	         String enrollDate = rs.getString("ENROLL_DATE");
	         String modifyDate = rs.getString("MODIFY_DATE");
	         String hit = rs.getString("HIT");
	         
	         FaqVo vo = new FaqVo();
	         vo.setFaqNo(faqNo);
	         vo.setFaqCategoryNo(faqCategoryNo);
	         vo.setTitle(title);
	         vo.setContent(content);
	         vo.setEnrollDate(enrollDate);
	         vo.setModifyDate(modifyDate);
	         vo.setHit(hit);
	         
	         faqVoList.add(vo);
	      }
	
		// close
	    JDBCTemplate.close(rs);
	    JDBCTemplate.close(pstmt);
	      
	    return faqVoList;
		
	}
	
	//공지사항 검색 (제목+내용)
//	public List<FaqVo> searchByTitleAndContent(Connection conn, Map<String, String> m, PageVo pvo) throws Exception {
//
//		//sql
//		String sql = "SELECT * FROM (SELECT ROWNUM RNUM, T.* FROM (SELECT FAQ_NO, FAQ_CATEGORY_NO, TITLE, CONTENT, ENROLL_DATE, MODIFY_DATE, HIT FROM NOTICE WHERE DELETE_YN = 'N' AND (TITLE LIKE '%' || ? || '%' OR CONTENT LIKE '%' || ? || '%') ORDER BY FAQ_NO DESC) T) WHERE RNUM BETWEEN ? AND ?";
//        PreparedStatement pstmt = conn.prepareStatement(sql);
//        pstmt.setString(1, m.get("searchValue"));
//        pstmt.setString(2, m.get("searchValue"));
//        pstmt.setInt(3, pvo.getStartRow());
//        pstmt.setInt(4, pvo.getLastRow());
//        ResultSet rs = pstmt.executeQuery();
//
//        List<FaqVo> faqVoList = new ArrayList<FaqVo>();
//        while (rs.next()) {
//            String faqNo = rs.getString("FAQ_NO");
//            String faqCategoryNo = rs.getString("FAQ_CATEGORY_NO");
//            String title = rs.getString("TITLE");
//            String content = rs.getString("CONTENT");
//            String enrollDate = rs.getString("ENROLL_DATE");
//            String modifyDate = rs.getString("MODIFY_DATE");
//            String hit = rs.getString("HIT");
//
//            FaqVo vo = new FaqVo();
//            vo.setFaqNo(faqNo);
//            vo.setFaqCategoryNo(faqCategoryNo);
//            vo.setTitle(title);
//            vo.setContent(content);
//            vo.setEnrollDate(enrollDate);
//            vo.setModifyDate(modifyDate);
//            vo.setHit(hit);
//
//            faqVoList.add(vo);
//        }
//
//        JDBCTemplate.close(rs);
//        JDBCTemplate.close(pstmt);
//
//        return faqVoList;
//		
//	}

	// 게시글 갯수 조회 (검색값에 따라)
	public int getFaqCountBySearch(Connection conn, Map<String, String> m) throws Exception {

		// SQL
		String sql = "SELECT COUNT(*) FROM FAQ WHERE DELETE_YN = 'N' AND " + m.get("searchType") + " LIKE '%' || ? || '%'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, m.get("searchValue"));
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


	//faq 작성
	public int write(Connection conn, FaqVo vo) throws Exception {

		//SQL
	    String sql = "INSERT INTO FAQ (FAQ_NO, FAQ_CATEGORY_NO, TITLE, CONTENT) VALUES ( SEQ_FAQ.NEXTVAL, ?, ?, ?)";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, vo.getFaqCategoryNo());
	    pstmt.setString(2, vo.getTitle());
	    pstmt.setString(3, vo.getContent());
	    int result = pstmt.executeUpdate();
	      
	    //close
	    JDBCTemplate.close(pstmt);
	    return result;
	
	}


	//카테고리 리스트 조회
	public List<CategoryVo> getCategoryList(Connection conn) throws Exception {

		//sql
		String sql = "SELECT FAQ_CATEGORY_NO, DIVISION AS CATEGORY_NAME FROM FAQ_CATEGORY";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    ResultSet rs = pstmt.executeQuery();
	
	    List<CategoryVo> categoryList = new ArrayList<>();
	    while (rs.next()) {
	        String faqCategoryNo = rs.getString("FAQ_CATEGORY_NO");
	        String categoryName = rs.getString("CATEGORY_NAME");
	
	        CategoryVo vo = new CategoryVo();
	        vo.setFaqCategoryNo(faqCategoryNo);
	        vo.setCategoryName(categoryName);
	
	        categoryList.add(vo);
	    }
	
	    JDBCTemplate.close(rs);
	    JDBCTemplate.close(pstmt);
	
	    return categoryList;
	
	}


	//faq 수정
	public int updateFaqByNo(Connection conn, FaqVo vo) throws Exception {

		// SQL
		String sql = "UPDATE FAQ SET FAQ_CATEGORY_NO = ? , TITLE = ? , CONTENT = ? , MODIFY_DATE = LOCALTIMESTAMP WHERE FAQ_NO = ? AND DELETE_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getFaqCategoryNo());
		pstmt.setString(2, vo.getTitle());
		pstmt.setString(3, vo.getContent());
		pstmt.setString(4, vo.getFaqNo());
		int result = pstmt.executeUpdate();
		   
		// close
		JDBCTemplate.close(pstmt);
		   
		return result; 
	
	}


	//faq 
	public int delete(Connection conn, String faqNo, String memberNo) throws Exception {

		//SQL
	    String sql = "UPDATE FAQ SET DELETE_YN = 'Y' WHERE FAQ_NO = ?";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, faqNo);
	    int result = pstmt.executeUpdate();
	      
	    //close
	    JDBCTemplate.close(pstmt);
	      
	    return result;
	
	}


	

}





















