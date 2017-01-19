<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="wrap_content">
<div class="switch_tab_div pt10">
	<span><a href="/data/page_hd_type.action?pid=${pid}">互动列表</a>  <i class="gt">&gt;&gt;</i>  授权</span><br>
	<p class="clearfix"></p>
</div>
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>微博昵称</th>
			<th>导航</th>
			<th>首次授权时间</th>
			<th>最近授权时间</th>
			<th>终端</th>
			<th>IP</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.hd111Model" var="m">
			<tr>
				<td>${m.nickname }</td>
				<td>${m.sitename }</td>
				<td><s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td><s:date name="updatetime" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${m.terminal }</td>
				<td>${m.ip}</td>
			</tr>
		</s:iterator>
	</tbody>
</table>
<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>