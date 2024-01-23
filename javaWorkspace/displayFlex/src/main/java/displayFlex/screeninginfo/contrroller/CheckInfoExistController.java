package displayFlex.screeninginfo.contrroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.screeninginfo.dto.ScreenInfoDto;
import displayFlex.screeninginfo.service.ScreenInfoService;


@WebServlet("/admin/screen-info/check")
public class CheckInfoExistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ScreenInfoService infoService;

    public CheckInfoExistController() {
        super();
        infoService = new ScreenInfoService();
        // TODO Auto-generated constructor stub
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.service(request, response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		try {
			String title = request.getParameter("title").substring(request.getParameter("title").indexOf("|")+1);
			String theater = request.getParameter("theater");
			String date = request.getParameter("dateInput");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			ScreenInfoDto screenInfoDto = new ScreenInfoDto(title, theater, date, startTime, endTime);
			System.out.println(screenInfoDto);
			int count = infoService.isExistScreeningInfo(screenInfoDto);
			System.out.println("count = " + count);
			if(count != 0) {
				throw new Exception();
			}
			out.write("상영 정보를 입력하시겠습니까?");
		} catch(Exception e) {
			e.printStackTrace();
			response.setStatus(500);
		}
	}

}
