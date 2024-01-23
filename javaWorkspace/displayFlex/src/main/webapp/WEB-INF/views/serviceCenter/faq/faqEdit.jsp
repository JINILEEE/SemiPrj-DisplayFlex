<%@page import="displayFlex.serviceCenter.faq.vo.FaqVo"%>
<%@page import="displayFlex.serviceCenter.faq.vo.CategoryVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% 
    	FaqVo vo = (FaqVo) request.getAttribute("vo");
    	List<CategoryVo> categoryVoList = (List<CategoryVo>) request.getAttribute("categoryVoList"); 
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/cinema/resources/css/serviceCenter/faq/faqAdd.css">

</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>

	<form action="/cinema/admin/faqEdit" method="post">
	    <main>
	        <div id="contents">
	            <div id="title_top">
	                <h1>고객센터</h1>
	            </div>
	            <div id="tab_tit">
	                <nav>
	                    <a href="/cinema/serviceCenter/faqList">FAQ</a>
	                    <a href="/cinema/serviceCenter/noticeList">공지사항</a>
	                    <a href="/cinema/serviceCenter/inquiryWrite">1:1 문의</a>
	                    <a href="/cinema/serviceCenter/recommendList">상영요청</a>
	                </nav>
	            </div>
	            <div id="con_tit ty1">
	                <div id="faq_cont">
	                    <div>
	                        <h2  class="tit">FAQ 등록</h2>
	                    </div>
	                    <div>
	                        <h3>* 필수입력</h3>
	                    </div>
	                </div>
	                <table id="tbl_form" summary="문의내용작성 테이블">
	                    <colgroup>
	                        <col style="width: 15%;">
	                        <col style="width: auto;">
	                    </colgroup>
	                    <tbody>
	                        <tr>
	                            <th scope="row" class="req">
	                                분류
	                            </th>
	                            <td>
	                                <select name="category" id="iDdivisionCode">
	                                    
	                                    <% for(CategoryVo categoryVo : categoryVoList) { %>
											<option value="<%= categoryVo.getFaqCategoryNo() %>"><%= categoryVo.getCategoryName() %></option>
										<% } %>
	                                    
	                                    <!-- <option value="0">분류선택</option>
	                                    <option value="1">영화관 이용</option>
	                                    <option value="2">회원</option>
	                                    <option value="3">등급</option>
	                                    <option value="4">쿠폰</option>
	                                    <option value="5">스토어</option>
	                                    <option value="6">이벤트</option> -->
	                                </select>
	                            </td>
	                        </tr>
	                        <tr>
	                            <th scope="row" class="req">
	                                제목
	                            </th>
	                            <td>
	                                <div class="bx_textarea">
	                                    <input type="text" name="title" class="ty2" placeholder="제목을 입력해주세요" id="iDtitle" value="<%= vo.getTitle() %>">
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
	                                    <textarea name="content" class="ty2" id="iDcontents" cols="10" rows="10" title="문의내용을 입력해주세요">
	                                    	<%= vo.getContent() %>
	                                    </textarea> 
	                                    <br>
	                                    <div class="txt_red txt_color01">
	                                        <img src="/cinema/resources/image/faqIcon/caution_.png" alt="주의 아이콘" width="14px">
	                                        현재 페이지 내 '클릭/타이핑' 없이 머무르는 경우,
	                                        <br>
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
	                    <input class="submit" type="submit" value="수정">
	                </div>
	            </div>
	        </div>
    	</main>
    	 <input type="hidden" name="faqNo" value="<%= vo.getFaqNo() %>">
	</form>
	
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
	
	<script>
	
		function setOptionSelected() {
		    const optionTag = document.querySeleterAll("#iDdivisionCode option");
		    for(let i = 0; i < optionTagArr.length; i++){
		        const optionTag = optionTagArr[i];
		        if(optionTag.value == <%= vo.getFaqCategoryNo() %>) {
		            optionTagArr[].selected = true;
		        }
		    }
		}

	setOptionSelected();
	</script>

</body>
</html>