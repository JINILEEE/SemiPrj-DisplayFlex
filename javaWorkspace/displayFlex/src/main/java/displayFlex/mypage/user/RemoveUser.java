package displayFlex.mypage.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import displayFlex.member.MemberService;


/**
 * 회원 탈퇴 기능 수행
 */
@WebServlet("/mypage/delete")
public class RemoveUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RemoveUser() {
        super();

    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.service(request, response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String memberNo = request.getParameter("memberNo");
			
			 // 로그인 여부를 체크
	        HttpSession session = request.getSession();
	        Object loginMember = session.getAttribute("loginMember");

	        if (loginMember == null) {
	            // 로그인되어 있지 않으면 오류 처리
	            throw new Exception("로그인되어 있지 않습니다.");
	        }
	        
			MemberService ms = new MemberService();
			int result = ms.delete(memberNo);

			if (result != 1) {
				throw new Exception();
			} else {
				//로그아웃 
				request.getSession().setAttribute("alertMsg", "회원 탙퇴 완료");
				response.sendRedirect(request.getContextPath() +"/member/logout");
			}
		} catch (Exception e) {
			System.out.println("[ERROR-U001] 회원탈퇴 중 에러 발생...");
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() +"/mypage/correct");
		}
	}
}
