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

<div class="hero hero3"> 
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<!-- <h1> movie listing - list</h1>
				<ul class="breadcumb">
					<li class="active"><a href="#">Home</a></li>
					<li> <span class="ion-ios-arrow-right"></span> movie listing</li>
				</ul> -->
			</div>
		</div>
	</div>
</div>

<!-- celebrity single section-->

<div class="page-single movie-single cebleb-single">
	<div class="container">
		<div class="row ipad-width">
			<div class="col-md-4 col-sm-12 col-xs-12">
				<div class="mv-ceb">
					<img src="<c:url value='${model.avatar}'/>" alt="">
				</div>
			</div>
			<div class="col-md-8 col-sm-12 col-xs-12">
				<div class="movie-single-ct">
					<h1 class="bd-hd">${model.name }</h1>
					<p class="ceb-single">${model.roleCode }</p>
					<div class="social-link cebsingle-socail" style="height: 30px;"></div>
					<div class="movie-tabs">
						<div class="tabs">
							<ul class="tab-links tabs-mv">
								<li class="active"><a href="#overviewceb">Overview</a></li>
								<li><a href="#filmography">Movies</a></li>                        
							</ul>
						    <div class="tab-content">
						        <div id="overviewceb" class="tab active">
						            <div class="row">
						            	<div class="col-md-8 col-sm-12 col-xs-12">
						            		<p>${model.overview}</p>
						            	</div>
						            	<div class="col-md-4 col-xs-12 col-sm-12">
						            		<div class="sb-it">
						            			<h6>Fullname:  </h6>
						            			<p><a href="#">${model.name }</a></p>
						            		</div>
						            		<div class="sb-it">
						            			<h6>Follow Count: </h6>
						            			<p>${model.followerCount }</p>
						            		</div>
						            		<div class="sb-it">
						            			<h6>Website:  </h6>
						            			<p>${model.website }</p>
						            		</div>
						            		<div class="sb-it">
						            			<h6>Keywords:</h6>
						            			<p class="tags">
													<span class="time"><a href="<c:url value='/relatedparty?roleCode=${model.roleCode}'/>">${model.roleCode}</a></span>
						            			</p>
						            		</div>
						            	</div>
						            </div>
						        </div>
 
					       	 	<div id="filmography" class="tab">
						        	<div class="row">
						            	<div class="rv-hd">
						            		<div>
						            			<h3>Movies of</h3>
					       	 					<h2>${model.name }</h2>
						            		</div>
										
						            	</div>
						            	<div class="topbar-filter">
											<p>Found <span>${workList.size()} movies</span></p>
										</div>
										<!-- movie cast -->
										<div class="mvcast-item">											
											<c:forEach var="work" items="${workList}">
											<div class="cast-it">
												<div class="cast-left cebleb-film">
													<img style="height: 50px;" src="<c:url value='${work.thumbnail}'/>" alt="">
													<div>
														<a href="<c:url value='/work/detail?code=${work.code}'/>">${work.name} </a>
													</div>				
												</div>
												<p>${work.relatedDate} </p>
											</div>
											</c:forEach>
										</div>
						            </div>
					       	 	</div>
						    </div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>



<script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.29.2/dist/sweetalert2.min.js"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="<c:url value='/template/paging/jq-paginator.js'/>"></script>