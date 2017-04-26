<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-default header navbar-fixed-top">
	<div class="col-md-12 nav-wrapper">
		<div class="navbar-header nav-justified">
			<div class="opener-left-menu is-open">
				<span class="top"></span>
				<span class="middle"></span>
				<span class="bottom"></span>
			</div>
			<!-- /div.opener-left-menu.is-open -->

			<a href="${home}" class="navbar-brand text-uppercase"><b>Quản lý trạm xăng</b></a>

			<ul class="nav navbar-nav navbar-right user-nav">
				<li class="user-name"><span>${usernameLoggedIn}</span></li>
				<li class="dropdown avatar-dropdown">
					<img src="<c:url value='/static/img/avatar.jpg' />" class="img-circle avatar" alt="${loggedInUser}" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" />

					<ul class="dropdown-menu user-dropdown">
						<li>
							<a href="javascript:void(0);"><i class="fa fa-user" aria-hidden="true"></i> Thông tin cá nhân</a>
						</li>
						<li>
							<a href="javascript:void(0);"><i class="fa fa-calendar" aria-hidden="true"></i> Lịch làm việc</a>
						</li>
						<li role="separator" class="divider"></li>
						<li class="more">
							<ul>
								<li>
									<a href=""><i class="fa fa-cogs" aria-hidden="true"></i></a>
								</li>
								<li>
									<a href=""><i class="fa fa-lock" aria-hidden="true"></i></a>
								</li>
								<li>
									<a href=""><i class="fa fa-power-off " aria-hidden="true"></i></a>
								</li>
							</ul>
							<!-- /ul -->
						</li>
					</ul>
					<!-- /ul.dropdown-menu.user-dropdown -->
				</li>
			</ul>
			<!-- /ul.nav.navbar-nav.navbar-right.user-nav -->
		</div>
		<!-- /div.navbar-header.nav-justified -->
	</div>
	<!-- /div.col-md-12.nav-wrapper -->
</nav>
<!-- /nav.navbar.navbar-default.header.navbar-fixed-top -->