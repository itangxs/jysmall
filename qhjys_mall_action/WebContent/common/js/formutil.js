	function checkFieldNull(o){
		if(o.value==""){
			$("#sp_"+o.id).show();	
			return false;	
		}else{
			$("#sp_"+o.id).hide();	
		}
		return true;
	}
	//数字验证
	function checkFieldNum(o){
		if(o.value.lastIndexOf(".")==(o.value.length-1))
			o.value = o.value.substr(0,o.value.length-1);
		if(isNaN(o.value)){
			$("#num_"+o.id).show();
			o.value = "";	
			return false;				
		}
		else
			$("#num_"+o.id).hide();
	};	

     function checkNumber(e)
     {
         var key = window.event ? e.keyCode : e.which;

         if(key == 8 || key == 46 || key == 4 || key == 19)
         {
             return true;
         }
        
         var keychar = String.fromCharCode(key);
         reg = /\d/;
         var result = reg.test(keychar);
         if(!result)
         {
             return false;
         }
         else
         {
             return true;
         }
     }