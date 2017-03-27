<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="price-content" class="col-md-7 padding-0">
	<div class="col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3>Đơn giá xăng dầu (Đơn vị: Đồng)</h3>
			</div>
			<!-- /div.panel-heading -->

			<div class="panel-body">
				<div class="responsive-table">
					<table id="table-price-content" class="table table-striped" width="100%" cellspacing="0">
						<thead>
							<tr class="text-center">
								<th></th>
								<th colspan="2">Xăng A92</th>
								<th colspan="2">Xăng A95</th>
								<th colspan="2">Dầu DO</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Giá lần trước</td>

								<c:forEach var="o" items="${oldPrice}">
									<td><span id="old-time-${o.productId}"><fmt:formatDate pattern="dd/MM/yyyy" value="${o.insTime}" /></span></td>
									<td><span id="old-price-${o.productId}"><fmt:formatNumber value="${o.price}" /></span></td>
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
										<span id="new-price-${n.productId}"><fmt:formatNumber value="${n.price}" /></span>
										<input type="text" id="new-price-input-${n.productId}" class="hide" value="${n.price}" data-temp="${n.price}" data-price-id="${n.id}" data-product-id="${n.productId}" />
									</td>
								</c:forEach>
							</tr>
							<tr class="text-center">
								<td></td>

								<c:forEach var="productId" items="${productIdList}">
									<td colspan="2"><a href="#" class="link-update" data-product-id="${productId}">Điều chỉnh</a></td>
								</c:forEach>
							</tr>
						</tbody>
					</table>
					<!-- /table#table-price-content -->
				</div>
				<!-- /div.responsive-table -->
			</div>
			<!-- /div.panel-body -->
		</div>
		<!-- /div.panel.panel-default -->
	</div>
	<!-- /div.col-md-12 -->
</div>
<!-- /div#price-content -->