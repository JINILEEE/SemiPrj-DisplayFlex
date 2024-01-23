<%@page import="java.util.Map"%>
<%@page import="displayFlex.event.dto.EventDto"%>
<%@page import="java.util.List"%>



<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/cinema/resources/css/event/ongoingevent.css">
 <%
    	List<EventDto> eventDtoList = (List<EventDto>) request.getAttribute("eventDtoList");
    	Map<String, String> searchMap = (Map<String, String>)request.getAttribute("searchMap");
    %>


<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>진행중 이벤트</title>
    <style>
        a{text-decoration: none; color: black;}
    a:visited { text-decoration: none; }
    a:hover { text-decoration: none; }
    a:focus { text-decoration: none; }
    a:hover, a:active { text-decoration: none; }
    </style>
    
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>

    <div class="bw"> 
        <hr id="jul1">      
        <div><div id="event_title"> &nbsp;&nbsp;진행중 이벤트  
            <div id="event_title2"><a href="/cinema/event/eventlist" class="event" style="text-decoration: underline;">진행중 이벤트</a> | <a href="/cinema/event/pastevent" class="past">지난 이벤트</a></div>
        </div>
         
            <div><div id="enroll" style="float: left;">
                <c:if test="${not empty loginMember && loginMember.adminYn eq 'Y'}">
                <button onclick="location.href='/cinema/admin/event/adminevent'">등록</button>
            </c:if>
            </div>
            <div id="delete"><c:if test="${not empty loginMember && loginMember.adminYn eq 'Y'}"><button>삭제</button></c:if></div>
				
        </div>
        </div>
        <hr id="jul2">
        <!-- <form action="/cinema/event/eventlist" method="get">
            <!-- <select name="searchType">
                <option value="title">제목</option>
                <option value="content">내용</option>
            </select> -->
            <!-- &nbsp;&nbsp;<input type="text" name="searchValue" placeholder="조회하기 버튼을 눌러주세요.">
            <input type="submit" value="조회하기">
        </form> -->
        <div id="main_top">
            <div id="main_title">
                <div style="color: white; font-size:xx-large"><a href="/cinema/event/eventlist" style="color: white; text-decoration: underline;">전체</a></div>
                <div style="color: white; font-size:xx-large"><a href="/cinema/event/eventAffDis" style="color: white; text-decoration: none;">제휴/할인</a></div>
                <div style="color: white; font-size:xx-large"><a href="/cinema/event/eventPreMee" style="color: white; text-decoration: none;">시사회/간담회</a></div>
                <div style="color: white; font-size:xx-large"><a href="/cinema/event/eventStaGre" style="color: white; text-decoration: none;">무대인사</a></div>
            </div>
        </div>
        <div id="main_content">
            
            <% if(eventDtoList!= null) { %>
                <%  for(EventDto dto : eventDtoList){ %>
                    <div class="content-section">
                        <DIV> 
                            <h2> 
                                <div style="text-align: center; font-weight: 100;" >[게시글 이미지]</div>
                                <br>
                            <div style="text-align: center;">
                                <a href="/cinema/event/eventdetail?eventNo=<%= dto.getEventNo()%>"> 제목 : <%=dto.getEventTitle() %></a>
                            </div>
                            </h2>
                        </DIV>
                    </div>
                        <% }} %>
        
             <!-- <div class="content-section">
             


                <DIV> <h2> <% 
                    if(eventDtoList!= null){
                    for(EventDto dto : eventDtoList){
                        if(dto.getEventtypeNo().equals("2")) {%>
                        <div><%=dto.getEventTitle() %></div>
                       
                        <% }} }%></h2>
                </DIV>
            </div>

            <div class="content-section">
               

                
                <DIV> <h2> <% 
                    if(eventDtoList!= null){
                    for(EventDto dto : eventDtoList){
                        if(dto.getEventtypeNo().equals("3")) {%>
                        <div><%=dto.getEventTitle() %></div>
                       
                        <% }} }%></h2>
                </DIV>
            </div>
        
            <div class="content-section">

               


                <DIV> <h2> <% 
                    if(eventDtoList!= null){
                    for(EventDto dto : eventDtoList){
                        if(dto.getEventtypeNo().equals("4")) {%>
                        <div><%=dto.getEventTitle() %></div>
                       
                        <% }} }%></h2>
                </DIV>
            </div>  -->
        
        


        </div>
    </div>
    
</body>
</html>
