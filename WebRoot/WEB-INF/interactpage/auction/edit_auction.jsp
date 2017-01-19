<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">

function nameonblur(){
		if($("#editname").val() == null || $("#editname").val() == ""){
			$("#editname_").html("标题不能为空!").css("color", "RED");
			return false;
		}else{
			$("#editname_").html("");
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
	function editstartbalanceonblur(){
		if($("#editstartbalance").val() == null || $("#editstartbalance").val() == ""){
			$("#editstartbalance_").html("最低价格不能为空!").css("color", "RED");
			return false;
		}else{
			$("#editstartbalance_").html("");
			return true;
		}
	}
	function editbalanceonblur(){
		if($("#editaddbalance").val() == null || $("#editaddbalance").val() == ""){
			$("#editaddbalance_").html("竞价增加价格不能为空!").css("color", "RED");
			return false;
		}else{
			$("#editaddbalance_").html("");
			return true;
		}
	}
	
	function checkSub(){
	 	if($("#editname").val() == null || $("#editname").val() == ""){
			$("#editname_").html("标题不能为空!").css("color", "RED");
			window.location.hash="maodian";
		}else if($("#startTime").val() == null || $("#startTime").val() == ""){
			$("#startTime_").html("开始时间不能为空!").css("color", "RED");
			window.location.hash="maodian";
		}else if($("#endTime").val() == null || $("#endTime").val() == ""){
			$("#endTime_").html("结束时间不能为空!").css("color", "RED");
			window.location.hash="maodian";
		}else if($("#editstartbalance").val() == null || $("#editstartbalance").val() == ""){
			$("#editstartbalance_").html("最低价格不能为空!").css("color", "RED");
			window.location.hash="maodian";
		}else if($("#editaddbalance").val() == null || $("#editaddbalance").val() == ""){
			$("#editaddbalance_").html("竞价增加价格不能为空!").css("color", "RED");
			window.location.hash="maodian";
		}else if($("#editstartbalance").val()<=0){
			$("#editstartbalance_").html("最低价格不能为0!").css("color", "RED");
			return false;
		}else if($("#editaddbalance").val()<=0){
			$("#editaddbalance_").html("竞价增加价格不能为0!").css("color", "RED");
			return false;
		}else{
			$("#form1").submit();
		}
	}
</script>
<a name="maodian"></a>
<div class="wrap_content left_module">
  <form action="editAuctionSub.action" method="post" enctype="multipart/form-data" id="form1" class="formview jNice">
  	<input type="hidden" name="auid" value="${dto.auction.id }"/>
  	<input type="hidden" name="mid" value="10007">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">编辑竞拍</a></li>
	  </ul>
	  <a href="index.action?mid=10007" class="return" title="返回"></a>
	</div>
 	<dl>
	 	<dt>商品名称</dt>
		<dd>
			<input id="editname" onblur="nameonblur()" type="text" class="text-medium" name="name" value="${dto.auction.title }">
			<span class="must">*</span>
			<span id="editname_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>简介</dt>
		<dd>
			<textarea  maxlength="200" name="detail" placeholder="简介内容方便您记忆，不会在页面中显示">${dto.auction.description }</textarea>
	 		<span class="notice">最多不超过200个汉字</span>
		</dd>
	</dl>
	<dl>
	 	<dt>活动开始时间</dt>
		<dd>
			<input id="startTime" onblur="startTimeonblur()" type="text" value="<s:date name="dto.auction.startTime" format="yyyy-MM-dd HH:mm:ss"/>"
					name="startTime" class="Wdate"
					onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					readonly="readonly" />
			<span class="must">*</span>
			<span id="startTime_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>活动结束时间</dt>
		<dd>
			<input id="endTime" onblur="endTimeonblur()" type="text" value="<s:date name="dto.auction.endTime" format="yyyy-MM-dd HH:mm:ss"/>" 
					name="endTime" class="Wdate"	onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					readonly="readonly" />
			<span class="must">*</span>
			<span id="endTime_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>开始前提示语</dt>
		<dd>
			<input type="text" class="text-long" name="startnote" value="${dto.auction.startnote }"><span class="notice">提示用户该申请还没开始</span>
		</dd>
	</dl>
	<dl>
	 	<dt>结束后提示语</dt>
		<dd>
			<input type="text" class="text-long" name="endnote" value="${dto.auction.endnote }"><span class="notice">提示用户该申请已结束</span>
		</dd>
	</dl>
	<dl>
	 	<dt>商品图片</dt>
		<dd>
			<input type="file" name="img">
		</dd>
		<dd>
			<img id="img" src="${imgDomain }${dto.auction.simg }" style="width: 100px">
			<input type="hidden" name="imgurl" value="${dto.auction.simg }">
		</dd>
	</dl>
	<dl>
	 	<dt>商品链接</dt>
		<dd>
			<input type="text" class="text-medium" value="${dto.auction.url }" name="url"/>
		</dd>
	</dl>
	<dl>
	 	<dt>起拍积分</dt>
		<dd>
			<input id="editstartbalance" onblur="editstartbalanceonblur()" type="text" class="text-medium" name="startbalance" value="${dto.auction.startbalance }" onkeyup="this.value = this.value.replace(/\D/g,'');"/>
			<span class="must">*</span>
			<span id="editstartbalance_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>每次竞价增加价格</dt>
		<dd>
			<input id="editaddbalance" onblur="editbalanceonblur()" type="text" class="text-medium" name="addbalance" value="${dto.auction.addbalance }" onkeyup="this.value = this.value.replace(/\D/g,'');"/>
			<span class="must">*</span>
			<span id="editaddbalance_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>延迟时间</dt>
		<dd>
			<input type="text" class="text-medium" value="${dto.auction.addsecond }" name="addsecond" onkeyup="this.value = this.value.replace(/\D/g,'');"/>秒
		</dd>
	</dl>
	<dl>
	 	<dt>中标用户填写信息</dt>
		<dd>
			<label class="forradio ml10"><input type="checkbox" name="userName" value="Y"  <s:if test='dto.auction.userName=="Y"'>checked="checked"</s:if>>姓名</label>
			<label class="forradio ml10"><input type="checkbox" name="userPhone" value="Y"  <s:if test='dto.auction.userPhone=="Y"'>checked="checked"</s:if>>手机</label>
			<label class="forradio ml10"><input type="checkbox" name="userEmail" value="Y" <s:if test='dto.auction.userEmail=="Y"'>checked="checked"</s:if>>邮箱</label>
			<label class="forradio ml10"><input type="checkbox" name="userLocation" value="Y" <s:if test='dto.auction.userLocation=="Y"'>checked="checked"</s:if>>地区</label>
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