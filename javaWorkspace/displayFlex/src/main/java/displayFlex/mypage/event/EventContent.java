package displayFlex.mypage.event;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.event.dto.EventDto;
import displayFlex.mypage.MypageService;

@WebServlet("/mypage/eventContent")
public class EventContent extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			//data
			String eventNo = req.getParameter("eventNo");
			
			//service
			MypageService ms = new MypageService();
			EventDto dto = ms.selectEventByNo(eventNo);
			
			req.setAttribute("dto", dto);
			req.setAttribute("currentPage" ,req.getParameter("currentPage"));
			req.getRequestDispatcher("/WEB-INF/views/mypage/eventContent.jsp").forward(req, resp);
			
		}catch(Exception e) {
			System.out.println("[ERROR-B003] 이벤트 게시글 상세조회 중 에러발생...!");
			e.printStackTrace();
			req.setAttribute("errorMsg", "게시글 상세조회 실패...!");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
