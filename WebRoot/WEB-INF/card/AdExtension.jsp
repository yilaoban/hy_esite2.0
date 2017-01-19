<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<input id='cardid' type='hidden' value='${pcid}' />

<style type="text/css">
	.time{width:80px;height:30px;line-height:30px;text-align:center;color:#333;position:absolute;top:8px;right:8px;}
</style>
<script type="text/javascript">
$(function(){
	if('${method}' =='E'){
		return;
	}
	var vu = ${sessionScope.visitUser.wxUser.subscribe};
	if(vu != 1){
		//非粉丝
		window.location.href="http://page.mocaidan.com/caidan/user/wxshow/41575/wxn.html";//关注引导页
	}else{
		window.location.href="http://page.mocaidan.com/caidan/user/wxshow/41417/wxn.html";//这个就是试手气
	}
});
</script>
<div class="contentimg" style="width:100%">
</div>
<s:if test='blocks[0].display=="Y"'>
	<div class="time block" status="0" style="${blocks[0].hyct };color:${blocks[0].color }" hydata="${blocks[0].rid}"></div>
</s:if>
