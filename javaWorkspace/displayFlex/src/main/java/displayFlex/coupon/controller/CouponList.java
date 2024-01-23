package displayFlex.coupon.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.admincoupon.service.AdminCouponService;
import displayFlex.coupon.vo.CouponVo;
import displayFlex.event.dto.EventDto;
import displayFlex.event.service.EventService;

@WebServlet("/coupon/couponlist")
public class CouponList extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			AdminCouponService acs = new AdminCouponService();

//				// data
//				int listCount = acs.selectBoardCount();		//전체 게시글 갯수
//				String currentPage_ = req.getParameter("pno");
//				if(currentPage_ == null) {
//					currentPage_ = "1";
//				}
//				int currentPage = Integer.parseInt(currentPage_);	//현재 페이지
//				int pageLimit = 5;
//				int boardLimit = 10;
//				PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

			// service
			List<CouponVo> couponVoList = acs.selectCouponList();

			// result (==view)
			req.setAttribute("couponVoList", couponVoList);

			req.getRequestDispatcher("/WEB-INF/views/coupon/couponlist.jsp").forward(req, resp);

		} catch (Exception e) {
			System.out.println("[ERROR-B001]게시글 목록 조회 중 에러 발생 ...");
			e.printStackTrace();
			req.setAttribute("errorMsg", "게시글 목록 조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}

	}
}
