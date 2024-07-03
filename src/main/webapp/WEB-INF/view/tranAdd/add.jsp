<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<main>
	<div class="contMain">
		<h3>가계부 쓰기 화면입니다.</h3>

		<form action="add" method="post" name="fm" onSubmit="return catchSub()">
		
	<!-- 0.일련번호(hidden)-->
		<div>
			<input type="hidden" name="SEQNO" value="${requestScope.MSN +1 }">
		</div>
			
	<!-- 1.수입/지출 구분 -->
		<div>
			<input type="text" name="INEX" id="inex" size ="5" placeholder="자동변경"> 
			<input type="button" value="수입" onClick="doIN()" id="btn_in"> 
			<input type="button" value="지출" id="btn_ex">
		</div>
		
	<!-- 2.거래날짜 -->
		<div>
			<input type="date" name="DATE" id="date">
			<input type="button" value="오늘" id="btn_today">
		</div>

	<!-- 3.카테고리 -->
		<div>	
		<!-- 드롭다운 : 초기화면 -->
			<input type="text" name="CCODE" id="ccode" placeholder="ccode 자동수신">		
			<select name="SLCT_NN" id="slct_nn">
				<option value="">--카테고리(미지정)--</option>
			</select>		
		<!-- 드롭다운 : 수입 -->
			<select name="SLCT_IN" id="slct_in" class="hidden" onChange="setCCODE(this)">
				<option value="">--카테고리(수입)--</option>
				<c:forEach items="${CATELIST }" var="c">
					<c:set var="cate_code" value="${c.cate_code }" />
					<c:set var="cate_name" value="${c.cate_name }" />
					<c:if test="${ fn:startsWith(cate_code,'IN') }">
						<option value="${c.cate_code }">${c.cate_name }</option>
					</c:if>
				</c:forEach>
			</select> 	
		<!-- 드롭다운 : 지출 -->
			<select name="SLCT_EX" id="slct_ex" class="hidden" onChange="setCCODE(this)">
				<option value="">--카테고리(지출)--</option>
				<c:forEach items="${CATELIST }" var="c">
					<c:set var="cate_code" value="${c.cate_code }" />
					<c:set var="cate_name" value="${c.cate_name }" />
					<c:if test="${ fn:startsWith(cate_code,'EX') }">
						<option value="${c.cate_code }">${c.cate_name }</option>
					</c:if>
				</c:forEach>
			</select>		
		</div> <!-- 3.카테고리 끝 -->

	<!-- 4.거래내용 -->
		<div>
			<textarea name="ITEM" placeholder="내용을 입력하세요" cols="20" rows="3"></textarea>
		</div>
		
	<!-- 5.거래금액 -->
		<div>
			<input type="text" placeholder="금액을 입력하세요" name="AMOUNT" id="amount">
		</div>
		
	<!-- 8.form 등록/취소 -->
			<div class="marginTop">
				<input type="submit" value="등록"> 
				<input type="reset" value="취소" onClick="backToHome()">
			</div>
			
		</form>

	</div>
	<!-- contMain 끝 -->
</main>
