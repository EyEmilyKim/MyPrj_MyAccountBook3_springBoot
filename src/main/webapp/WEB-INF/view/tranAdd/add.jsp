<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<main>
	<div class="contMain">
		<h3>가계부 쓰기 화면입니다.</h3>
		
		<form action="addTransaction.do" method = "post" name="fm" onSubmit="return catchSub()">
<!-- 0.일련번호(hidden)-->
		<input type="hidden" name="SEQNO" value="${requestScope.MSN +1 }">
<!-- 1.수입or지출 구분 -->
		<div><input type="hidden" name="INEX" id="inex">
			<input type="button" value="수입" onClick="doIN()" id="btn_in">
			<input type="button" value="지출" onClick="doEX()" id="btn_ex">
			</div>
<!-- 2.거래날짜 -->
		<div><input type="date" name="DATE" id="date"></div>
<!--  버려둔 버튼	<input type="button" value="오늘" onClick="setToday()"></div></div> -->
<!-- 3.카테고리 -->
	<!-- 드롭다운 : 초기화면 -->
		<div align="center"><input type="text" name="CCODE" id="ccode" placeholder="ccode 자동수신">
				<select name="SLCT_NN" id="slct_nn">
					<option value="">--카테고리(미지정)--</option>
				</select>
	<!-- 드롭다운 : 수입 -->
				<select name="SLCT_IN" id="slct_in" onChange="setCCODE(this)">
					<option value="">--카테고리(수입)--</option>
				<c:forEach items="${CATELIST }" var="c">
				<c:set var="cate_code" value="${c.cate_code }"/>
				<c:set var="cate_name" value="${c.cate_name }"/>
				<c:if test="${ fn:startsWith(cate_code,'IN') }">
					<option value="${c.cate_code }">${c.cate_name }</option>
				</c:if>		
				</c:forEach></select>
	<!-- 드롭다운 : 지출 -->
				<select name="SLCT_EX" id="slct_ex" onChange="setCCODE(this)">
					<option value="">--카테고리(지출)--</option>
				<c:forEach items="${CATELIST }" var="c">
				<c:set var="cate_code" value="${c.cate_code }"/>
				<c:set var="cate_name" value="${c.cate_name }"/>
				<c:if test="${ fn:startsWith(cate_code,'EX') }">
					<option value="${c.cate_code }">${c.cate_name }</option>
				</c:if>		
				</c:forEach></select>
			</div>	
<!-- 4.거래내용 -->
		<div><textarea name="ITEM" placeholder="내용을 입력하세요"
		 			cols="40" rows="5"></textarea></div>
<!-- 5.거래금액 -->
		<div><input type="text" placeholder="금액을 입력하세요" name="AMOUNT"></div>
<!-- 6.결제수단 -->	
	<!-- 현금or카드 버튼 -->
		<div id="row_meth">
			<input type="hidden" name="SupMETHOD">
				<input type="text" name="MCODE" id="mcode"  placeholder="mcode 자동수신">
				<input type="button" value="현금" onClick="doMN()" id="btn_mn">
				<input type="button" value="카드" onClick="doCRD()" id="btn_crd">
				
	<!-- 드롭다운 : 현금 -->
			<div align="center"><select name="SLCT_MN" id="slct_mn" onChange="setMCODE(this.value)">
					<option value="">--결제수단(현금)--</option>
				<c:forEach items="${METHLIST }" var="m">
				<c:set var="meth_code" value="${m.meth_code }"/>
				<c:set var="meth_name" value="${m.meth_name }"/>
				<c:if test="${ fn:startsWith(meth_code,'MN') }">
					<option value="${m.meth_code }">${m.meth_name }</option>
				</c:if>		
				</c:forEach></select>
	<!-- 드롭다운 : 카드 -->
				<select name="SLCT_CRD" id="slct_crd" onChange="setMCODE(this.value)">
					<option value="">--결제수단(카드)--</option>
				<c:forEach items="${METHLIST }" var="m">
				<c:set var="meth_code" value="${m.meth_code }"/>
				<c:set var="meth_name" value="${m.meth_name }"/>
				<c:if test="${ fn:startsWith(meth_code,'CRD') }">
					<option value="${m.meth_code }">${m.meth_name }</option>
				</c:if>		
				</c:forEach></select>
			</div></div>	
	<!-- (확인용 hidden) 전체 결제수단 출력-->
		<div class="hidden">
			<div><c:forEach items="${METHLIST }" var="m">
				<%-- <c:set var="meth_code" value="${m.meth_code }"/>
				<c:set var="meth_name" value="${m.meth_name }"/> --%>
				${m.meth_code } / ${m.meth_name }<br>
<%-- 				<c:out value="${m.meth_code }"/> / <c:out value="${m.meth_name }"/><br> --%>
				</c:forEach>
			</div></div>
		<div class="hidden test"><div><input type="text" name="TEST1"></div></div>	
		<div class="hidden test"><div><input type="text" name="TEST2"></div></div>	
		<div class="hidden test"><div><input type="text" name="TEST3"></div></div>	
<!-- 7.안내문구 출력row -->			
		<div><div id="guide"></div></div>	
<!-- 8.form 등록/취소 -->			
		<div><div><br>
			<input type="submit" value="등록">
			<input type="reset" value="취소" onClick="backToList()"></div></div>
	</form>
		
	</div> <!-- contMain 끝 -->
</main>
