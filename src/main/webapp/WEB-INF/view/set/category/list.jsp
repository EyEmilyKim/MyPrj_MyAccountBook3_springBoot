<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<main>
	<section class="contMain">
	
		<h2 class="title border">카테고리 목록</h2>
		<div class="count border">총 <span class="cnt">${COUNT }</span> 건</div>
	
	<table class="table borderRed" border="1">
	<thead class="">
	<tr>
		<td class="id">id</td>
		<td class="seqno">seqno</td>
		<td class="cate_code">cate_code</td>
		<td>inex</td>
		<td>cate_name</td>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${LIST }" var="c">
		<c:set var="urlDel" value="del?CCODE=${c.cate_code }"/>	
		<c:set var="urlUpd" value="upd?CCODE=${c.cate_code }"/>	
	  <!-- choose when: system용 '미지정'은 별도 tr로.   -->
	  <!-- otherwise: 사용자용 '수입'/'지출'은 같은 tr 내 일부 td만 다르게 처리  -->
	  <c:choose>
		<c:when test="${c.inex == 'caNN' }">
			<tr id="sys">
				<td class="id">${c.id }</td>
				<td class="seqno">${c.seqno }</td><td class="cate_code">${c.cate_code }</td>
				<td>기본</td><td>${c.cate_name }</td>
				<td class="hidden"><a href="${urlUpd }" onClick="popupUpd(this); return false;">수정</a></td>
				<td class="hidden"><a href="${urlDel }" onClick="popupDel(this); return false;">삭제</a></td>
				<td class="hidden">url : <c:out value="${url }"></c:out></td></tr>
		</c:when>
		<c:otherwise>
			<tr>
				<td class="id">${c.id }</td>
				<td class="seqno">${c.seqno }</td><td class="cate_code">${c.cate_code }</td>
				<c:if test="${c.inex == 'IN' }"><td id="in">수입</td><td>${c.cate_name }</td></c:if>
				<c:if test="${c.inex == 'EX' }"><td id="ex">지출</td><td>${c.cate_name }</td></c:if>
				<td><a class="btn" href="${urlUpd }" onClick="popupUpd(this); return false;">수정</a></td>
				<td><a class="btn" href="${urlDel }" onClick="popupDel(this); return false;">삭제</a></td>
				<td class="">url : <c:out value="${urlUpd}"></c:out></td>
			</tr>
	    </c:otherwise>
	  </c:choose>
	</c:forEach>
	</tbody>
	</table>
	<br>
	<div class="buttons">
	<a  class="btn add" href="add">추가하기</a>
	</div>
	
	</section>

</main>