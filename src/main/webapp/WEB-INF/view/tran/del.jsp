<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	
<main>
	
	<div class="contMain">
		<h2 class="title ">가계부 기록 삭제하기</h2>
		
	
		<div class="listMain">
			
			<table class="tranTable" >
			<thead class="">
				<tr>
					<td class="tran_id">tran_id</td>
					<td>날짜</td>
					<td>구분</td>
					<td class="tran_cate">카테고리</td>
					<td class="tran_amount">내용</td>
					<td class="tran_item">금액</td>
					<td class="tran_meth">결제수단</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="tran_id">${T.tran_id }</td>
					<td><fmt:formatDate value="${T.tran_date}" pattern="yyyy-MM-dd" /></td>
					<c:if test="${T.inex == 'EX' }">
						<td class="ex">지출</td>
						<td class="ex">${T.cate_name}</td>
					</c:if>
					<c:if test="${T.inex == 'IN' }">
						<td class="in">수입</td>
						<td class="in">${T.cate_name}</td>
					</c:if>
					<td class="item">${T.item }</td>
					<td class="amount"><fmt:formatNumber value="${T.amount}" type="number" pattern="#,##0" /> 원</td>
					<c:if test="${T.inex == 'EX' }"><td>${T.meth_name }</td></c:if>
					<c:if test="${T.inex == 'IN' }"><td> - </td></c:if>
				</tr>
			</tbody>
			</table>
		
		</div>
		
		<form action="del" name="fm" method="post">
			<input type="hidden" name="TRAN_ID" value="${T.tran_id }" />
			<input type="hidden" name="PREV_URL" value="" placeholder="PREV_URL 자동" />
			<div class="buttons">
				<input type="submit" value="삭제" class="btn" />
				<input type="reset" value="취소" class="btn" />
			</div>	
		</form>
	
	</div> <!-- contMain 끝 -->
</main>

<script src="../../js/tran/del.js"></script>
