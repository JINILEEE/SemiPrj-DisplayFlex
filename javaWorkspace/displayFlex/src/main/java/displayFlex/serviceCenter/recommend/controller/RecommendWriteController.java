package displayFlex.serviceCenter.recommend.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import displayFlex.member.MemberVo;
import displayFlex.serviceCenter.notice.service.NoticeService;
import displayFlex.serviceCenter.notice.vo.NoticeVo;
import displayFlex.serviceCenter.recommend.service.RecommendService;
import displayFlex.serviceCenter.recommend.vo.RecommendVo;

@WebServlet("/serviceCenter/recommendWrite")
public class RecommendWriteController extends HttpServlet {
   
   //작성 화면
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      try {

         //로그인 안되어있으면 에러페이지로 보내기
         MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
         if(loginMember == null) {
            req.setAttribute("errorMsg", "잘못된 접근입니다. (회원 로그인 하고 오세요)");
            req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
            throw new Exception("상영요청 작성 실패");
         }
         
         NoticeService bs = new NoticeService();
         req.getRequestDispatcher("/WEB-INF/views/serviceCenter/recommend/recommendWrite.jsp").forward(req, resp);
         
      } catch(Exception e) {
         e.printStackTrace();
         req.setAttribute("errorMsg", "상영요청 작성하기 (화면) 에러 ");
         req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
      }
   }
   
   //공지사항 작성 로직
   @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   
	   try {
	         
	         HttpSession session = req.getSession();
	         
	         // data
	         String year = req.getParameter("year");
	         String title = req.getParameter("title");
	         String content = req.getParameter("content");
	         MemberVo loginMember = (MemberVo)session.getAttribute("loginMember");
	         
	         if(loginMember == null) {
	            throw new Exception("로그인 안함");
	         }
//	            if(loginAdmin == null) {
//	               throw new Exception("로그인 안했음");
//	            }
	         
	         RecommendVo vo = new RecommendVo();
	         vo.setYear(year);
	         vo.setTitle(title);
	         vo.setContent(content);
	         vo.setMemberNo(loginMember.getMemberNo());
//	         System.out.println(vo);
	         
	         // service
	         RecommendService rs = new RecommendService();
	         int result = rs.write(vo);
	         
	         // result == view
	         if(result != 1) {
	            throw new Exception("result 가 1이 아님");
	         }
	         
	         req.getSession().setAttribute("alertMsg", "상영요청 작성 성공 !");
	         resp.sendRedirect("/cinema/serviceCenter/recommendList");
	         
	      }catch(Exception e) {
	         System.out.println("[ERROR-B004] 상영요청 작성 실패");
	         e.printStackTrace();
	         req.setAttribute("errorMsg", "상영요청 작성 실패");
	         req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
	      }
	   
   
   }

}