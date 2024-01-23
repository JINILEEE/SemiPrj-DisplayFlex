package displayFlex.serviceCenter.inquiry.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import displayFlex.serviceCenter.inquiry.dto.UpdateDto;
import displayFlex.serviceCenter.inquiry.service.InquiryService;
import displayFlex.serviceCenter.inquiry.vo.InquiryVo;

@WebServlet("/admin/inquiry/update")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final InquiryService inquiryService;

    public UpdateController() {
        super();
        inquiryService = new InquiryService();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		super.service(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			// 요청에서 body 데이터 읽어오기
	        StringBuilder buffer = new StringBuilder();
	        BufferedReader reader = request.getReader();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            buffer.append(line);
	        }

	        String requestBody = buffer.toString();
	        
	        // 읽어온 데이터 처리
	        Gson g = new Gson();
	        UpdateDto updateInquiry = g.fromJson(requestBody, UpdateDto.class);
			
			int result = inquiryService.updateInquiry(updateInquiry);
			
			if(result != 1) {
				throw new Exception();
			}
			
			out.write("답글을 수정했습니다");
		} catch (Exception e) {
			response.setStatus(500);
		}
	}

}
