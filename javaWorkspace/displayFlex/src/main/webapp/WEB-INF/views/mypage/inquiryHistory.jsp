<%@page import="java.util.Map"%>
<%@page import="displayFlex.mypage.vo.PageVo"%>
<%@page import="displayFlex.serviceCenter.inquiry.vo.InquiryVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%
    	List<InquiryVo> inquiryVoList = (List<InquiryVo>) request.getAttribute("inquiryVoList");
    	PageVo pvo = (PageVo)request.getAttribute("pvo");
        Map<String, String> searchMap = (Map<String, String>)request.getAttribute("searchMap");
 %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/cinema/resources/css/mypage/inquiryHistory.css">

</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <div class="container">
        <div class="main">
            <div class="main-first">나의 문의내역</div>
            <div class="main-second">1:1 문의</div>
                <form action="/cinema/mypage/inquiry/search" method="get">
                    <select name="searchType">
                        <option value="TITLE">문의제목</option>
                        <option value="RE_TITLE">답변제목</option>
                    </select>
                    <input class="main-text-t2" type="text" name="searchValue" placeholder="검색할 내용을 입력하세요">
					<input class="main-text-t3" type="submit" value="검색하기">
                </form>
        
            <div class="main-table">
                <div class="main-table-header">번호</div>
                <div class="main-table-header">회원번호</div>
                <div class="main-table-header">문의제목</div>
                <div class="main-table-header">답변제목</div>
                <div class="main-table-header">문의일자</div>
                <div class="main-table-header">답변일자</div>
            <% for(InquiryVo vo : inquiryVoList){ %>
                <div class="main-table-body"><%= vo.getOnetooneNo() %></div>            
                <div class="main-table-body"><%= vo.getMemberNo() %></div>            
                <div class="main-table-body"><%= vo.getTitle() %></div>            
                <div class="main-table-body"><%= vo.getReTitle() %></div>            
                <div class="main-table-body"><%= vo.getEnrollDate() %></div>            
                <div class="main-table-body"><%= vo.getReEnrollDate() %></div>            
            <% } %>   
            </div>

            <div class="page-area">
			
				<% if(pvo.getStartPage() != 1){ %>
					<a href="/cinema/mypage/inquiry?pno=<%= pvo.getStartPage()-1 %>">이전</a>
				<% } %>
				
				<% for(int i = pvo.getStartPage() ; i <= pvo.getEndPage(); i++){ %>
					
					<% if( i == pvo.getCurrentPage() ){ %>
						<span><%= i %></span>
					<% }else{ %>
						<a href="/cinema/mypage/inquiry?pno=<%= i %>"><%= i %></a>
					<% } %>
					
				<% } %>
				
				<% if( pvo.getEndPage() != pvo.getMaxPage() ){ %>
					<a href="/cinema/mypage/inquiry?pno=<%= pvo.getEndPage()+1 %>">다음</a>	
				<% } %>
			
			</div>

            <div class="main-bottom">
                <div class="main-bottom-icon"><img src="/cinema/resources/image/mypage/icon1.svg"></div>
                <div class="main-botton-content">
                    <div class="main-botton-content-t1">자주하시는 질문<a href="/cinema/serviceCenter/faqList"><input type="button" value="바로가기"></a></div>
                    <div class="main-botton-content-t2">고객님들께서 주로 하시는 질문에 대한 답변을
                        한곳에 모아두었습니다.</div>
                </div>
                <div class="main-bottom-icon"><img src="/cinema/resources/image/mypage/icon2.svg"></div>
                <div class="main-button-content"> 
                    <div class="main-botton-content-t1">고객의 말씀<a href="/cinema/admin/inquiryList"><input type="button" value="바로가기"></a></div>
                    <div class="main-botton-content-t2">불편사항과 문의사항을 남겨주시면 친절히
                        답변드리겠습니다.</div></div>
            </div>
		</div>
        </div>
    </div>
        <footer></footer>
</body>
</html>

<script>

const trArr = document.querySelectorAll("main > table > tbody > tr");
	for(let i = 0 ; i < trArr.length; ++i){
		trArr[i].addEventListener('click' , handleClick);
	}

	function handleClick(event){
		const tr = event.currentTarget;
		const no = tr.children[0].innerText;
		location.href = '/cinema/mypage/inquiry?onetooneNo=' + onetooneNo + '&currentPage=<%= pvo.getCurrentPage() %>';	
	}
	
	<% if(searchMap != null){ %>
		function setSearchArea(){
			
			// 옵션태그 셋팅
			const optionTagArr = document.querySelectorAll("form option");
			const searchType = "<%= searchMap.get("searchType") %>";
			for(let i = 0; i < optionTagArr.length; ++i){
				if( optionTagArr[i].value === searchType ){
					optionTagArr[i].selected = true;
					break;
				}
			}
			
			// 인풋태그 셋팅
			const searchValueTag = document.querySelector("form input[name=searchValue]");
			searchValueTag.value = "<%= searchMap.get("searchValue") %>";
			
		}
		setSearchArea();
		
		function setPageArea(){
			const aTagArr = document.querySelectorAll(".page-area a");
			for(let i = 0 ; i < aTagArr.length; ++i){
				aTagArr[i].href = aTagArr[i].href.replace("list" , "search"); 
				aTagArr[i].href += "&searchType=<%= searchMap.get("searchType") %>";
				aTagArr[i].href += "&searchValue=<%= searchMap.get("searchValue") %>";
			}
		}
		setPageArea();
	<% } %>

</script>