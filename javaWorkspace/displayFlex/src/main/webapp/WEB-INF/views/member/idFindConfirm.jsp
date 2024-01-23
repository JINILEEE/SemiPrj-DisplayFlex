<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% MemberVo vo = (MemberVo) request.getAttribute("vo"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/cinema/resources/css/find/idFindConfirm.css">

</head>
<body>
  <%@ include file="/WEB-INF/views/common/header.jsp" %>
  
    <div class="container">
      <div class="main">
          <div class="main-first-font">아이디 찾기 결과</div>
          <div class="line"></div>
          <div class="main-option"><span>이름</span>이우철</div>
          <div class="main-option"><span>아이디</span><%= vo.getMemberId() %>></div>
          
          <div class="refer">회원 이메일로 아이디를 보내드립니다.</div>
          <div class="line"></div>
          <div class="main-button"><button type="button" onclick="location.href='/cinema/member/pwdFind'">비밀번호 찾기</button><button type="button" onclick="location.href='/cinema/home'">돌아가기</button></div>
      </div>
    </div>

</body>
</html>
