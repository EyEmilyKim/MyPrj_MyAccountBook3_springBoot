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
//	clearValCCODE(); //카테고리 선택값 초기화
//	clearValMCODE(); //결제수단 선택값 초기화
}

document.addEventListener('DOMContentLoaded', () => {

	const btn_in = document.getElementById("btn_in");
	const btn_ex = document.getElementById("btn_ex");
	const slct_nn = document.getElementById("slct_nn");
	const slct_in = document.getElementById("slct_in");
	const slct_ex = document.getElementById("slct_ex");

	btn_in.addEventListener('click', () => {
		setINEX("IN"); // INEX : 수입(EX)
		colorBtnINEX("IN"); // 버튼색 바꾸기
		showSlct_XX("IN"); // 카테고리 드롭다운 설정
	})

	btn_ex.addEventListener('click', () => {
		setINEX("EX"); // INEX : 지출(EX)
		colorBtnINEX("EX"); // 버튼색 바꾸기
		showSlct_XX("EX"); // 카테고리 드롭다운 설정
	})

});
/* ------------ 공통 기능 func ------------ */

function setINEX(v) { // INEX 입력값 설정
	document.fm.INEX.value = v;
}
function colorBtnINEX(v) { // 수입/지출 버튼색 바꾸기
	if (v == "IN") {
		btn_ex.classList.remove('selected_ex');
		btn_in.classList.add('selected_in');
	} else if (v == "EX") {
		btn_ex.classList.add('selected_ex');
		btn_in.classList.remove('selected_in');
	} else if (v == "NN") {
		btn_ex.classList.remove('selected_ex');
		btn_in.classList.remove('selected_in');
	}
}
function showSlct_XX(v) {  // 카테고리 드롭다운 설정
	if (v == "NN") {
		slct_nn.classList.remove('hidden');
		slct_in.classList.add('hidden');
		slct_ex.classList.add('hidden');
	} else if (v == "IN") {
		slct_nn.classList.add('hidden');
		slct_in.classList.remove('hidden');
		slct_ex.classList.add('hidden');
	} else if (v == "EX") {
		slct_nn.classList.add('hidden');
		slct_in.classList.add('hidden');
		slct_ex.classList.remove('hidden');
	}
}

/* ------------ form 취소 ------------ */

function backToHome() {
	if (confirm("취소하고 홈으로 돌아가시겠습니까?"))
		location.href = "/index";
	else {
		colorBtnINEX("NN"); // 수입/지출 버튼색 초기화
		// 현금/카드 버튼색 초기화
		//결제수단 row 초기화 (기본:가려짐)
		showSlct_XX("NN"); // 카테고리 드롭다운 초기화
		setINEX("") // INEX 입력값 초기화
		return false;
	}
}

