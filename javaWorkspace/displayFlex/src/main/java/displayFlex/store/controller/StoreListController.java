package displayFlex.store.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import displayFlex.member.MemberVo;
import displayFlex.store.service.StoreService;
import displayFlex.store.vo.StoreVo;

@WebServlet("/store")
public class StoreListController extends HttpServlet{

	//스토어 리스트 화면 보여주기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			//service
			StoreService ss = new StoreService();
			
			List<StoreVo> storeVoList = null;
			List<StoreVo> storeVoList2 = ss.selectMenuList(); // 위쪽 메뉴 리스트들 출력용
			
			//data
			MemberVo loginMember = (MemberVo)req.getSession().getAttribute("loginMember");

			if(loginMember == null || loginMember.getAdminYn().equals("N")) {
				
				//storeVoList = 사용자용 쿼리 실행하러 이동하기(아래 메뉴 리스트 출력용)
				storeVoList = ss.selectStoreList();
				req.setAttribute("storeVoList", storeVoList);
				
			} else {
				
				//storeVoList = 관리자용 쿼리 실행하러 이동하기(아래 메뉴 리스트 출력용)
				storeVoList = ss.selectStoreListAdmin();
				req.setAttribute("storeVoList", storeVoList);
				
			}
			req.setAttribute("storeVoList2", storeVoList2);	
			req.getRequestDispatcher("/WEB-INF/views/store/storeList.jsp").forward(req, resp);
			
		}catch(Exception e) {
			System.out.println("[ERROR-S001] 스토어 목록 조회중 에러발생...");
			e.printStackTrace();
			req.setAttribute("errorMsg" , "스토어 목록조회 실패");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
		
	
	}//doGet
	
	
}//class
