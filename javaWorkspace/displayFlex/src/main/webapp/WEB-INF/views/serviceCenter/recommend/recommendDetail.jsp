<%@page import="displayFlex.serviceCenter.recommend.vo.RecommendVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	RecommendVo vo = (RecommendVo) request.getAttribute("vo");
    	String currPage = (String)request.getAttribute("currPage");
    	if(currPage == null){
    		currPage = "1";
    	}
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/cinema/resources/css/serviceCenter/recommend/recommendDetail.css">
<script defer type="text/javascript" src="/cinema/resources/js/serviceCenter/recommend.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>

    <main>
        <div id="contents">
            <div id="title_top">
                <h1>고객센터</h1>
                <c:if test="${not empty loginMember}"> 
					<button onclick="location.href='/cinema/serviceCenter/recommendEdit?recommendMvNo=<%= vo.getRecommendMvNo() %>'">수정</button>
	                <button onclick="location.href='/cinema/serviceCenter/recommendDelete?recommendMvNo=<%= vo.getRecommendMvNo() %>'">삭제</button>
                </c:if> 
            </div>
            <div id="tab_tit">
                <nav>
                    <a href="/cinema/serviceCenter/faqList">FAQ</a>
                    <a href="/cinema/serviceCenter/noticeList">공지사항</a>
                    <a href="/cinema/serviceCenter/inquiryWrite">1:1 문의</a>
                    <a href="/cinema/serviceCenter/recommendList">상영요청</a>
                </nav>
            </div>
            <div id="tab_con">
                <div id="tab_wrap inner">
                    <!-- <h2></h2> -->
                    <table id="tb1_dtl" summary="상영요청글 상세 내용에 대한 표">
                        <colgroup>
                            <col style="width: auto;">
                        </colgroup>
                        <thead>
                            <tr>
                                <th><%= vo.getTitle() %></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr id="tb_info">
                                <td>
                                    <span class="tit">번호</span>
                                    <span class="mid">|</span>
                                    <span class="cont"><%= vo.getRecommendMvNo() %></span>
                                    <span class="tit">작성자</span>
                                    <span class="mid">|</span>
                                    <span class="cont"><%= vo.getWriterNick() %></span>
                                    <span class="tit">추천수</span>
                                    <span class="mid">|</span>
                                    <span class="cont"><%= vo.getRecommendCount() %></span>
                                    <span class="tit">조회수</span>
                                    <span class="mid">|</span>
                                    <span class="cont"><%= vo.getHit() %></span>
                                    <span class="tit">등록일</span>
                                    <span class="mid">|</span>
                                    <span class="cont"><%= vo.getEnrollDate() %></span>
                                    <span class="tit">수정일</span>
                                    <span class="mid">|</span>
                                    <span class="cont"><%= vo.getModifyDate() %></span>
                                    
                                </td>
                            </tr>
                            <tr id="tb_content">
                                <td colspan="2">
                                    <div id="noticeContents">
                                        <p>
                                            <%= vo.getContent() %>
                                        </p>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div id="list_btn">
                        <a href="/cinema/serviceCenter/recommendList?pno=<%= currPage %>">목록</a>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>

	
</body>
</html>