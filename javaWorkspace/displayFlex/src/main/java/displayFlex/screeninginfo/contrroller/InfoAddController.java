package displayFlex.screeninginfo.contrroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.movie.vo.MovieVo;
import displayFlex.screeninginfo.service.ScreenInfoService;
import displayFlex.screeninginfo.vo.ScreeingInfoVo;
import displayFlex.screeninginfo.vo.ScreeningTimeVo;

/**
 * Servlet implementation class InfoAddController
 */
@WebServlet("/admin/screen-info/add")
public class InfoAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ScreenInfoService infoService;

    public InfoAddController() {
    	infoService = new ScreenInfoService();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.service(request, response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//영화 목록
			List<MovieVo> movieList = infoService.getAllMovie();
			
			//상영관 가져오기
			List<String> theaterList = infoService.getAllTheater();
			//jsp로 보내기
			request.setAttribute("movieList", movieList);
			request.setAttribute("theater", theaterList);
			request.getRequestDispatcher("/WEB-INF/views/screening-info/add.jsp").forward(request, response);			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String[] title = request.getParameterValues("inputTitle");
			String[] theater = request.getParameterValues("inputTheater");
			String[] date = request.getParameterValues("inputDate");
			String[] startTime = request.getParameterValues("inputStartTime");
			String[] endTime = request.getParameterValues("inputEndTime");

			List<ScreeingInfoVo> infoList = new ArrayList<ScreeingInfoVo>();
			List<ScreeningTimeVo> timeList = new ArrayList<ScreeningTimeVo>();
			for(int i =0; i < title.length; i++) {
				System.out.println("title = "+title[i]);
				System.out.println("theater = "+theater[i]);
				System.out.println("date = "+date[i]);
				System.out.println("startTime = "+startTime[i]);
				System.out.println("endTime = "+endTime[i]);
				
				infoList.add(new ScreeingInfoVo(title[i], theater[i], date[i]));
				timeList.add(new ScreeningTimeVo(null, date[i] +" "+startTime[i], date[i] +" "+endTime[i]));
			}
			
			int result = infoService.addScreeningInfo(infoList, timeList);
			
			if(result != 1) {
				throw new Exception();
			}
			
			request.getSession().setAttribute("alertMsg", "상영 정보를 등록하셨습니다.");
			response.sendRedirect(request.getContextPath()+"/admin/screen-info/list?pno=1");
		} catch(Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("alertMsg", "상영 정보 등록에 실패하셨습니다.");
			response.sendRedirect(request.getContextPath()+"/admin/screen-info/add");
			e.printStackTrace();
		}
	}

}
