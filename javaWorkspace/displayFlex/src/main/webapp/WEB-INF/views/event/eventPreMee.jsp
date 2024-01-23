<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/cinema/resources/css/event/ongoingevent.css">
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
        <div id="event_title"> &nbsp;&nbsp;&nbsp;&nbsp;진행중 이벤트  
            <div id="event_title2"><a href="/cinema/event/eventlist" class="event" style="text-decoration: underline;">진행중 이벤트</a> | <a href="/cinema/event/pastevent" class="past">지난 이벤트</a></div>
        </div>
        <hr id="jul2">
        <div id="main_top">
            <div id="main_title">
                <div style="color: white; font-size:xx-large"><a href="/cinema/event/eventlist" style="color: white; text-decoration: none;">전체</a></div>
                <div style="color: white; font-size:xx-large"><a href="/cinema/event/eventAffDis" style="color: white; text-decoration: none;">제휴/할인</a></div>
                <div style="color: white; font-size:xx-large"><a href="/cinema/event/eventPreMee" style="color: white; text-decoration: underline;">시사회/간담회</a></div>
                <div style="color: white; font-size:xx-large"><a href="/cinema/event/eventStaGre" style="color: white; text-decoration: none;">무대인사</a></div>
            </div>
        </div>
        <div id="main_content">
            <a style=text-decoration:none; href="/cinema/event/eventdetail"><div class="content_area">
                <h1>이벤트 게시글1</h1>
                <div>
                    <div class="content_title" >이벤트 게시글 제목1</div>
                    
                </div>
            </div>
            </a>

            <div class="content_area">
                <h1>이벤트 게시글 사진2</h1>
                <div>
                    <div class="content_title">이벤트 게시글 제목2</div>
                    
                </div>
            </div>

            <div class="content_area">
                <h1>이벤트 게시글 사진3</h1>
                <div>
                    <div class="content_title">이벤트 게시글 제목3</div>
                    
                </div>
            </div>

            <div class="content_area">
                <h1>이벤트 게시글 사진4</h1>
                <div>
                    <div class="content_title">이벤트 게시글 제목4</div>
                    
                </div>

            </div>
        </div>
    </div>
    
</body>
</html>
