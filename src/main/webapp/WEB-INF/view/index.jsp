<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<main>
	<section>
		<h1 id="mainTitle">스프링Boot로 만들어보는 My가계부3 ~♪</h1>
		<div><img src="${pageContext.request.contextPath}/images/graph.png" width="300px" id="mainImage"/></div>
	<c:if test="${! empty sessionScope.NICKNAME }">
		<h2 id="guide_main_1">반갑습니다 ${sessionScope.NICKNAME }님~~</h2>
		<div><img src="${pageContext.request.contextPath}/images/animals_hi.png" width="200px" /></div>
	</c:if>
	<c:if test="${ empty sessionScope.NICKNAME }">
		<div><img src="${pageContext.request.contextPath}/images/animals_back.png" width="200px"  style="margin-top:-50px;"/></div>
	</c:if>
	</section>
</main>
