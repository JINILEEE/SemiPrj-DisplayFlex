package displayFlex.mypage.inquiry;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import displayFlex.member.MemberVo;
import displayFlex.mypage.MypageService;
import displayFlex.mypage.vo.PageVo;
import displayFlex.serviceCenter.inquiry.vo.InquiryVo;

@WebServlet("/mypage/inquiry")
public class InquiryHistory extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			//data
			MypageService ms = new MypageService();
			int listCount = ms.selectMypageCount();
			
			String currentPage_ = req.getParameter("pno");
			if(currentPage_ == null) {
				currentPage_ = "1";
			}
			int currentPage = Integer.parseInt(currentPage_);
			int pageLimit = 5;
			int boardLimit = 10;
			PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);
			HttpSession session = req.getSession();
			MemberVo memberVo = (MemberVo) session.getAttribute("loginMember");
			String memberNo = memberVo.getMemberNo();
			
			//service
			List<InquiryVo> inquiryVoList = ms.selectInquiryList(pvo, memberNo);
			
			
			//result
			req.setAttribute("inquiryVoList", inquiryVoList);
			req.setAttribute("pvo", pvo);
			
			req.getRequestDispatcher("/WEB-INF/views/mypage/inquiryHistory.jsp").forward(req, resp);
		}catch(Exception e) {
			System.out.println("[ERROR-I001] 1:1 문의 게시글 목록 조회 중 에러 발생...");
			e.printStackTrace();
			req.setAttribute("errorMsg", "1:1 문의 목록 조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
		
		
	}
}
