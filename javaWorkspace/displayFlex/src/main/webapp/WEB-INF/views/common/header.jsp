<%@page import="displayFlex.member.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<% MemberVo loginMember = (MemberVo)request.getSession().getAttribute("loginMember"); %>


<link rel="stylesheet" href="/cinema/resources/css/common/header.css">
<script src="/cinema/resources/js/common/header.js"></script>
<script src="https://kit.fontawesome.com/08e9cd3338.js" crossorigin="anonymous"></script>

<header-area id="header-area">
    <div>
       <img src="/cinema/resources/image/background/topbar.png" alt="topbar">
    </div>
    <div class="topMenu">
        <div>
            <a href="/cinema/home">
                <img src="/cinema/resources/image/background/newtroLogo.png" alt="logo">
            </a>
        </div>
        <div></div>
        <div class="search">
            <div>
               <!--  <button onclick="/cinema/home"><i class="fa-solid fa-magnifying-glass"></i></button>
                <input type="search" id="search" placeholder="검색어를 입력하세요."> -->
            </div>
        </div>
        <div class="icon">
            <button onclick="location.href='/cinema/ticket/select'"><i class="fa-solid fa-ticket"></i></button>
            <%if(loginMember == null) { %>
            <button onclick="location.href='/cinema/member/login'"><i class="fa-regular fa-user"></i></button>
            <% }else{ %>
            <button onclick="location.href='/cinema/mypage/main'"><i class="fa-solid fa-house-user"></i></button>
            <% } %>
            
            <button onclick="toggleMenu()"><i class="fa-solid fa-bars"></i></button>
        </div>
    </div>
    <div class="sideMenu">
        <ul>
            <li><i class="fa-solid fa-film"></i>&nbsp;&nbsp;<a href="/cinema/ticket/select">영화예매</a></li>
            <li><i class="fa-solid fa-pen-to-square"></i>&nbsp;&nbsp;<a href="/cinema/movie/list">영화검색</a></li>
            <li><i class="fa-solid fa-store"></i>&nbsp;&nbsp;<a href="/cinema/store">스토어</a></li>
            <li><i class="fa-solid fa-calendar-days"></i>&nbsp;&nbsp;<a href="/cinema/event/eventlist">이벤트</a></li>
            <li><i class="fa-solid fa-headset"></i>&nbsp;&nbsp;<a href="/cinema/serviceCenter/faqList?category=영화관이용">고객센터</a></li>
            <li><i class="fa-solid fa-wallet"></i>&nbsp;&nbsp;<a href="/cinema/sale/saleinfo">할인혜택</a></li>
            <li><i class="fa-solid fa-video"></i>&nbsp;&nbsp;<a href="/cinema/admin/screen-info/list">상영정보</a></li>
            <c:if test="${loginMember.adminYn eq 'Y' }">
            	<li><i class="fa-solid fa-gift"></i>&nbsp;&nbsp;<a href="/cinema/admin/coupon/list?searchValue=">쿠폰</a></li>
            </c:if>
        </ul>
    </div>
</header-area>



    
    
    
    
    
    
    