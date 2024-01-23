package displayFlex.serviceCenter.faq.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.serviceCenter.faq.service.FaqService;
import displayFlex.serviceCenter.faq.vo.CategoryVo;
import displayFlex.serviceCenter.faq.vo.FaqVo;
import displayFlex.serviceCenter.notice.service.NoticeService;
import displayFlex.serviceCenter.notice.vo.NoticeVo;

@WebServlet("/admin/faqEdit")
public class FaqEditController extends HttpServlet {
	
	//faq 수정 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			//data
			String faqNo = req.getParameter("faqNo");
			
			//service
			FaqService fs = new FaqService();
			Map<String, Object> m = fs.edit(faqNo);
			FaqVo vo = (FaqVo) m.get("vo");
			List<CategoryVo> categoryVoList = (List<CategoryVo>) m.get("categoryVoList");
			
			//result
			if(vo == null) {
//				System.out.println(vo);
				throw new Exception();
			}
			
			req.setAttribute("vo", vo);
			req.setAttribute("categoryVoList", categoryVoList);
			req.getRequestDispatcher("/WEB-INF/views/serviceCenter/faq/faqEdit.jsp").forward(req, resp);
		}catch(Exception e) {
			System.out.println("FAQ 수정하기 화면 조회 에러");
			e.printStackTrace();
			req.setAttribute("errorMsg", "FAQ 수정 화면 조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
		
	}
	
	//faq 수정 로직
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			//data
			String category = req.getParameter("category");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String faqNo = req.getParameter("faqNo");
			
			FaqVo vo = new FaqVo();
			vo.setFaqCategoryNo(category);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setFaqNo(faqNo);
			
			//service
			FaqService fs = new FaqService();
			int result = fs.edit(vo);
			
			//result
			if(result != 1) {
				throw new Exception();
			}
			resp.sendRedirect("/cinema/serviceCenter/faqDetail?faqNo=" + faqNo);
			
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("alertMsg", "FAQ 수정 실패하였습니다.");
			resp.sendRedirect("/cinema/admin/faqEdit");
			return;
		}

	}

}
