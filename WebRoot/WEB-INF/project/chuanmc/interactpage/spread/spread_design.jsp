<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	 function getContent(){
	 	 var obj = document.getElementById("lottery");
	 	 var id = obj.value;
	 	 if(id == "Z"){
	 	 	$('#Z').hide(); $('#G').hide();$('#L').hide();$('#Y').hide();
	 	 	$('#Z').show();
	 	 }else if(id == "G"){
	 	 	$('#Z').hide(); $('#G').hide();$('#L').hide();$('#Y').hide();
	 	 	$('#G').show();
	 	 }else if(id == "L"){
	 	 	$('#Z').hide(); $('#G').hide();$('#L').hide();$('#Y').hide();
	 		$('#L').show();
	 	 }else if(id == "Y"){
	 	 	$('#Z').hide(); $('#G').hide();$('#L').hide();$('#Y').hide();
	 	 	$('#Y').show();
	 	 }
	 }
	 
	 function checkTitle1(){
		if($("#title").val()==""){
			$("#title_").html("请输入标题").css("color", "RED");
			return false;
		}else{
			$("#title_").html("");
			return true;
		}
	}
	 
	 function checkTitle2(){
		if($("#start").val()==""){
			$("#start_").html("请输入开始时间").css("color", "RED");
			return false;
		}else{
			$("#start_").html("");
			return true;
		}
	}
	function checkTitle3(){
		if($("#end").val()==""){
			$("#end_").html("请输入结束时间").css("color", "RED");
			return false;
		}else{
			$("#end_").html("");
			return true;
		}
	}
	 function checkmyfrom(){
	 //	var editor = CKEDITOR.instances.editor1;
	 //	$("#ckvalue").val(editor.getData());
	 	
		if($("#title").val()==""){
			$("#title_").html("请输入标题").css("color", "RED");
			window.location.hash="maodian";
			return false;
		}else if(document.getElementById("start").value==""){
			$("#start_").html("请填写开始时间").css("color", "RED");
			window.location.hash="maodian";
			return false;
		}else if(document.getElementById("end").value==""){
			$("#end_").html("请填写结束时间").css("color", "RED");
			window.location.hash="maodian";
			return false;
		}
		var obji = document.getElementById("lotterychance");
		if(!(/^\d*$/.test(obji.value)) || obji.value == ""){
				$("#start_").html("");
				$("#end_").html("");
				$("#lotterychance_").html("请输入次数").css("color", "RED");
				return false;			
		}
	}
</script>  
   <a name="maodian"></a>            
