    document.addEventListener('DOMContentLoaded', function() {
      flatpickr(document.getElementById('dateInput'), {
        dateFormat: "Y-m-d",
        minDate: "today",
        locale: 'ko', // 한국어로 설정
      });
    });
    
    document.addEventListener('DOMContentLoaded', function() {
    flatpickr('#startTime', {
      enableTime: true,       // 시간 선택 활성화
      noCalendar: true,       // 달력 비활성화
      dateFormat: "H:i",      // 시간 형식 설정
      time_24hr: true,        // 24시간 형식 사용
      locale: 'ko', // 한국어로 설정
    });
  });
  
  
  document.addEventListener('DOMContentLoaded', function() {
    flatpickr('#endTime', {
      enableTime: false,       // 시간 선택 활성화
      noCalendar: true,       // 달력 비활성화
      dateFormat: "H:i",      // 시간 형식 설정
      time_24hr: true,        // 24시간 형식 사용
      locale: 'ko', // 한국어로 설정
      value: '',
    });
  });
  
  const deleteTableRow = (row) => {
        document.getElementById("info-table").deleteRow(row.rowIndex);
    };
 
 const checkExistInfo = () => {
	 
	 
	 const title = encodeURIComponent(document.getElementById('title').value);
	 const theater = encodeURIComponent(document.getElementById('theater').value);
	 const dateInput = encodeURIComponent(document.getElementById('dateInput').value);
	 const startTime = encodeURIComponent(document.getElementById('startTime').value);
	 const endTime = encodeURIComponent(document.getElementById('endTime').value);
	 
	  if(title === '') {
			  alert('제목을 입력하세요.');
			  title.focus();
			  return false;
		  }
		  
		  if(theater === '') {
			  alert('상영관을 선택하세요.');
			  theater.focus();
			  return false;
		  }
		  if(dateInput === '') {
			  alert('상영 일자를 입력하세요.');
			  title.focus();
			  return false;
		  }
		  if(startTime === '') {
			  alert('시작 시간을 입력하세요.');
			  startTime.focus();
			  return false;
		  }
		  if(endTime === '') {
			  alert('제목을 입력하세요.');
			  endTime.focus();
			  return false;
		  }
		  
	 fetch(`http://localhost:9002/cinema/admin/screen-info/check?title=${title}&theater=${theater}&dateInput=${dateInput}&startTime=${startTime}&endTime=${endTime}`)
	 .then((res) => {
		 if(!res.ok) {
			 throw new Error();
		 }
		 return res.text();
	 })
	 .then((data) => {
		 const check = confirm(data);
		 if(check) {
		 	addTableRow();			 
		 }
	 })
	 .catch((err) => {
		 alert('상영 시간이 겹치는 정보입니다.');
		 console.log(err);
		 return false;
	 })
}
  
  //단순 텍스트를 input태그에 넣는 작업 필요
  const addTableRow = () => {
	  
		  const tbody = document.getElementById("info-table").getElementsByTagName("tbody")[0];
		  
		  let newRow = tbody.insertRow();
		  
		  let titleCell = newRow.insertCell(0);
		  let theaterCell = newRow.insertCell(1);
		  let screenDayCell = newRow.insertCell(2);
		  //시작 시간 ~ 종료 시간 형태
		  let periodCell = newRow.insertCell(3);
		  let deleteCell = newRow.insertCell(4);
		  
		  const title = document.getElementById("title");
		  const theater = document.getElementById("theater");
		  const screenInput = document.getElementById("dateInput");
		  const startTime = document.getElementById("startTime");
		  const endTime = document.getElementById("endTime");
		  
		  
		  titleCell.innerHTML = title.value.split('|')[0];
		  const titleInput = document.createElement("input");
		  titleInput.type = "text";
		  titleInput.name = "inputTitle";
		  titleInput.value = title.value.split('|')[1];
		  titleInput.hidden = true;
		  titleCell.appendChild(titleInput);
		  
		  theaterCell.innerHTML = theater.value;
		  const theaterInput = document.createElement("input");
		  theaterInput.type = "text";
		  theaterInput.name = "inputTheater";
		  theaterInput.value = theater.value;
		  theaterInput.hidden = true;
		  theaterCell.appendChild(theaterInput);
		  
		  screenDayCell.innerHTML = screenInput.value;
		  const dateInput = document.createElement("input");
		  dateInput.type = "text";
		  dateInput.name = "inputDate";
		  dateInput.value = screenInput.value;
		  dateInput.hidden = true;
		  screenDayCell.appendChild(dateInput);
		  
		  periodCell.innerHTML = startTime.value + ' ~ ' + endTime.value;
		  const startTimeInput = document.createElement("input");
		  startTimeInput.type = "text";
		  startTimeInput.name = "inputStartTime";
		  startTimeInput.value = startTime.value;
		  startTimeInput.hidden = true;
		  periodCell.appendChild(startTimeInput);
		  
		  const endTimeInput = document.createElement("input");
		  endTimeInput.type = "text";
		  endTimeInput.name = "inputEndTime";
		  endTimeInput.value = endTime.value;
		  endTimeInput.hidden = true;
		  periodCell.appendChild(endTimeInput);
		  
		  
		  const deleteLink = document.createElement('a');
	        deleteLink.href = '#';
	        deleteLink.textContent = 'X';
	        deleteLink.addEventListener('click', function () {
	            deleteTableRow(newRow);
	        });
        
        	deleteCell.appendChild(deleteLink);
        
        title.value = '';
        theater.value ='';
        screenInput.value = '';
        startTime.value = '';
        endTime.value = '';
        
	}
	
	const checkValidate = () => {
		const allTr = document.querySelectorAll("#info-table > tbody > tr");
		
		if(allTr.length === 0 || allTr === undefined) {
			alert('등록할 상영 정보를 입력해주세요.');
			return false;
		}
		
		const allTitle = document.querySelectorAll('input[name=inputTitle]');
		allTitle.forEach(el => {
			if(el.value === '') {
				alert('영화제목란에 비어있는 값이 있습니다.');
				return false;
			}
		});
		
		const allTheater = document.querySelectorAll('input[name=inputTheater]');
		allTheater.forEach(el => {
			if(el.value === '') {
				alert('상영관에 비어있는 값이 있습니다.');
				return false;
			}
		});
		
		const allInputDate = document.querySelectorAll('input[name=inputDate]');
		allInputDate.forEach(el => {
			if(el.value === '') {
				alert('상영 일자에 비어있는 값이 있습니다.');
				return false;
			}
		});
		
		const allStartTime = document.querySelectorAll('input[name=inputStartTime]');
		allStartTime.forEach(el => {
			if(el.value === '') {
				alert('시작 시간에 비어있는 값이 있습니다.');
				return false;
			} else {
				console.log(el.value);
			}
		});
		
		const allendTime = document.querySelectorAll('input[name=inputEndTime]');
		allendTime.forEach(el => {
			if(el.value === '') {
				alert('상영관에 비어있는 값이 있습니다.');
				return false;
			}  else {
				console.log(el.value);
			}
		});
		
		return true;
	}
	  
    
  
  window.onload = () => {
	  
	  document.getElementById("startTime").addEventListener("input", () => {
		  const startTime = document.getElementById("startTime").value;
		 flatpickr('#endTime', {
	      enableTime: true,       // 시간 선택 활성화
	      minTime: startTime,
	      noCalendar: true,       // 달력 비활성화
	      dateFormat: "H:i",      // 시간 형식 설정
	      time_24hr: true,        // 24시간 형식 사용
	      locale: 'ko', // 한국어로 설정
	    });
	  });
	
	
	document.getElementById("startTime").addEventListener("change", () => {
		  const startTime = document.getElementById("startTime");
		  const endTime = document.getElementById("endTime");
		  if(startTime.value > endTime.value) {
			 flatpickr('#endTime', {
			  value: '',
		      enableTime: true,       // 시간 선택 활성화
		      minTime: startTime.value, 
		      noCalendar: true,       // 달력 비활성화
		      dateFormat: "H:i",      // 시간 형식 설정
		      time_24hr: true,        // 24시간 형식 사용
		      locale: 'ko', // 한국어로 설정
		    });
			  
		  } 
	  });
  }
  
  
  
  