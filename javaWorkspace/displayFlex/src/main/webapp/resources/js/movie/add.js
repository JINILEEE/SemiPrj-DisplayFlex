 const getMovieList = async (value) => {
		
		const url = "http://kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json";
		const key = 'afac623fe11d16bd6e9bd798babc2d7b';
	
		const res = await fetch(`${url}?key=${key}&movieNm=${value}&itemPerPage=50`);
		const result = await res.json();
		let data = result.movieListResult.movieList.filter(el => el["movieNmEn"] !== "Package Screening");
		return data.sort((a, b) => Number(a["prdtYear"]) - Number(b["prdtYear"]));
	}

	const getMoviePoster = async (value) => {
		const url = 'http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp?collection=kmdb_new2&detail=y&listCount=100';
		const key = '9Z10BL3097X14RC40FSC';
		const res = await fetch(`${url}&title=${value}&ServiceKey=${key}`);
		
		const result = await res.json();
		return result.Data[0].Result;
		
	}

	const getDetailInfo = async (movieCd) => {
		const url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json";
		const key = 'afac623fe11d16bd6e9bd798babc2d7b';
	
		const res = await fetch(`${url}?key=${key}&movieCd=${movieCd}`);
		let result = await res.json();
		return result.movieInfoResult.movieInfo
	}

	const title = document.getElementById("title");
	title.addEventListener("input", async () => {
		const searchResult = document.getElementById("search-result");
		
		searchResult.innerHTML = '';
		
		let content = title.value;
	    //전체 조회 리스트 가져오기
		const searchList = await getMovieList(content);
		
		
		//검색 결과 리스트 보여주기
		for (let index = 0; index < searchList.length; index++) {

			const element = searchList[index];
			const itemTitle = document.createElement("span");
			itemTitle.innerText = element["movieNm"];
			const itemYear = document.createElement("span");
			itemYear.innerText = '(' + element["prdtYear"] + ')';
			
			const listItem = document.createElement("option");
			listItem.innerHTML = element["movieNm"] + ' (개봉년도: ' + element["prdtYear"] + ')';
			listItem.value = element["movieNm"]+'/'+element["movieCd"];
			
			searchResult.appendChild(listItem);
			
		}
	});
	
	title.addEventListener("change", async () => {
			const selectedMovie = title.value.split("/");

			title.value = selectedMovie[0];
			let searchMovie = await getMoviePoster(title.value);

			searchMovie = searchMovie?.filter(el => el.Codes.Code[0].CodeNo !== '' && el.Codes.Code[0].CodeNo === selectedMovie[1]).shift();
			console.log(searchMovie);
			
			//데이터가 존재하면 input 값에 값 채워넣기
			if(searchMovie !== undefined) {
				const movieCdInput = document.getElementById('movieCd');
				movieCdInput.value = selectedMovie[1];
				//감독
				const directorInput = document.getElementById("director");
				directorInput.value = searchMovie.directors.director[0].directorNm;

				//장르
				const genreInput = document.getElementById("genre");
				genreInput.value = searchMovie.genre;
				
				//포스터
				
				let posterSrc = searchMovie.posters;
				const posterUrlInput = document.getElementById("poster-url");
				const poster = document.getElementById("poster");
				posterUrlInput.value = '';
				let inputPoster = posterSrc !== '' ? posterSrc.split("|")[0] : ''
				poster.src = inputPoster;
				posterUrlInput.value = inputPoster;
				poster.alt = "포스터가 없습니다";
				
				
				//관람 등급
				const gradeArray = document.querySelectorAll("#screen-grade > option");
				
				for (index = 0; index < gradeArray.length ;index++) {
					if(gradeArray[index].innerHTML.substr(0,2) === searchMovie["rating"].substr(0,2)) {
						gradeArray[index].selected = true;
						break;
					}
				}
				
				const detailMovieInfo = await getDetailInfo(selectedMovie[1]);
				
				console.log(detailMovieInfo);
				
				//개봉일
				const releaseDateInput = document.getElementById("releaseDate");
				releaseDateInput.value = detailMovieInfo.openDt;
				//상영 시간
				const runningTimeInput = document.getElementById("runningTime");
				runningTimeInput.value = detailMovieInfo.showTm;
				
				//제작 국가
				const nationInput = document.getElementById("nation");
				nationInput.value = searchMovie.nation;
				
				//출연 배우
				const actorInput = document.getElementById("actor");
				actorInput.innerHTML =''; 
				let actors = detailMovieInfo["actors"];
				let length = actors.length > 9 ? 9 : actors.length;
				for(let index = 0; index < length; index++) {
					actorInput.innerHTML += actors[index].peopleNm + '(' + actors[index].cast +')';
					if(index !== length-1) {
						actorInput.innerHTML += ', ';
					}
				}
				
				const storyInput = document.getElementById("story");
				storyInput.innerText = searchMovie.plots.plot[0].plotText;
				
				//스틸 이미지 경로 담는 input file 태그 배열
				const stillImageFileArr = document.querySelectorAll("input[name=stillImage]");
				//스틸 이미지 보여주는 태그 배열
				const stillImageArr = document.querySelectorAll("img[name=still]");
				for(let index = 0; index < 3; index++) {
					stillImageArr[index].src = '';
					stillImageArr[index].alt = '';
					const srcInput = document.getElementById('still-url'+(index+1));
					srcInput.value = '';
					stillImageFileArr[index].disabled = false;
				}
			if(searchMovie.stlls !== "") {
				let stillSrcArr = searchMovie.stlls.split("|", 3);
				for(let index = 0; index < stillSrcArr.length; index++) {
					const srcInput = document.getElementById('still-url'+(index+1));
					stillImageArr[index].width = 300
					stillImageArr[index].src = stillSrcArr[index];
					srcInput.value = stillSrcArr[index];
					stillImageArr[index].alt = "스틸 이미지가 없습니다.";
					stillImageFileArr[index].disabled = true;
				}				
			}
				
			} else {
				alert('입력한 영화에 대한 정보가 없습니다.');
			}
		
		});
		//파일 선택시 value값 삭제
		const stillImgArr = document.getElementsByName('stillImage');
		for(let index = 0; index < stillImgArr.length; index++) {
			stillImgArr[index].addEventListener("change", () => {
				document.getElementById('still-url'+(index+1)).value= '';
			})
		}
		
		
		//값이 유효한지 확인
		const checkInputValue = () => {
			
			if(title.value.trim() == '') {
				alert('영화제목은 필수 입력값입니다');
				title.focus();
				return false;
			}
			
			const rateInput = document.getElementById('rate');
			if(rateInput.value == '') {
				alert('별점은 필수 입력값입니다');
				rateInput.focus();
				return false;
			}
			
			const check = confirm('영화를 등록하시겠습니까?');
			return check;
		}