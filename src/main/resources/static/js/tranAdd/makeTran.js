/**
 * for view/tranAdd/add.jsp
 */
function alertJSloaded() {
	alert("js loaded.")
}

/* 변수 : css 통제를 위한 html 요소 식별 */
const inex = document.getElementById("inex");
const btn_in = document.getElementById("btn_in");
const btn_ex = document.getElementById("btn_ex");
const date = document.getElementById("id");
const ccode = document.getElementById("ccode");
const slct_nn = document.getElementById("slct_nn");
const slct_in = document.getElementById("slct_in");
const slct_ex = document.getElementById("slct_ex");
const row_meth = document.getElementById("row_meth");
const btn_mn = document.getElementById("btn_mn");
const btn_crd = document.getElementById("btn_crd");
const mcode = document.getElementById("mcode");
const slct_mn = document.getElementById("slct_mn");
const slct_crd = document.getElementById("slct_crd");

/* --- form 제출 or 취소 --- */
function catchSub(){
//	alert("catchSub() 호출됨. ");
	const fmValues = { //모든 form 입력 내용을 object 에 담아 전달
		seqno : document.fm.SEQNO.value,
		inex : document.fm.INEX.value,
		date : document.fm.DATE.value,
		ccode : document.fm.CCODE.value,
		slct_in : document.fm.SLCT_IN.value,
		slct_ex : document.fm.SLCT_EX.value,
		item : document.fm.ITEM.value,
		amount : document.fm.AMOUNT.value,
		supMethod : document.fm.SupMETHOD.value,
		mcode : document.fm.MCODE.value,
		slct_mn : document.fm.SLCT_MN.value,
		slct_crd : document.fm.SLCT_CRD.value,
	}
	//no필수 항목 미입력시 안내문 출력해주기 위한 문자열 준비
	const guide = makeGuide(fmValues);	//ok	alert("돌아온 guide : \n"+guide); 
	//check() 통과후 form 내용 최종 확인을 위한 문자열 준비
	const detail = makeDetail(fmValues); //ok	alert("돌아온 detail : \n"+detail); 
//	alert("catchSub() 돌아옴. 끝.");
	return check(fmValues, guide, detail);
}
function check(fmV, guide, detail){ //insert 전 필수항목 확인 & 컨펌 (인자:form입력값,안내문자열,확인문자열)
// 	alert("check() 호출됨. ");
//	alert( isNaN(fmV.amount) );
	//지출or소비
	if(fmV.inex == ''){ alert("지출/소비 구분을 선택해주세요."); return false; }
	//거래날짜
	if(fmV.date == ''){ alert("거래날짜를 선택해주세요."); return false; }
	//여기서 안내문 출력해줌
	document.getElementById("guide").innerHTML = guide;
	//금액
	if(fmV.amount == ''){ alert("금액을 입력해주세요."); return false; }
	else { if(isNaN(fmV.amount)){ alert("금액은 숫자로 입력해주세요."); return false; } }
	//최종 컨펌
	if(! confirm("등록하시겠습니까?\n---\n"+detail) ) return false;
//	if(! confirm("등록될 seqno : "+fmV.seqno) ) return false;	
//	alert("check() 끝");
}
function backToList(){
//	alert("backToList() 호출됨.");
	if(confirm("취소하고 홈화면으로 돌아가시겠습니까?")){
		location.href="index.jsp";
	}else { //안나가고 reset 시 html,css & js_value 수동 초기화(fm 입력 값은 자동 초기화)
		clearBtnAll(); //버튼 색 all초기화 - 수입,지출,현금,카드 
		onoffROW_METH("OFF"); //결제수단 row 초기화 (기본:가려짐)
		showSLCT_XX(); //카테고리 드롭다운 초기화
		clearValINEX();//func로 전달한 INEX 값 초기화.
	}
//	alert("끝");
}

