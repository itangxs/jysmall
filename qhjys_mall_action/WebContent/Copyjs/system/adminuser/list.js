function changeGroup(valueid,userid){
	var param = 'id='+userid+'&valuezhi='+valueid;
	var urlval = '/managermall/systemmall/user/adminUserGroupAjax.do';
   $.ajax({
    	type:'post',//可选get
    	url:urlval,//这里是接收数据的后台程序
    	data:param,//传给后台的数据，多个参数用&连接
    	dataType:'html',//服务器返回的数据类型 可选xml, json, script, or html,text等,建议还回json
    	success:function(data){
    		if (date=="sucess"){
    			alert("修改成功");
    		}
    		if (date=="error"){
    			alert("修改失败");
    		}
	    	window.location.reload();
    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
    	},
    	error:function(){
    	//ajax提交失败的处理函数！
    	}
    });
}

function changeEnabled(valueid,userid){
	var param = 'id='+userid+'&valuezhi='+valueid;
	var urlval = '/managermall/systemmall/user/adminUserEnabledAjax.do';
   $.ajax({
    	type:'post',//可选get
    	url:urlval,//这里是接收数据的后台程序
    	data:param,//传给后台的数据，多个参数用&连接
    	success:function(data){
    		if (date=="sucess"){
    			alert("修改成功");
    		}
    		if (date=="error"){
    			alert("修改失败");
    		}
	    	window.location.reload();
    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
    	},
    	error:function(){
    	//ajax提交失败的处理函数！
    	}
    });
}


$(function() {
});