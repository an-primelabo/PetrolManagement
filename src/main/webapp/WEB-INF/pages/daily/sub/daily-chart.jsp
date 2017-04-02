<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="daily-chart">
	<div id="daily-chart-modal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">Thống kê <span></span></h4>
				</div>
				<!-- /div.modal-header -->

				<div class="modal-body">
					<div class="row">
						<form id="form-daily-chart" class="form-horizontal" action="" method="post" autocomplete="off">
							<div class="col-md-12">
								<div class="col-md-4">
									<div class="form-group">
										<label class="col-md-6 control-label">Xem thống kê: </label>

										<div class="col-md-6">
											<div class="input-group-btn">
												<button type="button" id="dropdown-chart-type" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="text-change">Chọn</span> <span class="caret"></span></button>
												<ul class="dropdown-menu" aria-labelledby="dropdown-chart-type">
													<li><a href="#" id="chart-type-day">Theo ngày</a></li>
													<li><a href="#" id="chart-type-month">Theo tháng</a></li>
												</ul>
											</div>
											<!-- /div.input-group-btn -->
										</div>
										<!-- /div.col-md-6 -->
									</div>
									<!-- /div.form-group -->
								</div>
								<!-- /div.col-md-4 -->
	
								<div class="col-md-8">
									<div class="form-group">
										<div class="col-md-5">
											<input type="date" id="chart-type-day-from" name="chart-type-day-from" class="form-control animated fadeIn hide" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />" required />
											<input type="month" id="chart-type-month-from" name="chart-type-month-from" class="form-control animated fadeIn hide" value="<fmt:formatDate pattern="yyyy-MM" value="${now}" />" required />
										</div>
										<!-- /div.col-md-5.col-md-offset-1 -->
	
										<label class="col-md-1 control-label"> ~ </label>
	
										<div class="col-md-5">
											<input type="date" id="chart-type-day-to" name="chart-type-day-to" class="form-control animated fadeIn hide" />
											<input type="month" id="chart-type-month-to" name="chart-type-month-to" class="form-control animated fadeIn hide" />
										</div>
										<!-- /div.col-md-5 -->
									</div>
									<!-- /div.form-group -->
								</div>
								<!-- /div.col-md-8 -->
							</div>
							<!-- /div.col-md-12 -->

							<div class="col-md-12">
								<div id="chart-view" class="form-animate-radio text-center hide">
									<label class="radio">
										<input type="radio" id="chart-view-amount" name="chart-view" value="1" />
										<span class="outer"><span class="bg-primary inner"></span></span> Xem thành tiền (Trụ)
									</label>

									<label class="radio">
										<input type="radio" id="chart-view-amount-elec" name="chart-view" value="2" />
										<span class="outer"><span class="bg-primary inner"></span></span> Xem thành tiền (Điện Tử)
									</label>

									<label class="radio">
										<input type="radio" id="chart-view-different" name="chart-view" value="3" checked />
										<span class="outer"><span class="bg-primary inner"></span></span> Xem chênh lệch
									</label>
								</div>
								<!-- /div#chart-view -->
							</div>
							<!-- /div.col-md-12 -->
						</form>
						<!-- /form#form-daily-chart -->

						<div id="chart-donut" class="col-md-12 animated fadeIn hide">
							<div class="col-md-6">
								<div class="panel panel-default box-shadow-none">
									<div class="panel-heading bg-white border-none text-center">
										<h3>Ca Sáng</h3>
									</div>
									<!-- /div.panel-heading.bg-white.border-none.text-center -->

									<div class="panel-body">
										<div id="chart-donut-morning"></div>
										<!-- /div#chart-donut-morning -->
									</div>
									<!-- /div.panel-body -->
								</div>
								<!-- /div.panel.panel-default.box-shadow-none -->
							</div>
							<!-- /div.col-md-6 -->

							<div class="col-md-6">
								<div class="panel panel-default box-shadow-none">
									<div class="panel-heading bg-white border-none text-center">
										<h3>Ca Tối</h3>
									</div>
									<!-- /div.panel-heading.bg-white.border-none.text-center -->

									<div class="panel-body">
										<div id="chart-donut-evening"></div>
										<!-- /div#chart-donut-evening -->
									</div>
									<!-- /div.panel-body -->
								</div>
								<!-- /div.panel.panel-default.box-shadow-none -->
							</div>
							<!-- /div.col-md-6 -->
						</div>
						<!-- /div#chart-donut -->
	
						<div id="chart-line" class="col-md-12 animated fadeIn hide">
							<div id="chart-multi" class="animated fadeIn"></div>
							<!-- /div#chart-multi -->
						</div>
						<!-- /div#chart-line -->
					</div>
					<!-- /div.row -->
				</div>
				<!-- /div.modal-body -->

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary">Save changes</button>
				</div>
				<!-- /div.modal-footer -->
			</div>
			<!-- /div.modal-content -->
		</div>
		<!-- /div.modal-dialog.modal-lg -->
	</div>
	<!-- /div#daily-chart-modal -->
</div>
<!-- /div#daily-chart -->