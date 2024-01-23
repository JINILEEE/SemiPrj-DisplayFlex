<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="/cinema/resources/css/coupon/admincoupon.css">
<script src="https://kit.fontawesome.com/08e9cd3338.js"
	crossorigin="anonymous"></script>
<script defer src="/cinema/resources/js/store/adminStoreEnroll.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠폰 관리자</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>

	<form action="/cinema/admin/coupon/add" method="post"
		entype="multipart/form-data">
		<div class="product">
			<div></div>
			<div class="product-main">
				<div id="top">
					<div>         
						<h1>쿠폰(ADMIN)</h1>
					</div>
				</div>
				<div id="product-detail">
					
					<div id="detail">
						<div id="description">
							<div style="font-size: xx-large;">쿠폰선택</div>
							<span> 
								<select name="s_type" id="diskind" >
										<option value=""  style="color: gray;" >쿠폰을 선택해주세요.</option>
										<option value="1">VIP할인쿠폰</option>
										<option value="2">어린이할인쿠폰</option>
										<option value="3">경로할인쿠폰</option>
										<option value="4">장애인할인쿠폰</option>	
										<option value="5">스토어할인쿠폰</option>				
								</select>
							</span>		
							<div style="font-size: xx-large;">할인금액</div>
							<span> 
								<select name="s_discount" id="diskind" >
										<option value=""  style="color: gray;" >할인금액</option>
										<option value="8000">VIP전액(8000원)</option>
										<option value="4000">VIP반값(4000원)</option>
										<option value="2000">어린이할인(2000원)</option>
										<option value="1000">경로할인(1000원)</option>
										<option value="1000">장애인할인(1000원)</option>	
										<option value="1000">스토어할인(1000원)</option>			
								</select>
							</span>
							<div  style="font-size: xx-large;">쿠폰이름	</div>
							<div>
								<select name="i_name" id="diskind" >
									<option value=""  style="color: gray;" >쿠폰선택</option>
									<option value="VIP할인쿠폰">VIP할인</option>
									<option value="어린이할인쿠폰">어린이할인</option>
									<option value="경로할인쿠폰">경로할인</option>
									<option value="장애인할인쿠폰">장애인할인</option>	
									<option value="스토어할인쿠폰">스토어할인</option>			
							</select>
							</div>		
							<div style="font-size: xx-large;">쿠폰발행일자</div>
							<div>
								<input style="width: 10em; font-size: 15px;"type="date" name="i_CreationDate" value="2023-12-01">
							</div>
							<div style="font-size: xx-large;">쿠폰사용기간</div>
							<div>
								발행일부터<input style="width: 10em; font-size: 15px;"type="date" name="i_ValidPeriod" value="2023-12-01">까지
							</div>
                            
							<div style="font-size: xx-large;">쿠폰안내</div>
							<div>
								<textarea name="t_info" cols="50" rows="2"
									placeholder="쿠폰 안내 메세지를 입력하세요. "></textarea>
							</div>
							
						</div>
					</div>
				</div>
				<div id="adminButton">
					<div id="enroll">
						<input type="submit" value="등록" >
					</div>
					<div id="delete">
						<button>취소</button>
					</div>
				</div>
			</div>

			<div></div>
		</div>
	</form>
</body>
</html>