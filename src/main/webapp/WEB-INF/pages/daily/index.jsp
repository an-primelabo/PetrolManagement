<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ah.petrolmanagement.enums.Shifts" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="<c:url value='/static/css/pages/daily.css' />" rel="stylesheet">

<fmt:setLocale value="vi-VN" />
<c:set var="now" value="<%= new java.util.Date() %>" />

<div class="col-md-7 padding-0">
	<div class="col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3>Đơn giá xăng dầu (Đơn vị: Đồng)</h3>
			</div>
			<!-- /div.panel-heading -->

			<div class="panel-body">
				<div class="responsive-table">
					<table id="price-content" class="table table-striped" width="100%" cellspacing="0">
						<thead>
							<tr>
								<th></th>
								<th colspan="2" class="text-center">Xăng A92</th>
								<th colspan="2" class="text-center">Xăng A95</th>
								<th colspan="2" class="text-center">Dầu DO</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Giá lần trước</td>
								<c:forEach var="o" items="${oldPrice}">
									<td><span id="old-time-${o.productId}"><fmt:formatDate pattern="dd/MM/yyyy" value="${o.insTime}" /></span></td>
									<td><span id="old-price-${o.productId}"><fmt:formatNumber type="number" value="${o.price}" /></span></td>
								</c:forEach>
							</tr>
							<tr>
								<td>Giá hiện tại</td>
								<c:forEach var="n" items="${newPrice}">
									<td>
										<span id="new-time-${n.productId}"><fmt:formatDate pattern="dd/MM/yyyy" value="${n.insTime}" /></span>
										<span id="new-time-label-${n.productId}" class="hide">Giá mới: </span>
									</td>
									<td>
										<span id="new-price-${n.productId}"><fmt:formatNumber type="number" value="${n.price}" /></span>
										<input type="text" id="new-price-input-${n.productId}" class="no-margin hide" value="${n.price}" />
										<input type="hidden" id="new-price-temp-${n.productId}" value="${n.price}" />
										<input type="hidden" id="price-id-${n.productId}" value="${n.id}" />
									</td>
								</c:forEach>
							</tr>
							<tr>
								<td></td>
								<c:forEach var="productId" items="${productIdList}">
									<td colspan="2" class="text-center"><a href="#" class="link-update" data-product-id="${productId}">Điều chỉnh</a></td>
								</c:forEach>
							</tr>
						</tbody>
					</table>
					<!-- /table#price-content -->
				</div>
				<!-- /div.responsive-table -->
			</div>
			<!-- /div.panel-body -->
		</div>
		<!-- /div.panel -->
	</div>
	<!-- /div.col-md-12 -->
</div>
<!-- /div.col-md-7 -->

<div class="col-md-5 padding-0">
	<div class="col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3>Bảng chức năng</h3>
			</div>
			<!-- /div.panel-heading -->

			<div class="panel-body">
				<div class="form-animate-radio">
					<c:forEach var="shift" items="<%= Shifts.values() %>">
						<label class="radio">
							<input type="radio" id="shift-${shift.code}" name="shift" value="${shift.code}" />
							<span class="outer"><span class="inner"></span></span> ${shift.value}
						</label>
					</c:forEach>
				</div>
				<!-- /div.form-animate-radio -->

				<div class="form-element">
					<div class="form-group form-animate-text">
						<input type="text" id="daily-search" class="form-text dateAnimate" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${now}" />" />
						<span class="bar"></span>
						<label><span class="fa fa-calendar"></span> Tìm kiếm theo ngày</label>
					</div>
					<!-- /div.input-group -->
				</div>
				<!-- /div.form-element -->
	
				<div class="top-20">
					<button type="button" id="btn-daily-search" class="btn btn-gradient btn-success"><i class="fa fa-search" aria-hidden="true"></i> Tìm kiếm!</button>
					<button type="button" class="btn btn-gradient btn-primary"><i class="fa fa-plus" aria-hidden="true"></i> Thêm số liệu</span></button>
					<button type="button" class="btn btn-gradient btn-danger"><i class="fa fa-bar-chart" aria-hidden="true"></i> Xem báo cáo</span></button>
				</div>
				<!-- /div.top-20 -->
			</div>
			<!-- /div.panel-body -->
		</div>
		<!-- /div.panel -->
	</div>
	<!-- /div.col-md-12 -->
</div>
<!-- /div.col-md-5 -->

<div class="col-md-12 padding-0">
	<div class="col-md-12">
		<div class="panel">
			<div class="panel-heading">
				<h3>Số liệu xăng dầu <span id="daily-display">hôm nay (<fmt:formatDate pattern="dd/MM/yyyy" value="${now}" />)</span></h3>
			</div>
			<!-- /div.panel-heading -->

			<div class="panel-body">
				<div class="responsive-table">
					<table id="daily-content" class="table table-striped table-bordered" width="100%" cellspacing="0">
						<thead>
							<tr>
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
								<th class="td-actions"></th>
							</tr>
						</thead>
						<tfoot>
							<tr>
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
								<th class="td-actions"></th>
							</tr>
						</tfoot>
						<jsp:include page="sub/list-daily-content.jsp" />
					</table>
					<!-- /table#daily-content -->
				</div>
				<!-- /div.responsive-table -->
			</div>
			<!-- /div.panel-body -->
		</div>
		<!-- /div.panel -->
	</div>
	<!-- /div.col-md-12 -->
