package displayFlex.serviceCenter.inquiry.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.serviceCenter.inquiry.service.InquiryService;
import displayFlex.serviceCenter.inquiry.vo.InquiryVo;

@WebServlet("/serviceCenter/inquiryDetail")
public class InquiryDetailController  extends HttpServlet {
	
	//상세조회
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			// data
			String onetooneNo = req.getParameter("onetooneNo");
			
			// service
			InquiryService is = new InquiryService();
			InquiryVo vo = is.selectInquiryByNo(onetooneNo);
//			List<ReplyVo> replyVoList = (List<ReplyVo>) map.get("replyVoList");
			
			// result == view
			req.setAttribute("vo", vo);
			req.setAttribute("currPage", req.getParameter("currPage"));
			req.getRequestDispatcher("/WEB-INF/views/serviceCenter/inquiry/inquiryDetail.jsp").forward(req, resp);
			
		}catch(Exception e) {
			System.out.println("[ERROR-B003] 1:1문의 상세조회 중 에러 발생 ...");
			e.printStackTrace();
			req.setAttribute("errorMsg", "1:1문의 상세조회 실패...");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
		
	}

}
