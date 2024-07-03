/* for view/tranAdd/add.jsp */

/* ------------ form 입력 과정 func ------------ */

document.addEventListener('DOMContentLoaded', () => {

	// 직접 event 갖지 않는 요소들
	const slct_nn = document.getElementById("slct_nn");
	const slct_in = document.getElementById("slct_in");
	const slct_ex = document.getElementById("slct_ex");
	const row_meth = document.getElementById("row_meth");
	const slct_mn = document.getElementById("slct_mn");
	const slct_crd = document.getElementById("slct_crd");
	const declaredForUse = { slct_nn, slct_in, slct_ex, row_meth, slct_mn, slct_crd };
	
	// 1 수입 or 지출 설정 => 3. 카테고리 드롭다운
	const btn_in = document.getElementById("btn_in");
	const btn_ex = document.getElementById("btn_ex");
	btn_in.addEventListener('click', () => {
		setINEX("IN");
		colorBtn("IN");
		showSlct("IN");
		resetMethBlock(); // 결제수단 블럭 초기화
		// clearValCCODE(); //카테고리 선택값 초기화
		// clearValMCODE(); //결제수단 선택값 초기화
	})
	btn_ex.addEventListener('click', () => {
		setINEX("EX");
		colorBtn("EX");
		showSlct("EX");
		openMethBlock(); // 결제수단 블럭 보이기
		// clearValCCODE(); //카테고리 선택값 초기화
	})

	// 2. 오늘 날짜 설정
	const btn_today = document.getElementById("btn_today");
	btn_today.addEventListener('click', () => {
		const today = new Date();
		const year = today.getFullYear();
		const month = String(today.getMonth() + 1).padStart(2, '0');
		const day = String(today.getDate()).padStart(2, '0');
		const formattedDate = `${year}-${month}-${day}`;
		document.fm.DATE.value = formattedDate;
	})

	// 5. 금액 세자리마다 ',' 표시
	const amount = document.getElementById("amount");
	amount.addEventListener('input', () => {
		let val = amount.value.replace(/,/g, '');
		if (!isNaN(val) && val.trim() !== "") {
			val = parseInt(val, 10).toLocaleString('ko-KR');
			amount.value = val;
		}
	})

	// 6. 현금 or 카드 설정 => 결제수단 드롭다운
	const btn_mn = document.getElementById("btn_mn");
	const btn_crd = document.getElementById("btn_crd");
	btn_mn.addEventListener('click', () => {
		setMNCRD("MN");
		colorBtn("MN");
		showSlct("MN");
		// clearValMCODE();
	})
	btn_crd.addEventListener('click', () => {
		setMNCRD("CRD");
		colorBtn("CRD");
		showSlct("CRD");
		// clearValMCODE();
	})
});

/* ------------ 공통 기능 func ------------ */

function setINEX(v) { // INEX 입력값 설정
	document.fm.INEX.value = v;
}
function colorBtn(v) { // 수입/지출, 현금/카드 버튼색 바꾸기
	switch(v){
		case "IN":
			btn_in.classList.add('selected_in');
			btn_ex.classList.remove('selected_ex'); break;
		case "EX":
			btn_in.classList.remove('selected_in');
			btn_ex.classList.add('selected_ex'); break;
		case "MN":
			btn_mn.classList.add('selected_mn');
			btn_crd.classList.remove('selected_crd'); break;
		case "CRD":
			btn_mn.classList.remove('selected_mn');
			btn_crd.classList.add('selected_crd');	 break;
		case "NN":
			btn_in.classList.remove('selected_in');
			btn_ex.classList.remove('selected_ex');
			btn_mn.classList.remove('selected_mn');
			btn_crd.classList.remove('selected_crd'); break;
		case "reset_MNCRD":
			btn_mn.classList.remove('selected_mn');
			btn_crd.classList.remove('selected_crd'); break;
	}
}
function showSlct(v) { // 카테고리, 결제수단 드롭다운 설정
	switch(v){
		case "IN":
			slct_nn.classList.add('hidden');
			slct_in.classList.remove('hidden');
			slct_ex.classList.add('hidden'); break;
		case "EX":
			slct_nn.classList.add('hidden');
			slct_in.classList.add('hidden');
			slct_ex.classList.remove('hidden'); break;
		case "MN":
			slct_mn.classList.remove('hidden');
			slct_crd.classList.add('hidden'); break;
		case "CRD":
			slct_mn.classList.add('hidden');
			slct_crd.classList.remove('hidden'); break;
		case "NN":
			slct_nn.classList.remove('hidden');
			slct_in.classList.add('hidden');
			slct_ex.classList.add('hidden');
			slct_mn.classList.add('hidden');
			slct_crd.classList.add('hidden'); break;
		case "reset_MNCRD":
			slct_mn.classList.add('hidden');
			slct_crd.classList.add('hidden'); break;
	}
}
function openMethBlock() { // 결제수단 블럭 보이기
	methBlock.classList.remove('hidden');
}
function setMNCRD(v) { // MNCRD 입력값 설정
	document.fm.MNCRD.value = v;
}
function resetMethBlock() { // 결제수단 블럭 초기화
	setMNCRD("");
	colorBtn("reset_MNCRD");
	showSlct("reset_MNCRD");
	methBlock.classList.add('hidden');
}

/* ------------ form 취소 ------------ */

function backToHome() {
	if (confirm("취소하고 홈으로 돌아가시겠습니까?"))
		location.href = "/index";
	else {
		colorBtn("NN"); // 수입/지출, 현금/카드 버튼색 초기화
		showSlct("NN"); // 카테고리, 결제수단 드롭다운 초기화
		resetMethBlock(); // 결제수단 블럭 초기화
		return false;
	}
}

