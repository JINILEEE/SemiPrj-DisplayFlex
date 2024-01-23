package displayFlex.store.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import displayFlex.store.service.StoreService;
import displayFlex.store.vo.StoreVo;

@WebServlet("/admin/store/edit")
//@MultipartConfig 가 있어야 jsp에서 폼태그로 데이터 전송이 가능하다!!
@MultipartConfig(maxFileSize = 1024 * 1024/* 1MB */ * 10, maxRequestSize = 1024 * 1024 * 50)
public class ProductEditController extends HttpServlet {

	// 제품 수정 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			//data
			String no = req.getParameter("no");
			
			//service
			StoreService ss = new StoreService();
			Map<String, Object> m = ss.edit(no);
			StoreVo vo = (StoreVo) m.get("vo");
			//result
			if(vo == null) {
				throw new Exception("제품 수정용 vo가 null값이다.");
			}
	        
			req.setAttribute("vo", vo);
			req.getRequestDispatcher("/WEB-INF/views/store/adminProductEdit.jsp").forward(req, resp);

		} catch (Exception e) {
			System.out.println("제품 수정하기 화면 조회 에러 ...");
			e.printStackTrace();
			req.setAttribute("errorMsg", "제품 수정 화면 에러...");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}

	}// doGet

	// 제품 상세글 수정 로직
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			// data
			String no = req.getParameter("no");
			String category = req.getParameter("category");
			String title = req.getParameter("title");
			String price = req.getParameter("price");
			String productElement = req.getParameter("productElement");
			String shortDescription = req.getParameter("shortDescription");
			String image = req.getParameter("image");
			String delYn = req.getParameter("delYn");
			String enrollDate = req.getParameter("enrollDate");
			System.out.println(image);

			// 파일 업로드 준비
			Part f = req.getPart("f");
			

			// 파일이 업로드 됐는지 확인
			if(f !=null && f.getSize() > 0) {
				
				// 읽기 준비
				InputStream in = f.getInputStream();
				
				// 내보내기 준비
				String sep = File.separator;
				String path = req.getServletContext().getRealPath(sep + "resources" + sep + "image" + sep + "store");
				String fileName = sep + f.getSubmittedFileName();
				File target = new File(path + fileName);
				FileOutputStream out = new FileOutputStream(target);

				byte[] buf = new byte[1024];
				int size = 0;
				while ((size = in.read(buf)) != -1) {
					out.write(buf, 0, size);
				}
				
				// 정리
				in.close();
				out.close();
				
				// StoreVo객체에 넣을 이미지 파일경로 셋팅
				image = req.getContextPath() + "/resources/image/store" + fileName;
			}
			
			
			// StoreVo 값 셋팅
			StoreVo vo = new StoreVo();
			vo.setProductNo(no);
			vo.setTitle(title);
			vo.setPrice(price);
			vo.setProductElement(productElement);
			vo.setShortDescription(shortDescription);
			vo.setCategory(category);
			vo.setImage(image);
			vo.setDelYn(delYn);
			vo.setEnrollDate(enrollDate);
			
			// service
			StoreService ss = new StoreService();
			int result = ss.edit(vo);

			System.out.println(vo);

			// result == view
			if (result != 1) {
				throw new Exception("result 가 1이 아님 ,,,,");
			}
			resp.sendRedirect("/cinema/store/product?no=" + no);

		} catch (Exception e) {
			System.out.println("[ERROR-S006] 제품 수정 실패 ...");
			e.printStackTrace();
			req.setAttribute("errorMsg", "제품 등록 실패 ...");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);

		}

	}

	
	
}//class
