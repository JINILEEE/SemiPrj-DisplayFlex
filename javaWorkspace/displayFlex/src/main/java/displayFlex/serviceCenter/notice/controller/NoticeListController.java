package displayFlex.serviceCenter.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.serviceCenter.notice.service.NoticeService;
import displayFlex.serviceCenter.notice.vo.NoticeVo;
import displayFlex.util.page.vo.PageVo;

@WebServlet("/serviceCenter/noticeList")
public class NoticeListController extends HttpServlet {
	
	//공지사항 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			NoticeService ns = new NoticeService();
			
			// data
			int listCount = ns.selectBoardCount();				//전체 게시글 갯수
			String currentPage_ = req.getParameter("pno");
			if(currentPage_ == null) {
				currentPage_ = "1";
			}
			int currentPage = Integer.parseInt(currentPage_);	//현재 페이지
			int pageLimit = 5;
			int NoticeLimit = 10;
			PageVo pvo = new PageVo(listCount, currentPage, pageLimit, NoticeLimit);
			
			// service
			List<NoticeVo> noticeVoList = ns.selectNoticeList(pvo);
			
			// result (==view)
			req.setAttribute("noticeVoList", noticeVoList);
			req.setAttribute("pvo" , pvo);
			req.getRequestDispatcher("/WEB-INF/views/serviceCenter/notice/noticeList.jsp").forward(req, resp);
			
		}catch(Exception e) {
			System.out.println("[ERROR-B001] 공지사항 목록 조회 중 에러 발생");
			e.printStackTrace();
			req.setAttribute("errorMsg", "공지사항 목록 조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
		
	}
	
		
	
	
	//공지사항 로직
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
