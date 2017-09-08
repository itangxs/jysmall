var TABLE_WIDTH;
var TABLE_HEIGHT=200;
function fiexedTableResult(divName,fixedTalbeClass,w,h){
   $(divName).each(function() {      
        var Id = $(this).get(0).id;
        var maintbheight = h;
        var maintbwidth = w;

        $("#" + Id + fixedTalbeClass).fixedTable({
            width: maintbwidth,
            height: maintbheight,
            fixedColumns: 1,
            // header style
            classHeader: "fixedHead",
            // footer style        
            classFooter: "fixedFoot",
            // fixed column on the left        
            classColumn: "fixedColumn",
            // the width of fixed column on the left      
            fixedColumnWidth: 0,
            // table's parent div's id           
            outerId: Id,
            // tds' in content area default background color                     
            Contentbackcolor: "#FFFFFF",
            // tds' in content area background color while hover.     
            Contenthovercolor: "#99CCFF", 
            // tds' in fixed column default background color   
            fixedColumnbackcolor:"#FFFFFF", 
            // tds' in fixed column background color while hover. 
            fixedColumnhovercolor:"#FFFFFF"  
        });        
    });
}

$(document).ready(function(){
		TABLE_WIDTH = document.body.clientWidth - 40;
		//如果鼠标移到name为row_differentiate的表格的tr上时，执行函数
        $("table[name=row_differentiate] tr").mouseover(function(){  
        $(this).addClass("mouseover");}).mouseout(function(){ 
        //给这行添加class值为mouseover，并且当鼠标一出该行时执行函数
        $(this).removeClass("mouseover");});
        //给name为row_differentiate的表格的偶数行添加class值为diff
        $("table[name=row_differentiate] tr[name!='selrow']:even").addClass("diff");
         
});

       function tableUtils(tableId) {
            //隐藏模板tr
            $("#"+tableId+" tbody tr").eq(0).hide();
            var i = 0;
            $("#btAddRow").click(function() {										  											  
            //复制一行
                var tr = $("#"+tableId+" tbody tr").eq(0).clone();
                //tr.find("td").get(0).innerHTML = ++i;
                tr.show();
                tr.appendTo("#"+tableId);
				//fiexedTableResult(".tableDiv1"," .FixedTables",TABLE_WIDTH,TABLE_HEIGHT);				
            });
            $("#btDelRow").click(function() {
                $("#"+tableId+" tbody tr:gt(0)").each(function() {
                    if ($(this).find("#CK").get(0).checked == true) {
                        $(this).remove();
                    }
                });
                $("#CKA").attr("checked", false);
            });
            $("#CKA").click(function() {
                $("#"+tableId+" tbody tr:gt(0)").each(function() {
                    $(this).find("#CK").get(0).checked = $("#CKA").get(0).checked;
                });
            });
        }

