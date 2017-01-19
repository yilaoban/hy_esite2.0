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
		if($('#startnote').val().trim() == ""){
			$('#startnote').val("测评还未开始");
		}
		if($('#endnote').val().trim() == ""){
			$('#endnote').val("测评已经结束");
		}
		var obji = document.getElementById("examchance");
		if(!(/^\d*$/.test(obji.value)) || obji.value == ""){
				$("#start_").html("");
				$("#end_").html("");
				$("#examchance_").html("请输入次数").css("color", "RED");
				return false;			
		}
		var islottery=$('input:radio[name="dto.exam.islottery"]:checked').val();
		if(islottery == 'Y'){
			if($('#zlotteryid').val() == 0 && $('#llotteryid').val() == 0 && $('#glotteryid').val() == 0 && $('#ylotteryid').val() == 0){
				$("#lotteryinfo").html("请选择抽奖方式").css("color", "RED");
				location.href = "#md"; 
				return false;
			}
		}
	}
</script>  
   <a name="maodian"></a>       
<div class="wrap_content left_module">
  <form action="save_exam_design.action" method="post" id="myform" name="myform" enctype="multipart/form-data" onsubmit="return checkmyfrom();" class="formview jNice">
  	<input type="hidden" name="mid" value="${mid }">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">新增测评</a></li>
	  </ul>
	  <img src="/images/help.jpg" height="20" width="20" onclick="help_exam()" title="帮助文档" style="cursor:pointer">
	  <a href="/${oname }/interact/index.action?mid=10015" class="return" title="返回"></a>
	</div>
	  <dl>
	 	<dt>标题</dt>
		<dd>
			<input type="text" class="text-medium" name="dto.exam.title" id="title" onblur="checkTitle1()" placeholder="此标题仅方便您记忆，请任意填写"><span class="must">*</span><span id="title_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>简介</dt>
		<dd>
			<textarea  maxlength="200" name="dto.exam.content" placeholder="简介内容方便您记忆，不会在页面中显示"></textarea>
	 		<span class="notice">最多不超过200个汉字</span>
		</dd>
	</dl>
	<dl>
	 	<dt>活动开始时间</dt>
		<dd>
			<input type="text" id="start" placeholder="请选择开始时间" onblur="checkTitle2()" name="dto.exam.starttime" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'end\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  class="Wdate"><span class="must">*</span><span id="start_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>活动结束时间</dt>
		<dd>
			<input type="text" id="end" placeholder="请选择结束时间" onblur="checkTitle3()" name="dto.exam.endtime" onfocus="WdatePicker({	minDate:'#F{$dp.$D(\'start\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  class="Wdate"><span class="must">*</span><span id="end_"></span>
		</dd>
	</dl>
	 <dl>
	 	<dt>开始前提示语</dt>
		<dd>
			<input type="text" class="text-long" name="dto.exam.startnote" placeholder="测评还未开始" id="startnote"><span class="notice">提示用户该测评还没开始</span>
		</dd>
	</dl>
	<dl>
	 	<dt>结束后提示语</dt>
		<dd>
			<input type="text" class="text-long" name="dto.exam.endnote" placeholder="测评已经结束" id="endnote"><span class="notice">提示用户该测评已结束</span>
		</dd>
	</dl>
	<dl>
	 	<dt>测评上限</dt>
		<dd>
			<input type="text" class="text-medium" name="dto.exam.totallimit" value="1" onkeyup="this.value=this.value.replace(/\D/g,'')" >次/人
			<span class="notice"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>每日测评上限</dt>
		<dd>
			<input type="text" class="text-medium" name="dto.exam.daylimit" value="1" onkeyup="this.value=this.value.replace(/\D/g,'')" >次/人
			<span class="notice"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>测评后关联抽奖</dt>
		<dd>
 			<label class="forradio"><input type="radio" name="dto.exam.islottery" value="Y"  onclick="$('.type').show()"/>是 </label>
			<label class="forradio"><input type="radio" name="dto.exam.islottery" value="N" checked="checked" onclick="$('.type').hide()"/>否</label>
		</dd>
	</dl>
	<dl style="display: none" class="type">
	 	<dt>抽奖方式</dt>
		<dd class="pt10">
			<select name="dto.type" id="lottery" onchange="getContent()">
				  <option value ="N">请选择抽奖方式</option>
				  <option value ="Z">大转盘</option>
				  <option value ="L">砸金蛋</option>
				  <option value ="Y">摇一摇</option>
			</select>
			<span id="lotteryinfo" class="must"></span><a name="md"></a>
		</dd>
		<dd id="Z">
			<select name="dto.zlotteryid" class="dis" id="zlotteryid">
				<option value ="0" selected="selected">请选择一项</option>
				<s:iterator value="zlotteryList" var="f">
				<option value ="${f.id  }">${f.name }</option>
				</s:iterator>  
			</select>
		</dd>
		<dd id="L" style="display:none">
			<select name="dto.llotteryid" class="dis" id="llotteryid">
				<option value ="0" selected="selected">请选择一项</option>
				<s:iterator value="llotteryList" var="f" >
				  <option value ="${f.id}">${f.name}</option>
				</s:iterator>  
			</select>
		</dd>
		<dd id="G" style="display:none">
			<select name="dto.glotteryid" class="dis" id="glotteryid">
				<option value ="0" selected="selected">请选择一项</option>
				<s:iterator value="glotteryList" var="f" >
				  <option value ="${f.id}">${f.name}</option>
				</s:iterator>  
			</select>
		</dd>
		<dd id="Y" style="display:none">
			<select name="dto.ylotteryid" class="dis" id="ylotteryid">
				<option value ="0" selected="selected">请选择一项</option>
				<s:iterator value="ylotteryList" var="f" >
				  <option value ="${f.id}">${f.name}</option>
				</s:iterator>  
			</select>
		</dd>
	</dl>
	<dl class="type" style="display: none">
	 	<dt>测评增加抽奖机会</dt>
		<dd>
			<input type="text" class="text-small" name="dto.exam.lotterychance" value="1" id="examchance" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">次 /人<span id="examchance_"></span>
			<span class="notice">（0表示不增加）</span>
		</dd>
	</dl>
	<dl class="type" style="display: none">
	 	<dt>增加抽奖机会上限</dt>
		<dd>
			<input type="text" class="text-small" name="dto.exam.maxlotterychance" value="1" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')">次/人
			<span class="notice">（0表示不增加）</span>
		</dd>
	</dl>
	<dl>
	 	<dt>测评后赠送积分</dt>
		<dd>
			<input type="text" class="text-small" name="dto.exam.balance" value="0" onkeyup="value=value.replace(/[^\d-]/g,'')">积分/人
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
