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
					<h1> movie listing - grid</h1>
					<ul class="breadcumb">
						<li class="active"><a href="#">Home</a></li>
						<li> <span class="ion-ios-arrow-right"></span> movie listing</li>
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
					<p>Found <span>1,608 movies</span> in total</p>
					<label>Genre:</label>
					<select id="genreFilter">
						<option value="-">All</option>
						<c:forEach var = "genre" items="${genreList}">
							<option value="${genre.code}">${genre.name}</option>
						</c:forEach>
					</select>
					<a href="movielist.html" class="list"><i class="ion-ios-list-outline "></i></a>
					<a  href="moviegrid.html" class="grid"><i class="ion-grid active"></i></a>
				</div>
				<div class="flex-wrap-movielist">
					<c:forEach var="work" items="${model.listResults}">
						<div class="movie-item-style-2 movie-item-style-1">
							<img src="<c:url value='${work.thumbnail}'/>" alt="">
							<div class="hvr-inner">
	            				<a  href="work/detail?code=${work.code}"> Read more <i class="ion-android-arrow-dropright"></i> </a>
	            			</div>
							<div class="mv-item-infor">
								<h6><a href="#">${work.name}</a></h6>
								<p class="rate"><i class="ion-android-star"></i><span>${work.score}</span> /10</p>
								<p class=""><span>${work.voteCount}</span> Votes</p>
								
							</div>
						</div>
					</c:forEach>		
				</div>		
				<div class="topbar-filter">
					<label>Movies per page:</label>
					<form id="formChangeFilter" action="work" method="get">
						<select id="maxPageItem" name="limit">
							<option value="8">8</option>
	                        <option value="16" selected="selected">16</option>
	                        <option value="32">32</option>
	                        <option value="64">64</option>
						</select>
						<input type="hidden" value="${model.nextPage}" name="page" id="nextPage" /> 
                		<input type="hidden" value="${model.searchValue}" name="search" id="search" /> 
                		<input type="hidden" value="${genreCode}" name="genreCode" id="genreCode" />                 		
						<input type="hidden" value="${model.nextPage}" name="page" id="page" />                 		
						<input type="hidden" value="${subGenreCodeList}" name="subGenreCodeList" id="subGenreCodeList" />
					</form>	
					<ul class="pagination" id="pagination1"></ul>
					
				</div>
			</div>
			<div class="col-md-4 col-sm-12 col-xs-12">
				<div class="sidebar">
					<div class="searh-form">
						<h4 class="sb-title">Search for movie</h4>
						<form class="form-style-1" action="#">
							<div class="row">
								<div class="col-md-12 form-it">
									<label>Movie name</label>
									<input type="text" id = "searchForm" value="${model.searchValue}" placeholder="Enter keywords">
								</div>
								<div class="col-md-12 form-it">
									<label>Genres</label>
									<div class="group-ip">
										<select name="genreCode" id = "genreCodeForm"
											name="skills" style="font-size: 12px; color: #abb7c4">
											<option value="-">Enter to filter genres</option>
											<c:forEach var="genre" items="${genreList}">
												<option value="${genre.code}">${genre.name}</option>
											</c:forEach>
										</select>
									</div>	
								</div>
								<div class="col-md-12 form-it">
									<label>Sub Genres</label>
									<div class="group-ip" style="display: flex; width: 100%; flex-wrap: wrap;">
										<c:forEach var = "subGenre" items="${subGenreList}">
											<div style="margin-right: 10px; display: flex; flex-direction: row; align-items: center">
												<input class="subGenreCheckBox" name="subGenreCheckBox" id="subGenreItem${subGenre.code}" type="checkbox" value="${subGenre.code}" >
												<label style="margin: auto;" for="subGenreItem${subGenre.code}"> ${subGenre.name}</label>
											</div>
										</c:forEach>
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
<c:forEach var="subgenre" items="${subGenreCodeList}">
	<script>
		var checkbox = document.querySelector("input[value='${subgenre}']");
	    if (checkbox) {
	        checkbox.checked = true;
	    }
	</script>
</c:forEach>
<script>

	var totalPages = ${model.totalPages};
	var currentPage = ${model.nextPage};
	var maxPageItem = ${model.limit};   
	$("#maxPageItem").val(maxPageItem).change();	
	var genreCode = '${genreCode}'; 
	$("#genreFilter").val(genreCode).change();
	$("#genreCodeForm").val(genreCode).change();
	$('#maxPageItem').on('change', function (e) {
		$("#nextPage").val(1);
		var subGenreCodeList = $('.subGenreCheckBox:checked').map(function() {
		    return this.value;
		}).get();
		$("#subGenreCodeList").val(subGenreCodeList)
		$("#formChangeFilter").submit();			
	});
	$('.page').on('change', function (e) {
		$("#nextPage").val(1);
		var subGenreCodeList = $('.subGenreCheckBox:checked').map(function() {
		    return this.value;
		}).get();
		$("#subGenreCodeList").val(subGenreCodeList)
		$("#formChangeFilter").submit();			
	});
	$('#genreFilter').on('change', function (e) {
		$("#nextPage").val(1);
		$('#genreCode').val($(this).val());
		$("#subGenreCodeList").val('')
		$("#formChangeFilter").submit();			
	});
	$.jqPaginator('#pagination1', {
		totalPages : totalPages,
		visiblePages : 5,
		currentPage : currentPage,
		onPageChange : function(nextPage, type) {			
			if (type != "init") {
				var subGenreCodeList = $('.subGenreCheckBox:checked').map(function() {
				    return this.value;
				}).get();
				$("#subGenreCodeList").val(subGenreCodeList)
				$("#nextPage").val(nextPage);	
				$("#formChangeFilter").submit();
			}
		}
	});
	
	$("#btn_SubmitFormFilter").click(function (e) {
		e.preventDefault();
		var subGenreCodeList = $('.subGenreCheckBox:checked').map(function() {
		    return this.value;
		}).get();
		var searchValue = $("#searchForm").val()
		var genreCodeForm = $("#genreCodeForm").val()
		console.log(subGenreCodeList)
		console.log(searchValue)
		console.log(genreCodeForm)
		
		$("#subGenreCodeList").val(subGenreCodeList)
		$("#search").val(searchValue)
		$("#nextPage").val(1);	
		$('#genreCode').val(genreCodeForm);
		$("#formChangeFilter").submit();
		
	})
	
	//Xử lý hiển thị Subgenre theo đúng Genre được chọn
	<c:url var="API_SubGenre_URL" value="/api/subgenre/getbygenrecode" />
	function checkListSubGenre_Genre(genreCode) {
		console.log("data: "+genreCode)
        data = genreCode;
		if(genreCode=='-') {
            $('input[type=checkbox][name=subGenreCheckBox]').parent().show();
		} else {
			$.ajax({
	            url: '${API_SubGenre_URL}',
	            type: 'POST',
	            contentType: 'application/json',
	            data: data,
	            dataType: 'json',
	            success: function (result) {
	                $('input[type=checkbox][name=subGenreCheckBox]').parent().hide();
	                $('input[type=checkbox][name=subGenreCheckBox]').each(function () {
	                    subgenre = $(this).val();
	                    element = $(this);         
	                    result.forEach(function (obj) {
	                    	element.prop('checked', false);
	                        if (subgenre + "" == obj.code + "") {
	                            element.parent().show();
	                        } 
	                                                        
	                    })
	                });
	            },
	            error: function (error) {
	                console.log(error)
	            }
	        })			
		}
        
	}
    $('#genreCodeForm').change(function () {
        console.log("data: "+ this.value)
        checkListSubGenre_Genre(this.value)
    });
</script>