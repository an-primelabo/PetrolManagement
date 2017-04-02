<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<title>403 | Từ Chối Truy Cập</title>

	<!-- Bootstrap Core CSS -->
	<link rel="stylesheet" type="text/css" href="<c:url value='/static/css/bootstrap.min.css' />">
	<!-- Plugins -->
	<link rel="stylesheet" type="text/css" href="<c:url value='/static/css/plugins/animate.min.css' />">
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
	<c:set var="now" value="<%= new java.util.Date() %>" scope="page" />

	<div class="container">
		<div class="form-signin page-404 center-block">
			<div class="panel">
				<div class="panel-body text-center">
					<h1 class="atomic-symbol">403</h1>
					<p class="atomic-mass">
						<fmt:formatDate pattern="dd/MM/yyyy" value="${now}" />
					</p>
					<p class="element-name">Truy cập bị từ chối</p>
					<p class="element-name">Bạn không có quyền truy cập vào trang này</p>
					<i class="icons icon-arrow-down" aria-hidden="true"></i>
					<a href="${home}/">Quay lại</a>
				</div>
				<!-- /div.panel-body.text-center -->
			</div>
			<!-- /div.panel.center-block -->
		</div>
		<!-- /div.form-signin.page-404.center-block -->
	</div>
	<!-- /div.container -->
</body>

</html>