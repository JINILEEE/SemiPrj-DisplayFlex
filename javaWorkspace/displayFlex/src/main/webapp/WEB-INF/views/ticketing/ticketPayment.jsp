<%@page import="displayFlex.ticketing.payment.vo.UserGradeVo"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="displayFlex.ticketing.payment.vo.SelectCouponVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<SelectCouponVo> couponList	= (List<SelectCouponVo>)request.getAttribute("couponList");
	UserGradeVo gradeVo = (UserGradeVo)request.getAttribute("userGradeVo");
	String gradeNo = gradeVo.getGradeNo();
	String previlegedYn = gradeVo.getPrevilegedYn();
	String age = gradeVo.getAge();
%>

<script>

window.onload = () => {
	const gradeNo = <%=gradeNo %>;
	const previlegedYn = "<%=previlegedYn %>";
	const age = <%=age%>;
	
	if(gradeNo !== 1){
		document.getElementById("discount_vip").disabled = true;
	}	
	
	if(age > 8){
		document.getElementById("discount_child").disabled = true;
	}

	if(age < 65){
		document.getElementById("discount_older").disabled = true;
	}

	if(previlegedYn === "N"){
		document.getElementById("discount_disabled").disabled = true;
	}
	
}

</script>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <script src="https://cdn.iamport.kr/v1/iamport.js"></script>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
    <script src="../resources/bootstrap-5.3.2-dist/js/bootstrap.js"></script>
    <link rel="stylesheet" href="../resources/css/ticketing/ticketPayment.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="../resources/bootstrap-5.3.2-dist/css/bootstrap.css">
    <script defer src="../resources/js/ticketing/ticketPayment.js"> </script>


    <title>예매페이지-결제</title>

</head>
<body>
            <%@ include file="/WEB-INF/views/common/header.jsp" %>

    <main>
        <div class="empty"></div>
        <div id="stepMenu_area">
            <div class="accordion" id="step-accordion">
                <div class="accordion-item">
                  <h2 class="accordion-header" id="headingOne">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                      <div class="step-name">
                        <p>STEP1</p>
                        <span>할인쿠폰</span>
                      </div>
                    </button>
                  </h2>
                  <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#step-accordion">
                    <div class="accordion-body">
                      <!-- 할인쿠폰 -->
                      <div id="step-coupon">
                        <p>FLEX 할인쿠폰</p>
                        <table id="couponList">
                            <thead>
                                <tr>
                                    <th>사용가능 쿠폰</th>
                                    <th>쿠폰 번호</th>
                                    <th>유효 기간</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<tr>
                            		<td><input type="radio" name="discount" value="null" checked="checked" id="noCoupon">  선택안함</td>
                            	</tr>
                                <% for(SelectCouponVo vo : couponList){%>
                                <tr>
                                    <td>
                                    	<input type="radio" name="discount" value="<%= vo.getRetainedNo() %>">
                                    	<%= vo.getName() %>    <%= vo.getDiscount() %>
                                    </td>
                                    <td><%= vo.getRetainedNo() %></td>
                                    <td><%= vo.getCouponEnddate() %></td>
                                </tr>
                                <% } %>
                            </tbody>

                        </table>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="accordion-item">
                  <h2 class="accordion-header" id="headingTwo">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                      <div class="step-name">
                        <p>STEP2</p>
                        <span>할인혜택</span>
                      </div>
                    </button>
                  </h2>
                  <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#step-accordion">
                    <div class="accordion-body">
                      <!-- 할인 혜택 -->
                      <div id="step-discount">
                        <div id="gradeDiscount">
                          <div><span>등급할인</span></div>

                          <span><input type="radio" name="discount" value="VIP 할인" id="discount_vip">VIP 할인</span>

                        </div>
                        <div id="specialDiscount">
                          <div><span>우대사항</span></div>
                          <span><input type="radio" name="discount" value="어린이 할인" id="discount_child">어린이</span>
                          <span><input type="radio" name="discount" value="경로 할인" id="discount_older">경로할인</span>
                          <span><input type="radio" name="discount" value="장애인 할인" id="discount_disabled">장애인</span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="accordion-item">
                  <h2 class="accordion-header" id="headingThree">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                      <div class="step-name">
                        <p>STEP3</p>
                        <span>결제수단</span>
                      </div>
                    </button>
                  </h2>
                  <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#step-accordion">
                    <div class="accordion-body">
                      <!-- 결제수단 -->
                      <div id="step-payment">
                        <div><h4>결제 방법</h4></div>
                        <div id="paymentMethod">
                          <div id="kakaoPay">
                          	<input type="radio" id="radioButton1" name="paymentGroup" checked="checked">
                          	<label for="radioButton1" class="custom-radio">
                          		<img src="../resources/image/ticketing/payment_icon_yellow_small.png" alt="카카오페이">
                          	</label>
                          </div>
                          <div>	
                          	<input type="radio" id="radioButton2" name="paymentGroup">
							<label for="radioButton2" class="custom-radio">결제ㅋㅋ</label>
                          </div>
                          <div>	
                          	<input type="radio" id="radioButton3" name="paymentGroup">
							<label for="radioButton3" class="custom-radio">결제ㅋㅋㅎㅎ</label>
                          </div>
                        </div>
                      	<div></div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
        </div>
        <div id="paymentInfo_area">
            <div id="paymentInfo">
                <div id="paymentAmount">
                    <div>
                        <div><span>결제 금액</span></div>
                        <div><span id="paymentAmount-value">0</span><span>원</span></div>
                    </div>
                </div>
                <div id="discount">
                    <div>
                        <div><span>할인 내역</span></div>
                        <div><span id="discountDetails">쿠폰 또는 혜택을 선택하세요</span></div>
                        <div><span id="discount-value">0</span><span>원</span></div>                            
                    </div>
                </div>
                <div id="totalPayment">
                    <div>
                        <div><span>최종 결제 금액</span></div>
<!--                         <div id="pay-description"> -->
<!-- 							뭘 넣지? -->
<!-- 						</div> -->
                        <div><span id="totalPayment-value">0</span><span>원</span></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="empty"></div>
    </main>

    <div id="selectInfo">
        <div id="ticket-posterImg">
			    <img src="../resources/image/ticketing/flexLogo.png" alt="포스터" id="posterImg">
		</div>
        <div id="ticket-movieName">
            <span id="movieInfo">
<!--             	영화이름 들어가는곳 -->
            </span>
        </div>
        <div id="ticket-movieInfo">
            <table>
                <tbody>
                    <tr>
                        <td>일시</td>
                        <td>
                        <span id="dateInfo">
<!--                          날짜 정보 들어가는곳 -->
                        </span>
                        <span id="timeInfo">
<!--                         		시간정보 들어가는곳 -->
                        </span></td>
                    </tr>
                    <tr>
                        <td>상영관</td>
                        <td><span id="theaterInfo">
<!--                         	상영관 번호 들어가는곳 -->
                        </span></td>
                    </tr>
                    <tr>
                        <td>인원</td>
                        <td id="reservedInfo">
<!--                         	몇명인지  -->
                        
                        </td>
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
                        <td id="seatInfo">
<!--                         	선택한 좌석 번호 -->
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div id="ticket-payInfo">
            <table>
                <tbody>
                    <tr>
                        <td></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div id="ticket-payButton">
            <button id="kakaopay" onclick="kakaoPay();">
                <i class="bi bi-arrow-right-circle-fill"></i>
                <span>결제하기</span>
            </button>
        </div>
    </div>

	<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>