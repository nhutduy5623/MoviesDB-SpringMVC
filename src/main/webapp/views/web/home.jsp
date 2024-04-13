<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="slider sliderv2">
	<div class="container">
		<div class="row">
	    	<div class="slider-single-item">	    		
	    		<c:forEach var="work" items="${Top5ByScore}">
		    		<div class="movie-item">
		    			<div class="row">
		    				<div class="col-md-8 col-sm-12 col-xs-12">
		    					<div class="title-in">
				    				<div class="cate">				    					
				    					<c:forEach var="Map_Subgenre" items="${Map_Subgenres}" >
			    							<c:if test="${Map_Subgenre.key == work.code}">
			    								<c:forEach var="subGenre" items="${Map_Subgenre.value}">
			    									<span class="blue"><a href="<c:url value='/work?subGenreCode=${subGenre.code}'/>">${subGenre.name}</a></span>			    								
			    								</c:forEach>
			    							</c:if>				    						
				    					</c:forEach>
				    				</div>
				    				<h1><a href="#">${work.name} <span>${work.relatedDate.year + 1900}</span></a></h1>
									<div class="social-btn">
										<a href="#" class="parent-btn"><i class="ion-play"></i> Watch Trailer</a>
										<a href="#" class="parent-btn"><i class="ion-heart"></i> Add to Favorite</a>
										<div class="hover-bnt">
											<a href="#" class="parent-btn"><i class="ion-android-share-alt"></i>share</a>
											<div class="hvr-item">
												<a href="#" class="hvr-grow"><i class="ion-social-facebook"></i></a>
												<a href="#" class="hvr-grow"><i class="ion-social-twitter"></i></a>
												<a href="#" class="hvr-grow"><i class="ion-social-googleplus"></i></a>
												<a href="#" class="hvr-grow"><i class="ion-social-youtube"></i></a>
											</div>
										</div>		
									</div>
				    				<div class="mv-details">
				    					<p><i class="ion-android-star"></i><span>${work.score }</span> /10</p>
				    					<ul class="mv-infor">
				    						<li>  Run Time: 2h21’ </li>
				    						<li>  Rated: PG-13  </li>
				    						<li>  Release: ${work.relatedDate}</li>
				    					</ul>
				    				</div>
				    				<div class="btn-transform transform-vertical">
										<div><a href="<c:url value='/work/detail?code=${work.code}'/>" class="item item-1 redbtn">more detail</a></div>
										<div><a href= "<c:url value='/work/detail?code=${work.code}'/>" class="item item-2 redbtn hvrbtn">more detail</a></div>
									</div>
				    				
				    			</div>
		    				</div>
		    				<div class="col-md-4 col-sm-12 col-xs-12">
			    				<div class="mv-img-2">
				    				<a href="<c:url value='/work/detail?code=${work.code}'/>"><img  src="<c:url value='${work.thumbnail}'/>" alt=""></a>
				    			</div>
			    			</div>
		    			</div>	
		    		</div>
	    		
	    		</c:forEach>
	    	</div>
	    </div>
	</div>
</div>
<div class="movie-items  full-width">
	<div class="row">
		<div class="col-md-12">
			<div class="title-hd">
				<h2>in theater</h2>
				<a href="<c:url value='/work'/>" class="viewall">View all <i class="ion-ios-arrow-right"></i></a>
			</div>
			<div class="tabs">
				<ul class="tab-links">
					<li class="active"><a href="#tab1-h2">#Popular</a></li>
					<li><a href="#tab2-h2"> #Coming soon</a></li>
					<li><a href="#tab3-h2">  #Top rated  </a></li>                  
				</ul>
			    <div class="tab-content">
			        <div id="tab1-h2" class="tab active">
			            <div class="row">
			            	<div class="slick-multiItem2">	
			            		<c:forEach var="work" items="${Top7ByVoteCount}">
			            			<div class="slide-it">
				            			<div class="movie-item">
					            			<div class="mv-img">
					            				<img src="<c:url value='${work.thumbnail }'/>" alt="">
					            			</div>
					            			<div class="hvr-inner">
					            				<a  href="<c:url value='/work/detail?code=${work.code}'/>"> Read more <i class="ion-android-arrow-dropright"></i> </a>
					            			</div>
					            			<div class="title-in">
					            				<h6><a href="#">${work.name}</a></h6>
					            				<p><i class="ion-android-star"></i><span>${work.score}</span> /10</p>
					            			</div>
					            		</div>
				            		</div>
			            		</c:forEach>
			            	</div>
			            </div>
			        </div>
			        <div id="tab2-h2" class="tab">
			           <div class="row">
			            	<div class="slick-multiItem2">			            		
			            		<c:forEach var="work" items="${Top7ByRelatedDate}">
			            			<div class="slide-it">
				            			<div class="movie-item">
					            			<div class="mv-img">
					            				<img src="<c:url value='${work.thumbnail }'/>" alt="">
					            			</div>
					            			<div class="hvr-inner">
					            				<a  href="<c:url value='/work/detail?code=${work.code}'/>"> Read more <i class="ion-android-arrow-dropright"></i> </a>
					            			</div>
					            			<div class="title-in">
					            				<h6><a href="#">${work.name}</a></h6>
					            				<p><i class="ion-android-star"></i><span>${work.score}</span> /10</p>
					            			</div>
					            		</div>
				            		</div>
			            		</c:forEach>
			            	</div>
			            </div>
			        </div>
			        <div id="tab3-h2" class="tab">
			        	<div class="row">
			            	<div class="slick-multiItem2">			            		
			            		<c:forEach var="work" items="${Top7ByVoteCount}">
			            			<div class="slide-it">
				            			<div class="movie-item">
					            			<div class="mv-img">
					            				<img src="<c:url value='${work.thumbnail }'/>" alt="">
					            			</div>
					            			<div class="hvr-inner">
					            				<a href="<c:url value='/work/detail?code=${work.code}'/>"> Read more <i class="ion-android-arrow-dropright"></i> </a>
					            			</div>
					            			<div class="title-in">
					            				<h6><a href="#">${work.name}</a></h6>
					            				<p><i class="ion-android-star"></i><span>${work.score}</span> /10</p>
					            			</div>
					            		</div>
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