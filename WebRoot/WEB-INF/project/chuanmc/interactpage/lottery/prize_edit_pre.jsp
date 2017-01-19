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
		<script>parent.layer.msg('${result}',2,3);
				window.location="prizeEditPre.action?id="+'${id}';
		</script>
	</s:else>
</s:if>
<div id="addprizeHtml">
<form action="updateprize.action"  enctype="multipart/form-data" id="form1" method="post" class="formview">
	<dl>
		<dt>奖品名称</dt>
		<dd>
			<input type="hidden" name="id" value="${id }"/>
			<input id="uname" type="text" class="text-medium" name="name" value="${dto.prize.name }"/><span class="must">*</span>
			<span id="tm_uname" style="color:red; display:none;" >名称不能为空!</span>
		</dd>
	</dl>
	<dl>
		<dt>奖品数量</dt>
		<dd>
			<input id="utotle" type="text" class="text-medium" name="total" value="${dto.prize.total }" onkeyup="this.value = this.value.replace(/\D/g,'');"/>
			<span id="tm_utotle" style="color:red; display:none;" >必须为数字!</span>
		</dd>
	</dl>
	<dl>
		<dt>奖品权重</dt>
		<dd>
			<select id="uprice" name="price">
				<option value="1" <s:if test="dto.prize.price==1">selected="selected"</s:if>>一等奖</option>
				<option value="2" <s:if test="dto.prize.price==2">selected="selected"</s:if>>二等奖</option>
				<option value="3" <s:if test="dto.prize.price==3">selected="selected"</s:if>>三等奖</option>
				<option value="4" <s:if test="dto.prize.price==4">selected="selected"</s:if>>四等奖</option>
				<!-- <option value="5" <s:if test="dto.prize.price==5">selected="selected"</s:if>>五等奖</option>
				<option value="6" <s:if test="dto.prize.price==6">selected="selected"</s:if>>六等奖</option> -->
			</select>
			<span id="tm_uprice" style="color:red; display:none;" >必须为数字!</span>
		</dd>
	</dl>
	<!-- 
	<dl>
		<dt>图片</dt>
		<dd>
			<input type="file" name="file" id="fsimg" />
			<input type="hidden" name="fileurl" value="${dto.prize.img }">
		</dd>
		<dd>
			<img id="img" style="width:100px;" src="${imgDomain }${dto.prize.img }">
		</dd>
	</dl>
	 -->
	<dl>
		<dt>奖品类别</dt>
		<dd class="pt10">
			<select id="type" name="type" onchange="addjf()">
				<option value="S" <s:if test='dto.prize.type=="S"'>selected="selected"</s:if>>实物奖品</option>
				<option value="J" <s:if test='dto.prize.type=="J"'>selected="selected"</s:if>>积分</option>
				<option value="C" <s:if test='dto.prize.type=="C"'>selected="selected"</s:if>>优惠券</option>
				<option value="M" <s:if test='dto.prize.type=="M"'>selected="selected"</s:if>>口令红包</option>
				<option value="H" <s:if test='dto.prize.type=="H"'>selected="selected"</s:if>>微信红包</option>
				<option value="Z" <s:if test='dto.prize.type=="Z"'>selected="selected"</s:if>>自提商品</option>
			</select>
		</dd>
	</dl>
	<dl id="jfdl" <s:if test='dto.prize.type!="J"'>style="display: none"</s:if>>
		<dt>积分数量</dt>
		<input id="jf" type="text"class="text-medium" name="jf" value="${dto.prize.jf }" onkeyup="this.value = this.value.replace(/\D/g,'');"/>
		<span id="tm_jf" style="color:red; display:none;" >必须为数字!</span>
	</dl>
	<dl class="hbdl" <s:if test='dto.prize.type!="M"&&dto.prize.type!="H"'>style="display: none"</s:if>>
		<dt>红包价格</dt>
		<input id="hb" type="text"class="text-medium" name="hb" value="${dto.prize.hprice }" onkeyup="this.value = this.value.replace(/\D/g,'');"/>单位(分)
		<span style="color:red; display:none;" >必须为数字!</span>
	</dl>
	<dl class="hbdl" <s:if test='dto.prize.type!="M"&&dto.prize.type!="H"'>style="display: none"</s:if>>
		<dt>祝福语</dt>
		<input id="hb" type="text"class="text-medium" name="wishing" onblur="checkempty(this)" value="${dto.prize.wishing }"/><span class="must">*</span>
		<span style="color:red; display:none;" >祝福语不能为空!</span>
	</dl>
	<dl class="hbdl" <s:if test='dto.prize.type!="M"&&dto.prize.type!="H"'>style="display: none"</s:if>>
		<dt>活动名称</dt>
		<input id="hb" type="text"class="text-medium" name="act_name" onblur="checkempty(this)" value="${dto.prize.act_name }"/><span class="must">*</span>
		<span style="color:red; display:none;" >活动名称不能为空!</span>
	</dl>
	<dl class="hbdl" <s:if test='dto.prize.type!="M"&&dto.prize.type!="H"'>style="display: none"</s:if>>
		<dt>备注</dt>
		<input id="hb" type="text"class="text-medium" name="remark" onblur="checkempty(this)" value="${dto.prize.remark }"/><span class="must">*</span>
		<span style="color:red; display:none;" >备注不能为空!</span>
	</dl>
	<dl class="mhbdl" <s:if test='dto.prize.type=="M"'>style="display: block;"</s:if><s:else>style="display: none"</s:else>>
		<dt>关注领取</dt>
		 <label class="forradio"> <input id="hydefault" type="radio" value="1" name="hbgz" <s:if test="dto.prize.hbgz == 1">checked="checked"</s:if>>是</label>
		 <label class="forradio"> <input type="radio" value="0" name="hbgz" <s:if test="dto.prize.hbgz == 0">checked="checked"</s:if>>否</label>
	</dl>
	<dl class="mhbdl" <s:if test='dto.prize.type=="M"'>style="display: block;"</s:if><s:else>style="display: none"</s:else>>
		<dt>红包口令</dt>
		<input type="text"class="text-medium" name="hbkey" onblur="checkHbkye(this)" value="${dto.prize.hbkey }"/>
	</dl>
	<dl class="ztdl" <s:if test='dto.prize.type=="Z"'>style="display: block;"</s:if><s:else>style="display: none"</s:else>>
		<dt>自提商品</dt>
		<select id="type" name="productid">
				<option value="-1">未选择</option>
				<s:iterator value="list" var="p">
					<option value="${p.id }" <s:if test="dto.prize.productid==#p.id">selected="selected"</s:if>>${p.name }</option>
				</s:iterator>
		</select>
	</dl>
	<dl>
		<dt>是否安慰奖品</dt>
		<dd>
			 <label class="forradio"><input id="uhydefault" type="radio" value="Y" name="hydefault"  <s:if test='dto.prize.hydefault=="Y"'>checked="checked"</s:if>>是</label>
			 <label class="forradio"><input type="radio" value="N" name="hydefault" <s:if test='dto.prize.hydefault=="N"'>checked="checked"</s:if>>否</label>
		</dd>
	</dl>
	<dl>
		<dt>领奖地址</dt>
		<dd>
			<input type="text" name="link" value="${dto.prize.link }"/>
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
function checkHbkye(obj){
	var v=$(obj).val();
	if(v==""){
		var hbgz =$('input:radio[name="hbgz"]:checked').val();
		if(hbgz == 0){
			alert("关注领取或者填写红包口令");
		}
	}
}

<!--
$("#utotle").blur(function(){
    var reg = new RegExp("^[0-9]*$");
    var obj = document.getElementById("utotle");
    if(!reg.test(obj.value)){
       $("#tm_utotle").show();
    }
    else{
		$("#tm_utotle").hide();
	}
});
$("#uprice").blur(function(){
    var reg = new RegExp("^[0-9]*$");
    var obj = document.getElementById("uprice");
	if(!reg.test(obj.value)){
		$("#tm_uprice").show();
	}else{
		$("#tm_uprice").hide();
	}
});
$("#uname").blur(function(){
	if($("#uname").val() == null || $("#uname").val() == ""){
		$("#tm_uname").show();
	}else{
		$("#tm_uname").hide();
	}
});
//-->
</script>