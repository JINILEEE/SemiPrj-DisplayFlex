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
	%>

<link rel="stylesheet" href="/cinema/resources/css/serviceCenter/faq/faqListdata.css">

	<table id="tb_acc_wrap">
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
		<a onclick="aa(<%= pvo.getStartPage()-1 %>);">이전</a>
		<% } %>
		
		<% for(int i = pvo.getStartPage() ; i <= pvo.getEndPage(); i++){ %>
		<% if( i == pvo.getCurrentPage() ){ %>
		<span><%= i %></span>
		<% } else { %>
		<a onclick="aa(<%= i %>);"><%= i %></a>
		<% } %>
		<% } %>
		
		<% if( pvo.getEndPage() != pvo.getMaxPage() ){ %>
		<a onclick="aa(<%= pvo.getEndPage()+1 %>);">다음</a>	
		<% } %>
	</div>
