function throttle(method, scope) {
            clearTimeout(method.tId);
            method.tId= setTimeout(function(){
                method.call(scope);
            }, 100);
        }

var nav = {
	init : function() {
		nav.tdTxtJustify();
		nav.addFb();
		nav.tableColorCol();
        nav.resizeNav ();
         $(window).resize(function(){
                    nav.resizeNav ();
                });
	},
	tdTxtJustify : function() {
		$(".td_txtR tbody").find("tr").each(function() {
			$(this).find("td:even").css( {
				textAlign : "right",
				paddingRight : "5px"
			}).next().css( {
				textAlign : "left",
				paddingLeft : "10px"
			});
		});
	},
	addFb : function() {
		$("#site_config").find("tbody tr").each(function() {
			$(this).find("td:eq(0)").addClass("fb");
		});
	},
	tableColorCol : function() {
		$(".color_col_table tbody").find("tr").each(function() {
			$(this).find("td:even").css("background", "#e8f4fc");
		});
	},
	highlightRows : function(tableid) {
		$("#"+tableid+"").find("tbody tr:not('.table_footer')").hover(function() {
			$(this).addClass("highlight");
		}, function() {
			$(this).removeClass("highlight");
		});
	},
    resizeNav:function(){
        var width = $(window).outerWidth();
        if (width <= 1024) {
            $(".nav").addClass("nav_little");
          
        } 
        else {
            $(".nav").removeClass("nav_little");
            
        }
    }
};
$(document).ready(function() {
	nav.init();
});