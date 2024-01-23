<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="displayFlex.mypage.vo.PageVo" %>
<%@ page import="displayFlex.mypage.MoviePaymentVo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
        
 <%
    	List<MoviePaymentVo> moviePaymentVoList = (List<MoviePaymentVo>) request.getAttribute("moviePaymentVoList");
    	PageVo pvo = (PageVo)request.getAttribute("pvo");
 %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/cinema/resources/css/mypage/movieRev.css">

</head>
<body>

<%
    // 기존 코드 유지

    // 조회 일자 선택을 통한 필터링
    String startDate = request.getParameter("startDate");
    String endDate = request.getParameter("endDate");

    // 서버로 전달할 날짜 목록을 담을 리스트
    List<String> dateList = new ArrayList<>();

    // 조회 일자가 선택된 경우에만 적용
    if (startDate != null && endDate != null) {
        // 기간을 하루씩 증가시키면서 조회
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse(startDate);
        Date end = sdf.parse(endDate);

        // 각 날짜에 대해 조회 수행
        for (Date date = start; date.compareTo(end) <= 0; date = new Date(date.getTime() + 24 * 60 * 60 * 1000)) {
            // date를 String 형태로 변환하여 리스트에 추가
            dateList.add(sdf.format(date));
        }
    }

    // dateList를 JSP 페이지로 전달
    request.setAttribute("dateList", dateList);
%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

	<div class="container">
		<div class="main">
			<div class="main-first">□ 결제/예매내역 목록</div>
			<div class="main-second">예매한 영화티켓 내역입니다.</div>
			<div class="main-third">
				<div class="main-third-t1">조회기간 선택</div>
				<div class="main-third-t2">
				        <input type="hidden" name="pno" value="<%= pvo.getCurrentPage() %>">
				        <button type="submit" name="dateFilter" value="7days">7일</button>
				        <button type="submit" name="dateFilter" value="15days">15일</button>
				        <button type="submit" name="dateFilter" value="30days">30일</button>
				</div>
				<div class="main-third-t3">
				<div>예매일자</div>
				 <form method="get" action="/cinema/mypage/movieRev">
                    <input type="hidden" name="pno" value="<%= pvo.getCurrentPage() %>">
                    <input type='date' name='startDate' id='startDate'/>
                    <div>~</div>
                    <input type='date' name='endDate' id='endDate'/>
                    <button type="submit">조회</button>
                </form>
				</div>
			</div>
			<div>
			<table>
				<thead>
					<tr>
						<td>결제번호</td>
						<td>회원번호</td>
						<td>영화이름</td>
						<td>결제일시</td>
						<td>결제금액</td>
					</tr>
				 <tbody>
				 <% for(MoviePaymentVo vo : moviePaymentVoList){ %>
					<tr>
						<td><%= vo.getPaymentsNo() %></td>
						<td><%= vo.getMemberNo() %></td>
						<td><%= vo.getMovieName() %></td>
						<td><%= vo.getPaymentDate() %></td>
						<td><%= vo.getPrice() %></td>
					</tr>
				<% } %>	
				 </tbody>
				</thead>
			</table>
			</div>
			<div class="page">
			
			<div class="page-area">
			
			<% if(pvo.getStartPage() != 1){ %>
				<a href="/cinema/mypage/movieRev?pno=<%= pvo.getStartPage()-1 %>">이전</a>
			<% } %>
			
			<% for(int i = pvo.getStartPage() ; i <= pvo.getEndPage(); i++){ %>
				
				<% if( i == pvo.getCurrentPage() ){ %>
					<span><%= i %></span>
				<% }else{ %>
					<a href="/cinema/mypage/movieRev?pno=<%= i %>"><%= i %></a>
				<% } %>
				
			<% } %>
			
			<% if( pvo.getEndPage() != pvo.getMaxPage() ){ %>
				<a href="/cinema/mypage/movieRev?pno=<%= pvo.getEndPage()+1 %>">다음</a>	
			<% } %>
		
		</div>
			
			</div>
		</div>
	</div>	

</body>
</html>

<script>

  document.getElementById('currentDate').value = new Date().toISOString().substring(0, 10);;
  
  const trArr = document.querySelectorAll("table > tbody > tr");
	for(let i = 0 ; i < trArr.length; ++i){
		trArr[i].addEventListener('click' , (e) => {
			handleClick(e);
		});
	}

	function handleClick(event){
		const tr = event.currentTarget;
		const no = tr.children[0].innerText;
		location.href = 'http://localhost:9002/cinema/mypage/movieRev?paymentsNo=' + no + '&currentPage=<%= pvo.getCurrentPage() %>';	
	}
  
  </script>
