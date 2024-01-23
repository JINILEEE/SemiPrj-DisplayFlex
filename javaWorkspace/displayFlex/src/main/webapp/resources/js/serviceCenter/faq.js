window.onload = () => {
	

	const trArr = document.querySelectorAll("#tab > tr");
	for(let i = 0 ; i < trArr.length; ++i){
		trArr[i].addEventListener('click' ,() => {
			
			const currentPage = document.querySelector('#paging > span').innerText;
			console.log(currentPage);
			const faqNo = trArr[i].children[0].innerText;
			location.href = '/cinema/serviceCenter/faqDetail?faqNo=' + faqNo + '&currPage='+ currentPage;	
		});
	}
	
	const buttonArr = document.querySelectorAll('#faq_icon_wrap > button');
	console.log(buttonArr);
	
	buttonArr.forEach(el => {
		el.addEventListener("click", () => {
		    const type = el.innerText;
		    localStorage.setItem("type", type);
		    fetch(`/cinema/serviceCenter/faqListdata?category=${type}&pno=1`)
		    .then((res) => res.text())
		    .then((data) => {
		        //리스트 동적 생성
		        console.log(data);
		        const accDiv = document.getElementById('acc');
		        accDiv.innerHTML = data;
		        
		        
	        	const trArr = document.querySelectorAll("#tab > tr");
				for(let i = 0 ; i < trArr.length; ++i){
					trArr[i].addEventListener('click' ,() => {
						
						const currentPage = document.querySelector('#paging > span').innerText;
						console.log(currentPage);
						const faqNo = trArr[i].children[0].innerText;
						location.href = '/cinema/serviceCenter/faqDetail?faqNo=' + faqNo + '&currPage='+ currentPage;	
					});
				}
		        
		    });
		});
	});
	
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

	
}

	
	
	

function aa(page) {
	console.log(page)
	function getQueryStringParameter(name) {
	    const urlParams = new URLSearchParams(window.location.search);
	    return urlParams.get(name);
	}
	const category = localStorage.getItem("type");
	const paramNameValue = getQueryStringParameter('category');
		 const aTagArr = document.querySelectorAll('#paging > a');
			aTagArr.forEach(el => {
				fetch(`/cinema/serviceCenter/faqListdata?category=${category}&pno=${page}`)
			    .then((res) => res.text())
			    .then((data) => {
			        //리스트 동적 생성
			        console.log(data);
			        const accDiv = document.getElementById('acc');
			        accDiv.innerHTML = data;
			    });
			})
	}
	












