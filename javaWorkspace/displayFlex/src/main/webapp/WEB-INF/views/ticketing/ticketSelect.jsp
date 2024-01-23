<%@page import="displayFlex.ticketing.select.vo.SelectMovieVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	List<SelectMovieVo> movieList = (List<SelectMovieVo>)request.getAttribute("movieList");
	
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../resources/css/ticketing/ticketSelect.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
    <script defer type="text/javascript" src="../resources/js/ticketing/ticketSelect.js"></script>
    <title>예매페이지</title>
    
</head>
<body>
     <%@ include file="/WEB-INF/views/common/header.jsp" %>

    <main>
        <section id="section1">
            <div id="ticketing1-back">
                <div id="ticketing1">
                    <div id="movie">
                
                    <div class="menuName">
                        <span class="text">영화</span>
                    </div>
                    <ul id="movieList">
<%--                     <c:forEach var="movie" items="${movieList}" varStatus="status"> --%>
<%-- 					    <c:set var="movieName" value="${movie.movieName}" /> --%>
<%-- 					    <c:set var="movieNo" value="${movie.movieNo}" /> --%>
<%-- 					    <c:set var="movieImage" value="${movie.movieImage}" /> --%>
<%-- 					    <c:set var="screenGradeNo" value="${movie.screenGradeNo}" /> --%>

<%-- 					    <c:set var="ratedImgSrc" value="../resources/image/ticketing/ratedAll.png" /> --%>
<%-- 					    <c:choose> --%>
<%-- 					        <c:when test="${screenGradeNo eq '2'}"> --%>
<%-- 				            	<c:set var="ratedImgSrc" value="../resources/image/ticketing/rated12.png" /> --%>
<%--        						</c:when> --%>
<%--         					<c:when test="${screenGradeNo eq '3'}"> --%>
<%--             					<c:set var="ratedImgSrc" value="../resources/image/ticketing/rated15.png" /> --%>
<%-- 					        </c:when> --%>
<%-- 					        <c:when test="${screenGradeNo eq '5'}"> --%>
<%-- 					            <c:set var="ratedImgSrc" value="../resources/image/ticketing/rated19.png" /> --%>
<%-- 					        </c:when> --%>
<%--     					</c:choose> --%>

<!-- 					    <li class="ticketingMovie"> -->
<%-- 					        <button type="button" id="movieSelect" onclick="changeMovieInfo('${status.index + 1}')"> --%>
<%-- 					            <img src="${ratedImgSrc}" alt="이용가"> --%>
<%-- 					            <span class="text" id="movieNo${movieNo}" value="${movieNo}">${movieName}</span> --%>
<!-- 					        </button> -->
<!-- 					    </li> -->
<%-- 					</c:forEach> --%>
<!--                     상영중인 영화리스트..(수정필요)  -->
                        <% for(int i = 0; i < movieList.size(); i++){
                         	String movieName = movieList.get(i).getMovieName();
                         	int movieNo = Integer.parseInt(movieList.get(i).getMovieNo());
                         	String movieImage = movieList.get(i).getMovieImage();
                         	String screenGradeNo = movieList.get(i).getScreenGradeNo();
                         	String ratedImgSrc = "../resources/image/ticketing/ratedAll.png";
                         	switch(screenGradeNo){
                         	case "2" : ratedImgSrc = "../resources/image/ticketing/rated12.png"; break;
                         	case "3" : ratedImgSrc = "../resources/image/ticketing/rated15.png"; break;
                         	case "4" : ratedImgSrc = "../resources/image/ticketing/rated19.png"; break;
                         	case "5" : ratedImgSrc = "../resources/image/ticketing/rated19.png"; break;
                         	}
                        %>
                        <li class="ticketingMovie">
                            <button type="button" id="movieSelect" onclick="changeMovieInfo('<%=movieNo%>');">
                        		<img src="<%=ratedImgSrc%>" alt="이용가">
                       			<span class="text" id="movieNo<%=movieNo%>" value="<%=movieNo%>"><%=movieName%></span>
                            </button>
                        </li>
                        <% } %>
                    </ul>
                </div>
                <div id="arrow1">
                    <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-chevron-double-right" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M3.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L9.293 8 3.646 2.354a.5.5 0 0 1 0-.708z"/>
                        <path fill-rule="evenodd" d="M7.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L13.293 8 7.646 2.354a.5.5 0 0 1 0-.708z"/>
                    </svg>
                </div>
                <div id="date">
                    <div class="menuName">
                        <span class="text">날짜</span>
                    </div>
                    <ul id="dateList">

<!--                     	상영 날짜 리스트 들어가는곳 -->
                    </ul>
                </div>
                <div id="arrow2">
                    <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-chevron-double-right" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M3.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L9.293 8 3.646 2.354a.5.5 0 0 1 0-.708z"/>
                        <path fill-rule="evenodd" d="M7.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L13.293 8 7.646 2.354a.5.5 0 0 1 0-.708z"/>
                    </svg>
                </div>
                <div id="time">
                    <div class="menuName">
                        <span>상영관</span>
                    </div>
                    <div id="theater">	                
	                    <div id="theater1">
	                        <div>1관</div>
	                        <div id="theater1_time">
<!-- 							 	1관 상영타임 -->
	                        </div>
	                    </div>
	                    <div id="theater2">
	                        <div>2관</div>
	                        <div id="theater2_time">
<!-- 								2관 상영타임 -->
	                        </div>
	                    </div>
	                    <div id="theater3">
	                        <div>3관</div>
	                        <div id="theater3_time">
