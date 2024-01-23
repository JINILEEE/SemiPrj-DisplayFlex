<%@page import="displayFlex.serviceCenter.notice.vo.NoticeVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% NoticeVo vo = (NoticeVo) request.getAttribute("vo");  %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/cinema/resources/css/serviceCenter/notice/noticeAdd.css">
<script defer type="text/javascript" src="/cinema/resources/js/serviceCenter/notice.js"></script>
</head>
<body>

    <%@ include file="/WEB-INF/views/common/header.jsp" %>

	<form action="/cinema/admin/noticeEdit" method="post">
	    <main>
	        <div id="contents">
	            <div id="title_top">
	                <h1>고객센터</h1>
	            </div>
	            <div id="tab_tit">
	                <nav>
	                    <a href="/cinema/serviceCenter/faqList">FAQ</a>
	                    <a href="/cinema/serviceCenter/noticeList">공지사항</a>
	                    <a href="/cinema/serviceCenter/inquiryWrite">1:1 문의</a>
	                    <a href="/cinema/serviceCenter/recommendList">상영요청</a>
	                </nav>
	            </div>
	            <div id="con_tit ty1">
	                <div id="notice_cont">
	                    <div>
	                        <h2  class="tit">공지사항 등록</h2>
	                    </div>
	                    <div>
	                        <h3>* 필수입력</h3>
	                    </div>
	                </div>
	                <table id="tbl_form">
	                    <colgroup>
	                        <col style="width: 15%;">
	                        <col style="width: auto;">
	                    </colgroup>
	                    <tbody>
	                        <tr>
	                            <th scope="row" class="req">
	                                제목
	                            </th>
	                            <td>
	                                <div class="bx_textarea">
	                                    <input type="text" name="title" class="ty2" id="iDtitle" value="<%= vo.getTitle() %>">
	                                    <span class="txt_count">
	                                        <em id="strongContentsCount_title">0</em>
	                                        /한글
	                                        <em>100</em>
	                                        자
	                                    </span>
	                                </div>
	                            </td>
	                        </tr>
	                        <tr>
	                            <th scope="row" class="req">
	                                내용
	                            </th>
	                            <td>
	                                <div class="bx_textarea">
	                                    <textarea name="content" class="ty2" id="iDcontents" cols="10" rows="10" >
	                                    	<%= vo.getContent() %>
	                                    </textarea>
	                                    <br>
	                                    <div class="txt_red txt_color01">
	                                        <img src="/cinema/resources/image/faqIcon/caution_.png" alt="주의 아이콘" width="14px">
	                                        현재 페이지 내 '클릭/타이핑' 없이 머무르는 경우,
	                                        <br>
	                                        <P style="margin: 0;">20분 후 접속이 자동 종료되며 작업한 내용은 저장이 되지 않습니다.</P>
	                                    </div>
	                                    <span class="txt_count">
	                                        <em id="strongContentsCount">0</em>
	                                        /한글
	                                        <em>4,000</em>
	                                        자
	                                    </span>
	                                </div>
	                            </td>
	                        </tr>
	                    </tbody>
	                </table>
	                <div id="btn_wrap">
	                    <input class="submit" type="submit" value="수정">
	                </div>
	            </div>
	        </div>
		</main>
        <input type="hidden" name="noticeNo" value="<%= vo.getNoticeNo() %>">
	</form>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>