<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<fmt:setLocale value="en_US" scope="session" />
<style>
#pagination1 {
	margin: 0px;
	padding: 0px;
	background-color: rgba(0, 0, 0, 0);
}

#pagination1 * {
	background-color: rgba(0, 0, 0, 0);
}

#pagination1 * {
	border: 0px rgba(0, 0, 0, 0);
}

.describe {
	max-height: 50%;
	overflow: hidden;
	text-overflow: ellipsis;
}

.user-img a img {
	border-radius: 50%;
	width: 150px;
	height: 150px;
}

.hero {
	text-align: left;
}

.user-hero .hero-ct h1 {
	margin-left: 0;
}

.user-hero ul.breadcumb {
	margin-left: 0;
}

.sticky-container {
	position: sticky;
	top: 80px;
}
</style>

<div class="hero user-hero">
	<div class="container">
		<div class="col-md-3 col-sm-12 col-xs-12"></div>
		<div class="col-md-9 col-sm-12 col-xs-12">
			<div class="hero-ct">
				<h1 id="name_111">${currentUser.fullName}RateList</h1>
				<ul class="breadcumb">
					<li class="active"><a href="<c:url value='/'/>">Home</a></li>
					<li><span class="ion-ios-arrow-right"></span>Rate Movies</li>
				</ul>
			</div>
		</div>
	</div>
</div>

<div class="page-single userfav_list" style="padding: 0;">
	<div class="container">
		<div class="row ipad-width2" style="display: flex;">
			<div class="col-md-3 col-sm-12 col-xs-12">
				<div class="user-information sticky-container">
					<div class="user-img">
						<a href="#"><img src="<c:url value='${currentUser.avatar}' />"
							alt=""><br></a> <a href="<c:url value='/profile'/>"
							class="redbtn">Change avatar</a>
					</div>
					<div class="user-fav">
						<p>Account Details</p>
						<ul>
							<li><a href="<c:url value='/profile'/>">Profile</a></li>
							<li><a href="<c:url value='/userfavorite'/>">Favorite
									movies</a></li>
							<li class="active"><a href="<c:url value='/userrate'/>">Rated
									movies</a></li>
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
					<p>
						Found <span>${size} rates</span> in total
					</p>
				</div>
				<div class="flex-wrap-movielist user-fav-list"
					style="display: flex; flex-direction: column;">
					<c:forEach var="item" items="${model.listResults}">
						<div class="movie-item-style-2" style="flex: 10">
							<img src="<c:url value='${item.work.thumbnail}'/>"
								style="width: 30%;" alt="">
							<div class="mv-item-infor" style="flex: 6">
								<h6>
									<a href="<c:url value='/work/detail?code=${item.work.code}'/>">${item.work.name}
										<span>(${item.work.relatedDate})</span>
									</a>
								</h6>
								<p class="rate">
									<i class="ion-android-star"></i><span>${item.work.score}</span>
									/10
								</p>
								<p class="describe" id="describe${item.work.code}">${item.work.overview}</p>
								<p class="run-time">
									Your vote: <i class="ion-android-star" style="color: #f5b50a;"></i>
									${item.score}
								</p>
								<p class="run-time">
									Date:
									<fmt:formatDate value="${item.modifiedDate}"
										pattern="dd MMMM yyyy 'at' HH:mm" />
								</p>
								<p>
									Content: <a
										href="<c:url value='/work/detail?code=${item.work.code}#reviews'/>">${item.content}</a>
								</p>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="topbar-filter">
					<label>Rates per page:</label>
					<form id="formChangeFilter" action="<c:url value='/userrate'/>" method="get">
						<select id="maxPageItem" name="limit">
							<option value="8" <c:if test="${model.limit==8}">selected</c:if>>8</option>
							<option value="16" <c:if test="${model.limit==16}">selected</c:if>>16</option>
							<option value="32"<c:if test="${model.limit==32}">selected</c:if>>32</option>
							<option value="64" <c:if test="${model.limit==64}">selected</c:if>>64</option>
						</select>
					</form>
					<div class="pagination2" id="page-review-container"
						style="margin-right: 20px;">
						<span>Page:</span> <a
							href="<c:url value='/userrate?page=1&limit=${model.limit}'/>"
							style="margin-left: 0;">First</a>
						<c:choose>
							<c:when test="${2 == model.nextPage or 3 == model.nextPage}">
								<c:set var="begin" value="1"></c:set>
							</c:when>
							<c:otherwise>
								<c:set var="begin" value="${model.nextPage-3}"></c:set>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when
								test="${model.totalPages == model.nextPage or model.totalPages == model.nextPage-1}">
								<c:set var="end" value="${model.totalPages}"></c:set>
							</c:when>
							<c:otherwise>
								<c:set var="end" value="${model.nextPage+1}"></c:set>
							</c:otherwise>
						</c:choose>
						<c:forEach var="i" begin="${begin}" end="${end}">
							<c:choose>
								<c:when test="${i == model.nextPage-1}">
									<a class="active"
										href="<c:url value='/userrate?page=${i}&limit=${model.limit}'/>"
										style="margin-left: 0;">${i}</a>
								</c:when>
								<c:otherwise>
									<a
										href="<c:url value='/userrate?page=${i}&limit=${model.limit}'/>"
										style="margin-left: 0;">${i}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<a
							href="<c:url value='/userrate?page=${model.totalPages}&limit=${model.limit}'/>"
							style="margin-left: 0;">Last</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
$(document).ready(function(){
    $("#maxPageItem").change(function(){
        var limitValue = $(this).val();
        $("#formChangeFilter").submit();
    });
});
</script>