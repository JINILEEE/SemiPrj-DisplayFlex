<%@page import="displayFlex.mypage.vo.PageVo"%>
<%@page import="displayFlex.event.dto.EventDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%
    	List<EventDto> eventDtoList = (List<EventDto>) request.getAttribute("eventDtoList");
    	PageVo pvo = (PageVo)request.getAttribute("pvo");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/cinema/resources/css/mypage/eventCheck.css">

</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <div class="container">
        <div class="main">
            <div class="main-first">이벤트 참여내역</div>
            <div class="main-box">
                <div class="main-box-b1">이벤트 내역</div>
                <div class="main-box-b2">이벤트 내용</div>
            </div>
            <div class="main-blank"></div>
            <div class="main-table">
			<table style="width: 1000px;">
					<thead class="thead">
						<tr>
							<th>이벤트번호</th>
							<th>이벤트제목</th>
							<th>이벤트진행여부</th>
							<th>이벤트시작일</th>
							<th>이벤트종료일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
			<% for(EventDto dto : eventDtoList){ %>
						<tr>
							<td><%= dto.getEventNo() %></td>
							<td><%= dto.getEventTitle() %></td>
							<td><%= dto.getEventProgress() %></td>
							<td><%= dto.getEventStartdate() %></td>
							<td><%= dto.getEventEnddate() %></td>
							<td><%= dto.getEventHit() %></td>
						</tr>
			<% } %>
					</tbody>
				</table>
            </div>
        </div>
		<div class="page-area">
			
			<% if(pvo.getStartPage() != 1){ %>
				<a href="/cinema/mypage/eventCheck?pno=<%= pvo.getStartPage()-1 %>">이전</a>
			<% } %>
			
			<% for(int i = pvo.getStartPage() ; i <= pvo.getEndPage(); i++){ %>
				
				<% if( i == pvo.getCurrentPage() ){ %>
					<span><%= i %></span>
				<% }else{ %>
					<a href="/cinema/mypage/eventCheck?pno=<%= i %>"><%= i %></a>
				<% } %>
				
			<% } %>
			
			<% if( pvo.getEndPage() != pvo.getMaxPage() ){ %>
				<a href="/cinema/mypage/eventCheck?pno=<%= pvo.getEndPage()+1 %>">다음</a>	
			<% } %>
		
		</div>
    </div>
    <footer></footer>

</body>
</html>

<script>
	const trArr = document.querySelectorAll(".main  table > tbody > tr");
	for(let i = 0 ; i < trArr.length; ++i){
		trArr[i].addEventListener('click' , (e) => {
			handleClick(e);
		});
	}

	function handleClick(event){
		const tr = event.currentTarget;
		const no = tr.children[0].innerText;
		location.href = 'http://localhost:9002/cinema/mypage/eventContent?eventNo=' + no + '&currentPage=<%= pvo.getCurrentPage() %>';	
	}

</script>