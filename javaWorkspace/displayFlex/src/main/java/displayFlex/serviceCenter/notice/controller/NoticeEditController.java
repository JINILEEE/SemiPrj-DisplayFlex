package displayFlex.serviceCenter.notice.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.serviceCenter.notice.service.NoticeService;
import displayFlex.serviceCenter.notice.vo.NoticeVo;

@WebServlet("/admin/noticeEdit")
public class NoticeEditController extends HttpServlet {
	
	//게시글 수정 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			//data
			String noticeNo = req.getParameter("noticeNo");
			
			//service
			NoticeService ns = new NoticeService();
			NoticeVo vo = ns.edit(noticeNo);
			
			//result
			if(vo == null) {
				throw new Exception();
			}
			req.setAttribute("vo", vo);
			req.getRequestDispatcher("/WEB-INF/views/serviceCenter/notice/noticeEdit.jsp").forward(req, resp);
		}catch(Exception e) {
			System.out.println("공지사항 수정하기 화면 조회 에러");
			e.printStackTrace();
			req.setAttribute("errorMsg", "공지사항 수정 화면 조회 에러 ...");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			//data
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String noticeNo = req.getParameter("noticeNo");
			
			NoticeVo vo = new NoticeVo();
			vo.setTitle(title);
			vo.setContent(content);
//			vo.setCategoryNo(category);
			vo.setNoticeNo(noticeNo);
			
			//service
			NoticeService ns = new NoticeService();
			int result = ns.edit(vo);
			
			//result
			if(result != 1) {
				throw new Exception();
			}
			resp.sendRedirect("/cinema/serviceCenter/noticeDetail?noticeNo=" + noticeNo);
			
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "공지사항 수정 실패");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
	
	}

}
