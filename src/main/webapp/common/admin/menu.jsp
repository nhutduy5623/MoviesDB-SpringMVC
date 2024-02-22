<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- sidebar menu area start -->
<div class="sidebar-menu">
    <div class="sidebar-header">
        <div class="logo">
            <a href="index2.html"><img src="<c:url value='/template/admin/assets/images/icon/logo.png'/>" alt="logo"></a>
        </div>
    </div>
    <div class="main-menu">
        <div class="menu-inner">
            <nav>
                <ul class="metismenu" id="menu">
                    <li class="active">
                        <a href="javascript:void(0)" aria-expanded="true"><i class="ti-dashboard"></i><span>dashboard</span></a>
                        <ul class="collapse">
                            <li class="active"><a href="index2.html">Dashboard</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="javascript:void(0)" aria-expanded="true"><i class="fa fa-table"></i>
                            <span>Genre Management</span></a>
	                        <ul class="collapse">
	                        	<li><a href="<c:url value='/admin/genre'/>">Genre Management</a></li>
	                            <li><a href="<c:url value='/admin/subgenre'/>">SubGenre Management</a></li>
	                        </ul>
                    </li> 
                    
                    <li>
                        <a href="javascript:void(0)" aria-expanded="true"><i class="fa fa-table"></i>
                            <span>Work Management</span></a>
	                        <ul class="collapse">
	                            <li><a href="<c:url value='/admin/serie'/>">Series Management</a></li>
	                            <li><a href="<c:url value='/admin/work'/>">Work Management</a></li>
	                            <li><a href="<c:url value='/admin/comment'/>">Comment Management</a></li>
	                        </ul>
                    </li>  
                    
                    <li>
                        <a href="javascript:void(0)" aria-expanded="true"><i class="fa fa-table"></i>
                            <span>RelatedParty Management</span></a>
	                        <ul class="collapse">
	                            <li><a href="<c:url value='/admin/relatedpartyrole'/>">Role Management</a></li>
	                            <li><a href="<c:url value='/admin/relatedparty'/>">RelatedParty Management</a></li>
	                        </ul>
                    </li>  
                    
                    <li>
                        <a href="javascript:void(0)" aria-expanded="true"><i class="fa fa-table"></i>
                            <span>Account Management</span></a>
	                        <ul class="collapse">
	                            <li><a href="<c:url value='/admin/account'/>">Account Management</a></li>
	                        </ul>
                    </li>  
                    
                    <li>
                        <a href="javascript:void(0)" aria-expanded="true"><i class="fa fa-table"></i>
                            <span>Contact Management</span></a>
                        <ul class="collapse">
                            <li><a href="datatable.html">datatable</a></li>
                        </ul>
                    </li>
                    
                    <li>
                        <a href="javascript:void(0)" aria-expanded="true"><i class="fa fa-table"></i>
                            <span>Authorization Management</span></a>
                        <ul class="collapse">
                            <li><a href="<c:url value='/admin/role'/>">Role Management</a></li>
                            <li><a href="<c:url value='/admin/permission'/>">Permission Management</a></li>
                        </ul>
                    </li>                             
                </ul>
            </nav>
        </div>
    </div>
</div>
<!-- sidebar menu area end -->