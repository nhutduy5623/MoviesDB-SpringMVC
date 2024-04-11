<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!--login form popup-->
<%@ page import="com.laptrinhweb.util.SecurityUtil" %>
<%@include file="/common/taglib.jsp"%>    
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@7.29.2/dist/sweetalert2.min.css">

<div class="favoriteForm-wrapper login-wrapper" id="favoriteForm-content">
    <div class="favoriteForm-content login-content">
        <a href="#" class="close">x</a>
        <h3>Add your favorite</h3>
        <form id="form_SubmitFavorite">
        	<div class="row" style=" display: flex; justify-content: center;"> 
        		 <label id="workName" for="username">
        		 
                </label>
        	</div>
           <div class="row">
            	<label for="sl_Status">
                    Status:	
                    <select id="sl_Status" name="viewingStatus">
                    	<option value="1">Watching</option>
                    	<option value="2">Completed</option>
                    	<option value="3">On-hold</option>
                    	<option value="4">Dropper</option>
                    	<option value="5">Prepare to watch</option>
                    </select>
                </label>
            </div>
            <div class="row">
            	<label for="sl_Score">
                    Your score:	                    
                    <select id="sl_Score" name="reviewScore">
                    	<option value="10">(10) Masterpiece</option>
						<option value="9">(9) Great</option>						
						<option value="8">(8) Very Good</option>						
						<option value="7">(7) Good</option>						
						<option value="6">(6) Fine</option>						
						<option value="5">(5) Average</option>						
						<option value="4">(4) Bad</option>						
						<option value="3">(3) Very Bad</option>
						<option value="2">(2) Horrible</option>						
						<option value="1">(1) Appalling</option>
                    </select>
                </label>
            </div>    
            
            <div class="row">
            	<label for="ip_note">
                    Note:	
                    <textarea style="height: 10%" rows="3" cols="5" name="note" id="ip_note"></textarea>
                </label>
            </div>  
            <input type="hidden" id= "id" name="id" value="">      
            <input type="hidden" id = "workCode" name="workCode" value="">
            <input type="hidden" id="userCode" name="userCode" value="<%= SecurityUtil.getPrincipal().getCode() %>">
           <div class="row">
           	 <button id="btnSubmitFavoriteForm" type="submit">Change My Favorite</button>
           </div>
        </form>
    </div>
</div>
<c:url var="restAPI_URL" value="/api/userfavorite"/>
<c:url var="listURL" value="/userfavorite"/>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.29.2/dist/sweetalert2.min.js"></script>

<script>

$("#btnSubmitFavoriteForm").click(function(e){
	e.preventDefault();
    var data = {};
    var formData = $('#form_SubmitFavorite').serializeArray();
    $.each(formData, function (i, v) {
        data[""+v.name+""] = v.value;	
    })    
	save(data);	 	     	    	
});

function save(data){			
	$.ajax({
    	url: '${restAPI_URL}',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (result) {
        	Toast.fire({
  			  icon: "success",
  			  title: "Add Success"
  			});
        	window.location.href = "${listURL}?message=SaveFavorite_Success";
        },
        error: function (error) {
        	console.log(error)
        }
    });
};
const Toast = Swal.mixin({
	  toast: true,
	  position: "top",
	  showConfirmButton: false,
	  timer: 3000,
	  timerProgressBar: true,
	  didOpen: (toast) => {
	    toast.onmouseenter = Swal.stopTimer;
	    toast.onmouseleave = Swal.resumeTimer;
	  }
	});
window.onload = (event) => {
	mess = getUrlParameter("message")
	if(mess!=null && mess != ""){
		Toast.fire({
			  icon: "success",
			  title: mess
		});
	}
};

var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
        }
    }
    return false;
};



</script>