<%@page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="displayFlex.serviceCenter.recommend.vo.RecommendVo"%>
<%@page import="displayFlex.util.page.vo.PageVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	List<RecommendVo> recommendVoList = (List<RecommendVo>) request.getAttribute("recommendVoList");
    	PageVo pvo = (PageVo)request.getAttribute("pvo");
    	Map<String, String> searchMap = (Map<String, String>)request.getAttribute("searchMap");
    	String x = (String) session.getAttribute("alertMsg");
    	session.removeAttribute("alertMsg");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/cinema/resources/css/serviceCenter/recommend/recommendList.css">
<script defer type="text/javascript" src="/cinema/resources/js/serviceCenter/recommend.js"></script>

</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>

    <c:set var="msg"  value="<%= x %>" />
 	<c:if test="${not empty msg}">
	<script>
		alert('<%= x %> ');
	</script>
 	</c:if>    

	<main>
        <div id="contents">
            <div id="title_top">
                <h1>고객센터</h1>
                <c:if test="${not empty loginMember and loginMember.adminYn eq 'N'}">
                	<button onclick="location.href='/cinema/serviceCenter/recommendWrite'">작성</button>
                </c:if>
            </div>
            <div id="tab_tit">
                <nav>
                    <a href="/cinema/serviceCenter/faqList?category=영화관이용">FAQ</a>
                    <a href="/cinema/serviceCenter/noticeList">공지사항</a>
                    <a href="/cinema/serviceCenter/inquiryWrite">1:1 문의</a>
                    <a href="/cinema/serviceCenter/recommendList">상영요청</a>
                </nav>
            </div>
            <fieldset id="search_wrap2">
            	<form action="/cinema/serviceCenter/recommendSearch" method="get">
	                <select name="searchType" class="ty3" title="검색조건 제목 선택" id="selectCondition1">
	                    <option value="title">제목</option>
	                    <option value="content">내용</option>
	                    <option value="titcon">제목+내용</option>
	                </select>
	                <input type="text" name="searchValue" placeholder="검색어를 입력해주세요." id="seachKeyword2">
	                <input type="submit" class="btn_col3" value="검색" onclick="location.href='/cinema/serviceCenter/recommendSearch?searchType=' + document.getElementById('selectCondition1').value + '&searchValue=' + document.getElementById('seachKeyword2').value;">
            	</form>
            </fieldset>
            <div id="acc3">
                <table id="tb_acc_wrap3"
                    summary="상영요청 표입니다. 구분, 질문 순서로 행이 구성되어 있습니다." >
                    <colgroup>
                        <col style="width: 20%;">
                        <col style="width: auto">
                        <col style="width: 20%;">
                    </colgroup>
                    <thead>
                        <tr>
                            <th id="thead1">번호</th>
                            <th id="thread2">제목</th>
                            <th id="thread3">작성자</th>
                            <th id="thread4">등록일</th>
                        </tr>
                    </thead>
                    <tbody id="tab">
						<% for(RecommendVo vo : recommendVoList){ %>
							<tr>
								<td><%= vo.getRecommendMvNo() %></td>
								<td><%= vo.getTitle() %></td>
								<td><%= vo.getWriterNick() %></td>
								<td><%= vo.getEnrollDate() %></td>
							</tr>
						<% } %>
                    </tbody>
                </table>
                <div id="paging">
                    <% if(pvo.getStartPage() != 1){ %>
                   		<a href="/cinema/serviceCenter/recommendList?pno=<%= pvo.getStartPage()-1 %>">이전</a>
                   	<% } %>
                   	
                   	<% for(int i = pvo.getStartPage() ; i <= pvo.getEndPage(); i++){ %>
						<% if( i == pvo.getCurrentPage() ){ %>
							<span><%= i %></span>
						<% } else { %>
							<a href="/cinema/serviceCenter/recommendList?pno=<%= i %>"><%= i %></a>
						<% } %>
					<% } %>
					
					<% if( pvo.getEndPage() != pvo.getMaxPage() ){ %>
						<a href="/cinema/serviceCenter/recommendList?pno=<%= pvo.getEndPage()+1 %>">다음</a>	
					<% } %>
                </div>
            </div>
            
        </div>
    </main>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
    
    <script>
	    <% if(searchMap != null){ %>
			function setSearchArea(){
				
				// 옵션태그 셋팅
				const optionTagArr = document.querySelectorAll("#search_wrap2 select option");
				const searchType = "<%= searchMap.get("searchType") %>";
				for(let i = 0; i < optionTagArr.length; ++i){
					if( optionTagArr[i].value === searchType ){
						optionTagArr[i].selected = true;
						break;
					}
				}
				
				// 인풋태그 셋팅
				const searchValueTag = document.querySelector("#search_wrap2 input[name=searchValue]");
				searchValueTag.value = "<%= searchMap.get("searchValue") %>";
				
			}
			setSearchArea();
			
			function setPageArea(){
				const aTagArr = document.querySelectorAll("#paging a");
				for(let i = 0 ; i < aTagArr.length; ++i){
					aTagArr[i].href = aTagArr[i].href.replace("list" , "search"); 
					aTagArr[i].href += "&searchType=<%= searchMap.get("searchType") %>";
					aTagArr[i].href += "&searchValue=<%= searchMap.get("searchValue") %>";
				}
			}
			setPageArea();
		<% } %>

		/* // 각 공지사항의 상세조회 페이지로 이동하는 함수
		function goToRecommendDetail(RecommendMvNo) {
			// 공지사항 상세조회 페이지 URL을 생성
			var url = "/cinema/serviceCenter/recommendDetail?noticeNo=" + RecommendMvNo;
			// 페이지 이동
			window.location.href = url; 
		} */
			
    </script>

</body>
</html>