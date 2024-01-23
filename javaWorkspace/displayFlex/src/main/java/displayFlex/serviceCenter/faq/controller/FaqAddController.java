package displayFlex.serviceCenter.faq.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import displayFlex.member.MemberVo;
import displayFlex.serviceCenter.faq.service.FaqService;
import displayFlex.serviceCenter.faq.vo.CategoryVo;
import displayFlex.serviceCenter.faq.vo.FaqVo;
import displayFlex.serviceCenter.notice.service.NoticeService;
import displayFlex.serviceCenter.notice.vo.NoticeVo;

@WebServlet("/admin/faqAdd")
public class FaqAddController extends HttpServlet {
	
	// faq 작성 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {

	         //로그인 안되어있으면 에러페이지로 보내기
	         MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
	         if(loginMember == null) {
	            req.getSession().setAttribute("alertMsg", "잘못된 접근입니다. (관리자 로그인 하고 오세요)");
				resp.sendRedirect("/cinema/admin/faqAdd");
				return; // 로그인이 안 된 경우는 더 이상 진행하지 않도록 리턴
	         }
	         
	         FaqService fs = new FaqService();
	         List<CategoryVo> categoryVoList = fs.getCategoryList(); 
	         req.setAttribute("categoryVoList", categoryVoList);
	         req.getRequestDispatcher("/WEB-INF/views/serviceCenter/faq/faqAdd.jsp").forward(req, resp);
	         
	      } catch(Exception e) {
	         e.printStackTrace();
	         req.setAttribute("errorMsg", "faq 작성하기 (화면) 에러 ");
	         req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
	      }
		
	}
	
	//faq 작성 로직
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			HttpSession session = req.getSession();
	        
	        // data
			String category = req.getParameter("category");
	        String title = req.getParameter("title");
	        String content = req.getParameter("content");
	        MemberVo loginMember = (MemberVo)session.getAttribute("loginMember");
	        
	        if(loginMember == null) {
	           throw new Exception("로그인 안함");
	        }
	        
	        FaqVo vo = new FaqVo();
	        vo.setFaqCategoryNo(category);
	        vo.setTitle(title);
	        vo.setContent(content);
	        System.out.println(vo);
	        
	        // service
	        FaqService fs = new FaqService();
	        int result = fs.write(vo);
	        
	        // result == view
	        if(result != 1) {
	       	 throw new Exception("result 가 1이 아님");
	        }
	        
	        req.getSession().setAttribute("alertMsg", "FAQ가 등록되었습니다.");
	        resp.sendRedirect("/cinema/serviceCenter/faqList");
        
		}catch(Exception e) {
			System.out.println("[ERROR-B002] faq 작성 실패");
			e.printStackTrace();
			req.setAttribute("errorMsg", "faq 등록에 실패하였습니다.");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
	
	}

}
