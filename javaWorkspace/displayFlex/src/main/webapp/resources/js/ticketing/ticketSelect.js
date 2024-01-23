
/*---------------------------예매 선택 정보 */


let ticketData = {};
let seatData = [];

// 포스터 이미지 바꾸기
function changeMovieImage(){
	
	fetch("http://localhost:9002/cinema/ticket/select/image?movieNo=" + sessionStorage.getItem("movieNo"))
	.then( (resp) => { return resp.text() } ) 
	.then( (movieImgUrl) => { 
			console.log(movieImgUrl);
			
			const posterImg = document.getElementById("posterImg");
			ticketData.posterImg = movieImgUrl;
			posterImg.src = movieImgUrl;
			
	});
}

// 상영 날짜 정보
function printDateList(){
	
	fetch("http://localhost:9002/cinema/ticket/select/dateList?movieNo=" + sessionStorage.getItem("movieNo"))
	.then( (resp) => { return resp.json() } ) 
	.then( (voList) => { 

            const dateList = document.getElementById("dateList");

            dateList.innerHTML = "";

			if(voList.length == 0 ){
				const x = document.createElement("li");
				dateList.appendChild(x);
				x.innerText = "상영정보가 없습니다.";
			}
			const processedStartTimes = processVoList(voList);
			
			console.log(processedStartTimes);
			console.log(voList);
			sessionStorage.setItem("voList", voList);
			
            processedStartTimes.forEach((date, index) => {
                const listItem = createDateList(voList, date, index);
                dateList.appendChild(listItem);
            });
            
            
        })
}

function processVoList(voList) {
	
    const processedStartTimes = [];

    for (let i = 0; i < voList.length; i++) {
        const vo = voList[i];
        const startTime = vo.startTime;
        if (!processedStartTimes.includes(startTime)) {    
            processedStartTimes.push(startTime);
        }
    }
    return processedStartTimes;
}

// 날짜 li 생성
function createDateList(voList, date, index) {
	
    const listItem = document.createElement("li");
    listItem.className = "ticketingDate";

    const button = document.createElement("button");
    button.type = "button";
    button.onclick = function() {
        changeDateInfo(voList, date, index);
    };

    const span = document.createElement("span");
    span.className = "text";
    span.id = "movieDate" + index;
    span.textContent = date;

    button.appendChild(span);

    listItem.appendChild(button);


	
    return listItem;
}


// 시간 생성 (상영관별..?)
function printTimeList(){
 	fetch("http://localhost:9002/cinema/ticket/select/timeList?movieNo=" + sessionStorage.getItem("movieNo") +"&selectedDate="+ sessionStorage.getItem("selectedDate"))
	.then( (resp) => { return resp.json() } ) 
	.then( (theatersData) => { 
			console.log(theatersData);

            const theater1 = document.getElementById("theater1_time");
            const theater2 = document.getElementById("theater2_time");
            const theater3 = document.getElementById("theater3_time");
            const theater4 = document.getElementById("theater4_time");
            
			theater1.innerHTML = "";
			theater2.innerHTML = "";
			theater3.innerHTML = "";
			theater4.innerHTML = "";
			
			theatersData.forEach((list) => {
				switch(list.theaterNo){
					case '1' : 
						theater1.appendChild(createTimeDiv(list));
						break;
			        case '2' :
						theater2.appendChild(createTimeDiv(list));
						break;
			        case '3' :
						theater3.appendChild(createTimeDiv(list));
						break;
			        case '4' :	
			        	theater4.appendChild(createTimeDiv(list));	
			        	break;
				}
			})
        })
}

// 시간 div 생성
function createTimeDiv(list){
	const div = document.createElement("div");
	const button = document.createElement("button");
	button.type = "button";
	button.onclick = () => changeTimeInfo(list);
	button.className="selectTime";
	button.value= list.theaterNo + ":" +  list.startTime; 
	div.appendChild(button);
	button.textContent =  list.startTime;
	
	return div;
}

