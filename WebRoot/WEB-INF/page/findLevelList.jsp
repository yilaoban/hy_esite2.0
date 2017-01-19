<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<link href="/js/jquery-weui/css/weui.css" rel="stylesheet"/>
<link href="/js/jquery-weui/css/jquery-weui.css" rel="stylesheet"/>
<script src="/js/jquery-weui/js/jquery-weui.js" type="text/javascript"></script>
<SCRIPT type="text/javascript">

	function savePrice(){
		var names="";
		var ids="";
		var str;
		var isrun=1;
		//var reg = /(^[-+]?[1-9]\d*(\.\d{1,2})?$)|(^[-+]?[0]{1}(\.\d{1,2})?$)/;  
		var isuse=1;
		var ched = $("#notuse").prop("checked");
		if(ched =="checked" ||ched==true){
			isuse =0;
		}else{
			$("[name='price']").each(function(){
				if($(this).val() == ""){
					layer.alert("商品价格不能为空！");
					isrun=0;
					return;
				}
				names+=$(this).val()+",";
				ids += $(this).attr("lid")+",";
			});
		}
		if(isrun == 0) return;
		var productid = ${pid};
		$.post("/${oname}/content/addProductLevel.action",{"productid":productid,"ids":ids+"","names":names+"","isuse":isuse},function(data){
			if(data > 0){
				layer.alert("保存成功！");
			}
		});
	}
  	function isprice(num){
		if(num == 1){
	  		$("#userprice").show();
		}else{
	  		$("#userprice").hide();
		}
	}
	
</script>
<div  class="wrap_content">
<form action="" method="post" enctype="multipart/form-data" id="form1" class="formview">
<span id="errrSpan" style="color:red"><strong style="color:red"><s:fielderror theme="" fieldName="error"/></strong> </span>
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">会员价</a></li>
	  </ul>
	  <a href="javascript:window.history.back()" class="return" title="返回"></a>
	</div>
	<dl>
	 	<dt>会员价</dt>
		<dd>
			<label class="forradio"><input type="radio" <s:if test="dto.cproduct.vip == 1">checked="checked"</s:if> name="vip" onclick="isprice(1);" >使用</label><label class="forradio"><input type="radio" id="notuse" <s:if test="dto.cproduct.vip == 0">checked="checked"</s:if> name="vip" onclick="isprice(0);"">不使用</label></label>
		</dd>
	</dl>
	<div id="userprice" <s:if test="dto.cproduct.vip == 0">style="display: none;"</s:if> >
		<s:if test="dto.userpriceList.size() > 0">
			<s:iterator value="dto.userpriceList" var="p" status="st">
			<dl>
				<dt>${p.name }:</dt>
		    	<dd><input type="text" class="text-medium" value="${p.price }" data-provide="typeahead" lid="${p.id }" name="price" autocomplete="off"/></dd>
			</dl>
			</s:iterator>
		</s:if>
		<s:elseif test="dto.userlevelList.size() > 0">
			<s:iterator value="dto.userlevelList" var="l" status="st">
			<dl>
				<dt>${l.name }:</dt>
		    	<dd><input type="text" class="text-medium"  data-provide="typeahead" lid="${l.id }" name="price" autocomplete="off"/></dd>
			</dl>
			</s:iterator>
		</s:elseif>
		<s:else>
			<p>请先添加会员等级!</p>
		</s:else>
	</div>
	<dl>
		<dd>
		    <button type="button" class="btn btn-primary" onclick="savePrice();">保存</button>
		</dd>
	</dl>
</form>
</div>
<SCRIPT type="text/javascript">
	$(".text-medium").keyup(function () {
        var reg = $(this).val().match(/\d+\.?\d{0,2}/);
        var txt = '';
        if (reg != null) {
            txt = reg[0];
        }
        $(this).val(txt);
    }).change(function () {
        $(this).keyup();
    });

</script>