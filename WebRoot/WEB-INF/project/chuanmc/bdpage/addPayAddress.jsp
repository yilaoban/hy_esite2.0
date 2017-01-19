<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="/css/shop/myorders2.css" rel="stylesheet" type="text/css">
 <script type="text/javascript">
 var type='${type}';
 	function saveUserAddress(){
 		var name = $('#name').val().trim();
		var telphone = $('#telphone').val().trim();
		var provinceId = $('#province').val().trim();
		var cityId = $('#city').val().trim();
		if(provinceId == -100 || cityId == -100 ){
			$.alert("请选择省份城市！");
			return;
		}
		var province = $('#p_'+provinceId).html();
		var city = $('#c_'+cityId).html();
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
		$.post("/${oname}/user/savePayAddress.action",{"dto.address.name":name,"dto.address.address":address,"dto.address.telphone":telphone,"dto.address.province":province,"dto.address.city":city,"dto.address.telphone":telphone,"dto.address.isdefault":isdefault},function(data){
			if(data > 0){
				$.alert("保存成功！","",function(){
					if(type){
						window.location.href='/${oname}/user/myAddress.action?type='+type;
					}else{
						var param = $.cookie('showOrder_cookie');
						window.location.href="/${oname}/user/showOrder.action?aid="+data+"&"+param;
					}
				});
			}else {
				$.alert("保存失败！","",function(){
					window.location.reload();
				});
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
<div class="top"><a href="javascript:void(0)" onclick="window.history.back()" class="left"><img src="/images/shop/back.png" />&nbsp;返回</a>新增地址　<a href="#" class="right"></a></div>
<div style="width:100%;height:44px;"></div>
<form>
<div class="add">
    <ul>
    <li><input type="text" placeholder="收货人姓名" id="name"></li>
    <li><input type="number" placeholder="手机号码" id="telphone"></li>
    <li>
        <a href="#"  class="btn-selectn" id="btn_select2">
           <span class="cur-selectn" >省份</span>
            <select id="province" name="province">
            	<option value ="-100">选择省份</option>
				<s:iterator value="dto.provinceList" var="f">
				  <option value="${f.provinceId}" id="p_${f.provinceId}">${f.province}</option>
				</s:iterator>
            </select>
         </a>
     </li>
    <li>
        <a href="#" class="btn-selectn" id="btn_select">
           <span class="cur-selectn" >城市</span>
            <select name="city" id="city">
            	<option value ="-100" >选择城市</option>
				<s:iterator value="dto.cityList" var="f">
				  <option value="${f.cityId}" id="c_${f.cityId}">${f.city}</option>
				</s:iterator>
            </select>
         </a>
     </li>
     <li class="area"><textarea placeholder="详细地址" id="address"></textarea></li>
     <li><b class="border"><input type="checkbox" id="isdefault" value="Y" ></b><span class="tip1">设为默认地址</span><br><em class="tip2">注：每次下单时会使用该地址</em></li>
    </ul>
    <s:if test='type=="W" || type=="J"'>
    	<div class="bottom"><a href="javascript:saveUserAddress()">保存至地址簿</a></div>
    </s:if>
    <s:else>
	    <div class="bottom"><a href="javascript:saveUserAddress()">保存并使用</a></div>
    </s:else>
</div>
</form>

<script>
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
  			$(".add ul li b").click();
  		});

</script>

