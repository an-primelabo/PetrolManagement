<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:if test="${empty dailies}">
	<tbody>
		<tr>
			<td colspan="12" class="text-center">Chưa có dữ liệu.</td>
		</tr>
	</tbody>
</c:if>
<c:if test="${not empty dailies}">
	<c:set var="shift" value="" />

	<c:forEach var="daily" items="${dailies}">
		<c:set var="cssTank" value="initial" />
		<c:set var="cssDifferent" value="initial" />

		<c:if test="${daily.productId == 1}">
			<c:set var="cssTank" value="#36c;" />
		</c:if>
		<c:if test="${daily.productId == 2}">
			<c:set var="cssTank" value="#3c3;" />
		</c:if>
		<c:if test="${daily.different < 0}">
			<c:set var="cssDifferent" value="#f30;" />
		</c:if>
		<c:if test="${daily.shiftId != shift}">
			<c:set var="shift" value="${daily.shiftId}" />
			<tbody>
				<tr>
					<td colspan="12" class="caption text-center">${daily.shiftName}</td>
				</tr>
		</c:if>
		<tr style="color: ${cssTank}">
			<td>${daily.tankName}</td>
			<td>
				<fmt:formatNumber type="number" value="${daily.meterNew}" />
			</td>
			<td>
				<fmt:formatNumber type="number" value="${daily.meterOld}" />
			</td>
			<td>
				<fmt:formatNumber type="number" value="${daily.meterQuantity}" />
			</td>
			<td>
				<fmt:formatNumber type="number" maxFractionDigits="1" value="${daily.meterElecNew}" />
			</td>
			<td>
				<fmt:formatNumber type="number" maxFractionDigits="1" value="${daily.meterElecOld}" />
			</td>
			<td>
				<fmt:formatNumber type="number" maxFractionDigits="1" value="${daily.meterElecQuantity}" />
			</td>
			<td>
				<fmt:formatNumber type="number" value="${daily.price}" />
			</td>
			<td>
				<fmt:formatNumber type="number" value="${daily.amount}" />
			</td>
			<td>
				<fmt:formatNumber type="number" value="${daily.amountElec}" />
			</td>
			<td style="color: ${cssDifferent}">
				<fmt:formatNumber type="number" maxFractionDigits="0" value="${daily.different}" />
			</td>
			<td class="td-actions"><a href="javascript:;" class="btn btn-small btn-success"><i class="btn-icon-only icon-ok"></i></a></td>
		</tr>
	</c:forEach>
	</tbody>
</c:if>