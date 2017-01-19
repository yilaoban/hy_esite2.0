<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
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
		if($("#startTime").val()==""){
			$("#startTime_").html("请输入开始时间").css("color", "RED");
			return false;
		}else{
			$("#startTime_").html("");
			return true;
		}
	}
	function checkTitle3(){
		if($("#endTime").val()==""){
			$("#endTime_").html("请输入结束时间").css("color", "RED");
			return false;
		}else{
			$("#endTime_").html("");
			return true;
		}
	}
	
	function checkSub(){
		//var editor = CKEDITOR.instances.editor1;
	 	//$("#ckvalue").val(editor.getData());
		if($("#title").val()==""){
			$("#title_").html("请输入标题").css("color", "RED");
			window.location.hash="maodian";
		}else if(document.getElementById("startTime").value==""){
			$("#startTime_").html("请输入开始时间").css("color", "RED");
			window.location.hash="maodian";
		}else if(document.getElementById("endTime").value==""){
			$("#endTime_").html("请输入结束时间").css("color", "RED");
			window.location.hash="maodian";
		}else{
			$("#form1").submit();
		}
	}
	
</script>
<a name="maodian"></a>   
<div class="wrap_content left_module">
  <form action="save_ggl_sub.action?mid=${mid}" method="post" enctype="multipart/form-data" id="form1" class="formview jNice">
  	<input type="hidden" name="htype" value="G" /> 
  	<input type="hidden" name="mid" value="10005">
  	<div class="toolbar pb10">
  	  <ul class="c_switch">
	  <li class="selected"><a href="">新增刮刮乐</a></li>
	  </ul>
	  <img src="/images/help.jpg" height="20" width="20" onclick="help_ggl()" title="帮助文档" style="cursor:pointer">
	  <a href="/${oname }/interact/index.action?mid=10005" class="return" title="返回"></a>
	</div>
	<dl>
	 	<dt>标题</dt>
		<dd>
			<input id="title" type="text" placeholder="此标题仅方便您记忆，请任意填写" onblur="checkTitle1()" class="text-medium" name="name" value="${name }">
			<span class="must">*</span>
			<span id="title_"></span>
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
			<input id="startTime" onblur="checkTitle2()" type="text" value="${startTime }" name="startTime" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
			<span class="must">*</span>
			<span id="startTime_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>活动结束时间</dt>
		<dd>
			<input id="endTime" onblur="checkTitle3()" type="text" value="${endTime }" name="endTime" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
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
	<dl id="ruletype">
	 	<dt>抽奖资格</dt>
		<dd>
			<label class="forradio"><input type="radio" name="usertype" value="A" checked="checked" onclick="$('#usertype').hide();$('#jfdl').hide();" <s:if test='usertype=="A"'>checked="checked"</s:if>>所有用户</label> 
			<label class="forradio"><input type="radio" name="usertype" value="S" onclick="$('#jfdl').hide();" <s:if test='usertype=="S"'>checked="checked"</s:if>>微信粉丝</label>
			<label class="forradio"><input type="radio" name="usertype" value="J" onclick="$('#jfdl').show();$('#usertype').hide();" <s:if test='usertype=="J"'>checked="checked"</s:if>>积分用户</label>
			<span><strong style="color:red"><s:fielderror theme="" fieldName="error4"/></strong></span>
		</dd>
	</dl>
	<dl id="jfdl" <s:if test='usertype!="J"'>style="display: none"</s:if>>
	 	<dt>所需积分</dt>
		<dd>
			<input type="text" class="text-long" name="jf">
			<span><strong style="color:red"><s:fielderror theme="" fieldName="error5"/></strong></span>
		</dd>
	</dl>
		<dl id="usertype" style="display: none">
	 	<dt></dt>
		<dd>
			<label class="forradio"><input type="checkbox" name="wbgroup" value="Y" >易博组</label>
			<input type="hidden" name="wbgroupid" id="wbgroupid" value="0"/>
			<input type="text" class="text-medium" value="" id="wbgroup" placeholder="请选择一组易博" name="wbgroupname"> 
			<input type="button" value="选择" onclick="showwbgroup(true)">
		</dd>
		<dd>
			<label class="forradio"><input type="checkbox" name="wxg" value="1" >微信粉丝</label>
		</dd>
	</dl>
	<dl>
	 	<dt>抽奖机会</dt>
		<dd>
			用户初始抽奖机会<input type="text" class="text-small" name="usertotal" value="${usertotal }"/>次/人
		</dd>
		<dd>
			每日抽奖次数上限<input type="text" class="text-small" name="userdaynum" value="${userdaynum }"/>次/人
		</dd>
	</dl>
	<dl>
	 	<dt>活动中奖率</dt>
		<dd>
			万分之<input type="text" class="text-small" name="zjl" value="${zjl }" onkeyup="this.value = this.value.replace(/\D/g,'');"/>
		</dd>
	</dl>
	<dl>
	 	<dt>&nbsp;</dt>
		<dd>
			<input type="button" class="btn btn-primary" value="提交" onclick="checkSub()">
		</dd>
	</dl>
 </form>
 </div>
 <div id="wideModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog" style="width:900px;">
    <div class="modal-content">
    </div>
  </div>
</div>