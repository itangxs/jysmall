$(function(){$.ajax({async:false,type:"POST",url:"/queryProvinceInfo.do",success:function(a){$("#licenseProvince").combobox({data:a.provinceList,valueField:"id",textField:"name",editable:false,onChange:function(b){var c=b;$.ajax({async:false,type:"POST",data:{provinceId:c},url:"/queryCityInfo.do",success:function(d){$("#licenseCity").combobox({data:d.cityInfoList,valueField:"id",textField:"name",editable:false,onChange:function(e){var f=e;$.ajax({async:false,type:"POST",data:{cityId:f},url:"/queryDistrictInfo.do",success:function(g){$("#licenseArea").combobox({data:g.districtInfoList,valueField:"id",textField:"name",editable:false})}})}})}})}})}});$("#licenseProvince").combobox("setValue",$("#province1").val());$("#licenseCity").combobox("setValue",$("#city1").val());$("#licenseArea").combobox("setValue",$("#area1").val())});$.fn.datebox.defaults.formatter=function(b){var e=b.getFullYear();var a=b.getMonth()+1;var c=b.getDate();return e+"-"+(a<10?("0"+a):a)+"-"+(c<10?("0"+c):c)};$.fn.datebox.defaults.parser=function(c){if(!c){return new Date()}var b=c.split("-");var f=parseInt(b[0],10);var a=parseInt(b[1],10);var e=parseInt(b[2],10);if(!isNaN(f)&&!isNaN(a)&&!isNaN(e)){return new Date(f,a-1,e)}else{return new Date()}};function Excel(){var b=$("#from").serialize();var a="/managermall/systemmall/statistics/shopAreaListExcel.do?"+b;window.open(a)};