<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<main>
	<section class="contMain">
	
	<h2 class="title">결제수단 수정하기</h2>
	
	<!-- Form 영역 -->
	<div class="form"> 
	<form action="upd" name="fm" onSubmit="return check()" method="post">
		<input type="hidden" name="MCODE" value="${M.meth_code }">
		<input type="hidden" name="MNAME" value="${M.meth_name }">
		<div class="content">
			<div class="mncrd">
				<input type="hidden" value="${M.mncrd }" name="MNCRD">
				<c:if test="${M.mncrd == 'MN' }">
				<input type="button" value="현금" onClick="guideMncrd()" id="btn_in"></c:if>
				<c:if test="${M.mncrd == 'CRD' }">
				<input type="button" value="카드" onClick="guideMncrd()" id="btn_ex"></c:if>
			</div>
			<div class="mname">
				<div>현재 결제수단명 : ${M.meth_name }</div>
				<div><input type="text" placeholder="변경할 결제수단명" name="N_MNAME" size="30"></div>
			</div>
		</div>
		<div><span id="guide" class="guideRed"></span></div>
		<div class="buttons">
			<input type="submit" value="저장" class="btn">
			<input type="reset" value="취소" onClick="backToList()" class="btn">		
		</div>
	</form>
	</div>
		
		<!-- 중복확인용 기존 목록 -->
		<div class="hidden"> 
		<p class="textGray">중복 확인용 기준 목록 / 개발자용 임시!!!</p>
			<input type="text" value="${LIST.size() }" id="slct_name_cnt">
			<select class="" id="slct_name">
				<c:forEach items="${LIST }" var="m">
					<option>${m }</option>
				</c:forEach>
			</select>
		</div>
		
	</section>
</main>

<script type="text/javascript">

function guideMncrd(){
	let guideMncrd = "현금/카드 구분은 수정할 수 없습니다.<br/>목록에서 삭제 후 다시 등록해주세요.";
	document.getElementById("guide").innerHTML = guideMncrd;
}
function nameDupCheck(n_mname){
	let slct_name = document.getElementById("slct_name");
	let slct_name_cnt = document.getElementById("slct_name_cnt");
	let cnt = slct_name_cnt.value;
	for(i=0; i<cnt; i++){
		if(n_mname == slct_name.options[i].value){
			alert("이미 사용중인 결제수단명입니다."); return false; }
	}
	return true;
}
function check(){
	document.getElementById("guide").innerHTML = "";
	let mncrd = document.fm.MNCRD.value;
	let s_mncrd = ""; if(mncrd == "MN") s_mncrd = "현금"; else s_mncrd = "카드";
	let mname = document.fm.MNAME.value;
	let n_mname = document.fm.N_MNAME.value;
	let guideConf = "기존에 이 결제수단을 사용해 입력한 가계부 기록에도 변경된 결제수단명이 반영됩니다.";
	let detailConf = "구분 : "+s_mncrd+"\n수정 전 : "+mname+"\n수정 후 : "+n_mname;
	if(n_mname == ''){ alert("결제수단명을 입력해주세요."); return false }
	if(! nameDupCheck(n_mname) ) return false; // 기존 카테고리명과 중복 확인
	if(! confirm( guideConf+"\n\n저장하시겠습니까?\n\n---\n"+detailConf ) ) return false;
}
function backToList(){
	if(confirm("취소하고 목록으로 돌아가시겠습니까?")){
		history.back();
	}else{
		document.getElementById("guide").innerHTML = "";
	}
}

</script>
