package displayFlex.member.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import displayFlex.member.MemberService;
import displayFlex.member.MemberVo;


@WebServlet("/member/login")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String memberId = req.getParameter("memberId");
			String memberPwd = req.getParameter("memberPwd");
			
			MemberVo vo = new MemberVo();
			
			vo.setMemberId(memberId);
			vo.setMemberPwd(memberPwd);
			
			//service
			MemberService ms = new MemberService();
			MemberVo loginMember = ms.login(vo);
			
			
			//result
			if(loginMember == null) {
				throw new Exception("로그인 실패...!");
			}
				
			HttpSession session = req.getSession();
			session.setAttribute("alertMsg", "로그인 성공!");
			session.setAttribute("loginMember", loginMember);
			resp.sendRedirect("/cinema/home");
		}catch(Exception e) {
			System.out.println("[ERROR-M002] 로그인 중 예외 발생...!!");
			e.printStackTrace();
			req.setAttribute("errorMsg", "로그인 실패");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
			
		}
		
	}
	
}
	