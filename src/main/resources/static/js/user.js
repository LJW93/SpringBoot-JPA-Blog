let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
	},

	save: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		//console.log(data);

		// ajax 호출시 default가 비동기 호출
		$.ajax({
			type: "POST",
			url: "/auth/joinProc", // 메서드가 Post라서 join을 따로 적지않음
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8",
			dataType: "json" // 요청을 서버로해서  응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascript 오브젝트로 변경
		}).done(function(resp) {
			alert("회원가입이 완료되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		}); // ajax 통신을 해서 3개의 데이터를 json으로 변경하여 insert 요청을 함
	}
}

index.init();