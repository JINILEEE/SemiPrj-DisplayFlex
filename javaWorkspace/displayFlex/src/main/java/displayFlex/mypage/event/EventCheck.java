package displayFlex.mypage.event;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import displayFlex.event.dto.EventDto;
import displayFlex.member.MemberVo;
import displayFlex.mypage.MypageService;
import displayFlex.mypage.vo.PageVo;

@WebServlet("/mypage/eventCheck")	
public class EventCheck extends HttpServlet {
	
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
			
			
			HttpSession session = req.getSession();
			MemberVo memberVo = (MemberVo) session.getAttribute("loginMember");
			String memberNo = memberVo.getMemberNo();
			
			//service
			List<EventDto> eventDtoList = ms.selectEventList(pvo, memberNo);
			//result
			req.setAttribute("eventDtoList", eventDtoList);
			req.setAttribute("pvo", pvo);
			
			req.getRequestDispatcher("/WEB-INF/views/mypage/eventCheck.jsp").forward(req, resp);
		}catch(Exception e) {
			System.out.println("[ERROR-B001] 이벤트 게시글 목록 조회 중 에러 발생...!");
			e.printStackTrace();
			req.setAttribute("errorMsg", "이벤트 목록 조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
	}
}
