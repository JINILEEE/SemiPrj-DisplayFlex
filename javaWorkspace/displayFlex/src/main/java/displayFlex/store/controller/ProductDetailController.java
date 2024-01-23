package displayFlex.store.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.store.service.StoreService;
import displayFlex.store.vo.StoreVo;

@WebServlet("/store/product")
public class ProductDetailController extends HttpServlet{

	//제품 디테일 화면 보여주기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			//data
			String no = req.getParameter("no");
			
			//service
			StoreService ss = new StoreService();
			Map<String, Object> map = ss.selectMenuListByNo(no);
			StoreVo vo = (StoreVo) map.get("vo");
			
			//result == view
			req.setAttribute("vo", vo);
			req.getRequestDispatcher("/WEB-INF/views/store/productDetail.jsp").forward(req, resp);
			
		}catch(Exception e) {
			System.out.println("[ERROR-S003] 스토어 상세조회 중 에러 발생 ...");
			e.printStackTrace();
			req.setAttribute("errorMsg" , "스토어 상세조회 실패");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
		
	}
	
	//제품 디테일 관리자용
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	
	}
	
}//class
