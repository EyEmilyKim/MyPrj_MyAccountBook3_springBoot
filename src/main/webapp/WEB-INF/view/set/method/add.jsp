<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<main>
	<section class="contMain">

		<h2 class="title ">결제수단 추가 화면입니다.</h2>

		<!-- Form 영역 -->
		<div class="form ">
			<form action="add" name="fm" onSubmit="return check(this)"
				method="post">
				<input type="hidden" name="SEQNO" value="${MSN +1 }">
				<div class="item">
					<div class="mncrd">
						<input type="hidden" name="MNCRD"> <input type="button"
							value="현금" onClick="setMN()" id="btn_mn"> <input
							type="button" value="카드" onClick="setCRD()" id="btn_crd">
					</div>
					<div class="mname">
						<input type="text" placeholder="결제수단명을 입력하세요" value=""
							name="MNAME" id="mname">
					</div>
				</div>
				<div class="buttons">
					<input type="submit" value="등록" class="btn"> <input
						type="reset" value="취소" onClick="backToList()" class="btn">
				</div>
			</form>
		</div>

		<!-- 중복확인용 기존 목록 -->
		<div class="hidden">
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

	let btn_mn = document.getElementById("btn_mn");
	let btn_crd = document.getElementById("btn_crd");
	let slct_name = document.getElementById("slct_name");
	let slct_name_cnt = document.getElementById("slct_name_cnt");

	function setMN() {
		document.fm.MNCRD.value = "MN";
		btn_mn.classList.add('selected_mn');
		btn_crd.classList.remove('selected_crd');
	}
	function setCRD() {
		document.fm.MNCRD.value = "CRD";
		btn_mn.classList.remove('selected_mn');
		btn_crd.classList.add('selected_crd');
	}

	function backToList() {
		if (confirm("취소하고 목록으로 돌아가시겠습니까?")) {
			history.back();
		} else {
			document.fm.MNCRD.value = null;
			btn_mn.classList.remove('selected_mn');
			btn_crd.classList.remove('selected_crd');
		}
	}

	function check() {
		let mncrd = document.fm.MNCRD.value;
		let s_mncrd = mncrd == "CRD" ? "카드" : "현금";
		let mname = document.fm.MNAME.value;
		if (mncrd == '') {
			alert("현금/카드 구분을 선택해주세요."); return false;
		}
		if (mname == '') {
			alert("결제수단명을 입력해주세요."); return false;
		}
		if (!nameDupCheck(mname)) return false; //기존 결제수단명과 중복 확인
		if (!confirm("등록하시겠습니까?\n\n---\n구분 : " + s_mncrd + "\n결제수단명 : " + mname))
			return false;
	}
	function nameDupCheck(val) {
		let cnt = slct_name_cnt.value;
		for (i = 0; i < cnt; i++) {
			if (val == slct_name.options[i].value) {
				alert("이미 사용중인 결제수단명입니다.");
				return false;
			}
		}
		return true;
	}

</script>
