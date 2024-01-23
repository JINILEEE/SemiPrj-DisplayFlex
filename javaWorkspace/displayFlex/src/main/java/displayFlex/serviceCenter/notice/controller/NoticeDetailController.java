package displayFlex.serviceCenter.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.serviceCenter.notice.service.NoticeService;
import displayFlex.serviceCenter.notice.vo.NoticeVo;

@WebServlet("/serviceCenter/noticeDetail")
public class NoticeDetailController extends HttpServlet {
	
	//공지사항 상세조회
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {	
			
			// data
			String noticeNo = req.getParameter("noticeNo");
			
			// service
			NoticeService ns = new NoticeService();
			NoticeVo vo = ns.selectNoticeByNo(noticeNo);
			
			// result == view
			req.setAttribute("vo", vo);
			req.setAttribute("currPage", req.getParameter("currPage"));
			req.getRequestDispatcher("/WEB-INF/views/serviceCenter/notice/noticeDetail.jsp").forward(req, resp);
			
		}catch(Exception e) {
			System.out.println("[ERROR-B003] 공지사항 상세조회 중 에러 발생 ...");
			e.printStackTrace();
			req.setAttribute("errorMsg", "공지사항 상세조회 실패...");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
		
	}

}
