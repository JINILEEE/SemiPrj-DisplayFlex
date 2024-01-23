package displayFlex.event.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.event.service.EventService;
import displayFlex.event.dto.EventDto;
import displayFlex.util.page.vo.PageVo;

@WebServlet("/event/eventlist")
public class EventList extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			EventService es= new EventService();
			
			
			
			
			
			
//			// data
//			int listCount = acs.selectBoardCount();		//전체 게시글 갯수
//			String currentPage_ = req.getParameter("pno");
//			if(currentPage_ == null) {
//				currentPage_ = "1";
//			}
//			int currentPage = Integer.parseInt(currentPage_);	//현재 페이지
//			int pageLimit = 5;
//			int boardLimit = 10;
//			PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);
			
			
			
			
			
			
			
			
			// service
			List<EventDto> eventDtoList = es.selectEventList();
			
			// result (==view)
			req.setAttribute("eventDtoList", eventDtoList);
			
			req.getRequestDispatcher("/WEB-INF/views/event/ongoingevent.jsp").forward(req, resp);
			
		}catch(Exception e) {
			System.out.println("[ERROR-B001]게시글 목록 조회 중 에러 발생 ...");
			e.printStackTrace();
			req.setAttribute("errorMsg", "게시글 목록 조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
		
	}

}
