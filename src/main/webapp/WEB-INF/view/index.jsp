<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<main>
	<section>
		<h1 class="margin-bot">스프링Boot로 만들어보는 My가계부3 ~♪</h1>
	<c:if test="${! empty sessionScope.NICKNAME }">	
		<h2 class="margin-bot">반갑습니다 ${sessionScope.NICKNAME }님~~</h2>
	</c:if>
		<div><img src="images/ocean.jpg" width="200px" /></div>
		<div><img src="images/image.png" width="200px" /></div>
	</section>
</main>
