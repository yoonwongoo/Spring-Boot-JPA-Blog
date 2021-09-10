<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">



	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	
	
	<c:if test ="${board.user.id == principal.user.id}">
	<button id="btn-delete"class="btn btn-danger">삭제</button>
	<a  href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a> 
	</c:if>
	
	
	<br />
	<br />
	<br />
	
	<div>
	글번호 : <span id="id"><b>${board.id}</b></span>
	작성자 :<span><b> ${board.user.username}</b></span>
	</div>
	
	<br />
	
	<div class="form-group">
		<label for="Username">Title</label>
		<h3>${board.title}</h3>
	</div>
	<hr />
	<div class="form-group">
		<label for="content">Content</label>
		<div>${board.content}</div>
	</div>
	<hr />

	<div class ="card">
	
			<div class ="card-body"><textarea class="form-control" rows="1"></textarea></div>
			<div class ="card-footer"><button class ="btn btn-primary">등록</button></div>

	</div>
	</br>
	<div class ="card">
	
		<div class ="card-header">댓글 리스트</div>
		
			<ul class = "list-group" id="reply--box">
			
				<c:forEach var="reply" items="${board.replys}">
				
					<li class ="list-group-item d-flex justify-content-between" id="reply--1">
				
					<div>${reply.content}</div>
					<div class = "d-flex">
						<div class="font-italic">작성자:${reply.user.username} &nbsp</div>
						<button class="badge">삭제</button>
					</div>
				</li>
				
				</c:forEach>
			
			</ul>
		
	</div>
<script src="/js/board.js"></script>

<%@ include file="../layout/footer.jsp"%>