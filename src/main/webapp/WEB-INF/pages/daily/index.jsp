<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="<c:url value='/static/css/pages/daily.css' />" rel="stylesheet">

<jsp:include page="sub/price-content.jsp" />

<jsp:include page="sub/daily-controls.jsp" />

<jsp:include page="sub/daily-content.jsp" />

<jsp:include page="sub/daily-insert.jsp" />

<jsp:include page="sub/daily-chart.jsp" />

<script src="<c:url value='/static/js/plugins/morris.min.js' />"></script>
<script src="<c:url value='/static/js/plugins/raphael.min.js' />"></script>
<script src="<c:url value='/static/js/pages/daily.js' />"></script>
<script type="text/javascript">
var REQUEST_SEARCH = '${home}/daily/search';
var REQUEST_CHART = '${home}/daily/chart';
var REQUEST_INSERT_PRICE = '${home}/daily/insert/price';
var REQUEST_INSERT_DAILY = '${home}/daily/insert/daily';
</script>