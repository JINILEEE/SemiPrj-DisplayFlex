package displayFlex.event.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import displayFlex.coupon.vo.CouponVo;
import displayFlex.event.dto.EventDto;
import test.JDBCTemplate;

public class EventDao {
	
	
	//이벤트 등록
	public int createEvent(Connection conn, EventDto dto) throws Exception {
	      
	      //SQL
		
			String sql ="INSERT INTO EVENT (EVENT_NO ,EVENTTYPE_NO ,MEMBER_NO ,EVENT_TITLE ,EVENT_CONTENTS ,EVENT_PREPARATIONDATE ,EVENT_REPORTINGDATE ,EVENT_PROGRESS ,EVENT_STARTDATE ,EVENT_ENDDATE ,EVENT_HIT) VALUES (SEQ_EVENT.NEXTVAL, ?, ?, ?, ?, SYSDATE ,SYSDATE, ?, ? , ?, ?)";    //이거 
		
				PreparedStatement pstmt= conn.prepareStatement(sql);
				pstmt.setString(1, dto.getEventtypeNo());
				pstmt.setString(2, "1");
				pstmt.setString(3, dto.getEventTitle());
				pstmt.setString(4, dto.getEventContents());
//				pstmt.setString(5, dto.getEventPreparationdate());
//				pstmt.setString(6, dto.getEventReportingdate());
				pstmt.setString(5, "N");
				pstmt.setString(6, dto.getEventStartdate());
				pstmt.setString(7, dto.getEventEnddate());
				pstmt.setString(8, dto.getEventHit());
				
				int result = pstmt.executeUpdate();
				
				//close
				JDBCTemplate.close(pstmt);
				return result;
	}

	public List<EventDto> selectEventList(Connection conn) throws Exception{
	      
	      //SQL
	      String sql = "SELECT A.EVENT_NO ,A.EVENTTYPE_NO , A.MEMBER_NO ,A.EVENT_TITLE ,A.EVENT_CONTENTS ,A.EVENT_PREPARATIONDATE ,A.EVENT_REPORTINGDATE ,A.EVENT_PROGRESS ,A.EVENT_STARTDATE ,A.EVENT_ENDDATE ,A.EVENT_HIT FROM EVENT A ORDER BY EVENT_NO";
	      
	      
	      
	      
	      PreparedStatement pstmt = conn.prepareStatement(sql);
//	      pstmt.setInt(1, pvo.getStartRow());
//	      pstmt.setInt(2, pvo.getLastRow());
	      
	      ResultSet rs = pstmt.executeQuery();
	   
	      //rs
	      List<EventDto> eventDtoList = new ArrayList<EventDto>();
	      while(rs.next()) {
	         
	         String eventNo = rs.getString("EVENT_NO");
	         String eventTypeNo= rs.getString("EVENTTYPE_NO");
	         String memberNo = rs.getString("MEMBER_NO");
	         String eventTitle = rs.getString("EVENT_TITLE");
	         String eventContents = rs.getString("EVENT_CONTENTS");
	         String eventPreparationDate = rs.getString("EVENT_PREPARATIONDATE");
	         String eventReportingDate = rs.getString("EVENT_REPORTINGDATE");
	         String eventProgress = rs.getString("EVENT_PROGRESS");
	         String eventStartDate = rs.getString("EVENT_STARTDATE");
	         String eventEndDate = rs.getString("EVENT_ENDDATE");
	         String eventHit = rs.getString("EVENT_HIT");
	         
	         
	         EventDto dto = new EventDto();
	         dto.setEventNo(eventNo);
	         dto.setEventtypeNo(eventTypeNo);
	         dto.setMemberNo(memberNo);
	         dto.setEventTitle(eventTitle);
	         dto.setEventContents(eventContents);
	         dto.setEventPreparationdate(eventPreparationDate);
	         dto.setEventReportingdate(eventReportingDate);
	         dto.setEventProgress(eventProgress);
	         dto.setEventStartdate(eventStartDate);
	         dto.setEventEnddate(eventEndDate);
	         dto.setEventHit(eventHit);
	         
	         
	         
	         eventDtoList.add(dto);
	         
	      }
	      
	      
	      //close
	      JDBCTemplate.close(pstmt);
	      JDBCTemplate.close(rs);
	      
	      return eventDtoList;
	   }
	
	
	public EventDto selectEvenDetail(Connection conn, String eventNo) throws Exception{
	      
	      //SQL
	      String sql = "SELECT A.EVENT_NO ,A.EVENTTYPE_NO , A.MEMBER_NO ,A.EVENT_TITLE ,A.EVENT_CONTENTS ,A.EVENT_PREPARATIONDATE ,A.EVENT_REPORTINGDATE ,A.EVENT_PROGRESS ,A.EVENT_STARTDATE ,A.EVENT_ENDDATE ,A.EVENT_HIT FROM EVENT A WHERE A.EVENT_NO = ? ";
	      
	      
	      
	      
	      PreparedStatement pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1,eventNo);
//	      pstmt.setInt(2, pvo.getLastRow());
	      
	      ResultSet rs = pstmt.executeQuery();
	   
	      //rs
	      EventDto dto =new EventDto();
	      while(rs.next()) {
	         
//	         String eventNo = rs.getString("EVENT_NO");
	         String eventTypeNo= rs.getString("EVENTTYPE_NO");
	         String memberNo = rs.getString("MEMBER_NO");
	         String eventTitle = rs.getString("EVENT_TITLE");
	         String eventContents = rs.getString("EVENT_CONTENTS");
	         String eventPreparationDate = rs.getString("EVENT_PREPARATIONDATE");
	         String eventReportingDate = rs.getString("EVENT_REPORTINGDATE");
	         String eventProgress = rs.getString("EVENT_PROGRESS");
	         String eventStartDate = rs.getString("EVENT_STARTDATE");
	         String eventEndDate = rs.getString("EVENT_ENDDATE");
	         String eventHit = rs.getString("EVENT_HIT");
	        
	         dto.setEventNo(eventNo);
	         dto.setEventtypeNo(eventTypeNo);
	         dto.setMemberNo(memberNo);
	         dto.setEventTitle(eventTitle);
	         dto.setEventContents(eventContents);
	         dto.setEventPreparationdate(eventPreparationDate);
	         dto.setEventReportingdate(eventReportingDate);
	         dto.setEventProgress(eventProgress);
	         dto.setEventStartdate(eventStartDate);
	         dto.setEventEnddate(eventEndDate);
	         dto.setEventHit(eventHit);
	         
	         
	         
	         //eventDtoList.add(dto);
	         
	      }
	      
	      
	      //close
	      JDBCTemplate.close(pstmt);
	      JDBCTemplate.close(rs);
	      
	      return dto;
	   }

}//class