package displayFlex.ticketing.select.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.ticketing.select.service.TicketSelectService;
import displayFlex.ticketing.select.vo.ScreeningDateVo;
import displayFlex.ticketing.select.vo.SelectMovieVo;

@WebServlet("/ticket/select")
public class TicketSelectController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		try {

			TicketSelectService tss = new TicketSelectService();
			List<SelectMovieVo> movieList = tss.getMovieList();
//			List<ScreeningVo> screeningList = tss.getScreeningList();
//			
//			req.setAttribute("screeningList", screeningList);
			req.setAttribute("movieList", movieList);			
			req.getRequestDispatcher("/WEB-INF/views/ticketing/ticketSelect.jsp").forward(req, resp);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
