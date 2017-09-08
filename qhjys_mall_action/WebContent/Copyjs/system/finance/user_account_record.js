function Excel() {
	var ser = $("#from").serialize();
	var urlval = '/managermall/systemmall/finance/userAccountRecordListExcel.do?'+ser;
	window.open(urlval);
}