<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
header .top-search input {
    background: none;
}

.loader {
  width: 16px;
  height: 16px;
  border-radius: 50%;
  background-color: #fff;
  box-shadow: 32px 0 #fff, -32px 0 #fff;
  position: relative;
  animation: flash 0.5s ease-out infinite alternate;
}

@keyframes flash {
  0% {
    background-color: #FFF2;
    box-shadow: 32px 0 #FFF2, -32px 0 #FFF;
  }
  50% {
    background-color: #FFF;
    box-shadow: 32px 0 #FFF2, -32px 0 #FFF2;
  }
  100% {
    background-color: #FFF2;
    box-shadow: 32px 0 #FFF, -32px 0 #FFF2;
  }
}
      
</style>
<div class="hero common-hero">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="hero-ct">
					<h1> AI Generate</h1>
					<ul class="breadcumb">
						<li class="active"><a href="<c:url value='/'/>">Home</a></li>
						<li> <span class="ion-ios-arrow-right"></span> AI Generate</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="page-single">
	<div class="container">
		<div class="row ipad-width">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="topbar-filter">
					<p style="align-items: flex-start; align-content: flex-start;">Answer: 
						<span id="answer" style="color: #abb7c4">Test Test Test</span> 					
					</p>
				</div>
				<div id="listResult" style="display: flex; justify-content: center;" class="flex-wrap-movielist">
				</div>						
			</div>			
		</div>
	</div>
</div>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
$(document).ready(function(){
	// Tạo một thẻ div mới
	var newDiv = $("<div class='top-search'><input type='text' id='inp_question' placeholder='Search for a movie, TV Show or celebrity that you are looking for'></div>");
	// Thêm thẻ div mới vào trong phần tử có id là "container"
	newDiv.appendTo("#headerContainer");
});
</script>

<script>
$(document).ready(function(){
    // Biến đánh dấu xem người dùng có đang nhập liệu vào ô input hay không
    var isTyping = false;
    var isSearch = true;
    // Lắng nghe sự kiện focus và blur trên input field
    $("#inp_question").on("focus", function() {
        isTyping = true;
    }).on("blur", function() {
        isTyping = false;
    });
    // Lắng nghe sự kiện keyup trên input field
    $("#inp_question").keyup(function(event) {
        // Kiểm tra xem người dùng có đang nhập liệu vào ô input không
        if (isTyping && isSearch && event.which === 13) {
            // Lấy giá trị từ input field
            var inputValue = $(this).val().trim();
            // Kiểm tra xem người dùng có nhập nội dung không
            if (inputValue !== '') {
                // Thực hiện hành động chỉ khi người dùng nhập nội dung
                request(inputValue);
                isSearch = false
            }
        }
    });
    // Hàm thực hiện hành động
    function request(question) {    	
    	$.ajax({
            type: "POST",
            url: "<c:url value='/AiGenerate'/>",
            data:  {"question": question},
            dataType: "json",
            beforeSend: function() {
                // Hiển thị hiệu ứng load
                $("#inp_question").prop("disabled", true);
                $("#answer").html("<span class='loader'></span>");
                $("#listResult").html("<span class='loader'></span>");
            },
            success: function(response) {              	
            	var answer = response.answer;
            	var workDTOs = response.workDTOList
                // Tạo một chuỗi HTML chứa thông tin từ workDTOs
                var htmlListResult = "";
            	var htmlAnswer = "";
            	var img = ""
            	htmlAnswer+=answer
            	for (var i = 0; i < workDTOs.length; i++) {
            		img = workDTOs[i].thumbnail
            		if (img.includes("/template/uploads/")) {
            			img = "<c:url value='/"+workDTOs[i].thumbnail+"'/>"
            		} else {
            			img = "<c:url value='"+workDTOs[i].thumbnail+"'/>"
            		}
                    htmlListResult += "<div class='movie-item-style-2 movie-item-style-1'>"
                   	htmlListResult += "<img src='"+img+"'>";
                   	htmlListResult += "<div class='hvr-inner'>";
                   	htmlListResult += "<a  href='work/detail?code="+workDTOs[i].code+"'> Read more <i class='ion-android-arrow-dropright'></i> </a>";
                   	htmlListResult += "</div>";
                   	htmlListResult += "<div class='mv-item-infor'>";
                   	htmlListResult += "<h6><a href='#'>"+workDTOs[i].name+"</a></h6>";
                   	htmlListResult += "<p class='rate'><i class='ion-android-star'></i><span>"+workDTOs[i].score+"</span> /10</p>";
                   	htmlListResult += "<p class=''><span>"+workDTOs[i].voteCount+"</span> Votes</p>";
                   	htmlListResult += "</div></div>";
                }               
            	$("#answer").html(htmlAnswer);
            	$("#listResult").html(htmlListResult);
           		console.log("Response from Flask API: " + response.answer);	
           		isSearch = true
           		$("#inp_question").prop("disabled", false);
            },
            error: function(xhr, status, error) {
            	$("#answer").html("Đã xảy ra lỗi vui lòng thử lại!");
            	$("#listResult").html("");
            	isSearch = true
                $("#inp_question").prop("disabled", false);
                
            }
        });
    }
});
</script>



