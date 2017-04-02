<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<title>Đăng Nhập</title>

	<!-- Bootstrap Core CSS -->
	<link rel="stylesheet" type="text/css" href="<c:url value='/static/css/bootstrap.min.css' />">
	<!-- Plugins -->
	<link rel="stylesheet" type="text/css" href="<c:url value='/static/css/plugins/font-awesome.min.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='/static/css/plugins/animate.min.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='/static/css/plugins/icheck/skins/flat/aero.css' />">
	<!-- Custom CSS -->
	<link rel="stylesheet" href="<c:url value='/static/css/style.css' />">
	<link rel="shortcut icon" href="<c:url value='/static/img/logomi.png' />">
	<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

	<!-- jQuery -->
	<script src="<c:url value='/static/js/jquery.min.js' />"></script>
	<!-- jQuery UI JavaScript -->
	<script src="<c:url value='/static/js/jquery.ui.min.js' />"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value='/static/js/bootstrap.min.js' />"></script>
</head>

<body id="petrol">
	<c:set var="home" value="${pageContext.request.contextPath}" scope="session" />
	<c:set var="now" value="<%= new java.util.Date() %>" scope="page" />
	<spring:htmlEscape defaultHtmlEscape="true" />

	<div class="container">
		<form class="form-signin" action="${home}/login" method="post" autocomplete="off">
			<div class="panel">
				<div class="panel-body text-center">
					<h1 class="atomic-symbol">PM</h1>
					<p class="atomic-mass">
						<fmt:formatDate pattern="dd/MM/yyyy" value="${now}" />
					</p>
					<p class="element-name">Quản Lý Trạm Xăng</p>
					<i class="fa fa-angle-down" aria-hidden="true"></i>

					<div class="form-group form-animate-text">
						<input type="text" name="username" class="form-text" value="admin" required />
						<span class="bar"></span>
						<label>Tài khoản</label>
					</div>
					<!-- /div.form-group.form-animate-text -->

					<div class="form-group form-animate-text">
						<input type="password" name="password" class="form-text" value="Abc12345" required />
						<span class="bar"></span>
						<label>Mật khẩu</label>
					</div>
					<!-- /div.form-group.form-animate-text -->

					<label class="pull-left">
						<input type="checkbox" name="remember" class="icheck pull-left" /> Nhớ đăng nhập
					</label>

					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<button type="submit" class="btn col-md-12">Đăng Nhập</button>
				</div>
				<!-- /div.panel-body.text-center -->
			</div>
			<!-- /div.panel -->
		</form>
		<!-- /form.form-signin -->
	</div>
	<!-- /div.container -->

	<!-- Plugins -->
	<script src="<c:url value='/static/js/plugins/icheck.min.js' />"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('.form-signin input').iCheck({
				checkboxClass: 'icheckbox_flat-aero'
			});
		});
	</script>
</body>

</html>