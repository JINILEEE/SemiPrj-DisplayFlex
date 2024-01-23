package displayFlex.serviceCenter.faq.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.member.MemberVo;
import displayFlex.serviceCenter.faq.service.FaqService;
import displayFlex.serviceCenter.notice.service.NoticeService;

@WebServlet("/admin/faqDelete")
public class FaqDeleteController extends HttpServlet {
	
	//faq 삭제 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			// data
			String faqNo = req.getParameter("faqNo");
			
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			if(loginMember == null) {
				req.getSession().setAttribute("alertMsg", "로그인 후 이용해 주세요.");
				resp.sendRedirect("/cinema/admin/faqDelete");
				return; // 로그인이 안 된 경우는 더 이상 진행하지 않도록 리턴
			}
			String memberNo = loginMember.getMemberNo();
			
			// service
			FaqService fs = new FaqService();
			int result = fs.delete(faqNo, memberNo);
			
			// result == view
			if(result != 1) {
				throw new Exception("FAQ 삭제 중 에러 발생");
			}
			
			// 게시글 삭제 성공 => 게시글 목록으로 이동
			req.getSession().setAttribute("alertMsg", "FAQ가 삭제되었습니다.");
			resp.sendRedirect("/cinema/serviceCenter/faqList");
			
		}catch(Exception e) {
			System.out.println("[ERROR-B004] FAQ 삭제 중 에러 발생");
			e.printStackTrace();
			req.setAttribute("alertMsg", "FAQ 삭제가 취소되었습니다. (에러 발생)");
			resp.sendRedirect("/cinema/admin/faqDelete");
		}

	
	}

}
