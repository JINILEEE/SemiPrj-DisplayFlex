package displayFlex.coupon.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import displayFlex.admincoupon.dao.AdminCouponDao;
import displayFlex.admincoupon.service.AdminCouponService;
import displayFlex.coupon.vo.CouponVo;

@WebServlet("/admin/coupon/add")
public class CouponAddController extends HttpServlet {
	
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		
		AdminCouponService service = new AdminCouponService();
		
		try {
					
			//인코딩
			req.setCharacterEncoding("UTF-8");	//필터에서 인코딩 처리 해줌
			
			HttpSession session = req.getSession();
			
			// data
			String type = req.getParameter("s_type");
			String discount = req.getParameter("s_discount");
			String name = req.getParameter("i_name");
			String CreationDate = req.getParameter("i_CreationDate");
			String ValidPeriod = req.getParameter("i_ValidPeriod");
			String info = req.getParameter("t_info");
			
			System.out.println("s_type----["+type+"]");
			System.out.println("discount----["+discount+"]");
			System.out.println("name----["+name+"]");
			System.out.println("CreationDate----["+CreationDate+"]");
			System.out.println("ValidPeriod----["+ValidPeriod+"]");
			System.out.println("info----["+info+"]");
	
			CouponVo vo = new CouponVo();
			
			vo.setCouponType(type);
			vo.setCouponDiscount(discount);
			vo.setCouponName(name);
			vo.setCouponCreationDate(CreationDate);
			vo.setCouponValidePeriod(ValidPeriod);
			vo.setCouponInfo(info);
			
			int result = service.couponCreate(vo);
		
			
			
//			req.getSession().setAttribute("alertMsg", "게시글 작성 성공 !");
			
			resp.sendRedirect("/cinema/coupon/couponlist");
			
		}catch(Exception e) {
			System.out.println("[ERROR-B002] 게시글 작성 실패 ...");
			e.printStackTrace();
			req.setAttribute("errorMsg", "게시글 작성 실패 ...");
			//req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
		
	}	
}
