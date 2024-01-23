<%@page import="java.util.Map"%>
<%@page import="displayFlex.event.dto.EventDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="/cinema/resources/css/event/ongoingeventdetail.css">

<%
    EventDto eventDtoDetail = (EventDto) request.getAttribute("eventDtoDetail");
    Map<String, String> searchMap = (Map<String, String>)request.getAttribute("searchMap"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>진행중 이벤트 상세조회</title>
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
        <div id="event_title"> &nbsp;&nbsp;진행중 이벤트
            <div id="event_title2"><a href="/cinema/event/eventlist" class="ongoing">진행중 이벤트</a> | <a href="/cinema/event/pastevent" class="past">지난 이벤트</a></div>
        </div>
        <hr id="jul2">

        <div id="title_name"> &nbsp;&nbsp;&nbsp; [ <%=eventDtoDetail.getEventTitle() %> ] </div>

        <div id="main_content">

            <span style="font-size: large;"><%=eventDtoDetail.getEventContents() %> </span>
        </div>
        

        
                
        






    </div>    
</body>
</html>