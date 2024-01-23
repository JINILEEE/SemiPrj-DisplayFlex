/**
 * 
 */

window.onload = () => {
	const element = document.querySelectorAll("div.result-container .element");
	element.forEach(el => {
		el.addEventListener("click", (e) => {
		const target = e.currentTarget;
		const movieNo = target.children[1].children[0].innerText;
		location.href = `http://localhost:9002/cinema/movie/detail?movieNo=${movieNo}`
	});
		
	})
}