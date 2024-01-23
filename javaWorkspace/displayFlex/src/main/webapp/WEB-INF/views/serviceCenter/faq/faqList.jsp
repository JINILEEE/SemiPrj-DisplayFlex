<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="displayFlex.serviceCenter.faq.vo.CategoryVo"%>
<%@page import="displayFlex.util.page.vo.PageVo"%>
<%@page import="displayFlex.serviceCenter.faq.vo.FaqVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%
    	List<FaqVo> faqVoList = (List<FaqVo>) request.getAttribute("faqVoList");
     	PageVo pvo = (PageVo)request.getAttribute("pvo");
     	List<CategoryVo> categoryList = (List<CategoryVo>) request.getAttribute("categoryList");
     	Map<String, String> searchMap = (Map<String, String>)request.getAttribute("searchMap");
     	String x = (String) session.getAttribute("alertMsg");
    	session.removeAttribute("alertMsg");
     %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/cinema/resources/css/serviceCenter/faq/faqList.css">
<script type="text/javascript" src="/cinema/resources/js/serviceCenter/faq.js"></script>
</head>
<body>
 		

	<%@ include file="/WEB-INF/views/common/header.jsp" %>

	<c:set var="msg" value="<%= x %>" />
	<c:if test="${not empty msg}">
		 <script>
		        alert('<%= x %>');
		</script>
	</c:if>
	
    <main>
        <div id="contents">
            <div id="title_top">
                <h1>고객센터</h1>
                <c:if test="${loginMember.adminYn eq 'Y'}">
	                <button onclick="location.href='/cinema/admin/faqAdd'">등록</button>
                </c:if>
            </div>
            <div id="tab_tit">
                <nav>
                    <div id="top_tab1">
                        <a href="/cinema/serviceCenter/faqList?category=영화관이용"">FAQ</a>
                    </div>
                    <div id="top_tab2">
                        <a href="/cinema/serviceCenter/noticeList">공지사항</a>
                    </div>
                    <div id="top_tab3">
                        <a href="/cinema/serviceCenter/inquiryWrite">1:1 문의</a>
                    </div>
                    <div id="top_tab4">
                        <a href="/cinema/serviceCenter/recommendList">상영요청</a>
                    </div>
                </nav>
            </div>
            <div id="tab_con">
                	<div id="faq_icon_wrap">
	                    <button id="icon_01">
	                        <img src="/cinema/resources/image/faqIcon/cinema_2yong.png" alt="영화관 이용">
	                        영화관이용
	                    </button>
	                    <button id="icon_02">
	                        <img src="/cinema/resources/image/faqIcon/member.png" alt="사람 아이콘">
	                        회원
	                    </button>
	                    <button id="icon_03">
	                        <img src="/cinema/resources/image/faqIcon/star.png" alt="별 아이콘">
	                        등급
	                    </button>
	                    <button id="icon_04">
	                        <img src="/cinema/resources/image/faqIcon/ticket.png" alt="티켓 아이콘">
							쿠폰
	                    </button>
	                    <button id="icon_05">
	                        <img src="/cinema/resources/image/faqIcon/popcorn.png" alt="팝콘 아이콘">
							스토어
	                    </button>
	                    <button id="icon_06">
	                        <img src="/cinema/resources/image/faqIcon/ribbon.png" alt="리본 아이콘">
							이벤트
	                    </button>
	                </div>
                <fieldset id="search_wrap">
                	<form action="/cinema/serviceCenter/faqSearch" method="get">
	                    <input type="text" name="searchValue" placeholder="검색어를 입력해주세요." id="seachKeyword">
	                    <input type="submit" name="searchValue id="btn_col1" value="검색">
	                    <div id="help_wrap" style="text-align: left;">
	                        <p>더 궁금한 점이 있거나, 이미 문의한 내용과 답변을 확인하려면?</p>
	                        <a href="/cinema/serviceCenter/inquiryWrite" style="font-weight: bold; color: #000000;">1:1문의 바로가기</a>
	                    </div>
	                </form>
                </fieldset>
                <div id="acc">
                    <table id="tb_acc_wrap"
                        summary="FAQ 표입니다. 구분, 질문 순서로 행이 구성되어 있습니다." >
                        <colgroup>
                            <col style="width: 20%;">
                            <col style="width: auto">
                            <col style="width: 20%;">
                        </colgroup>
                        <thead>
                            <tr>
                                <th id="thead1">번호</th>
                                <th id="thread2">제목</th>
                                <th id="thread3">등록일</th>
                            </tr>
                        </thead>
                        <tbody id="tab">
                        <% for(FaqVo vo : faqVoList){ %>
                        	<tr>
                        		<td><%= vo.getFaqNo() %></td>
                        		<td><%= vo.getTitle() %></td>
                        		<td><%= vo.getEnrollDate() %></td>
                        	</tr>
                        <% } %>
                        </tbody>
                    </table>
                    <div id="paging">
                    	<% if(pvo.getStartPage() != 1){ %>
                    		<a href="/cinema/serviceCenter/faqList?category=영화관이용&pno=<%= pvo.getStartPage()-1 %>">이전</a>
                    	<% } %>
                    	
                    	<% for(int i = pvo.getStartPage() ; i <= pvo.getEndPage(); i++){ %>
							<% if( i == pvo.getCurrentPage() ){ %>
								<span><%= i %></span>
							<% } else { %>
								<a href="/cinema/serviceCenter/faqList?category=영화관이용&pno=<%= i %>"><%= i %></a>
							<% } %>
						<% } %>
						
						<% if( pvo.getEndPage() != pvo.getMaxPage() ){ %>
							<a href="/cinema/serviceCenter/faqList?category=영화관이용&pno=<%= pvo.getEndPage()+1 %>">다음</a>	
						<% } %>
                    	
                        
                    </div>
                </div>

            </div>

        </div>
    </main>
	
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
	
	<!-- faqList.jsp -->
    <script>
	    function clickCategory(categoryNo) {
	        // 추가 구현이 필요한 경우 함수 내용을 작성하세요.
	    }
	
	    <% if(searchMap != null) { %>
	    function setSearchArea() {
	        // 인풋태그 셋팅
	        const searchValueTag = document.querySelector("#search_wrap input[name=searchValue]");
	        searchValueTag.value = "<%= searchMap.get("searchValue") %>";
	    }
	    setSearchArea();
	
	    function setPageArea() {
	        const aTagArr = document.querySelectorAll("#paging a");
	        for (let i = 0; i < aTagArr.length; ++i) {
	            aTagArr[i].href = aTagArr[i].href.replace("list", "search");
	            aTagArr[i].href += "&searchValue=<%= searchMap.get("searchValue") %>";
	        }
	    }
	    setPageArea();
	    <% } %>
	    
	    
	    
	</script>


	

</body>
</html>
