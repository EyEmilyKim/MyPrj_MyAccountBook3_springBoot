/* for view/tranAdd/add.jsp */

document.addEventListener('DOMContentLoaded', () => {

	const btn_today = document.getElementById("btn_today");
	const btn_in = document.getElementById("btn_in");
	const btn_ex = document.getElementById("btn_ex");
	const slct_nn = document.getElementById("slct_nn");
	const slct_in = document.getElementById("slct_in");
	const slct_ex = document.getElementById("slct_ex");
	const item = document.getElementById("item");
	const amount = document.getElementById("amount");
	const methBlock = document.getElementById("methBlock");
	const btn_mn = document.getElementById("btn_mn");
	const btn_crd = document.getElementById("btn_crd");
	const slct_mn = document.getElementById("slct_mn");
	const slct_crd = document.getElementById("slct_crd");
	const guide = {
		item_length : document.getElementById("guide_item_length"),
		amount : document.getElementById("guide_amount"),
		opt_ccode : document.getElementById("guide_opt_ccode"),
		opt_item : document.getElementById("guide_opt_item"),
		opt_mcode : document.getElementById("guide_opt_mcode"),
	}
	
	const fm = document.forms['fm'];
	
	const msg = {
		inex : "지출/소비 구분을 선택해주세요.",
		date : "거래날짜를 선택해주세요.",
		itemLength : "거래내용은 최대 20자 한글 또는 60자 영문/숫자로 입력해주세요.",
		amount : "금액을 입력해주세요.",
		amountType : "금액은 숫자로 입력해주세요.",
		opt_ccode : "카테고리가 선택되지 않았습니다.",
		opt_item : "거래내용이 비어있습니다.",
		opt_mcode : "결제수단이 선택되지 않았습니다.",
		blank : " - ",
	};
	
	/* ------------ form 입력 과정 func ------------ */
	
	// 1. 오늘 날짜 설정
	btn_today.addEventListener('click', () => {
		const today = new Date();
		const year = today.getFullYear();
		const month = String(today.getMonth() + 1).padStart(2, '0');
		const day = String(today.getDate()).padStart(2, '0');
		const formattedDate = `${year}-${month}-${day}`;
		document.fm.DATE.value = formattedDate;
	})

	// 2. 수입 or 지출 설정 => 3. 카테고리 드롭다운
	btn_in.addEventListener('click', () => {
		setINEX("IN");
		colorBtn("IN");
		showSlct("IN");
		resetSLCT_INEX(); // slct_in, slct_ex 선택상태 + CCODE 입력값 초기화
		closeMethBlock(); // 결제수단 블럭 초기화, 숨기기
	})
	btn_ex.addEventListener('click', () => {
		setINEX("EX");
		colorBtn("EX");
		showSlct("EX");
		resetSLCT_INEX(); // slct_in, slct_ex 선택상태 + CCODE 입력값 초기화
		openMethBlock(); // 결제수단 블럭 초기화, 보이기
	})

	// 3. 카테고리 드롭다운 선택 시 CCODE 설정, 미선택 안내(no필수)
	const forCCODE = [slct_in, slct_ex];
	for (let i of forCCODE) {
		i.addEventListener('change', () => {
			setCCODE(i.value);
			if (ccode.value == "") guide.opt_ccode.innerHTML = msg.opt_ccode;
			else guide.opt_ccode.innerHTML = "";
		})
	}
	
	// 4. 거래내용 입력값 길이 제한 안내, 미입력 안내(no필수)
	item.addEventListener('input', () => {
		const val = item.value; 
		const byteLength = calculateByteLength(val);
		if (byteLength > 60) guide.item_length.innerHTML = msg.itemLength;
		else guide.item_length.innerHTML = "";
		if (val == "") guide.opt_item.innerHTML = msg.opt_item;
		else guide.opt_item.innerHTML = "";
	})
	
	// 5. 금액 세자리마다 ',' 표시
	amount.addEventListener('input', () => {
		let val = amount.value.replace(/,/g, '');
		if (!isNaN(val) && val.trim() !== "") {
			val = parseInt(val, 10).toLocaleString('ko-KR');
			amount.value = val;
		}
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
	
	// 6-2. 결제수단 드롭다운 선택 시 MCODE 설정, 미선택 안내(no필수)
	const forMCode = [slct_mn, slct_crd];
	for (let i of forMCode) {
		i.addEventListener('change', () => {
			setMCODE(i.value);
			if (mcode.value == "") guide.opt_mcode.innerHTML = msg.opt_mcode;
			else guide.opt_mcode.innerHTML = "";
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
	
	function setINEX(v) { // INEX 입력값 설정
		document.fm.INEX.value = v;
	}
	function setMNCRD(v) { // MNCRD 입력값 설정
		document.fm.MNCRD.value = v;
	}
	function setCCODE(v) { // CCODE 입력값 설정
		document.fm.CCODE.value = v;
	}
	function setMCODE(v) { // MCODE 입력값 설정
		document.fm.MCODE.value = v;
	}
	
	function resetSLCT_INEX() { // slct_in, slct_ex 선택상태 + CCODE 입력값 초기화
		document.fm.SLCT_IN.value = "";
		document.fm.SLCT_EX.value = "";
		setCCODE("");
	}
	function resetSLCT_MNCRD() { // slct_mn, slct_crd 선택상태 + MCODE 입력값 초기화
		document.fm.SLCT_MN.value = "";
		document.fm.SLCT_CRD.value = "";
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
	
	function clearAllGuide() { // 모든 안내문 초기화
		for (let key in guide) guide[key].innerHTML = ""
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
			seqno : document.fm.SEQNO.value,
			inex : document.fm.INEX.value,
			date : document.fm.DATE.value,
			ccode : document.fm.CCODE.value,
			slct_in : document.fm.SLCT_IN.value,
			slct_ex : document.fm.SLCT_EX.value,
			item : document.fm.ITEM.value,
			amount : Number(document.fm.AMOUNT.value.replace(/,/g, '')),
			mncrd : document.fm.MNCRD.value,
			mcode : document.fm.MCODE.value,
			slct_mn : document.fm.SLCT_MN.value,
			slct_crd : document.fm.SLCT_CRD.value,
		};
		console.log('fmV', fmV);
		// no필수항목 미입력 안내문 출력
		printOptGuide(fmV);
		// 필수항목 확인
		if (fmV.inex == '') { alert(msg.inex); return false; }
		if (fmV.date == '') { alert(msg.date); return false; }
		const byteLength = calculateByteLength(fmV.item);
		if (byteLength > 60) { alert(msg.itemLength);
			guide.item_length.innerHTML = msg.itemLength; return false; }
		if (fmV.amount == 0) { alert(msg.amount); return false; }
		if (isNaN(fmV.amount)) { alert(msg.amountType); return false; }
		// 최종 컨펌
		const detail = makeDetail(fmV);
		if (! confirm("등록하시겠습니까?\n──────\n"+detail) ) return false;
		return true;
	}
	
	function printOptGuide(fmV){ // no필수항목 미입력 안내문 출력
		if (fmV.ccode == "") guide.opt_ccode.innerHTML = msg.opt_ccode;
		if (fmV.item == "") guide.opt_item.innerHTML = msg.opt_item;
		if (fmV.inex == "EX" && fmV.mcode == '') guide.opt_mcode.innerHTML = msg.opt_mcode;
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
