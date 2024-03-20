<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>    
<style>
</style>
<div class="main-content-inner">
    <div class="row">
        <div class="col-12 mt-5">
            <div class="row">
                <!-- Textual inputs start -->
                <div class="col-12 mt-5">
                    <div class="card">
                        <form:form class="card-body" role="form" id="form-edit-save" modelAttribute="model"> 
                            <h4 class="header-title title">Save Related Party Role</h4>
                            <div class="form-group">
                                <label for="code" class="col-form-label">Related Party Role Code</label>
                                <form:input path="code" id="code" cssClass="form-control"/>
                                <!-- <input class="form-control" type="text" name="code" id="code" value="${model.code}"> -->
                            </div>
                            <div class="form-group">
                                <label for="name" class="col-form-label">Name</label>
                                <form:input path="name" id="name" cssClass="form-control"/>
                                <!-- <input class="form-control" type="text" name="name" id="name" value="${model.name}"> -->
                            </div>
							<div class="form-group">
                                <label for="name" class="col-form-label">Detail</label>
                                <form:input path="detail" id="detail" cssClass="form-control"/>
                            </div>
	                        <div class="form-group" style="display: flex; justify-content: flex-end;">
	                        	<button class="btn btn-primary btnSave_Edit" id="btnSave_Edit" type="button">Submit form</button></div>
                            <input type="hidden" name="id" value="${model.id}"> 
                        </form:form>
                    </div>
                </div>
                <!-- Textual inputs end -->
            </div>       
        </div>
    </div>
    <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
    
    <c:url var="listURL" value="/admin/relatedpartyrole"/>
	<c:url var="formURL" value="/admin/relatedpartyrole/edit"/>
	<c:url var="restAPI_URL" value="/api/admin/relatedpartyrole"/>
    <script>
	    $(".btn_changeTypeInputThumbnail").click(function(){
	    	$(".btn_changeTypeInputThumbnail").parent().parent().show();
	    	$(this).parent().parent().hide();
	    });
	    
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
	    
	    var id = getUrlParameter('id');
	    if(id == false){
	    	$(".title").html("Save Role")		    	
	    }    
	    else {
	    	$(".title").html("Edit Role")		    	
	    }  
	    
	    $("#btnSave_Edit").click(function(e){
	    	e.preventDefault();
		    var data = {};
		    var formData = $('#form-edit-save').serializeArray();
		    $.each(formData, function (i, v) {
	            data[""+v.name+""] = v.value;	
		    })
		    uploadFile(formData)
	    	if(id == false)
	 	    	save(data);	 	     
	 	    else 
	 	    	edit(data);	    	    	
	    });
	    
		function save(data){			
			$.ajax({
		    	url: '${restAPI_URL}',
		        type: 'POST',
		        contentType: 'application/json',
		        data: JSON.stringify(data),
		        dataType: 'json',
		        success: function (result) {
		        	window.location.href = "${listURL}?message=insert_success";
		        },
		        error: function (error) {
		        	console.log(error)
		        }
		    });
		};
		
		function edit(data) {
			$.ajax({
		    	url: '${restAPI_URL}',
		        type: 'PUT',
		        contentType: 'application/json',
		        data: JSON.stringify(data),
		        dataType: 'json',
		        success: function (result) {
		        	window.location.href = "${listURL}?message=insert_success";
		        },
		        error: function (error) {
		        	window.location.href = "${listURL}?page=1&limit=2&message=error_system";
		        }
		    });
		};
	    function uploadFile(formData) {
	    	$.ajax({
				url: '${Upload_URL}',
				type: 'POST',
				contentType: false,
				processData: false,
				data: formData,
				success: function(result) {
				},
				error: function (error) {
					console.log(error)
				}
			})
	    }
	</script>
</div>
