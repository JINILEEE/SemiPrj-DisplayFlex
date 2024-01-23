package displayFlex.serviceCenter.recommend.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import displayFlex.serviceCenter.inquiry.dao.InquiryDao;
import displayFlex.serviceCenter.inquiry.vo.InquiryVo;
import displayFlex.serviceCenter.notice.dao.NoticeDao;
import displayFlex.serviceCenter.notice.vo.NoticeVo;
import displayFlex.serviceCenter.recommend.dao.RecommendDao;
import displayFlex.serviceCenter.recommend.vo.RecommendVo;
import displayFlex.util.page.vo.PageVo;
import test.JDBCTemplate;

public class RecommendService {

	public List<RecommendVo> selectRecommendList(PageVo pvo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		RecommendDao dao = new RecommendDao();
		List<RecommendVo> recommendVoList = dao.selectRecommendList(conn, pvo);
		
		// close
		JDBCTemplate.close(conn);
		
		return recommendVoList;
		
	}

	//전체 게시글 갯수 조회
	public int selectRecommendCount() throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		RecommendDao dao = new RecommendDao();
		int cnt = dao.selectRecommendCount(conn);
		
		// close
		JDBCTemplate.close(conn);
		
		return cnt;
		
	}

	//검색
	public List<RecommendVo> search(Map<String, String> m, PageVo pvo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO
		RecommendDao dao = new RecommendDao();
		List<RecommendVo> recommendVoList = null;
		
		if(m.get("searchType").equals("titcon")) {
			recommendVoList = dao.searchByTitleAndContent(conn , m , pvo);
		}else {
			recommendVoList = dao.search(conn , m, pvo);
		}
		
		//close
		JDBCTemplate.close(conn);
		
		return recommendVoList;
		
	}

	//게시글 갯수 조회 (검색값에 따라)
	public int selectSearchRecommendCount(Map<String, String> m) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO
		RecommendDao dao = new RecommendDao();
		int cnt = dao.getRecommendCountBySearch(conn , m);
		
		// close
		JDBCTemplate.close(conn);
		
		return cnt;
		
	}

	//상영요청 작성
	public int write(RecommendVo vo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		RecommendDao dao = new RecommendDao();
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

	//상영요청 상세조회 (조회수 증가)
	public RecommendVo selectRecommendByNo(String recommendMvNo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		RecommendDao dao = new RecommendDao();
		int result = dao.increaseHit(conn, recommendMvNo);
		RecommendVo vo = null;
		if(result == 1) {
			vo = dao.selectRecommendByNo(conn , recommendMvNo);
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

	//상영요청글 수정
	public RecommendVo edit(String recommendMvNo) throws Exception {

		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		RecommendDao dao = new RecommendDao();
		RecommendVo vo = dao.selectRecommendByNo(conn , recommendMvNo);
		
		//close
		JDBCTemplate.close(conn);
		
		return vo;
	
	}

	//상영요청글 수정
	public int edit(RecommendVo vo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		RecommendDao dao = new RecommendDao();
		int result = dao.updateRecommendByNo(conn , vo);
		
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

	//삭제
	public int delete(String recommendMvNo, String memberNo) throws Exception {

		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		RecommendDao dao = new RecommendDao();
		int result = dao.delete(conn , recommendMvNo , memberNo);
		
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

}
