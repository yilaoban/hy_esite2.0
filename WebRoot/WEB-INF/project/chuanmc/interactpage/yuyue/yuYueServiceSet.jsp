<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function yuYueSet(serid,serviceid){
		$.post("/${oname}/interact/yuYueSet.action",{inajax:1,serviceid:serviceid,serid:serid},function(data){
			if(data==0){
				layer.msg('该提供者已经添加', {icon: 5, time: 2000});
			}else{
				layer.msg('设置成功', {icon: 6, time: 1500});
				window.location.reload();
			}
		});
	}
	
	function addUser(){
		var name = $('#name').val().trim();
		if(name != ""){
			$.post("/${oname}/interact/saveYuYueSS.action",{inajax:1,"yuYueServicer.caid":${catid},"yuYueServicer.name":name,"serviceid":${serviceid}},function(data){
				if(data==0){
					layer.msg('该提供者已经添加', {icon: 5, time: 2000});
				}else{
					layer.msg('设置成功', {icon: 6, time: 1500});
					window.location.reload();
				}
			});
		}
	}
</script>
<div class="wrap_content">
	<s:if test="dto.showServicerList.size()>0">
		已经添加的提供者有：
		<s:iterator value="dto.showServicerList" var="s">
			${s.name}
		</s:iterator>
	</s:if>
	<form>
		<input type="text" placeholder="姓名" name="name" id="name">
		<input type="hidden" name="yuYueServicer.caid" value="${catid}">
		<input type="button" value="添加" onclick="addUser()">
	</form>
	<table width="100%" class="tb_normal" border="1" cellspacing="1" cellpadding="1">
		<tr>
			<td>
				姓名
			</td>
			<td>
				操作
			</td>
		</tr>
		<s:iterator value="dto.servicerList" var="s">
			<tr>
				<td>${s.name }</td>
				<td><a href="javascript:yuYueSet(${s.id},${serviceid})">配置</a></td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
