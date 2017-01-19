<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="modal-body">
	<p>
		<span>昵称: ${dto.ulist[0].nickname}</span>
		<span style="margin:0 20px;">性别: ${dto.ulist[0].sex}</span>
		<span>总访问次数： ${dto.pager.totalCount}</span>
	</p>
	<table width="100%" cellspacing="1" cellpadding="1" border="1" class="tb_normal">
		<thead>
			<tr>
				<th>访问时间</th>
				<th>访问时长</th>
				<th>访问页面</th>
				<th>访问设备</th>
				<th>IP地址</th>
				<th>地区</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.ulist" var="u">
				<tr align="center">
					<td><s:date name="visittime" format="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${u.visitlong}</td>
					<td>${u.visitpage}</td>
					<td>${u.agent}</td>
					<td>${u.ip}</td>
					<td>${u.area}</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
