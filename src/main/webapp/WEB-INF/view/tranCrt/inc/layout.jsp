<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<tiles:insertAttribute name="meta" />
	<title><tiles:getAsString name="title"/></title>
	<link href="${pageContext.request.contextPath}/css/tranCrt/layout.css" rel="stylesheet" /> 
</head>

<body>
	
	<!----------- header 부분 ----------->
	<tiles:insertAttribute name="header" />
	
	
	<!----------- 가운데 몸통 부분 ----------->
	<div id="middle">
	
		<!----------- aside 부분 ----------->
		<tiles:insertAttribute name="aside" />
		
		<!----------- main 부분 ----------->
		<tiles:insertAttribute name="main" />
	
	</div>
	
	<!----------- modal 부분 ----------->
	<tiles:insertAttribute name="modal" />

	<!----------- footer 부분 ----------->
	<tiles:insertAttribute name="footer" />

</body>

<script type="text/javascript">
    const contextPath = '${pageContext.request.contextPath}';
</script>

</html>
