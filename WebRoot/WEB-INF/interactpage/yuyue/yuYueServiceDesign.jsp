<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function formsub(){
		var service=$('input:radio[name="service"]:checked').val();
		var servicer=$('input:radio[name="servicer"]:checked').val();
		if(service == 0){
			var weekday = parseInt($('#weekday').val());
			if(weekday == 0){
				layer.msg('请选择星期几', {icon: 5, time: 2000});
				return;
			}
		}else if(service == 1){
			var dateday = $('#dateday').val().trim();
			$('#weekday').val(0);
			if(dateday == ""){
				layer.msg('请填写日期', {icon: 5, time: 2000});
				return;
			}
		}
		if(servicer == 0){
			var serid = parseInt($('#serid').val());
			if(serid == 0){
				layer.msg('请选择一个提供者', {icon: 5, time: 2000});
				return;
			}
		}else{
			var sername = $('#sername').val().trim();
			$('#serid').val(0);
			if(sername == ""){
				layer.msg('请填写提供者名称', {icon: 5, time: 2000});
				return;
			}
		}
		if($('#total').val() == ""){
			$("#total_").html("请填写预约人数!").css("color", "RED");
			return;
		}
		if(service != 2){
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
		}
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:'/${oname }/interact/saveYuYueSSTime.action',
	        data:$('#form1').serialize(),// 你的formid
	        async: false,
	        error: function(request) {
	            layer.alert("Connection error",0);
	        },
	        success: function(data) {
	            if(data > 0){
	            	window.parent.location.href = "/${oname}/interact/yuyueService.action?catid=${catid }";
	            }else{
	            	layer.alert("操作失败!",0);
	            }
	        }
	    });
		
	}
</script>
<a name="maodian"></a>
  <form action="/${oname }/interact/saveYuYueSSTime.action" method="post" enctype="multipart/form-data" id="form1" class="formview jNice">
  <input type="hidden" value="${catid }" name="catid" />
   <input type="hidden" value="S" name="swtype" />
   <input type="hidden" value="${serviceid }" name="yuYueService.id" id="serviceid"/>
   <input type="hidden" value="0" name="yuYueSSTime.type" id="type"/>
   <dl>
   		<dt>选择日期</dt>
   		<dd>
   			<input type="radio" name="service" value="0" onclick="$('#ser2').show();$('#ser1').hide();$('#type').val(0);$('#time').hide();$('#clock').show()" checked="checked">按周
			<input type="radio" name="service" value="1" onclick="$('#ser1').show();$('#ser2').hide();$('#type').val(1);$('#time').hide();$('#clock').show()">按日
			<input type="radio" name="service" value="2" onclick="$('#ser1').hide();$('#ser2').hide();$('#type').val(2);$('#time').show();$('#clock').hide()">按时间段
   		</dd>
   </dl>
	<dl id="ser1" style="display: none;">
	 	<dt>日期</dt>
		<dd>
			<input id="dateday" type="text" name="yuYueSSTime.dateday" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyyMMdd'})" readonly="readonly" />
		</dd>
	</dl>
	<dl id="ser2">
		<dt>星期几</dt>
		<dd>
			<select name="yuYueSSTime.weekday" id="weekday">
				<option value="0">选择星期几</option>
					<option value="1">星期一</option>
					<option value="2">星期二</option>
					<option value="3">星期三</option>
					<option value="4">星期四</option>
					<option value="5">星期五</option>
					<option value="6">星期六</option>
					<option value="7">星期日</option>
			</select>
		</dd>
	</dl>
	<dl>
	 	<dt>选择提供者</dt>
		<dd>
			<input type="radio" name="servicer" value="0" onclick="$('#service2').show();$('#service1').hide()" checked="checked">现有提供者
			<input type="radio" name="servicer" value="1" onclick="$('#service1').show();$('#service2').hide();">新建提供者
		</dd>
	</dl>
	<dl id="service1" style="display: none;">
	 	<dt>新建提供者</dt>
		<dd>
			<input type="text" name="yuYueServicer.name" id="sername">
		</dd>
	</dl>
	<dl id="service2">
	 	<dt>选择提供者</dt>
		<dd>
			<select name="yuYueServicer.id" id="serid">
				<option value="0">选择提供者</option>
				<option value="-1">全部</option>
				<s:iterator value="dto.servicerList" var="s">
					<option value="${s.id }">${s.name }</option>
				</s:iterator>
			</select>
		</dd>
	</dl>
	<dl>
	 	<dt>预约人数</dt>
		<dd>
			<input type="text" class="text-medium" id="total" name="yuYueSSTime.total" onkeyup="this.value = this.value.replace(/\D/g,'');"/>
			<span id="total_"></span>
		</dd>
	</dl>
	<div id="clock">
		<dl>
		 	<dt>开始时间</dt>
			<dd>
				<input id="startTime" type="text" name="yuYueSSTime.startTime" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'HH:mm'})" readonly="readonly"  />
				<span id="startTime_"></span>
			</dd>
		</dl>
		<dl>
		 	<dt>结束时间</dt>
			<dd>
				<input id="endTime" type="text" name="yuYueSSTime.endTime" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'HH:mm'})" readonly="readonly"  />
				<span id="endTime_"></span>
			</dd>
		</dl>
	</div>
	<div style="display: none;" id="time">
		<dl>
		 	<dt>开始时间</dt>
			<dd>
				<input id="starttime" type="text" name="yuYueSSTime.starttime" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly"  />
				<span id="starttime_"></span>
			</dd>
		</dl>
		<dl>
		 	<dt>结束时间</dt>
			<dd>
				<input id="endtime" type="text" name="yuYueSSTime.endtime" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly"  />
				<span id="endtime_"></span>
			</dd>
		</dl>
	</div>
	
	<dl>
		<dt></dt>
		<dd>
			<input type="button" class="btn btn-primary" value="保存" onclick="formsub()"/>
		</dd>
	</dl>
 </form>
 