function makeGuide(fmV){ //no필수 항목 미입력시 안내문 출력해주기 위한 문자열 준비
//	alert("makeGuide(fmV) 호출됨. ");
	let guide = "";	
	if(fmV.ccode == ''){  //카테고리 - 미선택시 서블릿에서 디폴트 값 입력
		guide = guide+"카테고리가 선택되지 않았습니다.<br/>"; }
	if(fmV.item == ''){ //내용 - 미입력시 서블릿에서 디폴트 값 입력
		guide = guide+"내용이 비어있습니다.<br/>"; }	
	if(fmV.inex == "EX"){ //결제수단 - 미선택시 서블릿에서 디폴트(수입:미지정,지출:해당없음) 값 입력
		if(fmV.mcode == ''){ guide = guide+"결제수단이 선택되지 않았습니다.<br/>"; } }	
//	alert("만든 guide : \n"+guide); //ok
	return guide;
}
function makeDetail(fmV) { //최종 컨펌을 위한 txt 출력값과 문자열 준비
// 	alert("makeConf(fmV) 호출됨.");
	let inex_txt = "-"; //선택한 수입or지출 구분의 txt명 
	switch(fmV.inex){
	  case "IN" : inex_txt = "수입"; break;
	  case "EX" : inex_txt = "지출"; break;	}
	let ccode_txt = "-"; //select한 [카테고리 코드]의 txt명 
	switch(fmV.inex) {
	  case "IN" : 
		  if(fmV.slct_in !=""){ ccode_txt = slct_in.options[slct_in.selectedIndex].text; } break;
	  case "EX" :
		  if(fmV.slct_ex !=""){ ccode_txt = slct_ex.options[slct_ex.selectedIndex].text; } break; }
	let item_txt = "-"; if(fmV.item !="") item_txt="";	//[내용] 미입력일 때 이거 하나만 보일 txt명
	let mcode_txt = "-"; //select 한 [결제수단 코드]의 txt명
	switch(fmV.supMethod) {
	  case "MN" : 
		  if(fmV.slct_mn !=""){ mcode_txt = slct_mn.options[slct_mn.selectedIndex].text; } break; 
	  case "CRD" :
		  if(fmV.slct_crd !=""){ mcode_txt = slct_crd.options[slct_crd.selectedIndex].text; }	break; 
	  case "" : 
		  if(fmV.inex == "IN"){ mcode_txt = "해당없음"; }	break; }
	//최종 컨펌 txt
	let detail = "● 구분 : "+fmV.inex+" - "+inex_txt
				+"\n● 거래날짜 : "+fmV.date //ok
				+"\n● 카테고리 : "+fmV.ccode+" - "+ccode_txt　//ok
				+"\n● 내용 : "+fmV.item+ item_txt //ok
				+"\n● 금액 : "+fmV.amount+" 원"
				+"\n● 결제수단 : "+fmV.mcode+" - "+mcode_txt;
//	alert("만든 detail : \n"+detail);
// 	alert("makeDetail(fmV) 끝."); 
	return detail;
}
/* --- form 제출 or 취소 끝--- */

/* --- form 입력 과정 func --- */
/* 수입 or 지출 버튼 */
function doIN(){ //[수입]
// 	alert("doIN() 호출됨");
	setINEX("IN"); //수입or지출 구분(INEX) : 수입(IN)
	colorBtnINEX("IN"); //버튼 색 바꾸기
	onoffROW_METH("OFF"); //결제수단 row 가리기
	showSLCT_XX("IN"); //카테고리(수입)만 남기고 가리기
	clearValCCODE(); //카테고리 선택값 초기화
	clearValMCODE(); //결제수단 선택값 초기화
// 	alert("끝");
}
function doEX(){ //[지출]
// 	alert("doEX() 호출됨");
	setINEX("EX"); //수입or지출 구분(INEX) : 지출(EX)
	colorBtnINEX("EX"); //버튼 색 바꾸기
	onoffROW_METH("ON"); //결제수단 row 보여주기
	showSLCT_XX("EX"); //카테고리(지출)만 남기고 가리기
	clearValCCODE(); //카테고리 선택값 초기화
// 	alert("끝");
}
/* 현금 or 카드 버튼 */
function doMN(){ //[현금]버튼 클릭시
// 	alert("doMN() 호출됨");
	setSupMETHOD("MN"); //현금or카드 대분류(SupMETHOD) : 현금(MN)
	colorBtnMNCRD("MN"); //버튼 색 바꾸기
	openSlct("MN"); //결제수단 드롭다운 보여주기 : 현금
	clearValMCODE();//결제수단 선택값 초기화
// 	alert("끝");
}
function doCRD(){ //[카드]버튼 클릭시
// 	alert("doCRD() 호출됨");
	setSupMETHOD("CRD"); //현금or카드 대분류(SupMETHOD) : 현금(CRD)
	colorBtnMNCRD("CRD"); //버튼 색 바꾸기
	openSlct("CRD"); //결제수단 드롭다운 보여주기 : 카드
	clearValMCODE();//결제수단 선택값 초기화
// 	alert("끝");
}
/*[수입/지출] 드롭다운 선택 시 form 의 CCODE 값 자동 입력  */  
function setCCODE(thisS){ //인자: html의 select 요소 자체
//	alert("setCCODE() 호출됨");
//	alert("arg : ["+thisS+"]");
//	alert("this.options[this.selectedIndex].value : "+thisS.options[thisS.selectedIndex].value);
	document.fm.CCODE.value = thisS.options[thisS.selectedIndex].value;
//	alert("setCCODE() 끝");
}
/*[현금/카드] 드롭다운 선택 시 form 의 MCODE 값 자동 입력*/
function setMCODE(thisV){ //인자: html select요소의 this.value
//	alert("setMCODE() 호출됨");
//	alert("arg : ["+thisV+"]");
//	alert("thisV : "+thisV);
	document.fm.MCODE.value = thisV;
//	alert("setMCODE() 끝");
}
/* 거래날짜 오늘로 설정 <- 일단 버려둔 기능..*/
//function setToday(){
//	alert("setToday() 호출됨 ---작성중---");
//	document.fm.DATE.value = new Date();
//	alert("set DATE : "+document.fm.SupMETHOD.value);
//}
/* --- form 입력 과정 func 끝--- */

