<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

	<p>
		<span>总记录： ${dto.pager.totalCount}</span>
	</p>
	<table width="100%" cellspacing="1" cellpadding="1" border="1" class="tb_normal">
		<thead>
			<tr>
				<th>类型</th>
				<th>积分变动</th>
				<th>描述</th>
				<th>日期</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.ulist" var="u">
				<tr align="center">
					<td>
						${u.stypename }
					</td>
					<td>${u.score}</td>
					<td>${u.desc}</td>
					<td><s:date name="created" format="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
