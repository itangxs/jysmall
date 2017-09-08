var loginname = "_ln";
var loginnameadmin = "_lnadmin";
var userinfo = "_user";
var userinfoadmin = "_useradmin";

function getDomain(){
	      var h = window.location.href.substr(7);
        var end=h.indexOf("/");
        var temp=h.substr(0,end).split(".");
        var len=temp.length;
        return "."+temp[len-2]+"."+temp[len-1];
}

function getCookie( name, isEncode ) {
	var start=document.cookie.indexOf(name+"=");
	if(start==-1) return null;
	var len=start + name.length + 1;
	var end = document.cookie.indexOf(';',len);
	if (end==-1) end=document.cookie.length;
	var retValue = "";
	if (isEncode) {
		//retValue = decodeURI (document.cookie.substring(len, end));
		retValue = unescape(document.cookie.substring(len, end));
	} else {
		retValue = document.cookie.substring(len, end);
	}
	
	return retValue;
}

function setCookie(name,value,expires,isEncode){
	var expires_date;
	if (expires){
		expires=expires*1000*60*60*24;
		var today = new Date();
		expires_date=new Date(today.getTime()+(expires));
	}
	if (isEncode) {
		value = encodeURI(value);
	}
	document.cookie=name+'='+value+((expires)?';expires='+expires_date.toGMTString():'')+';path=/'+((getDomain())?';domain='+getDomain():'');
}

function deleteCookie(name){
	if(getCookie(name))
	document.cookie=name+'=;path=/'+((getDomain())?';domain='+getDomain():'')+ ';expires=Thu, 01-Jan-1970 00:00:01 GMT'; 
	getCookie(name);
} 

function getUserloginInfo(name){
	var userLoginInfo = unescape(getCookie(name));
	if(userLoginInfo!=null&&userLoginInfo!=""&&userLoginInfo!="null"){
		return userLoginInfo.split('_');
	}
	return null;
}