/* --- form값, css 초기화 func --- */
function clearValINEX(){ //수입or지출 구분(INEX)값 초기화
	inex.value = "";	
}
function clearValCCODE(){ //카테고리 선택값 초기화
// 	alert("BEFORE> slct_nn: "+slct_nn.value+" / slct_in: "+slct_in.value+" / slct_ex: "+slct_ex.value+"\n=> CCODE: "+ccode.value);
	ccode.value = ""; slct_nn.value = ""; slct_in.value = ""; slct_ex.value = "";
// 	alert("AFTER> slct_nn: "+slct_nn.value+" / slct_in: "+slct_in.value+" / slct_ex: "+slct_ex.value+"\n=> CCODE: "+ccode.value);
}
function clearValMCODE(){ //결제수단 선택값 초기화
// 	alert("BEFORE> slct_mn: "+slct_mn.value+" / slct_crd: "+slct_crd.value+"\n=> MCODE: "+mcode.value);
	mcode.value = ""; slct_mn.value = ""; slct_crd.value = "";
// 	alert("AFTER> slct_mn: "+slct_mn.value+" / slct_crd: "+slct_crd.value+"\n=> MCODE: "+mcode.value);
}
function clearBtnAll(){ //버튼 색 all초기화 - 수입,지출,현금,카드
	btn_in.style.backgroundColor = null;
	btn_ex.style.backgroundColor = null;
	btn_mn.style.backgroundColor = null;
	btn_crd.style.backgroundColor = null;
}
/* --- form값, css 초기화 func 끝--- */

/* --- 공통 기능 func --- */
function setINEX(INorEX){ //수입or지출 구분(INEX) 매개변수 받아 설정
	document.fm.INEX.value = INorEX;
// 	alert("set INEX : "+document.fm.INEX.value);
}
function setSupMETHOD(MNorCRD){ //현금or카드 대분류(SupMETHOD) 매개변수 받아 설정
	document.fm.SupMETHOD.value = MNorCRD;
// 	alert("set SupMETHOD : "+document.fm.SupMETHOD.value);
}
function onoffROW_METH(ONorOFF){ //결제수단 row 표시,비표시 (기본:가려짐)
	if(ONorOFF=="ON") row_meth.style.display = "block";
	if(ONorOFF=="OFF") row_meth.style.display = null;
}
function showSLCT_XX(NNINEX){ //카테고리(XX)만 남기고 가리기
	if(NNINEX=="NN") {
		slct_nn.style.display = "block";
		slct_in.style.display = "none";
		slct_ex.style.display = "none";
	}else if(NNINEX=="IN") {
		slct_nn.style.display = "none";
		slct_in.style.display = "block";
		slct_ex.style.display = "none";
	}else if(NNINEX=="EX") {
		slct_nn.style.display = "none";
		slct_in.style.display = "none";
		slct_ex.style.display = "block";
	}else {
		slct_nn.style.display = "none";
		slct_in.style.display = "none";
		slct_ex.style.display = "none";
	}
}
function colorBtnINEX(INorEX){ //버튼 색 바꾸기 - 수입or지출
	if(INorEX=="IN"){ 
		btn_in.style.backgroundColor = "blue";
		btn_ex.style.backgroundColor = null;
	}else if(INorEX=="EX"){ 
		btn_in.style.backgroundColor = null;
		btn_ex.style.backgroundColor = "red";
	}
}
function colorBtnMNCRD(MNorCRD){ //버튼 색 바꾸기 - 현금or카드
	if(MNorCRD=="MN"){ 
		btn_mn.style.backgroundColor = "orange";
		btn_crd.style.backgroundColor = null;
	}else if(MNorCRD=="CRD"){ 
		btn_mn.style.backgroundColor = null;
		btn_crd.style.backgroundColor = "orange";
	}
}
function openSlct(MNorCRD){ //결제수단 드롭다운(현금or카드) 보여주기
	if(MNorCRD=="MN"){ 
		slct_mn.style.display = "block";
		slct_crd.style.display = null;
	}else if(MNorCRD=="CRD"){ 
		slct_mn.style.display = null;
		slct_crd.style.display = "block";
	}
}
/* --- 공통 기능 func 끝 --- */