/* for view/tranAdd/add.jsp */

document.addEventListener('DOMContentLoaded', () => {

	/* ------------ form 요소 식별 변수 ------------ */
	
	const btn_yesterday = document.getElementById("btn_yesterday");
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
	};
	
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
	
	const isInvalid = {
		date : () => { const flag = fm.DATE.value == "" ? true : false; return flag; },
		inex : () => { const flag = fm.INEX.value == "" ? true : false; return flag; },
		amount : () => { const flag = fm.AMOUNT.value.trim() == "" ? true : false; return flag; },
		amount_type : () => { const flag = isNaN(fm.AMOUNT.value.replace(/,/g, '')) ? true : false; return flag; },
		item_length : () => { const flag = calculateByteLength(fm.ITEM.value.trim()) > 60 ? true : false; return flag; },
		opt_item : () => { const flag = fm.ITEM.value.trim() == "" ? true : false; return flag; },
		opt_ccode : () => { const flag = fm.CCODE.value == "" ? true : false; return flag; },
		opt_mcode : () => { const flag = fm.MCODE.value == "" ? true : false; return flag; },
	};

	const validate = {
		date : () => { isInvalid.date() ? setInnerHTML(guide.date, msg.date) : clearInnerHTML(guide.date); },
		inex : () => { isInvalid.inex() ? setInnerHTML(guide.inex, msg.inex) : clearInnerHTML(guide.inex); },
		amount : () => {
			isInvalid.amount() ? setInnerHTML(guide.amount, msg.amount) : clearInnerHTML(guide.amount); 
			isInvalid.amount_type() ? setInnerHTML(guide.amount_type, msg.amount_type) : clearInnerHTML(guide.amount_type);
		},
		item : () => {
			isInvalid.item_length() ? setInnerHTML(guide.item_length, msg.item_length) : clearInnerHTML(guide.item_length);
			isInvalid.opt_item() ? setInnerHTML(guide.opt_item, msg.opt_item) : clearInnerHTML(guide.opt_item);
		},
		opt_ccode : () => { isInvalid.opt_ccode() ? setInnerHTML(guide.opt_ccode, msg.opt_ccode) : clearInnerHTML(guide.opt_ccode); },
		opt_mcode : () => { isInvalid.opt_mcode() ? setInnerHTML(guide.opt_mcode, msg.opt_mcode) : clearInnerHTML(guide.opt_mcode); },
	};
	
	/* ------------ form 입력 과정 func ------------ */
	
	// 1. 어제, 오늘 날짜 설정. 날짜 미입력 안내
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
		const val = date.value
		const today = getFormattedDate(0);
		const yesterday = getFormattedDate(-1);
		if(val == today) colorBtn("TODAY");
		else if(val == yesterday) colorBtn("YESTERDAY");
		else colorBtn("PICK")
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
		validate.item();
	})
	
	// 5. 금액 세자리 콤마 표시, 타입 제한 안내
	amount.addEventListener('input', () => {
		let val = fm.AMOUNT.value.replace(/,/g, '');
		if (!isNaN(val) && val.trim() !== "") {
			val = parseInt(val, 10).toLocaleString('ko-KR');
			amount.value = val;
		}
		validate.amount();
	})

	// 6-1. 현금 or 카드 설정 => 6-2. 결제수단 드롭다운
	btn_mn.addEventListener('click', () => {
		setMNCRD("MN");
		colorBtn("MN");
		showSlct("MN");
		resetSLCT_MNCRD(); // slct_mn, slct_crd 선택상태 + MCODE 입력값 초기화
		validate.opt_mcode();
	})
	btn_crd.addEventListener('click', () => {
		setMNCRD("CRD");
		colorBtn("CRD");
		showSlct("CRD");
		resetSLCT_MNCRD(); // slct_mn, slct_crd 선택상태 + MCODE 입력값 초기화
		validate.opt_mcode();
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
			case "PICK":
				btn_yesterday.classList.remove('selected_date');
				btn_today.classList.remove('selected_date'); break;
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
				slct_nn_c.classList.remove('hidden');
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
		slct_in.value = "";
		slct_ex.value = "";
		setCCODE("");
	}
	function resetSLCT_MNCRD() { // slct_mn, slct_crd 선택상태 + MCODE 입력값 초기화
		slct_mn.value = "";
		slct_crd.value = "";
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
		methBlock.style.display = 'flex';
	}
	function closeMethBlock() { // 결제수단 블럭 초기화, 숨기기
		resetMethBlock();
		methBlock.style.display = 'none';
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
		//console.log('checkSubmit() called');
		const fmV = { // 모든 입력사항 Object
			my_seqno : fm.MY_SEQNO.value,
			date : fm.DATE.value,
			inex : fm.INEX.value,
			ccode : fm.CCODE.value,
			slct_in : slct_in.value,
			slct_ex : slct_ex.value,
			item : fm.ITEM.value,
			amount : fm.AMOUNT.value,
			mncrd : fm.MNCRD.value,
			mcode : fm.MCODE.value,
			slct_mn : slct_mn.value,
			slct_crd : slct_crd.value,
		};
		console.log('fmV', fmV);
		printAllGuide(); // 모든 항목 안내문 출력
		if (! checkEssential() ) return false; // 필수항목 미통과 시 return false
		const detail = makeDetail(fmV);
		if (! confirm("등록하시겠습니까?\n──────\n"+detail) ) return false; // 최종 컨펌
		fm.AMOUNT.value = Number(fmV.amount.replace(/,/g, ''));
		return true;
	}
	function checkEssential() { // 필수항목 미통과 시 return false
		//console.log('checkEssential() called');
		if (isInvalid.date()) return false;
		if (isInvalid.inex()) return false;
		if (isInvalid.amount() || isInvalid.amount_type()) return false;
		if (isInvalid.item_length()) return false;
		return true;
	}
	function printAllGuide(){ // 모든 항목 안내문 출력
		//console.log('printAllGuide() called');
		// 필수 사항
		validate.date();
		validate.inex();
		validate.amount();
		// 선택 사항
		validate.opt_item;
		validate.opt_ccode();
		if (fm.INEX.value== "EX") validate.opt_mcode();
	}
	function makeDetail(fmV) { // 입력 최종확인용 문자열 준비
		//console.log('makeDetail() called');
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
	const forDeveloper = [my_seqno, inex, ccode, mncrd, mcode];
	showForDeveloper(forDeveloper, false);
	function showForDeveloper(arr, boolean) {
		if (boolean) {
			for (let i of arr) i.classList.remove('hidden');
		}
	}
		
});
