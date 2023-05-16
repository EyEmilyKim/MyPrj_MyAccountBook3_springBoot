<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title><tiles:getAsString name="title"/></title>
	<link href="../../css/set/layout.css" rel="stylesheet" />
</head>

<body onload="startClock()">
<script src="../../js/clock.js"></script>
	
	<!----------- header 부분 ----------->
	<tiles:insertAttribute name="header" />
	
	
	<!----------- 가운데 몸통 부분 ----------->
	<div id="middle">
	
		<!----------- aside 부분 ----------->
		<tiles:insertAttribute name="aside" />
		
		<!----------- main 부분 ----------->
		<tiles:insertAttribute name="main" />
	
	</div>
	<!----------- footer 부분 ----------->
	<tiles:insertAttribute name="footer" />

</body>

</html>