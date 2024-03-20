<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
<!--
.movie-item-style-1 {
	justify-content: space-between !important;
	align-items: center !important;
}
.movie-item-style-1 .hvr-inner {
	top: 15%;
	margin-top: 0%;
 	margin: auto; 	
 }
 .movie-item-style-1 a {
 	color: white;
 }
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
-->
</style>
<div class="hero common-hero">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="hero-ct">
					<h1>SubGenre List</h1>
					<ul class="breadcumb">
						<li class="active"><a href="home">Home</a></li>
						<li> <span class="ion-ios-arrow-right"></span> SubGenre listing</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="page-single">
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="topbar-filter fw">
					<p>Found <span>1,608 movies</span> in total</p>
					<label>Genre:</label>
					<select id="genreFilter">
						<option value="-">All</option>
						<c:forEach var = "genre" items="${genreList}">
							<option value="${genre.code}">${genre.name}</option>
						</c:forEach>
					</select>
					<a href="movielist.html" class="list"><i class="ion-ios-list-outline "></i></a>
					<a  href="moviegridfw.html" class="grid"><i class="ion-grid active"></i></a>
				</div>
				<div class="flex-wrap-movielist mv-grid-fw">
					<c:forEach var="subGenre" items="${model.listResults}" >
						<div class="movie-item-style-2 movie-item-style-1" style="align-items: center;">
							<div style="width: 100%">
								<img src="${subGenre.thumbnail}" alt="sad">
								<h6 ><a href="work?subGenreCode=${subGenre.code}">${subGenre.name}</a></h6>
								<p>${subGenre.shortDescription}</p>	
							</div>
							<div class="hvr-inner">
	            				<a style="margin: 0;" href="work?subGenreCode=${subGenre.code}"> Read more <i class="ion-android-arrow-dropright"></i> </a>
	            			</div>
	            				
						</div>	
					</c:forEach>								
				</div>		
				<div class="topbar-filter">
					<label>Movies per page:</label>
					<form id="formChangeFilter" action="subgenre" method="get">
						<select id="maxPageItem" name="limit">
							<option value="2">2</option>
	                        <option value="5" selected="selected">5</option>
	                        <option value="10">10</option>
	                        <option value="20">20</option>
						</select>
						<input type="hidden" value="${model.nextPage}" name="page" id="nextPage" /> 
                		<input type="hidden" value="${model.searchValue}" name="search" id="search" /> 
                		<input type="hidden" value="${genreCode}" name="genreCode" id="genreCode" />                 		
						<input type="hidden" value="${model.nextPage}" name="page" id="page" />                 		
					</form>										
					<ul class="pagination" id="pagination1"></ul>
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
	var genreCode = '${genreCode}'; 
	$("#genreFilter").val(genreCode).change();
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
		$('#genreCode').val($(this).val());
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
</script>