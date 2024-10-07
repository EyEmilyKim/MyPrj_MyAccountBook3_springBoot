<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<footer>

	<div id="c_values">
		<c:set var="url_blog" value="https://blog.naver.com/kiyukey" />
		<c:set var="url_github" value="https://github.com/EyEmilyKim/MyPrj_MyAccountBook3_springBoot.git" />
		<c:set var="tooltip_blog" value="개발자 블로그" />
		<c:set var="tooltip_github" value="깃허브 링크" />
		<c:set var="tooltip_share" value="공유하기" />
		<c:set var="tooltip_email" value="개발자에게 문의하기" />
	</div>

	<h3>All Right Reserved.2024 EyEmilyKim</h3>
	
	<ul class="icons">
        <li><a href="${url_blog }" title="${tooltip_blog }"><i id="icon_blog" class="fa-solid fa-blog icon"></i></a></li>
        <li><a href="${url_github }" title="${tooltip_github }"><i id="icon_github" class="fa-brands fa-github icon"></i></a></li>
        <li><i id="icon_share"class="fa-solid fa-share-nodes" title="${tooltip_share }"></i></li>
        <li><i id="icon_email"class="fa-solid fa-envelope" title="${tooltip_email }"></i></li>
    </ul>
    
	<div id="clock">지금은... <span id="clock"></span></div>
	
</footer>

<script type="module" src="${pageContext.request.contextPath}/js/home/inc/footer.js" ></script>
<script src="https://kit.fontawesome.com/f747bc5595.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
