package displayFlex.serviceCenter.notice.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import displayFlex.serviceCenter.notice.dao.NoticeDao;
import displayFlex.serviceCenter.notice.vo.NoticeVo;
import displayFlex.util.page.vo.PageVo;
import test.JDBCTemplate;

public class NoticeService {

	//공지사항 목록 조회
	public List<NoticeVo> selectNoticeList(PageVo pvo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		NoticeDao dao = new NoticeDao();
		List<NoticeVo> NoticeVoList = dao.selectNoticeList(conn, pvo);
		
		// close
		JDBCTemplate.close(conn);
		
		return NoticeVoList;
	}
	
	//전체 게시글 갯수 조회
	public int selectBoardCount() throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		NoticeDao dao = new NoticeDao();
		int cnt = dao.selectNoticeCount(conn);
		
		// close
		JDBCTemplate.close(conn);
		
		return cnt;
		
	}

	// 공지사항 검색
	public List<NoticeVo> search(Map<String, String> m, PageVo pvo) throws Exception {

	    // conn
	    Connection conn = JDBCTemplate.getConnection();

	    // DAO
	    NoticeDao dao = new NoticeDao();
	    List<NoticeVo> noticeVoList = null;

	    if (m.get("searchType").equals("titcon")) {
	        noticeVoList = dao.searchByTitleAndContent(conn, m, pvo); // 수정된 부분
	    } else {
	        noticeVoList = dao.search(conn, m, pvo);
	    }

	    // close
	    JDBCTemplate.close(conn);

	    return noticeVoList;
	}


	// 게시글 갯수 조회 (검색값에 따라)
	public int selectSearchNoticeCount(Map<String, String> m) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO
		NoticeDao dao = new NoticeDao();
		int cnt = dao.getNoticeCountBySearch(conn , m);
		
		// close
		JDBCTemplate.close(conn);
		
		return cnt;
	}

	//공지사항 작성
	public int write(NoticeVo vo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		NoticeDao dao = new NoticeDao();
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

	//공지사항 상세조회 (+조회수 증가)
	public NoticeVo selectNoticeByNo(String noticeNo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		NoticeDao dao = new NoticeDao();
		int result = dao.increaseHit(conn, noticeNo);
		NoticeVo vo = null;
		if(result == 1) {
			vo = dao.selectNoticeByNo(conn , noticeNo);
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

	//공지사항 삭제
	public int delete(String noticeNo, String memberNo)  throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		NoticeDao dao = new NoticeDao();
		int result = dao.delete(conn , noticeNo , memberNo);
		
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

	// 게시글 수정 (화면)
	public NoticeVo edit(String noticeNo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		NoticeDao dao = new NoticeDao();
		NoticeVo vo = dao.selectNoticeByNo(conn , noticeNo);
		
		// close
		JDBCTemplate.close(conn);
		
		return vo;
	}

	//공지사항 수정
	public int edit(NoticeVo vo) throws Exception {

		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		NoticeDao dao = new NoticeDao();
		int result = dao.updateNoticeByNo(conn , vo);
		
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








