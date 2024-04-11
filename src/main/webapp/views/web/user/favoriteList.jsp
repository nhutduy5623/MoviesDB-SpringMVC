<%@include file="/common/taglib.jsp" %>
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
.describe {
	max-height: 50%;
	overflow: hidden;
	text-overflow: ellipsis;
}
</style>
    
<div class="hero user-hero">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="hero-ct">
					<h1>${currentUser.fullName} Favorite List</h1>
					<ul class="breadcumb">
						<li class="active"><a href="#">Home</a></li>
						<li> <span class="ion-ios-arrow-right"></span>Favorite movies</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="page-single userfav_list">
	<div class="container">
		<div class="row ipad-width2">
			<div class="col-md-3 col-sm-12 col-xs-12">
				<div class="user-information">
					<div class="user-img">
						<a href="#"><img src="<c:url value='${currentUser.avatar}' />" alt=""><br></a>
						<a href="<c:url value='/profile'/>" class="redbtn">Change avatar</a>
					</div>
					<div class="user-fav">
						<p>Account Details</p>
						<ul>
							<li><a href="<c:url value='/profile'/>">Profile</a></li>
							<li class="active"><a href="<c:url value='/userfavorite'/>">Favorite movies</a></li>
							<li><a href="userrate.html">Rated movies</a></li>
						</ul>
					</div>
					<div class="user-fav">
						<p>Others</p>
						<ul>
							<li><a href="#">Change password</a></li>
							<li><a href="<c:url value='/logout'/>">Log out</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-md-9 col-sm-12 col-xs-12">
				<div class="topbar-filter user">
					<p>Found <span>${size} movies</span> in total</p>
					<label>Sort by:</label>
					<select name="genreCode" id="genreFilter">
						<option value="-">All</option>
						<c:forEach var="genre" items="${genreList}" >
							<option value="${genre.code}">${genre.name}</option>
						</c:forEach>
					</select>
					<a href="userfavoritelist.html" class="list"><i class="ion-ios-list-outline active"></i></a>
					<a href="userfavoritegrid.html" class="grid"><i class="ion-grid "></i></a>
				</div>
				<div class="flex-wrap-movielist user-fav-list" style="display: flex; flex-direction: column;">				
					<c:forEach var="favoriteItem" items="${model.listResults}">
						<div class="movie-item-style-2" style="flex: 10">
							<img src="<c:url value='${favoriteItem.workDTO.thumbnail}'/>" style="width: 30%; flex: 3" alt="">
							<div class="mv-item-infor" style="flex: 6">
								<h6><a href="<c:url value='/work/detail?code=${favoriteItem.workDTO.code}'/>">${favoriteItem.workDTO.name} <span>(${favoriteItem.workDTO.relatedDate})</span></a></h6>
								<p class="rate"><i class="ion-android-star" ></i><span>${favoriteItem.workDTO.score}</span> /10</p>
								<p class="describe" id="describe${favoriteItem.workDTO.code}">${favoriteItem.workDTO.overview}</p>    
								<p class="run-time"> Your star: <i class="ion-android-star" style="color: #f5b50a;"></i>${favoriteItem.reviewScore}     .     <span>Your status: 
								<c:if test="${favoriteItem.viewingStatus==1}">Watching</c:if>
								<c:if test="${favoriteItem.viewingStatus==2}">Completed</c:if>
								<c:if test="${favoriteItem.viewingStatus==3}">On-hold</c:if>
								<c:if test="${favoriteItem.viewingStatus==4}">Dropper</c:if>
								<c:if test="${favoriteItem.viewingStatus==5}">Prepare to watch</c:if>
								 </span>  
								<p>Note: <a href="#">${favoriteItem.note}</a></p>
							</div>
							<div style="flex: 1; height: 30rem; display: flex; justify-content: space-around; flex-direction: column;">
								<div class="fw-icons" style="align-content: center; align-items: center; display: flex; justify-content: center;">
									<a href="#" class="btnRepair" id="btnRepair_${favoriteItem.id}" style="margin: auto;"><i class="fa fa-wrench" style="color: white; font-size: 20px"></i></a>
	                            </div>
	                            <div class="fw-icons" id="${item.id}" style="align-content: center; align-items: center; display: flex; justify-content: center;">
				            		<a href="#" class="btnDelete"  id="btnDelete_${favoriteItem.id}"><i class="fa fa-trash" style="color: white; font-size: 20px"></i></a>
	                            </div>  
							</div>
								<input type="hidden" id="WorkCode_${favoriteItem.id}" value="${favoriteItem.workDTO.code}"/>
								<input type="hidden" id="WorkName_${favoriteItem.id}" value="${favoriteItem.workDTO.name}"/>
								<input type="hidden" id="Star_${favoriteItem.id}" value="${favoriteItem.reviewScore}"/>
								<input type="hidden" id="Status_${favoriteItem.id}" value="${favoriteItem.viewingStatus}"/>
								<input type="hidden" id="Note_${favoriteItem.id}" value="${favoriteItem.note}"/>
						</div>
					</c:forEach>					
				</div>		
				<div class="topbar-filter">
					<label>Movies per page:</label>
					<form id="formChangeFilter" action="<c:url value='/userfavorite'/>" method="get">
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
					</form>	
					<ul class="pagination" id="pagination1"></ul>
				</div>
			</div>
		</div>
	</div>
