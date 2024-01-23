<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../resources/css/ticketing/ticketPopup.css">
    <title>예매 정보</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>

<script>

window.onload = () => {
	let ticketData = JSON.parse(sessionStorage.getItem("ticketData"));
	
	
	const imgUrl = document.getElementById("imgUrl");
	const movieName = document.getElementById("movieName");
	const theaterNo = document.getElementById("theaterNo");
	const screeningDate = document.getElementById("screeningDate");
	const reservedInfo = document.getElementById("reservedInfo");
	const seatInfo = document.getElementById("seatInfo");
	const payAmount = document.getElementById("payAmount");
	
	imgUrl.src = ticketData.posterImg;
    movieName.innerText = ticketData.selectedMovie;
    theaterNo.innerText = ticketData.selectedTheater;
    screeningDate.innerText = ticketData.selectedDate + "  " + ticketData.selectedTime;
    reservedInfo.innerText = ticketData.totalReserved;
    seatInfo.innerText = ticketData.selectedSeat;
    payAmount.innerText = ticketData.totalAmount;
}
    // 캡쳐

	

</script>

</head>



<body>
    <div id="ticketPopup">
        <div><span>예매완료</span></div>
        <div id="ticket">
            <div id="movieImg">
            	<img src="http://file.koreafilm.or.kr/thm/02/00/01/14/tn_DPF000702.jpg" alt="영화이미지" id="imgUrl">
            </div>
            <div id="ticketInfo">
            	<table>
                    <tbody>
                        <tr>
                            <th>영화</th>
                            <td id="movieName">
<!--                             	영화제목 -->
                            </td>
                        </tr>
                        <tr>
                            <th>상영관</th>
                            <td id="theaterNo">
<!--                             	상영관 번호 -->
                            </td>
                        </tr>
                        <tr>
                            <th>일시</th>
                            <td id="screeningDate">
<!--                             	상영 날짜,시간 -->
                            </td>
                        </tr>
                        <tr>
                            <th>인원</th>
                            <td id="reservedInfo">
<!--                             	예매인원 -->
                            </td>
                        </tr>
                        <tr>
                            <th>좌석</th>
                            <td id="seatInfo">
<!--                             	예매좌석 -->
                            </td>
                        </tr>
                        <tr>
                            <th>결제금액</th>
                            <td id="payAmount">
<!--                             	최종 결제한 금액 -->
                            </td>
                        </tr>
                        <tr>
                            <th>결제수단</th>
                            <td id="payMethod">카카오페이</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div id="logo">
        	<img src="../resources/image/ticketing/flexLogo.png" alt="디스플레이플렉스">
        </div>
    </div>
</body>
</html>