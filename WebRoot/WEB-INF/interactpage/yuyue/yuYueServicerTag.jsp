<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function yuYueTagSet(id,name){
		$.post("/${oname}/interact/yuYueTagSet.action",{inajax:1,serid:${serid},tagid:id},function(data){
			if(data==0){
				layer.msg('操作失败,请重试……', {icon: 5, time: 2000});
			}else{
				layer.msg('设置成功', {icon: 6, time: 1500});
			}
		});
	}
</script>
<div class="wrap_content">
	<table width="100%" class="tb_normal" border="1" cellspacing="1" cellpadding="1">
		<tr>
			<td>
				标签
			</td>
			<td>
				操作
			</td>
		</tr>
		<s:iterator value="dto.tagList" var="t">
			<tr>
				<td>${t.name }</td>
				<td><a href="javascript:yuYueTagSet(${t.id})">设为标签</a></td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
