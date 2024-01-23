<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet"
	href="/cinema/resources/css/store/adminStoreEnroll.css">
<script src="https://kit.fontawesome.com/08e9cd3338.js"
	crossorigin="anonymous"></script>
<script defer src="/cinema/resources/js/store/adminProductEdit.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 수정 페이지</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>

	<!-- form태그의 enctype이 텍스트와 이미지 모두 존재하므로 "multipart/mixed stream" 사용! -->
	<form action="/cinema/admin/store/edit" method="post"
		enctype="multipart/form-data">
		<div class="product">
			<div></div>
			<div class="product-main">
				<div id="top">
					<div>
						<h1>스토어 - 제품수정</h1>
					</div>
				</div>
				<div id="product-detail">
					<div>
						<div >
							<img id="imgPreview" alt="기존 이미지" src="${ vo.image }" width="400" height="400">
						</div>
						<div>
							<!-- Add a hidden field to store the image path -->
                            <input type="hidden" id="imagePath" name="image" value="${ vo.image }">
                            <!-- image name이 "f"인것은 컨트롤러에서 Part f = req.getPart("f"); 로 "f" 설정했기 때문이다!!! -->
							<input type="file" id="upload" name="f" accept="image/*" value="${ vo.image }" onchange="previewImage(event)">
						</div>
					</div>
					<div id="detail">
						<div>
							<!-- post 방식으로 데이터처리하려면 반드시 name 값을 만들어서 자바에 getParameter 에 가져가야한다!!!! -->
							<input type="text" name="title" placeholder="ex)우리 패키지" value="${ vo.title }">
						</div>
						<div id="description">
							<div>가격</div>
							<div>
								<input type="text" id="price" name="price" placeholder="61,000원" value="${ vo.price }" oninput="onlyNumber();">
							</div>
							<div>상품구성</div>
							<div>
								<input type="text" name="productElement" placeholder="일반 영화 관람권 4매+더블콤보 1개" value="${vo.productElement}">
							</div>
							<div>제품설명</div>
							<div>
								<textarea id="productDetail" name="shortDescription" cols="20" rows="5"
									placeholder="일반 영화를 관람할 수 있는 영화 관람권 4매와 맛있는 팝콘과 콜라를 먹을 수 있는 우리 패키지">${ vo.shortDescription }</textarea>
							</div>
							<div>분류</div>
							<span> 
								<input type="radio" value="1" name="category" <c:if test="${vo.category eq '추천메뉴'}">checked="checked"</c:if>> 추천메뉴 
							    <input type="radio" value="2" name="category" <c:if test="${vo.category eq '기프트카드'}">checked="checked"</c:if>> 기프트카드
								<input type="radio" value="3" name="category" <c:if test="${vo.category eq '콤보'}">checked="checked"</c:if>> 콤보 
								<br>
								<input type="radio" value="4" name="category" <c:if test="${vo.category eq '팝콘'}">checked="checked"</c:if>> 팝콘 
							    <input type="radio" value="5" name="category" <c:if test="${vo.category eq '음료'}">checked="checked"</c:if>> 음료 
								<input type="radio" value="6" name="category" <c:if test="${vo.category eq '스낵'}">checked="checked"</c:if>> 스낵
							</span>
							<div>공개여부</div>
							<span> 
								<input type="radio" value="N" <c:if test="${vo.delYn eq 'N'}">checked="checked"</c:if> name="delYn" > 공개 
							    <input type="radio" value="Y" <c:if test="${vo.delYn eq 'Y'}">checked="checked"</c:if> name="delYn"> 비공개
							</span>
						</div>
					</div>
				</div>
				<div id="adminButton">
					<div id="enroll">
						<input type="submit" value="수정">
					</div>
					<div id="delete">
						<button onclick="location.href='/cinema/store/product?no=${ vo.productNo }'">취소</button>
					</div>
				</div>
			</div>

			<div></div>
		</div>
		<input type="hidden" name="no" value="${ vo.productNo }">
	</form>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>