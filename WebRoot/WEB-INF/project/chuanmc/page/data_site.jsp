<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
	<table width="100%" cellspacing="1" cellpadding="1" border="1" class="tb_normal">
		<thead>
			<tr>
				<th>站点名称</th>
				<th>站点类型</th>
				<th>创建时间</th>
				<th>历史趋势分析</th>
				<th>用户访问详情</th>
				<th>总PV</th>
				<th>总UV</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.list" var="s">
				<tr align="center">
					<td><a href="javascript:void(0)" onclick="dataPage(${s.id})">${s.groupname}</a></td>
					<td><s:if test='#s.type=="W"'>微现场</s:if> <s:if test='#s.type=="J"'>集人气</s:if> <s:if test='#s.type=="N"'>普通站点</s:if></td>
					<td><s:date name="createtime" format="yyyy-MM-dd HH:mm:ss" /></td>
					<td><a href="javascript:void(0);" onclick="showData('${s.id}','${oname}','${ptype }')">分析</a></td>
					<td><a href="javascript:void(0);" onclick="showUser('${s.id}','${s.groupname}')">详情</a></td>
					<td>${s.pv}</td>
					<td>${s.uv}</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
	<form id="form" action="/${oname}/data/page.action" method="post">
		<input type="hidden" name="ptype" value="site" /> <input type="hidden" name="siteid" />
	</form>

	<input id="sitename" type="hidden" />
	<iframe id="data" src="" width="100%" height="100%" scrolling="no" frameborder="0" marginheight="0" marginwidth="0"
		onload="this.height=this.contentWindow.document.documentElement.scrollHeight"> </iframe>
</div>
<script type="text/javascript">
	function dataPage(siteid) {
		$("#form").find("input[name='siteid']").val(siteid);
		$("#form").submit();
	}
</script>