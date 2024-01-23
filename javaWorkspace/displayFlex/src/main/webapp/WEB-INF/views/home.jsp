<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String x = (String) session.getAttribute("alertMsg");
	session.removeAttribute("alertMsg");
%>

<link rel="stylesheet" href="/cinema/resources/css/home.css">
<script src="/cinema/resources/js/home.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DisplayFlex</title>

    <script>
	<% if( x != null ){ %>
	    alert('<%= x %>');
	<% } %>
	</script>
  
</head>
<body>

	<%@ include file="/WEB-INF/views/common/header.jsp" %>
    <div id="wrap">

        <div class="poster">
            <img src="/cinema/resources/image/home/ponyo.jpeg" alt="포뇨사진">
        </div>
	    <div class="info">
	    	<div>다시 보고 싶으신 영화가 있으신가요?</div>
	    	<div><span><a href="/cinema/serviceCenter/recommendList">여기를 클릭</a>해서 다시 보고 싶은 영화를 요청하세요!</span></div>
	    </div>
	    <div class="event">
	    	<div>
	    		<div id="eventLetter"><b>Event</b></div>
	    		<div><a href="/cinema/event/eventlist">더보기►</a></div>
	    	</div>
	    	<div>
	    		<div><img width="400" height="300" src="/cinema/resources/image/event/NamSanPreMee1.jpg" alt="NamSanPreMee1.jpg"></div>
	    		<div><img width="400" height="300" src="/cinema/resources/image/event/BookAffDis.jpg" alt="BookAffDis.jpg"></div>
	    		<div><img width="400" height="300" src="/cinema/resources/image/event/HarryPreMee.jpg" alt="HarryPreMee.jpg"></div>
	    	</div>
	    </div>

    </div>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
    
</body>
</html>