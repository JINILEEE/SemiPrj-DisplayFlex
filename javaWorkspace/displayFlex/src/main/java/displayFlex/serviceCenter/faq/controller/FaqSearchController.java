package displayFlex.serviceCenter.faq.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.serviceCenter.faq.service.FaqService;
import displayFlex.serviceCenter.faq.vo.FaqVo;
import displayFlex.serviceCenter.notice.service.NoticeService;
import displayFlex.serviceCenter.notice.vo.NoticeVo;
import displayFlex.util.page.vo.PageVo;

@WebServlet("/serviceCenter/faqSearch")
public class FaqSearchController extends HttpServlet {
	
	// faq 검색 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			FaqService fs = new FaqService();
			
			// data
			String searchType = req.getParameter("searchType");
			String searchValue = req.getParameter("searchValue");
			
			Map<String, String> m = new HashMap<String, String>();
			m.put("searchType", searchType);
			m.put("searchValue", searchValue);
			
			
			int listCount = fs.selectSearchFaqCount(m);
			int currentPage = 1;
			if(req.getParameter("pno") != null) {
				currentPage = Integer.parseInt(req.getParameter("pno"));
			}
			int pageLimit = 5;
			int noticeLimit = 10;
			PageVo pvo = new PageVo(listCount, currentPage, pageLimit, noticeLimit);
			
			
			// service
			List<FaqVo> faqVoList = fs.search(m , pvo);
			
			// result
			req.setAttribute("faqVoList", faqVoList);
			req.setAttribute("pvo", pvo);
			req.setAttribute("searchMap", m);
			req.getRequestDispatcher("/WEB-INF/views/serviceCenter/faq/faqList.jsp").forward(req, resp);
			
		}catch(Exception e) {
			System.out.println("[ERROR-B123] FAQ 검색 중 에러 발생");
			e.printStackTrace();
			req.getSession().setAttribute("alertMsg", "FAQ 검색에 실패하였습니다.");
			resp.sendRedirect("/cinema/serviceCenter/faqSearch");
			return; 
		}

	
	}

}
