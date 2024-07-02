/**
 * for view/tranAdd/add.jsp
 */
function alertJSloaded() {
	alert("js loaded.")
}

/* ------------ form 취소 ------------ */

function backToHome(){
	if(confirm("취소하고 홈으로 돌아가시겠습니까?"))	
		location.href="/index";
	else { //안나가고 reset 시 html,css & js_value 수동 초기화(fm 입력 값은 자동 초기화)
		//버튼 색 all초기화 - 수입,지출,현금,카드 
		//결제수단 row 초기화 (기본:가려짐)
		//카테고리 드롭다운 초기화
		//func로 전달한 INEX 값 초기화.
		return false;
	}
}

