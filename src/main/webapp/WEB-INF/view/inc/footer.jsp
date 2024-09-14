<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<footer>

	<div id="c_values">
		<c:set var="url_blog" value="https://blog.naver.com/kiyukey" />
		<c:set var="url_github" value="https://github.com/EyEmilyKim/MyPrj_MyAccountBook3_springBoot.git" />
	</div>

	<h3>All Right Reserved.2024 EyEmilyKim</h3>
	<ul class="icons">
        <li><a href="${url_blog }"><i id="icon_blog" class="fa-solid fa-blog icon"></i></a></li>
        <li><a href="${url_github }"><i id="icon_github" class="fa-brands fa-github icon"></i></a></li>
        <li><i id="icon_share"class="fa-solid fa-share-nodes"></i></li>
    </ul>
	<div id="clock">지금은... <span id="clock"></span></div>
	
</footer>

<script src="/js/inc/footer.js" ></script>
<script src="https://kit.fontawesome.com/f747bc5595.js" ></script>