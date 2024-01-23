<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% MemberVo vo = (MemberVo) request.getAttribute("vo"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/cinema/resources/css/find/pwdFindConfirm.css">

</head>
<body>

  <%@ include file="/WEB-INF/views/common/header.jsp" %>
  
  <div class="container">
        <div class="main">
            <div class="main-first-font">비밀번호 찾기 결과</div>
            <div class="line"></div>
            <div class="main-option">
              <span>아이디</span><%= vo.getMemberId() %>
            </div>
            <div class="main-option">
              <span>회원정보</span>
              <div class="main-option2">
                <div>이메일 : <%= vo.getMemberEmail() %></div>
                <div>비밀번호 : <%= vo.getMemberPwd() %></div>
              </div>
            </div>
            <div class="refer">찾으신 비밀번호로 로그인 후, 새 비밀번호로 변경하시기 바랍니다.</div>
            <div class="line"></div>
            <div class="main-button"><button type="button" onclick="location.href='/cinema/home'">돌아가기</button></div>
        </div>
        
  </div>
</body>
</html>

</body>
</html>