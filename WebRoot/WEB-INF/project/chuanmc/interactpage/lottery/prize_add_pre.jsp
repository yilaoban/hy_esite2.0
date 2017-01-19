<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test='result != null && result != "" '>
	<s:if test='result == "Y"'>
		<script>
			parent.layer.load(2,2);
			setTimeout('parent.layer.msg("操作成功!",2,1)',2000);
			var i = parent.layer.getFrameIndex();
			setTimeout('window.top.location.reload()',4000);
		</script>
	</s:if>
	<s:else>
		<script>parent.layer.msg('${result}',2,3);</script>
	</s:else>
</s:if>
<div id="addprizeHtml">
<form action="prizeAdd.action"  enctype="multipart/form-data" id="form1" method="post" class="formview">
	<input type="hidden" name="lid" value="${lid }">
	<dl>
		<dt>奖品名称</dt>
		<dd>
			<input id="addname" type="text"class="text-medium" name="name" />
			<span class="must">*</span>
			<span id="tm_addname" style="color:red; display:none;" >名称不能为空!</span>
		</dd>
	</dl>
	<dl>
		<dt>奖品数量</dt>
		<dd>
			<input id="addtotle" type="text"class="text-medium" name="total" maxlength="8" onkeyup="this.value = this.value.replace(/\D/g,'');"/>
            <span id="tm_addtotle" style="color:red; display:none;" >必须为数字!</span>
		</dd>
	</dl>
	<dl>
		<dt>奖品权重</dt>
		<dd>
			<select id="addprice" name="price">
				<option value="1">一等奖</option>
				<option value="2">二等奖</option>
				<option value="3">三等奖</option>
				<option value="4">四等奖</option>
				<!-- <option value="5">五等奖</option>
				<option value="6">六等奖</option> -->
			</select>
			<span id="tm_addprice" style="color:red; display:none;" >必须为数字!</span>
		</dd>
	</dl>
	<!-- 
	<dl>
		<dt>奖品图片</dt>
		<dd>
			<input type="file" name="file" >
		</dd>
	</dl>
	 -->
	<dl>
		<dt>奖品类别</dt>
		<dd class="pt10">
			<select id="type" name="type" onchange="addjf()">
				<option value="S">实物奖品</option>
				<option value="J">积分</option>
				<option value="C">优惠券</option>
				<option value="M">口令红包</option>
				<option value="H">微信红包</option>
				<option value="Z">自提商品</option>
			</select>
		</dd>
	</dl>
	<dl id="jfdl" style="display: none">
		<dt>积分数量</dt>
		<input id="jf" type="text"class="text-medium" name="jf" onkeyup="this.value = this.value.replace(/\D/g,'');"/>
		<span id="tm_jf" style="color:red; display:none;" >必须为数字!</span>
	</dl>
	<dl class="hbdl" style="display: none">
		<dt>红包价格</dt>
		<input id="hb" type="text"class="text-medium" name="hb" onkeyup="this.value = this.value.replace(/\D/g,'');"/>单位(分)
		<span style="color:red; display:none;" >必须为数字!</span>
	</dl>
	<dl class="hbdl" style="display: none">
		<dt>祝福语</dt>
		<input id="hb" type="text"class="text-medium" name="wishing" onblur="checkempty(this)"/><span class="must">*</span>
		<span style="color:red; display:none;" >祝福语不能为空!</span>
	</dl>
	<dl class="hbdl" style="display: none">
		<dt>活动名称</dt>
		<input id="hb" type="text"class="text-medium" name="act_name" onblur="checkempty(this)"/><span class="must">*</span>
		<span style="color:red; display:none;" >活动名称不能为空!</span>
	</dl>
	<dl class="hbdl" style="display: none">
		<dt>备注</dt>
		<input id="hb" type="text"class="text-medium" name="remark" onblur="checkempty(this)"/><span class="must">*</span>
		<span style="color:red; display:none;" >备注不能为空!</span>
	</dl>
	<dl class="mhbdl" style="display: none">
		<dt>关注领取</dt>
		 <label class="forradio"> <input id="hydefault" type="radio" value="1" name="hbgz">是</label>
		 <label class="forradio"> <input type="radio" value="0" name="hbgz" checked="checked">否</label>
	</dl>
	<dl class="mhbdl" style="display: none">
		<dt>红包口令</dt>
		<input type="text"class="text-medium" name="hbkey" onblur="checkHbkye(this)"/>
	</dl>
	<dl class="ztdl" style="display: none">
		<dt>自提商品</dt>
		<select id="type" name="productid">
				<option value="-1">未选择</option>
				<s:iterator value="list" var="p">
					<option value="${p.id }">${p.name }</option>
				</s:iterator>
		</select>
	</dl>
	<dl>
		<dt>是否安慰奖品</dt>
		<dd>
			 <label class="forradio"> <input id="hydefault" type="radio" value="Y" name="hydefault">是</label>
			 <label class="forradio"> <input type="radio" value="N" name="hydefault" checked="checked">否</label>
		</dd>
	</dl>
	<dl>
		<dt>领奖地址</dt>
		<dd>
			<input type="text" name="link"/>
		</dd>
	</dl>
	<dl>
		<dt></dt>
		<dd>
			<input type="submit" value="确定">
			<input type="button" value="取消" onclick="closeFrame()">
		</dd>
	</dl>
</form>
</div>
<script type="text/javascript">
<!--
$("#addtotle").blur(function(){
    var reg = new RegExp("^[0-9]*$");
    var obj = document.getElementById("addtotle");
    if(!reg.test(obj.value)){
       $("#tm_addtotle").show();
    }
    else{
		$("#tm_addtotle").hide();
	}
});
$("#jf").blur(function(){
    var reg = new RegExp("^[0-9]*$");
    var obj = document.getElementById("jf");
    if(!reg.test(obj.value)){
       $("#tm_jf").show();
    }
    else{
		$("#tm_jf").hide();
	}
});
$("#hb").blur(function(){
    var reg = new RegExp("^[0-9]*$");
    var obj = document.getElementById("hb");
    if(!reg.test(obj.value)){
       $("#tm_hb").show();
    }
    else{
		$("#tm_hb").hide();
	}
});

$("#addprice").blur(function(){
    var reg = new RegExp("^[0-9]*$");
    var obj = document.getElementById("addprice");
	if(!reg.test(obj.value)){
		$("#tm_addprice").show();
	}else{
		$("#tm_addprice").hide();
	}
});
$("#addname").blur(function(){
	if($("#addname").val() == null || $("#addname").val() == ""){
		$("#tm_addname").show();
	}else{
		$("#tm_addname").hide();
	}
});

function checkempty(obj){
	var v=$(obj).val();
	if(v==""){
		$(obj).next().show();	
	}else{
		$(obj).next().hide();
	}
}

function checkHbkye(obj){
	var v=$(obj).val();
	if(v==""){
		var hbgz =$('input:radio[name="hbgz"]:checked').val();
		if(hbgz == 0){
			alert("关注领取或者填写红包口令");
		}
	}
}

//-->
</script>

