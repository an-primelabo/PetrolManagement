<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta name="_csrf" content="${_csrf.token}">
	<meta name="_csrf_header" content="${_csrf.headerName}">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<title>
		<tiles:getAsString name="title" />
	</title>

	<!-- Bootstrap Core CSS -->
	<link rel="stylesheet" type="text/css" href="<c:url value='/static/css/bootstrap.min.css' />">
	<!-- Plugins -->
	<link rel="stylesheet" type="text/css" href="<c:url value='/static/css/plugins/font-awesome.min.css' />">
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
	<c:set var="loggedInUser" value="${loggedInUser}" scope="session" />
	<c:set var="now" value="<%= new java.util.Date() %>" scope="session" />
	<spring:htmlEscape defaultHtmlEscape="true" />

	<tiles:insertAttribute name="header" />

	<div class="petrol-wrapper container-fluid">
		<tiles:insertAttribute name="left-menu" />

		<div id="content">
			<tiles:insertAttribute name="content-header" />
			<tiles:insertAttribute name="body" />
		</div>
		<!-- /div#content -->

		<tiles:insertAttribute name="right-menu" />
	</div>
	<!-- /div.petrol-wrapper -->

	<tiles:insertAttribute name="mobile" />

	<tiles:insertAttribute name="modal" />

	<!-- Plugins -->
	<script src="<c:url value='/static/js/plugins/moment-with-locales.min.js' />"></script>
	<script src="<c:url value='/static/js/plugins/jquery.nicescroll.js' />"></script>
	<script src="<c:url value='/static/js/plugins/jquery.validate.min.js' />"></script>
	<!-- Numeral JavaScript -->
	<script src="<c:url value='/static/js/numeral.min.js' />"></script>
	<!-- Custom JavaScript -->
	<script src="<c:url value='/static/js/main.js' />"></script>
	<script src="<c:url value='/static/js/base.js' />"></script>
</body>

</html>