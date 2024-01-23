package displayFlex.mypage.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import displayFlex.member.MemberService;
import displayFlex.member.MemberVo;

@WebServlet("/mypage/main")
public class Main extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			req.getRequestDispatcher("/WEB-INF/views/mypage/main.jsp").forward(req, resp);
			
//			String memberNick = req.getParameter("memberNick");
//			System.out.println(memberNick);
//			
//			MemberVo vo = new MemberVo();
//			vo.setMemberNick(memberNick);
//			
//			MemberService ms = new MemberService();
//			MemberVo loginMember = ms.login(vo);
//			
//			//result
//			if(loginMember == null) {
//				throw new Exception("로그인 실패...!");
//			}
//				
//			HttpSession session = req.getSession();
//			session.setAttribute("alertMsg", "로그인 성공!");
//			session.setAttribute("loginMember", loginMember);
			
		}catch(Exception e) {
			System.out.println("[ERROR-M002] 로그인 중 예외 발생...!!");
			e.printStackTrace();
			req.setAttribute("errorMsg", "로그인 실패");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
			
		}
	}
}
