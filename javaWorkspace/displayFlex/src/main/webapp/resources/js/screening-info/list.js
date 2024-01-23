    document.addEventListener('DOMContentLoaded', function() {
      flatpickr(document.getElementById('dateInput'), {
        dateFormat: "Y-m-d",
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
  
/*  document.addEventListener('DOMContentLoaded', function() {
    flatpickr('#endTime', {
      enableTime: true,       // 시간 선택 활성화
      noCalendar: true,       // 달력 비활성화
      dateFormat: "H:i",      // 시간 형식 설정
      time_24hr: true,        // 24시간 형식 사용
      locale: 'ko', // 한국어로 설정
      minTime: document.getElementById("startTime").value, 
    });
  });*/
  
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