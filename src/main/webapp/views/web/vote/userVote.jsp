
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<style>
	.rate-container {
		margin-top: 10px;
		position: relative;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%) rotateY(180deg);
	}
	
	.rate-container .rate-container__items {
		display: flex;
		align-items: center;
		justify-content: center;
		gap: 0 .5em;
		width: 100%;
		height: 100%;
	}
	
	.rate-container .rate-container__items input {
		display: none;
	}
	
	.rate-container .rate-container__items label {
		width: 20%;
		margin: 0;
		aspect-ratio: 1;
		cursor: pointer;
	}
	
	.rate-container .rate-container__items label .star-stroke {
		display: grid;
		place-items: center;
		width: 100%;
		height: 100%;
		background: gray;
		clip-path: polygon(50% 0%, 61% 35%, 98% 35%, 68% 57%, 79% 91%, 50% 70%, 21% 91%,
			32% 57%, 2% 35%, 39% 35%);
	}
	
	.rate-container .rate-container__items label .star-stroke .star-fill {
		width: 70%;
		aspect-ratio: 1;
		background: #191919;
		clip-path: polygon(50% 0%, 61% 35%, 98% 35%, 68% 57%, 79% 91%, 50% 70%, 21% 91%,
			32% 57%, 2% 35%, 39% 35%);
	}
	
	.rate-container .rate-container__items input:hover ~ label .star-stroke,
		.rate-container .rate-container__items input:checked ~ label .star-stroke {
		background: hsl(38 90% 55%);
	}
	
	.rate-container .rate-container__items input:checked ~ label .star-stroke .star-fill
		{
		background: hsl(38 90% 55%);
	}
	.confirm-delete-content{
		background: white;
	    padding: 20px;
	    border-radius: 10px;
	}
	.confirm-delete-content div{
		float: right;
		margin-top: 50px;
	}
	.confirm-delete-content div button{
		margin-top: 20px;
		margin-left: 20px;
		border-radius: .25rem;
		border: none;
		width: 70px;
	}
	.confirm-delete-content span{
		font-family: 'Dosis', sans-serif;
    	font-size: 25px;
    	font-weight: bold;
	}
	.confirm-delete-content span i{
		color: red;
	}
	.confirm-delete-content .btn-no-del-your-review{
		background-color: #565e64;
		color: #ffff;
	}
	.confirm-delete-content .btn-no-del-your-review:hover{
		background-color: #474e53;
	}
	
	.confirm-delete-content .btn-yes-del-your-review{
		background-color: #0d6efd;
		color: #ffff;
	}
	.confirm-delete-content .btn-yes-del-your-review:hover{
		background-color: #0653c5;
	}
