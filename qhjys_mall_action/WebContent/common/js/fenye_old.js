function goPageXX(type, pagecount,targetpage) {
//	var targetpage = parseInt($("#targetpage")[0].value);
	switch (type) {
	case 1:
		if (targetpage == 1) {
			alert("已经是第一页！");
			return;
		} else
			$("#targetpage")[0].value = 1;
		break;
	case 2:
		if (targetpage > 1) {
			targetpage--;
			$("#targetpage")[0].value = targetpage;
		} else {
			alert("已经是第一页！");
			return;
		}
		break;
	case 3:
		if (targetpage < pagecount) {
			targetpage++;
			$("#targetpage")[0].value = targetpage;
		} else {
			alert("已经最后一页！");
			return;
		}

		break;
	case 4:
		if (targetpage == pagecount) {
			alert("已经最后一页！");
			return;
		} else {
			$("#targetpage")[0].value = pagecount;
		}
		break;
	}
	$('#dataForm').submit();
}