<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
 #pagination1{
 	margin: 0px;
 	padding: 0px;
 	background-color: rgba(0, 0, 0, 0);
 }
 #pagination1 *{ 
 	background-color: rgba(0, 0, 0, 0);
 }
 #pagination1 *{ 
 	border: 0px rgba(0, 0, 0, 0);
 }

</style>

<div class="hero common-hero">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="hero-ct">
					<h1> related party listing - grid</h1>
					<ul class="breadcumb">
						<li class="active"><a href="#">Home</a></li>
						<li> <span class="ion-ios-arrow-right"></span> related party listing</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="page-single">
	<div class="container">
		<div class="row ipad-width">
			<div class="col-md-8 col-sm-12 col-xs-12">
				<div class="topbar-filter">
					<p>Found <span>${model.listResults.size()} related parties</span> in total</p>
					<label>Role:</label>
					<select id="genreFilter">
						<option value="-">All</option>
						<c:forEach var = "role" items="${relatedPartyRoleList}">
							<option value="${role.code}">${role.name}</option>
						</c:forEach>
					</select>
					<a href="movielist.html" class="list"><i class="ion-ios-list-outline "></i></a>
					<a  href="moviegrid.html" class="grid"><i class="ion-grid active"></i></a>
				</div>
				<div class="flex-wrap-movielist">
					<c:forEach var="relatedparty" items="${model.listResults}">
						<div class="movie-item-style-2 movie-item-style-1">
							<img src="<c:url value='${relatedparty.avatar}'/>"  alt="">
							<div class="hvr-inner">
	            				<a  href="relatedparty/details?code=${relatedparty.code}"> Read more <i class="ion-android-arrow-dropright"></i> </a>
	            			</div>
							<div class="mv-item-infor">
								<h6><a href="#">${relatedparty.name}</a></h6>
								<p class=""><span>${relatedparty.followerCount}</span> Follows</p>
								
							</div>
						</div>
					</c:forEach>		
				</div>		
				<div class="topbar-filter">
					<label>Related party per page:</label>
					<form id="formChangeFilter" action="relatedparty" method="get">
						<select id="maxPageItem" name="limit">
							<option value="2">2</option>
	                        <option value="5" selected="selected">5</option>
	                        <option value="10">10</option>
	                        <option value="20">20</option>
						</select>
						<input type="hidden" value="${model.nextPage}" name="page" id="nextPage" /> 
                		<input type="hidden" value="${model.searchValue}" name="search" id="search" /> 
                		<input type="hidden" value="${roleCode}" name="roleCode" id="roleCode" />                 		
						<input type="hidden" value="${model.nextPage}" name="page" id="page" />                 		
					</form>	
					<ul class="pagination" id="pagination1"></ul>
					
				</div>
			</div>
			<div class="col-md-4 col-sm-12 col-xs-12">
				<div class="sidebar">
					<div class="searh-form">
						<h4 class="sb-title">Search for related party</h4>
						<form class="form-style-1" action="#">
							<div class="row">
								<div class="col-md-12 form-it">
									<label>Related Party Name</label>
									<input type="text" id = "searchForm" value="${model.searchValue}" placeholder="Enter keywords">
								</div>
								<div class="col-md-12 form-it">
									<label>Role</label>
									<div class="group-ip">
										<select name="roleCode" id = "roleCodeForm"
											name="skills" style="font-size: 12px; color: #abb7c4">
											<option value="-">Enter to filter related party</option>
											<c:forEach var="role" items="${relatedPartyRoleList}">
												<option value="${role.code}">${role.name}</option>
											</c:forEach>
										</select>
									</div>	
								</div>								
								<div class="col-md-12 ">
									<input id="btn_SubmitFormFilter" class="submit" type="submit" value="submit">
								</div>
							</div>
						</form>
					</div>					
				</div>
			</div>
		</div>
	</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.29.2/dist/sweetalert2.min.js"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="<c:url value='/template/paging/jq-paginator.js'/>"></script>
<script>

	var totalPages = ${model.totalPages};
	var currentPage = ${model.nextPage};
	var maxPageItem = ${model.limit};   
	$("#maxPageItem").val(maxPageItem).change();	
	var roleCode = '${roleCode}'; 
	$("#genreFilter").val(roleCode).change();
	$("#roleCodeForm").val(roleCode).change();
	$('#maxPageItem').on('change', function (e) {
		$("#nextPage").val(1);
		$("#formChangeFilter").submit();			
	});
	$('.page').on('change', function (e) {
		$("#nextPage").val(1);
		$("#formChangeFilter").submit();			
	});
	$('#genreFilter').on('change', function (e) {
		$("#nextPage").val(1);
		$('#roleCode').val($(this).val());
		$("#formChangeFilter").submit();			
	});
	$.jqPaginator('#pagination1', {
		totalPages : totalPages,
		visiblePages : 5,
		currentPage : currentPage,
		onPageChange : function(nextPage, type) {			
			if (type != "init") {
				$("#nextPage").val(nextPage);	
				$("#formChangeFilter").submit();
			}
		}
	});
	
	$("#btn_SubmitFormFilter").click(function (e) {
		e.preventDefault();
		var searchValue = $("#searchForm").val()
		var roleCodeForm = $("#roleCodeForm").val()
		console.log(searchValue)
		console.log(roleCodeForm)
		
		$("#search").val(searchValue)
		$("#nextPage").val(1);	
		$('#roleCode').val(roleCodeForm);
		$("#formChangeFilter").submit();
		
	})
	
</script>