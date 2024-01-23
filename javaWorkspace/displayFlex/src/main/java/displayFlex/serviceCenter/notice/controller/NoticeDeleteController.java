package displayFlex.serviceCenter.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.member.MemberVo;
import displayFlex.serviceCenter.notice.service.NoticeService;

@WebServlet("/admin/noticeDelete")
public class NoticeDeleteController extends HttpServlet {
	
	// 게시글 삭제
	// UPDATE NOTICE SET STATUS = 'X' WHERE NO = ? and writer_no = ?
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// data
			String noticeNo = req.getParameter("noticeNo");
			
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			if(loginMember == null) {
				throw new Exception("잘못된 접근입니다. (로그인 안 하면 삭제안됨)");
			}
			String memberNo = loginMember.getMemberNo();
			
			// service
			NoticeService ns = new NoticeService();
			int result = ns.delete(noticeNo, memberNo);
			
			// result == view
			if(result != 1) {
				throw new Exception("공지사항 삭제 중 에러 발생");
			}
			// 게시글 삭제 성공 => 게시글 목록으로 이동
			req.getSession().setAttribute("alertMsg", "공지사항 게시글 삭제 성공!");
			resp.sendRedirect("/cinema/serviceCenter/noticeList");
			
		}catch(Exception e) {
			System.out.println("[ERROR-B004] 공지사항 삭제 중 에러 발생 ...");
			e.printStackTrace();
			req.setAttribute("errorMsg", "공지사항 삭제 중 에러 발생 ...");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
	}//doGet

}
