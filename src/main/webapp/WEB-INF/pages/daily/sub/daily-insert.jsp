<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ah.petrolmanagement.enums.Shifts"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="daily-insert" class="col-md-12 padding-0 animated fadeIn hide">
	<div class="col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3>Thêm mới số liệu xăng dầu hôm nay (<fmt:formatDate pattern="dd/MM/yyyy" value="${now}" />)</h3>
			</div>
			<!-- /div.panel-heading -->

			<div class="panel-body">
				<form id="form-daily-insert" class="form-horizontal" action="" method="post">
					<div class="col-md-12 tabs-area margin-0">
						<ul id="tabs-tanks" class="nav nav-tabs nav-tabs-v3 padding-0" role="tablist">
							<c:forEach var="tank" items="${tankList}" varStatus="idx">
								<c:set var="active" value="" />

								<c:if test="${idx.count == 1}">
									<c:set var="active" value="active" />
								</c:if>
								<li class="${active}" role="presentation">
									<a href="#tab-tank-content-${tank.id}" id="tab-tank-${tank.id}" data-toggle="tab" role="tab" aria-expanded="true">${tank.tankName}</a>
								</li>
							</c:forEach>
						</ul>
						<!-- /ul#tabs-tanks -->

						<div id="tabs-tanks-content" class="tab-content tab-content-v3">
							<c:forEach var="tank" items="${tankList}" varStatus="idx">
								<c:set var="active" value="" />

								<c:if test="${idx.count == 1}">
									<c:set var="active" value="active in" />
								</c:if>
								<div id="tab-tank-content-${tank.id}" class="tab-pane fade ${active}" data-id="${tank.id}" role="tabpanel" aria-labelledby="tab-tank-content-${tank.id}">
									<div class="col-md-12">
										<div id="tab-tank-message-${tank.id}" class="col-md-12 col-sm-12 alert alert-icon alert-dismissible animated fadeIn top-20" role="alert">
											<div class="col-md-1 col-sm-1 icon-wrapper text-center"><span class="fa fa-flash"></span></div>
											<!-- /div.col-md-1.col-sm-1.icon-wrapper.text-center -->
	
											<div class="col-md-11 col-sm-11">
												<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
												<p></p>
											</div>
											<!-- /div.col-md-11.col-sm-11 -->
										</div>
										<!-- /div.col-md-12.col-sm-12.alert.alert-icon.alert-dismissible.animated.fadeIn.top-20 -->
									</div>
									<!-- /div.col-md-12 -->

									<div class="col-md-12 text-center top-20">
										<div class="col-md-2">
											<div id="price-${tank.id}" class="alert alert-outline alert-warning in" role="alert">
												<p>Đơn giá: <strong>123</strong></p>
											</div>
											<!-- /div.alert.alert-outline.alert-warning.in -->
										</div>
										<!-- /div.col-md-2 -->

										<div class="col-md-5">
											<div class="alert alert-outline alert-primary in" role="alert">
												<strong>Trụ</strong>
											</div>
											<!-- /div.alert.alert-outline.alert-primary.in -->
										</div>
										<!-- /div.col-md-5 -->

										<div class="col-md-5">
											<div class="alert alert-outline alert-success in" role="alert">
												<strong>Điện Tử</strong>
											</div>
											<!-- /div.alert.alert-outline.alert-success.in -->
										</div>
										<!-- /div.col-md-5 -->
									</div>
									<!-- /div.col-md-12.text-center.top-20 -->

									<div class="col-md-12">
										<div class="form-group">
											<label class="col-md-2 control-label">Chỉ Số Mới - Cũ</label>

											<div class="col-md-5 padding-0">
												<div class="col-md-6">
													<input type="text" id="meter-new-${tank.id}" name="meter-new-${tank.id}" class="form-control android" placeholder="Chỉ số mới" required />
												</div>
												<!-- /div.col-md-6 -->

												<div class="col-md-6">
													<input type="text" id="meter-old-${tank.id}" name="meter-old-${tank.id}" class="form-control android" placeholder="Chỉ số cũ" required />
												</div>
												<!-- /div.col-md-6 -->
											</div>
											<!-- /div.col-md-5.padding-0 -->

											<div class="col-md-5 padding-0">
												<div class="col-md-6">
													<input type="text" id="meter-elec-new-${tank.id}" name="meter-elec-new-${tank.id}" class="form-control android" placeholder="Chỉ số mới" required />
												</div>
												<!-- /div.col-md-6 -->

												<div class="col-md-6">
													<input type="text" id="meter-elec-old-${tank.id}" name="meter-elec-old-${tank.id}" class="form-control android" placeholder="Chỉ số cũ" required />
												</div>
												<!-- /div.col-md-6 -->
											</div>
											<!-- /div.col-md-5.padding-0 -->
										</div>
										<!-- /div.form-group -->
									</div>
									<!-- /div.col-md-12 -->

									<div class="col-md-12">
										<div class="form-group">
											<label class="col-md-2 control-label">Bán</label>

											<div class="col-md-5">
												<p id="meter-quantity-${tank.id}" class="form-control-static">0</p>
											</div>
											<!-- /div.col-md-5 -->

											<div class="col-md-5">
												<p id="meter-elec-quantity-${tank.id}" class="form-control-static">0</p>
											</div>
											<!-- /div.col-md-5 -->
										</div>
										<!-- /div.form-group -->
									</div>
									<!-- /div.col-md-12 -->

									<div class="col-md-12">
										<div class="form-group">
											<label class="col-md-2 control-label">Thành Tiền</label>

											<div class="col-md-5">
												<p id="amount-${tank.id}" class="form-control-static">0</p>
											</div>
											<!-- /div.col-md-5 -->

											<div class="col-md-5">
												<p id="amount-elec-${tank.id}" class="form-control-static">0</p>
											</div>
											<!-- /div.col-md-5 -->
										</div>
										<!-- /div.form-group -->
									</div>
									<!-- /div.col-md-12 -->

									<div class="col-md-12">
										<div class="form-group">
											<label class="col-md-2 control-label">Chênh Lệch</label>

											<div class="col-md-10">
												<p id="different-${tank.id}" class="form-control-static">0</p>
											</div>
											<!-- /div.col-md-10 -->
										</div>
										<!-- /div.form-group -->
									</div>
									<!-- /div.col-md-12 -->

									<input type="hidden" id="tank-product-id-${tank.id}" value="${tank.productId}" data-tank-id="${tank.id}" data-price-id="" />
								</div>
								<!-- /div#tab-tank-content-${tank.id} -->
							</c:forEach>
						</div>
						<!-- /div#tabs-tanks-content -->
					</div>
					<!-- /div.col-md-12.tabs-area.margin-0 -->

					<div class="col-md-12 top-20">
						<div class="col-md-4 form-group form-animate-radio padding-0">
							<c:forEach var="shift" items="<%= Shifts.values() %>">
								<label class="radio margin-0">
									<input type="radio" id="shift-insert-${shift.code}" name="shift-insert" value="${shift.code}" required />
									<span class="outer"><span class="bg-primary inner"></span></span> ${shift.value}
								</label>
							</c:forEach>
						</div>
						<!-- /div.col-md-4.form-group.form-animate-radio.padding-0 -->

						<div class="col-md-8">
							<button type="button" id="btn-daily-insert" class="btn btn-gradient btn-primary"><i class="fa fa-plus" aria-hidden="true"></i> Thêm số liệu</button>
						</div>
						<!-- /div.col-md-8 -->
					</div>
					<!-- /div.col-md-12.top-20 -->
				</form>
				<!-- /form#form-daily-insert -->
			</div>
			<!-- /div.panel-body -->
		</div>
		<!-- /div.panel.panel-default -->
	</div>
	<!-- /div.col-md-12 -->
</div>
<!-- /div#daily-insert -->