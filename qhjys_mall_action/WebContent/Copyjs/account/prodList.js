$(function() {
	var keywork = $("#keyW").val();
	if (/^[\u4e00-\u9fa5]+$/i.test(keywork))
		$("#keyW").val(encodeURI(encodeURI(keywork)));
	if ($("#hMaxu").val() != "") {
		$("#userNum").children().removeClass("all");
		$("input[value=" + $("#hMaxu").val() + "]", "#userNum").parent().addClass("all");
	}
	$("a", "#search_type").on("click", function(e) {
		var url = "searchProduct.do";
		$(this).siblings().removeClass("all");
		$(this).addClass("all");
		var sort = $("#sort"), area = $("#area"), userNum = $("#userNum");
		var vArea = area.find("a.all>input").val();
		var vUser = userNum.find("a.all>input").val();
		url += "?type=" + $("#hType").val();
		if ($("#keyW").val() != "")
			url += "&keywork=" + $("#keyW").val();
		if ($("#hCate").val() != "")
			url += "&category=" + $("#hCate").val();
		if (sort.length > 0)
			url += "&sort=" + sort.find("a.all>input").val();
		if (vArea && vArea != "")
			url += "&area=" + vArea;
		if (vUser && vUser != "")
			url += "&maxUse=" + vUser;
		window.location.href = url;
	});
	$("#sortField a").on("click", function() {
		var url = window.location.href;
		var par = $(this).parent().attr("lang");
		window.location.href = changeURLPar(url, "clause", par);
	});
	$("#priceRange").on("change", function() {
		var url = window.location.href;
		var par = $(this).children('option:selected').val();
		window.location.href = changeURLPar(url, "priceRange", par);
	});
});

function changeURLPar(destiny, param, value) {
	if (destiny.indexOf(param) > 0) {
		var re = eval('/(' + param + '=)([^&]*)/gi');
		var destiny = destiny.replace(re, param + '=' + value);
		return destiny;
	} else
		return destiny + "&" + param + "=" + value;
}