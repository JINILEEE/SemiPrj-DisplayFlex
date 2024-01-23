
let ticketData = JSON.parse(sessionStorage.getItem("ticketData"));
console.log("payment >>>>" + ticketData);
console.log("payment >>>>" + JSON.stringify(ticketData));

const movieInfo = document.getElementById("movieInfo");
const dateInfo = document.getElementById("dateInfo");
const timeInfo = document.getElementById("timeInfo");
const theaterInfo = document.getElementById("theaterInfo");
const reservedInfo = document.getElementById("reservedInfo");
const seatInfo = document.getElementById("seatInfo");
const paymentAmount = document.getElementById("paymentAmount-value");
const discountValue = document.getElementById("discount-value");
const totalPayment = document.getElementById("totalPayment-value");
const posterImg = document.getElementById("posterImg");


//현재 적용된 할인 금액

movieInfo.innerText = ticketData.selectedMovie;
dateInfo.innerText = ticketData.selectedDate;
timeInfo.innerText = ticketData.selectedTime;
theaterInfo.innerText = ticketData.selectedTheater;
reservedInfo.innerText = ticketData.totalReserved;
seatInfo.innerText = ticketData.selectedSeat;
paymentAmount.innerText = ticketData.paymentAmount;
totalPayment.innerText = ticketData.paymentAmount;
posterImg.src = ticketData.posterImg;



const checkedCoupon = document.getElementsByName('discount');

checkedCoupon.forEach((checkbox) => {
    checkbox.addEventListener('change', function() {
					
  
  		if(checkbox.value !== 'null' && !isNaN(checkbox.value)){
	  		fetch("http://localhost:9002/cinema/ticket/payment", {
				  method: "POST",
				  headers: {
				  	"Content-Type": "application/json"
				  },
				  	body: checkbox.value
			})
	  		.then((resp) =>{ return resp.json() })
	  		.then((couponVo) => {
        		const discountDetails = document.getElementById("discountDetails");
		        const discountValue = document.getElementById("discount-value");
		        const totalPaymentValue = document.getElementById("totalPayment-value");
				
				ticketData.retainedNo = couponVo.retainedNo;
				
				ticketData.discount = parseInt(couponVo.discount);
				ticketData.totalAmount = ticketData.paymentAmount - ticketData.discount;
		
				discountDetails.innerText = couponVo.name;
				discountValue.innerText = ticketData.discount;
				totalPaymentValue.innerHTML = ticketData.totalAmount;

				sessionStorage.setItem("ticketData", JSON.stringify(ticketData)); 
				

			  })
			  .catch(()=>{
				  
			  })
		} else if(checkbox.value.includes("할인")){
			
			const discountDetails = document.getElementById("discountDetails");
		    const discountValue = document.getElementById("discount-value");
		    const totalPaymentValue = document.getElementById("totalPayment-value");
			
			discountDetails.innerHTML = checkbox.value;
		    ticketData.discount = 1000;
		    
		    discountValue.innerHTML = ticketData.discount;
		    totalPaymentValue.innerHTML = ticketData.paymentAmount - ticketData.discount;

		}     
        if(checkbox.value === 'null'){
			const discountDetails = document.getElementById("discountDetails");
		    const discountValue = document.getElementById("discount-value");
		    const totalPaymentValue = document.getElementById("totalPayment-value");
		    discountDetails.innerText = "쿠폰 또는 혜택을 선택하세요";
		    ticketData.discount = 0;
		    discountValue.innerHTML = ticketData.discount;
		    totalPaymentValue.innerHTML = ticketData.paymentAmount;
		    
		    sessionStorage.setItem("ticketData", JSON.stringify(ticketData)); 
		}
        
        
    });
});


//                                              결제

//구매자 정보

console.log("페이버튼 >>" + ticketData)

const user_email = "zz";

const username = "zz";

var IMP = window.IMP;

//var today = new Date();
//var hours = today.getHours();
//var minutes = today.getMinutes(); 
//var seconds = today.getSeconds(); 
//var milliseconds = today.getMilliseconds();
//var makeMerchantUid = `${hours}` + `${minutes}` + `${seconds}` + `${milliseconds}`;


function kakaoPay() {
    if (confirm("예매 하시겠습니까?")) {

            IMP.init("imp70267381"); // 가맹점 식별코드
            IMP.request_pay({
            	pg: 'kakaopay.TC0ONETIME', 
              	pay_method: 'kakaopay', 
//                merchant_uid: "IMP" + makeMerchantUid, // 결제 고유 번호
                name: ticketData.selectedMovie, 
                amount: ticketData.totalAmount,
//                buyer_email: `${useremail}`,
//                buyer_name: `${username}`,
                buyer_tel : '010-4233-5951',
                buyer_addr : '서울특별시 강남구 역삼동',
                buyer_postcode : '123-456'
            }, async function (rsp) {
                if (rsp.success) { 
                    console.log(rsp);
                    paymentSuccess();

            		window.location.href = "http://localhost:9002/cinema/home";
            		
      				const url = "http://localhost:9002/cinema/ticket/popup";
      				const popup = openPopup(url);
					sessionStorage.clear();


//                    if (response.status == 200) { // DB저장 성공시
//                        alert('결제 완료!')
//                        location.href("/http://localhost:9002/cinema/ticket/popup");
//                        window.location.reload();
//                    } else { // 결제완료 후 DB저장 실패시
//                        alert(`error:[${response.status}]\n결제요청이 승인된 경우 관리자에게 문의바랍니다.`);
//                        // DB저장 실패시 status에 따라 추가적인 작업 가능성
//                    }
					
                } else if (rsp.success == false) { // 결제 실패시
                    alert(rsp.error_msg)
                }
            });
    } else { 
        return false;
    }
}

function paymentSuccess(){
	fetch("http://localhost:9002/cinema/ticket/kakaoPay", {
	  method: "POST",
	  headers: {
		"Content-Type": "application/json"
		},
	  body: JSON.stringify(ticketData)
	  
		})
	.then((resp) =>{ return resp.json() })
	.then((x) => {
		console.log(x);

	 })	
}


function openPopup(url){
    const ticketPopup = window.open(url, '티켓','width=600, height=569');
}


