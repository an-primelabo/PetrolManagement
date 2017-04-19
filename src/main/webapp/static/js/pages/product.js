$(document).ready(function() {
	var tableCategory = $('#table-category-content').DataTable({
		rowCallback: function(row, data, index) {
			$('td:eq(0)', row).html(index + 1);
			return row;
		},
		columnDefs: [{
			targets: 0,
			className: 'text-center',
			data: null,
			orderable: false,
			searchable: false,
			orderData: 0
		}, {
			targets: 1,
			data: 'categoryName'
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
	var tableProduct = $('#table-product-content').DataTable({
		rowCallback: function(row, data, index) {
			$('td:eq(0)', row).html(index + 1);
			return row;
		},
		columnDefs: [{
			targets: 0,
			className: 'text-center',
			data: null,
			orderable: false,
			searchable: false,
			orderData: 0
		}, {
			targets: 1,
			data: 'productName'
		}, {
			targets: 2,
			data: 'insTime',
			render: function(data) {
				return formatDate(data);
			}
		}, {
			targets: 3,
			data: 'price',
			render: function(data) {
				return numeral(data).format();
			}
		}, {
			targets: 4,
			data: 'priceNewest',
			render: function(data) {
				return formatDate(data);
			}
		}, {
			targets: 5,
			data: 'priceId',
			orderable: false,
			searchable: false,
			visible: false
		}],
		language: {
			infoFiltered: '(Đã lọc từ _MAX_ hạng mục)',
			zeroRecords: 'Không tìm thấy hạng mục phù hợp'
		}
	});

	showTableContent(tableCategory);
	$('div.dataTables_wrapper').find('.row:first .col-sm-6:first').remove();
	$('div#table-product-content_paginate').hide();

	$('#table-category-content tbody').on('click', 'tr', function() {
		var flag = true;

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

			CATEGORY_ROW_SELECTED = tableCategory.row(this).index();
			flag = false;

			showTableContent(tableProduct, {
				categoryId: getDataRowSelected(tableCategory).id
			});
		}
		$('#btn-category-edit, #btn-category-delete, #btn-product-insert').attr({
			disabled: flag
		});
		$('#btn-product-edit, #btn-product-delete').attr({
			disabled: true
		});
	});
	$('#table-product-content tbody').on('click', 'tr', function() {
		var flag = true;

		if ($(this).find('td').attr('colspan')) {
			return false;
		}
		if ($(this).hasClass('bg-light-green')) {
			$(this).removeClass('bg-light-green text-white');
		} else {
			tableProduct.$('tr.bg-light-green').removeClass('bg-light-green text-white');
			$(this).addClass('bg-light-green text-white');

			PRODUCT_ROW_SELECTED = tableProduct.row(this).index();
			flag = false;
		}
		$('#btn-product-edit, #btn-product-delete').attr({
			disabled: flag
		});
	});
	$('#modal-category-form').on('show.bs.modal', function(event) {
		var modal = $(this);
		var button = $(event.relatedTarget);
		var buttonId = button.attr('id');

		TABLE_WORKING = 'table-category-content';

		if (buttonId.endsWith('-insert')) {
			var headerClass = 'primary';
			var titleName = 'Thêm mới';
			var categoryId = 0;
			var categoryName = '';

			modal.find('.form-horizontal')[0].reset();
		} else if (buttonId.endsWith('-edit')) {
			var headerClass = 'success';
			var titleName = 'Thay đổi';
			var categoryId = getDataRowSelected(tableCategory).id;
			var categoryName = getDataRowSelected(tableCategory).categoryName;
		}
		modal.find('#category-name').val(categoryName);
		modal.find('.modal-header').changeHeaderType({
			type: headerClass,
			text: titleName + ' hàng hóa'
		});
		modal.find('#btn-category-action').changeButtonType({
			type: headerClass,
			text: titleName
		});

		var validator = addValidateForm(modal.find('.form-horizontal'));
		validator.resetForm();
	});
	$('#modal-product-form').on('show.bs.modal', function(event) {
		var modal = $(this);
		var button = $(event.relatedTarget);
		var buttonId = button.attr('id');

		TABLE_WORKING = 'table-product-content';

		if (buttonId.endsWith('-insert')) {
			var headerClass = 'primary';
			var titleName = 'Thêm mới';
			var productName = '';
			var price = '';

			if (!modal.find('#category-select').hasClass('hide')) {
				modal.find('#category-select-show, #category-select').toggleClass('hide');
			}
			modal.find('.form-horizontal')[0].reset();
		} else if (buttonId.endsWith('-edit')) {
			var headerClass = 'success';
			var titleName = 'Thay đổi';
			var productName = getDataRowSelected(tableProduct).productName;
			var price = getDataRowSelected(tableProduct).price;

			if (modal.find('#category-select').hasClass('hide')) {
				modal.find('#category-select-show, #category-select').toggleClass('hide');
			}
		}
		var categoryId = getDataRowSelected(tableCategory).id;
		var categoryName = getDataRowSelected(tableCategory).categoryName;
		var select = modal.find('#category-select');
		select.empty();

		$.each(tableCategory.rows().data(), function(idx, category) {
			var option = $('<option/>', {
				value: category.id,
				text: category.categoryName
			});
			if (categoryId == category.id) {
				option.attr({
					selected: true
				});
			}
			select.append(option);
		});
		modal.find('#category-select-show').html(categoryName);
		modal.find('#product-name').val(productName);
		modal.find('#product-price').val(price);
		modal.find('.modal-header').changeHeaderType({
			type: headerClass,
			text: titleName + ' hạng mục'
		});
		modal.find('#btn-product-action').changeButtonType({
			type: headerClass,
			text: titleName
		});

		var validator = addValidateForm(modal.find('.form-horizontal'));
		validator.resetForm();
	});
	$('#modal-confirm-delete').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);

		TABLE_WORKING = button.parent().closest('div.panel-body').find('table').attr('id');
	});
	$('#modal-category-form #btn-category-action').on('click', function() {
		var modal = $(this).parent().closest('div.modal');
		var buttonId = $(this).attr('id');

		if (!modal.find('.form-horizontal').valid()) {
			return false;
		}
		if ($(this).hasClass('btn-primary')) {
			var msgClass = 'primary';
			var msg = 'Thêm mới thành công !';
			var data = {
				mode: 'insert'
			};
		} else if ($(this).hasClass('btn-success')) {
			var msgClass = 'success';
			var msg = 'Thay đổi thành công !';
			var data = {
				id: getDataRowSelected(tableCategory).id,
				mode: 'update'
			};
		}
		data['categoryName'] = $('#modal-category-form #category-name').val();

		callAjax(REQUEST_CATEGORY_ACTION, data, function(json) {
			if (json.status == 0) {
				showTableContent(tableCategory);
			} else {
				msgClass = 'danger';
				msg = '';

				$.each(json.error, function(idx, err) {
					msg += err + '<br/>';
				});
			}
			modal.modal('hide');

			$('#category-message').removeClass('hide').fadeIn().delay(3000).fadeOut().changeMessageType({
				type: msgClass,
				text: msg
			});
		});
	});
	$('#modal-product-form #btn-product-action').on('click', function() {
		var modal = $(this).parent().closest('div.modal');
		var buttonId = $(this).attr('id');

		if (!modal.find('.form-horizontal').valid()) {
			return false;
		}
		if ($(this).hasClass('btn-primary')) {
			var msgClass = 'primary';
			var msg = 'Thêm mới thành công !';
			var categoryId = getDataRowSelected(tableCategory).id;
			var price = '';
			var data = {
				mode: 'insert'
			};
		} else if ($(this).hasClass('btn-success')) {
			var msgClass = 'success';
			var msg = 'Thay đổi thành công !';
			var categoryId = $('#modal-product-form #category-select').val();
			var oldProductName = getDataRowSelected(tableProduct).productName;
			var oldPrice = getDataRowSelected(tableProduct).price;
			var data = {
				id: getDataRowSelected(tableProduct).id,
				priceId: getDataRowSelected(tableProduct).priceId,
				mode: 'update'
			};
		}
		var categoryName = getDataRowSelected(tableCategory).categoryName;
		var productName = $('#modal-product-form #product-name').val();
		var price = $('#modal-product-form #product-price').val();
		var updateProduct = false;
		var updatePrice = false;

		if (getDataRowSelected(tableCategory).id != categoryId || oldProductName != productName) {
			updateProduct = true;
		}
		if (oldPrice != price) {
			updatePrice = true;
		}
		data['categoryId'] = categoryId;
		data['productName'] = productName;
		data['price'] = price;
		data['updateProduct'] = updateProduct;
		data['updatePrice'] = updatePrice;

		callAjax(REQUEST_PRODUCT_ACTION, data, function(json) {
			if (json.status == 0) {
				showTableContent(tableProduct, {
					categoryId: getDataRowSelected(tableCategory).id
				});
			} else {
				msgClass = 'danger';
				msg = '';

				$.each(json.error, function(idx, err) {
					msg += err + '<br/>';
				});
			}
			modal.modal('hide');

			$('#product-message').removeClass('hide').fadeIn().delay(3000).fadeOut().changeMessageType({
				type: msgClass,
				text: msg
			});
		});
	});
	$('#modal-confirm-delete #btn-delete').on('click', function() {
		var form = $(this).parent().closest('div.modal');
		var msgClass = 'warning';
		var msg = 'Xóa thành công !';

		switch (TABLE_WORKING) {
			case 'table-category-content':
				var url = REQUEST_CATEGORY_ACTION;
				var message = 'category-message';
				var data = {
					id: getDataRowSelected(tableCategory).id,
					mode: 'delete'
				};
				break;
			case 'table-product-content':
				var url = REQUEST_PRODUCT_ACTION;
				var message = 'product-message';
				var data = {
					id: getDataRowSelected(tableProduct).id,
					mode: 'delete'
				};
				break;
		}
		callAjax(url, data, function(json) {
			if (json.status == 0) {
				PRODUCT_ROW_SELECTED = -1;

				switch (TABLE_WORKING) {
					case 'table-category-content':
						CATEGORY_ROW_SELECTED = -1;
						showTableContent(tableCategory);
						tableProduct.clear().draw();
						$('div#table-product-content_paginate').hide();

						$('#btn-category-edit, #btn-category-delete, #btn-product-insert, #btn-product-edit, #btn-product-delete').attr({
							disabled: true
						});
						break;
					case 'table-product-content':
						showTableContent(tableProduct, {
							categoryId: getDataRowSelected(tableCategory).id
						});
						$('#btn-product-edit, #btn-product-delete').attr({
							disabled: true
						});
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

			$('#' + message).removeClass('hide').fadeIn().delay(3000).fadeOut().changeMessageType({
				type: msgClass,
				text: msg
			});
		});
	});
	$('#modal-product-form #product-price').on('keydown', function(e) {
		numbericFilter(e, $(this).val());
	});
});
var showTableContent = function(table, data) {
	var url = REQUEST_CATEGORY_SELECT;
	data = (data == undefined) ? {} : data;

	if (table.table().node().id == 'table-product-content') {
		url = REQUEST_PRODUCT_SELECT;
	}
	callAjax(url, data, function(json) {
		drawTableContent(table, json);
	});
};
var drawTableContent = function(table, json) {
	var tableId = table.table().node().id;
	var paginate = 'div#' + tableId + '_paginate';
	var row = CATEGORY_ROW_SELECTED;

	if (tableId == 'table-product-content') {
		row = PRODUCT_ROW_SELECTED;
	}
	table.clear();
	table.rows.add(json).draw();

	if (table.data().count() <= 10) {
		$(paginate).hide();
	} else {
		$(paginate).show();
	}
	if (row != -1 && table.row(row).node() != null) {
		table.row(row).node().classList.add('bg-light-green', 'text-white');
	}
};
var getDataRowSelected = function(table) {
	var row = CATEGORY_ROW_SELECTED;

	if (table.table().node().id == 'table-product-content') {
		row = PRODUCT_ROW_SELECTED;
	}
	return table.row(row).data();
};