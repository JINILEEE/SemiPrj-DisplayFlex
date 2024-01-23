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

@WebServlet("/ticket/select/timeList")
public class ScreeningTimeListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String movieNo = req.getParameter("movieNo");
			String selectedDate = req.getParameter("selectedDate");

			TicketSelectService tss = new TicketSelectService();
			List<ScreeningDateVo> timeList = tss.getScreeningTimeList(movieNo, selectedDate);
			
			List<String> x = new ArrayList<String>(); 
			for (ScreeningDateVo vo : timeList) {
				x.add(vo.getStartTime());
			}
			
			Gson gson = new Gson();
			String gsonList = gson.toJson(timeList);
			PrintWriter out = resp.getWriter();
			out.write(gsonList);

			System.out.println("상영관,시간리스트 : " + gsonList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
