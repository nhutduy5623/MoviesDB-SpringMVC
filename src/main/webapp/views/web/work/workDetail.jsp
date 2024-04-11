<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="hero mv-single-hero">
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
<style>
	.mv-single-hero {
		background: url("<c:url value='${model.background}'/>") no-repeat;
		height: 598px;
	}
	.your-review-topbar {
	margin-bottom: 0;
	}
	
	.your-review-control {
		padding-right: 10px;
	}
	
	.your-review-control button {
		margin-right: 10px;
		margin-top: 3px;
		margin-bottom: 3px;
	}
	
	.btn-del-danger {
		font-family: 'Dosis', sans-serif;
		font-size: 14px;
		color: #ffffff;
		font-weight: bold;
		border-radius: 3px;
		text-transform: uppercase;
		border: none;
		background-color: #dd003f;
	}
	
	.btn-edit-your-review {
		font-family: 'Dosis', sans-serif;
		font-size: 14px;
		color: #ffffff;
		border-radius: 3px;
		font-weight: bold;
		text-transform: uppercase;
		border: none;
		background-color: #0babc2e8;
	}
</style>
<div class="page-single movie-single movie_single">
	<div class="container">
		<div class="row ipad-width2">
			<div class="col-md-4 col-sm-12 col-xs-12">
				<div class="movie-img sticky-sb">
					<img src="<c:url value='${model.thumbnail}'/>" alt="">
					<div class="movie-btn">
						<div class="btn-transform transform-vertical red">
							<div><a href="#" class="item item-1 redbtn"> <i class="ion-play"></i> Watch Trailer</a>
							</div>
							<div><a href="${model.video}"
									class="item item-2 redbtn fancybox-media hvr-grow"><i class="ion-play"></i></a>
							</div>
						</div>
						<div class="btn-transform transform-vertical">
							<div><a href="#" class="item item-1 yellowbtn"> <i class="ion-card"></i> Buy ticket</a>
							</div>
							<div><a href="#" class="item item-2 yellowbtn"><i class="ion-card"></i></a></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-8 col-sm-12 col-xs-12">
				<div class="movie-single-ct main-content">
					<h1 class="bd-hd">${model.name}<span> ${model.relatedDate.year + 1900}</span></h1>
					<div class="social-btn">						
						<c:if test="${userFavorited !=1 }"><a href="#" class="parent-btn favoriteFormLink"><i class="ion-heart"></i> Add to Favorites</a></c:if>
						<c:if test="${userFavorited == 1 }"><a href="#" class="parent-btn removeFormFavorite" style="color: #f5b50a;"><i style="border-color: #f5b50a" class="ion-heart"></i> Remove from Favorites</a></c:if>
						
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
					<div class="movie-rate">
						<div class="rate">
							<i class="ion-android-star"></i>
							<p><span>${model.score}</span> /10<br>
								<span class="rv">${model.voteCount } Reviews</span>
							</p>
						</div>
						<div class="rate-star">
							<p>Rate This Movie: </p>
							<c:forEach var="i" begin="1" end="${model.score}">
							    <i class="ion-ios-star"></i>
							</c:forEach>
							<c:forEach var="i" begin="1" end="${10-model.score}">
							   <i class="ion-ios-star-outline"></i>
							</c:forEach>							
						</div>
					</div>
					<div class="movie-tabs">
						<div class="tabs">
							<ul class="tab-links tabs-mv">
								<li class="active"><a href="#overview">Overview</a></li>
								<li><a href="#reviews"> Reviews</a></li>
								<li><a href="#cast"> Cast & Crew </a></li>
								<li><a href="#media"> Media</a></li>
								<li><a href="#moviesrelated"> Related Movies</a></li>
							</ul>
							<div class="tab-content">
								<div id="overview" class="tab active">
									<div class="row">
										<div class="col-md-8 col-sm-12 col-xs-12">
											<p>${model.overview }</p>
											<div class="title-hd-sm">
												<h4>cast</h4>
												<a href="#" class="time">Full Cast & Crew <i
														class="ion-ios-arrow-right"></i></a>
											</div>
											<!-- movie cast -->
											<div class="mvcast-item">
												<c:forEach var="rpDetailMap" items="${relatedPartyDetailList.entrySet()}" >
													<div class="cast-it">
														<div class="cast-left">
															<img src="<c:url value='${rpDetailMap.key.avatar}' />" width="20%"  alt="">
															<a href="#">${rpDetailMap.key.name}</a>
														</div>
														<p>... ${rpDetailMap.value}</p>
													</div>	
												</c:forEach>									
																							
											</div>
											<div class="title-hd-sm">
												<h4>User reviews</h4>
												<a href="#" class="time">See All 56 Reviews <i
														class="ion-ios-arrow-right"></i></a>
											</div>
											<!-- movie user review -->
											<div class="mv-user-review-item">
												<h3>Best Marvel movie in my opinion</h3>
												<div class="no-star">
													<i class="ion-android-star"></i>
													<i class="ion-android-star"></i>
													<i class="ion-android-star"></i>
													<i class="ion-android-star"></i>
													<i class="ion-android-star"></i>
													<i class="ion-android-star"></i>
													<i class="ion-android-star"></i>
													<i class="ion-android-star"></i>
													<i class="ion-android-star"></i>
													<i class="ion-android-star last"></i>
												</div>
												<p class="time">
													17 December 2016 by <a href="#"> hawaiipierson</a>
												</p>
												<p>This is by far one of my favorite movies from the MCU. The
													introduction of new Characters both good and bad also makes the
													movie more exciting. giving the characters more of a back story
													can also help audiences relate more to different characters
													better, and it connects a bond between the audience and actors
													or characters. Having seen the movie three times does not bother
													me here as it is as thrilling and exciting every time I am
													watching it. In other words, the movie is by far better than
													previous movies (and I do love everything Marvel), the plotting
													is splendid (they really do out do themselves in each film,
													there are no problems watching it more than once.</p>
											</div>
										</div>
										<div class="col-md-4 col-xs-12 col-sm-12">
											<div class="sb-it">
												<h6>Director: </h6>
												<p><a href="#">Joss Whedon</a></p>
											</div>
											<div class="sb-it">
												<h6>Writer: </h6>
												<p><a href="#">Joss Whedon,</a> <a href="#">Stan Lee</a></p>
											</div>
											<div class="sb-it">
												<h6>Stars: </h6>
												<p><a href="#">Robert Downey Jr,</a> <a href="#">Chris Evans,</a> <a
														href="#">Mark Ruffalo,</a><a href="#"> Scarlett
														Johansson</a></p>
											</div>
											
											<div class="sb-it">
												<h6>Genre:</h6>
												<p>
													<a href="<c:url value='/work?genreCode=${genre.code}'/>">${genre.name}, </a>
												</p>
											</div>
											
											
											<div class="sb-it">
												<h6>SubGenres:</h6>
												<p>
													<c:forEach var="subGenre" items="${subGenreList}">
														<a href="<c:url value='/work?subGenreCode=${subGenre.code}'/>">${subGenre.name}, </a>
													</c:forEach>
												</p>
											</div>
											
											<div class="sb-it">
												<h6>Release Date:</h6>
												<p>${model.relatedDate}</p>
											</div>
											<div class="sb-it">
												<h6>Run Time:</h6>
												<p>141 min</p>
											</div>
											<div class="sb-it">
												<h6>MMPA Rating:</h6>
												<p>PG-13</p>
											</div>
											<div class="sb-it">
												<h6>Plot Keywords:</h6>
												<p class="tags">
													<span class="time"><a href="#">superhero</a></span>
													<span class="time"><a href="#">marvel universe</a></span>
													<span class="time"><a href="#">comic</a></span>
													<span class="time"><a href="#">blockbuster</a></span>
													<span class="time"><a href="#">final battle</a></span>
												</p>
											</div>
											<div class="ads">
												<img src="images/uploads/ads1.png" alt="">
											</div>
										</div>
									</div>
								</div>
								<div id="reviews" class="tab review">
									<div class="row">
										<div class="rv-hd">
											<div class="div">
												<h3>Review of</h3>
												<h2>${model.name}</h2>
											</div>
											<security:authorize access="isAuthenticated()">
												<a href="#" class="redbtn btn-write-review"
													id="btn-write-review">Write Review</a>
											</security:authorize>
										</div>
										<div id="container-your-review">
											<c:if test="${voteOfUser != null}">
												<div id="your-review">
													<div class="topbar-filter your-review-topbar">
														<p>Your review</p>
														<div class="your-review-control">
															<button type="button" id="btn-edit-your-review"
																class="btn-edit-your-review">
																<i class="fa fa-pencil-square-o"></i>
															</button>
															<button type="button" id="btn-del-your-review"
																class="btn-del-danger">
																<i class="fa fa-trash-o"></i>
															</button>
														</div>
													</div>
													<div class="mv-user-review-item last">
														<div class="user-infor">
															<img src="<c:url value='${voteOfUser.user.avatar}'/>"
																alt="">
															<div>
																<h3>${voteOfUser.user.fullName}</h3>
																<div class="no-star">
																	<c:forEach begin="1" end="${voteOfUser.score}">
																		<i class="ion-android-star"></i>
																	</c:forEach>
																	<c:forEach begin="${voteOfUser.score}" end="9">
																		<i class="ion-android-star last"></i>
																	</c:forEach>
																</div>
																<p class="time">
																	<fmt:setLocale value="en_US" scope="session" />
																	<fmt:formatDate value="${voteOfUser.modifiedDate}"
																		pattern="dd MMMM yyyy 'at' HH:mm" />
																</p>
															</div>
														</div>
														<p>${voteOfUser.content}</p>
													</div>
												</div>
											</c:if>
										</div>

										<div class="topbar-filter">
											<p>
												Found <span id="review-count">${model.voteCount} reviews</span> in total
											</p>
										</div>
										<div id="other-review">
											<c:if test="${vote.listResults.size()>1}">
												<c:forEach var="row" begin="0"
													end="${vote.listResults.size()-2}">
													<c:set var="item" value="${vote.listResults.get(row)}"></c:set>
													<div class="mv-user-review-item">
														<div class="user-infor">
															<img src="<c:url value='${item.user.avatar}'/>" alt="">
															<div>
																<h3>${item.user.fullName}</h3>
																<div class="no-star">
																	<c:forEach begin="1" end="${item.score}">
																		<i class="ion-android-star"></i>
																	</c:forEach>
																	<c:forEach begin="${item.score}" end="9">
																		<i class="ion-android-star last"></i>
																	</c:forEach>
																</div>
																<p class="time">
																	<fmt:formatDate value="${item.modifiedDate}"
																		pattern="dd MMMM yyyy 'at' HH:mm" />
																</p>
															</div>
														</div>
														<p>${item.content}</p>
													</div>
												</c:forEach>
											</c:if>
											<c:if test="${vote.listResults.size()>0}">
												<c:set var="itemEnd"
													value="${vote.listResults.get(vote.listResults.size()-1)}"></c:set>
												<div class="mv-user-review-item last">
													<div class="user-infor">
														<img src="<c:url value='${itemEnd.user.avatar}'/>" alt="">
														<div>
															<h3>${itemEnd.user.fullName}</h3>
															<div class="no-star">
																<c:forEach begin="1" end="${itemEnd.score}">
																	<i class="ion-android-star"></i>
																</c:forEach>
																<c:forEach begin="${itemEnd.score}" end="9">
																	<i class="ion-android-star last"></i>
																</c:forEach>
															</div>
															<p class="time">
																<fmt:formatDate value="${itemEnd.modifiedDate}"
																	pattern="dd MMMM yyyy 'at' HH:mm" />
															</p>
														</div>
													</div>
													<p>${itemEnd.content}</p>
												</div>
											</c:if>
										</div>
										<div class="topbar-filter">
											<label>Reviews per page:</label> <select
												id="review-limit-per-page">
												<option value="5">5 Reviews</option>
												<option value="10">10 Reviews</option>
											</select>
											<div class="pagination2" id="page-review-container"
												style="margin-right: 20px;">
												<span>Page:</span> <a href="1" style="margin-left: 0;">First</a>
												<a class="active" href="1" style="margin-left: 0;">1</a>
												<c:forEach var="i" begin="2" end="${vote.totalPages}">
													<a href="${i}" style="margin-left: 0;">${i}</a>
												</c:forEach>
												<a href="${vote.totalPages}" style="margin-left: 0;">Last</a>
											</div>
										</div>
									</div>
								</div>
								<div id="cast" class="tab">
									<div class="row">
										<h3>Cast & Crew of</h3>
										<h2>${model.name}</h2>
										<!-- //== -->
										<c:forEach var="RelatedPartyRole" items="${relatedPartyRoleList}">
											<div class="title-hd-sm">
											<h4>${RelatedPartyRole.name}</h4>
											</div>
											<c:forEach var="rpDetailMap" items="${relatedPartyDetailList.entrySet()}">
												<c:if test="${rpDetailMap.key.roleCode == RelatedPartyRole.code}">
													<div class="mvcast-item">
													<div class="cast-it">
														<div class="cast-left">
															<img src="<c:url value='${rpDetailMap.key.avatar}'/>" width="20%" alt="">
															<a href="<c:url value='/relatedparty/details?code=${rpDetailMap.key.code}'/>">${rpDetailMap.key.name}</a>
														</div>
														<p>... ${rpDetailMap.value}</p>
													</div>
											</div>
												</c:if>
											</c:forEach>
											
										</c:forEach>
										
									</div>
								</div>
								<div id="media" class="tab">
									<div class="row">
										<div class="rv-hd">
											<div>
												<h3>Videos & Photos of</h3>
												<h2>Skyfall: Quantum of Spectre</h2>
											</div>
										</div>
										<div class="title-hd-sm">
											<h4>Videos <span>(8)</span></h4>
										</div>
										<div class="mvsingle-item media-item">
											<div class="vd-item">
												<div class="vd-it">
													<img class="vd-img" src="images/uploads/vd-item1.jpg" alt="">
													<a class="fancybox-media hvr-grow"
														href="https://www.youtube.com/embed/o-0hcF97wy0"><img
															src="images/uploads/play-vd.png" alt=""></a>
												</div>
												<div class="vd-infor">
													<h6> <a href="#">Trailer: Watch New Scenes</a></h6>
													<p class="time"> 1: 31</p>
												</div>
											</div>
											<div class="vd-item">
												<div class="vd-it">
													<img class="vd-img" src="images/uploads/vd-item2.jpg" alt="">
													<a class="fancybox-media hvr-grow"
														href="https://www.youtube.com/embed/o-0hcF97wy0"><img
															src="images/uploads/play-vd.png" alt=""></a>
												</div>
												<div class="vd-infor">
													<h6> <a href="#">Featurette: “Avengers Re-Assembled</a></h6>
													<p class="time"> 1: 03</p>
												</div>
											</div>
											<div class="vd-item">
												<div class="vd-it">
													<img class="vd-img" src="images/uploads/vd-item3.jpg" alt="">
													<a class="fancybox-media hvr-grow"
														href="https://www.youtube.com/embed/o-0hcF97wy0"><img
															src="images/uploads/play-vd.png" alt=""></a>
												</div>
												<div class="vd-infor">
													<h6> <a href="#">Interview: Robert Downey Jr</a></h6>
													<p class="time"> 3:27</p>
												</div>
											</div>
											<div class="vd-item">
												<div class="vd-it">
													<img class="vd-img" src="images/uploads/vd-item4.jpg" alt="">
													<a class="fancybox-media hvr-grow"
														href="https://www.youtube.com/embed/o-0hcF97wy0"><img
															src="images/uploads/play-vd.png" alt=""></a>
												</div>
												<div class="vd-infor">
													<h6> <a href="#">Interview: Scarlett Johansson</a></h6>
													<p class="time"> 3:27</p>
												</div>
											</div>
											<div class="vd-item">
												<div class="vd-it">
													<img class="vd-img" src="images/uploads/vd-item1.jpg" alt="">
													<a class="fancybox-media hvr-grow"
														href="https://www.youtube.com/embed/o-0hcF97wy0"><img
															src="images/uploads/play-vd.png" alt=""></a>
												</div>
												<div class="vd-infor">
													<h6> <a href="#">Featurette: Meet Quicksilver & The Scarlet
															Witch</a></h6>
													<p class="time"> 1: 31</p>
												</div>
											</div>
											<div class="vd-item">
												<div class="vd-it">
													<img class="vd-img" src="images/uploads/vd-item2.jpg" alt="">
													<a class="fancybox-media hvr-grow"
														href="https://www.youtube.com/embed/o-0hcF97wy0"><img
															src="images/uploads/play-vd.png" alt=""></a>
												</div>
												<div class="vd-infor">
													<h6> <a href="#">Interview: Director Joss Whedon</a></h6>
													<p class="time"> 1: 03</p>
												</div>
											</div>
											<div class="vd-item">
												<div class="vd-it">
													<img class="vd-img" src="images/uploads/vd-item3.jpg" alt="">
													<a class="fancybox-media hvr-grow"
														href="https://www.youtube.com/embed/o-0hcF97wy0"><img
															src="images/uploads/play-vd.png" alt=""></a>
												</div>
												<div class="vd-infor">
													<h6> <a href="#">Interview: Mark Ruffalo</a></h6>
													<p class="time"> 3:27</p>
												</div>
											</div>
											<div class="vd-item">
												<div class="vd-it">
													<img class="vd-img" src="images/uploads/vd-item4.jpg" alt="">
													<a class="fancybox-media hvr-grow"
														href="https://www.youtube.com/embed/o-0hcF97wy0"><img
															src="images/uploads/play-vd.png" alt=""></a>
												</div>
												<div class="vd-infor">
													<h6> <a href="#">Official Trailer #2</a></h6>
													<p class="time"> 3:27</p>
												</div>
											</div>
										</div>
										<div class="title-hd-sm">
											<h4>Photos <span> (21)</span></h4>
										</div>
										<div class="mvsingle-item">
											<a class="img-lightbox" data-fancybox-group="gallery"
												href="images/uploads/image11.jpg"><img
													src="images/uploads/image1.jpg" alt=""></a>
											<a class="img-lightbox" data-fancybox-group="gallery"
												href="images/uploads/image21.jpg"><img
													src="images/uploads/image2.jpg" alt=""></a>
											<a class="img-lightbox" data-fancybox-group="gallery"
												href="images/uploads/image31.jpg"><img
													src="images/uploads/image3.jpg" alt=""></a>
											<a class="img-lightbox" data-fancybox-group="gallery"
												href="images/uploads/image41.jpg"><img
													src="images/uploads/image4.jpg" alt=""></a>
											<a class="img-lightbox" data-fancybox-group="gallery"
												href="images/uploads/image51.jpg"><img
													src="images/uploads/image5.jpg" alt=""></a>
											<a class="img-lightbox" data-fancybox-group="gallery"
												href="images/uploads/image61.jpg"><img
													src="images/uploads/image6.jpg" alt=""></a>
											<a class="img-lightbox" data-fancybox-group="gallery"
												href="images/uploads/image71.jpg"><img
													src="images/uploads/image7.jpg" alt=""></a>
											<a class="img-lightbox" data-fancybox-group="gallery"
												href="images/uploads/image81.jpg"><img
													src="images/uploads/image8.jpg" alt=""></a>
											<a class="img-lightbox" data-fancybox-group="gallery"
												href="images/uploads/image91.jpg"><img
													src="images/uploads/image9.jpg" alt=""></a>
											<a class="img-lightbox" data-fancybox-group="gallery"
												href="images/uploads/image101.jpg"><img
													src="images/uploads/image10.jpg" alt=""></a>
											<a class="img-lightbox" data-fancybox-group="gallery"
												href="images/uploads/image111.jpg"><img
													src="images/uploads/image1-1.jpg" alt=""></a>
											<a class="img-lightbox" data-fancybox-group="gallery"
												href="images/uploads/image121.jpg"><img
													src="images/uploads/image12.jpg" alt=""></a>
											<a class="img-lightbox" data-fancybox-group="gallery"
												href="images/uploads/image131.jpg"><img
													src="images/uploads/image13.jpg" alt=""></a>
											<a class="img-lightbox" data-fancybox-group="gallery"
												href="images/uploads/image141.jpg"><img
													src="images/uploads/image14.jpg" alt=""></a>
											<a class="img-lightbox" data-fancybox-group="gallery"
												href="images/uploads/image151.jpg"><img
													src="images/uploads/image15.jpg" alt=""></a>
											<a class="img-lightbox" data-fancybox-group="gallery"
												href="images/uploads/image161.jpg"><img
													src="images/uploads/image16.jpg" alt=""></a>
											<a class="img-lightbox" data-fancybox-group="gallery"
												href="images/uploads/image171.jpg"><img
													src="images/uploads/image17.jpg" alt=""></a>
											<a class="img-lightbox" data-fancybox-group="gallery"
												href="images/uploads/image181.jpg"><img
													src="images/uploads/image18.jpg" alt=""></a>
											<a class="img-lightbox" data-fancybox-group="gallery"
												href="images/uploads/image191.jpg"><img
													src="images/uploads/image19.jpg" alt=""></a>
											<a class="img-lightbox" data-fancybox-group="gallery"
												href="images/uploads/image201.jpg"><img
													src="images/uploads/image20.jpg" alt=""></a>
											<a class="img-lightbox" data-fancybox-group="gallery"
												href="images/uploads/image211.jpg"><img
													src="images/uploads/image2-1.jpg" alt=""></a>
										</div>
									</div>
								</div>
								<div id="moviesrelated" class="tab">
									<div class="row">
										<h3>Related Movies To</h3>
										<h2>Skyfall: Quantum of Spectre</h2>
										<div class="topbar-filter">
											<p>Found <span>12 movies</span> in total</p>
											<label>Sort by:</label>
											<select>
												<option value="popularity">Popularity Descending</option>
												<option value="popularity">Popularity Ascending</option>
												<option value="rating">Rating Descending</option>
												<option value="rating">Rating Ascending</option>
												<option value="date">Release date Descending</option>
												<option value="date">Release date Ascending</option>
											</select>
										</div>
										<div class="movie-item-style-2">
											<img src="images/uploads/mv1.jpg" alt="">
											<div class="mv-item-infor">
												<h6><a href="#">oblivion <span>(2012)</span></a></h6>
												<p class="rate"><i class="ion-android-star"></i><span>${model.score}</span> /10
												</p>
												<p class="describe">Earth's mightiest heroes must come together and
													learn to fight as a team if they are to stop the mischievous
													Loki and his alien army from enslaving humanity...</p>
												<p class="run-time"> Run Time: 2h21’ . <span>MMPA: PG-13 </span> .
													<span>Release: 1 May 2015</span></p>
												<p>Director: <a href="#">Joss Whedon</a></p>
												<p>Stars: <a href="#">Robert Downey Jr.,</a> <a href="#">Chris
														Evans,</a> <a href="#"> Chris Hemsworth</a></p>
											</div>
										</div>
										<div class="movie-item-style-2">
											<img src="images/uploads/mv2.jpg" alt="">
											<div class="mv-item-infor">
												<h6><a href="#">into the wild <span>(2014)</span></a></h6>
												<p class="rate"><i class="ion-android-star"></i><span>7.8</span> /10
												</p>
												<p class="describe">As Steve Rogers struggles to embrace his role in
													the modern world, he teams up with a fellow Avenger and
													S.H.I.E.L.D agent, Black Widow, to battle a new threat...</p>
												<p class="run-time"> Run Time: 2h21’ . <span>MMPA: PG-13 </span> .
													<span>Release: 1 May 2015</span></p>
												<p>Director: <a href="#">Anthony Russo,</a><a href="#">Joe Russo</a>
												</p>
												<p>Stars: <a href="#">Chris Evans,</a> <a href="#">Samuel L.
														Jackson,</a> <a href="#"> Scarlett Johansson</a></p>
											</div>
										</div>
										<div class="movie-item-style-2">
											<img src="images/uploads/mv3.jpg" alt="">
											<div class="mv-item-infor">
												<h6><a href="#">blade runner <span>(2015)</span></a></h6>
												<p class="rate"><i class="ion-android-star"></i><span>7.3</span> /10
												</p>
												<p class="describe">Armed with a super-suit with the astonishing
													ability to shrink in scale but increase in strength, cat burglar
													Scott Lang must embrace his inner hero and help...</p>
												<p class="run-time"> Run Time: 2h21’ . <span>MMPA: PG-13 </span> .
													<span>Release: 1 May 2015</span></p>
												<p>Director: <a href="#">Peyton Reed</a></p>
												<p>Stars: <a href="#">Paul Rudd,</a> <a href="#"> Michael
														Douglas</a></p>
											</div>
										</div>
										<div class="movie-item-style-2">
											<img src="images/uploads/mv4.jpg" alt="">
											<div class="mv-item-infor">
												<h6><a href="#">Mulholland pride<span> (2013) </span></a></h6>
												<p class="rate"><i class="ion-android-star"></i><span>7.2</span> /10
												</p>
												<p class="describe">When Tony Stark's world is torn apart by a
													formidable terrorist called the Mandarin, he starts an odyssey
													of rebuilding and retribution.</p>
												<p class="run-time"> Run Time: 2h21’ . <span>MMPA: PG-13 </span> .
													<span>Release: 1 May 2015</span></p>
												<p>Director: <a href="#">Shane Black</a></p>
												<p>Stars: <a href="#">Robert Downey Jr., </a> <a href="#"> Guy
														Pearce,</a><a href="#">Don Cheadle</a></p>
											</div>
										</div>
										<div class="movie-item-style-2">
											<img src="images/uploads/mv5.jpg" alt="">
											<div class="mv-item-infor">
												<h6><a href="#">skyfall: evil of boss<span> (2013) </span></a></h6>
												<p class="rate"><i class="ion-android-star"></i><span>7.0</span> /10
												</p>
												<p class="describe">When Tony Stark's world is torn apart by a
													formidable terrorist called the Mandarin, he starts an odyssey
													of rebuilding and retribution.</p>
												<p class="run-time"> Run Time: 2h21’ . <span>MMPA: PG-13 </span> .
													<span>Release: 1 May 2015</span></p>
												<p>Director: <a href="#">Alan Taylor</a></p>
												<p>Stars: <a href="#">Chris Hemsworth, </a> <a href="#"> Natalie
														Portman,</a><a href="#">Tom Hiddleston</a></p>
											</div>
										</div>
										<div class="topbar-filter">
											<label>Movies per page:</label>
											<select>
												<option value="range">5 Movies</option>
												<option value="saab">10 Movies</option>
											</select>
											<div class="pagination2">
												<span>Page 1 of 2:</span>
												<a class="active" href="#">1</a>
												<a href="#">2</a>
												<a href="#"><i class="ion-arrow-right-b"></i></a>
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
</div>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.29.2/dist/sweetalert2.min.js"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<security:authorize access="isAnonymous()">
<script>
	var overlay = $(".overlay");
	var loginct = $( "#login-content" );
	var favoriteFormLink = $(".favoriteFormLink");
	favoriteFormLink.on('click', function(event){
	event.preventDefault();
	loginct.parents(overlay).addClass("openform");
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
});
</script>
</security:authorize>
<c:url var="restAPI_DeleteWithWork_URL" value="/api/userfavorite_withworkcode"/>
<security:authorize access="isAuthenticated()">
<%@include file="/views/web/favorite/addFavorite_Form.jsp"%>
<%@include file="/views/web/vote/userVote.jsp"%>
<script>
var favoriteFormLink = $(".favoriteFormLink");
var faovoriteCT = $("#favoriteForm-content");
var overlay = $(".overlay");
//pop up for Favorite form
favoriteFormLink.on('click', function(event){
	event.preventDefault();
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
});

$(".removeFormFavorite").click(function(e){
		e.preventDefault();
		var workCode = ${model.code};
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
				  var data={};
				  workCodes = [];
				  workCodes.push(workCode+"");	
				  deleteFavorite(workCodes)
			  } 
			});
	})
	function deleteFavorite(data) {
		console.log("data: "+data)
		    $.ajax({
		        url: '${restAPI_DeleteWithWork_URL}',
		        type: 'DELETE',
		        contentType: 'application/json',
		        data: JSON.stringify(data),
		        success: function (result) {
		            window.location.href = "${listURL}?code=${model.code}&message=RemoveFavorite_Success";
		        },
		        error: function (error) {
		        	console.log(error)
		        }
		    });
		}
</script>
</security:authorize>

