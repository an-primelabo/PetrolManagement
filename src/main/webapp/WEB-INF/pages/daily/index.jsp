<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ah.petrolmanagement.enums.Shifts"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="<c:url value='/static/css/plugins/datatables.bootstrap.min.css' />" rel="stylesheet">
<link href="<c:url value='/static/css/pages/daily.css' />" rel="stylesheet">

<div id="daily-content" class="animated fadeIn">
	<div class="col-md-12 padding-0">
		<div class="col-md-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3>Đơn giá xăng dầu (Đơn vị: Đồng)</h3>
				</div>
				<!-- /div.panel-heading -->

				<div class="panel-body">
					<div class="responsive-table">
						<table class="table table-striped margin-0" width="100%" cellspacing="0">
							<thead>
								<tr>
									<th>Tên</th>
									<th>Giá mới nhất</th>
									<th>Ngày cập nhật</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="price" items="${priceList}">
									<tr id="price-${price.id}" data-product-id="${price.productId}" data-price="${price.price}">
										<td>${price.productName}</td>
										<td>
											<fmt:formatNumber value="${price.price}" />
										</td>
										<td>
											<fmt:formatDate pattern="dd/MM/yyyy" value="${price.insTime}" />
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- /table.table.table-striped.margin-0 -->
					</div>
					<!-- /div.responsive-table -->
				</div>
				<!-- /div.panel-body -->
			</div>
			<!-- /div.panel.panel-default -->
		</div>
		<!-- /div.col-md-6 -->

		<div class="col-md-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3>Bảng chức năng</h3>
				</div>
				<!-- /div.panel-heading -->

				<div class="panel-body text-center">
					<div class="form-animate-radio">
						<c:forEach var="shift" items="<%= Shifts.values() %>">
							<label class="radio">
								<input type="radio" id="shift-${shift.code}" name="shift" value="${shift.code}" />
								<span class="outer"><span class="bg-primary inner"></span></span> ${shift.value}
							</label>
						</c:forEach>
					</div>
					<!-- /div.form-animate-radio -->

					<div class="form-element">
						<div class="input-group">
							<span id="daily-search-addon" class="input-group-addon bg-primary"><i class="fa fa-calendar text-white" aria-hidden="true"></i></span>
							<input type="date" id="daily-search" class="form-control" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />" aria-describedby="daily-search-addon" />
						</div>
						<!-- /div.input-group -->
					</div>
					<!-- /div.form-element -->

					<div class="top-20">
						<button type="button" id="btn-daily-insert" class="btn btn-3d btn-primary" data-toggle="modal" data-target="#modal-daily-form">Thêm số liệu</button>
						<button type="button" id="btn-daily-edit" class="btn btn-3d btn-success" data-toggle="modal" data-target="#modal-daily-form" disabled>Thay đổi</button>
						<button type="button" id="btn-daily-check" class="btn btn-3d btn-info hide">Kiểm tiền</button>
						<button type="button" id="btn-daily-chart" class="btn btn-3d btn-danger hide">Thống kê</button>
					</div>
					<!-- /div.top-20 -->
				</div>
				<!-- /div.panel-body.text-center -->
			</div>
			<!-- /div.panel.panel-default -->
		</div>
		<!-- /div.col-md-6 -->
	</div>
	<!-- /div.col-md-12.padding-0 -->

	<div class="col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading" style="position: relative;">
				<h3>Số liệu xăng dầu <span id="daily-display">hôm nay (<fmt:formatDate pattern="dd/MM/yyyy" value="${now}" />)</span></h3>

				<div class="badges-ribbon hide">
					<div class="badges-ribbon-content badge-primary">ĐÃ KIỂM</div>
					<!-- /div.badges-ribbon-content.badge-primary -->
				</div>
				<!-- /div.badges-ribbon.hide -->
			</div>
			<!-- /div.panel-heading -->

			<div class="panel-body">
				<div id="daily-message" class="alert alert-outline alert-dismissible animated hide" role="alert">
					<strong></strong>
				</div>
				<!-- /div#daily-message -->

				<div class="responsive-table">
					<table id="table-daily-content" class="table table-striped table-bordered table-hover" width="100%" cellspacing="0">
						<thead>
							<tr>
								<th>Theo ca</th>
								<th>Trụ</th>
								<th>Chỉ số mới (Trụ)</th>
								<th>Chỉ số cũ (Trụ)</th>
								<th>Bán (Trụ)</th>
								<th>Chỉ số mới (ĐT)</th>
								<th>Chỉ số cũ (ĐT)</th>
								<th>Bán (ĐT)</th>
								<th>Đơn giá</th>
								<th>Thành tiền (Trụ)</th>
								<th>Thành tiền (ĐT)</th>
								<th>Chênh lệch</th>
							</tr>
						</thead>

						<tfoot>
							<tr>
								<th>Theo ca</th>
								<th>Trụ</th>
								<th>Chỉ số mới (Trụ)</th>
								<th>Chỉ số cũ (Trụ)</th>
								<th>Bán (Trụ)</th>
								<th>Chỉ số mới (ĐT)</th>
								<th>Chỉ số cũ (ĐT)</th>
								<th>Bán (ĐT)</th>
								<th>Đơn giá</th>
								<th>Thành tiền (Trụ)</th>
								<th>Thành tiền (ĐT)</th>
								<th>Chênh lệch</th>
							</tr>
						</tfoot>

						<tbody class="animated fadeIn"></tbody>
					</table>
					<!-- /table#table-daily-content -->
				</div>
				<!-- /div.responsive-table -->
			</div>
			<!-- /div.panel-body -->
		</div>
		<!-- /div.panel.panel-default -->
	</div>
	<!-- /div.col-md-12 -->
</div>
<!-- /div#daily-content -->

<jsp:include page="sub/modal.jsp" />

<%-- <jsp:include page="sub/daily-content.jsp" /> --%>

<%-- <jsp:include page="sub/daily-insert.jsp" /> --%>

<%-- <jsp:include page="sub/daily-chart.jsp" /> --%>

<%-- <script src="<c:url value='/static/js/plugins/morris.min.js' />"></script> --%>
<%-- <script src="<c:url value='/static/js/plugins/raphael.min.js' />"></script> --%>
<script src="<c:url value='/static/js/plugins/jquery.datatables.min.js' />"></script>
<script src="<c:url value='/static/js/plugins/datatables.bootstrap.min.js' />"></script>
<script src="<c:url value='/static/js/pages/daily.js' />"></script>
<script type="text/javascript">
var REQUEST_DAILY_SELECT = '${home}/daily/select';
var REQUEST_DAILY_ACTION = '${home}/daily/action';
var FORM_INPUT = {};
var FORM_CHECK = null;
var DAILY_ROW_SELECTED = -1;
var DAILY_DATA = null;
// var REQUEST_CHART = '${home}/daily/chart';
// var REQUEST_INSERT_PRICE = '${home}/daily/insert/price';
// var REQUEST_INSERT_DAILY = '${home}/daily/insert/daily';
</script>