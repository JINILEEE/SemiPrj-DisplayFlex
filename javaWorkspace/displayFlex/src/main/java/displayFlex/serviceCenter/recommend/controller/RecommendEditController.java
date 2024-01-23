package displayFlex.serviceCenter.recommend.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.serviceCenter.notice.service.NoticeService;
import displayFlex.serviceCenter.notice.vo.NoticeVo;
import displayFlex.serviceCenter.recommend.service.RecommendService;
import displayFlex.serviceCenter.recommend.vo.RecommendVo;

@WebServlet("/serviceCenter/recommendEdit")
public class RecommendEditController extends HttpServlet {
	
	//상영요청 수정 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			//data
			String recommendMvNo = req.getParameter("recommendMvNo");
			
			//service
			RecommendService rs = new RecommendService();
			RecommendVo vo = rs.edit(recommendMvNo);
			
			//result
			if(vo == null) {
				System.out.println(vo);
//				throw new Exception();
			}
			
			req.setAttribute("vo", vo);
			req.getRequestDispatcher("/WEB-INF/views/serviceCenter/recommend/recommendEdit.jsp").forward(req, resp);
		}catch(Exception e) {
			System.out.println("상영요청 게시글 수정하기 화면 조회 에러");
			e.printStackTrace();
			req.setAttribute("errorMsg", "상영요청 게시글 수정 화면 조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
	}
		
		//상영요청 게시글 수정 로직
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			try {
				
				//data
				String year = req.getParameter("year");
				String title = req.getParameter("title");
				String content = req.getParameter("content");
				String recommendMvNo = req.getParameter("recommendMvNo");
				
				RecommendVo vo = new RecommendVo();
				vo.setYear(year);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setRecommendMvNo(recommendMvNo);
				
				//service
				RecommendService rs = new RecommendService();
				int result = rs.edit(vo);
				
				//result
				if(result != 1) {
					throw new Exception();
				}
				req.setAttribute("alertMsg", "상영요청글이 수정되었습니다.");
				resp.sendRedirect("/cinema/serviceCenter/recommendDetail?recommendMvNo=" + recommendMvNo);
				
			}catch(Exception e) {
				e.printStackTrace();
//				req.setAttribute("errorMsg", "상영요청글 수정 실패");
//				req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
				req.setAttribute("alertMsg", "상영요청글 수정 실패");
				resp.sendRedirect("/cinema/serviceCenter/recommendEdit");
				return;
			}
		}

}