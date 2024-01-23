package displayFlex.ticketing.select.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import displayFlex.ticketing.select.service.TicketSelectService;
import displayFlex.ticketing.select.vo.ScreeningDateVo;

@WebServlet("/ticket/select/dateList")
public class ScreeningDateListController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String movieNo = req.getParameter("movieNo");
			TicketSelectService tss = new TicketSelectService();
			
			List<ScreeningDateVo> screeningList = tss.getScreeningList(movieNo);
				
			List<String> dateList = new ArrayList<String>(); 
			for (ScreeningDateVo vo : screeningList) {
				dateList.add(vo.getStartTime());
			}

			Gson gson = new Gson();
			String gsonList = gson.toJson(screeningList);
			// 
			PrintWriter out = resp.getWriter();
//			req.setAttribute("screeningList", screeningList);
//			req.getSession().setAttribute();
			out.write(gsonList);
			
//			req.getRequestDispatcher("/WEB-INF/views/ticketing/ticketSelect.jsp").include(req, resp);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
}
