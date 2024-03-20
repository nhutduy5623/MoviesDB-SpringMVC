<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@include file="/common/taglib.jsp" %>
        <style>
            .checkBoxList>span {
                margin: 0 1%;
            }

            .checkBoxListRelatedParty {
                width: 100%; 
                display: "flex";
                flex-derection: "column";
                padding: 0 2%;
            }

            .listRelatedParty_1Role * {
            	box-sizing: border-box;
            }
            .listRelatedParty_1Role {
                width: 100%;
                display: flex;
                flex-direction: row;
                flex-wrap: wrap;
                padding: 0 3%;
                box-sizing: border-box;
                background-color: rgb(221, 240, 235);
				border-left: rgb(164, 171, 169) solid 20px;
            }

            .relatedPartyItem {
                width: 20%;
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                margin: 0 1%;
                text-align: center;
                height: 100%;

            }
            .img_value_RP {
            	width: 100%;
            	height: 70%
            	display: flex;
                flex-direction: column;                
            }
            .img_value_RP >img {
            	width: 70%;
            	border-radius: 5px; 
            }
            
            .inform_RP {
            	display: flex;
            	flex-direction: column;
            	width: 100%;
            	height: 100%;
            	text-align: center;
            	
            }
        </style>
        <div class="main-content-inner">
            <div class="row">
                <div class="col-12 mt-5">
                    <div class="row">
                        <!-- Textual inputs start -->
                        <div class="col-12 mt-5">
                            <div class="card">
                                <form:form class="card-body" role="form" id="form-edit-save" modelAttribute="model">
                                    <h4 class="header-title title">Save Work</h4>
                                    <label for="code" class="col-form-label">Work Code (Fill With API:
                                        input:genre/code-Ex:tv/11110)</label>
                                    <div class="form-group mb-3 fillInformAPI"
                                        style="display: flex; flex-direction: row; flex-wrap: nowrap;">
                                        <div class="btn_fillInformAPI">
                                            <button class="btn btn-outline-secondary btn_fillInformAPI" type="button">
                                                Fill With Code </button>
                                        </div>
                                        <div class="form-group" style="width: 100%; margin-bottom: 0%">
                                            <form:input path="code" id="code" cssClass="form-control" />
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="name" class="col-form-label">Work Name</label>
                                        <form:input path="name" id="name" cssClass="form-control" />
                                        <!-- <input class="form-control" type="text" name="name" id="name" value="${model.name}"> -->
                                    </div>
                                    <div class="form-group">
                                        <label for="overview" class="col-form-label">Overview</label>
                                        <form:input path="overview" id="overview" cssClass="form-control" />
                                        <!-- <input class="form-control" type="text" name="name" id="name" value="${model.name}"> -->
                                    </div>
                                    <div class="form-group">
                                        <label for="overview" class="col-form-label">Trailer link (From Youtube!!!)</label>
                                        <form:input path="video" id="video" cssClass="form-control" />
                                        <!-- <input class="form-control" type="text" name="name" id="name" value="${model.name}"> -->
                                    </div>
                                    <div class="form-group">
                                        <label for="budget" class="col-form-label">Budget</label>
                                        <form:input type="number" path="budget" id="budget" cssClass="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="followerCount" class="col-form-label">Follower Count</label>
                                        <form:input type="number" path="followerCount" id="followerCount" cssClass="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="viewerCount" class="col-form-label">Viewer Count</label>
                                        <form:input type="number" path="viewerCount" id="viewerCount" cssClass="form-control" />
                                    </div>
                                    <div class="form-group" style="display: flex; flex-direction: row;">
                                        <div
                                            style="display: flex; flex-direction: column; width: 50%; margin-right: 1px">
                                            <label for="score" class="col-form-label">Score</label>
                                            <div class="form-group" style="margin-bottom: 0">
                                                <form:input type="number" path="score" id="score" cssClass="form-control" />
                                            </div>
                                        </div>
                                        <div
                                            style="display: flex; flex-direction: column; width: 50%; margin-left: 1px">
                                            <label for="voteCount" class="col-form-label">Vote Count</label>
                                            <div class="form-group" style="margin-bottom: 0;">
                                                <form:input type="number" path="voteCount" id="voteCount" cssClass="form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="relatedDate" class="col-form-label">Related Date</label>
                                       	<form:input  type="date" path="relatedDate" id="relatedDate" cssClass="form-control" />
                                            
                                    </div>
                                    <div class="form-group">
                                        <label for="serieCode" class="col-form-label">Serie</label>
                                        <form:input path="serieCode" id="serieCode" cssClass="form-control" />
                                    </div>
                                    <label for="example-email-input" class="col-form-label">Work Thumbnail (URL or
                                        UploadFile)</label>
                                    <div class="form-group mb-3 changeTypeInputThumbnail"
                                        style="display: flex; flex-direction: row; flex-wrap: nowrap;">
                                        <div>
                                            <button class="btn btn-outline-secondary btn_changeTypeInputThumbnail"
                                                type="button"> With URL <i class="fa fa-exchange"></i></button>
                                        </div>
                                        <div class="form-group" style="width: 100%; margin-bottom: 0%">
                                            <input type="text" class="form-control" id="thumbnail1"
                                                value="${model.thumbnail}">
                                        </div>
                                    </div>
                                    <div class="input-group mb-3 changeTypeInputThumbnail" style="display: none">
                                        <div class="input-group-prepend ">
                                            <button class="btn btn-outline-secondary btn_changeTypeInputThumbnail"
                                                type="button"> With UploadFile <i class="fa fa-exchange"></i></button>
                                        </div>
                                        <div class="custom-file">
                                            <input type="file" class="custom-file-input" id="thumbnail2"
                                                name="thumbnail2">
                                            <label class="custom-file-label" for="thumbnail">Choose file</label>
                                        </div>
                                    </div>

                                    <label for="example-email-input" class="col-form-label">Work Background (URL or
                                        UploadFile)</label>
                                    <div class="form-group mb-3 changeTypeInputBackground"
                                        style="display: flex; flex-direction: row; flex-wrap: nowrap;">
                                        <div>
                                            <button class="btn btn-outline-secondary btn_changeTypeInputBackground"
                                                type="button"> With URL <i class="fa fa-exchange"></i></button>
                                        </div>
                                        <div class="form-group" style="width: 100%; margin-bottom: 0%">
                                            <input type="text" class="form-control" id="background1"
                                                value="${model.background}">
                                        </div>
                                    </div>
                                    <div class="input-group mb-3 changeTypeInputBackground" style="display: none">
                                        <div class="input-group-prepend ">
                                            <button class="btn btn-outline-secondary btn_changeTypeInputBackground"
                                                type="button"> With UploadFile <i class="fa fa-exchange"></i></button>
                                        </div>
                                        <div class="custom-file">
                                            <input type="file" class="custom-file-input" id="background2"
                                                name="background2">
                                            <label class="custom-file-label" for="background">Choose file</label>
                                        </div>
                                    </div>

                                    <label for="example-email-input" class="col-form-label">Genres</label>
                                    <div class="form-group checkBoxList" style="display: flex; flex-wrap: wrap">
                                        <form:radiobuttons items="${genreCodeList}" path="genreCode" />
                                    </div>
                                    <label for="example-email-input" class="col-form-label">SubGenres</label>
                                    <div class="form-group checkBoxList " style="display: flex; flex-wrap: wrap">
                                        <form:checkboxes items="${subGenreCodeList}" class="subgenreCheckBox" path="subGenreCodeList" />
                                    </div>
                                    <label for="example-email-input" class="col-form-label">RelatedParty</label>
									<div class="form-group checkBoxListRelatedParty">
									  <c:forEach var="item" items="${listRelatedPartyRole}">
									    <label for="example-email-input" class="col-form-label">${item.name}</label>
									    <div class="listRelatedParty_1Role">
									      <c:forEach var="relatedPartyByWork" items="${listRPByWork.entrySet()}" varStatus="loop">
									        <c:if test='${relatedPartyByWork.key.roleCode == item.code}'>
									          <div class="relatedPartyItem">
									            <input type="checkbox" id="relatedPartyByWork${loop.index}" checked="checked" class="btn_relatedPartyCheckBox"
									              value="${relatedPartyByWork.key.code}" />
									            <label for="relatedPartyWithout${loop.index}">
									              <img class="img_relatedPartyCheckBox" src='${relatedPartyByWork.key.avatar}' />
									              <div class="inform_RP">
									                <div>${relatedPartyByWork.key.name}</div>
									                Role:
									                <input type="text" class="inputRole" value="${relatedPartyByWork.value}"
									                  class="form-control" />
									              </div>
									            </label>
									          </div>
									        </c:if>
									      </c:forEach>
									      <c:forEach var="relatedPartyWithoutWork" items="${listRPWithoutWork}" varStatus="loop">
									        <c:if test='${relatedPartyWithoutWork.roleCode == item.code}'>
									          <div class="relatedPartyItem">
									            <input type="checkbox" id="relatedPartyWithout${loop.index}" class="btn_relatedPartyCheckBox"
									              value="${relatedPartyWithoutWork.code}" />
									            <label for="relatedPartyWithout${loop.index}">
									              <img class="img_relatedPartyCheckBox" src='${relatedPartyWithoutWork.avatar}' />
									              <div class="inform_RP">
									                <div>${relatedPartyWithoutWork.name}</div>
									                Role:
									                <input type="text" class="inputRole" class="form-control" />
									              </div>
									            </label>
									          </div>
									        </c:if>
									      </c:forEach>
									
									
									    </div>
									  </c:forEach>
									</div>
                                    <div class="form-group" style="display: flex; justify-content: flex-end;"><button
                                            class="btn btn-primary btnSave_Edit" id="btnSave_Edit" type="button">Submit
                                            form</button></div>
                                    <input type="hidden" name="id" id="id" value="${model.id}">
                                </form:form>
                            </div>
                        </div>
                        <!-- Textual inputs end -->
                    </div>
                </div>
            </div>
            <c:if test="${model.genreCode!=''}">
                <script>
                    $('input[type=radio][name=genreCode]').val('${model.genreCode}').change()
                </script>
            </c:if>

            <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>

            <c:url var="listURL" value="/admin/work" />
            <c:url var="formURL" value="/admin/work/edit" />
            <c:url var="restAPI_URL" value="/api/admin/work" />
            <c:url var="API_SubGenre_URL" value="/api/subgenre/getbygenrecode" />
            <c:url var="Upload_URL" value="/api/admin/UploadFile" />
            <c:url var="UploadImg_URL" value="/uploads/" />
            <c:url var="API_GetInformTMDB" value="/admin/work/fullfillinform" />
            
            <script>
                $(".btn_changeTypeInputThumbnail").click(function () {
                    $(".btn_changeTypeInputThumbnail").parent().parent().show();
                    $(this).parent().parent().hide();
                });
                $(".btn_changeTypeInputBackground").click(function () {
                    $(".btn_changeTypeInputBackground").parent().parent().show();
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
                if (id == false) {
                    $(".title").html("Save Work")
                }
                else {
                    $(".title").html("Edit Work")
                }

                $("#btnSave_Edit").click(function (e) {
                    e.preventDefault();
                    var data = {};
                    var formData = $('#form-edit-save').serializeArray();
                    console.log("FormData: " + formData[3].value)
                    $.each(formData, function (i, v) {
                        data["" + v.name + ""] = v.value;
                    })
                    //Xử lý thumbnail, Background
                    data["thumbnail"] = $("#thumbnail1").val();
                    var isParentVisible = $("#thumbnail2").parent().parent().is(":visible");
                    if (isParentVisible == false)
                        data["thumbnail"] = $("#thumbnail1").val();
                    else {
                        thumbnail = $("#thumbnail2")[0].files[0].name;
                        data["thumbnail"] = "/template/uploads/" + thumbnail;
                        var formData = new FormData();
                        formData.append('fileName', $("#thumbnail2")[0].files[0]);
                        uploadFile(formData)
                    }
                    data["background"] = $("#background1").val();
                    var isParentVisible = $("#background2").parent().parent().is(":visible");
                    if (isParentVisible == false)
                        data["background"] = $("#background1").val();
                    else {
                        thumbnail = $("#background2")[0].files[0].name;
                        data["background"] = "/template/uploads/" + thumbnail;
                        var formData = new FormData();
                        formData.append('fileName', $("#background2")[0].files[0]);
                        uploadFile(formData)
                    }

                    var subGenreCodeList = $('input[type=checkbox].subgenreCheckBox:checked').map(function () {
                        return $(this).val();
                    }).get();
                    console.log("subGenreCodeList: " + subGenreCodeList)
                    data["subGenreCodeList"] = subGenreCodeList;
                    
                    //RelatedParty:
                    var listRelatedPartyCode_Role = [];

					$('.relatedPartyItem').each(function() {
					    var $checkbox = $(this).find('.btn_relatedPartyCheckBox');
					    
					    if ($checkbox.prop('checked')) {
					        var relatedPartyCode = $checkbox.val();
					        var role = $(this).find('.inputRole').val();
					        
					        var relatedPartyData = {
					            relatedPartyCode: relatedPartyCode,
					            role: role
					        };
					
					        listRelatedPartyCode_Role.push(relatedPartyData);
					    }
					});
					data["listRelatedPartyCode_Role"] = listRelatedPartyCode_Role;
					console.log(listRelatedPartyCode_Role)
                    
                    if (id == false)
                        save(data);
                    else
                        edit(data);
                });

                function save(data) {
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
                        	alert("Error")
                        	console.log(error);
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
                        success: function (result) {
                        },
                        error: function (error) {
                            console.log(error)
                        }
                    })
                }

				function checkListSubGenre_Genre(genreCode) {
					console.log("data: ")
                    data = genreCode;
                    $.ajax({
                        url: '${API_SubGenre_URL}',
                        type: 'POST',
                        contentType: 'application/json',
                        data: data,
                        dataType: 'json',
                        success: function (result) {
                            $('input[type=checkbox][name=subGenreCodeList]').parent().hide();
                            $('input[type=checkbox][name=subGenreCodeList]').each(function () {
                                subgenre = $(this).val();
                                element = $(this);         
                                result.forEach(function (obj) {
                                	element.prop('checked', false);
                                    if (subgenre + "" == obj.code + "") {
                                        element.parent().show();
                                    } 
                                                                    
                                })
                            });
                        },
                        error: function (error) {
                            console.log(error)
                        }
                    })
				}
                $('input[type=radio][name=genreCode]').change(function () {
                    console.log("data: ")
                    checkListSubGenre_Genre(this.value)
                });
                
                
                $(".btn_fillInformAPI").click(function (e) {
                	if($("#code").val() != "")
                		window.location.href = '${API_GetInformTMDB}'+"?code="+$("#code").val()+"&id="+$("#id").val();
                })
            </script>
        </div>