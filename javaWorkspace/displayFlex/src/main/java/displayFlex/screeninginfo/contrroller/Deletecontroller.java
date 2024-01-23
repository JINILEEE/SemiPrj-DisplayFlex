package displayFlex.screeninginfo.contrroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.screeninginfo.service.ScreenInfoService;


@WebServlet("/admin/screen-info/delete")
public class Deletecontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final ScreenInfoService infoService;

    public Deletecontroller() {
        super();
        infoService = new ScreenInfoService();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.service(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String infoNo = request.getParameter("no");
			
			int result = infoService.deleteByNo(infoNo);
			
			if(result != 1) {
				throw new Exception();
			}

			request.getSession().setAttribute("alertMsg", "상영 정보를 삭제했습니다.");

			response.sendRedirect(request.getContextPath()+"/admin/screen-info/list?pno=1");				
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("alertMsg", "상영 정보를 삭제하지 못했습니다.");
			response.sendRedirect(request.getContextPath()+"/admin/screen-info/list?pno=1");
		}
		
	}

}
