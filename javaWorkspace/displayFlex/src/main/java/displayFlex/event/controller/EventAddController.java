package displayFlex.event.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import displayFlex.event.dto.EventDto;
import displayFlex.event.service.EventService;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 10 * 5)

@WebServlet("/admin/event/add")
public class EventAddController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		EventService service = new EventService();

		try {

			// 인코딩
			req.setCharacterEncoding("UTF-8"); // 필터에서 인코딩 처리 해줌

			HttpSession session = req.getSession();

			// data
			String title = req.getParameter("i_title");
			String sdate = req.getParameter("i_sdate");
			String edate = req.getParameter("i_edate");
			String eventTypeNo = req.getParameter("s_eventTypeNo");
			String contents = req.getParameter("t_contents");
//			String AffDis = req.getParameter("r_AffDis");
//			String PreMee = req.getParameter("r_PreMee");
//			String StaGre = req.getParameter("r_StaGre");

			System.out.println("i_title----[" + title + "]");
			System.out.println("i_sdate----[" + sdate + "]");
			System.out.println("i_edate----[" + edate + "]");
			System.out.println("s_eventTypeNo----[" + eventTypeNo + "]");

			System.out.println("t_contents----[" + contents + "]");
//			System.out.println("r_AffDis----[" + AffDis + "]");
//			System.out.println("r_PreMee----[" + PreMee + "]");
//			System.out.println("r_StaGre----[" + StaGre + "]");

			EventDto dto = new EventDto();

			dto.setEventTitle(title);
			dto.setEventStartdate(edate);
			dto.setEventEnddate(edate);

			dto.setEventtypeNo(eventTypeNo);

			dto.setEventContents(contents);
//			dto.setEventtypeNo(AffDis);
//			dto.setEventtypeNo(PreMee);
//			dto.setEventtypeNo(StaGre);

			int result = service.eventCreate(dto);

//			req.getSession().setAttribute("alertMsg", "게시글 작성 성공 !");

			resp.sendRedirect("/cinema/event/eventlist");

		} catch (Exception e) {
			System.out.println("[ERROR-B002] 게시글 작성 실패 ...");
			e.printStackTrace();
			req.setAttribute("errorMsg", "게시글 작성 실패 ...");
			// req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req,
			// resp);
		}
	}
}
