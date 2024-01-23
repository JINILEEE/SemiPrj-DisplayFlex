package displayFlex.review.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import displayFlex.member.MemberVo;
import displayFlex.review.dao.ReviewDao;
import displayFlex.review.dto.ReviewDto;
import displayFlex.review.vo.ReviewVo;
import displayFlex.util.page.vo.PageVo;
import test.JDBCTemplate;

public class ReviewService {

	private final ReviewDao reviewDao;
	
	public ReviewService() {
		reviewDao = new ReviewDao();
	}
	
	/**
	 * 영화에 대한 리뷰 가져오기 
	 * @param movieNo
	 * @param pageable 
	 * @param loginMember 
	 * @return
	 * @throws SQLException 
	 */
	public List<ReviewDto> getReviewListByMovieNo(String movieNo, PageVo pageable, MemberVo loginMember) throws SQLException {
		Connection con = JDBCTemplate.getConnection();
		
		List<ReviewDto> reviewList = reviewDao.getReviewListByMovieNo(movieNo, pageable, con);
		JDBCTemplate.close(con);
		
		try {
			for(ReviewDto dto : reviewList) {
				if(dto.getWriterNo().equals(loginMember.getMemberNo()) || loginMember.getAdminYn().equals("Y")) {
					dto.setAbleToWatch(true);
				}
			}			
		} catch (Exception e) {
			
		}
		return reviewList;
	}
	
	/**
	 * 리뷰 등록
	 * @param review
	 * @return
	 * @throws SQLException
	 */
	public int addReview(ReviewVo review) throws SQLException {
		Connection con = JDBCTemplate.getConnection();
		
		int result = reviewDao.addRiew(review, con);
		
		if(result == 1) JDBCTemplate.commit(con);
		else JDBCTemplate.rollback(con);
		
		JDBCTemplate.close(con);
		return result;
	}

	/**
	 * 모든 리뷰 개수 가져오기
	 * @param pno
	 * @return
	 * @throws SQLException
	 */
	public int getAllReviewCount(int pno) throws SQLException {
		Connection con = JDBCTemplate.getConnection();
		
		int count = reviewDao.getAllReviewCount(pno, con);
		JDBCTemplate.close(con);
		return count;
	}

	/**
	 * 리뷰 작성자 찾기
	 * @param reviewNo
	 * @return
	 * @throws SQLException 
	 */
	public ReviewVo findWriterByNo(String reviewNo) throws SQLException {
		Connection con = JDBCTemplate.getConnection();
		
		ReviewVo findReview = reviewDao.findWriterByNo(reviewNo, con);
		JDBCTemplate.close(con);
		return findReview;
	}

	/**
	 * 리뷰 삭제(del_yn 변경)
	 * @param reviewNo
	 * @return
	 * @throws SQLException 
	 */
	public int deleteReview(String reviewNo) throws SQLException {
		Connection con = JDBCTemplate.getConnection();
		
		int result = reviewDao.deleteReview(reviewNo, con);
		
		if(result == 1) JDBCTemplate.commit(con);
		else JDBCTemplate.rollback(con);
		
		JDBCTemplate.close(con);
		return result;
	}
}
