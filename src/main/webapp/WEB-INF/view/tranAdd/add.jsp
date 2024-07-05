<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<main>
	<div class="contMain">
		<h3 class="title ">가계부 쓰기 화면입니다.</h3>

		<form action="add" method="post" name="fm" onSubmit="return catchSub()">
		
	<!-- 0.일련번호(hidden)-->
		<div>
			<input type="text" name="SEQNO" value="${requestScope.MSN +1 }" id="seqno" placeholder="SEQNO 자동" />
		</div>
			
	<!-- 1.수입/지출 구분 -->
		<div>
			<input type="text" name="INEX" id="inex" placeholder="INEX 자동" />
			<input type="button" value="수입" id="btn_in" />
			<input type="button" value="지출" id="btn_ex" />
		</div>
		
	<!-- 2.거래날짜 -->
		<div>
			<input type="date" name="DATE" id="date" />
			<input type="button" value="오늘" id="btn_today" />
		</div>

	<!-- 3.카테고리 -->
		<div>	
		<!-- 드롭다운 : 초기화면 -->
			<input type="text" name="CCODE" id="ccode" placeholder="CCODE 자동" />		
			<select name="SLCT_NN" id="slct_nn">
				<option value="">--카테고리(미지정)--</option>
			</select>		
		<!-- 드롭다운 : 수입 -->
			<select name="SLCT_IN" id="slct_in" class="hidden">
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
			<select name="SLCT_EX" id="slct_ex" class="hidden">
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
			<textarea name="ITEM" id="item" placeholder="거래내용을 입력하세요"></textarea>
		</div>
		
	<!-- 5.거래금액 -->
		<div>
			<input type="text" name="AMOUNT" id="amount" placeholder="금액을 입력하세요" />
		</div>

	<!-- 6.결제수단 블럭 -->
		<div id="methBlock" class="hidden">
		<!-- 6-1. 현금/카드 구분 -->
			<div>
				<input type="text" name="MNCRD" id="mncrd" placeholder="MNCRD 자동" /> 
				<input type="button" value="현금" id="btn_mn" />
				<input type="button" value="카드" id="btn_crd" />
			</div>
			
		<!-- 6-2. 결제수단 코드 -->
			<div>
				<input type="text" name="MCODE" id="mcode" placeholder="MCODE 자동" />
			<!-- 드롭다운 : 현금 -->
				<select name="SLCT_MN" id="slct_mn" class="hidden">
					<option value="">--결제수단(현금)--</option>
					<c:forEach items="${METHLIST }" var="m">
						<c:set var="meth_code" value="${m.meth_code }" />
						<c:set var="meth_name" value="${m.meth_name }" />
						<c:if test="${ fn:startsWith(meth_code,'MN') }">
							<option value="${m.meth_code }">${m.meth_name }</option>
						</c:if>
					</c:forEach>
				</select>
			<!-- 드롭다운 : 카드 -->
				<select name="SLCT_CRD" id="slct_crd" class="hidden">
					<option value="">--결제수단(카드)--</option>
					<c:forEach items="${METHLIST }" var="m">
						<c:set var="meth_code" value="${m.meth_code }" />
						<c:set var="meth_name" value="${m.meth_name }" />
						<c:if test="${ fn:startsWith(meth_code,'CRD') }">
							<option value="${m.meth_code }">${m.meth_name }</option>
						</c:if>
					</c:forEach>
				</select>
			</div> <!-- 6-2. 결제수단 코드 끝-->
			
		<!-- 6-3. (확인용 hidden) 전체 결제수단 출력-->
			<div class="hidden">
				<c:forEach items="${METHLIST }" var="m">
					<c:set var="meth_code" value="${m.meth_code }" />
					<c:set var="meth_name" value="${m.meth_name }" />
					<c:out value="${m.meth_code }" /> / <c:out value="${m.meth_name }" /><br>
				</c:forEach>
			</div>
		</div> <!-- 6.결제수단 블럭 끝 -->

	<!-- 7.안내문구 출력row -->
		<div>
			<div class="guideRed">
				<p id="guide_item_length"></p>
				<p id="guide_amount"></p>
			</div>
		</div>

	<!-- 8.form 등록/취소 -->
		<div class="marginTop">
			<input type="submit" value="등록" /> 
			<input type="reset" value="취소" onClick="backToHome()" />
		</div>
			
		</form>

	</div>
	<!-- contMain 끝 -->
</main>
