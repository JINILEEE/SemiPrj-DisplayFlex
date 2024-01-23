package displayFlex.screeninginfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import displayFlex.movie.vo.MovieVo;
import displayFlex.screeninginfo.dto.ScreenInfoDto;
import displayFlex.screeninginfo.vo.ScreeingInfoVo;
import displayFlex.screeninginfo.vo.ScreeningTimeVo;
import displayFlex.util.page.vo.PageVo;
import test.JDBCTemplate;

public class ScreenInfoDao {

	private String query;
	
	/**
	 * 모든 영화 가져오기 - 검색용
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public List<MovieVo> getAllMovie(Connection con) throws SQLException {
		query = "SELECT MOVIE_NO, MOVIE_NAME, TO_CHAR(RELEASE_DATE, 'YYYY-MM-DD') RELEASE_DATE FROM MOVIE";
		PreparedStatement pstmt = con.prepareStatement(query);
		
		ResultSet rs = pstmt.executeQuery();
		List<MovieVo> movieList = new ArrayList<MovieVo>();
		
		while(rs.next()) {
			String movieNo = rs.getString("MOVIE_NO");
			String movietitle = rs.getString("MOVIE_NAME");
			String movieRate = rs.getString("RELEASE_DATE");
			
			movieList.add(new MovieVo(movieNo, movietitle, movieRate));
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return movieList;
	}
	
	/**
	 * 모든 상영관 가져오기
	 * @return
	 * @throws SQLException 
	 */
	public List<String> getAllTheater(Connection con) throws SQLException {
		query = "SELECT THEATER_NO FROM THEATER";
		PreparedStatement pstmt = con.prepareStatement(query);
		
		ResultSet rs = pstmt.executeQuery();
		List<String> theaterList = new ArrayList<String>();
		while(rs.next()) {
			String no = rs.getString("THEATER_NO");
			theaterList.add(no);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return theaterList;
	}

	/**
	 * 기존의 상영정보 데이터가 있는 지 확인 하기
	 * @param screeingInfo 
	 * @param con 
	 * @return
	 * @throws SQLException 
	 */
	public String getInfoNoByCondition(ScreeingInfoVo screeingInfo, Connection con) throws SQLException {
		query = "SELECT SCREENING_INFO_NO FROM SCREENING_INFO WHERE MOVIE_NO = ? AND  THEATER_NO = ? AND TO_CHAR(START_DATE, 'YYYY-MM-DD') = ?";
		
		PreparedStatement pstmt = con.prepareStatement(query);
		
		pstmt.setString(1, screeingInfo.getMovieNo());
		pstmt.setString(2, screeingInfo.getTheaterNo());
		pstmt.setString(3, screeingInfo.getStartDate());
		
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			return rs.getString(1);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return null;
	}

	/**
	 * 신규 상영정보 추가 
	 * @param screeingInfoVo
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public int addScreeningInfo(ScreeingInfoVo screeingInfo, Connection con) throws SQLException {
		query = "INSERT INTO SCREENING_INFO(SCREENING_INFO_NO, MOVIE_NO, THEATER_NO, START_DATE) VALUES (SEQ_SCREENING_INFO.NEXTVAL, ?, ? ,TO_TIMESTAMP(?, 'YYYY-MM-DD'))";
		
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, screeingInfo.getMovieNo());
		pstmt.setString(2, screeingInfo.getTheaterNo());
		pstmt.setString(3, screeingInfo.getStartDate());
		
		int result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		return result;
	}

	/**
	 * 상영 시간 추가
	 * @param screeningTimeVo
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public int addScreeningTime(ScreeningTimeVo screeningTime, Connection con) throws SQLException {
		query = "INSERT INTO SCREENING_TIME(SCREENING_TIME_NO,  SCREENING_INFO_NO, START_TIME, END_TIME) VALUES (SEQ_SCREENING_TIME.NEXTVAL, ?, TO_TIMESTAMP(?, 'YYYY/MM/DD HH24:MI:SS'), TO_TIMESTAMP(?, 'YYYY/MM/DD HH24:MI:SS'))";
		
		PreparedStatement pstmt = con.prepareStatement(query);

		pstmt.setString(1, screeningTime.getScreeningInfoNo());
		pstmt.setString(2, screeningTime.getStartTime());
		pstmt.setString(3, screeningTime.getEndTime());
			
		int result = pstmt.executeUpdate();

		JDBCTemplate.close(pstmt);
		return result;
	}

	/**
	 * 추가한 상영정보 삭제
	 * @param findNo
	 * @param con
	 * @throws SQLException 
	 */
	public int deleteScreeningInfo(String findNo, Connection con) throws SQLException {
		query = "DELETE FROM SCREENING_INFO WHERE SCREENING_INFO_NO = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, findNo);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		return result;
		
	}
	
	/**
	 * 기존 상영 정보가 있는지
	 * @param screenInfoDto
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public int isExistScreeningInfo(ScreenInfoDto screenInfoDto, Connection con) throws SQLException {
		query = "SELECT COUNT(ST.SCREENING_TIME_NO) FROM SCREENING_INFO SI INNER JOIN SCREENING_TIME ST ON SI.SCREENING_INFO_NO = ST.SCREENING_INFO_NO WHERE SI.THEATER_NO = ? AND TO_CHAR(SI.START_DATE, 'YYYY-MM-DD') = ? AND ( (TO_CHAR(ST.START_TIME, 'HH24:MI') BETWEEN ? AND ?) OR (TO_CHAR(ST.END_TIME, 'HH24:MI') BETWEEN ? AND ?) OR (TO_CHAR(ST.END_TIME, 'HH24:MI') <= ? AND TO_CHAR(ST.START_TIME, 'HH24:MI') >= ?) )";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, screenInfoDto.getTheater());
		pstmt.setString(2, screenInfoDto.getDate());
		pstmt.setString(3, screenInfoDto.getStartTime());
		pstmt.setString(4, screenInfoDto.getEndTime());
		pstmt.setString(5, screenInfoDto.getStartTime());
		pstmt.setString(6, screenInfoDto.getEndTime());
		pstmt.setString(7, screenInfoDto.getEndTime());
		pstmt.setString(8, screenInfoDto.getStartTime());
		
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
	 * 전체 상영정보 개수 가져오기
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public int getTotalCount(Connection con) throws SQLException {
		query = "SELECT COUNT(SCREENING_TIME_NO) FROM SCREENING_TIME WHERE END_TIME > LOCALTIMESTAMP";
		PreparedStatement pstmt = con.prepareStatement(query);
		
		ResultSet rs = pstmt.executeQuery();
		int totalCount = 0;
		if(rs.next()) {
			totalCount = rs.getInt(1);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return totalCount;
	}

	/**
	 * 전체 상영정보 출력
	 * @param page
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public List<ScreenInfoDto> getInfoList(PageVo page, Connection con) throws SQLException {
		query ="SELECT A.RNUM , A.SCREENING_TIME_NO , A.THEATER_NO, A.START_DATE , A.START_TIME , A.END_TIME , M.MOVIE_NAME FROM( SELECT ROW_NUMBER() OVER(ORDER BY ST.END_TIME ASC) RNUM , ST.SCREENING_TIME_NO , SI.MOVIE_NO , SI.THEATER_NO , TO_CHAR(SI.START_DATE, 'YYYY\"년 \"MM\"월 \"DD\"일\"') START_DATE , TO_CHAR(ST.START_TIME, 'HH24:MI') START_TIME , TO_CHAR(ST.END_TIME, 'HH24:MI') END_TIME FROM SCREENING_INFO SI INNER JOIN SCREENING_TIME ST ON SI.SCREENING_INFO_NO = ST.SCREENING_INFO_NO WHERE ST.END_TIME > LOCALTIMESTAMP ) A INNER JOIN MOVIE M ON A.MOVIE_NO = M.MOVIE_NO WHERE A.RNUM BETWEEN ? AND ? ORDER BY 1";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, page.getStartRow());
		pstmt.setInt(2, page.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		List<ScreenInfoDto> infoList = new ArrayList<ScreenInfoDto>();
		
		while(rs.next()) {
			String screeningTimeNo = rs.getString("SCREENING_TIME_NO");
			String title = rs.getString("MOVIE_NAME");
			String theaterNo = rs.getString("THEATER_NO");
			String startDate = rs.getString("START_DATE");
			String startTime = rs.getString("START_TIME");
			String endTime = rs.getString("END_TIME");
			
			infoList.add(new ScreenInfoDto(screeningTimeNo, title, theaterNo, startDate, startTime, endTime));
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return infoList;
	}

	/**
	 * 상영정보 삭제
	 * @param infoNo
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public int deleteByNo(String infoNo, Connection con) throws SQLException {
		query = "DELETE FROM SCREENING_TIME WHERE SCREENING_TIME_NO = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, infoNo);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		return result;
	}

	/**
	 * 조건에 맞는 상영 정보 개수 가져오기
	 * @param screenInfoDto
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public int getTotalCountByCondition(ScreenInfoDto screenInfoDto, Connection con) throws SQLException {
		StringBuilder dynamicQuery = new StringBuilder("SELECT COUNT(ST.SCREENING_TIME_NO) FROM SCREENING_TIME ST INNER JOIN SCREENING_INFO SI ON ST.SCREENING_INFO_NO = SI.SCREENING_INFO_NO INNER JOIN MOVIE M ON SI.MOVIE_NO = M.MOVIE_NO");
		int index = 1;
		if(screenInfoDto.getTitle() != null || screenInfoDto.getTheater() !=null || screenInfoDto.getDate() !=null || screenInfoDto.getStartTime() !=null || screenInfoDto.getEndTime() !=null) {
			dynamicQuery.append(" WHERE");
			
			if(screenInfoDto.getTitle() != null) {
				dynamicQuery.append(" M.MOVIE_NAME LIKE '%' || ? ||'%'");
				
				if(screenInfoDto.getTheater() != null) {
					dynamicQuery.append(" AND SI.THEATER_NO = ?");
				}
				
				if (screenInfoDto.getDate() != null) {
					 dynamicQuery.append(" AND TO_CHAR(SI.START_DATE, 'YYYY-MM-DD') = ?");
				 }
				 
				if(screenInfoDto.getStartTime() != null) {
					dynamicQuery.append(" AND TO_CHAR(ST.START_TIME, 'HH24:MI') >= ?");					
				}
				
				if(screenInfoDto.getEndTime() != null) {
					dynamicQuery.append(" AND TO_CHAR(ST.END_TIME, 'HH24:MI') <= ?");					
				}
				
			} else if(screenInfoDto.getTheater() != null) {
				dynamicQuery.append(" SI.THEATER_NO = ?");
				
				 if (screenInfoDto.getDate() != null) {
					 dynamicQuery.append(" AND TO_CHAR(SI.START_DATE, 'YYYY-MM-DD') = ?");
				 }
				 
				if(screenInfoDto.getStartTime() != null) {
					dynamicQuery.append(" AND TO_CHAR(ST.START_TIME, 'HH24:MI') >= ?");					
				}
				
				if(screenInfoDto.getEndTime() != null) {
					dynamicQuery.append(" AND TO_CHAR(ST.END_TIME, 'HH24:MI') <= ?");					
				}
				
			} else if (screenInfoDto.getDate() != null) {
				dynamicQuery.append(" TO_CHAR(SI.START_DATE, 'YYYY-MM-DD') = ?");
				
				if(screenInfoDto.getStartTime() != null) {
					dynamicQuery.append(" AND TO_CHAR(ST.START_TIME, 'HH24:MI') >= ?");					
				}
				
				if(screenInfoDto.getEndTime() != null) {
					dynamicQuery.append(" AND TO_CHAR(ST.END_TIME, 'HH24:MI') <= ?");					
				}
				
			} else if(screenInfoDto.getStartTime() != null) {
				dynamicQuery.append(" TO_CHAR(ST.START_TIME, 'HH24:MI') >= ?");
				
				if(screenInfoDto.getEndTime() != null) {
					dynamicQuery.append(" AND TO_CHAR(ST.END_TIME, 'HH24:MI') <= ?");					
				}
			}
		}
		
		PreparedStatement pstmt = con.prepareStatement(dynamicQuery.toString());
		
		if(screenInfoDto.getTitle() != null) {
			pstmt.setString(index, screenInfoDto.getTitle());
			index++;
			
			if(screenInfoDto.getTheater() != null) {
				pstmt.setString(index, screenInfoDto.getTheater());
				index++;
			}
			
			if(screenInfoDto.getDate() != null) {
				pstmt.setString(index, screenInfoDto.getDate());
				index++;
			}
			
			if(screenInfoDto.getStartTime() != null) {
				pstmt.setString(index, screenInfoDto.getStartTime());
				index++;
			}
			
			if(screenInfoDto.getEndTime() != null) {
				pstmt.setString(index, screenInfoDto.getEndTime());
				index++;
			}
			
		} else if(screenInfoDto.getTheater() != null) {
			pstmt.setString(index, screenInfoDto.getTheater());
			index++;
			
			if(screenInfoDto.getDate() != null) {
				pstmt.setString(index, screenInfoDto.getDate());
				index++;
			}
			
			if(screenInfoDto.getStartTime() != null) {
				pstmt.setString(index, screenInfoDto.getStartTime());
				index++;
			}
			
			if(screenInfoDto.getEndTime() != null) {
				pstmt.setString(index, screenInfoDto.getEndTime());
				index++;
			}
			
		} else if (screenInfoDto.getDate() != null) {
			pstmt.setString(index, screenInfoDto.getDate());
			index++;
			
			if(screenInfoDto.getStartTime() != null) {
				pstmt.setString(index, screenInfoDto.getStartTime());
				index++;
			}
			
			if(screenInfoDto.getEndTime() != null) {
				pstmt.setString(index, screenInfoDto.getEndTime());
				index++;
			}
			
		} else if(screenInfoDto.getStartTime() != null) {
			pstmt.setString(index, screenInfoDto.getStartTime());
			index++;
			
			if(screenInfoDto.getEndTime() != null) {
				pstmt.setString(index, screenInfoDto.getEndTime());
				index++;				
			}
		}

		
		ResultSet rs = pstmt.executeQuery();
		int count = 0;
		if(rs.next())  {
			count = rs.getInt(1);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return count;
	}

	/**
	 * 조건에 맞는 상영 정보리스트 출력하기
	 * @param screenInfoDto 
	 * @param page
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public List<ScreenInfoDto> getInfoListByCondition(ScreenInfoDto screenInfoDto, PageVo page, Connection con) throws SQLException {
		StringBuilder dynamicQuery = new StringBuilder("SELECT A.* FROM( SELECT ROW_NUMBER() OVER(ORDER BY ST.END_TIME ASC) RNUM , ST.SCREENING_TIME_NO , M.MOVIE_NAME , SI.THEATER_NO , TO_CHAR(SI.START_DATE, 'YYYY\"년 \"MM\"월 \"DD\"일\"') START_DATE , TO_CHAR(ST.START_TIME, 'HH24:MI') START_TIME , TO_CHAR(ST.END_TIME, 'HH24:MI') END_TIME FROM SCREENING_INFO SI INNER JOIN SCREENING_TIME ST ON SI.SCREENING_INFO_NO = ST.SCREENING_INFO_NO INNER JOIN MOVIE M ON SI.MOVIE_NO = M.MOVIE_NO");
		int index = 1;
		if(screenInfoDto.getTitle() != null || screenInfoDto.getTheater() !=null || screenInfoDto.getDate() !=null || screenInfoDto.getStartTime() !=null || screenInfoDto.getEndTime() !=null) {
			dynamicQuery.append(" WHERE");
			
			if(screenInfoDto.getTitle() != null) {
				dynamicQuery.append(" M.MOVIE_NAME LIKE '%' || ? ||'%'");
				
				if(screenInfoDto.getTheater() != null) {
					dynamicQuery.append(" AND SI.THEATER_NO = ?");
				}
				
				if (screenInfoDto.getDate() != null) {
					 dynamicQuery.append(" AND TO_CHAR(SI.START_DATE, 'YYYY-MM-DD') = ?");
				 }
				 
				if(screenInfoDto.getStartTime() != null) {
					dynamicQuery.append(" AND TO_CHAR(ST.START_TIME, 'HH24:MI') >= ?");					
				}
				
				if(screenInfoDto.getEndTime() != null) {
					dynamicQuery.append(" AND TO_CHAR(ST.END_TIME, 'HH24:MI') <= ?");					
				}
				
			} else if(screenInfoDto.getTheater() != null) {
				dynamicQuery.append(" SI.THEATER_NO = ?");
				
				 if (screenInfoDto.getDate() != null) {
					 dynamicQuery.append(" AND TO_CHAR(SI.START_DATE, 'YYYY-MM-DD') = ?");
				 }
				 
				if(screenInfoDto.getStartTime() != null) {
					dynamicQuery.append(" AND TO_CHAR(ST.START_TIME, 'HH24:MI') >= ?");					
				}
				
				if(screenInfoDto.getEndTime() != null) {
					dynamicQuery.append(" AND TO_CHAR(ST.END_TIME, 'HH24:MI') <= ?");					
				}
				
			} else if (screenInfoDto.getDate() != null) {
				dynamicQuery.append(" TO_CHAR(SI.START_DATE, 'YYYY-MM-DD') = ?");
				
				if(screenInfoDto.getStartTime() != null) {
					dynamicQuery.append(" AND TO_CHAR(ST.START_TIME, 'HH24:MI') >= ?");					
				}
				
				if(screenInfoDto.getEndTime() != null) {
					dynamicQuery.append(" AND TO_CHAR(ST.END_TIME, 'HH24:MI') <= ?");					
				}
				
			} else if(screenInfoDto.getStartTime() != null) {
				dynamicQuery.append(" TO_CHAR(ST.START_TIME, 'HH24:MI') >= ?");
				
				if(screenInfoDto.getEndTime() != null) {
					dynamicQuery.append(" AND TO_CHAR(ST.END_TIME, 'HH24:MI') <= ?");					
				}
			}
		}
		dynamicQuery.append(") A WHERE A.RNUM BETWEEN ? AND ? ORDER BY 1");
		PreparedStatement pstmt = con.prepareStatement(dynamicQuery.toString());
		
		if(screenInfoDto.getTitle() != null) {
			pstmt.setString(index, screenInfoDto.getTitle());
			index++;
			
			if(screenInfoDto.getTheater() != null) {
				pstmt.setString(index, screenInfoDto.getTheater());
				index++;
			}
			
			if(screenInfoDto.getDate() != null) {
				pstmt.setString(index, screenInfoDto.getDate());
				index++;
			}
			
			if(screenInfoDto.getStartTime() != null) {
				pstmt.setString(index, screenInfoDto.getStartTime());
				index++;
			}
			
			if(screenInfoDto.getEndTime() != null) {
				pstmt.setString(index, screenInfoDto.getEndTime());
				index++;
			}
			
		} else if(screenInfoDto.getTheater() != null) {
			pstmt.setString(index, screenInfoDto.getTheater());
			index++;
			
			if(screenInfoDto.getDate() != null) {
				pstmt.setString(index, screenInfoDto.getDate());
				index++;
			}
			
			if(screenInfoDto.getStartTime() != null) {
				pstmt.setString(index, screenInfoDto.getStartTime());
				index++;
			}
			
			if(screenInfoDto.getEndTime() != null) {
				pstmt.setString(index, screenInfoDto.getEndTime());
				index++;
			}
			
		} else if (screenInfoDto.getDate() != null) {
			pstmt.setString(index, screenInfoDto.getDate());
			index++;
			
			if(screenInfoDto.getStartTime() != null) {
				pstmt.setString(index, screenInfoDto.getStartTime());
				index++;
			}
			
			if(screenInfoDto.getEndTime() != null) {
				pstmt.setString(index, screenInfoDto.getEndTime());
				index++;
			}
			
		} else if(screenInfoDto.getStartTime() != null) {
			pstmt.setString(index, screenInfoDto.getStartTime());
			index++;
			
			if(screenInfoDto.getEndTime() != null) {
				pstmt.setString(index, screenInfoDto.getEndTime());
				index++;				
			}
		}
		
		pstmt.setInt(index, page.getStartRow());
		index++;
		pstmt.setInt(index, page.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		List<ScreenInfoDto> infoList = new ArrayList<ScreenInfoDto>();
		
		while(rs.next()) {
			String screeningTimeNo = rs.getString("SCREENING_TIME_NO");
			String title = rs.getString("MOVIE_NAME");
			String theaterNo = rs.getString("THEATER_NO");
			String startDate = rs.getString("START_DATE");
			String startTime = rs.getString("START_TIME");
			String endTime = rs.getString("END_TIME");
			
			infoList.add(new ScreenInfoDto(screeningTimeNo, title, theaterNo, startDate, startTime, endTime));
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return infoList;
	}

}
