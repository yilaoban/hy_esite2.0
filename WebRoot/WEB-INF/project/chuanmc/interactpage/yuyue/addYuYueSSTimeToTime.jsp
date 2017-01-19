<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function formsub(){
		var serviceids=$('input:checkbox[name="yuYueService.ids"]:checked');
		var serids=$('input:checkbox[name="yuYueServicer.ids"]:checked');
		var startTimes = $("input[name='yuYueSSTime.startTimes']");
		var endTimes = $("input[name='yuYueSSTime.endTimes']");
		if(serviceids.length == 0){
			$("#service_").html("请选择服务!").css("color", "RED");
			return;
		}else{
			$("#service_").html("");
		}
		if(serids.length == 0){
			$("#servicer_").html("请选择服务人员!").css("color", "RED");
			return;
		}else{
			$("#servicer_").html("");
		}
		if($('#total').val() == ""){
			$("#total_").html("请填写预约人数!").css("color", "RED");
			return;
		}else{
			$("#total_").html("");
		}
		for(var j=0; j<startTimes.length; j++){
		 if(startTimes[j].value == ""){
		 	$("#startTime_").html("开始时间不能为空!").css("color", "RED");
			return;
		 } 
	   }
		for(var j=0; j<endTimes.length; j++){
		 if(endTimes[j].value == ""){
		 	$("#endTime_").html("结束时间不能为空!").css("color", "RED");
			return;
		 } 
	   }
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:'/${oname }/interact/saveYuYueTime.action',
	        data:$('#form1').serialize(),// 你的formid
	        async: false,
	        error: function(request) {
	            layer.alert("Connection error",0);
	        },
	        success: function(data) {
	            if(data > 0){
	            	window.parent.location.reload();
	            }else{
	            	layer.alert("操作失败!",0);
	            }
	        }
	    });
		
	}
</script>
<a name="maodian"></a>
<div>
  <form  method="post" enctype="multipart/form-data" id="form1" class="formview jNice">
  <input type="hidden" value="${catid }" name="catid" />
   <input type="hidden" value="${swtype }" name="swtype" />
   <input type="hidden" value="2" name="yuYueSSTime.type" />
   	<dl id="ser2">
	 	<dt>选择服务</dt>
		<dd>
			<s:iterator value="dto.serviceList" var="s">
				<label><input type="checkbox" name="yuYueService.ids" value="${s.id }"> ${s.name}</label>
			</s:iterator>
			<span id="service_"></span>
		</dd>
	</dl>
	<dl id="service2">
	 	<dt>服务人员</dt>
		<dd>
			<s:iterator value="dto.servicerList" var="s">
				<label><input type="checkbox" name="yuYueServicer.ids" value="${s.id }"> ${s.name}</label>
			</s:iterator>
			<span id="servicer_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>预约人数</dt>
		<dd>
			<input type="text" class="text-medium" id="total" name="yuYueSSTime.total" onkeyup="this.value = this.value.replace(/\D/g,'');"/>
			<span id="total_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>开始时间</dt>
		<dd>
			<input id="startTime" type="text" name="yuYueSSTime.startTimes" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly"  />
			<span id="startTime_"></span>
		</dd>
	</dl>
	<dl id="times">
	 	<dt>结束时间</dt>
		<dd>
			<input id="endTime" type="text" name="yuYueSSTime.endTimes" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly"  />
			<span id="endTime_"></span>
			<a href="javascript:void(0);" onclick="adddl()"><b>+</b>更多</a>
		</dd>
	</dl>
	<dl>
		<dt></dt>
		<dd>
			<input type="button" class="btn btn-primary" value="保存" onclick="formsub()"/>
		</dd>
	</dl>
 </form>
 </div>
 <script type="text/javascript">
 	function adddl(){
 		var html = '<dl>';
	 	html += '<dt>开始时间</dt>';
		html += '<dd>';
		html += '<input id="startTime" type="text" name="yuYueSSTime.startTimes" class="Wdate" onFocus="WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\'})" readonly="readonly"  />';
		html += '<span id="startTime_"></span>';
		html += '</dd>';
		html += '</dl>';
		html += '<dl>';
		html += '<dt>结束时间</dt>';
		html += '<dd>';
		html += '<input id="endTime" type="text" name="yuYueSSTime.endTimes" class="Wdate" onFocus="WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\'})" readonly="readonly"  />';
		html += '<span id="endTime_"></span>';
		html += '</dd>';
		html += '</dl>';
		$("#times").after(html);
	}
 
</script>
