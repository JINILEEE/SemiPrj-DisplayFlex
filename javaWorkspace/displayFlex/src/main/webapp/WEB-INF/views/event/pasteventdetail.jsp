<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="/cinema/resources/css/event/pasteventdetail.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지난 이벤트 상세조회</title>
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
        <div id="event_title"> &nbsp;&nbsp;&nbsp;&nbsp;지난 이벤트
            <div id="event_title2"><a href="/cinema/event/event" class="ongoing">진행중 이벤트</a> | <a href="/cinema/event/pastevent" class="past">지난 이벤트</a></div>
        </div>
        <hr id="jul2">

        <div id="title_name"> &nbsp;&nbsp;&nbsp; [지난 이벤트] ex)종료된 이벤트 어ㅉ고어쩌고 </div>

        <div id="main_content">

            <p>종료된 이벤트 게시글 내용</p>
        </div>
        

        
                
        






    </div>    

</body>
</html>