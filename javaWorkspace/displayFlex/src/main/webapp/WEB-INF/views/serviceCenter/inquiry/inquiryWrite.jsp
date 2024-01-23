<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
	String x = (String) session.getAttribute("alertMsg");
	session.removeAttribute("alertMsg");
	%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/cinema/resources/css/serviceCenter/inquiry/inquiryWrite.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script defer type="text/javascript" src="/cinema/resources/js/serviceCenter/inquiry/inquiryWrite.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>

	<c:set var="msg"  value="<%= x %>" />
 	<c:if test="${not empty msg}">
 		<script>
	        alert('<%= x %> ');
		</script>
 	</c:if>


	<form action="/cinema/serviceCenter/inquiryWrite" method="post">
	    <main>
	        <div id="contents">
	            <div id="title_top">
	                <h1>고객센터</h1>
	            </div>
	            <div id="tab_tit">
	                <nav>
	                    <a href="/cinema/serviceCenter/faqList">FAQ</a>
	                    <a href="/cinema/serviceCenter/noticeList">공지사항</a>
	                    <c:choose>
		                    <c:when test="${loginMember.adminYn eq 'Y' }">
			                    <a href="/cinema/admin/serviceCenter/inquiryList?pno=1">1:1 문의</a>
		                    </c:when>
		                    <c:otherwise>
			                    <a href="/cinema/serviceCenter/inquiryWrite">1:1 문의</a>
		                    </c:otherwise>
	                    </c:choose>
	                    <a href="/cinema/serviceCenter/recommendList">상영요청</a>
	                </nav>
	            </div>
	            <div id="con_tit ty1">
	                <table id="tbl_form" summary="문의내용작성 테이블">
	                    <colgroup>
	                        <col style="width: 15%;">
	                        <col style="width: auto;">
	                    </colgroup>
	                    <div id="con_top">
	                        <div id="tit_qus">
	                            <img src="/cinema/resources/image/faqIcon/inquiry.png" alt="문의 이모지">
	                            <div class="list_tit">
	                                FAQ를 이용하시면 궁금증을 더 빠르게 해결하실 수 있습니다.
	                                <p>• 1:1 문의글 답변운영시간 10:00 ~ 17:30</p>
	                                <p>• 접수 후 48시간 안에 답변 드리겠습니다.</p>
	                            </div>
	                        </div>
	                    </div>
	                    <div id="context_type1">
	                        <dt class="tit">
	                            고객님의 문의에
	                            <span class="txt_red" style="padding: 0; font-size: 15.5px;">
	                                답변하는 직원은 고객 여러분의 가족 중 한 사람
	                            </span>
	                            일 수 있습니다
	                        </dt>
	                        <dd class="desc" style="margin: 0;">
	                            고객의 언어폭력(비하, 욕설, 반말, 성희롱 등)으로부터 직원을 보호하기 위해 관련 법에 따라 수사기관에 필요한 조치를 요구할 수 있으며, 형법에 의해 처벌 대상이 될 수 있습니다.
	                        </dd>
	                    </div>
	                    <tbody>
	                        <div id=inquiry_cont>
	                            <h2>문의내용</h2>
	                            <h3>* 필수입력</h3>
	                        </div>
	                        <tr>
	                            <th scope="row" class="req">
	                                제목
	                            </th>
	                            <td>
	                                <div class="bx_textarea">
	                                    <input type="text" name="title" class="ty2" placeholder="제목을 입력해주세요" title="문의내용 제목입력" id="iDtitle">
	                                    <span class="txt_count">
	                                        <em id="strongContentsCount_title">0</em>
	                                        /한글
	                                        <em>100</em>
	                                        자
	                                    </span>
	                                </div>
	                            </td>
	                        </tr>
	                        <tr>
	                            <th scope="row" class="req">
	                                내용
	                            </th>
	                            <td>
	                                <div class="bx_textarea">
	                                    <textarea type="text" name="content" class="ty2" id="iDcontents" cols="10" rows="10" title="문의내용을 입력해주세요" 
	                                    placeholder="내용에 개인정보(개인번호, 계좌번호, 주민번호)가 포함되지 않도록 유의하여 입력해주세요"></textarea>
	                                    <br>
	                                    <div class="txt_red txt_color01">
	                                        <img src="/cinema/resources/image/faqIcon/caution_.png" alt="주의 아이콘" width="14px">
	                                        현재 페이지 내 '클릭/타이핑' 없이 머무르는 경우,
	                                        <p style="margin: 0;">20분 후 접속이 자동 종료되며 작업한 내용은 저장이 되지 않습니다.</p>
	                                    </div>
	                                    <span class="txt_count">
	                                        <em id="strongContentsCount">0</em>
	                                        /한글
	                                        <em>4,000</em>
	                                        자
	                                    </span>
	                                </div>
	                            </td>
	                        </tr>
	                    </tbody>
	                </table>
	                <div id="btn_wrap">
	                    <input class="cancle" type="button" onclick="history.back();" value="취소">
	                    <input class="ok" type="submit" value="확인">
	                </div>
	            </div>
	        </div>
		</main>
	</form>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>