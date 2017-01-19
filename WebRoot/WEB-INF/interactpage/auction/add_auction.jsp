<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	
	function nameonblur(){
		if($("#addname").val() == null || $("#addname").val() == ""){
			$("#name_").html("标题不能为空!").css("color", "RED");
			return false;
		}else{
			$("#name_").html("");
			return true;
		}
	}
	function startTimeonblur(){
		if($("#startTime").val() == null || $("#startTime").val() == ""){
			$("#startTime_").html("开始时间不能为空!").css("color", "RED");
			return false;
		}else{
			$("#startTime_").html("");
			return true;
		}
	}
	function endTimeonblur(){
		if($("#endTime").val() == null || $("#endTime").val() == ""){
			$("#endTime_").html("结束时间不能为空!").css("color", "RED");
			return false;
		}else{
			$("#endTime_").html("");
			return true;
		}
	}
	function addstartbalanceonblur(){
		if($("#addstartbalance").val() == null || $("#addstartbalance").val() == ""){
			$("#addstartbalance_").html("最低价格不能为空!").css("color", "RED");
			return false;
		}else{
			$("#addstartbalance_").html("");
			return true;
		}
	}
	function addbalanceonblur(){
		if($("#addbalance").val() == null || $("#addbalance").val() == ""){
			$("#addbalance_").html("竞价增加价格不能为空!").css("color", "RED");
			return false;
		}else{
			$("#addbalance_").html("");
			return true;
		}
	}
	function checkSub(){
	 	if($("#addname").val() == null || $("#addname").val() == ""){
			$("#name_").html("标题不能为空!").css("color", "RED");
			window.location.hash="maodian";
		}else if($("#startTime").val() == null || $("#startTime").val() == ""){
			$("#startTime_").html("开始时间不能为空!").css("color", "RED");
			window.location.hash="maodian";
		}else if($("#endTime").val() == null || $("#endTime").val() == ""){
			$("#endTime_").html("结束时间不能为空!").css("color", "RED");
			window.location.hash="maodian";
		}else if($("#addstartbalance").val() == null || $("#addstartbalance").val() == ""){
			$("#addstartbalance_").html("最低价格不能为空!").css("color", "RED");
			window.location.hash="maodian";
		}else if($("#addbalance").val() == null || $("#addbalance").val() == ""){
			$("#addbalance_").html("竞价增加价格不能为空!").css("color", "RED");
			window.location.hash="maodian";
		}else if($("#addstartbalance").val()<=0){
			$("#addstartbalance_").html("最低价格不能为0!").css("color", "RED");
			return false;
		}else if($("#addbalance").val()<=0){
			$("#addbalance_").html("竞价增加价格不能为0!").css("color", "RED");
			return false;
		}else{
			$("#form1").submit();
		}
	 	
		
	}
</script>
<a name="maodian"></a>
<div class="wrap_content left_module">
  <form action="saveAuctionSub.action" method="post" enctype="multipart/form-data" id="form1" class="formview jNice">
  <input type="hidden" name="mid" value="10007">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">新增竞拍</a></li>
	  </ul>
	  <img src="/images/help.jpg" height="20" width="20" onclick="help_auction()" title="帮助文档" style="cursor:pointer">
	  <a href="/${oname }/interact/index.action?mid=10007" class="return" title="返回"></a>
	</div>
 	<dl>
	 	<dt>商品名称</dt>
		<dd>
			<input type="text" class="text-medium" placeholder="此标题仅方便您记忆，请任意填写" id="addname" name="name" value="${name }" onblur="nameonblur()"/>
			<span class="must">*</span>
			<span id="name_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>简介</dt>
		<dd>
			<textarea  maxlength="200" name="detail" placeholder="简介内容方便您记忆，不会在页面中显示"></textarea>
	 		<span class="notice">最多不超过200个汉字</span>
		</dd>
	</dl>
	<dl>
	 	<dt>活动开始时间</dt>
		<dd>
			<input id="startTime" type="text" value="${startTime }" name="startTime" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" onblur="startTimeonblur()" />
			<span class="must">*</span>
			<span id="startTime_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>活动结束时间</dt>
		<dd>
			<input id="endTime" type="text" value="${endTime }" name="endTime" class="Wdate"	onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" onblur="endTimeonblur()" />
			<span class="must">*</span>
			<span id="endTime_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>开始前提示语</dt>
		<dd>
			<input type="text" class="text-long" name="startnote"><span class="notice">提示用户该申请还没开始</span>
		</dd>
	</dl>
	<dl>
	 	<dt>结束后提示语</dt>
		<dd>
			<input type="text" class="text-long" name="endnote"><span class="notice">提示用户该申请已结束</span>
		</dd>
	</dl>
	<dl>
	 	<dt>商品图片</dt>
		<dd>
			<input type="file" name="img">
		</dd>
	</dl>
	<dl>
	 	<dt>商品链接</dt>
		<dd>
			<input type="text" class="text-medium" value="${url }" name="url"/>
		</dd>
	</dl>
	<dl>
	 	<dt>起拍积分</dt>
		<dd>
			<input id="addstartbalance" type="text" class="text-medium" name="startbalance" value="${startbalance }" onkeyup="this.value = this.value.replace(/\D/g,'');" onblur="addstartbalanceonblur()"/>积分
			<span class="must">*</span>
			<span id="addstartbalance_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>每次竞价增加积分</dt>
		<dd>
			<input id="addbalance" type="text" class="text-medium" name="addbalance" value="${addbalance }" onkeyup="this.value = this.value.replace(/\D/g,'');" onblur="addbalanceonblur()"/>积分
			<span class="must">*</span>
			<span id="addbalance_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>延迟时间</dt>
		<dd>
			<input type="text" class="text-medium" value="${addsecond }" name="addsecond" onkeyup="this.value = this.value.replace(/\D/g,'');"/>秒
		</dd>
	</dl>
	<dl>
	 	<dt>中标用户填写信息</dt>
		<dd>
			<label class="forradio ml10"><input type="checkbox" name="userName" value="Y"  <s:if test='userName=="Y"'>checked="checked"</s:if>>姓名</label>
			<label class="forradio ml10"><input type="checkbox" name="userPhone" value="Y"  <s:if test='userPhone=="Y"'>checked="checked"</s:if>>手机</label>
			<label class="forradio ml10"><input type="checkbox" name="userEmail" value="Y" <s:if test='userEmail=="Y"'>checked="checked"</s:if>>邮箱</label>
			<label class="forradio ml10"><input type="checkbox" name="userLocation" value="Y" <s:if test='userLocation=="Y"'>checked="checked"</s:if>>地区</label>
		</dd>
	</dl>
	<dl>
	 	<dt></dt>
		<dd>
			<input type="button" class="btn btn-primary" value="提交" onclick="checkSub()">
			<input type="reset" class="btn" value="取消">
		</dd>
	</dl>
 </form>
 </div>
 
