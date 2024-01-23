const trArr = document.querySelectorAll("#tab > tr");
	for(let i = 0 ; i < trArr.length; ++i){
		trArr[i].addEventListener('click' , handleClick);
	}

function handleClick(event){
	const tr = event.currentTarget;
	const recommendMvNo = tr.children[0].innerText;
	location.href = '/cinema/serviceCenter/recommendDetail?recommendMvNo=' + recommendMvNo + '&currPage=<%= pvo.getCurrentPage() %>';	
}


$(document).ready(function () {
	// 제목 입력 텍스트박스 이벤트 핸들러
    $("#iDtitle").on("input", function () {
        // 바이트수 세기
        var length = $(this).val().replace(/\s/g, "").replace(/\n/g, "").length; // 공백과 개행 문자 제거
        // 바이트수 표시
        $("#strongContentsCount_title").text(length);
        // 제목 최대 바이트수 체크
        if (length > 100) {
            alert("제목은 최대 100바이트까지 입력 가능합니다.");
            $(this).val($(this).val().substring(0, 100));
            return false;
        }
    });

    // 내용 입력 텍스트박스 이벤트 핸들러
    $("#iDcontents").on("input", function () {
        // 바이트수 세기
        var length = $(this).val().replace(/\s/g, "").replace(/\n/g, "").length; // 공백과 개행 문자 제거
        // 바이트수 표시
        $("#strongContentsCount").text(length);
        // 내용 최대 바이트수 체크
        if (length > 4000) {
            alert("내용은 최대 4000바이트까지 입력 가능합니다.");
            $(this).val($(this).val().substring(0, 4000));
            return false;
        }
    });
});
