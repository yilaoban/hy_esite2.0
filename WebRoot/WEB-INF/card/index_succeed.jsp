<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<link href="/css/mycards/index_succeed.css" rel="stylesheet" type="text/css" />
<!-- 信息完善成功 -->
<%@include file="/WEB-INF/card/background.jsp"%>
<div class="succeed0503 block" status="0" hyct="Y" style="${blocks[0].hyct}" hydata="${blocks[0].rid}">
	<span><img src="${blocks[0].img}"></span>
    <span>${blocks[0].title}</span>
</div>
<div class="succeed05031 block" status="0" hyct="Y" style="${blocks[1].hyct}" hydata="${blocks[1].rid}"><p><input type="button" value="完成" onclick="javascript:topage()"></p></div>
<script type="text/javascript">
function topage(){
	var kv = $("#hy_kv").val();
	if (!kv) {
		return;
	}
	window.location="/${oname}/user/wxshow/"+kv+"/wxn.html";
}
</script>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
