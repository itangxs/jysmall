var productcookie = null;
var productNum = 0;
var producttotalprice = 0;
var storeId = 0;
var productId = 0;
$(document).ready(function() {
	storeId = $("#storeId").val();
	productId = $("#productId").val();
	if ( $.cookie('productcookie'+storeId) != null) {
		productcookie = $.cookie('productcookie'+storeId);
	}
	if ( $.cookie('productNum'+storeId) != null) {
		productNum = $.cookie('productNum'+storeId);
	}
	if ( $.cookie('producttotalprice'+storeId) != null) {
		producttotalprice = $.cookie('producttotalprice'+storeId);
	}
	if (productcookie != null) {
		var json = $.parseJSON(productcookie);
		$.each(json, function(key, value) {
			if (key == productId) {
				$("#currentNum").val(value);
				$("#numberinput"+key).val(value);
				$("#howmany").html(value);
				$("#howmuch").html(parseFloat($("#price"+key).val()*value).toFixed(2));
			}
		}); 
	}
});

function detailminusnum(id){
	var num = $("#numberinput"+id).val();
	if(num > 0){
		var howmuch = parseFloat($("#price"+id).val()*(num-1)).toFixed(2);
		$("#numberinput"+id).val(num-1);
		$("#howmany").html(num - 1);
		$("#howmuch").html(howmuch);
	}
}

function detailplusnum(id){
	var num = parseInt($("#numberinput"+id).val())+1;
	var stock = $("#stock"+id).val();
	if (num>parseInt(stock)) {
		alert("该商品库存不足");
	}else{
		var howmuch =  parseFloat($("#price"+id).val()*(num)).toFixed(2);
		$("#numberinput"+id).val(num);
		$("#howmany").html(num);
		$("#howmuch").html(howmuch);
	}
}


function dishesdetailcart(storeId) {
	var num = parseInt($("#numberinput"+productId).val());
	if (productcookie !=null && productcookie !="") {
		var json = $.parseJSON(productcookie);
		json[productId]=num;
		productcookie = JSON.stringify(json);
	}else{
		productcookie +="{\""+productId+"\":\""+num+"\"}";
	}
	
	var currentNum = parseInt($("#currentNum").val());
	var price = parseFloat($("#price"+productId).val());
	productNum = parseInt(productNum)+num-currentNum;
	
	producttotalprice =  parseFloat(producttotalprice) + price*(num-currentNum);
	producttotalprice = parseFloat(producttotalprice).toFixed(2);
	$.cookie("productcookie"+storeId, productcookie,  {path:'/'}); 
	$.cookie("productNum"+storeId, productNum, {path:'/'}); 
	$.cookie("producttotalprice"+storeId, producttotalprice, {path:'/'}); 
	window.location.href = "/store/fqproducts/onlineorder.do?storeId="+storeId;
}


