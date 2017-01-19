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
			$('#startnote').val("调研还未开始");
		}
		if($('#endnote').val().trim() == ""){
			$('#endnote').val("调研已经结束");
		}
		var obji = document.getElementById("researchchance");
		if(!(/^\d*$/.test(obji.value)) || obji.value == ""){
				$("#start_").html("");
				$("#end_").html("");
				$("#researchchance_").html("请输入次数").css("color", "RED");
				return false;			
		}
		var islottery=$('input:radio[name="dto.research.islottery"]:checked').val();
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
  <form action="update_research_design.action" method="post" id="myform" name="myform" enctype="multipart/form-data" onsubmit="return checkmyfrom();" class="formview jNice">
  	<input type="hidden" name="researchid" value="${researchid }">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">编辑调研</a></li>
	  </ul>
	  <a href="/${oname }/interact/index.action?mid=10006" class="return" title="返回"></a>
	</div>
	  <dl>
	 	<dt>标题</dt>
		<dd>
			<input type="text" class="text-medium" name="dto.research.title" id="title" value="${dto.research.title}" onblur="checkTitle1()"><span class="must">*</span><span id="title_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>简介</dt>
		<dd>
			<textarea  maxlength="200" name="dto.research.content">${dto.research.content}</textarea>
	 		<span class="notice">最多不超过200个汉字</span>
		</dd>
	</dl>
	<dl>
	 	<dt>活动开始时间</dt>
		<dd>
			<input type="text" id="start" name="dto.research.starttime" value="<s:date name="dto.research.starttime" format="yyyy-MM-dd HH:mm:ss"/>" onfocus="WdatePicker({	maxDate:'#F{$dp.$D(\'end\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  class="Wdate"><span class="must">*</span><span id="start_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>活动结束时间</dt>
		<dd>
			<input type="text" id="end" name="dto.research.endtime" value="<s:date name="dto.research.endtime" format="yyyy-MM-dd HH:mm:ss"/>" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'start\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  class="Wdate"><span class="must">*</span><span id="end_"></span>
		</dd>
	</dl>
	 <dl>
	 	<dt>开始前提示语</dt>
		<dd>
			<input type="text" class="text-long" name="dto.research.startnote" value="${dto.research.startnote }" placeholder="调研还未开始" id="startnote"><span class="notice">提示用户该调研还没开始</span>
		</dd>
	</dl>
	<dl>
	 	<dt>结束后提示语</dt>
		<dd>
			<input type="text" class="text-long" name="dto.research.endnote" value="${dto.research.endnote }" placeholder="调研已经结束" id="endnote"><span class="notice">提示用户该调研已结束</span>
		</dd>
	</dl>
	<dl>
	 	<dt>调研上限</dt>
		<dd>
			<input type="text" class="text-medium" name="dto.research.totallimit" value="${dto.research.totallimit }" onkeyup="this.value=this.value.replace(/\D/g,'')">次/人
			<span class="notice"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>每日调研上限</dt>
		<dd>
			<input type="text" class="text-medium" name="dto.research.daylimit" value="${dto.research.daylimit }" onkeyup="this.value=this.value.replace(/\D/g,'')">次/人
			<span class="notice"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>调研后关联抽奖</dt>
		<dd>
 			<label class="forradio"><input type="radio" name="dto.research.islottery" value="Y" <s:if test='dto.research.islottery == "Y"'> checked="checked" </s:if> onclick="$('.type').show()"/>是</label>
			<label class="forradio"><input type="radio" name="dto.research.islottery" value="N" <s:if test='dto.research.islottery == "N"'> checked="checked" </s:if> onclick="$('.type').hide()"/>否</label>
		</dd>
	</dl>
	<dl class="type" <s:if test='dto.research.islottery == "N"'>style="display: none"</s:if>>
	 	<dt>抽奖方式</dt>
		<dd class="pt10">
			<select name="dto.type" id="lottery" onchange="getContent()">
				  <option value ="N">请选择抽奖方式</option>
				  <option value ="Z" <s:if test='dto.type == "Z"'> selected="selected" </s:if>>大转盘</option>
				  <option value ="L" <s:if test='dto.type == "L"'> selected="selected" </s:if>>砸金蛋</option>
				  <option value ="Y" <s:if test='dto.type == "Y"'> selected="selected" </s:if>>摇一摇</option>
			</select> 
			<span id="lotteryinfo" class="must"></span><a name="md"></a>
		</dd>
		<dd id="Z" <s:if test='dto.type != "Z"'> style="display: none" </s:if>>
			<select name="dto.zlotteryid" class="dis" id="zlotteryid">
				<option value ="0" >请选择一项</option>
				<s:iterator value="zlotteryList" var="f">
				  <option value ="${f.id  }" <s:if test='dto.research.lotteryid == #f.id'>selected="selected" </s:if>>${f.name }</option>
				</s:iterator>  
			</select>
		</dd>
		<dd id="L" <s:if test='dto.type != "L"'> style="display: none" </s:if>>
			<select name="dto.llotteryid" class="dis" id="llotteryid">
				<option value ="0" >请选择一项</option>
				<s:iterator value="llotteryList" var="f" >
				  <option value ="${f.id}" <s:if test='dto.research.lotteryid == #f.id'>selected="selected" </s:if>>${f.name}</option>
				</s:iterator>  
			</select>
		</dd>
		<dd id="G" <s:if test='dto.type != "G"'> style="display: none" </s:if>>
			<select name="dto.glotteryid" class="dis" id="glotteryid">
				<option value ="0" >请选择一项</option>
				<s:iterator value="glotteryList" var="f" >
				  <option value ="${f.id}" <s:if test='dto.research.lotteryid == #f.id'>selected="selected" </s:if>>${f.name}</option>
				</s:iterator>  
			</select>
		</dd>
		<dd id="Y" <s:if test='dto.type != "Y"'> style="display: none" </s:if>>
			<select name="dto.ylotteryid" class="dis" id="ylotteryid">
				<option value ="0">请选择一项</option>
				<s:iterator value="ylotteryList" var="f" >
				  <option value ="${f.id}" <s:if test='dto.research.lotteryid == #f.id'>selected="selected" </s:if>>${f.name}</option>
				</s:iterator>  
			</select>
		</dd>
	</dl>
	<dl class="type" <s:if test='dto.research.islottery == "N"'>style="display: none"</s:if>>
	 	<dt>调研增加抽奖机会</dt>
		<dd>
			<input type="text" class="text-medium" name="dto.research.lotterychance" value="${dto.research.lotterychance }" id="researchchance" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')">次 /人<span id="researchchance_"></span>
			<span class="notice">（0表示不增加）</span>
		</dd>
	</dl>
	<dl class="type" <s:if test='dto.research.islottery == "N"'>style="display: none"</s:if>>
	 	<dt>增加抽奖机会上限</dt>
		<dd>
			<input type="text" class="text-small" name="dto.research.maxlotterychance" value="${dto.research.maxlotterychance }" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')">次/人
			<span class="notice">（0表示不增加）</span>
		</dd>
	</dl>
	<dl>
	 	<dt>调研后赠送积分</dt>
		<dd>
			<input type="text" class="text-small" name="dto.research.balance" value="${dto.research.balance }" onkeyup="value=value.replace(/[^\d-]/g,'')">积分/人
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
