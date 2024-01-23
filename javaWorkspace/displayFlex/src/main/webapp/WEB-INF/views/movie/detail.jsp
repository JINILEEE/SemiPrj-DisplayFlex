<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/movie/detail.css">
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
    <!-- 관리자일 경우 버튼 or a 링크 보여주기 -->
    <div class="add-form bg-w">
        <div class="title-container df jc-sb">
            <div class="title"><strong>영화 정보</strong></div>
            <div class="admin-container">
                 <c:if test="${not empty loginMember && loginMember.adminYn eq 'Y'}">
                    <button class="bg-b73232 admin-btn" onclick="location.href='${pageContext.request.contextPath}/admin/movie/delete?movieNo=${movie.movieNo }'">삭제</button>
                  </c:if>
            </div>
        </div>
        <hr class="jh-hr">
        <table>
            <thead></thead>
            <tbody>
                <tr>
                    <td class="normal_text">제목</td>
                    <td>
                        <span>${movie.movieName}</span>
                    </td>
                    <td class="normal_text">감독</td>
                    <td><span>${movie.mainDirector}</span></td>
                    <td rowspan="4">
                    	<img src="${movie.movieImage}" alt="${movie.movieName}">
                    </td>
                </tr>
                <tr>
                    <td class="normal_text">장르</td>
                    <td><span>${movie.genre}</span></td>
                    <td class="normal_text">개봉일</td>
                    <td><span>${movie.releaseDate}</span></td>
                </tr>
                <tr>
                    <td class="normal_text">관람 등급</td>
                    <td>
                        <span>${movie.gradeName}</span>
                    </td>
                    <td class="normal_text">상영 시간</td>
                    <td><span>${movie.runningTime}분</span></td>
                </tr>
                <tr>
                    <td class="normal_text">출연</td>
                    <td colspan="3"><span>${movie.actors}</span></td>
                </tr>
                <tr>
                    <td class="normal_text">별점</td>
                    <td>${movie.rate}</td>
                    <td>제작 국가</td>
                    <td>${movie.nation }</td>
                    <td id="reserve-td">
                    	<c:if test="${movie.isScreening eq true  && loginMember.adminYn eq 'N'}">
                    		<button class=" btn btn-succsess bg-abc95f"  onclick='location.href="${pageContext.request.contextPath}/ticket/select"'>예매하기→</button>
                    	</c:if>
                    </td>
                </tr>

                <tr>
                    <td class="normal_text"><span class="title"><strong>줄거리</strong></span></td>
                </tr>
                <tr>
                    <td colspan="5"><div class="story">${movie.story}</div></td>
                </tr>
                <tr>
                    <td class="normal_text"><span class="title"><strong>스틸컷</strong></span></td>
                </tr>
                <tr>
                    <td colspan="5">
                        <div class="df js-sa">
	                        <c:forEach var="element" items="${movie.stillsList }">
	                            <div>
	                                <img src="${element.filePath}" alt="${movie.movieName} 스틸이미지" width="300">
	                            </div>                        
	                        </c:forEach>    
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="review-container bg-w" >
    	<div class="title mx-3">
    		<strong>리뷰 작성</strong>
   		</div>
    	<hr class="jh-hr">
	 	<div class="title-container m-auto">
	        <form id="rating-form" action="${pageContext.request.contextPath}/movie/review/add" method="post" onsubmit="return checkContent();">
	            <input type="text" name="movieNo" value="${movie.movieNo }" hidden>
	            <div>
	            	<div class="rating-container">
					    <div class="rating">
						    <i class="rating__star far fa-star"></i>
						    <i class="rating__star far fa-star"></i>
						    <i class="rating__star far fa-star"></i>
						    <i class="rating__star far fa-star"></i>
						    <i class="rating__star far fa-star"></i>
						    <i class="rating__star far fa-star"></i>
						    <i class="rating__star far fa-star"></i>
						    <i class="rating__star far fa-star"></i>
						    <i class="rating__star far fa-star"></i>
						    <i class="rating__star far fa-star"></i>
						</div>
						<span>별점: </span><input type="text" id="rating-value" name="rating-value" class="border-0" readonly value="0">
					</div>
	                <textarea name="review-content" id="review-content" name="review-content" cols="60" rows="3" class="form-control w-50"></textarea>
	               	<input class="review-submit btn btn-secondary" type="submit" value="작성하기">
	            </div>
	        </form>
	     </div>
	     <hr class="jh-hr">
	     <div id="review-container" class="review-list-container">
	     	
	     </div>
	     <div id="button-container" class="p-3 mt-3 text-center">
		     <button id="show-more" type="button" class="btn btn-link text-decoration-none text-black m-auto fw-bold" onclick="showReviews();">더보기</button>
	     </div>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/movie/detail.js"></script>
</body>
</html>