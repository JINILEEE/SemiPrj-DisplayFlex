package displayFlex.member.idPwdFind;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import displayFlex.member.MemberService;
import displayFlex.member.MemberVo;

@WebServlet("/member/idFind")
public class IdFindServlet extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/member/idFind.jsp").forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
		    MemberService ms = new MemberService();

		    String memberName = req.getParameter("memberName");
		    String memberPhoneNum = req.getParameter("memberPhoneNum");

		    System.out.println("memberName = " + memberName);
		    System.out.println("memberPhoneNum = " + memberPhoneNum);
		    
		    MemberVo vo = new MemberVo();
		    vo.setMemberName(memberName);
		    vo.setMemberPhoneNum(memberPhoneNum);

		    MemberVo result = ms.selectId(vo);
		    
		    System.out.println(result);
		    
		    if(result != null) {
		    	req.setAttribute("vo", result);
		    	req.getRequestDispatcher("/WEB-INF/views/member/idFindConfirm.jsp").forward(req, resp);
		    }else {
		    	req.setAttribute("errorMsg", "일치하는 회원 정보가 없습니다.");
		    	req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		    }
		    	
		} catch(Exception e) {
		    System.out.println("[ERROR-I001] 아이디 찾기 중 에러 발생...!");
		    e.printStackTrace();
		    req.setAttribute("errorMsg", "아이디 찾기 중 에러 발생...!");
		    req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
		
		
		
	}

}