// 예약된 좌석 스타일 변경
function setSeat(list){
	
	const theaterNo = list.theaterNo;
	const screeningTimeNo = list.screeningTimeNo;
	
	fetch("http://localhost:9002/cinema/ticket/select/seatInfo?selectTheater=" + theaterNo + "," + screeningTimeNo )
	.then( (resp) => { return resp.json() } ) 
	.then((seatData) => {
		console.log("시트데이터 : " + seatData);
		
		seatData.forEach((seat) => {
			console.log(seat);
			const reservedSeat = document.getElementById(seat);
			
			reservedSeat.style.cssText = "background-color : #B73131; pointer-events: none;";
		})
	})
}

// 예매 - 영화 선택
 function changeMovieInfo(index) {
	
    const selectedMovie = document.getElementById('movieNo' + index).parentNode;
    selectedMovie.style.cssText = 'background-color: #EDD711; border-radius: 10px;';
    
    if (window.selectedMovie && window.selectedMovie !== selectedMovie) {
    	window.selectedMovie.style.backgroundColor = '';
    	ticketData = {};

		resetSelectedInfo("changeMovie");

    }
    const movieNo = document.getElementById('movieNo' + index);
    const movieInfo = document.getElementById('movieInfo');
    const content = movieNo.innerText;
    movieInfo.innerText = content;

    window.selectedMovie = selectedMovie;

    ticketData.selectedMovie = content;
    const arrow = document.getElementById('arrow1');
    const dateMenu = document.getElementById('date');

    if(ticketData.selectedMovie != null){
      arrow.style.cssText = 'display : flex;'
      dateMenu.style.cssText = 'display : grid;'  
    } 

    console.log(ticketData);
 
   	sessionStorage.setItem("movieNo", index);
   	ticketData.selectedMovieNo = index;
   	changeMovieImage();
   	printDateList();
  }

// 예매 - 날짜 선택
  function changeDateInfo(voList, date, index) {
	  
	
    const selectedDate = document.getElementById('movieDate' + index).parentNode;
    selectedDate.style.cssText = 'background-color: #EDD711; border-radius: 10px;';

    if (window.selectedDate && window.selectedDate !== selectedDate) {
      window.selectedDate.style.backgroundColor = '';
      resetSelectedInfo("changeDate");
    }

    const movieDate = document.getElementById('movieDate' + index);
    const dateInfo = document.getElementById('dateInfo');
    const content = movieDate.innerText;
    dateInfo.innerText = content;

    window.selectedDate = selectedDate;

    ticketData.selectedDate = content;

    const arrow2 = document.getElementById('arrow2');
    const timeMenu = document.getElementById('time');
		
    if(ticketData.selectedDate !== null){
      arrow2.style.cssText = 'display : flex;'
      timeMenu.style.cssText = 'display : grid;'  
    } 
    else {
	  arrow2.style.cssText = 'display : none;'
      timeMenu.style.cssText = 'display : none;'
	}
	sessionStorage.setItem("selectedDate", ticketData.selectedDate);
    console.log(ticketData);
    printTimeList();
   }

// 예매 - 상영관, 시간 선택 
// list.theaterNo, list.startTime
function changeTimeInfo(list){
	
	
	console.log(list);
	const clickedTime = event.target;
	clickedTime.parentNode.style.backgroundColor = '#EDD711';
	
	if (window.selectedTime && window.selectedTime !== clickedTime) {
       window.selectedTime.parentNode.style.backgroundColor = '';
       
       resetSelectedInfo("changeTime");
    }

	const timeInfo = document.getElementById('timeInfo');
    timeInfo.innerText = list.startTime;

	const theaterInfo = document.getElementById('theaterInfo');
	theaterInfo.innerText = list.theaterNo + "관";

	window.selectedTime = clickedTime;
	
	ticketData.selectedTime = list.startTime;
	ticketData.selectedTimeNo = list.screeningTimeNo;
    ticketData.selectedTheater = list.theaterNo;
	
	console.log(ticketData);
	
	const seatMenu = document.getElementById('ticketing2')

    if(ticketData.selectedTime !== null){
    	seatMenu.style.cssText = 'display : block;'  
    }
 
    setSeat(list);
}

    
// 좌석 선택 - 몇 명인지, 몇번 좌석인지
document.querySelectorAll('.seat div').forEach(seat => {
    seat.addEventListener('click', handleSeatClick);
});

