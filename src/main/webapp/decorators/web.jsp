<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

	<!-- Basic need -->
	<meta charset="UTF-8">
	<title>Trang Chủ</title>
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
	<%@include file="/common/web/header.jsp"%>
	<!--Header-->
	
	<!--LoginForm-->
	<%@include file="/common/web/login.jsp"%>
	<!--LoginForm-->
	
	
	<!--Body-->
	<dec:body/>
	<!--Body-->
	
	<!--Header-->
	<%@include file="/common/web/footer.jsp"%>
	<!--Header-->


	<!-- bootstrap -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

	<script src="<c:url value='/template/web/js/jquery.js'/>"></script>
	<script src="<c:url value='/template/web/js/plugins.js'/>"></script>
	<script src="<c:url value='/template/web/js/plugins2.js'/>"></script>
	<script src="<c:url value='/template/web/js/custom.js'/>"></script>
	
</body>
</html>