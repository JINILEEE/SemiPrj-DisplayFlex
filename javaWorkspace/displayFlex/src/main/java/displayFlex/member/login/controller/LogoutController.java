package displayFlex.member.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/logout")
public class LogoutController extends HttpServlet {

	// 로그아웃 처리
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		req.getSession().invalidate();	
		HttpSession session = req.getSession();
		session.setAttribute("alertMsg", "실행 성공!");
		resp.sendRedirect("/cinema/home");
	}
	
}
