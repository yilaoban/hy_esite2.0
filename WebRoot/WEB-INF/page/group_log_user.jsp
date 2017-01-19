<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="toolbar">
	<form action="/${oname }/data/group_log_move.action" method="post">
		<input type="hidden" name="dto.owner" value="${dto.owner}" />
		<input type="hidden" name="dto.mtype" value="${dto.mtype}" />
		<input type="hidden" name="dto.atype" value="${dto.atype}" />

		<input type="hidden" name="dto.day_i" value="${dto.day_i}" />
		<input type="hidden" name="dto.hour" value="${dto.hour}" />
		<input type="hidden" name="dto.new_i" value="${dto.new_i}" />
		<input type="hidden" name="dto.area" value="${dto.area}" />
		<input type="hidden" name="dto.spage" value="${dto.spage}" />
		<button type="submit" class="btn btn-primary">复制到组</button>
	</form>
</div>
<table width="100%" class="tb_normal" border="1" cellspacing="1" cellpadding="1">
	<thead>
		<tr>
			<th>昵称</th>
			<th>性别</th>
			<th>区域</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.docs" var="u">
			<tr>
				<td>${u.nickname}</td>
				<td>${u.sex}</td>
				<td>${u.area}</td>
			</tr>
		</s:iterator>
	</tbody>
</table>
<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>