package displayFlex.ticketing.select.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import displayFlex.ticketing.select.service.TicketSelectService;

@WebServlet("/ticket/select/seatInfo")
public class SetSeatInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			TicketSelectService tss = new TicketSelectService();
			
			String x = req.getParameter("selectTheater");
			String[] selectTheater = x.split(",");

			String theaterNo = selectTheater[0];
			String screeningTimeNo = selectTheater[1];
			
			List<String> reservedSeatList = tss.getSeatInfo(theaterNo, screeningTimeNo);
			System.out.println("theaterNo : " + theaterNo);
			System.out.println("screeningTimeNo : " + screeningTimeNo);
			System.out.println("예약된 좌석 : " + reservedSeatList);
			Gson gson = new Gson();
			String gsonList = gson.toJson(reservedSeatList);
			PrintWriter out = resp.getWriter();
			out.write(gsonList);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}


}
