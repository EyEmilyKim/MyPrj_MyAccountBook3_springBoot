/**
 * for view/tranAdd/add.jsp
 */
function alertJSloaded() {
	alert("js loaded.")
}


/* ------------ form 입력 과정 func ------------ */
/* 수입 or 지출 버튼 */
function doIN(){ //[수입]
//	onoffROW_METH("OFF"); //결제수단 row 가리기
//	showSLCT_XX("IN"); //카테고리(수입)만 남기고 가리기
//	clearValCCODE(); //카테고리 선택값 초기화
//	clearValMCODE(); //결제수단 선택값 초기화
}

document.addEventListener('DOMContentLoaded', ()=>{
	
	const btn_in = document.getElementById("btn_in");
	const btn_ex = document.getElementById("btn_ex");

	btn_in.addEventListener('click', ()=>{
		setINEX("IN") // INEX : 수입(EX)
		//버튼 색 바꾸기
		btn_ex.classList.remove('selected_ex');
		btn_in.classList.add('selected_in');
		
	})
	
	btn_ex.addEventListener('click', ()=>{
		setINEX("EX") // INEX : 지출(EX)
		//버튼 색 바꾸기
		btn_ex.classList.add('selected_ex');
		btn_in.classList.remove('selected_in');
		
	})
	
})
/* ------------ 공통 기능 func ------------ */

function setINEX(v){ // INEX 매개변수 받아 설정
	document.fm.INEX.value = v;
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

