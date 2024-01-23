package displayFlex.mypage.inquiry;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.mypage.MypageService;
import displayFlex.mypage.vo.PageVo;
import displayFlex.serviceCenter.inquiry.vo.InquiryVo;

@WebServlet("/mypage/inquiry/search")
public class InquirySearchController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			MypageService ms = new MypageService();
			
			//data
			String searchType = req.getParameter("searchType");
			String searchValue = req.getParameter("searchValue");
			
			Map<String, String> m = new HashMap<String, String>();
			m.put("searchType", searchType);
			m.put("searchValue", searchValue);
			
			int listCount = ms.selectSearchInquiryCount(m);
			int currentPage = 1;
			if(req.getParameter("pno") != null) {
				currentPage = Integer.parseInt(req.getParameter("pno"));
			}
			int pageLimit = 5;
			int allLimit = 10;
			PageVo pvo = new PageVo(listCount, currentPage, pageLimit, allLimit);
			
			
			//service
			List<InquiryVo> inquiryVoList = ms.search(m, pvo);
			
			//result
			req.setAttribute("inquiryVoList", inquiryVoList);
			req.setAttribute("pvo", pvo);
			req.setAttribute("searchMap", m);
			req.getRequestDispatcher("/WEB-INF/views/mypage/inquiryHistory.jsp").forward(req, resp);
			
		}catch(Exception e) {
			System.out.println("[ERROR-I123] 1:1 문의 조회 중 에러 발생...!");
			e.printStackTrace();
			req.setAttribute("errorMsg", "1:1 문의 조회 중 에러 발생..!");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
		
	}

}