<div class="wrap_content left_module">
  <form action="save_spread_design.action" method="post" id="myform" name="myform" enctype="multipart/form-data" onsubmit="return checkmyfrom();" class="formview jNice">
  	<input type="hidden" name="mid" value="${mid }">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href=""> 新增口碑营销</a></li>
	  </ul>
	  <img src="/images/help.jpg" height="20" width="20" onclick="help_spread()" title="帮助文档" style="cursor:pointer">
	  <a href="/${oname }/interact/index.action?mid=10010" class="return" title="返回"></a>
	</div>
	  	<dl>
	 	<dt>标题</dt>
		<dd>
			<input type="text" class="text-medium" name="dto.spread.title" id="title" onblur="checkTitle1()" placeholder="此标题仅方便您记忆，请任意填写"><span class="must">*</span><span id="title_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>简介</dt>
		<dd>
			<textarea  maxlength="200" name="dto.spread.content" placeholder="简介内容方便您记忆，不会在页面中显示"></textarea>
		</dd>
	</dl>
	<dl>
	 	<dt>活动开始时间</dt>
		<dd>
			<input type="text" id="start" name="dto.spread.starttime" onfocus="WdatePicker({	maxDate:'#F{$dp.$D(\'end\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  class="Wdate"><span class="must">*</span><span id="start_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>活动结束时间</dt>
		<dd>
			<input type="text" id="end" name="dto.spread.endtime" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'start\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  class="Wdate"><span class="must">*</span><span id="end_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>开始前提示语</dt>
		<dd>
			<input type="text" class="text-long" name="dto.spread.startnote"><span class="notice">提示用户该申请还没开始</span>
		</dd>
	</dl>
	<dl>
	 	<dt>结束后提示语</dt>
		<dd>
			<input type="text" class="text-long" name="dto.spread.endnote"><span class="notice">提示用户该申请已结束</span>
		</dd>
	</dl>
	<!-- 
	<dl>
	 	<dt>互动次数限制</dt>
		<dd>
			<input type="text" class="text-medium" name="dto.spread.sharelimit" value="0" onkeyup="this.value = this.value.replace(/\D/g,'');">次/人
		</dd>
	</dl>
	<dl>
	 	<dt>口碑类型</dt>
		<dd>
			<label class="forradio"><input type="radio" name="dto.spread.htype" value="0" checked onclick="$('#sharetype').show();$('#wxsharetype').hide()"/>微博口碑</label>
 			<label class="forradio"><input type="radio" name="dto.spread.htype" value="1" onclick="$('#sharetype').hide();$('#wxsharetype').show()"/>微信口碑</label>
		</dd>
	</dl>
	<dl id="wxsharetype" style="display: none">
	 	<dt>分享类型</dt>
		<dd>
			<label class="forradio"><input type="radio" name="dto.spread.wxsharetype" value="FCF" checked/>分享页面至朋友圈</label>
 			<label class="forradio"><input type="radio" name="dto.spread.wxsharetype" value="XCF" />选择内容分享至朋友圈</label>
		</dd>
	</dl>
	 -->
	<dl id="sharetype">
	 	<dt>分享类型</dt>
		<dd>
			<label class="forradio"><input type="radio" name="dto.spread.type" value="SRE" checked/>内容随机直发</label>
 			<label class="forradio"><input type="radio" name="dto.spread.type" value="FWD" />选择内容直发</label>
 			<label class="forradio"><input type="radio" name="dto.spread.type" value="FAC" />转发并评论</label>
		</dd>
	</dl>
	<dl>
	 	<dt>分享上限</dt>
		<dd>
			<input type="text" class="text-small" name="dto.spread.totallimit" value="0" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')">次/人
			<span class="notice"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>分享后关联抽奖</dt>
		<dd>
 			<label class="forradio"><input type="radio" name="dto.spread.islottery" value="Y"  onclick="$('.type').show()"/>是 </label>
			<label class="forradio"><input type="radio" name="dto.spread.islottery" value="N" checked="checked" onclick="$('.type').hide()"/>否</label>
		</dd>
	</dl>
	<dl style="display: none" class="type">
	 	<dt>抽奖方式</dt>
		<dd class="pt10">
			<select name="dto.type" id="lottery" onchange="getContent()">
				  <option value ="N">请选择抽奖方式</option>
				  <option value ="Z">大转盘</option>
				  <option value ="L">砸金蛋</option>
				  <option value ="G">刮刮乐</option>
				  <option value ="Y">摇一摇</option>
			</select>
		</dd>
		<dd id="Z">
			<select name="dto.zlotteryid" class="dis">
				<option value ="0" selected="selected">请选择一项</option>
				<s:iterator value="zlotteryList" var="f">
				<option value ="${f.id  }">${f.name }</option>
				</s:iterator>  
			</select>
		</dd>
		<dd id="L" style="display:none">
			<select name="dto.llotteryid" class="dis">
				<option value ="0" selected="selected">请选择一项</option>
				<s:iterator value="llotteryList" var="f" >
				  <option value ="${f.id}">${f.name}</option>
				</s:iterator>  
			</select>
		</dd>
		<dd id="G" style="display:none">
			<select name="dto.glotteryid" class="dis">
				<option value ="0" selected="selected">请选择一项</option>
				<s:iterator value="glotteryList" var="f" >
				  <option value ="${f.id}">${f.name}</option>
				</s:iterator>  
			</select>
		</dd>
		<dd id="Y" style="display:none">
			<select name="dto.ylotteryid" class="dis">
				<option value ="0" selected="selected">请选择一项</option>
				<s:iterator value="ylotteryList" var="f" >
				  <option value ="${f.id}">${f.name}</option>
				</s:iterator>  
			</select>
		</dd>
	</dl>
	<dl class="type" style="display: none">
	 	<dt>分享增加抽奖机会</dt>
		<dd>
			<input type="text" class="text-small" name="dto.spread.lotterychance" value="0" onkeyup="this.value = this.value.replace(/\D/g,'');" id="lotterychance">次/人 <span id="researchchance_"></span>
			<span class="notice">（0表示不增加）</span>
		</dd>
	</dl>
	<dl class="type" style="display: none">
	 	<dt>增加抽奖机会上限</dt>
		<dd>
			<input type="text" class="text-small" name="dto.spread.maxlotterychance" value="0" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')">次/人
			<span class="notice">（0表示不增加）</span>
		</dd>
	</dl>
	<dl>
	 	<dt>分享后赠送积分</dt>
		<dd>
			<input type="text" class="text-small" name="dto.spread.balance" value="0" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')">积分/人
			<span class="notice">（0表示不赠送）</span>
		</dd>
	</dl>
	<dl>
	 	<dt>&nbsp;</dt>
		<dd>
			<input type="submit" class="btn btn-primary" value="提交">
			<input type="reset" class="btn" value="重置">
		</dd>
	</dl>	
 </form>
</div>
