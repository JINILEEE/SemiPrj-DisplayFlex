package displayFlex.serviceCenter.faq.service;

import java.sql.Connection;
import java.util.*;

import displayFlex.serviceCenter.faq.dao.FaqDao;
import displayFlex.serviceCenter.faq.vo.CategoryVo;
import displayFlex.serviceCenter.faq.vo.FaqVo;
import displayFlex.serviceCenter.notice.dao.NoticeDao;
import displayFlex.serviceCenter.notice.vo.NoticeVo;
import displayFlex.util.page.vo.PageVo;
import test.JDBCTemplate;

public class FaqService {

	//게시글 목록 조회
	public List selectFaqList(PageVo pvo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		FaqDao dao = new FaqDao();
		List<FaqVo> FaqVoList = dao.selectFaqList(conn, pvo);
		
		// close
		JDBCTemplate.close(conn);
		
		return FaqVoList;
	}

	
	// 카테고리 목록 조회
    public List<CategoryVo> selectCategoryList() throws Exception {
    	
        Connection conn = JDBCTemplate.getConnection();
        
        CategoryService categoryService = new CategoryService();
        List<CategoryVo> categoryList = categoryService.selectAllCategoryList();
        
        JDBCTemplate.close(conn);
        
        return categoryList;
    }

    // 카테고리에 따른 FAQ 목록 조회
    public List<FaqVo> selectFaqListByCategory(String categoryNo, PageVo pvo) throws Exception {
        
    	Connection conn = JDBCTemplate.getConnection();
    	
        FaqDao dao = new FaqDao();
        List<FaqVo> faqVoList = dao.selectFaqListByCategory(conn, categoryNo, pvo);
        
        JDBCTemplate.close(conn);
        
        return faqVoList;
    }

    // 카테고리에 따른 전체 FAQ 갯수 조회
    public int selectFaqCountByCategory(String categoryNo) throws Exception {
        
    	Connection conn = JDBCTemplate.getConnection();
    	
        FaqDao dao = new FaqDao();
        int cnt = dao.selectFaqCountByCategory(conn, categoryNo);
        
        JDBCTemplate.close(conn);
        
        return cnt;
    }
	
	
	//전체 게시글 갯수 조회
	public int selectFaqCount() throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		FaqDao dao = new FaqDao();
		int cnt = dao.selectFaqCount(conn);
		
		// close
		JDBCTemplate.close(conn);
		
		return cnt;
		
	}

	//게시글 상세조회 (조회수 증가)
	public FaqVo selectFaqByNo(String faqNo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		FaqDao dao = new FaqDao();
		int result = dao.increaseHit(conn, faqNo);
		FaqVo vo = null;
		if(result == 1) {
			vo = dao.selectFaqByNo(conn , faqNo);
		}
		
		// tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		// close
		JDBCTemplate.close(conn);
		
		return vo;
				
	}

	//faq 검색
	public List<FaqVo> search(Map<String, String> m, PageVo pvo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO
		FaqDao dao = new FaqDao();
		List<FaqVo> faqVoList = dao.search(conn , m, pvo);
		
//		if(m.get("searchType").equals("titcon")) {
//			dao.searchByTitleAndContent(conn , m , pvo);
//		}else {
//			faqVoList = dao.search(conn , m, pvo);
//		}
		
		//close
		JDBCTemplate.close(conn);
		
		return faqVoList;
		
	}

	// 게시글 갯수 조회 (검색값에 따라)
	public int selectSearchFaqCount(Map<String, String> m) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO
		FaqDao dao = new FaqDao();
		int cnt = dao.getFaqCountBySearch(conn , m);
		
		// close
		JDBCTemplate.close(conn);
		
		return cnt;
	
	}

	//faq 작성
	public int write(FaqVo vo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		FaqDao dao = new FaqDao();
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


	//카테고리 리스트 조회
	public List<CategoryVo> getCategoryList() throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		FaqDao dao = new FaqDao();
		List<CategoryVo> voList = dao.getCategoryList(conn);
		
		//close
		JDBCTemplate.close(conn);
		
		return voList;
		
	}


	//faq 수정 (화면)
	public Map<String, Object> edit(String faqNo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		FaqDao dao = new FaqDao();
		FaqVo vo = dao.selectFaqByNo(conn , faqNo);
		
		List<CategoryVo> categoryVoList = dao.selectAllCategoryList(conn);
		
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("vo", vo);
		m.put("categoryVoList", categoryVoList);
		
		// close
		JDBCTemplate.close(conn);
		
		return m;
	}

	
	//faq 수정
	public int edit(FaqVo vo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		FaqDao dao = new FaqDao();
		int result = dao.updateFaqByNo(conn , vo);
		
		//tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close
		JDBCTemplate.close(conn);
		
		return result;
		
	}


	//faq 삭제
	public int delete(String faqNo, String memberNo) throws Exception {

		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		FaqDao dao = new FaqDao();
		int result = dao.delete(conn , faqNo , memberNo);
		
		//tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close
		JDBCTemplate.close(conn);

		return result;
	
	}


	// faq 리스트(fetch, gson 사용해서 클릭시 해당메뉴 데이터 보내는 데이터용)
//	public List<FaqVo> selectFaqListdataByCategory(String categoryNo) throws Exception {
//		
//		//conn
//		Connection conn = JDBCTemplate.getConnection();
//		
//		//dao
//		FaqDao dao = new FaqDao();
//		List<FaqVo> faqVoList = dao.selectFaqListdataByCategory(conn, categoryNo);
//		
//		//close
//		JDBCTemplate.close(conn);
//		
//		return faqVoList;
//	
//	}
	
	

}
