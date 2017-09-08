/*
 * @date 2012-5-18
 * @author yufeng.zhu
 * @desciption TR选中高亮
 */
var SelectLightRows = {
	/** 当鼠标点击时添加 高亮显示的css 传table id */
	selectdlightRows : function(ele) {
		$("#" + ele).find("tbody tr").bind("click",function(){
			$(this).siblings().find("td").removeClass("selectd_light")
							.end().end().find("td").addClass("selectd_light")
		})
	}
}