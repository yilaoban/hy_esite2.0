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
	 	var editor = CKEDITOR.instances.editor1;
	 	$("#ckvalue").val(editor.getData());
	 	
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
  <form action="update_spread_design.action" method="post" id="myform" name="myform" enctype="multipart/form-data" onsubmit="return checkmyfrom();" class="formview jNice">
  	<input type="hidden" name="mid" value="${mid }">
  	<input type="hidden" name="spreadid" value="${spreadid }">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">编辑口碑营销</a></li>
	  </ul>
	  <a href="/${oname }/interact/index.action?mid=10010" class="return" title="返回"></a>
	</div>
  	  <dl>
	 	<dt>标题</dt>
		<dd>
			<input onblur="checkTitle1()" type="text" class="text-medium" name="dto.spread.title" id="title" value="${dto.spread.title}" onblur="checkTitle1()"><span class="must">*</span><span id="title_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>简介</dt>
		<dd>
			<textarea  maxlength="200" name="dto.spread.content" placeholder="简介内容方便您记忆，不会在页面中显示">${dto.spread.content }</textarea>
		</dd>
	</dl>
	<dl>
	 	<dt>活动开始时间</dt>
		<dd>
			<input type="text" id="start" onblur="checkTitle2()" name="dto.spread.starttime" value="<s:date name="dto.spread.starttime" format="yyyy-MM-dd HH:mm:ss"/>" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'end\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  class="Wdate"><span class="must">*</span><span id="start_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>活动结束时间</dt>
		<dd>
			<input type="text" id="end" onblur="checkTitle3()" name="dto.spread.endtime" value="<s:date name="dto.spread.endtime" format="yyyy-MM-dd HH:mm:ss"/>" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'start\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  class="Wdate"><span class="must">*</span><span id="end_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>开始前提示语</dt>
		<dd>
			<input type="text" class="text-long" name="dto.spread.startnote" value="${dto.spread.startnote }"><span class="notice">提示用户该申请还没开始</span>
		</dd>
	</dl>
	<dl>
	 	<dt>结束后提示语</dt>
		<dd>
			<input type="text" class="text-long" name="dto.spread.endnote" value="${dto.spread.endnote }"><span class="notice">提示用户该申请已结束</span>
		</dd>
	</dl>
	<!-- 
	<dl>
	 	<dt>互动次数限制</dt>
		<dd>
			<input type="text" class="text-medium" name="dto.spread.sharelimit" value="${dto.spread.sharelimit}" onkeyup="this.value = this.value.replace(/\D/g,'');">次
		</dd>
	</dl>
	<dl>
	 	<dt>赠送积分</dt>
		<dd>
			<input type="text" class="text-medium" name="dto.spread.balance" value="${dto.spread.balance}" onkeyup="this.value = this.value.replace(/\D/g,'');">
		</dd>
	</dl>
	 -->
	<dl>
	 	<dt>分享类型</dt>
		<dd>
			<label class="forradio"><input type="radio" name="dto.spread.type" value="SRE" <s:if test='dto.spread.type == "SRE"'> checked="checked" </s:if>/>内容随机直发</label>
 			<label class="forradio"><input type="radio" name="dto.spread.type" value="FWD" <s:if test='dto.spread.type == "FWD"'> checked="checked" </s:if>/>选择内容直发</label>
 			<label class="forradio"><input type="radio" name="dto.spread.type" value="FAC" <s:if test='dto.spread.type == "FAC"'> checked="checked" </s:if>/>转发并评论</label>
		</dd>
	</dl>
	<dl>
	 	<dt>分享上限</dt>
		<dd>
			<input type="text" class="text-small" name="dto.spread.totallimit" value="${dto.spread.totallimit }" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')">次/人
			<span class="notice"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>分享后关联抽奖</dt>
		<dd>
 			<label class="forradio"><input type="radio" name="dto.spread.islottery" value="Y" <s:if test='dto.spread.islottery == "Y"'> checked="checked" </s:if> onclick="$('.type').show()"/>是 </label>
			<label class="forradio"><input type="radio" name="dto.spread.islottery" value="N" <s:if test='dto.spread.islottery == "N"'> checked="checked" </s:if> onclick="$('.type').hide()"/>否</label>
		</dd>
	</dl>
	<dl class="type" <s:if test='dto.spread.islottery == "N"'>style="display: none"</s:if>>
	 	<dt>抽奖方式</dt>
		<dd class="pt10">
			<select name="dto.type" id="lottery" onchange="getContent()">
				  <option value ="N">请选择抽奖方式</option>
				  <option value ="Z" <s:if test='dto.type == "Z"'> selected="selected" </s:if>>大转盘</option>
				  <option value ="L" <s:if test='dto.type == "L"'> selected="selected" </s:if>>砸金蛋</option>
				  <option value ="G" <s:if test='dto.type == "G"'> selected="selected" </s:if>>刮刮乐</option>
				  <option value ="Y" <s:if test='dto.type == "Y"'> selected="selected" </s:if>>摇一摇</option>
			</select>
		</dd>
		<dd id="Z" <s:if test='dto.type != "Z"'> style="display: none" </s:if>>
			<select name="dto.zlotteryid">
				<option value ="0">请选择一项</option>
				<s:iterator value="zlotteryList" var="f">
				  <option value ="${f.id  }" <s:if test='dto.spread.lotteryid == #f.id'>selected="selected" </s:if>>${f.name }</option>
				</s:iterator>  
			</select>
		</dd>
		<dd id="L" <s:if test='dto.type != "L"'> style="display: none" </s:if>>
			<select name="dto.llotteryid">
				<option value ="0" >请选择一项</option>
				<s:iterator value="llotteryList" var="f" >
				  <option value ="${f.id}" <s:if test='dto.spread.lotteryid == #f.id'>selected="selected" </s:if>>${f.name}</option>
				</s:iterator>  
			</select>
		</dd>
		<dd id="G" <s:if test='dto.type != "G"'> style="display: none" </s:if>>
			<select name="dto.glotteryid">
				<option value ="0" >请选择一项</option>
				<s:iterator value="glotteryList" var="f" >
				  <option value ="${f.id}" <s:if test='dto.spread.lotteryid == #f.id'>selected="selected" </s:if>>${f.name}</option>
				</s:iterator>  
			</select>
		</dd>
		<dd id="Y" <s:if test='dto.type != "Y"'> style="display: none" </s:if>>
			<select name="dto.ylotteryid" class="dis">
				<option value ="0">请选择一项</option>
				<s:iterator value="ylotteryList" var="f" >
				  <option value ="${f.id}" <s:if test='dto.spread.lotteryid == #f.id'>selected="selected" </s:if>>${f.name}</option>
				</s:iterator>  
			</select>
		</dd>
	</dl>
	<dl class="type" <s:if test='dto.spread.islottery == "N"'>style="display: none"</s:if>>
	 	<dt>分享增加抽奖机会</dt>
		<dd>
			<input type="text" class="text-medium" name="dto.spread.lotterychance" value="${dto.spread.lotterychance}" onkeyup="this.value = this.value.replace(/\D/g,'');" id="lotterychance">次/人 <span id="researchchance_"></span>
			<span class="notice">（0表示不增加）</span>
		</dd>
	</dl>
	<dl class="type" style="display: none">
	 	<dt>增加抽奖机会上限</dt>
		<dd>
			<input type="text" class="text-small" name="dto.spread.maxlotterychance" value="${dto.spread.maxlotterychance }" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')">次/人
			<span class="notice">（0表示不增加）</span>
		</dd>
	</dl>
	<dl>
	 	<dt>分享后赠送积分</dt>
		<dd>
			<input type="text" class="text-small" name="dto.spread.balance" value="${dto.spread.balance }" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')">积分/人
			<span class="notice">（0表示不赠送）</span>
		</dd>
	</dl>
	<dl>
	 	<dt>&nbsp;</dt>
		<dd>
			<input type="submit" value="提交">
			<input type="reset" value="重置">
		</dd>
	</dl>	
 </form>
</div>
