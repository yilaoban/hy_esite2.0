<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="frame_content">

	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
		<thead>
			<tr>
				<th>微博昵称</th><th>赞时间</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.list" var="u">
				<tr align="center">
					<td>${u.nickname }</td>
					<td><s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
