<%@page import="displayFlex.event.dto.EventDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
		EventDto dto = (EventDto)request.getAttribute("dto");
		String currentPage = (String) request.getAttribute("currentPage");
		if(currentPage == null){
			currentPage = "1";
		}
	%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/cinema/resources/css/mypage/eventContent.css">

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
                <div class="main-table-header">이벤트 번호</div>
                <div class="main-table-header">이벤트 제목</div>
                <div class="main-table-header">이벤트 시작일</div>
                <div class="main-table-header">이벤트 종료일</div>
                <div class="main-table-header">조회수</div>
                <div class="main-table-body"><%= dto.getEventNo() %></div>
                <div class="main-table-body"><%= dto.getEventTitle() %></div>
                <div class="main-table-body"><%= dto.getEventStartdate() %></div>
                <div class="main-table-body"><%= dto.getEventEnddate() %></div>
                <div class="main-table-body"><%= dto.getEventHit() %></div>
            </div>
            <div class="main-content"><%= dto.getEventContents() %></div>
        </div>
        <a href="/cinema/mypage/eventCheck?pno=<%=currentPage %>">목록으로</a>
     </div>
     
     	
    <footer></footer>

</body>
</html>