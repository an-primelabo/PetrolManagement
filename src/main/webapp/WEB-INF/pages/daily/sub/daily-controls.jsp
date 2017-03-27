<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ah.petrolmanagement.enums.Shifts"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="daily-controls" class="col-md-5 padding-0">
	<div class="col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3>Bảng chức năng</h3>
			</div>
			<!-- /div.panel-heading -->

			<div class="panel-body text-center">
				<div class="form-animate-radio">
					<c:forEach var="shift" items="<%= Shifts.values() %>">
						<label class="radio">
							<input type="radio" id="shift-${shift.code}" name="shift" value="${shift.code}" />
							<span class="outer"><span class="bg-primary inner"></span></span> ${shift.value}
						</label>
					</c:forEach>
				</div>
				<!-- /div.form-animate-radio -->

				<div class="form-element">
					<div class="input-group">
						<span id="daily-search-addon" class="input-group-addon bg-primary"><i class="fa fa-calendar text-white" aria-hidden="true"></i></span>
						<input type="date" id="daily-search" class="form-control" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />" aria-describedby="daily-search-addon" />
					</div>
					<!-- /div.input-group -->
				</div>
				<!-- /div.form-element -->
	
				<div class="top-20">
					<button type="button" id="btn-daily-insert-new" class="btn btn-gradient btn-primary"><i class="fa fa-plus" aria-hidden="true"></i> Thêm số liệu</button>
					<button type="button" id="btn-daily-search" class="btn btn-gradient btn-success"><i class="fa fa-money" aria-hidden="true"></i> Kiểm tiền</button>
					<button type="button" class="btn btn-gradient btn-danger"><i class="fa fa-bar-chart" aria-hidden="true"></i> Báo cáo</button>
				</div>
				<!-- /div.top-20 -->
			</div>
			<!-- /div.panel-body.text-center -->
		</div>
		<!-- /div.panel.panel-default -->
	</div>
	<!-- /div.col-md-12 -->
</div>
<!-- /div#daily-controls -->