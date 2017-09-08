function checkPostLogin(msg) {
	try{
		if ($.type(msg)!="object" && msg[0].resultStr.indexOf('~`^&') > 0) {
			var a = msg[0].resultStr.split('~`^&');
			if (a.length >= 1) {
				var f = "?fromurl=" + encodeURIComponent(window.location.href);
				window.location.href = "/login/" + a[0] + f;
				return true;
			}
		}
	}catch(e){
		alert(msg);
	}
	return false;
}

var doc= document;



function createMarquee(){
            var marquee = doc.getElementById("notice_marquee");
            if (!marquee) return;
            var marqueeStyle = marquee.style,
                   textWidth = marquee.innerHTML.length * parseInt($(marquee).css("fontSize"));
            if (!textWidth) return;
            if (marqueeStyle.webkitMarquee !== undefined) {
                marqueeStyle.whiteSpace = "nowrap";
                var marqueeIncrement = marqueeStyle.webkitMarqueeIncrement;
                marquee.onmouseover = function(){
                    marqueeStyle.webkitMarqueeIncrement = "0";
                    return false;
                }
                marquee.onmouseout = function(){
                    marqueeStyle.webkitMarqueeIncrement = marqueeIncrement;
                    return false;
                }
                return
            }
            
           
            
         LazyLoad.js("/js/common/jquery_marquee.js", function() {
            $('#notice_marquee').marquee('pointer').mouseover(function() {
                $(this).trigger('stop');
            }).mouseout(function() {
                $(this).trigger('start');
            })
        });
               
        }
$(function(){
    createMarquee();
})