<%@page import="displayFlex.serviceCenter.inquiry.vo.InquiryVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
    	InquiryVo vo = (InquiryVo) request.getAttribute("vo");
    	String currPage = (String)request.getAttribute("currPage");
    	if(currPage == null){
    		currPage = "1";
    	}
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/cinema/resources/css/serviceCenter/inquiry/inquiryDetail.css">
<script type="text/javascript" src="/cinema/resources/js/serviceCenter/inquiry/inquiryDetail.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>

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
            <div id="tab_con">
                <div id="tab_wrap inner">
                    <!-- <h2></h2> -->
                    <table id="tb1_dtl" summary="1:1 문의 상세 내용에 대한 표">
                        <colgroup>
                            <col style="width: auto;">
                        </colgroup>
                        <thead>
                            <tr>
                                <th><%= vo.getTitle() %></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr id="tb_info">
                                <td>
                                    <span class="tit">번호</span>
                                    <span class="mid">|</span>
                                    <span class="cont"><%= vo.getOnetooneNo() %></span>
                                    <span class="tit">작성자</span>
                                    <span class="mid">|</span>
                                    <span class="cont"><%= vo.getWriterNick() %></span>
                                    <span class="tit">등록일</span>
                                    <span class="mid">|</span>
                                    <span class="cont"><%= vo.getEnrollDate() %></span>
                                    <span class="tit">답변여부</span>
                                    <span class="mid">|</span>
                                    <span class="cont"><%= vo.getState() %></span>
                                </td>
                            </tr>
                            <tr id="tb_content">
                                <td colspan="2">
                                    <div id="noticeContents">
                                        <p>
                                        	<%= vo.getContent() %>
                                        </p>
                                    </div>
                                </td>
                            </tr>
                            <tr id="tb_link">
                                <td>
                                	<div id="reTitle">
                                        <span>
                                        	<%= vo.getReTitle() %>
                                        </span>
                                        <c:if test="${not empty vo.reEnrollDate }">
	                                        <span style="margin-right: 16px;">
	                                        	<%= vo.getReEnrollDate() %>
	                                        </span>
                                        </c:if>
                                        <c:if test="${not empty vo.reTitle }">
	                                        <span>
	                                        	<a id="admin-inquiry-update">수정</a>
	                                        </span>
                                        </c:if>
                                    </div>
                                    <div id="reContents">
                                        <p>
                                        	<%= vo.getReContent() %>
                                        </p>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div id="list_btn">
                        <a href="/cinema/admin/serviceCenter/inquiryList?pno=<%= currPage %>">목록</a>
                    </div>
                </div>
            </div>
        </div>
    </main>
    
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>