</style>
<div class="login-wrapper" id="vote-content">
	<div class="login-content">
		<a href="#" class="close">x</a>
		<h3>Write Review</h3>
		<form method="post" action="<c:url value='/work/vote'/>" id="form-vote">
			<div class="mb-3">
				<label class="form-label" style="margin-bottom: 10px;">Rating star</label>
				<div class="rate-container">
					<div class="rate-container__items">
						<input type="radio" name="stars" id="st10" value="10"> <label for="st10">
							<div class="star-stroke">
								<div class="star-fill"></div>
							</div>
						</label> <input type="radio" name="stars" id="st9" value="9"> <label
							for="st9">
							<div class="star-stroke">
								<div class="star-fill"></div>
							</div>
						</label> <input type="radio" name="stars" id="st8" value="8"> <label
							for="st8">
							<div class="star-stroke">
								<div class="star-fill"></div>
							</div>
						</label> <input type="radio" name="stars" id="st7" value="7"> <label
							for="st7">
							<div class="star-stroke">
								<div class="star-fill"></div>
							</div>
						</label> <input type="radio" name="stars" id="st6" value="6"> <label
							for="st6">
							<div class="star-stroke">
								<div class="star-fill"></div>
							</div>
						</label> <input type="radio" name="stars" id="st5" value="5"> <label
							for="st5">
							<div class="star-stroke">
								<div class="star-fill"></div>
							</div>
						</label> <input type="radio" name="stars" id="st4" value="4"> <label
							for="st4">
							<div class="star-stroke">
								<div class="star-fill"></div>
							</div>
						</label> <input type="radio" name="stars" id="st3" value="3"> <label
							for="st3">
							<div class="star-stroke">
								<div class="star-fill"></div>
							</div>
						</label> <input type="radio" name="stars" id="st2" value="2"> <label
							for="st2">
							<div class="star-stroke">
								<div class="star-fill"></div>
							</div>
						</label> <input type="radio" name="stars" id="st1" value="1" checked> <label
							for="st1">
							<div class="star-stroke">
								<div class="star-fill"></div>
							</div>
						</label>
					</div>
				</div>
			</div>
			<div class="mb-3">
				<label class="form-label" style="margin-bottom: 10px;">Write content</label>
				<textarea class="form-control" rows="7" id="txt-content-vote" required></textarea>
			</div>
			<button type="submit" id="btn-submit-review" style="margin-top: 20px">Send</button>
		</form>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.29.2/dist/sweetalert2.min.js"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	$(document).ready(function() {
		
		var voteLink = $("#btn-write-review");
		var votect = $("#vote-content");
		if($("#your-review").length){
			voteLink.hide();
			btnEditYourReviewClick();
			btnDelYourReviewClick();
		}
		
		voteLink.on('click', function(event) {
			event.preventDefault();
			$("#txt-content-vote").html("");
    		$("#st1").click();
			var overlay = $(".overlay");
			votect.parents(overlay).addClass("openform");
		});
		
		$(document).on('click', function(e) {
			var target = $(e.target);
			if ($(target).hasClass("overlay")) {
				$(target).removeClass("openform");
				setTimeout(function() {
					$(target).removeClass("openform");
				}, 35000);
			}
		});
		
		var formVote = $("#form-vote")
		formVote.on("submit", function(event) {
			event.preventDefault();
			var btnSubmit = $("#btn-submit-review");
			btnSubmit.html('<img alt="" src="/MoviesDB_springMVC/template/web/images/fancybox_loading.gif">');
			btnSubmit.css('pointer-events', 'none');
			const url = formVote.attr("action");
			var score = $('input[name="stars"]:checked').val();
			var content = $("#txt-content-vote").val();
			var urlObj = new URL(window.location.href);
			var code = urlObj.searchParams.get("code");
			var jsonData = {};
			jsonData['score'] = score;
			jsonData['content'] = content;
			jsonData['workCode'] = code;
			
			console.log(jsonData);
			fetch(url, {
				method: 'POST',
				headers: {
					"Content-Type": 'application/json',
				},
				body: JSON.stringify(jsonData),
			})
			.then(response => {
				btnSubmit.html('Send');
				btnSubmit.css('pointer-events', '');
				voteLink.hide();
				if (response.ok) {
					closePopupWriteReview();
					return response.json();
				}
			})
			.then(data => {
				if(!$("#your-review").length){
					var spanItemCount = $("#review-count");
			        spanItemCount.text(parseInt(spanItemCount.text().split(" ")[0])+1+' reviews');
				}else{
					$("#your-review").remove();
				}
				var yourReviewDiv = $("<div>").attr("id", "your-review");
				var topbarFilterDiv = $("<div>").addClass("topbar-filter your-review-topbar");
				var pElement = $("<p>").text("Your review");
				var yourReviewControlDiv = $("<div>").addClass("your-review-control");
				var btnEditYourReview = $("<button>").attr("type", "button").attr("id", "btn-edit-your-review").addClass("btn-edit-your-review");
				btnEditYourReview.append($("<i>").addClass("fa fa-pencil-square-o"));
				var btnDelYourReview = $("<button>").attr("type", "button").attr("id", "btn-del-your-review").addClass("btn-del-danger");
				btnDelYourReview.append($("<i>").addClass("fa fa-trash-o"));
				yourReviewControlDiv.append(btnEditYourReview, btnDelYourReview);
				topbarFilterDiv.append(pElement, yourReviewControlDiv);
				yourReviewDiv.append(topbarFilterDiv);
				createItemReview(yourReviewDiv, data.userAvatar, data.userFullName, data.score, data.date, data.content)
				$("#container-your-review").append(yourReviewDiv);
				btnEditYourReviewClick();
				btnDelYourReviewClick();
			})
			.catch(error => {
				console.error('Error:', error);
			});
		});
		
		function closePopupWriteReview(){
			$(".overlay").removeClass("openform");
		}
		
	    // Số lượng trang trước và sau trang hiện tại
	    var numPagesToShow = 2;

	    // Tìm ra phần tử <a> đang được chọn
	    var activeLink = $("#page-review-container .active");

	    // Lấy chỉ số của trang hiện tại
	    var currentPageIndex = $("#page-review-container a").index(activeLink);

	    // Ẩn tất cả các phần tử trang
	    $("#page-review-container a").hide();

	    // Hiển thị trang hiện tại
	    activeLink.show();

	    // Hiển thị các trang trước và sau trang hiện tại
	    for (var i = currentPageIndex - numPagesToShow; i <= currentPageIndex + numPagesToShow; i++) {
	        if (i >= 0 && i < $("#page-review-container a").length) {
	            $("#page-review-container a").eq(i).show();
	            $("#page-review-container a").eq($("#page-review-container a").length-1).show();
	        }
	    }

	    // Xử lý sự kiện click trên các phần tử <a> của pagination
	    $("#page-review-container a").click(pageReviewClick);
	    function pageReviewClick(event) {
	        event.preventDefault(); // Ngăn chặn hành động mặc định của thẻ a (chuyển trang)

	        // Lấy chỉ số của phần tử <a> được nhấp
	        var clickedPageIndex = $("#page-review-container a").index($(this));
	  
	        // Hiển thị lại trang hiện tại
	        $("#page-review-container .active").removeClass("active");
	        $(this).addClass("active");

	        // Ẩn tất cả các phần tử trang
	        $("#page-review-container a").hide();

	        // Hiển thị các trang trước và sau trang được nhấp
	        for (var i = clickedPageIndex - numPagesToShow; i <= clickedPageIndex + numPagesToShow; i++) {
	            if (i >= 0 && i < $("#page-review-container a").length) {
	                $("#page-review-container a").eq(i).show();
	                $("#page-review-container a").eq(0).show();
	                $("#page-review-container a").eq($("#page-review-container a").length-1).show();
	            }
	        }
	        var urlObj = new URL(window.location.href);
			var code = urlObj.searchParams.get("code");
			var page = $(this).attr("href");
			var limit = $("#review-limit-per-page").val();
	        var url = 'vote?code='+code+'&page='+page+'&limit='+limit
	        fectPageReviewData(url, function(totalPage){});
	    }
	    
	    
	    $("#review-limit-per-page").change(function() {
	    	var selectedValue = $(this).val();
	    	var urlObj = new URL(window.location.href);
			var code = urlObj.searchParams.get("code");
	        var url = 'vote?code='+code+'&page=1&limit='+selectedValue
	    	fectPageReviewData(url, function(totalPage){
	    		var container = $("#page-review-container");
	    		container.empty();
	    		container.append('<span>Page:</span>'+
	    						'<a href="1" style="margin-left: 0;">First</a>'+
	    						'<a class="active" href="1" style="margin-left: 0;">1</a> ');
	    		for (let i=2; i <= totalPage; i++){
	    			container.append('<a href="'+i+'" style="margin-left: 0;">'+i+'</a>');
	    		}
	    		container.append('<a href="'+totalPage+'" style="margin-left: 0;">Last</a>');
	    		$("#page-review-container a").click(pageReviewClick);
	    	});
	    });
	    
	    function btnEditYourReviewClick(){
	    	var btn = $("#btn-edit-your-review");
	    	btn.click(function(e){
	    		var content = $("#your-review").children(".mv-user-review-item").children("p").text();
	    		var starCount = $("#your-review").find("i.ion-android-star").not(".last").length;
	    		$("#txt-content-vote").html(content);
	    		$("#st"+starCount).click();
	    		var overlay = $(".overlay");
				votect.parents(overlay).addClass("openform");
	    	});
	    }
	    
		function btnDelYourReviewClick(){
			var btn = $("#btn-del-your-review");
			btn.click(function(e) {
				var overlayDiv = $("<div>").addClass("overlay openform");
				var confirmDeleteContentDiv = $("<div>").addClass("confirm-delete-content");
				var spanElement = $("<span>").html('<i class="fa fa-exclamation-triangle"></i> Delete your comment!');
				var buttonDiv = $("<div>");
				var btnYes = $("<button>").attr("type", "button").addClass("btn-yes-del-your-review").text("Yes");
				var btnNo = $("<button>").attr("type", "button").addClass("btn-no-del-your-review").text("No");
				buttonDiv.append(btnYes, btnNo);
				confirmDeleteContentDiv.append(spanElement, buttonDiv);
				overlayDiv.append(confirmDeleteContentDiv);
				overlayDiv.appendTo("body");

				btnNo.click(function(e){
					overlayDiv.remove();
				});
				btnYes.click(function(e){
					overlayDiv.remove();
					var urlObj = new URL(window.location.href);
					var code = urlObj.searchParams.get("code");
			        var url = 'vote?code='+code;
			        var spanItemCount = $("#review-count");
			        spanItemCount.text(parseInt(spanItemCount.text().split(" ")[0])-1+' reviews');
					delYourReview(url);
				});
			});
	    }
	    
	    //Get data for page
	    function fectPageReviewData(url, callback){
	    	fetch(url, {
				method: 'GET',
				headers: {
					"Content-Type": 'application/json',
				},
			})
			.then(response => {
				if (response.ok) {
					return response.json();
				}
			})
			.then(data => {
				var container = $("#other-review");
				container.empty()
				for (var element of data.results) {
					createItemReview(container, element.userAvatar,
							element.userFullName, element.score, element.date, element.content);
				}
				callback(data.totalPage);
			})
			.catch(error => {
				console.error('Error:', error);
			});
	    }
	    
	    function createItemReview(container, userAvatar, userFullName, score, date, content){
			// Tạo một thẻ div mới
			var $div = $("<div>").addClass("mv-user-review-item");
			// Tạo phần tử user-infor và thêm vào trong thẻ div
			var $userInfor = $("<div>").addClass("user-infor").appendTo($div);
			// Tạo phần tử ảnh và thêm vào trong phần tử user-infor
			$("<img>").attr("src", '..'+userAvatar).attr("alt", "").appendTo($userInfor);
			// Tạo phần tử div chứa tên và đánh giá và thêm vào trong phần tử user-infor
			var $userInfo = $("<div>").appendTo($userInfor);
			$("<h3>").text(userFullName).appendTo($userInfo);
			var scoreStar = ''
			for (let i = 0; i < score; i++) {
				scoreStar += '<i class="ion-android-star"></i>'
			}
			for (let i = score; i < 10; i++) {
				scoreStar += '<i class="ion-android-star last"></i>'
			}
			$("<div>").addClass("no-star").appendTo($userInfo).append(scoreStar);
			$("<p>").addClass("time").text(date).appendTo($userInfo);
			// Thêm phần tử p chứa đánh giá vào trong thẻ div
			$("<p>").text(content).appendTo($div);
			// Thêm thẻ div vào một phần tử trong DOM
			$div.appendTo(container);
		}
	    
	    function delYourReview(url){
	    	fetch(url, {
	    		method: 'DELETE',
				headers: {
					"Content-Type": 'application/json',
				},
			})
			.then(response => {
				if (response.ok) {
					return response.json();
				}
			})
			.then(data => {
				if(data.success){
					$("#your-review").remove();
					$("#btn-write-review").show();
				}
			})
			.catch(error => {
				console.error('Error:', error);
			});
	    }
	});

</script>
