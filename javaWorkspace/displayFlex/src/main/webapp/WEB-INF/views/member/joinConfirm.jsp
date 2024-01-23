<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/cinema/resources/member/joinConfirm.css">

</head>
<body>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="main">
<div class="wrap">
    <div class="wrap-main">
        <div><img src="/cinema/resources/image/member/loginConfirm.png"></div>
        <div>가입완료</div>
        <div>
            <div class="wrap-main-first-font">FLEX 회원이 되신 것을 환영합니다.</div>
            <div class="wrap-main-second-font">회원가입 절차가 모두 완료되었습니다.</div>
            <div class="wrap-main-second-font">로그인 후 편리하고 안전한 보안 서비스를 확인해 보세요.</div>
        </div>
        <div><input type="button" value="확인" onclick="location.href='/cinema/home'"></div>
    </div>
  </div>
</div>
</body>
</html>