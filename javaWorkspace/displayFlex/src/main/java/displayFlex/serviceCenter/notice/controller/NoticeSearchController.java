package displayFlex.serviceCenter.notice.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.serviceCenter.notice.service.NoticeService;
import displayFlex.serviceCenter.notice.vo.NoticeVo;
import displayFlex.util.page.vo.PageVo;

@WebServlet("/serviceCenter/noticeSearch")
public class NoticeSearchController extends HttpServlet {
	
	//공지사항 검색
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			NoticeService ns = new NoticeService();
			
			// data
			String searchType = req.getParameter("searchType");
			String searchValue = req.getParameter("searchValue");
			System.out.println("searchType = " + searchType);
			System.out.println("searchValue = " + searchValue);
			Map<String, String> m = new HashMap<String, String>();
			m.put("searchType", searchType);
			m.put("searchValue", searchValue);
			
			
			int listCount = ns.selectSearchNoticeCount(m);
			int currentPage = 1;
			if(req.getParameter("pno") != null) {
				currentPage = Integer.parseInt(req.getParameter("pno"));
			}
			int pageLimit = 5;
			int noticeLimit = 10;
			PageVo pvo = new PageVo(listCount, currentPage, pageLimit, noticeLimit);
			
			
			// service
			List<NoticeVo> noticeVoList = ns.search(m , pvo);
			
			// result
			req.setAttribute("noticeVoList", noticeVoList);
			req.setAttribute("pvo", pvo);
			req.setAttribute("searchMap", m);
			req.getRequestDispatcher("/WEB-INF/views/serviceCenter/notice/noticeList.jsp").forward(req, resp);
			
		}catch(Exception e) {
			System.out.println("[ERROR-B123] 공지사항 검색 중 에러 발생 ...");
			e.printStackTrace();
			req.setAttribute("errorMsg", "공지사항 검색 중 에러 발생 ...");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
		
	}

}
