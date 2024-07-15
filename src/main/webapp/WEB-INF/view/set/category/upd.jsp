<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<main>
	<section class="contMain">
	
	<h2 class="title">카테고리 수정하기</h2>
	
	<!-- Form 영역 -->
	<div class="form"> 
	<form action="upd" name="fm" onSubmit="return check()" method="post">
		<input type="hidden" name="CCODE" value="${C.cate_code }">
		<input type="hidden" name="CNAME" value="${C.cate_name }">
		<div class="content">
			<div class="inex">
				<input type="hidden" value="${C.inex }" name="INEX">
				<c:if test="${C.inex == 'IN' }">
				<input type="button" value="수입" onClick="guideInex()" id="btn_in"></c:if>
				<c:if test="${C.inex == 'EX' }">
				<input type="button" value="지출" onClick="guideInex()" id="btn_ex"></c:if>
			</div>
			<div class="cname">
				<div>현재 카테고리명 : ${C.cate_name }</div>
				<div><input type="text" placeholder="변경할 카테고리명" name="N_CNAME" size="30"></div>
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
				<c:forEach items="${LIST }" var="c">
					<option>${c }</option>
				</c:forEach>
			</select>
		</div>
		
	</section>
</main>

<script type="text/javascript">
function guideInex(){
// 	alert("guideInex()호출됨");
	let guideInex = "수입/지출 구분은 수정할 수 없습니다.<br/>목록에서 삭제 후 다시 등록해주세요.";
	document.getElementById("guide").innerHTML = guideInex;
}
function nameDupCheck(n_cname){
// 	alert("nameDupCheck()호출됨");
	let slct_name = document.getElementById("slct_name");
	let slct_name_cnt = document.getElementById("slct_name_cnt");
	let cnt = slct_name_cnt.value;
	for(i=0; i<cnt; i++){
		if(n_cname == slct_name.options[i].value){
			alert("이미 사용중인 카테고리명입니다."); return false; }
	}
	return true;
}
function check(){
// 	alert("check()호출됨");
	document.getElementById("guide").innerHTML = "";
	let inex = document.fm.INEX.value;
	let s_inex = ""; if(inex == "EX") s_inex = "지출"; else s_inex = "수입";
	let cname = document.fm.CNAME.value;
	let n_cname = document.fm.N_CNAME.value;
	let guideConf = "기존에 이 카테고리를 사용해 입력한 가계부 기록에도 변경된 카테고리명이 반영됩니다.";
	let detailConf = "구분 : "+s_inex+"\n수정 전 : "+cname+"\n수정 후 : "+n_cname;
// 	alert("let OK");
	if(n_cname == ''){ alert("카테고리명을 입력해주세요."); return false }
	//기존 카테고리명과 중복 확인
	if(! nameDupCheck(n_cname) ) return false;
	if(! confirm( guideConf+"\n\n저장하시겠습니까?\n\n---\n"+detailConf ) ) return false;
// 	if(! confirm("수정될 cate_code : "+document.fm.CCODE.value) ) return false;
}
function backToList(){
	if(confirm("취소하고 목록으로 돌아가시겠습니까?")){
		history.back();
	}else{
		document.getElementById("guide").innerHTML = "";
	}
}
</script>