/**
 * 
 */

window.onload = () => {
	const buttonArr = document.querySelectorAll('#smallMenu button');
	
	for (let index = 0; index < buttonArr.length ; index++) {
		const el = buttonArr[index];
		el.addEventListener("click", () => {
			console.log(el.innerText);
			console.log(el);
		fetch(`http://localhost:9002/cinema/store/menu?category=${el.innerText}`)
		.then( ( resp )=>{
			console.log(resp);
			return resp.text();
		})
		.then( ( data )=>{ 
			const itemPhoto = document.querySelector('.itemPhoto');
			itemPhoto.innerHTML = data;
			
			formatPrices();
			} )
		.catch( ()=>{ alert("리스트 불러오기 실패..."); });
		})
	}
	// 값 출력문 나오는 것 ","와 "원" 추가하기!
    function formatPrices() {
        const priceElements = document.querySelectorAll('#third b');

        priceElements.forEach((element) => {
			
			// 숫자 아닌것 지우기
            const price = element.innerText.replace(/\D/g, ''); 
            
            const formattedPrice = Number(price).toLocaleString() + "원";
            element.innerText = formattedPrice;
        });
    }

    // 페이지에서 가격 초기화를 위한 호출
    formatPrices();
};
























