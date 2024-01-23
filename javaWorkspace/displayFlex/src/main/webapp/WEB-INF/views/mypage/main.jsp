<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/cinema/resources/css/mypage/main.css">

</head>
<body>

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    
  <div class="container">
        <div class="main">
            <div class="main-box">
                <div><a href="/cinema/home"><img src="/cinema/resources/image/mypage/6.svg"></a></div>
                <div><%= loginMember.getMemberNick() %>님, 환영합니다.</div>
                <!-- 수정 필요(향후 sql로 가져와야함) -->
                <div></div>   
                <!-- 수정 필요(향후 데이터 가져와야함) -->
                <div></div>
                 <div class="form-check-confirm-b3"><button type="button" onclick="location.href='/cinema/member/logout'">로그아웃</button></div>
            </div>
            <div class="main-text">마이페이지</div>
            <div class="main-menu">
                <div class="main-menu-area">
                    <div><a href="/cinema/mypage/movieRev"><img src="/cinema/resources/image/mypage/1.svg"></a></div>
                    <div>나의 예매내역</div>
                    <div>회원님이 예매하신 영화내역 
                        및 정보를 확인할 수 있습니다.</div>
                </div>
                <div class="main-menu-area">
                    <div><a href="/cinema/mypage/correct"><img src="/cinema/resources/image/mypage/2.svg"></a></div>
                    <div>회원정보관리</div>
                    <div>회원님의 개인정보를 
                        확인/변경할 수 있습니다.</div>
                </div>
                <div class="main-menu-area">
                    <div><a href="/cinema/mypage/eventCheck"><img src="/cinema/resources/image/mypage/3.svg"></a></div>
                    <div>이벤트 참여내역</div>
                    <div>회원님의 참여하신 이벤트
                          내역을 확인할 수 있습니다.</div>
                </div>
                <div class="main-menu-area">
                    <div><a href="/cinema/mypage/coupon"><img src="/cinema/resources/image/mypage/4.svg"></a></div>
                    <div>할인쿠폰관리</div>
                    <div>회원님이 소지하고 계신 할인쿠폰
                        정보 확인할 수 있습니다.</div>
                </div>
                <div class="main-menu-area">
                    <div><a href="/cinema/mypage/inquiry"><img src="/cinema/resources/image/mypage/5.svg"></a></div>
                    <div>나의 문의내역</div>
                    <div>회원님이 문의하신 1:1 내역을
                        확인할 수 있습니다</div>
                </div>
            </div>
           
            </div>
        </div>
  </div>
  <footer></footer>
</body>
</html>

</body>
</html>