<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<main>
	<div class="contMain">
		<h2 class="title ">가계부 목록 화면입니다.</h2>
	
		<div class="count ">총 <span class="cnt">${COUNT }</span> 건</div>
		
		<!-- 검색 블럭 -->
		<div id="searchBlock">
			<form name="fmSRCH" id="fmSRCH">
				<input type="date" name="D_FROM" value="${param.D_FROM}" id="search_d_from" />
				 ~ <input type="date" name="D_TO" value="${param.D_TO}" id="search_d_to" /> 
				<input type="text" name="ITEM" value="${param.ITEM}" placeholder="--내용--" id="search_item" />
				<input type="submit" value="조회하기" id="search_submit" />
				<c:if test="${not empty param}">
					<input type="button" value="전체보기" id="search_reset" />		
				</c:if>
			</form>
		</div>	<!-- 검색 블럭 끝 -->
	
	<!-- 실제 목록 -->
		<div class="listMain">
		<!-- 거래내역 없음 -->
			<c:if test="${COUNT == 0 }">
			<div class="noTran">
				<p>거래내역이 없습니다.</p>
				<a href="/tran/add">가계부 쓰러 가기</a>
			</div>
			</c:if>
			
			<c:if test="${COUNT > 0 }">
		<!-- 거래내역 테이블 -->
			<table class="table " >
			<thead class="">
				<tr>
					<td class="tran_id">tran_id</td>
					<td>날짜</td>
					<td>구분</td>
					<td>카테고리</td>
					<td>내용</td>
					<td>금액</td>
					<td>결제수단</td>
					<td>btn</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${LIST }" var="t">
				  <c:set var = "urlUpd" value="del?TRAN_ID=${t.tran_id }" />
				  <c:set var = "urlDel" value="del?TRAN_ID=${t.tran_id }" />
					<tr>
						<td class="tran_id">${t.tran_id }</td>
						<td><fmt:formatDate value="${t.tran_date}" pattern="yyyy-MM-dd" /></td>
						<c:if test="${t.inex == 'EX' }">
							<td class="ex">지출</td>
							<td class="ex">${t.cate_name}</td>
						</c:if>
						<c:if test="${t.inex == 'IN' }">
							<td class="in">수입</td>
							<td class="in">${t.cate_name}</td>
						</c:if>
						<td class="item">${t.item }</td>
						<td class="amount"><fmt:formatNumber value="${t.amount}" type="number" pattern="#,##0" /> 원</td>
						<c:if test="${t.inex == 'EX' }"><td>${t.meth_name }</td></c:if>
						<c:if test="${t.inex == 'IN' }"><td> - </td></c:if>
						<td><a class="btn" href="${urlUpd }" >수정</a>
							<a class="btn" href="${urlDel }" >삭제</a></td>
					</tr>
				</c:forEach>
			</tbody>
			
			</table>
			</c:if>
		</div> <!-- 실제 목록 끝 -->
	
	
	
	
	</div> <!-- contMain 끝 -->
</main>

