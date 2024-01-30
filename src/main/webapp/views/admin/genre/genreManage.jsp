<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- page title area start -->
<%@include file="/common/taglib.jsp"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@7.29.2/dist/sweetalert2.min.css">
<style>
      table {
        border-collapse: collapse;
        table-layout: fixed;
        width: 100%;
      }
      table td {
        word-wrap: break-word;
      }
    </style>
<div class="page-title-area">
    <div class="row align-items-center">
        <div class="col-sm-6">
            <div class="breadcrumbs-area clearfix">
                <h4 class="page-title pull-left">Genre Management</h4>
                <ul class="breadcrumbs pull-left">
                    <li><a href="home">Home</a></li>
                    <li><span>Genre Management</span></li>
                </ul>
            </div>
        </div>
        <div class="col-sm-6 clearfix">
            <div class="user-profile pull-right">
                <img class="avatar user-thumb" src="<c:url value='/template/admin/assets/images/author/avatar.png'/>" alt="avatar">
                <h4 class="user-name dropdown-toggle" data-toggle="dropdown">Kumkum Rai <i class="fa fa-angle-down"></i></h4>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">Message</a>
                    <a class="dropdown-item" href="#">Settings</a>
                    <a class="dropdown-item" href="#">Log Out</a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- page title area end -->
<div class="main-content-inner">
    <div class="row">
        <!-- data table start -->
        <div class="col-12 mt-5">
            <div class="card">
                <div class="card-body" style="padding-top: 0%">
                    <div style="width:100%; display: flex; flex-direction: columns; justify-content: space-between; align-content: center; margin: 2% 0% ; text-align: center;">
	                    <div  style="text-align: center; margin: auto 0%;"><p class="header-title" style="padding: 0%; margin: 0%">Genre Data</p></div>
	                    <a href="genre/save"><button type="button" class="btn btn-primary"><div class="fw-icons"><i class="fa fa-plus-square" style="margin-right: 2px"></i> Add New</div></button></a>
                    </div>
                    <div class="data-tables">
                        <table id="dataTable" class="text-center">
                            <thead class="bg-light text-capitalize">
                                <tr>
                                	<th>Code</th>
                                    <th>Name</th>
                                    <th>ThumbNail</th>
                                    <th>Create By</th>
                                    <th>Create Date</th>
                                    <th>Modified By</th>
                                    <th>Modified Date</th>
                                    <th>Tool</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${listResults}">
                                	<tr>
	                                    <td>${item.code}</td>
	                                    <td>${item.name}</td>
	                                    <td><img style="padding: 10%; border-radius: 1%" src="${item.thumbnail}"></td>
	                                    <td>${item.createdBy}</td>
	                                    <td>${item.createdDate}</td>
	                                    <td>${item.modifiedBy}</td>
	                                    <td>${item.modifiedDate}</td>
	                                    <td >
	                                   		<div class="fw-icons">
	                                   			<a href="genre/edit?id=${item.id}"><i class="fa fa-wrench"></i>Edit</a>
	                                    	</div>
	                                    	<div class="fw-icons" id="${item.id}">
	                                    		<a href="#" id="${item.id}" class="btnDelete" onclick="warningBeforeDelete(${item.id})"><i class="fa fa-trash"></i>Delete</a>
	                                    	</div>		       
	                                    	                            
	                                    </td>
                                	</tr>  
                                </c:forEach>                              
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- data table end -->
         <ul class="pagination" id="pagination1"></ul>
    </div>
   	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.29.2/dist/sweetalert2.min.js"></script>
   	<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
   	<script src="<c:url value='/template/paging/jq-paginator.js'/>"></script>
    <script type="text/javascript">
		$.jqPaginator('#pagination1', {
			totalPages : 5,
			visiblePages : 5,
			currentPage : 1,
			onPageChange : function(nextPage, type) {
			}
		});
		
		
		
		
	</script>
	<c:url var="restAPI_URL" value="/api/admin/genre"/>
	<script type="text/javascript">
	function warningBeforeDelete(id) {
			swal({
			  title: "Xác nhận xóa",
			  text: "Bạn có chắc chắn muốn xóa hay không",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonClass: "btn-success",
			  cancelButtonClass: "btn-danger",
			  confirmButtonText: "Xác nhận",
			  cancelButtonText: "Hủy bỏ",
			}).then(function(isConfirm) {
			  if (isConfirm['value']) {
				  deleteElement(id)
			  } 
			});
		} 
		function deleteElement(data) {
		    $.ajax({
		        url: '${restAPI_URL}',
		        type: 'DELETE',
		        contentType: 'application/json',
		        data: JSON.stringify(data),
		        success: function (result) {
		            window.location.href = "${newURL}?page=1&limit=2&message=delete_success";
		        },
		        error: function (error) {
		        	console.log(error)
		        }
		    });
		}
		
	</script>
</div>