</div>
<c:url var="restAPI_Delete_URL" value="/api/userfavorite"/>
<c:url var="listURL" value="/userfavorite"/>
<%@include file="/views/web/favorite/addFavorite_FormForRepair.jsp"%>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="<c:url value='/template/paging/jq-paginator.js'/>"></script>
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



//Sua, Xoa
var favoriteFormLink = $(".btnRepair");
var faovoriteCT = $("#favoriteForm-content");
var overlay = $(".overlay");
$(favoriteFormLink).on('click', function (e) {
	e.preventDefault();
	faovoriteCT.parents(overlay).addClass("openform");
	$(document).on('click', function(e){
	var target = $(e.target);
	if ($(target).hasClass("overlay")){
			$(target).find(loginct).each( function(){
				$(this).removeClass("openform");
			});
			setTimeout( function(){
				$(target).removeClass("openform");
			}, 350);
		}	
	});	
	var btn_id = $(this).attr('id');
	var modelId = btn_id.split('_')[1];
	var score = $("#Star_"+modelId).val()
	var status = $("#Status_"+modelId).val()
	$("#workName").html($("#WorkName_"+modelId).val())
	$("#sl_Score").val($("#Star_"+modelId).val()+"").change()
	$("#sl_Status").val($("#Status_"+modelId).val()+"").change()
	$("#ip_note").val($("#Note_"+modelId).val())
	$("#workCode").val($("#WorkCode_"+modelId).val())
	$("#id").val(modelId)
});



$(".btnDelete").click(function(e){
		e.preventDefault();
		var btn_id = $(this).attr('id');
		var modelId = btn_id.split('_')[1];
		swal({
			  title: "Xác nhận xóa",
			  text: "Confirm deletion from favorites list?",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonClass: "btn-success",
			  cancelButtonClass: "btn-danger",
			  confirmButtonText: "Xác nhận",
			  cancelButtonText: "Hủy bỏ",
			}).then(function(isConfirm) {				
			  if (isConfirm['value']) {	
				  ids = [];
				  ids.push(modelId+"");	
				  deleteFavorite(ids)
			  } 
			});
	})
	function deleteFavorite(data) {
		    $.ajax({
		        url: '${restAPI_Delete_URL}',
		        type: 'DELETE',
		        contentType: 'application/json',
		        data: JSON.stringify(data),
		        success: function (result) {
		            window.location.href = "${listURL}?message=RemoveFavorite_Success";
		        },
		        error: function (error) {
		        	console.log(error)
		        }
		    });
		}

</script>