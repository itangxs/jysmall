
var timeTimeout;
var kaifengTimeOut;
var allurlSrc;
var allth;
var allplace;
var one = 1;
var gapTime = new Date().getTime();
var timeSss = 0;
var ShowWay = {
		socket :null,
		tableConent:function(urlSrc,th,place){
			var d = new Date().getTime();
			if(one ==1 || (one >1 && (d-gapTime)>=timeSss)){
				//document.getElementById("maincontent").innerHTML = "页面加载中...";
	//			alert(RateChanger.socket);
//				if(RateChanger.socket!=null){
//					RateChanger.socket.disconnect();
	//	 			RateChanger.socket.emit('disconnect',{disconnect:1});
	//	 			RateChanger.socket.on('disconnect', function() {
	//	 				alert("aaa");
	//	 			});
//				}
				allurlSrc =urlSrc;
				allth= th;
				allplace= place;
				if(place==1){
					$("#topul > li[class='ui-state-active']").attr("class","");
					$("#list"+th).attr("class","ui-state-active");
				}else if(place==2){
					;
				}else{
					;
				}
				urlSrc = urlSrc+(urlSrc.indexOf("?")>=0?"&showWay=1":"?showWay=1");
				$.get(urlSrc+"&d="+ new Date().getTime(),function(data){
	//				alert(checkPostLogin(data));
					if(checkPostLogin(data)){
						return ;
					}else if (data == -1){
						alert("该玩法已经隐藏，不能投注，请刷新!")
					}else{
						//$(".confirm_zhudan .cancel").trigger("click");
						//$("#maincontent")[0].innerHTML = "";
		 				//$("#maincontent").html(data);
		 				//alert(data);
						//document.getElementById("maincontent").innerHTML = data;
						executeScript(data);
						//$(".main_content")[0].innerHTML = "";
						//document.getElementById("maincontent").innerHTML = data;
		 				//$(".main_content").html(data);
						//$(".main_content")[0].innerHTML = data;
		 				clearTimeout(timeTimeout);
		 				clearTimeout(kaifengTimeOut);
		 				GetRTime();
		    			getPankouStatus();
		    			
					}
				});
				
				gapTime = d;
				one +=1;
			}else{
				;
			}
			
		}
		
}

function executeScript(data)
{ 
	try{
	var regexp1 = /<script(.|\n)*?>(.|\n|\r\n)*?<\/script>/ig; 
	var regexp2 = /<script(.|\n)*?>((.|\n|\r\n)*)?<\/script>/im; 

	var html = data.replace(regexp1, ""); 
	if(document.getElementById("maincontent") == null)
		document.getElementById("lianma").innerHTML = html; 
	else
		document.getElementById("maincontent").innerHTML = html; 
	ConfirmOrder.init();
	var result = data.match(regexp1); 
	if (result) { 
		for (var i = 0; i < result.length; i++) { 
			var realScript = result[i].match(regexp2); 
			if(realScript&&typeof(realScript[2])!="undefined"){
				eval(realScript[2]);
			}
		} 
	} 
	return false;  
	}catch(e){}
} 

function GetRTime(){   
    	var seal_nseal=$('#seal_nseal').val();
    	if(seal_nseal == 1){
    		var nMS=$('#diff_time').val();
	        var nD=Math.floor(nMS/(1000 * 60 * 60 * 24));   
	        var nH=Math.floor(nMS/(1000*60*60)) % 24;   
	        var nM=Math.floor(nMS/(1000*60)) % 60;   
	        var nS=Math.floor(nMS/1000) % 60;
	        if(nD == 0 && nH==0 && nM==0 && nS==0){
	        	$('#timer').html("已封盘");
	        	if(!allurlSrc)
					location.reload();
				ShowWay.tableConent(allurlSrc,allth,allplace)
		    }else{
		    	$('#timer').html("<font color='black'>距封盘时间：</font>"+nD+"天"+toTwo(nH)+"小时"+toTwo(nM)+"分钟"+toTwo(nS)+"秒");
		        $('#diff_time').val(nMS-100)
		    	timeTimeout = setTimeout("GetRTime()",100);
			}   
    	}else{
    		$('#timer').html("已封盘");
    	}
    	
    }

    function toTwo(value){
		if(value<10){
			return "0"+value;
		}
		return value;
    }
    
    function getPankouStatus(){
        var ymCode = $("#topul .ui-state-active").attr('id');
        if(ymCode){
        	ymCode=ymCode.replace('list','');
        }else{
        	return;
        }
        if($('#timer').html()=="已封盘"){
        	$.ajax({
        		type : "get",
        		async:false,
        		url : "/z3/rateinfo/getpankoustatus",
        		success : function(data){
        			var result = eval('(' + data + ')');
        			if($('#timer').html()=="已封盘" && result.t == '1'){
        				if((ymCode == '10' && result.tm =='1')
                    			||(ymCode != '10' && result.ftm =='1')){
        					if(!allurlSrc)
            					location.reload();
            				ShowWay.tableConent(allurlSrc,allth,allplace)
                		}else{
                			kaifengTimeOut= setTimeout("getPankouStatus()",30000);
                    	}
            		}/*else{
            			if((ymCode == '10' && result.tm =='0')
                    			||(ymCode != '10' && result.ftm =='0')){
            				if(!allurlSrc)
            					location.reload();
            				ShowWay.tableConent(allurlSrc,allth,allplace)
                		}else{
                			kaifengTimeOut= setTimeout("getPankouStatus()",5000);
                    	}
                	}*/
        		},
        		error:function(){
        			//alert("error")
        		}
        	});
        } 
    }
    
    
    
	function q(v){
	    var j=['76','75','74','66','72','71'];
		var y=['68','65','67','70','69','73'];
		
		$(":input[type='checkbox']").removeAttr("checked");
		var gameCode = $("#gameCode").val();
		if(v==1){
			for(var i=0;i<j.length;i++){
				$("#td_"+gameCode+"_"+j[i]).next().find("input").attr("checked","checked")
			}
		}else{
			for(var i=0;i<y.length;i++){
				$("#td_"+gameCode+"_"+y[i]).next().find("input").attr("checked","checked")
			}
		}
	}
