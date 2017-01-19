<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
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
		}else{
			$("#form1").submit();
		}
	 	
		
	}
</script>
<a name="maodian"></a>
<div class="wrap_content left_module">
  <form action="csEditSub.action" method="post" enctype="multipart/form-data" id="form1" class="formview jNice">
  <input type="hidden" name="mid" value="10013">
  <input type="hidden" name="id" value="${id }">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">编辑传播</a></li>
	  </ul>
	  <a href="/${oname }/interact/index.action?mid=10013" class="return" title="返回"></a>
	</div>
 	<dl>
	 	<dt>标题</dt>
		<dd>
			<input type="text" class="text-medium" placeholder="此标题仅方便您记忆，请任意填写" id="addname" name="title" value="${cs.title }" onblur="nameonblur()"/>
			<span class="must">*</span>
			<span id="name_"><s:fielderror fieldName="err1"  cssStyle="color:red"/></span>
		</dd>
	</dl>
	<dl>
	 	<dt>活动开始时间</dt>
		<dd>
			<input id="startTime" type="text" value="<s:date name='cs.startStr' format='yyyy-MM-dd HH:mm:ss'/>" name="startTime" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" onblur="startTimeonblur()" />
			<span class="must">*</span>
			<span id="startTime_"><s:fielderror fieldName="err2"  cssStyle="color:red"/></span>
		</dd>
	</dl>
	<dl>
	 	<dt>活动结束时间</dt>
		<dd>
			<input id="endTime" type="text" value="<s:date name='cs.endStr' format='yyyy-MM-dd HH:mm:ss'/>" name="endTime" class="Wdate"	onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" onblur="endTimeonblur()" />
			<span class="must">*</span>
			<span id="endTime_"><s:fielderror fieldName="err3"  cssStyle="color:red"/></span>
		</dd>
	</dl>
	<dl>
	 	<dt>开始前提示语</dt>
		<dd>
			<input type="text" class="text-long" name="startNote" value="${cs.startnote }"><span class="notice">提示用户该申请还没开始</span>
		</dd>
	</dl>
	<dl>
	 	<dt>结束后提示语</dt>
		<dd>
			<input type="text" class="text-long" name="endNote" value="${cs.endnote }"><span class="notice">提示用户该申请已结束</span>
		</dd>
	</dl>
	<dl>
	 	<dt>传播类型</dt>
		<dd>
			<input type="radio" name="utype" value="0" onchange="renqc()" <s:if test="cs.utype==0">checked="checked"</s:if>>微博
			<input type="radio" name="utype" value="1" onchange="renqc()" <s:if test="cs.utype==1">checked="checked"</s:if>>微信
		</dd>
	</dl>
	<dl>
	 	<dt>微博分享内容</dt>
		<dd>
			<textarea rows="" cols="" name="content">${cs.content }</textarea>
		</dd>
	</dl>
	<dl>
	 	<dt>传播链接</dt>
		<dd>
			<input type="text" class="text-medium" value="${cs.link }" name="link"/>
		</dd>
	</dl>
	<dl>
	 	<dt></dt>
		<dd>
			<input type="button" class="btn btn-primary" value="提交" onclick="checkSub()">
		</dd>
	</dl>
 </form>
 </div>
 
