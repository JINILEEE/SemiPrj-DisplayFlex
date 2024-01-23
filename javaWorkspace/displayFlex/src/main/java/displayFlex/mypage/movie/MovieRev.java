package displayFlex.mypage.movie;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import displayFlex.event.dto.EventDto;
import displayFlex.member.MemberVo;
import displayFlex.mypage.MoviePaymentVo;
import displayFlex.mypage.MypageService;
import displayFlex.mypage.vo.PageVo;

@WebServlet("/mypage/movieRev")
public class MovieRev extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			MypageService ms = new MypageService();
			
			//data
			int listCount = ms.selectMypageCount();  //전체 게시글 갯수
			String currentPage_ = req.getParameter("pno");
			if(currentPage_ == null) {
				currentPage_ = "1";
			}
			int currentPage = Integer.parseInt(currentPage_);
			int pageLimit = 5;
			int boardLimit = 10;
			PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);
			
			
            // 조회 기간 설정
            String dateFilter = req.getParameter("dateFilter");
            if (dateFilter != null) {
                switch (dateFilter) {
                    case "7days":
                        pvo.setStartDateFilter(7);
                        break;
                    case "15days":
                        pvo.setStartDateFilter(15);
                        break;
                    case "30days":
                        pvo.setStartDateFilter(30);
                        break;
                    default:
                        break;
                }
            }
            
            HttpSession session = req.getSession();
			MemberVo memberVo = (MemberVo) session.getAttribute("loginMember");
			String memberNo = memberVo.getMemberNo();
			
			//service
			List<MoviePaymentVo> moviePaymentVoList = ms.selectMoviePaymentList(pvo, memberNo);
			
			//result
			req.setAttribute("moviePaymentVoList", moviePaymentVoList);
			req.setAttribute("pvo", pvo);
			
			req.getRequestDispatcher("/WEB-INF/views/mypage/movieRev.jsp").forward(req, resp);
		}catch(Exception e) {
			System.out.println("[ERROR-M001] 영화 예매 목록 조회 중 에러 발생...!");
			e.printStackTrace();
			req.setAttribute("errorMsg", "영화예매 목록 조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
	}
}
