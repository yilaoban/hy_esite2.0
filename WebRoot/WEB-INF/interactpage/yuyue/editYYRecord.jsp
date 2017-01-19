<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function formsub(){
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:'/${oname }/interact/updateYYRecord.action',
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
<div class="left_module">
  <form action="" method="post" enctype="multipart/form-data" id="form1" class="formview jNice">
  	<input type="hidden" value="${recordid }" name="recordid">
   	<dl>
	 	<dt>服务</dt>
		<dd>
			${dto.record.servicename }
		</dd>
	</dl>
	<dl>
	 	<dt>预约人员</dt>
		<dd>
			${dto.record.sername }
		</dd>
	</dl>
	<dl>
	 	<dt>预约时间</dt>
		<dd>
			<input id="yytime" type="text" value="<s:date name="dto.record.yytime" format="yyyy-MM-dd HH:mm:ss"/>" name="yytimeStr" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
		</dd>
	</dl>
	<dl>
		<dt></dt>
		<dd>
			<input type="button" class="btn btn-primary" value="确认" onclick="formsub()"/>
		</dd>
	</dl>
 </form>
 </div>
 
