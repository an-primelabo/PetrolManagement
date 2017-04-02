<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="<c:url value='/static/css/plugins/datatables.bootstrap.min.css' />" rel="stylesheet">

<div id="product-content" class="col-md-12 padding-0 animated fadeIn">
	<div class="col-md-12">
		<div class="col-md-6">
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
		<!-- /div.col-md-6 -->

		<div class="col-md-6">
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
									<th>Tên hàng hóa</th>
									<th>Tên hạng mục</th>
									<th>Ngày tạo</th>
								</tr>
							</thead>

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
		<!-- /div.col-md-6 -->
	</div>
	<!-- /div.col-md-12 -->
</div>
<!-- /div#product-content -->

<script src="<c:url value='/static/js/plugins/jquery.datatables.min.js' />"></script>
<script src="<c:url value='/static/js/plugins/datatables.bootstrap.min.js' />"></script>
<script type="text/javascript">
$(document).ready(function() {
	var tableCategory = $('#product-content #table-category-content').DataTable({
		columnDefs: [{
			targets: 0,
			className: 'text-center',
			searchable: false,
			orderable: false,
			orderData: 0,
			render: function(data, type, row, meta) {
				var html = '<input type="hidden" id="category-id-' + row.id + '" value="' + row.id + '" />';
				return html + (meta.row + 1);
			}
		}, {
			targets: 1,
			render: function(data, type, row, meta) {
				var html = '<input type="hidden" id="category-name-' + row.id + '" value="' + row.categoryName + '" />';
				return html + row.categoryName;
			}
		}, {
			targets: 2,
			data: 'insTime',
			render: function(data) {
				return formatDate(data);
			}
		}],
		language: {
			infoFiltered: '(Đã lọc từ _MAX_ hàng hóa)',
			zeroRecords: 'Không tìm thấy hàng hóa phù hợp'
		}
	});
	var tableProduct = $('#product-content #table-product-content').DataTable({
		columnDefs: [{
			targets: 0,
			className: 'text-center',
			searchable: false,
			orderable: false,
			orderData: 0,
			render: function(data, type, row, meta) {
				var html = '<input type="hidden" id="product-id-' + row.id + '" value="' + row.id + '" />';
				return html + (meta.row + 1);
			}
		}, {
			targets: 1,
			render: function() {
				return CATEGORY_NAME_SELECT;
			}
		}, {
			targets: 2,
			render: function(data, type, row, meta) {
				var html = '<input type="hidden" id="product-name-' + row.id + '" value="' + row.productName + '" />';
				return html + row.productName;
			}
		}, {
			targets: 3,
			data: 'insTime',
			render: function(data) {
				return formatDate(data);
			}
		}],
		language: {
			infoFiltered: '(Đã lọc từ _MAX_ hạng mục)',
			zeroRecords: 'Không tìm thấy hạng mục phù hợp'
		}
	});
	showTableContent(tableCategory);

	$('div#table-product-content_paginate').hide();
	$('#product-content #table-category-content tbody').on('click', 'tr', function() {
		var disabled = true;

		if ($(this).find('td').attr('colspan')) {
			return false;
		}
		if ($(this).hasClass('bg-light-green')) {
			$(this).removeClass('bg-light-green text-white');

			tableProduct.clear().draw();
			$('div#table-product-content_paginate').hide();
		} else {
			tableCategory.$('tr.bg-light-green').removeClass('bg-light-green text-white');
			$(this).addClass('bg-light-green text-white');

			CATEGORY_ID_SELECT = tableCategory.$('tr.bg-light-green').find('input:hidden[id^=category-id-]').val();
			CATEGORY_NAME_SELECT = tableCategory.$('tr.bg-light-green').find('input:hidden[id^=category-name-]').val();
			disabled = false;

			showTableContent(tableProduct, { categoryId: CATEGORY_ID_SELECT });
		}
		disabledButtons('btn-category-edit, btn-category-delete, btn-product-insert', disabled);
		disabledButtons('btn-product-edit, btn-product-delete', true);
	});
	$('#product-content #table-product-content tbody').on('click', 'tr', function() {
		var disabled = true;

		if ($(this).find('td').attr('colspan')) {
			return false;
		}
		if ($(this).hasClass('bg-light-green')) {
			$(this).removeClass('bg-light-green text-white');

// 			tableProduct.clear().draw();
// 			$('div#table-product-content_paginate').hide();
		} else {
			tableProduct.$('tr.bg-light-green').removeClass('bg-light-green text-white');
			$(this).addClass('bg-light-green text-white');

			PRODUCT_ID_SELECT = tableProduct.$('tr.bg-light-green').find('input:hidden[id^=product-id-]').val();
			PRODUCT_NAME_SELECT = tableProduct.$('tr.bg-light-green').find('input:hidden[id^=product-name-]').val();
			disabled = false;

// 			showProductContent(tableProduct, { categoryId: PRODUCT_ID_SELECT });
		}
		disabledButtons('btn-product-edit, btn-product-delete', disabled);
	});
	$('#modal-category-form, #modal-product-form').on('show.bs.modal', function(event) {
		var modal = $(this);
		var button = $(event.relatedTarget);
		var buttonId = button.attr('id');
		var buttonAction = 'btn-category-action';
		var headerClass = 'success';
		var titleName = 'Thay đổi';
		var type = ' hàng hóa';

		TABLE_WORKING = button.parent().closest('div.panel-body').find('table').attr('id');

		switch (TABLE_WORKING) {
		case 'table-category-content':
			modal.find('#category-name').val(CATEGORY_NAME_SELECT);
			break;
		case 'table-product-content':
			buttonAction = 'btn-product-action';
			type = ' hạng mục';
			var select = modal.find('#category-select');

			$.each(CATEGORY_DATA, function(idx, category) {
				var option = $('<option/>', {
					value: category.id,
					text: category.categoryName
				});
				if (category.id == CATEGORY_ID_SELECT) {
					option.attr({
						selected: true
					});
				}
				select.append(option);
			});
			if (modal.find('#category-select').hasClass('hide')) {
				modal.find('#category-select-show, #category-select').toggleClass('hide');
			}
			modal.find('#category-select-show').html(CATEGORY_NAME_SELECT);
			modal.find('#product-name').val(PRODUCT_NAME_SELECT);
			break;
		}
		if (buttonId.endsWith('-insert')) {
			headerClass = 'primary';
			titleName = 'Thêm mới';

			if (!modal.find('#category-select').hasClass('hide')) {
				modal.find('#category-select-show, #category-select').toggleClass('hide');
			}
			modal.find('.form-horizontal')[0].reset();
		}
		modal.find('.modal-header').changeHeaderType({
			type: headerClass,
			text: titleName + type
		});
		modal.find('#' + buttonAction).changeButtonType({
			type: headerClass,
			text: titleName
		});

		addValidateForm(modal.find('.form-horizontal'));
	});
	$('#modal-confirm-delete').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);

		TABLE_WORKING = button.parent().closest('div.panel-body').find('table').attr('id');
	});
	$('#modal-category-form #btn-category-action, #modal-product-form #btn-product-action').on('click', function() {
		var form = $(this).parent().closest('div.modal');
		var buttonId = $(this).attr('id');
		var url = REQUEST_CATEGORY_ACTION;
		var message = 'category-message';
		var msgClass = 'success';
		var msg = 'Thay đổi thành công !';
		var mode = 'update';
		var data = {
			id: CATEGORY_ID_SELECT
		};

		switch (TABLE_WORKING) {
		case 'table-category-content':
			data['categoryName'] = $('#modal-category-form #category-name').val();
			break;
		case 'table-product-content':
			url = REQUEST_PRODUCT_ACTION;
			message = 'product-message';
			var select = $('#modal-product-form #category-select');

			data['id'] = PRODUCT_ID_SELECT;
			data['categoryId'] = select.hasClass('hide') ? CATEGORY_ID_SELECT : select.val();
			data['productName'] = $('#modal-product-form #product-name').val();
			break;
		}
		if ($(this).hasClass('btn-primary')) {
			msgClass = 'primary';
			msg = 'Thêm mới thành công !';
			mode = 'insert';
			data['id'] = null;
		}
		data['mode'] = mode;

		callAjax(url, data, function(json) {
			if (json.status == 0) {
				switch (TABLE_WORKING) {
				case 'table-category-content':
					showTableContent(tableCategory);
					break;
				case 'table-product-content':
					showTableContent(tableProduct, { categoryId: CATEGORY_ID_SELECT });
					break;
				}
			} else {
				msgClass = 'danger';
				msg = '';

				$.each(json.error, function(idx, err) {
					msg += err + '<br/>';
				});
			}
			form.modal('hide');

			$('#product-content #' + message).removeClass('hide').fadeIn().delay(3000).fadeOut().changeMessageType({
				type: msgClass,
				text: msg
			});

			switch (TABLE_WORKING) {
			case 'table-category-content':
				tableProduct.clear().draw();
				$('div#table-product-content_paginate').hide();
	
				disabledButtons('btn-category-edit, btn-category-delete, btn-product-insert, btn-product-edit, btn-product-delete', true);
				break;
			case 'table-product-content':
				disabledButtons('btn-product-edit, btn-product-delete', true);
				break;
			}
		});
	});
	$('#modal-confirm-delete #btn-delete').on('click', function() {
		var form = $(this).parent().closest('div.modal');
		var url = REQUEST_CATEGORY_ACTION;
		var message = 'category-message';
		var msgClass = 'warning';
		var msg = 'Xóa thành công !';
		var data = {
			id: CATEGORY_ID_SELECT,
			mode: 'delete'
		};

		switch (TABLE_WORKING) {
		case 'table-product-content':
			url = REQUEST_PRODUCT_ACTION;
			message = 'product-message';
			data['id'] = PRODUCT_ID_SELECT;
			break;
		}
		callAjax(url, data, function(json) {
			if (json.status == 0) {
				switch (TABLE_WORKING) {
				case 'table-category-content':
					showTableContent(tableCategory);
					break;
				case 'table-product-content':
					showTableContent(tableProduct, { categoryId: CATEGORY_ID_SELECT });
					break;
				}
			} else {
				msgClass = 'danger';
				msg = '';

				$.each(json.error, function(idx, err) {
					msg += err + '<br/>';
				});
			}
			form.modal('hide');

			$('#product-content #' + message).removeClass('hide').fadeIn().delay(3000).fadeOut().changeMessageType({
				type: msgClass,
				text: msg
			});

			switch (TABLE_WORKING) {
			case 'table-category-content':
				tableProduct.clear().draw();
				$('div#table-product-content_paginate').hide();

				disabledButtons('btn-category-edit, btn-category-delete, btn-product-insert, btn-product-edit, btn-product-delete', true);
				break;
			case 'table-product-content':
				disabledButtons('btn-product-edit, btn-product-delete', true);
				break;
			}
		});
	});
});
var REQUEST_CATEGORY_SELECT = '${home}/product/category/select';
var REQUEST_CATEGORY_ACTION = '${home}/product/category/action';
var REQUEST_PRODUCT_SELECT = '${home}/product/select';
var REQUEST_PRODUCT_ACTION = '${home}/product/action';
var TABLE_WORKING = '';
var CATEGORY_DATA = '';
var CATEGORY_ID_SELECT = '';
var CATEGORY_NAME_SELECT = '';
var PRODUCT_DATA = '';
var PRODUCT_ID_SELECT = '';
var PRODUCT_NAME_SELECT = '';

var showTableContent = function(table, data) {
	var url = REQUEST_CATEGORY_SELECT;
	var paginate = 'div#' + table.table().node().id + '_paginate';
	data = (data == undefined) ? {} : data;

	switch (table.table().node().id) {
	case 'table-product-content':
		url = REQUEST_PRODUCT_SELECT;
		break;
	}
	callAjax(url, data, function(json) {
		table.clear();
		table.rows.add(json);
		table.draw();

		switch (table.table().node().id) {
		case 'table-category-content':
			CATEGORY_DATA = json;
			break;
// 		case 'table-product-content':
// 			url = REQUEST_PRODUCT_SELECT;
// 			data = (data == 'undefined') ? { categoryId: '0' } : data;
// 			break;
		}
	});
	if (table.data().count() <= 10) {
		$(paginate).hide();
	} else {
		$(paginate).show();
	}
};
var disabledButtons = function(buttons, flag) {
	buttons = buttons.split(', ');

	$.each(buttons, function(idx, button) {
		$('#product-content #' + button).attr({
			disabled: flag
		});
	});
};
</script>