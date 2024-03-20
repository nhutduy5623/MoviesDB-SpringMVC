<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<style>
<!--
.movie-item-style-1 {
	justify-content: center !important;
	align-items: center !important;
}
.movie-item-style-1 .hvr-inner {
	top: 15%;
	margin-top: 0%;
 	margin: auto; 	
 }
-->
</style>
<div class="hero common-hero">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="hero-ct">
					<h1>Genre List</h1>
					<ul class="breadcumb">
						<li class="active"><a href="home">Home</a></li>
						<li> <span class="ion-ios-arrow-right"></span> Genre listing</li>
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
				<div class="flex-wrap-movielist mv-grid-fw">
					<c:forEach var="genre" items="${model.listResults}" >
						<div class="movie-item-style-2 movie-item-style-1" style="justify-content: center; align-items: center;">
							<img src="${genre.thumbnail}" alt="sad">
							<div class="hvr-inner">
	            				<a style="margin: 0;" href="work?genreCode=${genre.code}"> Read more <i class="ion-android-arrow-dropright"></i> </a>
	            			</div>
							<div class="mv-item-infor">
								<h6><a href="#">${genre.name}</a></h6>
							</div>
						</div>	
					</c:forEach>								
				</div>		
			</div>
		</div>
	</div>
</div>