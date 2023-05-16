<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<main>
	<section class="contMain">
	
		<h2 class="title border">카테고리 삭제 화면입니다.</h2>
		<div class="form borderRed">
		<form action="del" name="fm" onSubmit="return check(this)" method="post">
				<input type="hidden" name="CCODE" value="${C.cate_code }">
				<input type="hidden" name="CNAME" value="${C.cate_name }">
			<div class="item">
				<div>
					<c:if test="${C.inex == 'IN' }">
						<input type="button" value="수입" name="S_INEX" id="in"></c:if>
					<c:if test="${C.inex == 'EX' }">
						<input type="button" value="지출" name="S_INEX" id="ex" class="align-right"></c:if>
				</div>
				<div>${C.cate_name }</div>
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
	let s_inex = ""; switch(document.fm.S_INEX.value){
	case "수입" : s_inex = "수입"; break; 
	case "지출" : s_inex = "지출"; break; }
	let cname = document.fm.CNAME.value;
	let guideConf = "기존에 이 카테고리를 사용해 입력한 가계부 기록의 카테고리 정보가 사라집니다.";
	let detailConf = "구분 : "+s_inex+"\n카테고리명 : "+cname;
// 	alert("let OK");
	if(! confirm( guideConf+"\n\n삭제하시겠습니까?\n\n---\n"+detailConf ) ) return false;
}

function backToList(){
	if(confirm("취소하고 목록으로 돌아가시겠습니까?")){
		history.back();
	}
}
</script>
