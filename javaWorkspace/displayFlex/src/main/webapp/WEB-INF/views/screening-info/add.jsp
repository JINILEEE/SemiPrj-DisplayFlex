<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/screening-info/add.css?ver=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr@4.6.3/dist/flatpickr.min.css">
  <script src="https://cdn.jsdelivr.net/npm/flatpickr@4.6.3/dist/flatpickr.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/flatpickr@4.6.3/dist/l10n/ko.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/screening-info/add.js"></script>
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
<!-- 검색 영역 + 리스트 영역 -->
<div class="form-container m-auto p-4">
    <div class="fs-5 fw-bold ml-248x pt-5 first-title">상영 정보 등록</div>
    <hr class="m-auto my-4 w-51e">
    <div class="input-container row g-3 m-auto">
        <div class="col-9 m-auto w-75 m-1">
            <label for="title" class="form-label">등록할 영화 검색하기</label>
            <input list="search-result"  type="text" class="form-control w-100" placeholder="영화 제목을 입력하새요" name="title" id="title">
            <dataList class="list-group-flush border-dark shadow" id="search-result">
             	<c:forEach var="movie" items="${movieList }">
             	<option value="${movie.movieName }|${movie.movieNo }">(개봉일: ${movie.releaseDate })</option>
             	</c:forEach>
             </dataList>
        </div>
        <div class="col-3 m-auto">
            <label for="theater" class="form-label">상영관 선택</label>
            <select class="form-select d-inline-block" aria-label="상영관 선택" id="theater" name="theater">
                <option selected>상영관 선택</option>
                <c:forEach var="element" items="${theater }">
	                <option value="${element }">${element }</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-4 m-auto mt-3">
            <label for="dateInput" class="form-label">상영 일자</label>
            <input type="text" class="form-control w-100" id="dateInput" name="screening-date" placeholder="상영일자 선택">
        </div>
        <div class="col-2 m-auto mt-3">
            <label for="startTime" class="form-label">시작 시간</label>
            <input type="text" class="form-control w-100" id="startTime" name="timeInput" placeholder="시작 시간">
        </div>
        <div class="col-md-auto m-auto text-center mt-4">
		<br>
        <div class="form-control border-0">~</div>
        </div>
        <div class="col-2 m-auto mt-3">
            <label for="endTime" class="form-label">종료 시간</label>
            <input type="text" class="form-control w-100" id="endTime" name="timeInput" placeholder="종료 시간">
        </div>
        <div class="col-3 m-auto mt-4">
            <br>
            <button type="button" class="btn btn-danger w-100" onclick="checkExistInfo();">상영 정보 추가</button>
        </div>
    </div>
    <div class="d-inline-block fs-5 fw-bold list-title">등록할 상영 정보</div>
    <hr class="custom-hr">
    <form action="${pageContext.request.contextPath }/admin/screen-info/add" method="post" onsubmit="return checkValidate();">
    <table id="info-table">
        <thead>
            <tr>
                <th>영화 이름</th>
                <th>상영관</th>
                <th>상영일자</th>
                <th>상영시간</th>
                <th>삭제하기</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
        <tfoot>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td><input type="submit" value="상영 정보 등록"  class="btn btn-success bg-1a w-6em"></td>
            </tr>
        </tfoot>
    	
    </table>
    </form>
</div>

</body>
</html>