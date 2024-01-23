/**
 * 
 */
function previewImage(event) {
    const input = event.target;
    const preview = document.getElementById('imgPreview');
    const plusIcon = document.querySelector('.fa-circle-plus');

    // Check if a file was selected
    if (input.files && input.files[0]) {
        const reader = new FileReader();

        reader.onload = function (e) {
            // Set the source of the preview div to the data URL of the selected image
            preview.style.backgroundImage = 'url(' + e.target.result + ')';
            // 사진 미리보기 업로드시 사진 비율 유지하면서 div 사이즈에 맞춰 보이기
        	preview.style.backgroundSize = 'contain';
        	preview.style.backgroundRepeat = 'no-repeat';
        };

        // Read the selected file as a data URL
        reader.readAsDataURL(input.files[0]);
        plusIcon.style.opacity=0;
    } else {
        // Clear the preview if no file is selected
        preview.style.backgroundImage = 'none';
    }
};


function onlyNumber(){
	let priceInput = document.getElementById("price");
	
	// 숫자 이외의 문자 제거
	let value = priceInput.value = priceInput.value.replace(/[^0-9]/g, '');
	
	// 세 자리마다 쉼표 추가
    //var formattedValue = Number(value).toLocaleString(); 
	
	// 입력값 업데이트
    //priceInput.value = formattedValue + "원";
	
};


