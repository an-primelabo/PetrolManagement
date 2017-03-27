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
				updatePriceContent(json);
				updatePrice();
			});
		}
	});
	/* ----- daily-controls ----- */
	$('#daily-controls input:radio[id^=shift-]:first').attr('checked', true);
	$('#daily-controls input:radio[id^=shift-]').on('change', function() {
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
		createSearchDaily($('#daily-controls input:radio[id^=shift-]:checked').val(), value);
	});
	$('#daily-controls #btn-daily-insert-new').on('click', function() {
		if ($('#daily-insert').hasClass('hide')) {
			$('#daily-insert #form-daily-insert')[0].reset();
			$('#daily-content, #daily-insert').toggleClass('hide in');

			updatePrice();
		}
	});
	/* ----- daily-insert ----- */
	var formDailyInsert = $('#daily-insert #form-daily-insert').validate({
		errorElement: 'em',
		errorPlacement: function(error, element) {
			error.appendTo(element.parent().closest('div'));
		}
	});
	$('#daily-insert #form-daily-insert input:text[required]').each(function() {
		$(this).rules('add', TEXT_REQUIRED_RULES);
	});
	$('#daily-insert #form-daily-insert input:radio[required]').rules('add', RADIO_REQUIRED_RULES);
	$('#daily-insert #form-daily-insert input:text[required]').on('keydown', function(e) {
		numbericFilter(e, $(this).val());
	});
	$('#daily-insert #form-daily-insert input:text[required]').on('blur', function() {
		checkValidateForm(formDailyInsert);

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
	$('#daily-insert #form-daily-insert input:checkbox[id^=tank-ignore-]').on('click', function() {
		checkValidateForm(formDailyInsert);
	});
	$('#daily-insert #form-daily-insert input:radio[id^=shift-insert-]').on('change', function() {
		checkValidateForm(formDailyInsert);
	});
	$('#tabs-tanks a[id^=tab-tank-]').on('click', function() {
		return checkValidateForm(formDailyInsert);
	});
	$('#daily-insert #form-daily-insert #btn-daily-insert').on('click', function() {
		var shift = $('#daily-insert #form-daily-insert input:radio[id^=shift-insert-]:checked').val();
		var data = [];

		$('#daily-insert #tabs-tanks-content div[id^=tab-tank-content-]').each(function() {
			var meterNewElement = $(this).find('input:text[id^=meter-new-]');
			var meterOldElement = $(this).find('input:text[id^=meter-old-]');
			var meterElecNewElement = $(this).find('input:text[id^=meter-elec-new-]');
			var meterElecOldElement = $(this).find('input:text[id^=meter-elec-old-]');
			var ignoreElement = $(this).find('input:checkbox[id^=tank-ignore-]');
			var idElement = $(this).find('input:hidden[id^=tank-product-id-]');
			var temp = {
				tankId: idElement.data('tank-id'),
				shiftId: shift,
				priceId: idElement.data('price-id'),
				meterNew: numeral(meterNewElement.val()).value(),
				meterOld: numeral(meterOldElement.val()).value(),
				meterElecNew: numeral(meterElecNewElement.val()).value(),
				meterElecOld: numeral(meterElecOldElement.val()).value(),
				insUser: 'test'
			};

			if (meterNewElement.val() && !ignoreElement.is(':checked')) {
				data.push(temp);
			}
		});
		callAjax(REQUEST_INSERT_DAILY, data, function(json) {
			createDailyContent(json);
		});
		createSearchDaily(shift, formatDate());
	});
});
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
var VALIDATE_STILL_ERROR = 'Vẫn còn <strong>{0}</strong> lỗi bên dưới. Yêu cầu nhập đầy đủ.';
var checkValidateForm = function(formDailyInsert) {
	var tabActive = $('#daily-insert #form-daily-insert div[id^=tab-tank-content-].active.in');
	var flag = false;

	$('#daily-insert #form-daily-insert div[id^=tab-tank-content-].active.in input[required]').each(function () {
		if ($(this).val()) {
			flag = true;
			return false;
		}
	});
	if (tabActive.find('input:checkbox[id^=tank-ignore-]').is(':checked')) {
		flag = false;
	}
	if (flag) {
		formDailyInsert.form();

		var errors = formDailyInsert.numberOfInvalids();

		if (errors) {
			tabActive.find('div[id^=tab-tank-message-] p span').html(VALIDATE_STILL_ERROR.replace('{0}', errors));
			tabActive.find('div[id^=tab-tank-message-]').removeClass('hide').addClass('in');
			return false;
		}
		tabActive.find('div[id^=tab-tank-message-]').removeClass('in').addClass('hide');
		return true;
	} else {
		formDailyInsert.resetForm();
		tabActive.find('div[id^=tab-tank-message-]').removeClass('in').addClass('hide');
		return true;
	}
};
var updatePrice = function() {
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
var callAjax = function(url, data, callback) {
	$.ajax({
		type: 'POST',
		contentType: 'application/json; charset=utf-8',
		url: url,
		data: JSON.stringify(data),
		dataType: 'json',
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
var createSearchDaily = function(shift, date) {
	var data = {
		insTime: date
	};
	if (shift != 0) {
		data['shiftId'] = shift;
	}
	callAjax(REQUEST_SEARCH, data, function(json) {
		createDailyContent(json);
	});
};
var updatePriceContent = function(json) {
	var id = 0;
	var productId = 0;
	var oldTime = '';
	var oldPrice = 0;
	var newTime = '';
	var newPrice = 0;

	$.each(json, function(i, item) {
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
var createDailyContent = function(json) {
	if ($('#daily-content').hasClass('hide')) {
		$('#daily-content, #daily-insert').toggleClass('hide in');
	}
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

		$.each(json, function(i, item) {
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
			if (item.shiftId != shift) {
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
				shift = item.shiftId;
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