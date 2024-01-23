/**
 * 
 */

let currentPage = 1;

document.addEventListener("DOMContentLoaded" , () => {
	showReviews();
});

const ratingStars = [...document.getElementsByClassName("rating__star")];

function executeRating(stars) {
  const starClassActive = "rating__star fas fa-star";
  const starClassInactive = "rating__star far fa-star";
  const starsLength = stars.length;
  let i;

  stars.map((star) => {
    star.onclick = () => {
      i = stars.indexOf(star);
	  const ratingValue = document.getElementById("rating-value");
	  ratingValue.value = i+1;
      if (star.className===starClassInactive) {
        for (i; i >= 0; --i) stars[i].className = starClassActive;
      } else {
        for (i; i < starsLength; ++i) stars[i].className = starClassInactive;
      }
    };
  });
}

executeRating(ratingStars);

//현재 페이지에서 영화 이름 가져온 후 해당 영화에 대한 리뷰값 가져오기?
function getReviews(page) {
	
    function getQueryStringParameter(name) {
	    const urlParams = new URLSearchParams(window.location.search);
	    return urlParams.get(name);
	}
	
	// 사용 예시
	const paramNameValue = getQueryStringParameter('movieNo');
	
	return fetch(`http://localhost:9002/cinema/movie/review/list?movieNo=${paramNameValue}&pno=${page}`)
	.then((res) => res.json())
	.then((data) => data)
	.catch((err) => {
		console.log(err);
	});
}

//받은 데이터 화면에 뿌리기
function renderData(data) {
	console.log('data', data);
      if(data.length === 0) {
		  const showMore = document.getElementById('show-more');
		  showMore.remove();
		   const endSpan = document.createElement('span');
		   endSpan.classList.add('mt-3');
		   endSpan.classList.add('text-center');
		   endSpan.innerHTML = '불러올 리뷰가 없습니다';
		   const buttonDiv =  document.getElementById('button-container');
		   buttonDiv.appendChild(endSpan);
	  }else {
	      const contentDiv = document.getElementById('review-container');
	      for(let index = 0; index < data.length; index++) {
			  let item = data[index];
			  
			  const reviewDiv = document.createElement('div');
			  reviewDiv.classList.add('p-3');
			  reviewDiv.classList.add('mt-3');
			  reviewDiv.classList.add('bg-f0f2f2');
		        const firstDiv = document.createElement('div');
		       	firstDiv.classList.add('p-2');
		       	
		       	 const reviewNoSpan = document.createElement('span');
		        reviewNoSpan.classList.add('m-1');
		        reviewNoSpan.innerHTML = '리뷰번호: '+ item.reviewNo;
		        firstDiv.appendChild(reviewNoSpan);
		        
		        const memberNickSpan = document.createElement('span');
		        memberNickSpan.classList.add('m-1');
		        memberNickSpan.innerHTML = '<strong>'+ item.memberNick +'</strong>';
		        firstDiv.appendChild(memberNickSpan);
		        
		        const writeDateSpan = document.createElement('span');
		        writeDateSpan.classList.add('m-1');
		        writeDateSpan.innerHTML = '작성일자: ' + item.writeDate;
		        firstDiv.appendChild(writeDateSpan);
		        
		        const rateSpan = document.createElement('span');
		        rateSpan.classList.add('m-1');
		        rateSpan.innerHTML = '<i class="rating__star fas fa-star"></i> '+ item.rate;
		        firstDiv.appendChild(rateSpan);
		        
		        if(item.ableToWatch) {
					const deleteTag = document.createElement('button');
					deleteTag.classList.add('btn');
					deleteTag.classList.add('btn-link');
					deleteTag.classList.add('text-decoration-none');
					deleteTag.classList.add('text-black');
					deleteTag.classList.add('m-1');
					deleteTag.innerHTML = '삭제';
					deleteTag.addEventListener("click", () => {
						location.href = `http://localhost:9002/cinema/movie/review/delete?reviewNo=${item.reviewNo}`
					})
					firstDiv.appendChild(deleteTag);
				}
		        
		        reviewDiv.appendChild(firstDiv);
		        
		        
		        const secondDiv = document.createElement('div');
		        secondDiv.classList.add('p-2');

		       	
		       	const contentSpan = document.createElement('span');
		        contentSpan.classList.add('m-1');
		        contentSpan.innerHTML = item.content;
		        secondDiv.appendChild(contentSpan);
		        
		        reviewDiv.appendChild(secondDiv);
		        contentDiv.appendChild(reviewDiv);
		  }
	  }
}

const showReviews = () => {
	console.log(currentPage);
		getReviews(currentPage).then((res) => {
			currentPage++;
			renderData(res);
		});
}

const checkContent =()=> {
	
	const ratingValue = document.getElementById('rating-value');
	if(ratingValue.value == 0) {
		alert('별점은 1점부터 넣을 수 있습니다.');
		return false;
	}
	
	const reviewContent = document.getElementById('review-content');
	if(reviewContent.value.length === 0) {
		alert('리뷰 내용을 입력해주세요');
		reviewContent.focus();
		return false;
	} else {
		reviewContent.innerText = reviewContent.innerText.replace(/\\/g, "\\\\");
	}
	
	const check = confirm('리뷰를 등록하시겠습니까?');
	return check;
}