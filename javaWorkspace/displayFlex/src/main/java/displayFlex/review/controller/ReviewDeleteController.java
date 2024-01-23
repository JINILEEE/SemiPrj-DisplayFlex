package displayFlex.review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.member.MemberVo;
import displayFlex.review.service.ReviewService;
import displayFlex.review.vo.ReviewVo;

/**
 * Servlet implementation class ReviewDeleteController
 */
@WebServlet("/movie/review/delete")
public class ReviewDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ReviewService reviewService;
       
    public ReviewDeleteController() {
        super();
        reviewService = new ReviewService();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.service(request, response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReviewVo findReview = null;
		try {
			String reviewNo = request.getParameter("reviewNo");
			MemberVo loginMember = (MemberVo)request.getSession().getAttribute("loginMember");
			findReview = reviewService.findWriterByNo(reviewNo);
			
			if(loginMember == null || (loginMember.getAdminYn().equals("N") && !loginMember.getMemberNo().equals(findReview.getMemberNo())) ) {
				throw new Exception("삭제 가능권한이 없습니다.");
			}
			
			int result = reviewService.deleteReview(reviewNo);
			if(result == 1) {
				request.getSession().setAttribute("alertMsg", "리뷰를 삭제했습니다");
				response.sendRedirect(request.getContextPath()+"/movie/detail?movieNo=" + findReview.getMovieNo());				
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("alertMsg", "리뷰를 삭제하지 못했습니다");
			response.sendRedirect(request.getContextPath()+"/movie/detail?movieNo=" + findReview.getMovieNo());
		}
		
		
	}

}
