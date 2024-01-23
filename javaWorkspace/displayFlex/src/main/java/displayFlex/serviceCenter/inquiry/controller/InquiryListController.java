package displayFlex.serviceCenter.inquiry.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.serviceCenter.inquiry.service.InquiryService;
import displayFlex.serviceCenter.inquiry.vo.InquiryVo;
import displayFlex.serviceCenter.notice.service.NoticeService;
import displayFlex.serviceCenter.notice.vo.NoticeVo;
import displayFlex.util.page.vo.PageVo;

@WebServlet("/admin/serviceCenter/inquiryList")
public class InquiryListController extends HttpServlet {
	
	//1:1 문의 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			InquiryService is = new InquiryService();
			
			// data
			int listCount = is.selectInquiryCount();				//전체 게시글 갯수
			String currentPage_ = req.getParameter("pno");
			if(currentPage_ == null) {
				currentPage_ = "1";
			}
			int currentPage = Integer.parseInt(currentPage_);	//현재 페이지
			int pageLimit = 5;
			int NoticeLimit = 10;
			PageVo pvo = new PageVo(listCount, currentPage, pageLimit, NoticeLimit);
			
			// service
			List<InquiryVo> inquiryVoList = is.selectInquiryList(pvo);
			
			// result (==view)
			req.setAttribute("inquiryVoList", inquiryVoList);
			req.setAttribute("pvo" , pvo);
			req.getRequestDispatcher("/WEB-INF/views/serviceCenter/inquiry/inquiryList.jsp").forward(req, resp);
			
		}catch(Exception e) {
			System.out.println("[ERROR-B001] 상영요청 목록 조회 중 에러 발생");
			e.printStackTrace();
			req.setAttribute("errorMsg", "상영요청 목록 조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
		
	}
	

}
