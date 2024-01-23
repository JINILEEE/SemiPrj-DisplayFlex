<%@page import="java.util.List"%>
<%@page import="displayFlex.store.vo.StoreVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/cinema/resources/css/store/storeList.css">
<script src="/cinema/resources/js/store/storeList.js"></script>
<%
	List<StoreVo> storeVoList2 = (List<StoreVo>)request.getAttribute("storeVoList2");
	List<StoreVo> storeVoList = (List<StoreVo>)request.getAttribute("storeVoList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스토어</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="store">
		
		<div></div>
		
		<div class="store-main">
			<div id="top">
				<div><h1>스토어</h1></div>
				<c:if test="${loginMember.adminYn eq 'Y'}">
				<div id="enroll"><button onclick="location.href='/cinema/admin/store/enroll'">등록</button></div>
				<!-- <div id="delete"><button>삭제</button></div> -->
				</c:if>
			</div>
			<div id="smallMenu">
				<c:forEach items="${storeVoList2}" var="vo">
				<div><button><span>${vo.category}</span></button></div>
				</c:forEach>
			</div>
			<div class="itemPhoto">
				<c:forEach items="${storeVoList}" var="vo">
					<div>
                        <!-- 제발 a 태그 주소 잘 받아와야한다!!!! 쫌!!!! -->
						<a href="/cinema/store/product?no=${vo.productNo }">
						<img src="${vo.image}" alt="product" width="200" height="200">
						<br>
						<span id="first"><b>${vo.title}</b></span>
						</a>
						<br>
						<span id="second">${vo.productElement}</span>
						<br>
						<br>
						<span id="third"><b><script>document.write(Number(${vo.price}).toLocaleString() + "원")</script></b></span>
					</div>
				</c:forEach>
					<div></div>
					<div></div>
			</div>

		</div>
		
		<div></div>
	
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>