<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<main>
	<div class="contMain">
		<h3>가계부 쓰기 화면입니다.</h3>

		<form action="add" method="post" name="fm" onSubmit="return catchSub()">
		
	<!-- 8.form 등록/취소 -->
			<div class="marginTop">
				<input type="submit" value="등록"> 
				<input type="reset" value="취소" onClick="backToHome()">
			</div>
			
		</form>

	</div>
	<!-- contMain 끝 -->
</main>
