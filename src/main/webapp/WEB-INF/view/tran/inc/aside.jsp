<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<aside>
	<nav id="sub_menu">
		<ul>
			<li><a href="${pageContext.request.contextPath}/tran/listAll" id="tranListAll">모두 보기</a></li>
			<li><a href="${pageContext.request.contextPath}/tran/listIn" id="tranListIn">수입 보기</a></li>
			<li><a href="${pageContext.request.contextPath}/tran/listEx" id="tranListEx">지출 보기</a></li>
		</ul>
	</nav>
</aside>

<script src="${pageContext.request.contextPath}/js/tran/inc/aside.js"></script>