</div>
<!-- /div.col-md-12 -->
<script type="text/javascript">
$(function() {
	$('input:radio[id^=shift-]:first').attr('checked', true);
	$('input:radio[id^=shift-]').on('change', function() {
		var data = {
			shiftId: $(this).val()
		};
		if ($(this).val() == 0) {
			data = {};
		}
		callAjax(REQUEST_SEARCH, data, function(json) {
			createDailyContent(json);
		});
	});
	$('#btn-daily-search').on('click', function() {
		var today = formatDate();
		var value = $('#daily-search').val();
		var data = {
			insTime: value
		};
		callAjax(REQUEST_SEARCH, data, function(json) {
			createDailyContent(json);

			if (today == value) {
				$('#daily-display').html('hôm nay (' + formatDate(value) + ')');
			} else {
				$('#daily-display').html(formatDate(value));
			}
		});
	});
	$('#price-content a.link-update').on('click', function() {
		var productId = $(this).data('product-id');
		var isSelect = $(this).hasClass('selected');

		if (!isSelect) {
			$('#price-content a.link-update').each(function(ele) {
				var productIdTemp = $(this).data('product-id');
				var isSelectTemp = $(this).hasClass('selected');

				if (isSelectTemp) {
					$('#price-content #new-price-input-' + productIdTemp).val($('#price-content #new-price-temp-' + productIdTemp).val());
					$('#price-content #new-time-' + productIdTemp + ', #price-content #new-price-' + productIdTemp).toggleClass('hide');
					$('#price-content #new-time-label-' + productIdTemp + ', #price-content #new-price-input-' + productIdTemp).toggleClass('hide');
					$(this).removeClass('selected');
					return false;
				}
			});
			$('#price-content #new-time-' + productId + ', #price-content #new-price-' + productId).toggleClass('hide');
			$('#price-content #new-time-label-' + productId + ', #price-content #new-price-input-' + productId).toggleClass('hide');
			$(this).addClass('selected');
		} else {
			var data = {
				id: $('#price-content #price-id-' + productId).val(),
				productId: productId,
				price: $('#price-content #new-price-input-' + productId).val()
			};
			callAjax(REQUEST_INSERT_PRICE, data, function(json) {
				updateFuelPriceContent(json);
			});
		}
	});
});
var REQUEST_INSERT_PRICE = '${home}/daily/insert/price';
var REQUEST_SEARCH = '${home}/daily/search';
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
var updateFuelPriceContent = function(json) {
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
	$('#price-content #old-time-' + productId).html(oldTime);
	$('#price-content #old-price-' + productId).html(oldPrice);
	$('#price-content #new-time-' + productId).html(newTime);
	$('#price-content #new-price-' + productId).html(newPrice);
	$('#price-content #new-price-input-' + productId).val(newPrice);
	$('#price-content #price-id-' + productId).val(id);
	$('#price-content #new-time-' + productId +', #price-content #new-price-' + productId).toggleClass('hide');
	$('#price-content #new-time-label-' + productId + ', #price-content #new-price-input-' + productId).toggleClass('hide');
	$('#price-content a.link-update.selected').removeClass('selected');
}
var createDailyContent = function(json) {
	$('#daily-content tbody').remove();

	if (json.length == 0) {
		var td = $('<td/>', {
			colspan: 12,
			class: 'text-center',
			text: 'Chưa có dữ liệu.'
		});
		var tr = $('<tr/>').append(td);
		var tbody = $('<tbody/>').append(tr);
		$('#daily-content').append(tbody);
	} else {
		var shift = '';
		var tbody = null;

		$.each(json, function(i, item) {
			var cssTank = 'initial';
			var cssDifferent = 'initial';

			if (item.productId == 1) {
				cssTank = '#36c;';
			} else if (item.productId == 2) {
				cssTank = '#3c3;';
			}
			if (item.different < 0) {
				cssDifferent = '#f30;';
			}
			if (item.shiftId != shift) {
				var td = $('<td/>', {
					colspan: 12,
					class: 'caption text-center',
					text: item.shiftName
				});
				var tr = $('<tr/>').append(td);
				tbody = $('<tbody/>').append(tr);
				$('#daily-content').append(tbody);
				shift = item.shiftId;
			}
			var tr = $('<tr/>', {
				style: 'color:' + cssTank
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
				style: 'color:' + cssDifferent,
				text: numeral(item.different).format()
			});
			tr.append(tdDifferent);
			var i = $('<i/>', {
				class: 'fa fa-check btn-icon-only',
				'aria-hidden': true
			});
			var a = $('<a/>', {
				href: 'javascript:;',
				class: 'btn btn-small btn-success'
			}).append(i);
			var tdActions = $('<td/>', {
				class: 'td-actions'
			}).append(a);
			tr.append(tdActions);
			tbody.append(tr);
		});
	}
};
</script>