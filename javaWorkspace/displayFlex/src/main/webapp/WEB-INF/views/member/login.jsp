<%@page import="displayFlex.member.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/cinema/resources/member/login.css">

</head>
<body>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
    <div id="wrap">
        <form action="/cinema/member/login" method="post">
            <div id="loginArea">
           		<div class="loginArea-width"><img src="/cinema/resources/image/find/1.svg"></div>
                <div class="loginArea-width"><label for="loginPage"><input type="text" id="loginPage" placeholder="로그인 / 회원가입"></label></div>
                <% if(loginMember == null) { %>
                <div class="loginArea-width"><input type="text" name="memberId" id="memberId" placeholder="아이디"></div>
                <div class="loginArea-width"><input type="password" name="memberPwd" id="memberPwd" placeholder="비밀번호"></div>
                <div class="check-box">
                    <input class="form-check-input" type="checkbox" value="rememberMe" id="rememberMe" name="rememberMe">
                    <label class="form-check-label" for="rememberMe">아이디 저장</label></div>
                <div class="form-check-confirm">
                    <div class="form-check-confirm-b1">
                    	<input type="submit" value="로그인" id="logincheck"></div>
                    </div>
                    <div class="form-check-confirm-b2">
                 <div><a href="/cinema/member/join">회원가입</a></div>
                 <div><a href="/cinema/member/idFind">아이디 찾기</a></div>
                 <div><a href="/cinema/member/pwdFind">비밀번호 찾기</a></div>
                </div>
                 <%}else { %>
                 	<h3><%= loginMember.getMemberNick() %></h3>
            		<br> 
            		님 환영합니다 ~ !
            		<br>
               <% } %>
            </div>
    </div>

    <script>

        //아이디 기억하기
        function rememberMemberId() {
            var memberId = document.getElementById('memberId').value;
            var rembmerMeCheckbox = document.getElementById('rememberMe');

            if(rememberMeCheckbox.checked) {
                document.cookie = 'memberId' + encodeURIComponent(memberId) + '; expires=Thu, 31 Dec 2037 12:00:00 UTC; path=/';\
            }else {
                document.cookie = 'memberId=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/';
            }
        }

        // 페이지 로드 시 저장된 아이디가 있으면 입력란에 표시
        window.onload = function() {
            var MemberIdInput = document.getElementById('memberId');
            var rememberMemberId = getCookie('memberId');

            if(rememberMemberId) {
                MemberIdInput.value = decodeURIComponent(rememberMemberId);
                document.getElementById('memberId').checked = true;
            }
        }

    </script>
    
</body>
</html>