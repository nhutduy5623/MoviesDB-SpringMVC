<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<!-- Basic need -->
	<meta charset="UTF-8">
	<title>ADMIN</title>
	<meta name="description" content="">
	<meta name="keywords" content="">
	<meta name="author" content="">
	<link rel="profile" href="#">
	
	<!--Google Font-->
	<link rel="stylesheet"
		href='http://fonts.googleapis.com/css?family=Dosis:400,700,500|Nunito:300,400,600' />
	<!-- Mobile specific meta -->
	<meta name=viewport content="width=device-width, initial-scale=1">
	<meta name="format-detection" content="telephone-no">

	<!-- CSS files -->
	<link rel="stylesheet" href="<c:url value='/template/web/css/plugins.css'/>">
	<link rel="stylesheet" href="<c:url value='/template/web/css/style.css'/>">
</head>
<body>

	<!--Header-->
	<%@include file="/common/admin/header.jsp"%>
	<!--Header-->
	
	
	<!--Body-->
	<dec:body/>
	<!--Body-->
	
	<!--Header-->
	<%@include file="/common/web/footer.jsp"%>
	<!--Header-->


	

	<script src="<c:url value='/template/web/js/jquery.js'/>"></script>
	<script src="<c:url value='/template/web/js/plugins.js'/>"></script>
	<script src="<c:url value='/template/web/js/plugins2.js'/>"></script>
	<script src="<c:url value='/template/web/js/custom.js'/>"></script>
	
	

</body>
</html>