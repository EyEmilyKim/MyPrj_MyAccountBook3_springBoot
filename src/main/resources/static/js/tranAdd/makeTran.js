/* for view/tranAdd/add.jsp */

document.addEventListener('DOMContentLoaded', () => {

	/* ------------ form 요소 식별 변수 ------------ */
	
	const btn_today = document.getElementById("btn_today");
	const date = document.getElementById("date");
	const btn_in = document.getElementById("btn_in");
	const btn_ex = document.getElementById("btn_ex");
	const slct_nn_c = document.getElementById("slct_nn_c");
	const slct_in = document.getElementById("slct_in");
	const slct_ex = document.getElementById("slct_ex");
	const item = document.getElementById("item");
	const amount = document.getElementById("amount");
	const methBlock = document.getElementById("methBlock");
	const btn_mn = document.getElementById("btn_mn");
	const btn_crd = document.getElementById("btn_crd");
	const slct_nn_m = document.getElementById("slct_nn_m");
	const slct_mn = document.getElementById("slct_mn");
	const slct_crd = document.getElementById("slct_crd");
	const guide = {
		date : document.getElementById("guide_date"),
		inex : document.getElementById("guide_inex"),
		amount : document.getElementById("guide_amount"),
		amount_type : document.getElementById("guide_amount_type"),
		item_length : document.getElementById("guide_item_length"),
		opt_item : document.getElementById("guide_opt_item"),
		opt_ccode : document.getElementById("guide_opt_ccode"),
		opt_mcode : document.getElementById("guide_opt_mcode"),
	}
	
	const fm = document.forms['fm'];
	
	/* ------------ form 유효성 검사 관련 ------------ */
	
	const msg = {
		date : "거래날짜를 선택해주세요.",
		inex : "수입/지출 구분을 선택해주세요.",
		amount : "금액을 입력해주세요.",
		amount_type : "금액은 숫자로 입력해주세요.",
		item_length : "거래내용은 최대 20자 한글 또는 60자 영문/숫자로 입력해주세요.",
		opt_item : "거래내용이 비어있습니다.",
		opt_ccode : "카테고리가 선택되지 않았습니다.",
		opt_mcode : "결제수단이 선택되지 않았습니다.",
		blank : " - ",
	};
	
	const validate = {
		date : () => { fm.DATE.value == "" ? setInnerHTML(guide.date, msg.date) : clearInnerHTML(guide.date); },
		inex : () => { fm.INEX.value == "" ? setInnerHTML(guide.inex, msg.inex) : clearInnerHTML(guide.inex); },
		amount : () => {
			let val = fm.AMOUNT.value.replace(/,/g, '');
			if (!isNaN(val) && val.trim() !== "") {
				val = parseInt(val, 10).toLocaleString('ko-KR');
				amount.value = val;
			}
			isNaN(Number(val.replace(/,/g, ''))) ? setInnerHTML(guide.amount_type, msg.amount_type) : clearInnerHTML(guide.amount_type);
			val.trim() == "" ? setInnerHTML(guide.amount, msg.amount) : clearInnerHTML(guide.amount); 
		},
		opt_item : () => {
			const val = fm.ITEM.value.trim(); 
			const byteLength = calculateByteLength(val);
			byteLength > 60 ? setInnerHTML(guide.item_length, msg.item_length) : clearInnerHTML(guide.item_length);
			byteLength == 0 ? setInnerHTML(guide.opt_item, msg.opt_item) : clearInnerHTML(guide.opt_item);
		},
		opt_ccode : () => { fm.CCODE.value == "" ? setInnerHTML(guide.opt_ccode, msg.opt_ccode) : clearInnerHTML(guide.opt_ccode); },
		opt_mcode : () => { fm.MCODE.value == "" ? setInnerHTML(guide.opt_mcode, msg.opt_mcode) : clearInnerHTML(guide.opt_mcode); },
	}
	
	/* ------------ form 입력 과정 func ------------ */
	
	// 1. 어제, 오늘 날짜 설정
	btn_yesterday.addEventListener('click', () => {
		const formattedDate = getFormattedDate(-1);
		fm.DATE.value = formattedDate;
		colorBtn("YESTERDAY");
		validate.date();
	})
	btn_today.addEventListener('click', () => {
		const formattedDate = getFormattedDate(0);
		fm.DATE.value = formattedDate;
		colorBtn("TODAY");
		validate.date();
	})
	date.addEventListener('change', () => {
		validate.date();
	})

	// 2. 수입 or 지출 설정 => 3. 카테고리 드롭다운
	btn_in.addEventListener('click', () => {
		setINEX("IN");
		colorBtn("IN");
		showSlct("IN");
		resetSLCT_INEX(); // slct_in, slct_ex 선택상태 + CCODE 입력값 초기화
		closeMethBlock(); // 결제수단 블럭 초기화, 숨기기
		validate.inex();
		validate.opt_ccode();
		clearMcodeGuide();
	})
	btn_ex.addEventListener('click', () => {
		setINEX("EX");
		colorBtn("EX");
		showSlct("EX");
		resetSLCT_INEX(); // slct_in, slct_ex 선택상태 + CCODE 입력값 초기화
		openMethBlock(); // 결제수단 블럭 초기화, 보이기
		validate.inex();
		validate.opt_ccode();
		validate.opt_mcode();
	})

	// 3. 카테고리 드롭다운 선택 시 CCODE 설정
	const forCCODE = [slct_in, slct_ex];
	for (let i of forCCODE) {
		i.addEventListener('change', () => {
			setCCODE(i.value);
			validate.opt_ccode();
		})
	}
	
	// 4. 거래내용 입력값 길이 제한 안내
	item.addEventListener('input', () => {
		validate.opt_item();
	})
	
	// 5. 금액 타입 제한 안내, 세자리 콤마 표시
	amount.addEventListener('input', () => {
		validate.amount();
	})

	// 6-1. 현금 or 카드 설정 => 6-2. 결제수단 드롭다운
	btn_mn.addEventListener('click', () => {
		setMNCRD("MN");
		colorBtn("MN");
		showSlct("MN");
		resetSLCT_MNCRD(); // slct_mn, slct_crd 선택상태 + MCODE 입력값 초기화
	})
	btn_crd.addEventListener('click', () => {
		setMNCRD("CRD");
		colorBtn("CRD");
		showSlct("CRD");
		resetSLCT_MNCRD(); // slct_mn, slct_crd 선택상태 + MCODE 입력값 초기화
	})
	
	// 6-2. 결제수단 드롭다운 선택 시 MCODE 설정
	const forMCode = [slct_mn, slct_crd];
	for (let i of forMCode) {
		i.addEventListener('change', () => {
			setMCODE(i.value);
			validate.opt_mcode();
		})
	}
	
	// 8. form 취소, 제출 동작 추가
	fm.addEventListener('reset', () => {
		backToHome();
	})
    fm.addEventListener('submit', (event) => {
        if (!checkSubmit()) {
	        event.preventDefault(); 
			return false;
		}
		return true;
    });

	/* ------------ 공통 기능 func ------------ */
	
	function getFormattedDate(offset) {
		const date = new Date(); // 오늘 날짜 가져오기
		date.setDate(date.getDate() + offset); // 오프셋 만큼 날짜 조정
		// YYYY-MM-DD 형식으로 포맷팅
		const year = date.getFullYear();
		const month = String(date.getMonth() + 1).padStart(2, '0');
		const day = String(date.getDate()).padStart(2, '0');
		const formattedDate = `${year}-${month}-${day}`;
		return formattedDate;
	}
	
	function colorBtn(v) { // 수입/지출, 현금/카드 버튼색 바꾸기
		switch(v){
			case "YESTERDAY":
				btn_yesterday.classList.add('selected_date');
				btn_today.classList.remove('selected_date'); break;
			case "TODAY":
				btn_yesterday.classList.remove('selected_date');
				btn_today.classList.add('selected_date'); break;
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
				btn_yesterday.classList.remove('selected_date');
				btn_today.classList.remove('selected_date');
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
				slct_nn_c.classList.add('hidden');
				slct_in.classList.remove('hidden');
				slct_ex.classList.add('hidden'); break;
			case "EX":
				slct_nn_c.classList.add('hidden');
				slct_in.classList.add('hidden');
				slct_ex.classList.remove('hidden'); break;
			case "MN":
				slct_nn_m.classList.add('hidden');
				slct_mn.classList.remove('hidden');
				slct_crd.classList.add('hidden'); break;
			case "CRD":
				slct_nn_m.classList.add('hidden');
				slct_mn.classList.add('hidden');
				slct_crd.classList.remove('hidden'); break;
			case "NN":
				slct_nn_c.classList.add('hidden');
				slct_in.classList.add('hidden');
				slct_ex.classList.add('hidden');
				slct_nn_m.classList.add('hidden');
				slct_mn.classList.add('hidden');
				slct_crd.classList.add('hidden'); break;
			case "reset_MNCRD":
				slct_nn_m.classList.remove('hidden');
				slct_mn.classList.add('hidden');
				slct_crd.classList.add('hidden'); break;
		}
	}
	
	function setINEX(v) { // INEX 입력값 설정
		fm.INEX.value = v;
	}
	function setMNCRD(v) { // MNCRD 입력값 설정
		fm.MNCRD.value = v;
	}
	function setCCODE(v) { // CCODE 입력값 설정
		fm.CCODE.value = v;
	}
	function setMCODE(v) { // MCODE 입력값 설정
		fm.MCODE.value = v;
	}
	
	function resetSLCT_INEX() { // slct_in, slct_ex 선택상태 + CCODE 입력값 초기화
		fm.SLCT_IN.value = "";
		fm.SLCT_EX.value = "";
		setCCODE("");
	}
	function resetSLCT_MNCRD() { // slct_mn, slct_crd 선택상태 + MCODE 입력값 초기화
		fm.SLCT_MN.value = "";
		fm.SLCT_CRD.value = "";
		setMCODE("");
	}
	function resetMethBlock() { // 결제수단 블럭 초기화
		setMNCRD("");
		resetSLCT_MNCRD();
		colorBtn("reset_MNCRD");
		showSlct("reset_MNCRD");
	}
	function openMethBlock() { // 결제수단 블럭 초기화, 보이기
		resetMethBlock();
		methBlock.classList.remove('hidden');
	}
	function closeMethBlock() { // 결제수단 블럭 초기화, 숨기기
		resetMethBlock();
		methBlock.classList.add('hidden');
	}
	
	function setInnerHTML(dom, val) {
		dom.innerHTML = val;
	}
	function clearInnerHTML(dom) {
		dom.innerHTML = "";
	}
	function clearAllGuide() { // 모든 안내문 초기화
		for (let key in guide) clearInnerHTML(guide[key]);
	}
	function clearMcodeGuide() {
		clearInnerHTML(guide.opt_mcode);
	}
	
	function calculateByteLength(str) { // 문자열 byte 길이 계산
		let len = 0;
		for (let i = 0 ; i < str.length ; i++) {
			let charCode = str.charCodeAt(i);
			if (charCode <= 0x007F) {
	            len += 1; // 1바이트: ASCII 문자
	        } else if (charCode <= 0x07FF) {
	            len += 2; // 2바이트: 확장 ASCII 문자
	        } else {
	            len += 3; // 3바이트: 한글 및 기타 문자
	        }
		}
		return len;
	}
	
	/* ------------ form 취소 ------------ */
	
	function backToHome() {
		if (confirm("취소하고 홈으로 돌아가시겠습니까?"))
			location.href = "/index";
		else {
			colorBtn("NN"); // 수입/지출, 현금/카드 버튼색 초기화
			showSlct("NN"); // 카테고리, 결제수단 드롭다운 초기화
			closeMethBlock(); // 결제수단 블럭 초기화
			clearAllGuide(); // 모든 안내문 초기화
			return false;
		}
	}
	
	/* ------------ form 제출 ------------ */
	
	function checkSubmit(){ // 필수항목 확인, 미선택 no필수항목 안내, 최종 컨펌
		const fmV = { // 모든 입력사항 Object
			seqno : fm.SEQNO.value,
			inex : fm.INEX.value,
			date : fm.DATE.value,
			ccode : fm.CCODE.value,
			slct_in : fm.SLCT_IN.value,
			slct_ex : fm.SLCT_EX.value,
			item : fm.ITEM.value,
			amount : Number(fm.AMOUNT.value.replace(/,/g, '')),
			mncrd : fm.MNCRD.value,
			mcode : fm.MCODE.value,
			slct_mn : fm.SLCT_MN.value,
			slct_crd : fm.SLCT_CRD.value,
		};
		console.log('fmV', fmV);
		printAllGuide(fmV); // 모든 항목 안내문 출력
		if (! alertEssential(fmV) ) return false; // 필수항목 미통과 시 alert, return false
		const detail = makeDetail(fmV);
		if (! confirm("등록하시겠습니까?\n──────\n"+detail) ) return false; // 최종 컨펌
		return true;
	}
	function alertEssential(fmV) { // 필수항목 미통과 시 alert, return false
		if (fmV.date == '') { alert(msg.date); return false; }
		if (fmV.inex == '') { alert(msg.inex); return false; }
		const byteLength = calculateByteLength(fmV.item);
		if (byteLength > 60) { alert(msg.item_length); return false; }
		if (fmV.amount == 0) { alert(msg.amount); return false; }
		if (isNaN(fmV.amount)) { alert(msg.amount_type); return false; }
		return true;
	}
	function printAllGuide(fmV){ // 모든 항목 안내문 출력
		// 필수 사항
		if (fmV.date == '') setInnerHTML(guide.date, msg.date);
		if (fmV.inex == '') setInnerHTML(guide.inex, msg.inex);
		const byteLength = calculateByteLength(fmV.item);
		if (byteLength > 60) setInnerHTML(guide.item_length, msg.item_length);
		if (fmV.amount == '') setInnerHTML(guide.amount, msg.amount);
		if (isNaN(fmV.amount)) setInnerHTML(guide.amount_type, msg.amount_type);
		// 선택 사항
		if (fmV.item == "") setInnerHTML(guide.opt_item, msg.opt_item);
		if (fmV.ccode == "") setInnerHTML(guide.opt_ccode, msg.opt_ccode);
		if (fmV.inex == "EX" && fmV.mcode == '') setInnerHTML(guide.opt_mcode, msg.opt_mcode);
	}
	function makeDetail(fmV) { // 입력 최종확인용 문자열 준비
		let txt_inex = fmV.inex == "IN" ? "수입" : "지출"; 
		let txt_ccode;
		if (fmV.inex == "IN" && fmV.slct_in == "" || fmV.inex == "EX" && fmV.slct_ex == "") 
			txt_ccode = msg.blank;
		else if (fmV.inex == "IN") txt_ccode = slct_in.options[slct_in.selectedIndex].text;
		else if (fmV.inex == "EX") txt_ccode = slct_ex.options[slct_ex.selectedIndex].text;
		let txt_item = fmV.item == "" ? msg.blank : fmV.item;
		let txt_mcode;
		if (fmV.inex == "EX" && fmV.mncrd == "" || 
			fmV.mncrd == "MN" && fmV.slct_mn == "" || 
			fmV.mncrd == "CRD" && fmV.slct_crd == "") {
				txt_mcode = msg.blank;
		}
		else if (fmV.mncrd == "MN") txt_mcode = slct_mn.options[slct_mn.selectedIndex].text;
		else if (fmV.mncrd == "CRD") txt_mcode = slct_crd.options[slct_crd.selectedIndex].text;
		//최종 컨펌 txt
		let detail = `● 구분 : ${txt_inex} ` //-----> ${fmV.inex}`
			+ `\n● 거래날짜 : ${fmV.date}`
			+ `\n● 카테고리 : ${txt_ccode} ` //-----> ${fmV.ccode}`
			+ `\n● 내용 : ${txt_item}`
			+ `\n● 금액 : ${fmV.amount} 원`
		if(fmV.inex == "EX") {
			detail += `\n● 결제수단 : ${txt_mcode} ` //-----> ${fmV.mcode}`;
		}
		console.log('detail', detail);
		return detail;
	}

	/* ------------ 개발자 편의용 ------------ */
	
	// hidden input 표시하기
	const forDeveloper = [seqno, inex, ccode, mncrd, mcode];
	showForDeveloper(forDeveloper, false);
	function showForDeveloper(arr, boolean) {
		if (boolean) {
			for (let i of arr) i.classList.remove('hidden');
		}
	}
		
});
