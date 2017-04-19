<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ah.petrolmanagement.enums.Shifts"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="modal-daily-form" class="modal fade" tabindex="-1" role="dialog">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span class="fa fa-times" aria-hidden="true"></span></button>
				<h4 class="modal-title text-white"></h4>
			</div>
			<!-- /div.modal-header -->

			<div class="modal-body">
				<div class="row margin-0">
					<c:forEach var="tank" items="${tankList}" varStatus="idx">
						<form id="daily-form-${tank.id}" class="form-horizontal" action="" method="post" autocomplete="off">
							<fieldset id="tank-${tank.id}" data-product-id="${tank.productId}">
								<legend>${tank.tankName}</legend>

								<div id="tank-content-${tank.id}">
									<div class="col-md-12 padding-0 text-center">
										<div class="col-md-2">
											<h5 class="page-header text-info margin-0">Đơn giá: <strong>123</strong></h5>
										</div>
										<!-- /div.col-md-2 -->

										<div class="col-md-5">
											<h5 class="page-header text-primary margin-0">Trụ</h5>
										</div>
										<!-- /div.col-md-5 -->

										<div class="col-md-5">
											<h5 class="page-header text-success margin-0">Điện tử</h5>
										</div>
										<!-- /div.col-md-5 -->
									</div>
									<!-- /div.col-md-12.padding-0.text-center -->

									<div class="col-md-12 padding-0">
										<div class="form-group">
											<label class="col-md-2 control-label">Chỉ số mới - cũ</label>

											<div class="col-md-5 padding-0">
												<div class="col-md-6">
													<input type="text" id="meter-new-${tank.id}" name="meter-new-${tank.id}" class="form-control android" placeholder="Chỉ số mới" maxlength="10" required />
												</div>
												<!-- /div.col-md-6 -->

												<div class="col-md-6">
													<input type="text" id="meter-old-${tank.id}" name="meter-old-${tank.id}" class="form-control android" placeholder="Chỉ số cũ" maxlength="10" required />
												</div>
												<!-- /div.col-md-6 -->
											</div>
											<!-- /div.col-md-5.padding-0 -->

											<div class="col-md-5 padding-0">
												<div class="col-md-6">
													<input type="text" id="meter-elec-new-${tank.id}" name="meter-elec-new-${tank.id}" class="form-control android" placeholder="Chỉ số mới" maxlength="10" required />
												</div>
												<!-- /div.col-md-6 -->

												<div class="col-md-6">
													<input type="text" id="meter-elec-old-${tank.id}" name="meter-elec-old-${tank.id}" class="form-control android" placeholder="Chỉ số cũ" maxlength="10" required />
												</div>
												<!-- /div.col-md-6 -->
											</div>
											<!-- /div.col-md-5.padding-0 -->
										</div>
										<!-- /div.form-group -->
									</div>
									<!-- /div.col-md-12.padding-0 -->

									<div class="col-md-12 padding-0">
										<div class="form-group">
											<label class="col-md-2 control-label">Bán - Thành tiền</label>

											<div class="col-md-5 padding-0">
												<div class="col-md-6">
													<p id="meter-quantity-${tank.id}" class="form-control-static">0</p>
												</div>
												<!-- /div.col-md-6 -->

												<div class="col-md-6">
													<p id="amount-${tank.id}" class="form-control-static">0</p>
												</div>
												<!-- /div.col-md-6 -->
											</div>
											<!-- /div.col-md-5.padding-0 -->

											<div class="col-md-5 padding-0">
												<div class="col-md-6">
													<p id="meter-elec-quantity-${tank.id}" class="form-control-static">0</p>
												</div>
												<!-- /div.col-md-6 -->

												<div class="col-md-6">
													<p id="amount-elec-${tank.id}" class="form-control-static">0</p>
												</div>
												<!-- /div.col-md-6 -->
											</div>
											<!-- /div.col-md-5.padding-0 -->
										</div>
										<!-- /div.form-group -->
									</div>
									<!-- /div.col-md-12.padding-0 -->

									<div class="col-md-12 padding-0">
										<div class="form-group">
											<label class="col-md-2 control-label">Chênh lệch</label>

											<div class="col-md-5">
												<p id="different-${tank.id}" class="form-control-static">0</p>
											</div>
											<!-- /div.col-md-5 -->

											<div class="col-md-5 padding-0">
												<div class="col-md-6">
													<div class="form-animate-checkbox">
														<input type="checkbox" id="ignore-${tank.id}" class="checkbox" />
														<label> Bỏ qua trụ này</label>
													</div>
													<!-- /div.form-animate-checkbox -->
												</div>
												<!-- /div.col-md-6 -->

												<div class="col-md-6">
													<div class="price-select form-animate-checkbox">
														<input type="checkbox" id="price-select-${tank.id}" name="price-select-${tank.id}" class="checkbox" />
														<label> Lấy giá mới nhất</label>
													</div>
													<!-- /div.price-select.form-animate-checkbox -->
												</div>
												<!-- /div.col-md-6 -->
											</div>
											<!-- /div.col-md-5.padding-0 -->
										</div>
										<!-- /div.form-group -->
									</div>
									<!-- /div.col-md-12.padding-0 -->
								</div>
								<!-- /div#tank-content-${tank.id} -->
							</fieldset>
							<!-- /fieldset#tank-${tank.id} -->
						</form>
						<!-- /form#daily-form-${tank.id} -->
					</c:forEach>
				</div>
				<!-- /div.row.margin-0 -->
			</div>
			<!-- /div.modal-body -->

			<div class="modal-footer">
				<form class="form-inline" action="" method="post" autocomplete="off">
					<div class="form-group form-animate-radio pull-left">
						<c:forEach var="shift" items="<%= Shifts.values() %>">
							<c:if test="${shift.code != 0}">
								<label class="radio margin-0">
									<input type="radio" id="shift-insert-${shift.code}" name="shift-insert" value="${shift.code}" required />
									<span class="outer"><span class="bg-primary inner"></span></span> ${shift.value}
								</label>
							</c:if>
						</c:forEach>
					</div>
					<!-- /div.form-animate-radio.pull-left -->
				</form>
				<!-- /form.form-horizontal -->

				<button type="button" id="btn-daily-action" class="btn btn-gradient"></button>
				<button type="button" class="btn btn-gradient btn-default" data-dismiss="modal">Không</button>
			</div>
			<!-- /div.modal-footer -->
		</div>
		<!-- /div.modal-content -->
	</div>
	<!-- /div.modal-dialog.modal-lg -->
</div>
<!-- /div#modal-daily-form -->