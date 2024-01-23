<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>VIP 혜택</title>

    <style>
        body {
            margin: 0; 
        }

        .vip_container {
            width: 100%; 
            margin: auto;
        }

        .white_background {
            background-color: white;
            padding: 20px;
            text-align: center;
        }

        #vip_hr_top,
        #vip_hr_bottom {
            border-top: 4px solid black;
            width: 100%; 
        }

        #vip_top {
           float: left; 
           font-size: 30px; 
           font-weight: 400;
        }

        #vip_content {
            float: right;
            width: 50%;
            padding: 10px;
            font-size: 30px; 
            text-align: left; 
        }

        #vip_j {
            font-size: larger;
            color: grey;
        }

        #vip_img img {
            max-width: 30%;
            height: auto;
        }
        .dd{
            width: 80em;
            height: 60em;
            margin: auto;
        }
    </style>
</head>
<body>
   
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <div class="dd"> 
        <div class="vip_container">
            <div class="white_background">
                <hr id="vip_hr_top">
                <h1 id="vip_top">&nbsp;&nbsp;&nbsp;VIP 혜택</h1>
                <hr id="vip_hr_bottom">
            </div>
        </div>

        <div class="white_background">
            <div id="vip_content">
                <br><br>
                - 생일 축하 쿠폰 발급(무료 영화 관람권)
                <br><br>
                - 생일 축하 쿠폰 발급(스위트 콤보 쿠폰)
                <br><br>
                - 월 1회 영화 반값 할인 쿠폰 발급
                <br><br>
                - VIP 시사회 우선 초대권 혜택
                <br><br>     
            </div>
        </div>

        <div class="white_background">
            <div id="vip_img">
                <h1>
                    <img src="/cinema/resources/image/vip/vip.png" alt="vip이미지">
                </h1>
                <p id="vip_j">* 연 12회 이상 관람시 VIP혜택 적용 가능</p>
            </div>
        </div>
    </div> 
</body>
</html>
