<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<link href="/css/Hshenqing/index.css" rel="stylesheet" type="text/css" />
<script src="/js/wyy_ht/mobiscroll_002.js" type="text/javascript"></script>
<script src="/js/wyy_ht/mobiscroll_004.js" type="text/javascript"></script>
<link href="/css/wyy_ht/mobiscroll_002.css" rel="stylesheet" type="text/css">
<link href="/css/wyy_ht/mobiscroll.css" rel="stylesheet" type="text/css">
<script src="/js/wyy_ht/mobiscroll.js" type="text/javascript"></script>
<script src="/js/wyy_ht/mobiscroll_003.js" type="text/javascript"></script>
<script src="/js/wyy_ht/mobiscroll_005.js" type="text/javascript"></script>
<link href="/css/wyy_ht/mobiscroll_003.css" rel="stylesheet" type="text/css">
<style>
	label {font-size:12px;margin:10px 0 5px 0;display:block;}
	input[type="text"] {border:1px solid #e3e3e3;width:96%;line-height:28px;padding:0 2%;}
	input[type="button"] {border:none;margin:10px auto 0;display:block;padding:8px 20px;background:#6289AE;border-radius:5px;color:#fff;font-size:16px;}
</style>
<!-- 可用于线下签到的申请记录,可更新 -->
<script type="text/javascript">
	function formsub(){
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:"/${oname}/user/updateAptRecord.action",
	        data:$('#form1').serialize(),
	        async: false,
	        error: function(request) {
	            alert("Connection error");
	        },
	        success: function(data) {
	            if(data > 0){
	            	window.history.back();
	            }else{
	            	alert("操作失败!");
	            }
	        }
	    });
	}
	
	function back(){
		window.history.back();
	}
	
</script>
<div class="page-head">
	<h4>预约信息</h4>
</div>
<div class="form-view">
   	<s:if test="record.id==0">
   		<div>
   		<br>
   		此用户没有填写申请表单,无内容可修改
   		<a href="javascript:window.history.back()">返回</a>
   		</div>
   	</s:if>
   	<s:else>
   		<s:if test='dto.record.status == "CMP"'>
				<s:iterator value="record.maps" var="m" status="st">
				        <label for="record.${m.mapping }">${m.name}</label>
				        	<input type="text" disabled="disabled" class="form-control input-sm" name="record.${m.mapping }" id="record.${m.mapping }" value="${record.values[st.index] }">
				</s:iterator>
			        <label for="servicename">${dto.record.tag1 }</label>
			        	<input type="text" class="form-control input-sm" id="servicename" value="${dto.record.servicename }" disabled="disabled">
			        <label for="sername">${dto.record.tag2 }</label>
			        	<input type="text" class="form-control input-sm" id="sername" value="${dto.record.sername }" disabled="disabled">
			        <label for="appDateTime">预约时间</label>
			        	<input type="text" id="appDateTime" disabled="disabled" value="<s:date name="dto.record.yytime" format="yyyy-MM-dd HH:mm:ss"/>" name="yytime"  readonly="readonly"/>
			    	 <label for="hydesc">留言</label>
			          <textarea id="hydesc" cols="3" rows="3" style="height:60px;" disabled="disabled">${dto.record.hydesc}</textarea>
			    	 <input onclick="back()" class="btn btn-primary btn-lg btn-block" type="button" value="返回"></input>
			    </div>
   		</s:if>
   		<s:else>
	    	<form id="form1">
		    	<input type="hidden" name="record.id" value="${record.id }"/>
		    	<input type="hidden" value="${recordid }" name="recordid">
				<s:iterator value="record.maps" var="m" status="st">
				        <label for="record.${m.mapping }">${m.name}</label>
				        	<input type="text" class="form-control input-sm" name="record.${m.mapping }" id="record.${m.mapping }" value="${record.values[st.index] }">
				</s:iterator>
			        <label for="servicename">${dto.record.tag1 }</label>
			        	<input type="text" class="form-control input-sm" id="servicename" value="${dto.record.servicename }" disabled="disabled">
			        <label for="sername">${dto.record.tag2 }</label>
			        	<input type="text" class="form-control input-sm" id="sername" value="${dto.record.sername }" disabled="disabled">
			        <label for="appDateTime">预约时间</label>
			        	<input type="text" id="appDateTime" value="<s:date name="dto.record.yytime" format="yyyy-MM-dd HH:mm:ss"/>" name="yytime"  readonly="readonly"/>
			          <label for="hydesc">留言</label>
			          <textarea id="hydesc" cols="3" rows="3" style="height:60px;" disabled="disabled">${dto.record.hydesc}</textarea>
			         <input onclick="formsub()" class="btn btn-primary btn-lg btn-block" type="button" value="确定"></input>
			    </div>
			   
	    	</form>
   		</s:else>
   	</s:else>
</div>
<script type="text/javascript">
 $(function () {
			var currYear = (new Date()).getFullYear();	
			var opt={};
			opt.date = {preset : 'date'};
			opt.datetime = {preset : 'datetime'};
			opt.time = {preset : 'time'};
			opt.default = {
				theme: 'android-ics light', //皮肤样式
		        display: 'modal', //显示方式 
		        mode: 'scroller', //日期选择模式
				dateFormat: 'yyyy-mm-dd',
				lang: 'zh',
				showNow: true,
				nowText: "今天",
		        startYear: currYear - 10, //开始年份
		        endYear: currYear + 10 //结束年份
			};

		  	$("#appDate").mobiscroll($.extend(opt['date'], opt['default']));
		  	var optDateTime = $.extend(opt['datetime'], opt['default']);
		  	var optTime = $.extend(opt['time'], opt['default']);
		    $("#appDateTime").mobiscroll(optDateTime).datetime(optDateTime);
		    $("#appTime").mobiscroll(optTime).time(optTime);
        });
</script>