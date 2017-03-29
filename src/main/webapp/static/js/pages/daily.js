$(function() {
	/* ----- price-content ----- */
	$('#price-content #table-price-content a.link-update').on('click', function() {
		var productId = $(this).data('product-id');
		var isSelect = $(this).hasClass('selected');

		if (!isSelect) {
			$('#price-content #table-price-content a.link-update').each(function() {
				var productIdTemp = $(this).data('product-id');
				var isSelectTemp = $(this).hasClass('selected');

				if (isSelectTemp) {
					$('#price-content #table-price-content #new-price-input-' + productIdTemp).val($('#price-content #table-price-content #new-price-input-' + productIdTemp).data('temp'));
					$('#price-content #table-price-content #new-time-' + productIdTemp + ', #price-content #table-price-content #new-price-' + productIdTemp).toggleClass('hide');
					$('#price-content #table-price-content #new-time-label-' + productIdTemp + ', #price-content #table-price-content #new-price-input-' + productIdTemp).toggleClass('hide');
					$(this).removeClass('selected');
					return false;
				}
			});
			$('#price-content #table-price-content #new-time-' + productId + ', #price-content #table-price-content #new-price-' + productId).toggleClass('hide');
			$('#price-content #table-price-content #new-time-label-' + productId + ', #price-content #table-price-content #new-price-input-' + productId).toggleClass('hide');
			$(this).addClass('selected');
		} else {
			var data = {
				id: $('#price-content #table-price-content #price-id-' + productId).val(),
				productId: productId,
				price: $('#price-content #table-price-content #new-price-input-' + productId).val()
			};
			callAjax(REQUEST_INSERT_PRICE, data, function(json) {
				showPriceContent(json);
				getNewestPrice();
			});
		}
	});
	/* ----- daily-controls ----- */
	$('#daily-controls input:radio[name=shift]:first').attr('checked', true);
	$('#daily-controls input:radio[name=shift]').on('change', function() {
		createSearchDaily($(this).val(), $('#daily-controls #daily-search').val());
	});
	$('#daily-controls #daily-search').on('input propertychange', function() {
		var today = formatDate();
		var value = $(this).val();

		if (today == value) {
			$('#daily-content #daily-display').html('hôm nay (' + formatDate(value) + ')');
		} else {
			$('#daily-content #daily-display').html(formatDate(value));
		}
		createSearchDaily($('#daily-controls input:radio[name=shift]:checked').val(), value);
	});
	$('#daily-controls #btn-daily-insert-new').on('click', function() {
		$('#daily-insert #form-daily-insert')[0].reset();
		$('#daily-insert #form-daily-insert p.form-control-static').html('0');
		$('#daily-insert').removeClass('hide');
		$('#daily-insert').show();
		$('#daily-content').hide();
		$(this).attr({
			disabled: true
		});

		getNewestPrice();
	});
	$('#daily-controls #btn-daily-chart').on('click', function() {
		$('#daily-chart #daily-chart-modal .modal-title span').empty();
		$('#daily-chart #daily-chart-modal #chart-donut div[id^=chart-donut-]').empty();
		$('#daily-chart #daily-chart-modal #chart-multi').empty();
		$('#daily-chart #form-daily-chart')[0].reset();
		$('#daily-chart #form-daily-chart #dropdown-chart-type span.text-change').html('Chọn');
		$('#daily-chart #form-daily-chart #chart-view').addClass('hide');
		$('#daily-chart #form-daily-chart input[id^=chart-type-]').addClass('hide')
	});
	/* ----- daily-insert ----- */
	var formDailyInsert = $('#daily-insert #form-daily-insert').validate({
		errorElement: 'em',
		errorPlacement: function(error, element) {
			error.appendTo(element.parent().closest('div'));
		}
	});
	hideMessageDailyInsert();
	$('#daily-insert #form-daily-insert input:text[required]').each(function() {
		$(this).rules('add', TEXT_REQUIRED_RULES);
	});
	$('#daily-insert #form-daily-insert input:radio[required]').rules('add', RADIO_REQUIRED_RULES);
	$('#daily-insert #tabs-tanks a[id^=tab-tank-]').on('click', function() {
		hideMessageDailyInsert();
	});
	$('#daily-insert #form-daily-insert input:text[required]').on('keydown', function(e) {
		numbericFilter(e, $(this).val());
	});
	$('#daily-insert #form-daily-insert input:text[required]').on('blur', function() {
		checkFormDailyInsert(formDailyInsert);

		var tabActive = $('#daily-insert #form-daily-insert div[id^=tab-tank-content-].active.in');
		var meterNewElement = tabActive.find('input:text[id^=meter-new-]');
		var meterOldElement = tabActive.find('input:text[id^=meter-old-]');
		var meterQuantityElement = tabActive.find('p[id^=meter-quantity-]');
		var amountElement = tabActive.find('p[id^=amount-]');
		var meterElecNewElement = tabActive.find('input:text[id^=meter-elec-new-]');
		var meterElecOldElement = tabActive.find('input:text[id^=meter-elec-old-]');
		var meterElecQuantityElement = tabActive.find('p[id^=meter-elec-quantity-]');
		var amountElecElement = tabActive.find('p[id^=amount-elec-]');
		var differentElement = tabActive.find('p[id^=different-]');

		var meterQuantity = numeral(meterNewElement.val()).value() - numeral(meterOldElement.val()).value();
		var amount = meterQuantity * 14040;
		var meterElecQuantity = numeral(meterElecNewElement.val()).value() - numeral(meterElecOldElement.val()).value();
		var amountElec = meterElecQuantity * 14040;
		var different = amountElec - amount;

		if (meterNewElement.val()) {
			meterNewElement.val(numeral(meterNewElement.val()).format());
		}
		if (meterOldElement.val()) {
			meterOldElement.val(numeral(meterOldElement.val()).format());
		}
		meterQuantityElement.html(numeral(meterQuantity).format());
		amountElement.html(numeral(amount).format());

		if (meterElecNewElement.val()) {
			meterElecNewElement.val(numeral(meterElecNewElement.val()).format('0,0.0'));
		}
		if (meterElecOldElement.val()) {
			meterElecOldElement.val(numeral(meterElecOldElement.val()).format('0,0.0'));
		}
		meterElecQuantityElement.html(numeral(meterElecQuantity).format('0,0.0'));
		amountElecElement.html(numeral(amountElec).format());

		if (different < 0) {
			differentElement.css({
				color: '#ff6656'
			});
		}
		differentElement.html(numeral(different).format());
	});
	$('#daily-insert #form-daily-insert input:radio[name=shift-insert]').on('change', function() {
		checkFormDailyInsert(formDailyInsert);
	});
	$('#daily-insert #form-daily-insert #btn-daily-insert').on('click', function() {
		var shift = $('#daily-insert #form-daily-insert input:radio[name=shift-insert]:checked').val();
		var flag = true;
		var data = [];

		if ($('#daily-insert #form-daily-insert').valid()) {
			$('#daily-insert #tabs-tanks-content div[id^=tab-tank-content-]').each(function() {
				var meterNewElement = $(this).find('input:text[id^=meter-new-]');
				var meterOldElement = $(this).find('input:text[id^=meter-old-]');
				var meterElecNewElement = $(this).find('input:text[id^=meter-elec-new-]');
				var meterElecOldElement = $(this).find('input:text[id^=meter-elec-old-]');
				var idElement = $(this).find('input:hidden[id^=tank-product-id-]');
				var temp = {
					tankId: idElement.data('tank-id'),
					shift: shift,
					priceId: idElement.data('price-id'),
					meterNew: numeral(meterNewElement.val()).value(),
					meterOld: numeral(meterOldElement.val()).value(),
					meterElecNew: numeral(meterElecNewElement.val()).value(),
					meterElecOld: numeral(meterElecOldElement.val()).value(),
					insUser: 'test'
				};
				if (!meterNewElement.val() || !meterOldElement.val() || !meterElecNewElement.val() || !meterElecOldElement.val()) {
					var id = $(this).attr('id');

					$('#daily-insert #tabs-tanks a[href$=' + id + ']').trigger('click');

					showWarningDailyInsert($(this));
					flag = false;
					return false;
				} else {
					data.push(temp);
				}
			});
			if (flag) {
				callAjax(REQUEST_INSERT_DAILY, data, function(json) {
					createSearchDaily(shift, formatDate());
				});
			}
		}
	});
	/* ----- daily-chart ----- */
	$('#daily-chart #form-daily-chart a[id^=chart-type-]').on('click', function() {
		$('#daily-chart #form-daily-chart #dropdown-chart-type span.text-change').html($(this).html());
		$('#daily-chart #form-daily-chart #chart-view').removeClass('hide');
		$('#daily-chart #form-daily-chart #chart-view').show();
		$('#daily-chart #form-daily-chart input').not('[id^=' + $(this).attr('id') + ']').addClass('hide');
		$('#daily-chart #form-daily-chart input').not('[id^=' + $(this).attr('id') + ']').hide();

		var elementSelect = $('#daily-chart #form-daily-chart input[id^=' + $(this).attr('id') + ']');
		elementSelect.removeClass('hide');
		elementSelect.show();
		$('#daily-chart #daily-chart-modal .modal-title span').html($(this).html().toLowerCase() + ' ' + elementSelect.val());
		$('#daily-chart #daily-chart-modal #chart-donut').removeClass('hide');

		createChart(elementSelect[0].value, elementSelect[1].value);
	});
	$('#daily-chart #form-daily-chart input[id^=chart-type-]').on('input propertychange', function() {
		var elementSelect = $('#daily-chart #form-daily-chart input[id^=chart-type-]:visible');

		createChart(elementSelect[0].value, elementSelect[1].value);
	});
	$('#daily-chart #form-daily-chart input:radio[name=chart-view]').on('change', function() {
		showChart(JSON_DATA_CHART);
	});
});
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
var showChart = function(json) {
	JSON_DATA_CHART = json;
	$('#daily-chart #daily-chart-modal #chart-donut div[id^=chart-donut-]').empty();
	$('#daily-chart #daily-chart-modal #chart-multi').empty();

	var view = $('#daily-chart #form-daily-chart input:radio[name=chart-view]:checked').val();

	if (json.status == 'single-day') {
		var dataMorning = [];
		var dataEvening = [];

		switch (view) {
		case '1':
			$.each(json.list, function(idx, item) {
				var temp = {
					label: item.tankName,
					value: item.amount
				};
				if (item.shift == 1) {
					dataMorning.push(temp);
				} else {
					dataEvening.push(temp);
				}
			});
			break;
		case '2':
			$.each(json.list, function(idx, item) {
				var temp = {
					label: item.tankName,
					value: item.amountElec
				};
				if (item.shift == 1) {
					dataMorning.push(temp);
				} else {
					dataEvening.push(temp);
				}
			});
			break;
		case '3':
			$.each(json.list, function(idx, item) {
				var temp = {
					label: item.tankName,
					value: item.different
				};
				if (item.shift == 1) {
					dataMorning.push(temp);
				} else {
					dataEvening.push(temp);
				}
			});
			break;
		default:
			break;
		}
		Morris.Donut({
			element : 'chart-donut-morning',
			data : dataMorning,
			colors : getRandom(CHART_COLORS, json.list.length)
		}).on('click', function(idx, row) {
			console.log(idx, row);
		});
		Morris.Donut({
			element : 'chart-donut-evening',
			data : dataEvening,
			colors : getRandom(CHART_COLORS, json.list.length)
		}).on('click', function(idx, row) {
			console.log(idx, row);
		});
	}
};
var TEXT_REQUIRED_RULES = {
	required: true,
	number: true,
	messages: {
		required: 'Yêu cầu nhập.',
		number: 'Yêu cầu chỉ nhập số và dấu (.) nếu có số thập phân'
	}
};
var RADIO_REQUIRED_RULES = {
	required: true,
	messages: {
		required: 'Yêu cầu chọn.'
	}
};
var JSON_DATA_CHART = '';
var CHART_COLORS = [ '#1c84c6', '#27c24c', '#73caef', '#fec8c3', '#f0ad4e',
		'#857e7e', '#f44336', '#e91e63', '#9c27b0', '#673ab7', '#3f51b5',
		'#2196f3', '#03a9f4', '#00bcd4', '#009688', '#4caf50', '#8bc34a',
		'#cddc39', '#ffeb3b', '#ffc107', '#ff9800', '#ff5722', '#795548',
		'#9e9e9e', '#f0f3f4', '#607d8b', '#d32f2f', '#c2185b', '#7b1fa2',
		'#512da8', '#303f9f', '#1976d2', '#0288d1', '#0097a7', '#00796b',
		'#388e3c', '#689f38', '#afb42b', '#fbc02d', '#ffa000', '#f57c00',
		'#e64a19', '#5d4037', '#616161', '#455a64' ];
