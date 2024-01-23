window.onload = () => {

	
	function handleClick(event){
		
	}
	
	const trArr = document.querySelectorAll("#tab > tr");
	for(let i = 0 ; i < trArr.length; ++i){
		trArr[i].addEventListener('click' , () => {

		const no = trArr[i].children[0].innerText;
		const currentPage = document.querySelector('#paging span').innerText;
		// alert('/cinema/serviceCenter/inquiryDetail?onetooneNo=' + no + '&currPage=<%= pvo.getCurrentPage() %>');
		location.href = '/cinema/serviceCenter/inquiryDetail?onetooneNo=' + no + '&currPage=' + currentPage;	
		});
	}
}







function getQueryStringParameter(name) {
	    const urlParams = new URLSearchParams(window.location.search);
	    return urlParams.get(name);
	}
	
	// 사용 예시
	
	
	
	
