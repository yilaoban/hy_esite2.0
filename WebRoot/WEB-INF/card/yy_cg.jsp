<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 预约成功 -->
<link rel="stylesheet" href="/css/fn/yy/succeed.css" type="text/css">
<%@include file="/WEB-INF/card/background.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
		var recordid = $('#hy_kv').val();
		console.log(recordid);
		$.post("/${oname}/user/findYuYueRecordById.action",{"recordid":recordid},function(data){
			if(data.record){
				$('#ser').html(data.record.sername);
				$('#service').html(data.record.servicename);
				$('#yytime').html(data.record.yytimeStr + " " + data.record.timeStr);
			}
		});
	
	});
	
</script>

<div class="con block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct}" hydata="${blocks[0].rid}">
	<span>技师：<i id="ser">1号</i></span>
	<span>服务：<i id="service">德国珈纳保湿紧肤</i></span>
	<span>时间：<i id="yytime">2016/04/16&nbsp;12:00</i></span>
</div>	
<%@include file="/WEB-INF/card/cardfile.jsp"%>
