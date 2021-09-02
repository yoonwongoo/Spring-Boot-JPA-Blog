<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>


<div class=container>
	<form>
		<div class="form-group">
			<label for="Username">UserName:</label> <input value="${principal.user.username}" type="text" class="form-control" placeholder="Enter Username" id="username">
		</div>

		<div class="form-group">
			<label for="password">Password:</label> <input value="${principal.user.password}"type="password" class="form-control" placeholder="Enter password" id="password">
		</div>

		<div class="form-group">
			<label for="email">Email address:</label> <input value="${principal.user.email}" type="email" class="form-control" placeholder="Enter email" id="email">
		</div>


	</form>
	<!-- 버튼을 폼태그 안에서 빼주자. -->
	<button id="btn-save" class="btn btn-primary">회원수정완료</button>


</div>


<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
