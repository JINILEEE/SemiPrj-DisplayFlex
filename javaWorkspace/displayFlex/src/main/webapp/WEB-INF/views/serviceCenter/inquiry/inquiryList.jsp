<%@page import="displayFlex.util.page.vo.PageVo"%>
<%@page import="displayFlex.serviceCenter.inquiry.vo.InquiryVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	List<InquiryVo> inquiryVoList = (List<InquiryVo>) request.getAttribute("inquiryVoList");
    	PageVo pvo = (PageVo)request.getAttribute("pvo");
    	String x = (String) session.getAttribute("alertMsg");
    	session.removeAttribute("alertMsg");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/cinema/resources/css/serviceCenter/inquiry/inquiryList.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="/cinema/resources/js/serviceCenter/inquiry/inquiryList.js"></script>

</head>
<body>
	
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    
    <c:set var="msg"  value="<%= x %>" />
 	<c:if test="${not empty msg}">
	<script>
		alert('<%= x %> ');
	</script>
 	</c:if>

	<main>
        <div id="contents">
            <div id="title_top">
                <h1>고객센터</h1>
                <c:if test="${loginMember eq 'Y'}">
                	<a href="/cinema/admin/inquiryWrite">등록</a>
                </c:if>
            </div>
            <div id="tab_tit">
                <nav>
                    <a href="/cinema/serviceCenter/faqList?category=영화관이용">FAQ</a>
                    <a href="/cinema/serviceCenter/noticeList">공지사항</a>
                    <a href="/cinema/serviceCenter/inquiryWrite">1:1 문의</a>
                    <a href="/cinema/serviceCenter/recommendList">상영요청</a>
                </nav>
            </div>
            <div id="acc2">
                <table id="tb_acc_wrap2">
                    <colgroup>
                        <col style="width: 20%;">
                        <col style="width: auto">
                        <col style="width: 20%;">
                    </colgroup>
                    <thead>
                        <tr>
                            <th id="thead1">번호</th>
                            <th id="thread2">제목</th>
                            <th id="thread3">작성자</th>
                            <th id="thread4">등록일</th>
                            <th id="thread5">답변상태</th>
                        </tr>
                    </thead>
                    <tbody id="tab">
                    <% for (InquiryVo vo : inquiryVoList) { %>
                    	<tr>
                    		<td><%= vo.getOnetooneNo() %></td>
                    		<td><%= vo.getTitle() %></td>
                    		<td><%= vo.getWriterNick() %></td>
                    		<td><%= vo.getEnrollDate() %></td>
                    		<td><%= vo.getState() %></td>
                    	</tr>
                    <% } %>
                    	
                    </tbody>
                </table>
                <div id="paging">
                    <% if(pvo.getStartPage() != 1){ %>
                   		<a href="/cinema/serviceCenter/inquiryList?pno=<%= pvo.getStartPage()-1 %>">이전</a>
                   	<% } %>
                   	
                   	<% for(int i = pvo.getStartPage() ; i <= pvo.getEndPage(); i++){ %>
						<% if( i == pvo.getCurrentPage() ){ %>
							<span><%= i %></span>
						<% } else { %>
							<a href="/cinema/serviceCenter/inquiryList?pno=<%= i %>"><%= i %></a>
						<% } %>
					<% } %>
					
					<% if( pvo.getEndPage() != pvo.getMaxPage() ){ %>
						<a href="/cinema/serviceCenter/inquiryList?pno=<%= pvo.getEndPage()+1 %>">다음</a>	
					<% } %>
                </div>
            </div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>