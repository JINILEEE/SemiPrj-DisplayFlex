package displayFlex.mypage.correct;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import displayFlex.member.MemberService;
import displayFlex.member.MemberVo;

@WebServlet("/mypage/correct/edit")
public class MypagePwdEdit extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String memberNo = req.getParameter("memberNo");
			String memberNewPwd = req.getParameter("password");
			
			System.out.println("서블릿 실행!");
			System.out.println(memberNo);
			System.out.println(memberNewPwd);
			
			//service
			MemberService ms = new MemberService();
			int result = ms.edit(memberNo, memberNewPwd);
			
			if(result != 1) {
				throw new Exception();
			}
			HttpSession session = req.getSession();
	        session.setAttribute("alertMsg", "비밀번호 변경이 완료되었습니다.");
			req.getRequestDispatcher("/WEB-INF/views/mypage/correct.jsp").forward(req, resp);
		
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}

}
