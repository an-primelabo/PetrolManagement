(function($) {
	$.fn.changeButtonType = function(options) {
		var _this = this;
		var settings = $.extend({
			text: _this.html(),
			type: 'default'
		}, options);
		this.removeClass('btn-primary btn-success btn-info btn-warning btn-danger btn-default').addClass('btn-' + settings.type);
		return this.html(settings.text);
	};

	$.fn.changeHeaderType = function(options) {
		var settings = $.extend({
			text: 'Chưa đặt tên',
			type: 'default'
		}, options);
		this.removeClass('bg-primary bg-success bg-info bg-warning bg-danger bg-default').addClass('bg-' + settings.type);
		return this.find('.modal-title').html(settings.text);
	};

	$.fn.changeMessageType = function(options) {
		var settings = $.extend({
			text: 'Chưa có thông báo',
			type: 'default'
		}, options);
		this.removeClass('alert-primary alert-success alert-info alert-warning alert-danger alert-default').addClass('alert-' + settings.type);
		return this.find('strong').html(settings.text);
	};

	$.fn.generateSelect = function(options) {
		this.removeClass('alert-primary alert-success alert-info alert-warning alert-danger alert-default').addClass('alert-' + settings.type);
		return this.find('strong').html(settings.text);
	};

	if ($.fn.dataTable) {
		$.extend(true, $.fn.dataTable.defaults, {
			deferRender: true,
			lengthChange: false,
			processing: true,
			stateSave: true,
			language: {
				decimal: '.',
				emptyTable: 'Không có dữ liệu',
				info: 'Đang hiển thị _START_ - _END_ / _TOTAL_ dòng (Trang _PAGE_ / _PAGES_)',
				infoEmpty: 'Đang hiển thị 0 dòng',
				thousands: ',',
				loadingRecords: 'Đang tải dữ liệu...',
				processing: 'Đang xử lý...',
				search: 'Tìm kiếm:',
				paginate: {
					first: 'Trang đầu',
					last: 'Trang cuối',
					next: 'Tiếp',
					previous: 'Trước'
				},
				aria: {
					sortAscending: ': Sắp xếp tăng dần',
					sortDescending: ': Sắp xếp giảm dần'
				}
			}
		});
	}
}(jQuery));
var addValidateForm = function(form) {
	var textRequiredRules = {
		required: true,
		messages: {
			required: 'Yêu cầu nhập.'
		}
	};
	form.validate({
		errorElement: 'em',
		errorPlacement: function(error, element) {
			error.appendTo(element.parent().closest('div'));
		}
	});
	form.find('input:text[required], input:password[required]').each(function() {
		$(this).rules('add', textRequiredRules);
	});
};
var callAjax = function(url, data, callback) {
	$.ajax({
		async: false,
		contentType: 'application/json; charset=utf-8',
		data: JSON.stringify(data),
		dataSrc: '',
		dataType: 'json',
		timeout: 100000,
		type: 'POST',
		url: url,
		success: function(response) {
			console.log(response);
			callback(response);
		},
		error: function(e) {
			console.log('ERROR: ', e);
		}
	});
};
var formatDate = function(value) {
	if (typeof value == 'undefined') {
		var d = new Date();
		var month = d.getMonth() + 1;
		var day = d.getDate();
		return d.getFullYear() + '-' + (('' + month).length < 2 ? '0' : '') + month + '-' + (('' + day).length < 2 ? '0' : '') + day;
	}
	var d = new Date(value);
	var month = d.getMonth() + 1;
	var day = d.getDate();
	return (('' + day).length < 2 ? '0' : '') + day + '/' + (('' + month).length < 2 ? '0' : '') + month + '/' + d.getFullYear();
};
var getRandom = function(arr, n) {
	var result = new Array(n);
	var len = arr.length;
	var taken = new Array(len);

	if (n > len) {
		throw new RangeError('getRandom: more elements taken than available.');
	}
	while (n--) {
		var x = Math.floor(Math.random() * len);
		result[n] = arr[x in taken ? taken[x] : x];
		taken[x] = --len;
	}
	return result;
};
var numbericFilter = function(event, value) {
	if ($.inArray(event.keyCode, [ 46, 8, 9, 27, 13, 110, 190 ]) !== -1 ||
	// Allow: Ctrl+A, Command+A
	(event.keyCode === 65 && (event.ctrlKey === true || event.metaKey === true)) ||
	// Allow: home, end, left, right, down, up
	(event.keyCode >= 35 && event.keyCode <= 40)) {
		if (value.indexOf('.') !== -1 && event.keyCode == 190) {
			event.preventDefault();
		}
		return;
	}
	// Ensure that it is a number and stop the keypress
	if ((event.shiftKey || (event.keyCode < 48 || event.keyCode > 57)) && (event.keyCode < 96 || event.keyCode > 105)) {
		event.preventDefault();
	}
};