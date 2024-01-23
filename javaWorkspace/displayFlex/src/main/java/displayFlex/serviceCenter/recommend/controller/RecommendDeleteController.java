package displayFlex.serviceCenter.recommend.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.member.MemberVo;
import displayFlex.serviceCenter.notice.service.NoticeService;
import displayFlex.serviceCenter.recommend.service.RecommendService;

@WebServlet("/serviceCenter/recommendDelete")
public class RecommendDeleteController extends HttpServlet {
	
	//삭제
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			// data
			String recommendMvNo = req.getParameter("recommendMvNo");
			
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			if(loginMember == null) {
				throw new Exception("잘못된 접근입니다. (로그인 안 하면 삭제안됨)");
			}
			String memberNo = loginMember.getMemberNo();
			
			// service
			RecommendService rs = new RecommendService();
			int result = rs.delete(recommendMvNo, memberNo);
			
			// result == view
			if(result != 1) {
				throw new Exception("상영요청글 삭제 중 에러 발생");
			}
			// 게시글 삭제 성공 => 게시글 목록으로 이동
			req.getSession().setAttribute("alertMsg", "상영요청글 게시글 삭제 성공!");
			resp.sendRedirect("/cinema/serviceCenter/recommendList");
			
		} catch (Exception e) {
			System.out.println("[ERROR-B004] 상영요청글 삭제 중 에러 발생 ...");
			e.printStackTrace();
			req.setAttribute("errorMsg", "상영요청글 삭제 중 에러 발생 ...");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
	
	}

}
