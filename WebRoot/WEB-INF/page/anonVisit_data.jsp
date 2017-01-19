<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="frame_content">
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>访问用户</th>
			<th>访问时间</th>
			<th>访问IP</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.vistLogList" var="s">
			<tr align="center" >
			    <td>匿名</td>
			    <td> <s:date name="#s.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
			    <td>${s.ip}</td>
			</tr>	
		</s:iterator>
	</tbody>
</table>
<div>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
</div>
