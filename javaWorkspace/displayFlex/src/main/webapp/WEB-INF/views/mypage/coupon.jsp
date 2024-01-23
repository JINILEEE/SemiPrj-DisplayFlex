<%@page import="java.util.Map"%>
<%@page import="displayFlex.mypage.vo.PageVo"%>
<%@page import="displayFlex.mypage.CouponVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    List<CouponVo> couponVoList = (List<CouponVo>) request.getAttribute("couponVoList");
    PageVo pvo = (PageVo)request.getAttribute("pvo");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="/cinema/resources/css/mypage/coupon.css">
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <div class="container">
        <div class="main">
            <div class="main-first">FLEX 할인쿠폰 내역</div>
            <div class="main-second">FLEX 할인쿠폰 보유(등록) 내역</div>
            <div class="main-box">
                <div class="main-box-b1">영화쿠폰</div>
            </div>
            <div class="main-table">
                <div class="main-table-header">
                    <div>쿠폰보유번호</div>
                    <div>회원번호</div>
                    <div>쿠폰번호</div>
                    <div>쿠폰생성일자</div>
                    <div>쿠폰유효기간</div>
                </div>
                <% for(CouponVo vo : couponVoList){ %>
                    <div class="main-table-body">
                        <div><%= vo.getRetainedNo() %></div>
                        <div><%= vo.getMemberNo() %></div>
                        <div><%= vo.getCouponNo() %></div>
                        <div><%= vo.getRetainedDate() %></div>
                        <div><%= vo.getCouponEnddate() %></div>
                    </div>
                <% } %>
            </div>
        </div>
        <div class="page-area">
            <div class="page-buttons">
                <% if(pvo.getStartPage() != 1){ %>
                    <a href="/cinema/mypage/coupon?pno=<%= pvo.getStartPage()-1 %>">이전</a>
                <% } %>

                <% for(int i = pvo.getStartPage(); i <= pvo.getEndPage(); i++){ %>
                    <% if(i == pvo.getCurrentPage()){ %>
                        <span><%= i %></span>
                    <% }else{ %>
                        <a href="/cinema/mypage/coupon?pno=<%= i %>"><%= i %></a>
                    <% } %>
                <% } %>

                <% if(pvo.getEndPage() != pvo.getMaxPage()){ %>
                    <a href="/cinema/mypage/coupon?pno=<%= pvo.getEndPage()+1 %>">다음</a>
                <% } %>
            </div>
        </div>
    </div>
    <footer></footer>
</body>
</html>

<script>
    // Function to handle click events on coupon items
    function handleClick(event, currentPage) {
        const tr = event.currentTarget;
        const no = tr.children[0].innerText;
        location.href = 'http://localhost:9002/cinema/mypage/coupon?memberNo=' + memberNo + '&currentPage=' + currentPage;
    }

    // Add event listeners to coupon items in the first table
    const trArr = document.querySelectorAll(".main-table .main-table-body");
    for (let i = 0; i < trArr.length; ++i) {
        trArr[i].addEventListener('click', (e) => {
            handleClick(e, '<%= pvo.getCurrentPage() %>');
        });
    }
</script>