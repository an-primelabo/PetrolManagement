<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="<c:url value='/static/css/plugins/datatables.bootstrap.min.css' />" rel="stylesheet">
<link href="<c:url value='/static/css/pages/product.css' />" rel="stylesheet">

<div id="product-content" class="animated fadeIn">
	<div class="col-md-12 padding-0">
		<div class="col-md-5">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3>Quản lý hàng hóa</h3>
				</div>
				<!-- /div.panel-heading -->

				<div class="panel-body">
					<div id="category-message" class="alert alert-outline alert-dismissible animated hide" role="alert">
						<strong></strong>
					</div>
					<!-- /div#category-message -->

					<div class="responsive-table">
						<table id="table-category-content" class="table table-striped table-bordered table-hover" width="100%" cellspacing="0">
							<thead>
								<tr>
									<th></th>
									<th>Tên hàng hóa</th>
									<th>Ngày tạo</th>
								</tr>
							</thead>

							<tfoot>
								<tr>
									<th></th>
									<th>Tên hàng hóa</th>
									<th>Ngày tạo</th>
								</tr>
							</tfoot>

							<tbody class="animated fadeIn"></tbody>
						</table>
						<!-- /table#table-category-content -->
					</div>
					<!-- /div.responsive-table -->

					<div class="top-20">
						<button type="button" id="btn-category-insert" class="btn btn-3d btn-primary" data-toggle="modal" data-target="#modal-category-form">Thêm hàng hóa</button>
						<button type="button" id="btn-category-edit" class="btn btn-3d btn-success" data-toggle="modal" data-target="#modal-category-form" disabled>Thay đổi</button>
						<button type="button" id="btn-category-delete" class="btn btn-3d btn-danger" data-toggle="modal" data-target="#modal-confirm-delete" disabled>Xóa</button>
					</div>
					<!-- /div.top-20 -->
				</div>
				<!-- /div.panel-body -->
			</div>
			<!-- /div.panel.panel-default -->
		</div>
		<!-- /div.col-md-5 -->

		<div class="col-md-7">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3>Quản lý hạng mục</h3>
				</div>
				<!-- /div.panel-heading -->

				<div class="panel-body">
					<div id="product-message" class="alert alert-outline alert-dismissible animated hide" role="alert">
						<strong></strong>
					</div>
					<!-- /div#product-message -->

					<div class="responsive-table">
						<table id="table-product-content" class="table table-striped table-bordered table-hover" width="100%" cellspacing="0">
							<thead>
								<tr>
									<th></th>
									<th>Tên hạng mục</th>
									<th>Ngày tạo</th>
									<th>Giá mới nhất</th>
									<th>Ngày cập nhật giá</th>
								</tr>
							</thead>

							<tfoot>
								<tr>
									<th></th>
									<th>Tên hạng mục</th>
									<th>Ngày tạo</th>
									<th>Giá mới nhất</th>
									<th>Ngày cập nhật giá</th>
								</tr>
							</tfoot>

							<tbody class="animated fadeIn"></tbody>
						</table>
						<!-- /table#table-product-content -->
					</div>
					<!-- /div.responsive-table -->

					<div class="top-20">
						<button type="button" id="btn-product-insert" class="btn btn-3d btn-primary" data-toggle="modal" data-target="#modal-product-form" disabled>Thêm hạng mục</button>
						<button type="button" id="btn-product-edit" class="btn btn-3d btn-success" data-toggle="modal" data-target="#modal-product-form" disabled>Thay đổi</button>
						<button type="button" id="btn-product-delete" class="btn btn-3d btn-danger" data-toggle="modal" data-target="#modal-confirm-delete" disabled>Xóa</button>
					</div>
					<!-- /div.top-20 -->
				</div>
				<!-- /div.panel-body -->
			</div>
			<!-- /div.panel.panel-default -->
		</div>
		<!-- /div.col-md-7 -->
	</div>
	<!-- /div.col-md-12.padding-0 -->
</div>
<!-- /div#product-content -->

<jsp:include page="sub/modal.jsp" />

<script src="<c:url value='/static/js/plugins/jquery.datatables.min.js' />"></script>
<script src="<c:url value='/static/js/plugins/datatables.bootstrap.min.js' />"></script>
<script src="<c:url value='/static/js/pages/product.js' />"></script>
<script type="text/javascript">
var REQUEST_CATEGORY_SELECT = '${home}/product/category/select';
var REQUEST_CATEGORY_ACTION = '${home}/product/category/action';
var REQUEST_PRODUCT_SELECT = '${home}/product/select';
var REQUEST_PRODUCT_ACTION = '${home}/product/action';
var TABLE_WORKING = '';
var CATEGORY_ROW_SELECTED = -1;
var PRODUCT_ROW_SELECTED = -1;
</script>