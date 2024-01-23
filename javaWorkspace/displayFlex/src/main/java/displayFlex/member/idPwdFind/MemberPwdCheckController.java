package displayFlex.member.idPwdFind;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.member.MemberService;

@WebServlet("/member/pwdFind/check")
public class MemberPwdCheckController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		
		try {
			// data
			String memberId = req.getParameter("memberId");
			String memberName = req.getParameter("memberName");
			String memberPhoneNum = req.getParameter("memberPhoneNum");
			
			System.out.println("memberName = " + memberName);
			System.out.println("memberName = " + memberName);
			System.out.println("memberPhoneNum = " + memberPhoneNum);;
			
			//service
			MemberService ms = new MemberService();
			boolean isOk = ms.Pwdcheck(memberId, memberName , memberPhoneNum);
			//result
			if(isOk) {
				out.write("비밀번호 찾기 성공");
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			System.out.println("[ERROR-M011] 비밀번호 찾기 중 에러발생...");
			resp.setStatus(500);
			e.printStackTrace();
		}
	}

}
