$(function() {
	var keywork = $("#keyW").val();
	if (/^[\u4e00-\u9fa5]+$/i.test(keywork))
		$("#keyW").val(encodeURI(encodeURI(keywork)));
	if ($("#hMaxu").val() != "") {
		$("#userNum").children().removeClass("all");
		$("input[value=" + $("#hMaxu").val() + "]", "#userNum").parent().addClass("all");
	}
	$("a", "#search_type").on("click", function(e) {
		var url = "searchStore.do";
		$(this).siblings().removeClass("all");
		$(this).addClass("all");
		url += "?keywork=" + $("#keyW").val();
		var category = $("#category").find("a.all>input").val();
		var area = $("#area").find("a.all>input").val();
		if (category != "")
			url = changeURLPar(url, "category", category);
		if (area != "")
			url = changeURLPar(url, "area", area);
		window.location.href = url;
	});
	$("#sortField a").on("click", function() {
		var url = window.location.href;
		var par = $(this).parent().attr("lang");
		window.location.href = changeURLPar(url, "clause", par);
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