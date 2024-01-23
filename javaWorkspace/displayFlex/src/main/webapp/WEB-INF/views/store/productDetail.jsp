<%@page import="displayFlex.store.vo.StoreVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/cinema/resources/css/store/productDetail.css">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 상세페이지</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>

	<div class="product">
		<div></div>

		<div class="product-main">
			<div>
				<div><h1>${ vo.category }</h1></div>
				<c:if test="${loginMember.adminYn eq 'Y'}"> 
				<!-- 경로 잘 설정하기!!! -->
				<div id="enroll"><button onclick="location.href='/cinema/admin/store/edit?no=${vo.productNo}'">수정</button></div>
				<div id="delete"><button onclick="location.href='/cinema/admin/store/delete?no=${vo.productNo}'">삭제</button></div>
				</c:if>
			</div>
			<div id="product-detail">
				<div><img src="${vo.image}" alt="product" width="400" height="400"></div>
				<div id="detail">
					<h1>
						<b>${vo.title}</b>
					</h1>
					<div id="description">
						<div>가격</div>
						<div><script>document.write(Number(${vo.price}).toLocaleString() + "원")</script></div>
						<div>상품구성</div>
						<div>${vo.productElement}</div>
						<div>제품설명</div>
						<div>${vo.shortDescription}</div>
					</div>
				</div>
			</div>
			
			<button onclick="location.href='/cinema/store'">목록으로</button>
		</div>

		<div></div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
	
</body>
</html>