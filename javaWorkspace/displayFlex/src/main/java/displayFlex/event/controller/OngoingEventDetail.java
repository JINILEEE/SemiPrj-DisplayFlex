package displayFlex.event.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import displayFlex.event.dto.EventDto;
import displayFlex.event.service.EventService;

@WebServlet("/event/eventdetail")
public class OngoingEventDetail extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		EventService service = new EventService();

		try {

			// 인코딩
			req.setCharacterEncoding("UTF-8");	//필터에서 인코딩 처리 해줌

			HttpSession session = req.getSession();

			// data
			String eventNo = req.getParameter("eventNo");
			
			System.out.println("eventNo----[" + eventNo + "]");
			


			EventDto result = service.EventDetail(eventNo);

//			req.getSession().setAttribute("alertMsg", "게시글 작성 성공 !");
//			List<EventDto> eventDtoList = es.selectEventList();
//			
			// result (==view)
			req.setAttribute("eventDtoDetail", result);
//			
			req.getRequestDispatcher("/WEB-INF/views/event/ongoingeventdetail.jsp").forward(req, resp);
		} catch (Exception e) {
			System.out.println("[ERROR-B002] 게시글 작성 실패 ...");
			e.printStackTrace();
			req.setAttribute("errorMsg", "게시글 작성 실패 ...");
			// req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req,
			// resp);
		}
	}

	//관리자 진행중 이벤트 상세조회
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
	}
}
