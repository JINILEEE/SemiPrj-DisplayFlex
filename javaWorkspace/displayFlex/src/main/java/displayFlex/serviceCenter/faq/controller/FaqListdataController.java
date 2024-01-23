package displayFlex.serviceCenter.faq.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import displayFlex.serviceCenter.faq.service.CategoryService;
import displayFlex.serviceCenter.faq.service.FaqService;
import displayFlex.serviceCenter.faq.vo.CategoryVo;
import displayFlex.serviceCenter.faq.vo.FaqVo;
import displayFlex.util.page.vo.PageVo;

@WebServlet("/serviceCenter/faqListdata")
public class FaqListdataController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		try {
			FaqService fs = new FaqService();
			CategoryService cs = new CategoryService();
			
			//data
			String categoryNo = req.getParameter("category");
			
			int listCount = fs.selectFaqCountByCategory(categoryNo);
			String currentPage_ = req.getParameter("pno");
			if(currentPage_ == null) {
				currentPage_ = "1";
			}
			int currentPage = Integer.parseInt(currentPage_);	//현재 페이지
			int pageLimit = 5;									//페이징 영역 페이지갯수
			int faqLimit = 7;									//한 페이지에 보여줄 게시글 갯수
			PageVo pvo = new PageVo(listCount, currentPage, pageLimit, faqLimit);
			
			List<FaqVo> faqVoList = fs.selectFaqListByCategory(categoryNo, pvo);
			List<CategoryVo> categoryList = cs.selectFaqCategoryList();

			req.setAttribute("categoryNo", categoryNo);
			req.setAttribute("pvo", pvo);
			req.setAttribute("faqVoList", faqVoList);
			req.setAttribute("categoryList", categoryList);
			req.getRequestDispatcher("/WEB-INF/views/serviceCenter/faq/faqListdata.jsp").forward(req, resp);
			
		} catch (Exception e) {
			System.out.println("[ERROR-F001] faq 리스트 불러오기 실패");
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp");
		} 

	
	}

}
