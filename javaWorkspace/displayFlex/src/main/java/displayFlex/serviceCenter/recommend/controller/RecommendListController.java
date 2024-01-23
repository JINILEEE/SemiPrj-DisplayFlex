package displayFlex.serviceCenter.recommend.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.serviceCenter.notice.service.NoticeService;
import displayFlex.serviceCenter.notice.vo.NoticeVo;
import displayFlex.serviceCenter.recommend.service.RecommendService;
import displayFlex.serviceCenter.recommend.vo.RecommendVo;
import displayFlex.util.page.vo.PageVo;

@WebServlet("/serviceCenter/recommendList")
public class RecommendListController extends HttpServlet {
	
	//상영요청 목록 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			RecommendService rs = new RecommendService();
			
			// data
			int listCount = rs.selectRecommendCount();			//전체 게시글 갯수
			String currentPage_ = req.getParameter("pno");
			if(currentPage_ == null) {
				currentPage_ = "1";
			}
			int currentPage = Integer.parseInt(currentPage_);	//현재 페이지
			int pageLimit = 2;
			int NoticeLimit = 7;
			PageVo pvo = new PageVo(listCount, currentPage, pageLimit, NoticeLimit);
			
			// service
			List<RecommendVo> recommendVoList = rs.selectRecommendList(pvo);
			
			// result (==view)
			req.setAttribute("recommendVoList", recommendVoList);
			req.setAttribute("pvo" , pvo);
			req.getRequestDispatcher("/WEB-INF/views/serviceCenter/recommend/recommendList.jsp").forward(req, resp);
			
		}catch(Exception e) {
			System.out.println("[ERROR-B001] 상영요청 목록 조회 중 에러 발생");
			e.printStackTrace();
			req.setAttribute("errorMsg", "상영요청 목록 조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
		
	}
	
	//상영요청 로직
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
