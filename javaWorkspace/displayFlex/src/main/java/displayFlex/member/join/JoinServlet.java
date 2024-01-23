package displayFlex.member.join;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import displayFlex.member.MemberService;
import displayFlex.member.MemberVo;

@WebServlet("/member/join")
public class JoinServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/member/join.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			//data
			String memberId = req.getParameter("memberId");
			String memberPwd = req.getParameter("memberPwd");
			String memberNick = req.getParameter("memberNick");
			String memberName = req.getParameter("memberName");
			String registerNo = req.getParameter("registerNo");
			String memberPhoneNum = req.getParameter("memberPhoneNum");
			String memberEmail = req.getParameter("memberEmail");
			
			MemberVo vo = new MemberVo();
			vo.setMemberId(memberId);
			vo.setMemberPwd(memberPwd);
			vo.setMemberNick(memberNick);
			vo.setMemberName(memberName);
			vo.setRegisterNo(registerNo);
			vo.setMemberPhoneNum(memberPhoneNum);
			vo.setMemberEmail(memberEmail);
			
			//service
			MemberService ms = new MemberService();
			int result = ms.join(vo);
			
			if(result == 1) {
				resp.sendRedirect("/cinema/member/joinConfirm");
			}else {
				throw new Exception("result 값이 1이 아님...!");
			}
		}catch(Exception e) {
			System.out.println("회원가입 중 에러 발생...");
			System.out.println(e.getMessage());
			req.setAttribute("errorMsg", "회원가입 실패...!");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
		
	}

}
