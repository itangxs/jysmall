function quxiao(){
	window.location.href = "/managermall/systemmall/ad/hongbao_list.do";
}

function xiugai(id){
	window.location.href = "/managermall/systemmall/ad/hongbao_modify.do?redpackId="+id;
}

function excel() {
	var b = $("#from").serialize();
	var a = "/managermall/systemmall/ad/sendExcel.do?" + b;
	window.open(a)
};