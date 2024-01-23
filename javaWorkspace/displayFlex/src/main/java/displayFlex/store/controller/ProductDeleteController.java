package displayFlex.store.controller;

import java.io.IOException;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import displayFlex.store.service.StoreService;
import displayFlex.store.vo.StoreVo;

@WebServlet("/admin/store/delete")
public class ProductDeleteController extends HttpServlet {

	// 제품 삭제 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			//data
			String no = req.getParameter("no");
			
			//service
			StoreService ss = new StoreService();
			int result = ss.delete(no);
			
			//result
			if(result != 1) {
				throw new Exception("제품 삭제하기 진행중 에러 ...");
			}
			
			// 제품 삭제 성공 => 리스트 페이지로 이동
			resp.sendRedirect("/cinema/store");

		} catch (Exception e) {
			System.out.println("제품 삭제하기 화면 조회 에러 ...");
			e.printStackTrace();
			req.setAttribute("errorMsg", "제품 삭제 화면 에러...");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
	
	}
	
}//class
