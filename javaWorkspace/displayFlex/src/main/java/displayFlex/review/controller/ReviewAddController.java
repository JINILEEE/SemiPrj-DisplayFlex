package displayFlex.review.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.member.MemberVo;
import displayFlex.review.service.ReviewService;
import displayFlex.review.vo.ReviewVo;


@WebServlet("/movie/review/add")
public class ReviewAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final ReviewService reviewService;

    public ReviewAddController() {
        super();
        reviewService = new ReviewService();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.service(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String movieNo = request.getParameter("movieNo");
		try {
			//회원만 이용가능하게 하기
			MemberVo sessionMember = (MemberVo)(request.getSession().getAttribute("loginMember"));
			if(sessionMember.getMemberNo() == null) {
				throw new Exception("회원만 이용 가능합니다");
			}
			
			String writerNo = sessionMember.getMemberNo();
			String ratingValue = request.getParameter("rating-value");
			String reviewContent = request.getParameter("review-content");

			int result = reviewService.addReview(new ReviewVo(writerNo, movieNo, reviewContent, ratingValue));
			
			if(result == 1) {
				request.getSession().setAttribute("alertMsg", "리뷰를 등록했습니다");
				response.sendRedirect(request.getContextPath()+"/movie/detail?movieNo="+movieNo);
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("alertMsg", "리뷰를 등록하지 못했습니다");
			response.sendRedirect(request.getContextPath()+"/movie/detail?movieNo="+movieNo);
		}
	}

}
