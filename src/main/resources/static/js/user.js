let index = {
	init: function() {
		$("#btn-save").on("click", () => {           //function(){}, ()=>{} this를 바인딩하기 위해서
			this.save();
		});
		
	},
	save:function() {
		//alert('user의 함수가 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		//console.log(data);
		//ajax통신을 이용해서 3개의 파라미터 데이터를 제이슨으로 변경하여 insert요청
		//ajax호출시 default가 비동기호출
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data),//이렇게 던지면 자바가 이해를 못해서 json을 변경 후 전송.
			contentType: "application/json;charset=utf-8",//바디의 데이터타입이 무엇인지(MIME)
			dataType: "json"//요청을 서버로해서 응답이 왔을 때, 기본적인 모든것이 문자열.생긴게 json이면 js오브젝트로 변경해준다.
		}).done(function(resp) {//컨트롤러의 리턴값.
			alert("회원가입이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},


}

index.init();