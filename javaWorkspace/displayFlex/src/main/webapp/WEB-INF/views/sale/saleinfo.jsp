<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>우대사항</title>
<link rel="stylesheet" href="/cinema/resources/css/sale/saleinfo.css">

<style>
    .sale_container {
        width: 90em;
        height: 40em;
        background-color: white;
        margin: auto;
    }

    
    

    #sale_child {
        border: 1px solid white;
        float: left;
        width: 33%;
        padding: auto;
        text-align: center;
        font-size: xx-large;
    }

    #sale_old {
        border: 1px solid white;
        float: left;
        width: 33%;
        padding: auto;
        text-align: center;
        font-size: xx-large;
    }


    #sale_jang {
        border: 1px solid white;
        float: left;
        width: 33%;
        padding: auto;
        text-align: center;
        font-size: xx-large;

        
   

    }
    


    #jul1 {
        border-top: 4px solid black; 
    }

    #sale_chil img,
    #sale_old img,
    #sale_jang img {
        display: block;
        margin: 0 auto;
    }

    #id {
        margin-bottom: 30%

    }

       
       

   
</style>

</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    
    <div id="oo">
    <div class="sale_container">
        <hr id="jul1">
        <h1 style="font-size: 30px; "> &nbsp;&nbsp;&nbsp;&nbsp; <b>우대할인혜택</b></h1>
        <hr>
        <div id="sale_child">
            <br>
            <img src="/cinema/resources/image/sale/child.png" alt="어린이할인"><br><br>
            <br>
            <div><b>어린이할인</b></div> <br>

            <div>
                <div id="salee">미취학 아동 2000원 할인</div>
                <div style="color: gray;font-size: 15px;" ><br><br>
                    -만 4세 ~ 만12세:미취학 아동 ~ 초등학생(의료보험증, 주민등록 초/등본 확인)<br><br>
                    -48개월 미만의 경우 증빙원(예:의료보험증, 주민등록 초/등본 등) 지참 시에만 무료 관람 가능
                </div>
            </div>
        </div>
        <div id="sale_old">
            <br>
            <img src="/cinema/resources/image/sale/old.png" alt="경로할인"><br><br>
            
            <div><b>경로할인</b></div> <br>

            <div>
                <div id="salee">만 65세 이상 1000원 할인</div>   
                <div style="color: gray;font-size: 15px; "><br><br>
                    - 만 65세 이상 본인에 한함<br><br>
                    - 1958년 이전 출생자(신분증 미소지시 현금 차액 지불)
                </div>
            </div>
        </div>
        <div id="sale_jang">
            <br>
            <img src="/cinema/resources/image/sale/jang.png" alt="장애인할인"><br><br>
            
            <div><b>장애인할인</b></div> <br>

            <div>
                <div id="salee">장애인 1000원 할인</div>
                <div style="color: gray;font-size: 15px;" ><br><br>
                    - 현장에서 복지카드를 소지한 장애인<br><br>
                    - 1급 ~ 3급까지 동반 1인까지 적용 / 4~6급은 본인에 한함
                </div>
            </div>
        </div>
    </div>
    </div>
    <!-- 버건디 색깔 - #ad5050 -->
</body>
</html>
