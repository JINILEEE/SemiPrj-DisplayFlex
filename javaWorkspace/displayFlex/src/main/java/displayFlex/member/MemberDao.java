package displayFlex.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import displayFlex.event.dto.EventDto;
import test.JDBCTemplate;

public class MemberDao {

	public MemberVo login(Connection conn, MemberVo vo) throws Exception {
		
		//sql
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PWD = ? AND DELETE_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemberId());
		pstmt.setString(2, vo.getMemberPwd());
		ResultSet rs = pstmt. executeQuery();
		
		MemberVo loginMember = null;
		if(rs.next()) {
			String memberNo = rs.getString("MEMBER_NO");
			String gradeNo = rs.getString("GRADE_NO");
			String adminYn = rs.getString("ADMIN_YN");
			String memberId = rs.getString("MEMBER_ID");
			String memberPwd = rs.getString("MEMBER_PWD");
			String memberNick = rs.getString("MEMBER_NICK");
			String memberEmail = rs.getString("MEMBER_EMAIL");
			String memberPhoneNum = rs.getString("MEMBER_PHONENUM");
			String enrolldate = rs.getString("ENROLLDATE");
			String deleteYn = rs.getString("DELETE_YN");
			String privilegedYn = rs.getString("PREVILEGED_YN");
			String registerNo = rs.getString("REGISTER_NO");
			String gradeScore = rs.getString("GRADE_SCORE");
			
			loginMember = new MemberVo();
			
			loginMember.setMemberNo(memberNo);
			loginMember.setGradeNo(gradeNo);
			loginMember.setAdminYn(adminYn);
			loginMember.setMemberId(memberId);
			loginMember.setMemberPwd(memberPwd);
			loginMember.setMemberNick(memberNick);
			loginMember.setMemberEmail(memberEmail);
			loginMember.setMemberPhoneNum(memberPhoneNum);
			loginMember.setEnrolldate(enrolldate);
			loginMember.setDeleteYn(deleteYn);
			loginMember.setPrivilegedYn(privilegedYn);
			loginMember.setRegisterNo(registerNo);
			loginMember.setGradeScore(gradeScore);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return loginMember;
	}

	public int join(Connection conn, MemberVo vo) throws Exception {
		//sql
		String sql = "INSERT INTO MEMBER ( MEMBER_NO, MEMBER_ID ,MEMBER_PWD ,MEMBER_NICK ,MEMBER_NAME, REGISTER_NO, MEMBER_PHONENUM ,MEMBER_EMAIL ) VALUES ( SEQ_MEMBER_NO.NEXTVAL , ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemberId());
		pstmt.setString(2, vo.getMemberPwd());
		pstmt.setString(3, vo.getMemberNick());
		pstmt.setString(4, vo.getMemberName());
		pstmt.setString(5, vo.getRegisterNo());
		pstmt.setString(6, vo.getMemberPhoneNum());
		pstmt.setString(7, vo.getMemberEmail());
		int result = pstmt.executeUpdate();

		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public MemberVo selectId(Connection conn, MemberVo vo) throws Exception {
	    //sql
	    String sql = "SELECT MEMBER_ID, MEMBER_NAME, MEMBER_PHONENUM FROM MEMBER WHERE MEMBER_NAME = ? AND MEMBER_PHONENUM = ?";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, vo.getMemberName());
	    pstmt.setString(2, vo.getMemberPhoneNum());
	    ResultSet rs = pstmt.executeQuery();

	    //rs
	    MemberVo dto = null;
	    while(rs.next()) {

	        String memberId = rs.getString("MEMBER_ID");
	        String memberName = rs.getString("MEMBER_NAME");
	        String memberPhoneNum = rs.getString("MEMBER_PHONENUM");

	       dto = new MemberVo(); // 반복문 안에서 객체를 생성하여 각 회원 정보를 담아야 함
	        dto.setMemberId(memberId);
	        dto.setMemberName(memberName);
	        dto.setMemberPhoneNum(memberPhoneNum);

	    }

	    JDBCTemplate.close(rs);
	    JDBCTemplate.close(pstmt);

	    return dto;
	}
	
	public boolean checkIdDup(Connection conn, String memberId) throws Exception {
		
		//sql
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberId);
		ResultSet rs = pstmt.executeQuery();
		
		boolean result = true;
		if(rs.next()) {
			result = false;
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public MemberVo SelectPwd(Connection conn, MemberVo vo) throws Exception {
		 //sql
	    String sql = "SELECT MEMBER_ID, MEMBER_NAME, MEMBER_PHONENUM, MEMBER_PWD, MEMBER_EMAIL FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_NAME = ? AND MEMBER_PHONENUM = ?";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, vo.getMemberId());
	    pstmt.setString(2, vo.getMemberName());
	    pstmt.setString(3, vo.getMemberPhoneNum());
	    ResultSet rs = pstmt.executeQuery();

	    //rs
	    
	    MemberVo dto = null;
	    while(rs.next()) {

	        String memberId = rs.getString("MEMBER_ID");
	        String memberName = rs.getString("MEMBER_NAME");
	        String memberPwd = rs.getString("MEMBER_PWD");
	        String memberPhoneNum = rs.getString("MEMBER_PHONENUM");
	        String memberEmail = rs.getString("MEMBER_EMAIL");

	        dto = new MemberVo(); // 반복문 안에서 객체를 생성하여 각 회원 정보를 담아야 함
	        dto.setMemberId(memberId);
	        dto.setMemberName(memberName);
	        dto.setMemberPwd(memberPwd);
	        dto.setMemberPhoneNum(memberPhoneNum);
	        dto.setMemberEmail(memberEmail);

	    }

	    JDBCTemplate.close(rs);
	    JDBCTemplate.close(pstmt);

	    return dto;
	}

	public boolean check(Connection conn, String memberName , String memberPhoneNum) throws Exception {
		
		//sql
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_NAME = ? AND MEMBER_PHONENUM = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberName);
		pstmt.setString(2, memberPhoneNum);
		ResultSet rs = pstmt.executeQuery();
		
		boolean result = false;
		if(rs.next()) {
			result = true;
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public boolean pwdCheck(Connection conn, String memberId, String memberName, String memberPhoneNum) throws Exception {
		
		//sql
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_NAME = ? AND MEMBER_PHONENUM = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberId);
		pstmt.setString(2, memberName);
		pstmt.setString(3, memberPhoneNum);
		ResultSet rs = pstmt.executeQuery();
		
		boolean result = false;
		if(rs.next()) {
			result = true;
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}

	public int edit(Connection conn, String memberNo, String memberNewPwd) throws Exception {
		
		String sql = "UPDATE MEMBER SET MEMBER_PWD = ? WHERE MEMBER_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberNewPwd);
		pstmt.setString(2, memberNo);
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;

	}

	public int delete(Connection conn, String memberNo) throws Exception {
		
		//sql
		String sql = "UPDATE MEMBER SET DELETE_YN = 'Y' WHERE MEMBER_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberNo);
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
	}


}
