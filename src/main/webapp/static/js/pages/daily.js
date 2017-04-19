$(document).ready(function() {
	var tableDaily = $('#table-daily-content').DataTable({
		info: false,
		paging: false,
		searching: false,
		drawCallback: function(settings) {
			var api = this.api();
			var rows = api.rows({
				page: 'current'
			}).nodes();
			var last = null;

			api.column(0, {
				page: 'current'
			}).data().each(function(group, i) {
				if (last !== group) {
					$(rows).eq(i).before(
						'<tr class="bg-info text-center"><td colspan="11" class="text-white">' + group + '</td></tr>'
					);

					last = group;
				}
			});
		},
		rowCallback: function(row, data, index) {
			var cssTank = 'initial';
			var cssDifferent = 'initial';

			if (data.productId == 1) {
				cssTank = '#1c84c6';
			} else if (data.productId == 2) {
				cssTank = '#27c24c';
			}
			if (parseInt(row.lastChild.innerHTML) <= 0) {
				cssDifferent = '#ff6656';
			}
			row.style.color = cssTank;
			row.lastChild.style.color = cssDifferent;
		},
		pageLength: 25,
		columnDefs: [{
			targets: 0,
			data: 'shiftName',
			orderable: false,
			orderData: 0,
			visible: false
		}, {
			targets: 1,
			data: 'tankName'
		}, {
			targets: 2,
			data: 'meterNew',
			render: function(data) {
				return numeral(data).format();
			}
		}, {
			targets: 3,
			data: 'meterOld',
			render: function(data) {
				return numeral(data).format();
			}
		}, {
			targets: 4,
			data: null,
			render: function(data, type, row) {
				var meterQuantity = row.meterNew - row.meterOld;
				return numeral(meterQuantity).format();
			}
		}, {
			targets: 5,
			data: 'meterElecNew',
			render: function(data) {
				return numeral(data).format('0,0.0');
			}
		}, {
			targets: 6,
			data: 'meterElecOld',
			render: function(data) {
				return numeral(data).format('0,0.0');
			}
		}, {
			targets: 7,
			data: null,
			render: function(data, type, row) {
				var meterElecQuantity = row.meterElecNew - row.meterElecOld;
				return numeral(meterElecQuantity).format('0,0.0');
			}
		}, {
			targets: 8,
			data: 'price',
			render: function(data) {
				return numeral(data).format();
			}
		}, {
			targets: 9,
			data: null,
			render: function(data, type, row) {
				var meterQuantity = row.meterNew - row.meterOld;
				var amount = meterQuantity * row.price;
				return numeral(amount).format();
			}
		}, {
			targets: 10,
			data: null,
			render: function(data, type, row) {
				var meterElecQuantity = row.meterElecNew - row.meterElecOld;
				var amountElec = meterElecQuantity * row.price;
				return numeral(amountElec).format();
			}
		}, {
			targets: 11,
			data: null,
			render: function(data, type, row) {
				var meterQuantity = row.meterNew - row.meterOld;
				var amount = meterQuantity * row.price;
				var meterElecQuantity = row.meterElecNew - row.meterElecOld;
				var amountElec = meterElecQuantity * row.price;
				var different = amountElec - amount;
				return numeral(different).format();
			}
		}]
	});

	initial(tableDaily);
	$('#table-daily-content tbody').on('click', 'tr', function() {
		var flag = true;

		if ($(this).find('td').attr('colspan')) {
			return false;
		}
		if ($(this).hasClass('bg-light-green')) {
			$(this).removeClass('bg-light-green text-white');
		} else {
			tableDaily.$('tr.bg-light-green').removeClass('bg-light-green text-white');
			$(this).addClass('bg-light-green text-white');

			DAILY_ROW_SELECTED = tableDaily.row(this).index();
			flag = false;
		}
		$('#btn-daily-edit').attr({
			disabled: flag
		});
	});
	$('input:radio[name=shift]').on('change', function() {
		var shift = $(this).val();
		var data = {
			insTime: $('#daily-search').val()
		};
		if (shift != 0) {
			data['shift'] = shift;
		}
		showTableContent(tableDaily, data);
	});
	$('#daily-search').on('change', function() {
		var today = formatDate();
		var value = $(this).val();
		var shift = $('input:radio[name=shift]:checked').val();
		var data = {};

		if (shift != 0) {
			data['shift'] = shift;
		}
		if (!value) {
			value = today;
			$('#daily-search').val(value);
		}
		data['insTime'] = value;

		if (today == value) {
			$('#daily-display').html('hôm nay (' + formatDate(value) + ')');
		} else {
			$('#daily-display').html('ngày ' + formatDate(value));
		}
		showTableContent(tableDaily, data);
	});
	$('#modal-daily-form').on('show.bs.modal', function(event) {
		var modal = $(this);
		var button = $(event.relatedTarget);
		var buttonId = button.attr('id');

		modal.find('fieldset[id^=tank-]').each(function() {
			var _this = $(this);

			$('tr[id^=price-]').each(function() {
				if (_this.data('product-id') == $(this).data('product-id')) {
					_this.attr({
						'data-price-id': $(this).attr('id').replace('price-', ''),
						'data-price': $(this).data('price')
					})
					_this.find('h5:first strong').html(numeral($(this).data('price')).format());
					return false;
				}
			});
		});

		if (buttonId.endsWith('-insert')) {
			var headerClass = 'primary';
			var titleName = 'Thêm mới';

			modal.find('.form-horizontal').removeClass('hide');

			$.each(DAILY_DATA, function(idx, daily) {
				modal.find('#daily-form-' + daily.tankId).addClass('hide');
			});
			modal.find('.form-horizontal:not(.hide)').each(function(idx, form) {
				form.reset();
			});
			modal.find('.form-inline')[0].reset();

			if (!modal.find('.price-select').hasClass('hide')) {
				modal.find('.price-select').toggleClass('hide');
			}
		} else if (buttonId.endsWith('-edit')) {
			var headerClass = 'success';
			var titleName = 'Thay đổi';
			var tankId = getDataRowSelected(tableDaily).tankId;
			var price = getDataRowSelected(tableDaily).price;
			var meterNew = getDataRowSelected(tableDaily).meterNew;
			var meterOld = getDataRowSelected(tableDaily).meterOld;
			var meterElecNew = getDataRowSelected(tableDaily).meterElecNew;
			var meterElecOld = getDataRowSelected(tableDaily).meterElecOld;
			var shift = getDataRowSelected(tableDaily).shift;

			modal.find('.form-horizontal').addClass('hide');
			modal.find('#daily-form-' + tankId).removeClass('hide');
			modal.find('#daily-form-' + tankId + ' h5:first strong').html(numeral(price).format());
			modal.find('#daily-form-' + tankId + ' h5:first strong').attr({
				'data-price': price
			});
			modal.find('#daily-form-' + tankId + ' .price-select').removeClass('hide');
			modal.find('#meter-new-' + tankId).val(meterNew);
			modal.find('#meter-old-' + tankId).val(meterOld);
			modal.find('#meter-elec-new-' + tankId).val(meterElecNew);
			modal.find('#meter-elec-old-' + tankId).val(meterElecOld);
			modal.find('#meter-elec-old-' + tankId).trigger('blur');
			modal.find('#shift-insert-' + shift).attr({
				checked: true
			});
		}
		modal.find('.modal-header').changeHeaderType({
			type: headerClass,
			text: titleName + ' số liệu'
		});
		modal.find('#btn-daily-action').changeButtonType({
			type: headerClass,
			text: titleName
		});

		FORM_INPUT = {};

		modal.find('.form-horizontal:not(.hide)').each(function() {
			FORM_INPUT[$(this).attr('id')] = addValidateForm($(this));
			FORM_INPUT[$(this).attr('id')].resetForm();
		});
		FORM_CHECK = addValidateForm(modal.find('.form-inline'));
		FORM_CHECK.resetForm();
	});
	$('#modal-daily-form input:text[id^=meter-new-][required], #modal-daily-form input:text[id^=meter-old-][required]').on('keydown', function(e) {
		numbericFilter(e, $(this).val());
	});
	$('#modal-daily-form input:text[id^=meter-elec-new-][required], #modal-daily-form input:text[id^=meter-elec-old-][required]').on('keydown', function(e) {
		decimalFilter(e, $(this).val());
	});
	$('#modal-daily-form input:text[required]').on('blur', function() {
		var target = $(this).parent().closest('fieldset').attr('id').replace('tank-', '');
		var price = $('#modal-daily-form #tank-content-' + target + ' h5:first strong').html();
		var _meterNew = $('#modal-daily-form #meter-new-' + target);
		var _meterOld = $('#modal-daily-form #meter-old-' + target);
		var _meterQuantity = $('#modal-daily-form #meter-quantity-' + target);
		var _amount = $('#modal-daily-form #amount-' + target);
		var _meterElecNew = $('#modal-daily-form #meter-elec-new-' + target);
		var _meterElecOld = $('#modal-daily-form #meter-elec-old-' + target);
		var _meterElecQuantity = $('#modal-daily-form #meter-elec-quantity-' + target);
		var _amountElec = $('#modal-daily-form #amount-elec-' + target);
		var _different = $('#modal-daily-form #different-' + target);

		var meterQuantity = numeral(_meterNew.val()).value() - numeral(_meterOld.val()).value();
		var amount = meterQuantity * numeral(price).value();
		var meterElecQuantity = numeral(_meterElecNew.val()).value() - numeral(_meterElecOld.val()).value();
		var amountElec = meterElecQuantity * numeral(price).value();
		var different = amountElec - amount;

		if (_meterNew.val()) {
			_meterNew.val(numeral(_meterNew.val()).format());
		}
		if (_meterOld.val()) {
			_meterOld.val(numeral(_meterOld.val()).format());
		}
		_meterQuantity.html(numeral(meterQuantity).format());
		_amount.html(numeral(amount).format());

		if (_meterElecNew.val()) {
			_meterElecNew.val(numeral(_meterElecNew.val()).format('0,0.0'));
		}
		if (_meterElecOld.val()) {
			_meterElecOld.val(numeral(_meterElecOld.val()).format('0,0.0'));
		}
		_meterElecQuantity.html(numeral(meterElecQuantity).format('0,0.0'));
		_amountElec.html(numeral(amountElec).format());

		if (different < 0) {
			_different.css({
				color: '#ff6656'
			});
		}
		_different.html(numeral(different).format());
	});
	$('#modal-daily-form fieldset[id^=tank-] legend').on('click', function() {
		var target = $(this).parent().attr('id').replace('tank-', '');

		if ($(this).attr('style')) {
			$('#modal-daily-form #tank-content-' + target).fadeIn();
			$('#modal-daily-form #tank-' + target + ' legend').removeAttr('style');
			$('#modal-daily-form #ignore-' + target).prop({
				checked: false
			});
		}
	});
	$('#modal-daily-form input:checkbox[id^=ignore-]').on('click', function() {
		var target = $(this).attr('id').replace('ignore-', '');

		if ($(this).is(':checked')) {
			$('#modal-daily-form #tank-content-' + target).fadeOut();
			$('#modal-daily-form #tank-' + target + ' legend').css({
				color: '#1c84c6',
				cursor: 'pointer'
			});
		}
	});
	$('#modal-daily-form input:checkbox[id^=price-select-]').on('click', function() {
		var target = $(this).attr('id').replace('price-select-', '');
		var fieldset = $(this).parent().closest('fieldset#tank-' + target);
		var priceShow = fieldset.find('h5:first strong');

		if ($(this).is(':checked')) {
			priceShow.html(numeral(fieldset.data('price')).format());
		} else {
			priceShow.html(numeral(priceShow.data('price')).format());
		}
	});
	$('#modal-daily-form #btn-daily-action').on('click', function() {
		var modal = $(this).parent().closest('div.modal');
		var buttonId = $(this).attr('id');
		var flag = false;

		$.each(FORM_INPUT, function(idx, form) {
			if (!form.currentForm.getElementsByClassName('checkbox')[0].checked) {
				if (!form.form()) {
					flag = true;
					return false;
				}
			} else {
				delete FORM_INPUT[idx];
			}
		});
		if (flag || !FORM_CHECK.form()) {
			return false;
		}
		if ($(this).hasClass('btn-primary')) {
			var msgClass = 'primary';
			var msg = 'Thêm mới thành công !';
			var dailyList = [];

			$.each(FORM_INPUT, function(idx, form) {
				var target = idx.replace('daily-form-', '');
				var fieldset = form.currentForm.getElementsByTagName('fieldset');
				var _priceId = fieldset['tank-' + target].getAttribute('data-price-id');
				var input = form.currentForm.getElementsByTagName('input');
				var _meterNew = input['meter-new-' + target].value;
				var _meterOld = input['meter-old-' + target].value;
				var _meterElecNew = input['meter-elec-new-' + target].value;
				var _meterElecOld = input['meter-elec-old-' + target].value;
				var _shift = FORM_CHECK.currentForm.getElementsByTagName('input');
				var shiftValue = 0;

				$.each(_shift, function(idx, ele) {
					if (ele.checked) {
						shiftValue = ele.value;
						return false;
					}
				});
				var daily = {
					tankId: target,
					shift: shiftValue,
					priceId: _priceId,
					meterNew: numeral(_meterNew).value(),
					meterOld: numeral(_meterOld).value(),
					meterElecNew: numeral(_meterElecNew).value(),
					meterElecOld: numeral(_meterElecOld).value()
				};
				dailyList.push(daily);
			});
			var data = {
				'dailyList': dailyList,
				mode: 'insert'
			};
		} else if ($(this).hasClass('btn-success')) {
			var msgClass = 'success';
			var msg = 'Thay đổi thành công !';
			var target = 0;
			var fieldset = null;
			var _priceId = 0;
			var newPrice = null;
			var input = null;
			var _meterNew = 0;
			var _meterOld = 0;
			var _meterElecNew = 0;
			var _meterElecOld = 0;
			var _shift = null;
			var shiftValue = 0;

			$.each(FORM_INPUT, function(idx, form) {
				target = idx.replace('daily-form-', '');
				fieldset = form.currentForm.getElementsByTagName('fieldset');
				_priceId = fieldset['tank-' + target].getAttribute('data-price-id');
				newPrice = form.currentForm.getElementsByClassName('checkbox')[1].checked;
				input = form.currentForm.getElementsByTagName('input');
				_meterNew = input['meter-new-' + target].value;
				_meterOld = input['meter-old-' + target].value;
				_meterElecNew = input['meter-elec-new-' + target].value;
				_meterElecOld = input['meter-elec-old-' + target].value;
				_shift = FORM_CHECK.currentForm.getElementsByTagName('input');

				$.each(_shift, function(idx, ele) {
					if (ele.checked) {
						shiftValue = ele.value;
						return false;
					}
				});
				_priceId = newPrice ? _priceId : getDataRowSelected(tableDaily).priceId;
			});
			var data = {
				id: getDataRowSelected(tableDaily).id,
				tankId: target,
				shift: shiftValue,
				priceId: _priceId,
				meterNew: numeral(_meterNew).value(),
				meterOld: numeral(_meterOld).value(),
				meterElecNew: numeral(_meterElecNew).value(),
				meterElecOld: numeral(_meterElecOld).value(),
				mode: 'update'
			};
		}

		callAjax(REQUEST_DAILY_ACTION, data, function(json) {
			if (json.status == 0) {
				initial(tableDaily);
			} else {
				msgClass = 'danger';
				msg = '';

				$.each(json.error, function(idx, err) {
					msg += err + '<br/>';
				});
			}
			modal.modal('hide');

			$('#daily-message').removeClass('hide').fadeIn().delay(3000).fadeOut().changeMessageType({
				type: msgClass,
				text: msg
			});
		});
	});
	$('#btn-daily-check').on('click', function() {
		$('.badges-ribbon').removeClass('hide');
	});
	//	$('#daily-controls #btn-daily-chart').on('click', function() {
	//		$('#daily-chart #daily-chart-modal .modal-title span').empty();
	//		$('#daily-chart #daily-chart-modal #chart-donut div[id^=chart-donut-]').empty();
	//		$('#daily-chart #daily-chart-modal #chart-multi').empty();
	//		$('#daily-chart #form-daily-chart')[0].reset();
	//		$('#daily-chart #form-daily-chart #dropdown-chart-type span.text-change').html('Chọn');
	//		$('#daily-chart #form-daily-chart #chart-view').addClass('hide');
	//		$('#daily-chart #form-daily-chart input[id^=chart-type-]').addClass('hide')
	//	});
	//	/* ----- daily-insert ----- */
	//	var formDailyInsert = $('#daily-insert #form-daily-insert').validate({
	//		errorElement: 'em',
	//		errorPlacement: function(error, element) {
	//			error.appendTo(element.parent().closest('div'));
	//		}
	//	});
	//	hideMessageDailyInsert();
	//	$('#daily-insert #form-daily-insert input:text[required]').each(function() {
	//		$(this).rules('add', TEXT_REQUIRED_RULES);
	//	});
	//	$('#daily-insert #form-daily-insert input:radio[required]').rules('add', RADIO_REQUIRED_RULES);
	//	$('#daily-insert #tabs-tanks a[id^=tab-tank-]').on('click', function() {
	//		hideMessageDailyInsert();
	//	});
	//	$('#daily-insert #form-daily-insert input:text[required]').on('keydown', function(e) {
	//		numbericFilter(e, $(this).val());
	//	});
	//	$('#daily-insert #form-daily-insert input:radio[name=shift-insert]').on('change', function() {
	//		checkFormDailyInsert(formDailyInsert);
	//	});
	//	$('#daily-insert #form-daily-insert #btn-daily-insert').on('click', function() {
	//		var shift = $('#daily-insert #form-daily-insert input:radio[name=shift-insert]:checked').val();
	//		var flag = true;
	//		var data = [];
	//
	//		if ($('#daily-insert #form-daily-insert').valid()) {
	//			$('#daily-insert #tabs-tanks-content div[id^=tab-tank-content-]').each(function() {
	//				var meterNewElement = $(this).find('input:text[id^=meter-new-]');
	//				var meterOldElement = $(this).find('input:text[id^=meter-old-]');
	//				var meterElecNewElement = $(this).find('input:text[id^=meter-elec-new-]');
	//				var meterElecOldElement = $(this).find('input:text[id^=meter-elec-old-]');
	//				var idElement = $(this).find('input:hidden[id^=tank-product-id-]');
	//				var temp = {
	//					tankId: idElement.data('tank-id'),
	//					shift: shift,
	//					priceId: idElement.data('price-id'),
	//					meterNew: numeral(meterNewElement.val()).value(),
	//					meterOld: numeral(meterOldElement.val()).value(),
	//					meterElecNew: numeral(meterElecNewElement.val()).value(),
	//					meterElecOld: numeral(meterElecOldElement.val()).value(),
	//					insUser: 'test'
	//				};
	//				if (!meterNewElement.val() || !meterOldElement.val() || !meterElecNewElement.val() || !meterElecOldElement.val()) {
	//					var id = $(this).attr('id');
	//
	//					$('#daily-insert #tabs-tanks a[href$=' + id + ']').trigger('click');
	//
	//					showWarningDailyInsert($(this));
	//					flag = false;
	//					return false;
	//				} else {
	//					data.push(temp);
	//				}
	//			});
	//			if (flag) {
	//				callAjax(REQUEST_INSERT_DAILY, data, function(json) {
	//					createSearchDaily(shift, formatDate());
	//				});
	//			}
	//		}
	//	});
	//	/* ----- daily-chart ----- */
	//	$('#daily-chart #form-daily-chart a[id^=chart-type-]').on('click', function() {
	//		$('#daily-chart #form-daily-chart #dropdown-chart-type span.text-change').html($(this).html());
	//		$('#daily-chart #form-daily-chart #chart-view').removeClass('hide');
	//		$('#daily-chart #form-daily-chart #chart-view').show();
	//		$('#daily-chart #form-daily-chart input').not('[id^=' + $(this).attr('id') + ']').addClass('hide');
	//		$('#daily-chart #form-daily-chart input').not('[id^=' + $(this).attr('id') + ']').hide();
	//
	//		var elementSelect = $('#daily-chart #form-daily-chart input[id^=' + $(this).attr('id') + ']');
	//		elementSelect.removeClass('hide');
	//		elementSelect.show();
	//		$('#daily-chart #daily-chart-modal .modal-title span').html($(this).html().toLowerCase() + ' ' + elementSelect.val());
	//		$('#daily-chart #daily-chart-modal #chart-donut').removeClass('hide');
	//
	//		createChart(elementSelect[0].value, elementSelect[1].value);
	//	});
	//	$('#daily-chart #form-daily-chart input[id^=chart-type-]').on('input propertychange', function() {
	//		var elementSelect = $('#daily-chart #form-daily-chart input[id^=chart-type-]:visible');
	//
	//		createChart(elementSelect[0].value, elementSelect[1].value);
	//	});
	//	$('#daily-chart #form-daily-chart input:radio[name=chart-view]').on('change', function() {
	//		showChart(JSON_DATA_CHART);
	//	});
});
var initial = function(table) {
	$('input:radio[name=shift]:first').attr('checked', true);
	showTableContent(table, {
		insTime: $('#daily-search').val()
	});
};
var showTableContent = function(table, data) {
	data = (data == undefined) ? {} : data;

	callAjax(REQUEST_DAILY_SELECT, data, function(json) {
		DAILY_DATA = json;

		table.clear();
		table.rows.add(json).draw();

		if (DAILY_ROW_SELECTED != -1 && table.row(DAILY_ROW_SELECTED).node() != null) {
			table.row(DAILY_ROW_SELECTED).node().classList.add('bg-light-green', 'text-white');
		}
	});
};
var getDataRowSelected = function(table) {
	return table.row(DAILY_ROW_SELECTED).data();
};
var createChart = function(from, to) {
	var data = {
		insTimeFrom: from
	}
	if (to) {
		data['insTimeTo'] = to;
	}
	callAjax(REQUEST_CHART, data, function(json) {
		showChart(json);
	});
};
//var showChart = function(json) {
//	JSON_DATA_CHART = json;
//	$('#daily-chart #daily-chart-modal #chart-donut div[id^=chart-donut-]').empty();
//	$('#daily-chart #daily-chart-modal #chart-multi').empty();
//
//	var view = $('#daily-chart #form-daily-chart input:radio[name=chart-view]:checked').val();
//
//	if (json.status == 'single-day') {
//		var dataMorning = [];
//		var dataEvening = [];
//
//		switch (view) {
//		case '1':
//			$.each(json.list, function(idx, item) {
//				var temp = {
//					label: item.tankName,
//					value: item.amount
//				};
//				if (item.shift == 1) {
//					dataMorning.push(temp);
//				} else {
//					dataEvening.push(temp);
//				}
//			});
//			break;
//		case '2':
//			$.each(json.list, function(idx, item) {
//				var temp = {
//					label: item.tankName,
//					value: item.amountElec
//				};
//				if (item.shift == 1) {
//					dataMorning.push(temp);
//				} else {
//					dataEvening.push(temp);
//				}
//			});
//			break;
//		case '3':
//			$.each(json.list, function(idx, item) {
//				var temp = {
//					label: item.tankName,
//					value: item.different
//				};
//				if (item.shift == 1) {
//					dataMorning.push(temp);
//				} else {
//					dataEvening.push(temp);
//				}
//			});
//			break;
//		default:
//			break;
//		}
//		Morris.Donut({
//			element : 'chart-donut-morning',
//			data : dataMorning,
//			colors : getRandom(CHART_COLORS, json.list.length)
//		}).on('click', function(idx, row) {
//			console.log(idx, row);
//		});
//		Morris.Donut({
//			element : 'chart-donut-evening',
//			data : dataEvening,
//			colors : getRandom(CHART_COLORS, json.list.length)
//		}).on('click', function(idx, row) {
//			console.log(idx, row);
//		});
//	}
//};
//var TEXT_REQUIRED_RULES = {
//	required: true,
//	number: true,
//	messages: {
//		required: 'Yêu cầu nhập.',
//		number: 'Yêu cầu chỉ nhập số và dấu (.) nếu có số thập phân'
//	}
//};
//var RADIO_REQUIRED_RULES = {
//	required: true,
//	messages: {
//		required: 'Yêu cầu chọn.'
//	}
//};
//var JSON_DATA_CHART = '';
//var CHART_COLORS = [ '#1c84c6', '#27c24c', '#73caef', '#fec8c3', '#f0ad4e',
//		'#857e7e', '#f44336', '#e91e63', '#9c27b0', '#673ab7', '#3f51b5',
//		'#2196f3', '#03a9f4', '#00bcd4', '#009688', '#4caf50', '#8bc34a',
//		'#cddc39', '#ffeb3b', '#ffc107', '#ff9800', '#ff5722', '#795548',
//		'#9e9e9e', '#f0f3f4', '#607d8b', '#d32f2f', '#c2185b', '#7b1fa2',
//		'#512da8', '#303f9f', '#1976d2', '#0288d1', '#0097a7', '#00796b',
//		'#388e3c', '#689f38', '#afb42b', '#fbc02d', '#ffa000', '#f57c00',
//		'#e64a19', '#5d4037', '#616161', '#455a64' ];
//var getNewestPrice = function() {
//	var priceList = {};
//
//	$('#price-content #table-price-content input:text[id^=new-price-input-]').each(function() {
//		var p = {
//			priceId: $(this).data('price-id'),
//			price: $(this).val()
//		};
//		priceList[$(this).data('product-id')] = p;
//	});
//	$('#daily-insert #form-daily-insert div[id^=tab-tank-content-] input:hidden[id^=tank-product-id-]').each(function() {
//		var tankId = $(this).data('tank-id');
//		var productId = $(this).val();
//
//		$(this).attr({
//			'data-price-id': priceList[productId].priceId
//		});
//		$('#daily-insert #tabs-tanks-content div[id^=tab-tank-content-' + tankId + '] div[id^=price-' + tankId + '] p strong').html(numeral(priceList[productId].price).format());
//	});
//};
//var createSearchDaily = function(shift, date) {
//	var data = {
//		insTime: date
//	};
//	if (shift != 0) {
//		data['shift'] = shift;
//	}
//	callAjax(REQUEST_SEARCH, data, function(json) {
//		showDailyContent(json);
//
//		if (!$('#daily-content').is(':visible')) {
//			$('#daily-content').show();
//			$('#daily-insert').hide();
//			$('#daily-controls #btn-daily-insert-new').removeAttr('disabled');
//		} else {
//			$('#daily-content #table-daily-content tbody').hide('fast').show('slow');
//		}
//	});
//};
//var showDailyContent = function(json) {
//	$('#daily-content #table-daily-content tbody').remove();
//
//	if (json.length == 0) {
//		var h4 = $('<h4/>', {
//			class: 'text-white',
//			text: 'Chưa có dữ liệu.'
//		});
//		var td = $('<td/>', {
//			colspan: 11,
//			class: 'bg-primary text-center'
//		}).append(h4);
//		var tr = $('<tr/>').append(td);
//		var tbody = $('<tbody/>').append(tr);
//		$('#daily-content #table-daily-content').append(tbody);
//	} else {
//		var shift = '';
//		var tbody = null;
//
//		$.each(json, function(idx, item) {
//			var cssTank = 'initial';
//			var cssDifferent = 'initial';
//
//			if (item.productId == 1) {
//				cssTank = '#1c84c6;';
//			} else if (item.productId == 2) {
//				cssTank = '#27c24c;';
//			}
//			if (item.different < 0) {
//				cssDifferent = '#ff6656;';
//			}
//			if (item.shift != shift) {
//				var h5 = $('<h5/>', {
//					class: 'text-white',
//					text: item.shiftName
//				});
//				var td = $('<td/>', {
//					colspan: 11,
//					class: 'bg-info text-center'
//				}).append(h5);
//				var tr = $('<tr/>').append(td);
//				tbody = $('<tbody/>').append(tr);
//				$('#daily-content #table-daily-content').append(tbody);
//				shift = item.shift;
//			}
//			var tr = $('<tr/>', {
//				style: 'color: ' + cssTank
//			});
//			var tdName = $('<td/>', {
//				text: item.tankName
//			});
//			tr.append(tdName);
//			var tdMeterNew = $('<td/>', {
//				text: numeral(item.meterNew).format()
//			});
//			tr.append(tdMeterNew);
//			var tdMeterOld = $('<td/>', {
//				text: numeral(item.meterOld).format()
//			});
//			tr.append(tdMeterOld);
//			var tdMeterQuantity = $('<td/>', {
//				text: numeral(item.meterQuantity).format()
//			});
//			tr.append(tdMeterQuantity);
//			var tdMeterElecNew = $('<td/>', {
//				text: numeral(item.meterElecNew).format('0,0.0')
//			});
//			tr.append(tdMeterElecNew);
//			var tdMeterElecOld = $('<td/>', {
//				text: numeral(item.meterElecOld).format('0,0.0')
//			});
//			tr.append(tdMeterElecOld);
//			var tdMeterElecQuantity = $('<td/>', {
//				text: numeral(item.meterElecQuantity).format('0,0.0')
//			});
//			tr.append(tdMeterElecQuantity);
//			var tdRate = $('<td/>', {
//				text: numeral(item.price).format()
//			});
//			tr.append(tdRate);
//			var tdAmount = $('<td/>', {
//				text: numeral(item.amount).format()
//			});
//			tr.append(tdAmount);
//			var tdAmountElec = $('<td/>', {
//				text: numeral(item.amountElec).format()
//			});
//			tr.append(tdAmountElec);
//			var tdDifferent = $('<td/>', {
//				style: 'color: ' + cssDifferent,
//				text: numeral(item.different).format()
//			});
//			tr.append(tdDifferent);
//			tbody.append(tr);
//		});
//	}
//};
//var checkFormDailyInsert = function(formDailyInsert) {
//	var tabActive = $('#daily-insert #form-daily-insert div[id^=tab-tank-content-].active.in');
//
//	formDailyInsert.form();
//
//	var errors = formDailyInsert.numberOfInvalids();
//
//	if (errors) {
//		showAlertDailyInsert(tabActive, errors);
//		return false;
//	}
//	formDailyInsert.resetForm();
//	hideMessageDailyInsert();
//	return true;
//};
//var showAlertDailyInsert = function (tabActive, errors) {
//	var message = 'Vẫn còn <strong>{0}</strong> lỗi bên dưới. Yêu cầu nhập đầy đủ.'; 
//
//	tabActive.find('div[id^=tab-tank-message-] span.fa').removeClass('fa-exclamation-triangle').addClass('fa-flash');
//	tabActive.find('div[id^=tab-tank-message-] p').html(message.replace('{0}', errors));
//	tabActive.find('div[id^=tab-tank-message-]').removeClass('alert-warning').addClass('alert-danger');
//	tabActive.find('div[id^=tab-tank-message-]').show();
//};
//var showWarningDailyInsert = function (tabActive) {
//	var message = 'Yêu cầu nhập đầy đủ.'; 
//
//	tabActive.find('div[id^=tab-tank-message-] span.fa').removeClass('fa-flash').addClass('fa-exclamation-triangle');
//	tabActive.find('div[id^=tab-tank-message-] p').html(message);
//	tabActive.find('div[id^=tab-tank-message-]').removeClass('alert-danger').addClass('alert-warning');
//	tabActive.find('div[id^=tab-tank-message-]').show();
//};
//var hideMessageDailyInsert = function(tabActive) {
//	if (typeof tabActive == 'undefined') {
//		$('#daily-insert #form-daily-insert div[id^=tab-tank-content-] div[id^=tab-tank-message-]').hide();
//	} else {
//		tabActive.find('div[id^=tab-tank-message-]').hide();
//	}
//};