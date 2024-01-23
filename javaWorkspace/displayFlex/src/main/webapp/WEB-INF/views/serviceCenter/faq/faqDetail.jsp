<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="displayFlex.serviceCenter.faq.vo.FaqVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	FaqVo vo = (FaqVo)request.getAttribute("vo");
    	String currPage = (String)request.getAttribute("currPage");
    	if(currPage == null) {
    		currPage = "1";
    	}
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/cinema/resources/css/serviceCenter/faq/faqDetail.css">
<script defer type="text/javascript" src="/cinema/resources/js/serviceCenter/faq.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>

    <main>
        <div id="contents">
            <div id="title_top">
                <h1>고객센터</h1>
                <c:if test="${loginMember.adminYn eq 'Y'}">
	                <a href="<c:if test='${not empty vo}'>/cinema/admin/faqEdit?faqNo=${vo.getFaqNo()}</c:if>">수정</a>
					<a href="<c:if test='${not empty vo}'>/cinema/admin/faqDelete?faqNo=${vo.getFaqNo()}</c:if>">삭제</a>
                </c:if>
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
                    <table id="tb1_dtl" summary="공지사항 상세 내용에 대한 표">
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
                                    <span class="cont"><%= vo.getFaqNo() %></span>
                                    <span class="tit">등록일</span>
                                    <span class="mid">|</span>
                                    <span class="cont"><%= vo.getEnrollDate() %></span>
                                    <span class="tit">수정일</span>
                                    <span class="mid">|</span>
                                    <span class="cont"><%= vo.getModifyDate() %></span>
                                    <span class="tit">조회수</span>
                                    <span class="mid">|</span>
                                    <span class="cont"><%= vo.getHit() %></span>
                                </td>
                            </tr>
                            <tr id="tb_content">
                                <td colspan="2">
                                    <div id="noticeContents">
                                    	<%= vo.getContent() %>
                                    </div>
                                </td>
                            </tr>
                            <!-- <tr id="tb_link">
                                <td>
                                    <a href="" id="">
                                        <span class="tb_next">다음글</span>
                                    </a>
                                </td>
                            </tr>
                            <tr id="tb_link">
                                <td>
                                    <a href="" id="">
                                        <span class="tb_back">이전글</span>
                                    </a>
                                </td>
                            </tr> -->
                        </tbody>
                    </table>
                    <div id="list_btn">
                        <a href="/cinema/serviceCenter/faqList?pno<%= currPage %>">목록</a>
                    </div>
                </div>
            </div>
        </div>
    </main>
    
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>