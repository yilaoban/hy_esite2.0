<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function formsub(){
		if($('#total').val() == ""){
			$("#total_").html("请填写预约人数!").css("color", "RED");
			return;
		}
		if($("#startTime").val() == null || $("#startTime").val() == ""){
			$("#startTime_").html("开始时间不能为空!").css("color", "RED");
			return;
		}else{
			$("#startTime_").html("");
		}
		if($("#endTime").val() == null || $("#endTime").val() == ""){
			$("#endTime_").html("结束时间不能为空!").css("color", "RED");
			return;
		}else{
			$("#endTime_").html("");
		}
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:'/${oname }/interact/updateYuYueSSTime.action',
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
	
	function delYuYueSSTime(){
		layer.confirm('确定删除？', function(index){
			$.post("/${oname}/interact/delYuYueSSTime.action",{"timeid":'${timeid}',"ssid":'${ssid}'},function(data){
				if(data > 0){
					window.parent.location.reload();
				}else{
	            	layer.alert("删除失败!",0);
	            }		
			});
		}); 
	}
</script>
<a name="maodian"></a>
  <form action="/${oname }/interact/updateYuYueSSTime.action" method="post" enctype="multipart/form-data" id="form1" class="formview jNice">
   <input type="hidden" value="${catid }" name="catid" />
  <input type="hidden" value="${ssid }" name="yuYueSSTime.ssid" />
  <input type="hidden" value="${timeid }" name="yuYueSSTime.id" />
  <input type="hidden" value="${weekday }" name="yuYueSSTime.weekday" />
  <input type="hidden" value="${dateday}" name="yuYueSSTime.dateday" />
  <input type="hidden" value="${swtype }" name="swtype" />
   <s:if test="yuYueSSTime.weekday > 0">
   	 <input type="hidden" value="0" name="yuYueSSTime.type" />
   </s:if>
   <s:if test="yuYueSSTime.dateday > 0">
    	<input type="hidden" value="1" name="yuYueSSTime.type" />
   </s:if>
	<dl>
	 	<dt>选择服务</dt>
		<dd>
			<select name="yuYueService.id" id="serviceid">
				<s:iterator value="dto.serviceList" var="s">
					<option value="${s.id }" <s:if test="yuYueSS.sid == #s.id">selected="selected"</s:if>>${s.name }</option>
				</s:iterator>
			</select>
		</dd>
	</dl>
	<dl>
	 	<dt>选择提供者</dt>
		<dd>
			<select name="yuYueServicer.id" id="serid">
				<s:iterator value="dto.servicerList" var="s">
					<option value="${s.id }" <s:if test="yuYueSS.serid == #s.id">selected="selected"</s:if>>${s.name }</option>
				</s:iterator>
			</select>
		</dd>
	</dl>
	<dl>
	 	<dt>预约人数</dt>
		<dd>
			<input type="text" class="text-medium" id="total" name="yuYueSSTime.total" onkeyup="this.value = this.value.replace(/\D/g,'');" value="${yuYueSSTime.total }"/>
			<span id="total_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>开始时间</dt>
		<dd>
			<input id="startTime" type="text" value="${yuYueSSTime.shoure}:${yuYueSSTime.sminute }" name="yuYueSSTime.startTime" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'HH:mm'})" readonly="readonly"  />
			<span id="startTime_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>结束时间</dt>
		<dd>
			<input id="endTime" type="text" value="${yuYueSSTime.ehoure}:${yuYueSSTime.eminute }" name="yuYueSSTime.endTime" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'HH:mm'})" readonly="readonly"  />
			<span id="endTime_"></span>
		</dd>
	</dl>
	<dl>
		<dt></dt>
		<dd>
			<input type="button" value="修改" onclick="formsub()" class="btn btn-primary"/>
			<input type="button" value="删除" onclick="delYuYueSSTime()" class="btn btn-primary"/>
		</dd>
	</dl>
	
 </form>
 
