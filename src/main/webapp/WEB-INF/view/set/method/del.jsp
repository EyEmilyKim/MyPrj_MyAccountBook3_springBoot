<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<main>
	<section class="contMain">
	
		<h2 class="title ">결제수단 삭제 화면입니다.</h2>
		<div class="form ">
		<form action="del" name="fm" onSubmit="return check(this)" method="post">
				<input type="hidden" name="MCODE" value="${M.meth_code }">
				<input type="hidden" name="MNAME" value="${M.meth_name }">
			<div class="content">
				<div>
					<c:if test="${M.mncrd == 'MN' }">
						<input type="button" value="현금" name="S_MNCRD" id="mn"></c:if>
					<c:if test="${M.mncrd == 'CRD' }">
						<input type="button" value="카드" name="S_MNCRD" id="crd" class="align-right"></c:if>
				</div>
				<div>${M.meth_name }</div>
			</div>
			<div class="buttons">
				<input type="submit" value="삭제" class="btn">
				<input type="reset" value="취소" onClick="backToList()" class="btn">
			</div>		
		</form>
		</div>
	
	</section>
</main>
	
<script type="text/javascript">
function check(){
// 	alert("check()호출됨");
	let s_mncrd = ""; switch(document.fm.S_MNCRD.value){
	case "현금" : s_mncrd = "현금"; break; 
	case "카드" : s_mncrd = "카드"; break; }
	let mname = document.fm.MNAME.value;
	let guideConf = "기존에 이 결제수단를 사용해 입력한 가계부 기록의 결제수단 정보가 사라집니다.";
	let detailConf = "구분 : "+s_mncrd+"\n결제수단명 : "+mname;
// 	alert("let OK");
	if(! confirm( guideConf+"\n\n삭제하시겠습니까?\n\n---\n"+detailConf ) ) return false;
}

function backToList(){
	if(confirm("취소하고 목록으로 돌아가시겠습니까?")){
		history.back();
	}
}
</script>