var getNewestPrice = function() {
	var priceList = {};

	$('#price-content #table-price-content input:text[id^=new-price-input-]').each(function() {
		var p = {
			priceId: $(this).data('price-id'),
			price: $(this).val()
		};
		priceList[$(this).data('product-id')] = p;
	});
	$('#daily-insert #form-daily-insert div[id^=tab-tank-content-] input:hidden[id^=tank-product-id-]').each(function() {
		var tankId = $(this).data('tank-id');
		var productId = $(this).val();

		$(this).attr({
			'data-price-id': priceList[productId].priceId
		});
		$('#daily-insert #tabs-tanks-content div[id^=tab-tank-content-' + tankId + '] div[id^=price-' + tankId + '] p strong').html(numeral(priceList[productId].price).format());
	});
};
var createSearchDaily = function(shift, date) {
	var data = {
		insTime: date
	};
	if (shift != 0) {
		data['shift'] = shift;
	}
	callAjax(REQUEST_SEARCH, data, function(json) {
		showDailyContent(json);

		if (!$('#daily-content').is(':visible')) {
			$('#daily-content').show();
			$('#daily-insert').hide();
			$('#daily-controls #btn-daily-insert-new').removeAttr('disabled');
		} else {
			$('#daily-content #table-daily-content tbody').hide('fast').show('slow');
		}
	});
};
var showPriceContent = function(json) {
	var id = 0;
	var productId = 0;
	var oldTime = '';
	var oldPrice = 0;
	var newTime = '';
	var newPrice = 0;

	$.each(json, function(idx, item) {
		id = item.id;
		productId = item.productId;

		if (item.updTime != null) {
			oldTime = formatDate(item.insTime);
			oldPrice = numeral(item.price).format();
		} else {
			newTime = formatDate(item.insTime);
			newPrice = numeral(item.price).format();
		}
	});
	$('#price-content #table-price-content #old-time-' + productId).html(oldTime);
	$('#price-content #table-price-content #old-price-' + productId).html(oldPrice);
	$('#price-content #table-price-content #new-time-' + productId).html(newTime);
	$('#price-content #table-price-content #new-price-' + productId).html(newPrice);
	$('#price-content #table-price-content #new-price-input-' + productId).attr({
		value: newPrice,
		'data-temp': newPrice,
		'data-price-id': id,
		'data-product-id': productId
	});
	$('#price-content #table-price-content #new-time-' + productId +', #price-content #table-price-content #new-price-' + productId).toggleClass('hide');
	$('#price-content #table-price-content #new-time-label-' + productId + ', #price-content #table-price-content #new-price-input-' + productId).toggleClass('hide');
	$('#price-content #table-price-content a.link-update.selected').removeClass('selected');
};
var showDailyContent = function(json) {
	$('#daily-content #table-daily-content tbody').remove();

	if (json.length == 0) {
		var h4 = $('<h4/>', {
			class: 'text-white',
			text: 'Chưa có dữ liệu.'
		});
		var td = $('<td/>', {
			colspan: 11,
			class: 'bg-primary text-center'
		}).append(h4);
		var tr = $('<tr/>').append(td);
		var tbody = $('<tbody/>').append(tr);
		$('#daily-content #table-daily-content').append(tbody);
	} else {
		var shift = '';
		var tbody = null;

		$.each(json, function(idx, item) {
			var cssTank = 'initial';
			var cssDifferent = 'initial';

			if (item.productId == 1) {
				cssTank = '#1c84c6;';
			} else if (item.productId == 2) {
				cssTank = '#27c24c;';
			}
			if (item.different < 0) {
				cssDifferent = '#ff6656;';
			}
			if (item.shift != shift) {
				var h5 = $('<h5/>', {
					class: 'text-white',
					text: item.shiftName
				});
				var td = $('<td/>', {
					colspan: 11,
					class: 'bg-info text-center'
				}).append(h5);
				var tr = $('<tr/>').append(td);
				tbody = $('<tbody/>').append(tr);
				$('#daily-content #table-daily-content').append(tbody);
				shift = item.shift;
			}
			var tr = $('<tr/>', {
				style: 'color: ' + cssTank
			});
			var tdName = $('<td/>', {
				text: item.tankName
			});
			tr.append(tdName);
			var tdMeterNew = $('<td/>', {
				text: numeral(item.meterNew).format()
			});
			tr.append(tdMeterNew);
			var tdMeterOld = $('<td/>', {
				text: numeral(item.meterOld).format()
			});
			tr.append(tdMeterOld);
			var tdMeterQuantity = $('<td/>', {
				text: numeral(item.meterQuantity).format()
			});
			tr.append(tdMeterQuantity);
			var tdMeterElecNew = $('<td/>', {
				text: numeral(item.meterElecNew).format('0,0.0')
			});
			tr.append(tdMeterElecNew);
			var tdMeterElecOld = $('<td/>', {
				text: numeral(item.meterElecOld).format('0,0.0')
			});
			tr.append(tdMeterElecOld);
			var tdMeterElecQuantity = $('<td/>', {
				text: numeral(item.meterElecQuantity).format('0,0.0')
			});
			tr.append(tdMeterElecQuantity);
			var tdRate = $('<td/>', {
				text: numeral(item.price).format()
			});
			tr.append(tdRate);
			var tdAmount = $('<td/>', {
				text: numeral(item.amount).format()
			});
			tr.append(tdAmount);
			var tdAmountElec = $('<td/>', {
				text: numeral(item.amountElec).format()
			});
			tr.append(tdAmountElec);
			var tdDifferent = $('<td/>', {
				style: 'color: ' + cssDifferent,
				text: numeral(item.different).format()
			});
			tr.append(tdDifferent);
			tbody.append(tr);
		});
	}
};
var checkFormDailyInsert = function(formDailyInsert) {
	var tabActive = $('#daily-insert #form-daily-insert div[id^=tab-tank-content-].active.in');

	formDailyInsert.form();

	var errors = formDailyInsert.numberOfInvalids();

	if (errors) {
		showAlertDailyInsert(tabActive, errors);
		return false;
	}
	formDailyInsert.resetForm();
	hideMessageDailyInsert();
	return true;
};
var showAlertDailyInsert = function (tabActive, errors) {
	var message = 'Vẫn còn <strong>{0}</strong> lỗi bên dưới. Yêu cầu nhập đầy đủ.'; 

	tabActive.find('div[id^=tab-tank-message-] span.fa').removeClass('fa-exclamation-triangle').addClass('fa-flash');
	tabActive.find('div[id^=tab-tank-message-] p').html(message.replace('{0}', errors));
	tabActive.find('div[id^=tab-tank-message-]').removeClass('alert-warning').addClass('alert-danger');
	tabActive.find('div[id^=tab-tank-message-]').show();
};
var showWarningDailyInsert = function (tabActive) {
	var message = 'Yêu cầu nhập đầy đủ.'; 

	tabActive.find('div[id^=tab-tank-message-] span.fa').removeClass('fa-flash').addClass('fa-exclamation-triangle');
	tabActive.find('div[id^=tab-tank-message-] p').html(message);
	tabActive.find('div[id^=tab-tank-message-]').removeClass('alert-danger').addClass('alert-warning');
	tabActive.find('div[id^=tab-tank-message-]').show();
};
var hideMessageDailyInsert = function(tabActive) {
	if (typeof tabActive == 'undefined') {
		$('#daily-insert #form-daily-insert div[id^=tab-tank-content-] div[id^=tab-tank-message-]').hide();
	} else {
		tabActive.find('div[id^=tab-tank-message-]').hide();
	}
};
var callAjax = function(url, data, callback) {
	$.ajax({
		type: 'POST',
		contentType: 'application/json; charset=utf-8',
		url: url,
		data: JSON.stringify(data),
		dataType: 'json',
		async: false,
		timeout: 100000,
		success: function(response) {
			console.log(response);
			callback(response);
		},
		error: function(e) {
			console.log('ERROR: ', e);
		},
		beforeSend: function() {
			$('.loading').removeClass('hide');
		},
		complete: function() {
			$('.loading').addClass('hide');
		}
	});
};