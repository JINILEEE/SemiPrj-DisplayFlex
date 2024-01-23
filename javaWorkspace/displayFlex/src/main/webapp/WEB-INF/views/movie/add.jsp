<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/movie/add.css?ver=1">
<script defer src="${pageContext.request.contextPath}/resources/js/movie/add.js"></script>
</head>
<body>
<%
	String x = (String) session.getAttribute("alertMsg");
	session.removeAttribute("alertMsg");
%>
 <c:set var="msg"  value="<%= x %>" />
 <c:if test="${not empty msg}">
	 <script>
	        alert('<%= x %> ');
	</script>
 </c:if>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
    <div class="add-form bg-w">
        <div class="title-container">
            <span class="title"><strong>영화 등록</strong></span>
            <span>- 영화제목을 입력하면 api에서 나머지 입력란의 정보를 가져옵니다.</span><br>
            <span ><span class="star">*</span>표시가 붙은 부분은 필수 입력란입니다.</span>
        </div>
        <hr>
        <form action="${pageContext.request.contextPath}/admin/movie/add" method="post" onsubmit="return checkInputValue()" class="m-auto" enctype="multipart/form-data">
           <input type="text" name="movieCd" id="movieCd" hidden>
            <table>
                <thead></thead>
                <tbody>
                    <tr>
                        <td class="normal_text">제목<span class="star">*</span></td>
                        <td>
                            <input list="search-result" type="text" class="form-control custom-form" placeholder="영화 제목을 입력하세요" name="title" id="title" >
                        	<dataList class="list-group-flush border-dark shadow" id="search-result">
                        		
                        	</dataList>
                        </td>
                        <td class="normal_text">감독</td>
                        <td><input type="text" class="form-control custom-form" name="director" id="director"></td>
                         <td rowspan="4">
                        	<img alt="" src="" id="poster">
                        	<input type="text" name="poster" id="poster-url" hidden>
                        </td>
                    </tr>
                    <tr>
                        <td class="normal_text">장르</td>
                        <td><input type="text" class="form-control custom-form" name="genre" id="genre"></td>
                        <td class="normal_text">개봉일</td>
                        <td><input type="text" class="form-control custom-form" name="releaseDate" id="releaseDate" placeholder='"YYYYMMDD"형식으로 입력하세요'></td>
                    </tr>
                    <tr>
                        <td class="normal_text">관람 등급</td>
                        <td>
                            <select id="screen-grade" class="w-60 form-select custom-form" name="screenGrade" aria-label="관람 등급 선택" >
                                <option value="0">선택하세요</option>
                            <c:forEach var="element" items="${screenGrade }">
                                <option value="${element.screenGradeNo }">${element.name}</option>                            
                            </c:forEach>
                            </select>
                        </td>
                        <td class="normal_text">상영 시간</td>
                        <td><input type="text" class="form-control custom-form" name="runningTime" id="runningTime" placeholder="(단위: 분)"></td>
                    </tr>
                    <tr>
                        <td class="normal_text">출연 배우</td>
                        <td colspan="3"><textarea type="text" class="form-control custom-form" name="actor" id="actor" placeholder="최대 3명까지 저장 가능(','로 구분 해주세요)" ></textarea>
                    </tr>
                    <tr>
                        <td class="normal_text">별점<span class="star">*</span></td>
                        <td><input type="number" class="w-50 form-control custom-form" name="rate" id="rate"  min="1" max="10" step="0.01"></td>
                        <td>제작 국가</td>
                        <td><input type="text" class="form-control custom-form" name="nation" id="nation" placeholder="2개 이상의 국가는', '로 작성"></td>
                    </tr>

                    <tr>
                        <td class="normal_text"><span class="title"><strong>줄거리</strong></span></td>
                    </tr>
                    <tr>
                        <td colspan="5"><textarea class="story form-control m-auto custom-form" id="story" name="story" rows="15"></textarea></td>
                    </tr>
                    <tr>
                        <td class="normal_text"><span class="title"><strong>대표 이미지</strong></span></td>
                    </tr>
                    <tr>
                        <td class="still-image"  colspan="5">
                        <div class="m-auto">
							<div class="mb-3">
							  <input class="form-control custom-form" type="file" id="mainImage" name="mainImage">
							</div>
                        </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="normal_text"><span class="title"><strong>스틸컷</strong></span></td>
                    </tr>
                    <tr>
                        <td class="still-image"  colspan="5">
                        <div class="m-auto">
							<div class="mb-3">
							<img alt="" src=""  name="still">
							  <input class="form-control custom-form" type="file" id="stillImage1" name="stillImage">
							  <input type="text" name="stillImageUrl" id="still-url1" hidden>
							</div>
							<div class="mb-3">
							<img alt="" src=""  name="still">
							  <input class="form-control custom-form" type="file" id="stillImage2" name="stillImage">
							  <input type="text" name="stillImageUrl" id="still-url2" hidden>
							</div>
							<div class="mb-3">
							<img alt="" src=""  name="still">
							  <input class="form-control custom-form" type="file" id="stillImage3" name="stillImage">
							  <input type="text" name="stillImageUrl" id="still-url3" hidden>
							</div>
                        </div>
                        </td>
                    </tr>
                    <tr>
                    	<td></td>
                    	<td></td>
                    	<td></td>
                    	<td></td>
                        <td>
                            <div class="df fd-cr">
									<input type="submit" value="등록하기" class="btn btn-success">
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
           
        </form>

    </div>
	
</body>
</html>