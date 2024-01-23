<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/cinema/resources/css/find/idFind.css">

</head>
<body>
  <%@ include file="/WEB-INF/views/common/header.jsp" %>
  <div class="main">
    <form action="/cinema/member/idFind" method="post" name="idFind" onsubmit="return checkValue();">
    <div class="container">
      <div class="container-first"><img src="/cinema/resources/image/find/1.svg" alt=""></div>
      <div class="container-second">아이디/비밀번호 찾기</div>
      <div class="container-third">
        <div class="b1">아이디 찾기</div>
        <a href="/cinema/member/pwdFind"><div class="b2">비밀번호 찾기</div></a>
      </div>
      <div class="container-fourth">간편찾기</div>
      <div class="container-fifth">
        <table>
            <tr style="border-top: 3px solid black;">
              <td>이름</td>
              <td><input type="text" name="memberName" placeholder="이름"></td>
            </tr>
            <tr>
              <td>휴대폰번호</td>
              <td><input type="tel" name="memberPhoneNum" placeholder="-포함 13자리"></td>
             </tr>
        </table>
      </div>
      <div class="container-sixth">※ 휴대폰 번호가 변경되었다면 고객센터로 문의 바랍니다.</div>
        <div class="container-seventh">
        
        <input type="submit" value="아이디 찾기">

        </div>
    </div>
  </form>
  </div>
    <footer></footer>
      
  
</body>
</html>

<script>
function checkValue() {
    const memberName = document.querySelector("input[name=memberName]").value;
    const memberPhoneNum = document.querySelector("input[name=memberPhoneNum]").value;

    // 간단한 유효성 검사 (이름과 전화번호가 비어있는지 확인)
    if (memberName.trim() === "" || memberPhoneNum.trim() === "") {
        alert("이름과 전화번호를 입력해주세요.");
        return false; // 폼 제출 중단
    }
    
    //일치 여부 확인
    fetch('/cinema/member/idFind/check?memberName='+memberName +'&memberPhoneNum='+memberPhoneNum)
    .then((res) => {
    	if(!res.ok) {
    		throw new Error();
    	}
    	return res.text();
    })
	.then((data) => {
		alert(data);
		return true;
	})
	.catch((err) => {
		alert('입력한 정보가 일치하지 않습니다');
		return false;
	})
}

</script>
