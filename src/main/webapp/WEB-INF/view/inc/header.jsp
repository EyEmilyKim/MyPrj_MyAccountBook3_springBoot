<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header>

	<div>
		<c:if test="${ empty sessionScope.USER_ID }">
			<img src="${pageContext.request.contextPath}/images/pig_money.png" class="iconLogo" id="iconLogo_guest">
		</c:if>
		<c:if test="${ !empty sessionScope.USER_ID }">
			<img src="${pageContext.request.contextPath}/images/pig_money_color.png" class="iconLogo" id="iconLogo_user">
		</c:if>
		<h1 id="titleLogo">My 가계부 Ver3.</h1>
	</div>
	
	<nav id="menu">
		<ul>
			<li><a href="${pageContext.request.contextPath}/tran/crt" id="tranCrt">가계부 쓰기</a></li>
			<li><a href="${pageContext.request.contextPath}/tran/listAll" id="tranList">가계부 보기</a></li>
			<li><a href="${pageContext.request.contextPath}/plan" id="plan">계획하기</a></li>
			<li><a href="${pageContext.request.contextPath}/set/category/list" id="set">설정</a></li>
		<c:if test="${ empty sessionScope.USER_ID }">
			<li><a href="${pageContext.request.contextPath}/login" id="login">로그인</a></li>
		</c:if>
		<c:if test="${ !empty sessionScope.USER_ID }">
			<li><a href="${pageContext.request.contextPath}/logout" id="logout">로그아웃</a></li>
		</c:if>
		</ul>
	</nav>
	
</header>

<script type="module" src="${pageContext.request.contextPath}/js/inc/header.js"></script>
