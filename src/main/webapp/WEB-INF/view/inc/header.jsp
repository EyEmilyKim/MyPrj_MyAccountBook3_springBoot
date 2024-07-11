<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	

<header>
	<div id="home">
		<a href="/index"><h1>My 가계부 Ver3.</h1></a>
	</div>
	<nav id="menu">
		<ul>
			<li><a href="/tran/add">가계부 쓰기</a></li>
			<li><a href="/tran/listAll">가계부 보기</a></li>
			<li><a href="/plan">월별 계획</a></li>
			<li><a href="/set/category/list">설정</a></li>
		<c:if test="${empty sessionScope.USER_ID }">
			<li><a href="/login">로그인</a></li>
		</c:if>
		<c:if test="${! empty sessionScope.USER_ID }">
			<li><a href="/logout" onclick="return check()">로그아웃</a></li>
		</c:if>
		</ul>
	</nav>
</header>

<script>
function check(){
	if(!confirm("정말 로그아웃하시겠습니까?")) return false;
}
</script>