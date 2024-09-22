<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<main>
	<section class="contMain">
	
		<h2 class="title ">결제수단 목록</h2>
		<div class="count ">총 <span class="cnt">${COUNT }</span> 건</div>
	
	<table class="table " >
	<thead class="label">
	<tr>
		<td class="user_id">user_id</td>
		<td class="seqno">seqno</td>
		<td class="meth_code">meth_code</td>
		<td><span class="hidden">mncrd</span><span>구분</span></td>
		<td><span class="hidden">meth_name</span><span>결제수단</span></td>
		<td><span class="hidden">btn</span><span>편집</span></td>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${LIST }" var="m">
		<c:set var="urlDel" value="del?MCODE=${m.meth_code }"/>	
		<c:set var="urlUpd" value="upd?MCODE=${m.meth_code }"/>	
	  <!-- choose when: system용 '미지정'은 별도 tr로.   -->
	  <!-- otherwise: 사용자용 '현금'/'카드'는 같은 tr 내 일부 td만 다르게 처리  -->
	  <c:choose>
		<c:when test="${m.meth_code == 'meNN0' }">
			<tr class="sys">
				<td class="user_id">${m.user_id }</td>
				<td class="seqno">${m.seqno }</td><td class="meth_code">${m.meth_code }</td>
				<td>기본</td><td>${m.meth_name }</td>
				<td class=""></td>
		</c:when>
		<c:when test="${m.meth_code == 'MN1' }">
			<tr class="sys">
				<td class="user_id">${m.user_id }</td>
				<td class="seqno">${m.seqno }</td><td class="meth_code">${m.meth_code }</td>
				<td class="mn">현금</td><td>${m.meth_name }</td>
				<td class=""></td>
		</c:when>
		<c:otherwise>
			<tr>
				<td class="user_id">${m.user_id }</td>
				<td class="seqno">${m.seqno }</td><td class="meth_code">${m.meth_code }</td>
				<c:if test="${m.mncrd == 'MN' }"><td class="mn">현금</td><td>${m.meth_name }</td></c:if>
				<c:if test="${m.mncrd == 'CRD' }"><td class="crd">카드</td><td>${m.meth_name }</td></c:if>
				<td><a class="btn" href="${urlUpd }" >수정</a>
					<a class="btn" href="${urlDel }" >삭제</a></td>
			</tr>
	    </c:otherwise>
	  </c:choose>
	</c:forEach>
	</tbody>
	</table>
	<br>
	<div class="buttons">
	<a  class="btn crt" href="crt">추가하기</a>
	</div>
	
	</section>

</main>