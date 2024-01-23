package displayFlex.movie.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import displayFlex.movie.dao.MovieDao;
import displayFlex.movie.dto.MovieDetailDto;
import displayFlex.movie.dto.MovieListDto;
import displayFlex.movie.vo.GenreCategoryVo;
import displayFlex.movie.vo.MovieVo;
import displayFlex.movie.vo.ScreenGradeVo;
import displayFlex.movie.vo.StillImageVo;
import displayFlex.util.page.vo.PageVo;
import test.JDBCTemplate;

public class MovieService {
	
	private final MovieDao movieDao;
	
	public MovieService() {
		movieDao = new MovieDao();
	}
	
	/**
	 * 모든 장르 카테고리 가져오기
	 * @return
	 * @throws SQLException
	 */
	public List<GenreCategoryVo> getAllGenreCategory() throws SQLException {

		Connection con = JDBCTemplate.getConnection();
		
		List<GenreCategoryVo> genreList =  movieDao.getAllGenreCategory(con);
		
		JDBCTemplate.close(con);
		return genreList;
	}

	/**
	 * 모든 관람 등급 가져오기
	 * @return
	 * @throws SQLException
	 */
	public List<ScreenGradeVo> getAllScreenGrade() throws SQLException {
		Connection con = JDBCTemplate.getConnection();
		
		List<ScreenGradeVo> screenGradeList = movieDao.getAllScreenGrade(con);
		return screenGradeList;
	}

	public int getAllMovieCount() throws SQLException {
		Connection con = JDBCTemplate.getConnection();
		
		int count = movieDao.getAllMovieCount(con);
		
		JDBCTemplate.close(con);
		return count;
	}

	/**
	 * 영화 리스트 가져오기
	 * @param page
	 * @return
	 * @throws SQLException 
	 */
	public List<MovieListDto> getMovieList(PageVo page) throws SQLException {
		Connection con = JDBCTemplate.getConnection();
		
		List<MovieListDto> movieList = movieDao.getAllMovieList(page, con);
		JDBCTemplate.close(con);
		return movieList;
	}

	/**
	 * 영화 번호로 상세 정보 가져오기
	 * @param movieNo
	 * @return
	 * @throws SQLException 
	 */
	public MovieDetailDto getMovieInfoByNo(String movieNo) throws SQLException {
		Connection con = JDBCTemplate.getConnection();
		
		MovieDetailDto findMovie = movieDao.getMovieInfoByNo(movieNo,con);
		
		JDBCTemplate.close(con);
		return findMovie;
	}

	/**
	 * 스틸이미지 가져오기
	 * @param movieNo
	 * @return
	 * @throws SQLException 
	 */
	public List<StillImageVo> getStillImageByMovieNo(String movieNo) throws SQLException {
		Connection con = JDBCTemplate.getConnection();
		
		List<StillImageVo> imageList = movieDao.getStillImageByMovieNo(movieNo, con);
		
		JDBCTemplate.close(con);
		return imageList;
	}

	/**
	 * 조건에 맞는 영화 전체 개수 가져오기
	 * @param genres
	 * @param grade
	 * @return
	 * @throws SQLException 
	 */
	public int getAllMovieCountByCondition(String[] genres, String grade) throws SQLException {

		Connection con = JDBCTemplate.getConnection();
		
		int count = movieDao.getAllMovieCountByCondition(genres, grade, con);
		JDBCTemplate.close(con);

		return count;
	}

	/**
	 * 조건에 맞는 영화 개수 가져오기
	 * @param genres
	 * @param grade
	 * @param page 
	 * @return
	 * @throws SQLException 
	 */
	public List<MovieListDto> findMoiveListByCondition(String[] genres, String grade, PageVo page) throws SQLException {
		Connection con = JDBCTemplate.getConnection();
		
		List<MovieListDto> movieList = movieDao.findMoiveListByCondition(genres, grade, page, con);
		JDBCTemplate.close(con);
		return movieList;
	}

	/**
	 * 영화 등록하기
	 * @param newMovie
	 * @param stillImageUrl
	 * @return
	 * @throws SQLException 
	 */
	public int addMovie(MovieVo newMovie, List<String> stillImageUrl) throws SQLException {
		Connection con = JDBCTemplate.getConnection();
		
		int result = movieDao.addMovie(con, newMovie, stillImageUrl);
		
		if(result == 1) {
			JDBCTemplate.commit(con);
		} else {
			JDBCTemplate.rollback(con);
		}
		
		String recentMovieNo = null;
		if(result ==1) {
			recentMovieNo = movieDao.getRecentOne(con);
			
			// 스틸이미지 넣기
			if(stillImageUrl.size() != 0 || stillImageUrl != null || recentMovieNo != null) {
				result = movieDao.addStillImage(con, recentMovieNo, stillImageUrl);
			}
			
			if(result == 1) JDBCTemplate.commit(con);
			else JDBCTemplate.rollback(con);
		} else {
			JDBCTemplate.rollback(con);
		}
		
		if(result == 1) {
			if(newMovie.getGenre() != null) {
				String[] gnereSplit = newMovie.getGenre().split(",");
				for(String s : gnereSplit) {
					String genre = movieDao.getExistingGenres(s, con);		
					
					if(genre == null) {
						result = movieDao.addGenre(s,con);
						
						if(result == 1) {
							JDBCTemplate.commit(con);
							genre = movieDao.getExistingGenres(s, con);		
						}
						else JDBCTemplate.rollback(con);
					} 
					result = movieDao.addGenreCate(genre, recentMovieNo, con);
					
					if(result == 1) {
						JDBCTemplate.commit(con);
					}
					else JDBCTemplate.rollback(con);
				}				
			}
		}

		JDBCTemplate.close(con);
		return result;
	}

	/**
	 * 영화가 상영중인지 체크하기
	 * @param movieNo
	 * @return
	 * @throws SQLException 
	 */
	public int checkScreeningByNo(String movieNo) throws SQLException {
		Connection con = JDBCTemplate.getConnection();
		
		int count = movieDao.checkScreeningByNo(movieNo, con);
		
		JDBCTemplate.close(con);
		return count;
	}

	/**
	 * 영화 삭제하기
	 * @param movieNo
	 * @return
	 * @throws SQLException 
	 */
	public int deleteMovieByNo(String movieNo) throws SQLException {
		Connection con = JDBCTemplate.getConnection();
		
		//스틸 이미지 제거
		int result = movieDao.deleteStillImageByNo(movieNo, con);
		
		if(result != 0) {
			//영화 장르
			result = movieDao.deleteMovieCate(movieNo, con);
			
			if(result != 0) {
				result = movieDao.deleteReviewsByNo(movieNo, con);
				result = movieDao.deleteMovieByNo(movieNo, con);		
				
				if(result != 0) {
					JDBCTemplate.commit(con);
				} else {
					JDBCTemplate.rollback(con);
				}			
			} else {
				JDBCTemplate.rollback(con);
			}
		} else JDBCTemplate.rollback(con);
		
		JDBCTemplate.close(con);
		return result;
	}

	/**
	 * 현재 상영정보가 존재하는 지
	 * @param movieNo
	 * @return
	 * @throws SQLException 
	 */
	public int isScreening(String movieNo) throws SQLException {
		Connection con = JDBCTemplate.getConnection();
		
		int count = movieDao.isScreening(movieNo, con);
		JDBCTemplate.close(con);
		return count;
	}

	
}
