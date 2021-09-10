<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>


<div class=container>
	<form>
		<input type="hidden" id="id" value="${principal.user.id}"></input>
		<div class="form-group">
			<label for="Username">UserName:</label> <input value="${principal.user.username}" type="text" class="form-control" placeholder="Enter Username" id="username" readonly="readonly">
		</div>
		<c:choose>
		<c:when test="${not empty principal.user.oauth}">
		<div class="form-group">
			<label for="password">Password:</label> <input value="${principal.user.password}" type="password" class="form-control" placeholder="Enter password" id="password" readonly="readonly" >
		</div>
		<div class="form-group">
			<label for="email">Email address:</label> <input value="${principal.user.email}" type="email" class="form-control" placeholder="Enter email" id="email" readonly="readonly">
		</div>
		</c:when>
		<c:otherwise>
		<div class="form-group">
			<label for="password">Password:</label> <input value="${principal.user.password}" type="password" class="form-control" placeholder="Enter password" id="password"  >
		</div>
		<div class="form-group">
			<label for="email">Email address:</label> <input value="${principal.user.email}" type="email" class="form-control" placeholder="Enter email" id="email" >
		</div>
		
		</c:otherwise>
		
		</c:choose>
		


	</form>
	<!-- 버튼을 폼태그 안에서 빼주자. -->
	
			<div id="btn-update" class="btn btn-primary">회원수정완료</div>
			<div id="btn-delete" class="btn btn-danger ">회원탈퇴</div>
	
</div>


<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
