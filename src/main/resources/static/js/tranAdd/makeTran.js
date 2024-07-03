/**
 * for view/tranAdd/add.jsp
 */
function alertJSloaded() {
	alert("js loaded.")
}


/* ------------ form 입력 과정 func ------------ */
/* 수입 or 지출 버튼 */
function doIN() { //[수입]
	//	onoffROW_METH("OFF"); //결제수단 row 가리기
	//	clearValCCODE(); //카테고리 선택값 초기화
	//	clearValMCODE(); //결제수단 선택값 초기화
}

document.addEventListener('DOMContentLoaded', () => {

	// 직접 event 갖지 않는 요소들
	const slct_nn = document.getElementById("slct_nn");
	const slct_in = document.getElementById("slct_in");
	const slct_ex = document.getElementById("slct_ex");
	const keepDeclared = { slct_nn, slct_in, slct_ex };
	// 수입 or 지출 설정
	const btn_in = document.getElementById("btn_in");
	const btn_ex = document.getElementById("btn_ex");
	btn_in.addEventListener('click', () => {
		setINEX("IN");
		colorBtnINEX("IN");
		showSlct_XX("IN");
	})
	btn_ex.addEventListener('click', () => {
		setINEX("EX");
		colorBtnINEX("EX");
		showSlct_XX("EX");
	})

	// 오늘 날짜 설정
	const btn_today = document.getElementById("btn_today");
	btn_today.addEventListener('click', () => {
		const today = new Date();
		const year = today.getFullYear();
		const month = String(today.getMonth() + 1).padStart(2, '0');
		const day = String(today.getDate()).padStart(2, '0');
		const formattedDate = `${year}-${month}-${day}`;
		document.fm.DATE.value = formattedDate;
	})

	// 금액 세자리마다 ',' 표시
	const amount = document.getElementById('amount');
	amount.addEventListener('input', () => {
		let value = amount.value.replace(/,/g, '');
		if (!isNaN(value) && value.trim() !== '') {
			value = parseInt(value, 10).toLocaleString('ko-KR');
			amount.value = value;
		}
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

