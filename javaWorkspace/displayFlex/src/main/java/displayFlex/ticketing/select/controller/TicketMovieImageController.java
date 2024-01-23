package displayFlex.ticketing.select.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.ticketing.select.service.TicketSelectService;

@WebServlet("/ticket/select/image")
public class TicketMovieImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String movieNo = req.getParameter("movieNo");
			PrintWriter out = resp.getWriter();
			
			TicketSelectService tss = new TicketSelectService();
			String movieImgUrl = tss.getMovieImageUrl(movieNo);
//			System.out.println("url = " + movieImgUrl);
			out.write(movieImgUrl);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		 
		
	}



}
