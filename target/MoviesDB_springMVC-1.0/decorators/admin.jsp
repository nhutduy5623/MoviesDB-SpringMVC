<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>	
	<meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Admin Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" type="image/png" href="assets/images/icon/favicon.ico">
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/font-awesome.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/themify-icons.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/metisMenu.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/owl.carousel.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/slicknav.min.css'/>">
    
    <!-- amchart css -->
    <link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />
    <!-- others css -->
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/typography.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/default-css.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/styles.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/responsive.css'/>">
    
    <!-- Start datatable css -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.18/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.jqueryui.min.css">
    
    <!-- Paging -->
    <script src="<c:url value='/template/paging/jquery.twbsPagination.js' />"></script>
    <link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
    <!-- modernizr css -->
    <script src="<c:url value='/template/admin/assets/js/vendor/modernizr-2.8.3.min.js'/>"></script>
    
    
</head>
<body>
	<!--Body-->
	<div class="page-container">
		<!-- Menu -->
		<%@include file="/common/admin/menu.jsp"%>
		<!-- Menu End -->
		<!-- main content area start -->
        <div class="main-content">
	        <!--Header-->
				<%@include file="/common/admin/header.jsp"%>
			<!--Header-->			        	
        	<dec:body/>
        </div>	
        <!--Footer-->
			<%@include file="/common/admin/footer.jsp"%>
		<!--Footer-->	
	</div>	
	<!--Body-->
	
	

	<!-- jquery latest version -->
    <script src="<c:url value='/template/admin/assets/js/vendor/jquery-2.2.4.min.js'/>"></script>
    <!-- bootstrap 4 js -->
    <script src="<c:url value='/template/admin/assets/js/popper.min.js'/>"></script>
    <script src="<c:url value='/template/admin/assets/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/template/admin/assets/js/owl.carousel.min.js'/>"></script>
    <script src="<c:url value='/template/admin/assets/js/metisMenu.min.js'/>"></script>
    <script src="<c:url value='/template/admin/assets/js/jquery.slimscroll.min.js'/>"></script>
    <script src="<c:url value='/template/admin/assets/js/jquery.slicknav.min.js'/>"></script>
	
	
	
    <!-- start chart js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js'/>"></script>
    <!-- start highcharts js -->
    <script src="https://code.highcharts.com/highcharts.js'/>"></script>
    <!-- start zingchart js -->
    <script src="https://cdn.zingchart.com/zingchart.min.js'/>"></script>
    <script>
    zingchart.MODULESDIR = "https://cdn.zingchart.com/modules/";
    ZC.LICENSE = ["569d52cefae586f634c54f86dc99e6a9", "ee6b7db5b51705a13dc2339db3edaf6d"];
    </script>
    <!-- all line chart activation -->
    <script src="<c:url value='/template/admin/assets/js/line-chart.js'/>"></script>
    <!-- all bar chart activation -->
    <script src="<c:url value='/template/admin/assets/js/bar-chart.js'/>"></script>
    <!-- all pie chart -->
    <script src="<c:url value='/template/admin/assets/js/pie-chart.js'/>"></script>
    <!-- others plugins -->
    <script src="<c:url value='/template/admin/assets/js/plugins.js'/>"></script>
    <script src="<c:url value='/template/admin/assets/js/scripts.js'/>"></script>
    
    <!-- Paging -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="<c:url value='/template/paging/jquery.twbsPagination.js' />"></script>
     
    <!-- Start datatable js -->
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
    <script src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.18/js/dataTables.bootstrap4.min.js"></script>
    <script src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
    <script src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap.min.js"></script>
</body>
</html>