<!-- 								3관 상영타임 -->
	                        </div>
	                    </div>
	                    <div id="theater4">
	                        <div>4관</div>
	                        <div id="theater4_time">
<!-- 								4관 상영타임 -->
	                        </div>
	                    </div>
                    </div>
                </div>   
            </div>  
            </div>
        </section>
        <section id="section2">
            <div id="ticketing2">
                <div id="ticketing2_screen"><span>SCREEN</span></div>
                <div id="ticketing2_seat">
                    <div id="seat_area">
                        <div class="seat" id="seat_A">
                            <div class="seatRow" id="A">A</div>
                            <div class="seat_A" id="A1"><button>1</button></div>
                            <div class="seat_A" id="A2"><button>2</button></div>
                            <div></div>
                            <div class="seat_A" id="A3"><button>3</button></div>
                            <div class="seat_A" id="A4"><button>4</button></div>
                            <div class="seat_A" id="A5"><button>5</button></div>
                            <div class="seat_A" id="A6"><button>6</button></div>
                            <div class="seat_A" id="A7"><button>7</button></div>
                            <div class="seat_A" id="A8"><button>8</button></div>
                            <div></div>
                            <div class="seat_A" id="A9"><button>9</button></div>
                            <div class="seat_A" id="A10"><button>10</button></div>
                        </div>
                        <div class="seat" id="seat_B">
                            <div class="seatRow" id="B">B</div>
                            <div class="seat_B" id="B1"><button>1</button></div>
                            <div class="seat_B" id="B2"><button>2</button></div>
                            <div></div>
                            <div class="seat_B" id="B3"><button>3</button></div>
                            <div class="seat_B" id="B4"><button>4</button></div>
                            <div class="seat_B" id="B5"><button>5</button></div>
                            <div class="seat_B" id="B6"><button>6</button></div>
                            <div class="seat_B" id="B7"><button>7</button></div>
                            <div class="seat_B" id="B8"><button>8</button></div>
                            <div></div>
                            <div class="seat_B" id="B9"><button>9</button></div>
                            <div class="seat_B" id="B10"><button>10</button></div>
                        </div>
                        <div class="seat" id="seat_C">
                            <div class="seatRow" id="C">C</div>
                            <div class="seat_C" id="C1"><button>1</button></div>
                            <div class="seat_C" id="C2"><button>2</button></div>
                            <div></div>
                            <div class="seat_C" id="C3"><button>3</button></div>
                            <div class="seat_C" id="C4"><button>4</button></div>
                            <div class="seat_C" id="C5"><button>5</button></div>
                            <div class="seat_C" id="C6"><button>6</button></div>
                            <div class="seat_C" id="C7"><button>7</button></div>
                            <div class="seat_C" id="C8"><button>8</button></div>
                            <div></div>
                            <div class="seat_C" id="C9"><button>9</button></div>
                            <div class="seat_C" id="C10"><button>10</button></div>
                        </div>
                        <div class="seat" id="seat_D">
                            <div class="seatRow" id="D">D</div>
                            <div class="seat_D" id="D1"><button>1</button></div>
                            <div class="seat_D" id="D2"><button>2</button></div>
                            <div></div>
                            <div class="seat_D" id="D3"><button>3</button></div>
                            <div class="seat_D" id="D4"><button>4</button></div>
                            <div class="seat_D" id="D5"><button>5</button></div>
                            <div class="seat_D" id="D6"><button>6</button></div>
                            <div class="seat_D" id="D7"><button>7</button></div>
                            <div class="seat_D" id="D8"><button>8</button></div>
                            <div></div>
                            <div class="seat_D" id="D9"><button>9</button></div>
                            <div class="seat_D" id="D10"><button>10</button></div>
                        </div>
                    </div>
                    <div id="seat_description">
                    	<img src="../resources/image/ticketing/description.png" alt="선택설명" id="descriptionImg">
                    </div>
                    
                </div>
            </div>
        </section>
    </main>

    <aside></aside>
    
    <div id="selectInfo">
        <div id="ticket-posterImg">
			<img src="../resources/image/ticketing/flexLogo.png" alt="포스터" id="posterImg">
		</div>
        <div id="ticket-movieName">
            <span id="movieInfo">영화를 선택해주세요</span>
        </div>
        <div id="ticket-movieInfo">
            <table>
                <tbody>
                    <tr>
                        <td>일시</td>
                        <td><span id="dateInfo"><!-- 선택날짜 --></span><span id="timeInfo"><!-- 선택시간 --></span></td>
                    </tr>
                    <tr>
                        <td>상영관</td>
                        <td><span id="theaterInfo"><!-- 선택상영관--></span></td>
                    </tr>
                    <tr>
                        <td>인원</td>
                        <td id="reservedInfo"><!-- 좌석몇개? --></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div id="ticket-seatInfo">
            <table>
                <tbody>
                    <tr>
                        <td>좌석명</td>
                        <td>일반석</td>
                    </tr>
                    <tr>
                        <td>좌석번호</td>
                        <td id="seatInfo"><!-- 좌석번호.. A1,A2... --></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div id="ticket-payInfo">
            <table>
                <tbody>
                    <tr>
                        <td>총금액</td>
                        <td id="payInfo">0<!-- 금액(좌석 수 * 8000)  --></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div id="ticket-payButton">
            <form action="/cinema/ticket/payment" method="get">
                <button type="submit" id="selectComplete">
                    <i class="bi bi-check-circle-fill"></i>
                    <span>선택 완료</span>
                </button>
            </form>
        </div>
    </div>

	<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>