function handleSeatClick(event) {
  	const selectedSeat = event.currentTarget;
  
  	const seatName = event.currentTarget.id;

	if (seatData.includes(seatName)){
		
    const button = selectedSeat.querySelector('button');
    button.style.backgroundColor = '';
    
    seatData.splice(seatData.indexOf(seatName), 1);
    
    resetSelectedInfo("changeSeat");
    
  } else {
    const button = selectedSeat.querySelector('button');
    button.style.backgroundColor = '#EDD711';

    seatData.push(seatName);
  }

  ticketData.selectedSeat = seatData;

  const totalReserved = seatData.length;
  ticketData.totalReserved = totalReserved;
  console.log(ticketData);

  const seatInfo = document.getElementById('seatInfo');
  seatInfo.innerText = seatData;
  const reservedInfo = document.getElementById('reservedInfo');
  reservedInfo.innerText = totalReserved;
  // 총 금액
  const payInfo = document.getElementById('payInfo');
  const paymentAmount = totalReserved * 8000;
  ticketData.paymentAmount = paymentAmount;
  payInfo.innerText = paymentAmount;
  console.log("마지막" + ticketData);
  
  ticketData.totalAmount = paymentAmount;
  
  sessionStorage.setItem("ticketData", JSON.stringify(ticketData)); 
  
  const selectComplete = document.getElementById('selectComplete'); 
  
  if(seatData.length > 0){
  	selectComplete.style.display = "flex";  
  } else if(seatData.length === 0){
	selectComplete.style.display = "none";  
  }
}

// 다른 항목 선택시 선택정보 초기화
function resetSelectedInfo(index){
	// 영화 재선택시
	if(index === "changeMovie"){
		setDisplay(['selectComplete','time', 'arrow2', 'ticketing2'], 'none');
		clearContent(['dateInfo','timeInfo', 'theaterInfo', 'reservedInfo', 'seatInfo', 'payInfo']);
	}
	// 날짜 재선택시
	if(index === "changeDate"){
		setDisplay(['selectComplete','ticketing2'], 'none');
		clearContent(['timeInfo', 'theaterInfo', 'reservedInfo', 'seatInfo', 'payInfo']);
	}
	// 상영시간 재선택시
	if(index === "changeTime"){
		//좌석 색상 초기화후 다시 로드
		setDisplay(['selectComplete'], 'none');
		clearContent(['timeInfo', 'theaterInfo', 'reservedInfo', 'seatInfo', 'payInfo']);
		//초기화
		seatData = [];
		const seatArr = document.querySelectorAll('#seat_area > #seat_A > .seat_A > button, #seat_area > #seat_B > .seat_B > button, #seat_area > #seat_C > .seat_C > button, #seat_area > #seat_D > .seat_D > button');
		console.log(seatArr);
		seatArr.forEach((x)=>{

				x.style.cssText = "'';";
		})
		
		// div에 배경먹인거 초기화하는거ㅣ임....
		const zz = document.querySelectorAll('#seat_area > #seat_A > .seat_A, #seat_area > #seat_B > .seat_B, #seat_area > #seat_C > .seat_C, #seat_area > #seat_D > .seat_D');

		zz.forEach((x)=>{
			x.style.cssText = "'';";
		})
		
	}
	
	if(index === "changeSeat"){
		
	}
}

function setDisplay(elementIds, displayValue) {
    elementIds.forEach((id) => {
        const element = document.getElementById(id);
        if (element) {
            element.style.display = displayValue;
        }
    });
}

function clearContent(elementIds) {
    elementIds.forEach((id) => {
        const element = document.getElementById(id);
        if (element) {
            element.innerText = "";
        }
    });
}

/*---------------------------예매 선택 정보 -끝*/

/*선택 완료 버튼*/

function completeSelect(){
  window.location.href = '/cinema/ticketing/payment';
}