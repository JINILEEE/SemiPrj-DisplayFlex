<%@page import="java.util.Map"%>
<%@page import="displayFlex.coupon.vo.CouponVo"%>
<%@page import="java.util.List"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="/cinema/resources/css/coupon/couponlist.css">
    
    <%
    	List<CouponVo> couponVoList = (List<CouponVo>) request.getAttribute("couponVoList");
    	Map<String, String> searchMap = (Map<String, String>)request.getAttribute("searchMap");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보유 쿠폰 내역</title>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>   
	<div class="bw"> 
        <hr id="jul1">
        
        <div id="coupon_title"> &nbsp;&nbsp;&nbsp;&nbsp;FLEX 할인쿠폰 내역</div>
        <hr id="jul2">
        <!-- <div style="color: blue; font-size: 25px; font-weight: bold; padding: 1%;" > &nbsp;FLEX 할인쿠폰 보유내역</div> -->
        
        <div class="search-area">
				<form action="/cinema/admin/coupon/list" method="get">
					<!-- <select name="searchType">
						<option value="title">제목</option>
						<option value="content">내용</option>
					</select> -->
					<!-- &nbsp;&nbsp;<input type="text" name="searchValue" placeholder="조회하기 버튼을 눌러주세요.">
					<input type="submit" value="조회하기"> -->
				</form>
                <div><div id="enroll" style="float: left;">
                    <c:if test="${not empty loginMember && loginMember.adminYn eq 'Y'}">
                        &nbsp; &nbsp;<button onclick="location.href='/cinema/coupon/admincoupon'">쿠폰등록</button>
                </c:if>
                </div>
                <div id="delete"><c:if test="${not empty loginMember && loginMember.adminYn eq 'Y'}"><button>쿠폰삭제</button></c:if></div>
                    
            </div>
			</div>
			
        
        
        <div id="coupon_title2">
        </div>
        <div id="main_top">
            <div id="main_title">
                <div style="color: white; font-size:large">쿠폰이름</div>
                <div style="color: white; font-size:large">쿠폰사용기간</div>
                <div style="color: white; font-size:large">쿠폰정보</div>
                <div style="color: white; font-size:large">쿠폰할인금액</div>
            </div>
            <div class="coupon_content">
              
                <div id="coupon_name">
                 <% 
                if(couponVoList != null){
                for(CouponVo vo : couponVoList){ %>
                    <div><%=vo.getCouponName() %></div>
                   
                    <% }} %>

                </div>
    			
                <div id="coupon_period">
                <% 
                if(couponVoList != null){
                for(CouponVo vo : couponVoList){ %>
                   <div><%=vo.getCouponValidePeriod() %>까지 사용가능</div>
                   <% }} %>
                </div>
    			
                <div id="coupon_info">
                <% 
                if(couponVoList != null){
                for(CouponVo vo : couponVoList){ %>
                    <div><%=vo.getCouponInfo() %></div>
                    <% }} %>
                </div>
                
    			
                <div id="coupon_discount">
                <% 
                if(couponVoList != null){
                for(CouponVo vo : couponVoList){ %>
                   <div><%=vo.getCouponDiscount() %>원할인</div>
                    <% }} %>
                </div>
                
            </div>
    
        </div>

        






































































        

        <!-- <div class="coupon_info">
            <div id="info_one">
                <div>VIP무료 관람쿠폰</div>
                <hr>
                <div>VIP 반값 할인 쿠폰</div>
                <hr>
                <div>어린이 1000원 할인 쿠폰</div>
                <hr>
                <div>VIP무료 관람쿠폰</div>
            </div>
            <div id="info_two">
                <div>2023-12-01 ~ 2023-12-31</div>
                <div>2023-12-01 ~ 2023-12-31</div>
                <div>2023-12-01 ~ 2023-12-31</div>
                <div>2023-12-01 ~ 2023-12-31</div>
            </div>
            <div id="info_three">
                <div>VIP할인쿠폰</div>
                <div>어린이할인쿠폰</div>
                <div>경로할인쿠폰</div>
                <div>장애인할인쿠폰</div>
            </div>
            <div id="info_four">
                <div>N</div>
                <div>N</div>
                <div>N</div>
                <div>N</div>
            </div>
        </div>     -->
    </div>
</body>
</html>