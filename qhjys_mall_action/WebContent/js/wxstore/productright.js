var productcookie = "";
var productNum = 0;
var producttotalprice = 0;
$(function(){
	
	$(".iframe_left li").click(function(){
		$(".iframe_left li").removeClass("current");
		$(this).addClass("current");
	})
	
	var storeId = $("#storeId").val();
	if ( $.cookie('productcookie'+storeId) != null) {
		productcookie = $.cookie('productcookie'+storeId);
	}
	if ( $.cookie('productNum'+storeId) != null) {
		productNum = $.cookie('productNum'+storeId);
	}
	if ( $.cookie('producttotalprice'+storeId) != null) {
		producttotalprice = $.cookie('producttotalprice'+storeId);
	}
	var json = $.parseJSON(productcookie);
	$.each(json, function(key, value) {
		if (value >0) {
			$("#numberinput"+key).val(value);
			$("#div1"+key).hide();
			$("#div2"+key).show();
			$("#btn"+key).attr("checked","checked");
			$("#btn"+key).parent('label').removeClass('c_on');
		}else{
			$("#numberinput"+key).val(0);
			$("#btn"+key).parent('label').addClass('c_on');
		}
		}); 
	$("#howmany").html(productNum);
	$("#howmuch").html(parseFloat(producttotalprice).toFixed(2));
	
});
function showselect(id){
	if ($("#btn"+id).is(':checked')) {
		var stock = $("#productstock"+id).val();
		if (parseInt(stock)>1) {
			$("#numberinput"+id).val(1);
			$("#div1"+id).hide();
			$("#div2"+id).show();
			$("#howmany").html(parseInt($("#howmany").html())+1);
			var howmuch = parseFloat(parseFloat($("#howmuch").html())+parseFloat($("#productprice"+id).val())).toFixed(2);
			$("#howmuch").html(howmuch);
		}else{
			$("#btn"+id).removeAttr("checked");
			$("#btn"+id).parent('label').addClass('c_on');
			alert("该菜品库存不足");
		}
	}else{
		$("#div1"+id).show();
		$("#div2"+id).hide();
		$("#howmany").html(parseInt($("#howmany").html())-$("#numberinput"+id).val());
		var howmuch = parseFloat(parseFloat($("#howmuch").html())-$("#productprice"+id).val()*$("#numberinput"+id).val()).toFixed(2);
		$("#howmuch").html(howmuch);
		$("#numberinput"+id).val(0);
	}
}

function minusnum(id){
	$("#numberinput"+id).val(parseInt($("#numberinput"+id).val()) - 1);
	if ($("#numberinput"+id).val() == 0) {
		$("#div1"+id).show();
		$("#div2"+id).hide();
		$("#btn"+id).removeAttr("checked");
		$("#btn"+id).parent('label').addClass('c_on');
	}
	$("#howmany").html(parseInt($("#howmany").html())-1);
	var howmuch = parseFloat($("#howmuch").html())-$("#productprice"+id).val()*1;
	$("#howmuch").html(parseFloat(howmuch).toFixed(2));
}

function plusnum(id){
	var num = $("#numberinput"+id).val();
	var stock = $("#productstock"+id).val();
	if (parseInt(stock)<=parseInt(num)) {
		alert("该商品库存不足");
	}else{
		$("#numberinput"+id).val(parseInt($("#numberinput"+id).val()) + 1);
		$("#howmany").html(parseInt($("#howmany").html())+1);
		var howmuch = parseFloat($("#howmuch").html())+$("#productprice"+id).val()*1;
		$("#howmuch").html(parseFloat(howmuch).toFixed(2));
	}
}

function commitproduct(storeId){
	var ids = "";
	var nums = "";
	var productcookiemap = "{";
	var pronums = 0;
	var prototalprice = 0;
	$('input[name="sample-checkbox-01"]:checked').each(function(){
		var proid = $(this).val();
		ids+=proid+",";
		var pronum = $("#numberinput"+proid).val();
		nums+=pronum+",";
		productcookiemap +="\""+proid+"\":\""+pronum+"\",";
		pronums+=parseInt(pronum);
		var proprice= $("#productprice"+proid).val();
		prototalprice+=parseFloat(proprice*pronum);
		});
	prototalprice = parseFloat(prototalprice).toFixed(2);
	productcookiemap = productcookiemap.substring(0,productcookiemap.length-1);
	productcookiemap+="}";
	$.cookie("productcookie"+storeId, productcookiemap,  {path:'/'}); 
	$.cookie("productNum"+storeId, pronums, {path:'/'}); 
	$.cookie("producttotalprice"+storeId, prototalprice, {path:'/'}); 
	ids = ids.substring(0,ids.length-1);
	nums = nums.substring(0,nums.length-1);
	if (ids.length > 0) {
		window.location.href = "/user/fqcart/products.do?storeId="+storeId+"&ids="+ids+"&nums="+nums;
	}else{
		alert("请选择菜品");
	}
}

function dishesdetail(storeId,prodId) {
	var productcookiemap = "{";
	var pronums = 0;
	var prototalprice = 0;
	$('input[name="sample-checkbox-01"]:checked').each(function(){
		var proid = $(this).val();
		var pronum = $("#numberinput"+proid).val();
		productcookiemap +="\""+proid+"\":\""+pronum+"\",";
		pronums+=parseInt(pronum);
		var proprice= $("#productprice"+proid).val();
		prototalprice+=parseFloat(proprice*pronum);
		});
	productcookiemap = productcookiemap.substring(0,productcookiemap.length-1);
	prototalprice = parseFloat(prototalprice).toFixed(2);
	if (productcookiemap.length >1) {
		productcookiemap+="}";
	}
	$.cookie("productcookie"+storeId, productcookiemap,  {path:'/'}); 
	$.cookie("productNum"+storeId, pronums, {path:'/'}); 
	$.cookie("producttotalprice"+storeId, prototalprice, {path:'/'}); 
	var currentNum = $("#numberinput"+prodId).val();
	window.location.href = "/store/fqproducts/dishesdetail.do?storeId="+storeId+"&prodId="+prodId+"&currentNum="+currentNum;
}

//function leftselect(){
//	alert($(".iframe_left li"))
//	$(".iframe_left li").removeClass("current");
//	alert($(this).attr("href"))
//	$(this).parent('li').addClass("current");
//}

