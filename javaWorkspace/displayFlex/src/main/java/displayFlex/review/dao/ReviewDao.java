package displayFlex.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import displayFlex.review.dto.ReviewDto;
import displayFlex.review.vo.ReviewVo;
import displayFlex.util.page.vo.PageVo;
import test.JDBCTemplate;

public class ReviewDao {
	private String query;
	
	/**
	 * 특정 영화에 대한 리뷰 가져오기
	 * @param movieNo
	 * @param pageable 
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public List<ReviewDto> getReviewListByMovieNo(String movieNo, PageVo pageable, Connection con) throws SQLException {
		query = "SELECT A.*, M.MEMBER_NICK FROM ( SELECT R.MOVIE_NO , R.REVIEW_NO , R.MEMBER_NO , R.CONTENT , TO_CHAR(R.WRITE_DATE, 'YYYY-MM-DD HH24:MI') WRITE_DATE , R.RATE , ROW_NUMBER() OVER(ORDER BY R.WRITE_DATE DESC) RNUM FROM REVIEW R WHERE R.DEL_YN = 'N' AND R.MOVIE_NO = ? ) A INNER JOIN MEMBER M ON A.MEMBER_NO = M.MEMBER_NO WHERE A.RNUM BETWEEN ? AND ?";
		
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, movieNo);
		pstmt.setInt(2, pageable.getStartRow());
		pstmt.setInt(3, pageable.getLastRow());
		ResultSet rs = pstmt.executeQuery();

		List<ReviewDto> reviewList = new ArrayList<ReviewDto>();

		while(rs.next()) {
			String reviewNo = rs.getString("REVIEW_NO");
			String writerNo = rs.getString("MEMBER_NO");			
			String memberNick = rs.getString("MEMBER_NICK");
			String content = rs.getString("CONTENT");
			String writeDate = rs.getString("WRITE_DATE");
			String rate = rs.getString("RATE");
			
			reviewList.add(new ReviewDto(reviewNo, writerNo, memberNick, content, writeDate, rate));
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return reviewList;
	}

	/**
	 * 
	 * @param review
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public int addRiew(ReviewVo review, Connection con) throws SQLException {
		query = "INSERT INTO REVIEW(REVIEW_NO, MEMBER_NO, MOVIE_NO, CONTENT, RATE) VALUES (SEQ_REVIEW.NEXTVAL, ?, ?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, review.getMemberNo());
		pstmt.setString(2, review.getMovieNo());
		pstmt.setString(3, review.getContent());
		pstmt.setString(4, review.getRate());
		
		int result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		return result;
	}

	/**
	 * 모든 리뷰 개수 가져오기
	 * @param pno
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public int getAllReviewCount(int pno, Connection con) throws SQLException {
		query = "SELECT COUNT(*) FROM REVIEW WHERE DEL_YN = 'N'";
		PreparedStatement pstmt = con.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		int count = 0;
		if(rs.next()) {
			count = rs.getInt(1);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return count;
	}

	/**
	 * 리뷰 작성자 찾기
	 * @param reviewNo
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public ReviewVo findWriterByNo(String reviewNo, Connection con) throws SQLException {
		query ="SELECT MEMBER_NO, MOVIE_NO FROM REVIEW WHERE REVIEW_NO = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		
		pstmt.setString(1, reviewNo);
		ResultSet rs = pstmt.executeQuery();
		ReviewVo findReview = null;
		if(rs.next()) {
			String memberNo = rs.getString("MEMBER_NO");
			String movieNo = rs.getString("MOVIE_NO");
			 
			findReview = new ReviewVo(memberNo, movieNo);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return findReview;
	}

	/**
	 * 리뷰 삭제
	 * @param reviewNo
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public int deleteReview(String reviewNo, Connection con) throws SQLException {
		query = "UPDATE REVIEW SET DEL_YN = 'Y' WHERE REVIEW_NO = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, reviewNo);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		return result;
	}
}
