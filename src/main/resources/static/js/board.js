let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		
		$("#btn-delete").on("click", () => {
			this.deleteById();//예약어여서 delete는 지양하자
		});
		
		$("#btn-update").on("click", () => {
			this.update();
		});
		
		$("#btn-reply-save").on("click", () => {
			this.replySave();
				
		});

	},
	save: function() {
		//alert('user의 함수가 호출됨');
		let data = {
			title: $("#title").val(),
			content: $("#content").val()

		};
		//console.log(data);
		//ajax통신을 이용해서 3개의 파라미터 데이터를 제이슨으로 변경하여 insert요청
		//ajax호출시 default가 비동기호출
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),//이렇게 던지면 자바가 이해를 못해서 json을 변경 후 전송.
			contentType: "application/json;charset=utf-8",//바디의 데이터타입이 무엇인지(MIME)
			dataType: "json"//요청을 서버로해서 응답이 왔을 때, 기본적인 모든것이 문자열.생긴게 json이면 js오브젝트로 변경해준다.
		}).done(function(resp) {//컨트롤러의 리턴값.
			alert("글쓰기 등록완료.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

	deleteById: function() {

		let id = $("#id").text();

		$.ajax({


			type: "DELETE",
			url: "/api/board/" + id,
			contentType: "application/json;charset=utf-8",
			dataType: "json"//요청을 서버로해서 응답이 왔을 때, 기본적인 모든것이 문자열.생긴게 json이면 js오브젝트로 변경해준다.
		}).done(function(resp) {//컨트롤러의 리턴값.
			alert("삭제완료");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

	update: function() {
		
		let id = $("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
			
		};
		

		$.ajax({
			type: "PUT",
			url: "/api/board/" + id,
			data: JSON.stringify(data),//이렇게 던지면 자바가 이해를 못해서 json을 변경 후 전송.
			contentType: "application/json;charset=utf-8",//바디의 데이터타입이 무엇인지(MIME)
			dataType: "json"//요청을 서버로해서 응답이 왔을 때, 기본적인 모든것이 문자열.생긴게 json이면 js오브젝트로 변경해준다.
		}).done(function(resp) {//컨트롤러의 리턴값.
			alert("글수정완료.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	
	replySave: function() {
		//alert('user의 함수가 호출됨');
		let data = {
			boardId:$("#boardId").val(),
			userId:$("#userId").val(),
			content:$("#reply-content").val()
		
		};
		
		
	
		$.ajax({
			type: "POST",
			url: `/api/board/${data.boardId}/reply`,
			data: JSON.stringify(data),//이렇게 던지면 자바가 이해를 못해서 json을 변경 후 전송.
			contentType: "application/json;charset=utf-8",//바디의 데이터타입이 무엇인지(MIME)
			dataType: "json"//요청을 서버로해서 응답이 왔을 때, 기본적인 모든것이 문자열.생긴게 json이면 js오브젝트로 변경해준다.
		}).done(function(resp) {//컨트롤러의 리턴값.
			alert("댓글등록완료.");
			location.href =`/board/${data.boardId}`;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	
	replyDelete: function(boardId, replyId) {
	
		$.ajax({
			type: "DELETE",
			url: `/api/board/${boardId}/reply/${replyId}`,
			dataType: "json"//요청을 서버로해서 응답이 왔을 때, 기본적인 모든것이 문자열.생긴게 json이면 js오브젝트로 변경해준다.
		}).done(function(resp) {//컨트롤러의 리턴값.
			alert("댓글삭제완료.");
			location.href =`/board/${boardId}`;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

}

index.init();