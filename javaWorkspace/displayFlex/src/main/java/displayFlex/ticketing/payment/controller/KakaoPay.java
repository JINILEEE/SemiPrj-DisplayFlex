package displayFlex.ticketing.payment.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import displayFlex.member.MemberVo;
import displayFlex.serviceCenter.inquiry.dto.UpdateDto;
import displayFlex.ticketing.payment.service.PaymentService;
import displayFlex.ticketing.payment.vo.PaymentVo;


@WebServlet("/ticket/kakaoPay")
public class KakaoPay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			HttpSession session = request.getSession();
			MemberVo memberVo = (MemberVo) session.getAttribute("loginMember");
			String memberNo = memberVo.getMemberNo();
			
			// 요청에서 body 데이터 읽어오기
			BufferedReader reader = request.getReader();
			StringBuilder buffer = new StringBuilder();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            buffer.append(line);
	        }

	        String requestBody = buffer.toString();
	        // 읽어온 데이터 처리
	        Gson gson = new Gson();
	        PaymentVo paymentVo = gson.fromJson(requestBody, PaymentVo.class);
	        paymentVo.setMemberNo(memberNo);
	        
	        PaymentService ps = new PaymentService();
	        int result = ps.setMoviePayment(paymentVo);
	        
	        if(result == 1) {
	        	out.write("success");
	        } else {
	        	out.write("fail");
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
