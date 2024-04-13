<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.laptrinhweb.util.SecurityUtil"%>
<style>
.sticky-container {
	position: sticky;
	top: 80px;
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

.user-img a img {
	border-radius: 50%;
	width: 150px;
	height: 150px;
}

.form-it input:disabled {
	background: #233a50;
}

.error-message-password {
	color: red;
	display: none;
}

a.disabled {
	background-color: gray;
	cursor: not-allowed; 
	pointer-events: none;
}
</style>


<div class="hero user-hero">
	<div class="container">
		<div class="col-md-3 col-sm-12 col-xs-12"></div>
		<div class="col-md-9 col-sm-12 col-xs-12">
			<div class="hero-ct">
				<h1 id="name_111">${model.fullName} profile</h1>
				<ul class="breadcumb">
					<li class="active"><a href="<c:url value='/'/>">Home</a></li>
					<li><span class="ion-ios-arrow-right"></span>Profile</li>
				</ul>
			</div>
		</div>
	</div>
</div>

<div class="page-single" style="padding: 0;">
	<div class="container">
		<div class="row ipad-width" style="display: flex;">
			<div class="col-md-3 col-sm-12 col-xs-12">
				<div class="user-information sticky-container">
					<div class="user-img">
						<a href="#" id="btn-change-avatar-profile"> <img
							id="img-avatar-profile"
							src="<c:choose><c:when test="${model.avatar != null}"><c:url value='${model.avatar}'/></c:when><c:otherwise><c:url value='template/web/images/uploads/avatar-default.jpg'/></c:otherwise></c:choose>">
							<br></a> <a href="#" id="btn-save-avatar-profile" class="redbtn disabled">Save
							avatar</a>
						<form action="<c:url value='change-avatar'/>"
							id="form-change-avatar-profile">
							<input style="display: none;" type="file" name="avatar"
								id="input-change-avatar-profile" accept="image/*">
						</form>
					</div>
					<div class="user-fav">
						<p>Account Details</p>
						<ul>
							<li class="active"><a href="<c:url value='/profile'/>">Profile</a></li>
							<li><a href="<c:url value='/userfavorite'/>">Favorite movies</a></li>
							<li><a href="userrate.html">Rated movies</a></li>
						</ul>
					</div>
					<div class="user-fav">
						<p>Others</p>	
						<ul>
							<li><a href="#" id="btn-change-password">Change password</a></li>
							<li><a href="<c:url value='/logout'/>">Log out</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-md-9 col-sm-12 col-xs-12">
				<div class="form-style-1 user-pro">
					<form action="<c:url value='change-info'/>" class="user"
						id="form-change-info-profile">
						<h4>1. Profile details</h4>
						<div class="row">
							<div class="col-md-6 form-it">
								<label>Fullname</label> <input name="fullName"
									value="${model.fullName}" type="text"
									id="profile-input-fullname" required>
							</div>
							<div class="col-md-6 form-it">
								<label>Email Address</label><input value="${model.email}"
									type="text" disabled="disabled">
							</div>
						</div>

						<div class="row">
							<div class="col-md-2">
								<input id="profile-btn-change-info" class="submit" type="submit"
									value="save">
							</div>
						</div>
					</form>
					<form action="<c:url value='change-password'/>" class="password"
						id="form-change-password-profile">
						<h4>2. Change password</h4>
						<div class="row">
							<div class="col-md-6 form-it">
								<label>Old Password</label> <input type="password"
									id="profile-old-password" name="oldPassword"
									placeholder="**********" required> <small
									id="profile-error-message-old-password"
									class="error-message-password">* Old password not match
									!</small>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 form-it">
								<label>New Password</label> <input type="password"
									id="profile-password" name="passWord"
									placeholder="***************" required> <small
									id="profile-error-message-password"
									class="error-message-password">* Password minimum 6
									character !</small>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 form-it">
								<label>Confirm New Password</label> <input type="password"
									id="profile-repassword" name="reTypePassword"
									placeholder="*************** " required> <small
									id="profile-error-message-repassword"
									class="error-message-password">* Retype password isn't
									match password !</small>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								<input id="profile-btn-change-password" class="submit"
									type="submit" value="change">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script>

	function changePassword(){
		// Change password
		const formChangePassword = document.getElementById("form-change-password-profile");
		formChangePassword.addEventListener("submit", function(event) {
			event.preventDefault();
			const url = formChangePassword.getAttribute("action");
			var formData = new FormData(formChangePassword);
			var jsonData = {};
			formData.forEach(function(value, key) {
				jsonData[key] = value;
			});

			const btnSubmitChangePass = document.getElementById('profile-btn-change-password');
			const inputOldPassword = document.getElementById('profile-old-password');
			const inputPassword = document.getElementById('profile-password');
			const inputRePassword = document.getElementById('profile-repassword');
			btnSubmitChangePass.value = '•••';
			fetch(url, {
				method: 'POST',
				headers: {
					"Content-Type": 'application/json',
				},
				body: JSON.stringify(jsonData),
			})
				.then(response => {
					btnSubmitChangePass.value = 'change';
					if (response.ok) {
						return response.json();
					}
				})
				.then(data => {
					// Mật khẩu cũng không khớp
					if (data.notMatchOldPassword) {
						showInputToError('profile-error-message-old-password', inputOldPassword);
					} else {
						hideInputToError('profile-error-message-old-password', inputOldPassword);
					}
					// Mật khẩu không khớp
					if (data.notMatchPassword) {
						showInputToError('profile-error-message-repassword', inputRePassword);
					} else {
						hideInputToError('profile-error-message-repassword', inputRePassword);
					}
					// Password quá ngắn
					if (data.passwordVeryShort) {
						showInputToError('profile-error-message-password', inputPassword);
					} else {
						hideInputToError('profile-error-message-password', inputPassword);
					}
					// Thành công
					if (data.success) {
						// Làm rỗng các input
						document.querySelectorAll('#form-change-password-profile input').forEach(input => {
							if (input.type === 'password') {
								input.value = "";
							}
						});
						// Đổi màu button
						var oldBackgroundColor = btnSubmitChangePass.style.background;
						btnSubmitChangePass.style.background = 'green';
						btnSubmitChangePass.style.pointerEvents = 'none';
						btnSubmitChangePass.value = '✔';
						// Đổi button về như cũ
						setTimeout(function() {
							btnSubmitChangePass.value = 'change';
							btnSubmitChangePass.style.background = oldBackgroundColor;
							btnSubmitChangePass.style.removeProperty('pointer-events');
						}, 5000);
					}
				})
				.catch(error => {
					btnSubmitChangePass.value = 'change';
					console.error('Error:', error);
				});
		});
	}
	
	function showInputToError(idElmErrorMessage, inputElement) {
		inputElement.style.borderColor = 'red';
		document.getElementById(idElmErrorMessage).style.display = 'unset';
	}

	function hideInputToError(idElmErrorMessage, inputElement) {
		inputElement.style.removeProperty('border-color');
		document.getElementById(idElmErrorMessage).style.display = 'none';
	}
	
	function changeInfo(){
		var form = document.getElementById("form-change-info-profile");
		form.addEventListener("submit", function(event) {
			event.preventDefault();
			var url = form.getAttribute("action");
			var formData = new FormData(form);
			var jsonData = {};
			formData.forEach(function(value, key) {
				jsonData[key] = value;
			});

			var btnSubmit = document.getElementById('profile-btn-change-info');
			btnSubmit.value = '•••';
			fetch(url, {
				method: 'POST',
				headers: {
					"Content-Type": 'application/json',
				},
				body: JSON.stringify(jsonData),
			})
				.then(response => {
					btnSubmit.value = 'save';
					if (response.ok) {
						return response.json();
					}
				})
				.then(data => {
					// Thành công
					if (data.success) {
						// Đổi màu button
						var oldBackgroundColor = btnSubmit.style.background;
						btnSubmit.style.background = 'green';
						btnSubmit.style.pointerEvents = 'none';
						btnSubmit.value = '✔';
						// Đổi button về như cũ
						setTimeout(function() {
							btnSubmit.value = 'save';
							btnSubmit.style.background = oldBackgroundColor;
							btnSubmit.style.removeProperty('pointer-events');
							location.reload();
						}, 2000);
					}
				})
				.catch(error => {
					btnSubmit.value = 'save';
					console.error('Error:', error);
				});
		});
	}
	function changeAvatar(){
		var btnChangeAvatar = document.getElementById('btn-change-avatar-profile');
		var inputAvatar = document.getElementById('input-change-avatar-profile');
		var btnSubmit = document.getElementById('btn-save-avatar-profile');
		var formChangeAvatar = document.getElementById("form-change-avatar-profile");
		var imageBase64 = '';
		btnChangeAvatar.addEventListener("click", function(event) {
			event.preventDefault();
			inputAvatar.click();
		});
		inputAvatar.addEventListener('change', function(event) {
		    var selectedFile = event.target.files[0];
		    if (selectedFile) {
		    	btnSubmit.classList.remove("disabled");
		    	var reader = new FileReader();
		        reader.onload = function(event) {
		            var imageUrl = event.target.result;
		            document.getElementById('img-avatar-profile').src = imageUrl;
		            imageBase64 = reader.result.split(',')[1];
		        };
		        reader.readAsDataURL(selectedFile);
		    }
		});
		btnSubmit.addEventListener("click", function(event) {
			event.preventDefault();
			var url = formChangeAvatar.getAttribute("action");
	
			btnSubmit.value = '•••';
			fetch(url, {
				method: 'POST',
				headers: {
					"Content-Type": 'application/json',
				},
				body: JSON.stringify(imageBase64),
			})
				.then(response => {
					btnSubmit.innerHTML = 'save avatar';
					if (response.ok) {	
						return response.json();
					}
				})
				.then(data => {
					// Thành công
					if (data.success) {
						// Đổi màu button
						var oldBackgroundColor = btnSubmit.style.background;
						btnSubmit.style.background = 'green';
						btnSubmit.style.pointerEvents = 'none';
						btnSubmit.innerHTML = '✔';
						// Đổi button về như cũ
						setTimeout(function() {
							btnSubmit.innerHTML = 'save avatar';
							btnSubmit.style.background = oldBackgroundColor;
							btnSubmit.classList.add("disabled");
						}, 5000);
					}
				})
				.catch(error => {
					btnSubmit.innerHTML = 'save avatar';
					console.error('Error:', error);
				});
		});
	}
	
	
	changePassword();
	changeInfo();
	changeAvatar();
</script>