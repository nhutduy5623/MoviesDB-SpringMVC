<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<body>

	<!--login form popup-->
	<div class="login-wrapper" id="login-content">
		<div class="login-content">
			<a href="#" class="close">x</a>
			<h3>Login</h3>
			<c:if test="${param.incorrectAccount != null}">
				<div class="alert alert-danger">Username or password incorrect
				</div>
			</c:if>
			<c:if test="${param.sessionTimeout != null}">
				<div class="alert alert-danger">Session Timeout</div>
			</c:if>
			<form method="post"
				action="<c:url value='/j_springSecurity_checkLogin'/>">
				<div class="row">
					<label for="username"> Username: <input type="text"
						name="j_username" id="username" placeholder="Hugh Jackman"
						pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{8,20}$" required="required" />
					</label>
				</div>

				<div class="row">
					<label for="password"> Password: <input type="password"
						name="j_password" id="password" placeholder="******"
						required="required" />
					</label>
				</div>
				<div class="row">
					<div class="remember">
						<div>
							<input type="checkbox" name="remember" value="Remember me"><span>Remember
								me</span>
						</div>
						<a href="#">Forget password ?</a>
					</div>
				</div>
				<div class="row">
					<button type="submit">Login</button>
				</div>
			</form>
			<div class="row">
				<p>Or via social</p>
				<div class="social-btn-2">
					<a class="fb" href="#"><i class="ion-social-facebook"></i>Facebook</a>
					<a class="tw" href="#"><i class="ion-social-twitter"></i>twitter</a>
				</div>
			</div>
		</div>
	</div>
	<!--end of login form popup-->
	<!--signup form popup-->
	<div class="login-wrapper" id="signup-content">
		<div class="login-content">
			<a href="#" class="close">x</a>
			<h3>sign up</h3>
			<form method="post" action="<c:url value='/register'/>"
				id="form-signup">
				<div class="row">
					<label for="username-2"> Fullname: <input type="text"
						name="fullName" id="fullname-2"
						pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{8,20}$" required="required" />
					</label>
				</div>
				<div class="row">
					<label for="email-2"> your email: <input type="email"
						name="email" id="email-2" required="required"
						class="border border-warning" /> <small id="error-message-email-2"
						style="color: red; display: none;">* Email used by another
							account !</small>
					</label>
				</div>
				<div class="row">
					<label for="password-2"> Password: <input type="password"
						name="passWord" id="password-2" required="required" /> 
						<small
						id="error-message-password-2" style="color: red; display: none;">*
							Password minimum 6 character !</small>
					</label>
				</div>
				<div class="row">
					<label for="repassword-2"> re-type Password: <input
						type="password" name="reTypePassword" id="repassword-2"
						required="required" /> <small id="error-message-repassword-2"
						style="color: red; display: none;">* Retype password isn't
							match password !</small>
					</label>
				</div>
				<div class="row">
					<button id="btn-submit-signup" type="submit">sign up</button>
				</div>
			</form>
		</div>
	</div>
	<!--end of signup form popup-->
	<!-- 	<img alt="" src="/MoviesDB_springMVC/template/web/images/fancybox_loading.gif"> -->
</body>