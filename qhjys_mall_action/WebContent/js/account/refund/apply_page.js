function submit1(g){var c=$("#token").val();var b=$("#detailId").val();var e=$("#volumes").val();var d=1;if(e==undefined){d=0}var f=$("#reason").val();if(confirm("确定退款吗?")){var a="/managermall/account/refund/submitApply.do";$.ajax({type:"post",url:a,data:{token:c,reason:f,detailId:b,isType:d},success:function(h){if(h=="success"){alert("退款提交成功");window.location.href="/managermall/account/refund/list.do"}if(h=="error"){alert("退款提交失败")}if(h=="tokenError"){alert("请不要重复提交");window.location.href="/managermall/account/refund/list.do"}},error:function(){alert("服务器忙")}})}else{}};