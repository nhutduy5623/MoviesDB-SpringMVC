<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- page title area start -->

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
                <h4 class="page-title pull-left">Dashboard</h4>
                <ul class="breadcrumbs pull-left">
                    <li><a href="index.html">Home</a></li>
                    <li><span>Datatable</span></li>
                </ul>
            </div>
        </div>
        <div class="col-sm-6 clearfix">
            <div class="user-profile pull-right">
                <img class="avatar user-thumb" src="assets/images/author/avatar.png" alt="avatar">
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
                <div class="card-body">
                    <h4 class="header-title">Data Table Default</h4>
                    <div class="data-tables">
                        <table id="dataTable" class="text-center">
                            <thead class="bg-light text-capitalize">
                                <tr>
                                	<th>Code</th>
                                    <th>Title</th>
                                    <th>ThumbNail</th>
                                    <th>Series</th>
                                    <th>Genre</th>
                                    <th>SubGenre</th>
                                    <th>Rate</th>
                                    <th>Follower Count</th>
                                    <th>Tool</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Airi Satou</td>
                                    <td>Accountant</td>
                                    <td><img style="width: 80px" src="https://www.themoviedb.org/t/p/w600_and_h900_bestv2/cMD9Ygz11zjJzAovURpO75Qg7rT.jpg"></td>
                                    <td>33asdaaaaaaaaaasaaaaaaaaaaaaaaaaaaaaad</td>
                                    <td>2008/11/28</td>
                                    <td>$162,700</td>
                                    <td>Tokyo</td>
                                    <td>33</td>
                                    <td>2008/11/28</td>
                                </tr>                                
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- data table end -->
    </div>
    
    <!-- Start datatable js -->
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
    <script src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.18/js/dataTables.bootstrap4.min.js"></script>
    <script src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
    <script src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap.min.js"></script>
    
</div>

