<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="/cinema/resources/css/store/adminStoreEnroll.css">
<script src="https://kit.fontawesome.com/08e9cd3338.js"
	crossorigin="anonymous"></script>
<script defer src="/cinema/resources/js/store/adminStoreEnroll.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 등록 페이지</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>

	<!-- form태그의 enctype이 텍스트와 이미지 모두 존재하므로 "multipart/mixed stream" 사용! -->
	<form action="/cinema/admin/store/enroll" method="post"
		enctype="multipart/form-data">
		<div class="product">
			<div></div>
			<div class="product-main">
				<div id="top">
					<div>
						<h1>스토어 - 제품등록</h1>
					</div>
				</div>
				<div id="product-detail">
					<div>
						<div id="imgPreview" width="400" height="400">
							<i class="fa-solid fa-circle-plus"></i>
						</div>
						<div>
							<!-- Add a hidden field to store the image path -->
                            <input type="hidden" id="imagePath" name="image" value="${ vo.image }">
                            <!-- image name이 "f"인것은 컨트롤러에서 Part f = req.getPart("f"); 로 "f" 설정했기 때문이다!!! -->
							<input type="file" id="upload" name="f" accept="image/*" onchange="previewImage(event)">
						</div>
					</div>
					<div id="detail">
						<div>
							<!-- post 방식으로 데이터처리하려면 반드시 name 값을 만들어서 자바에 getParameter 에 가져가야한다!!!! -->
							<input type="text" name="title" placeholder="ex)우리 패키지">
						</div>
						<div id="description">
							<div>가격</div>
							<div>
								<input type="text" id="price" name="price" placeholder="61000" oninput="onlyNumber();">
							</div>
							<div>상품구성</div>
							<div>
								<input type="text" name="productElement" placeholder="일반 영화 관람권 4매+더블콤보 1개">
							</div>
							<div>제품설명</div>
							<div>
								<textarea id="productDetail" name="shortDescription" cols="20" rows="5"
									placeholder="일반 영화를 관람할 수 있는 영화 관람권 4매와 맛있는 팝콘과 콜라를 먹을 수 있는 우리 패키지"></textarea>
							</div>
							<div>분류</div>
							<span> 
								<input type="radio" value="1" name="category"> 추천메뉴 
							    <input type="radio" value="2" name="category"> 기프트카드
								<input type="radio" value="3" name="category"> 콤보 
								<br>
								<input type="radio" value="4" name="category"> 팝콘 
							    <input type="radio" value="5" name="category"> 음료 
								<input type="radio" value="6" name="category"> 스낵
							</span>
							<div>공개여부</div>
							<span> 
								<input type="radio" value="N" name="delYn"> 공개 
							    <input type="radio" value="Y" name="delYn"> 비공개
							</span>
						</div>
					</div>
				</div>
				<div id="adminButton">
					<div id="enroll">
						<input type="submit" value="등록">
					</div>
					<div id="delete">
						<button>취소</button>
					</div>
				</div>
			</div>

			<div></div>
		</div>
	</form>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>

</html>