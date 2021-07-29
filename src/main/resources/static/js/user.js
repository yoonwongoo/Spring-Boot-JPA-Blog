let index ={
	init:function(){       
		$("#btn-save").on("click",()=>{
			this.save();		
	});	
},
	save:function(){
		//alert('user의 함수가 호출됨');
		let data ={
			username:$(username).val(),
			password:$(password).val(),
			email:$(email).val()
		} 
		console.log(data);
	} 
}

index.init();