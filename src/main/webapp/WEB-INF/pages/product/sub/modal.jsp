<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="modal-category-form" class="modal fade" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span class="fa fa-times" aria-hidden="true"></span></button>
				<h4 class="modal-title text-white"></h4>
			</div>
			<!-- /div.modal-header -->

			<div class="modal-body">
				<div class="row">
					<form class="form-horizontal" action="" method="post" autocomplete="off">
						<div class="col-md-12">
							<div class="form-group">
								<label class="col-md-3 control-label">Tên hàng hóa</label>

								<div class="col-md-9">
									<input type="text" id="category-name" name="category-name" class="form-control android" maxlength="20" required />
								</div>
								<!-- /div.col-md-9 -->
							</div>
							<!-- /div.form-group -->
						</div>
						<!-- /div.col-md-12 -->
					</form>
					<!-- /form.form-horizontal -->
				</div>
				<!-- /div.row -->
			</div>
			<!-- /div.modal-body -->

			<div class="modal-footer">
				<button type="button" id="btn-category-action" class="btn btn-gradient"></button>
				<button type="button" class="btn btn-gradient btn-default" data-dismiss="modal">Không</button>
			</div>
			<!-- /div.modal-footer -->
		</div>
		<!-- /div.modal-content -->
	</div>
	<!-- /div.modal-dialog -->
</div>
<!-- /div#modal-category-form -->

<div id="modal-product-form" class="modal fade" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span class="fa fa-times" aria-hidden="true"></span></button>
				<h4 class="modal-title text-white"></h4>
			</div>
			<!-- /div.modal-header -->

			<div class="modal-body">
				<div class="row">
					<form class="form-horizontal" action="" method="post" autocomplete="off">
						<div class="col-md-12">
							<div class="form-group">
								<label class="col-md-3 control-label">Chọn hàng hóa</label>

								<div class="col-md-9">
									<p id="category-select-show" class="form-control-static hide"></p>
									<select id="category-select" name="category-select" class="form-control" required></select>
								</div>
								<!-- /div.col-md-9 -->
							</div>
							<!-- /div.form-group -->
						</div>
						<!-- /div.col-md-12 -->

						<div class="col-md-12">
							<div class="form-group">
								<label class="col-md-3 control-label">Tên hạng mục</label>

								<div class="col-md-9">
									<input type="text" id="product-name" name="product-name" class="form-control android" maxlength="20" required />
								</div>
								<!-- /div.col-md-9 -->
							</div>
							<!-- /div.form-group -->
						</div>
						<!-- /div.col-md-12 -->

						<div class="col-md-12">
							<div class="form-group">
								<label class="col-md-3 control-label">Giá mới nhất</label>

								<div class="col-md-9">
									<input type="text" id="product-price" name="product-price" class="form-control android" maxlength="10" required />
								</div>
								<!-- /div.col-md-9 -->
							</div>
							<!-- /div.form-group -->
						</div>
						<!-- /div.col-md-12 -->
					</form>
					<!-- /form.form-horizontal -->
				</div>
				<!-- /div.row -->
			</div>
			<!-- /div.modal-body -->

			<div class="modal-footer">
				<button type="button" id="btn-product-action" class="btn btn-gradient"></button>
				<button type="button" class="btn btn-gradient btn-default" data-dismiss="modal">Không</button>
			</div>
			<!-- /div.modal-footer -->
		</div>
		<!-- /div.modal-content -->
	</div>
	<!-- /div.modal-dialog -->
</div>
<!-- /div#modal-product-form -->

<div id="modal-confirm-delete" class="modal fade" tabindex="-1" role="dialog">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header bg-danger">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span class="fa fa-times" aria-hidden="true"></span></button>
				<h4 class="modal-title text-white">Xác nhận</h4>
			</div>
			<!-- /div.modal-header.bg-danger -->

			<div class="modal-body">
				<p>Chắc chắn xóa dòng này ?</p>
			</div>
			<!-- /div.modal-body -->

			<div class="modal-footer">
				<button type="button" id="btn-delete" class="btn btn-gradient btn-danger">Có</button>
				<button type="button" class="btn btn-gradient btn-default" data-dismiss="modal">Không</button>
			</div>
			<!-- /div.modal-footer -->
		</div>
		<!-- /div.modal-content -->
	</div>
	<!-- /div.modal-dialog.modal-sm -->
</div>
<!-- /div#modal-confirm-delete -->