<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div>
	微信昵称:${dto.wxUser.nickname }  来源:${dto.ocs.name }
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
			<tr>
				<th>最近签到时间</th>
				<th>最近签到IP</th>
			</tr>
		<s:iterator value="dto.logs" var="p">
			<tr>
				<td><s:date name="#p.createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${p.ip }</td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>