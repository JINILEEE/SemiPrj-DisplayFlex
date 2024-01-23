package displayFlex.member;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import test.JDBCTemplate;

public class MemberService {

	public MemberVo login(MemberVo vo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		MemberDao dao = new MemberDao();
		MemberVo loginMember = dao.login(conn, vo);
		
		JDBCTemplate.close(conn);
		
		return loginMember;
	}

	public int join(MemberVo vo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
//		//busniesss logic
//		//아이디 4~12 영문소문자 + 숫자
//		String id = vo.getMemberId();
//		boolean idOk = id.matches("[a-z0-9]{4,12}");
//		
//		if(!idOk) {
//			throw new Exception("아이디 길이가 맞지 않습니다.");
//		}
//		//비밀번호 일치여부체크
//		if(!vo.getMemberPwd().equals(vo.getMemberPwd())) {
//			throw new Exception("비밀번호가 일치하지 않습니다.");
//		}
//		//비밀번호 4글자 이상
//		if(vo.getMemberPwd().length() < 4) {
//			throw new Exception("비밀번호는 4글자 이상 써주세요.");
//		}
//		//닉네임에 admin 금지
//		if(vo.getMemberNick().contains("관리자") || vo.getMemberNick().toLowerCase().contains("admin") || vo.getMemberNick().contains("ADMIN")) {
//			throw new Exception("관리자는 안됩니다.");
//		}
		
		//dao
		MemberDao dao = new MemberDao();
		int result = dao.join(conn, vo);
		
		//tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	public MemberVo selectId(MemberVo vo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		MemberDao dao = new MemberDao();
		MemberVo result = dao.selectId(conn, vo);
		
		//close
		JDBCTemplate.close(conn);
		
		return result;
		
	}

	public boolean checkIdDup(String memberId) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		MemberDao dao = new MemberDao();
		boolean result = dao.checkIdDup(conn, memberId);
		
		//close
		JDBCTemplate.close(conn);
		
		return result;
	}

	public MemberVo SelectPwd(MemberVo vo) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		MemberDao dao = new MemberDao();
		MemberVo result = dao.SelectPwd(conn, vo);
		
		//close
		JDBCTemplate.close(conn);
		
		return result;
	}

	public boolean check(String memberName, String memberPhoneNum) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		MemberDao dao = new MemberDao();
		boolean result = dao.check(conn, memberName, memberPhoneNum);
		
		//close
		JDBCTemplate.close(conn);
		
		return result;
	}

	public boolean Pwdcheck(String memberId, String memberName, String memberPhoneNum) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		MemberDao dao = new MemberDao();
		boolean result = dao.pwdCheck(conn, memberId, memberName, memberPhoneNum);
		
		//close
		JDBCTemplate.close(conn);
		
		return result;

	}

	public int edit(String memberNo, String memberNewPwd) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		MemberDao dao = new MemberDao();
		
		int result = dao.edit(conn, memberNo, memberNewPwd);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}

	public int delete(String memberNo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		MemberDao dao = new MemberDao();
		
		int result = dao.delete(conn, memberNo);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}


//	public MemberVo idFindConfirm(String memberId) throws Exception {
//		
//		//conn
//		Connection conn = JDBCTemplate.getConnection();
//		
//		//dao
//		MemberDao dao = new MemberDao();
//		MemberVo result = dao.idFindComfirm(conn, memberId);
//		
//		//close
//		JDBCTemplate.close(conn);
//		
//		
//		return result;
//	}

}
