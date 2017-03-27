<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="daily-content" class="col-md-12 padding-0 fade in">
	<div class="col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3>Số liệu xăng dầu <span id="daily-display">hôm nay (<fmt:formatDate pattern="dd/MM/yyyy" value="${now}" />)</span></h3>
			</div>
			<!-- /div.panel-heading -->

			<div class="panel-body">
				<div class="responsive-table">
					<table id="table-daily-content" class="table table-striped table-bordered" width="100%" cellspacing="0">
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
							</tr>
						</tfoot>
						<c:if test="${empty dailies}">
							<tbody>
								<tr>
									<td colspan="11" class="bg-primary text-center"><h4 class="text-white">Chưa có dữ liệu.</h4></td>
								</tr>
							</tbody>
						</c:if>
						<c:if test="${not empty dailies}">
							<c:set var="shift" value="" />

							<c:forEach var="daily" items="${dailies}">
								<c:set var="cssTank" value="initial" />
								<c:set var="cssDifferent" value="initial" />

								<c:if test="${daily.productId == 1}">
									<c:set var="cssTank" value="#1c84c6;" />
								</c:if>
								<c:if test="${daily.productId == 2}">
									<c:set var="cssTank" value="#27c24c;" />
								</c:if>
								<c:if test="${daily.different < 0}">
									<c:set var="cssDifferent" value="#ff6656;" />
								</c:if>
								<c:if test="${daily.shiftId != shift}">
									<c:set var="shift" value="${daily.shiftId}" />
									<tbody>
										<tr>
											<td colspan="11" class="bg-info text-center"><h5 class="text-white">${daily.shiftName}</h5></td>
										</tr>
								</c:if>
								<tr style="color: ${cssTank}">
									<td>${daily.tankName}</td>
									<td>
										<fmt:formatNumber value="${daily.meterNew}" />
									</td>
									<td>
										<fmt:formatNumber value="${daily.meterOld}" />
									</td>
									<td>
										<fmt:formatNumber value="${daily.meterQuantity}" />
									</td>
									<td>
										<fmt:formatNumber maxFractionDigits="1" value="${daily.meterElecNew}" />
									</td>
									<td>
										<fmt:formatNumber maxFractionDigits="1" value="${daily.meterElecOld}" />
									</td>
									<td>
										<fmt:formatNumber maxFractionDigits="1" value="${daily.meterElecQuantity}" />
									</td>
									<td>
										<fmt:formatNumber value="${daily.price}" />
									</td>
									<td>
										<fmt:formatNumber value="${daily.amount}" />
									</td>
									<td>
										<fmt:formatNumber value="${daily.amountElec}" />
									</td>
									<td style="color: ${cssDifferent}">
										<fmt:formatNumber maxFractionDigits="0" value="${daily.different}" />
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</c:if>
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