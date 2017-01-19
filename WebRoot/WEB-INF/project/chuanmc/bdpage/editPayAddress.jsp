<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="/css/shop/myorders2.css" rel="stylesheet" type="text/css">
 <script type="text/javascript">
 	function updateUserAddress(){
		var name = $('#name').val().trim();
		var telphone = $('#telphone').val().trim();
		var province = $('.span_province').text();
		var city = $('.span_city').text();
		var address = $('#address').val().trim();
		var isdefault="N";
		if($("#isdefault")&&$("#isdefault").is(":checked")){
			isdefault="Y";
		}
		if(name=="" || address=="" || telphone=="") {
			$.alert("请填写完整的信息！");
			return;
		}
		if(!checkTel()){
			return;
		}
		$.post("/${oname}/user/updatePayAddress.action",{"aid":'${aid}',"dto.address.name":name,"dto.address.address":address,"dto.address.telphone":telphone,"dto.address.province":province,"dto.address.city":city,"dto.address.telphone":telphone,"dto.address.isdefault":isdefault},function(data){
			if(data > 0){
				$.alert("修改收货地址成功","",function(){
					window.location.href = '/${oname}/user/myAddress.action?type=${type}';
				});
			}else {
				$.alert("修改收货地址失败！");
			}
		});
 	}
 	
 function checkTel(){
	var re=/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
	if(!(re.test($("#telphone").val()))){
		$.alert("请输入正确手机号码");
		return false;
	}else{
		$("#error").html("");
		return true;
	}
}

$(document).ready(function() {
		$("#province").change(function(){
			$.post("/${oname}/user/ajax_get_city.action",{"provinceId":$("#province").val()},
			   function(data){
					$("#city").html("");
					$("#city").append("<option value='-100'>选择城市</option>");
					$('#city').prev().children(":first").children(":first").text("选择城市");
					$.each( data, function(i, n){
						$("#city").append("<option value='"+n.cityId+"' id='c_"+n.cityId+"'>"+n.city+"</option>");
					});
			   }, "json");
		});
});
</script>
  
<div class="top"><a href="javascript:void(0)" onclick="window.history.back()" class="left"><img src="/images/shop/back.png" />&nbsp;返回</a>编辑地址　<a href="#" class="right"></a></div>
<div style="width:100%;height:44px;"></div>
<form>
<div class="add">
    <ul>
    <li><input type="text" placeholder="收货人姓名" id="name" value="${dto.address.name}"></li>
    <li><input type="number" placeholder="手机号码" id="telphone" value="${dto.address.telphone}"></li>
    <li>
        <a href="#"  class="btn-selectn" id="btn_select2">
           <span class="cur-selectn span_province" >${dto.address.province}</span>
            <select id="province" name="province">
				<s:iterator value="dto.provinceList" var="f">
				  <option value="${f.provinceId}" id="p_${f.provinceId}">${f.province}</option>
				</s:iterator>
            </select>
         </a>
     </li>
    <li>
        <a href="#" class="btn-selectn" id="btn_select">
           <span class="cur-selectn span_city" >${dto.address.city}</span>
            <select name="city" id="city">
				<s:iterator value="dto.cityList" var="f">
				  <option value="${f.cityId}" id="c_${f.cityId}">${f.city}</option>
				</s:iterator>
            </select>
         </a>
     </li>
     <li class="area"><textarea placeholder="详细地址" id="address">${dto.address.address}</textarea></li>
     <li><b class="border"><input type="checkbox" id="isdefault" value="Y" ></b><span class="tip1">设为默认地址</span><br><em class="tip2">注：每次下单时会使用该地址</em></li>
    </ul>
    <div class="bottom"><a href="javascript:updateUserAddress()">保存</a></div>
</div>
</form>
<script type="text/javascript">
var $$ = function (id) {
return document.getElementById(id);
}
$(function(){
	var btnSelect = $$("btn_select");
	var curSelect = btnSelect.getElementsByTagName("span")[0];
	var oSelect = btnSelect.getElementsByTagName("select")[0];
	var aOption = btnSelect.getElementsByTagName("option");
	oSelect.onchange = function () {
	var text=oSelect.options[oSelect.selectedIndex].text;
	curSelect.innerHTML = text;
	}
	})
$(function(){
	var btnSelect = $$("btn_select2");
	var curSelect = btnSelect.getElementsByTagName("span")[0];
	var oSelect = btnSelect.getElementsByTagName("select")[0];
	var aOption = btnSelect.getElementsByTagName("option");
	oSelect.onchange = function () {
	var text=oSelect.options[oSelect.selectedIndex].text;
	curSelect.innerHTML = text;
	}
	})

$(document).ready(function(){
 			$(".add ul li b").click(function(){
 				var bclass=$(this).attr("class");
 				if(bclass=="border"){
 					$(this).removeClass("border").addClass("choi");
 					$(this).children("input:checkbox").attr("checked",true);
 				}else{
 					$(this).removeClass("choi").addClass("border");
 	  				$(this).children("input:checkbox").attr("checked",false);
 				}
 			});
 			
 			var isdefault = '${dto.address.isdefault}';
 			if(isdefault == "Y"){
 				$(".add ul li b").click();
 			}
 			
 		});
</script>