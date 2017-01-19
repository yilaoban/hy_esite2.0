<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
<div class="switch_tab_div pb10">
	<span><a href="/data/page_hd_type.action?pid=${pid}">互动列表</a>  <i class="gt">&gt;&gt;</i> 邮件预定</span><br>
	
</div>
	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>邮件地址</th>
			<th>IP</th>
			<th>终端</th>
			<th>来源</th>
			<th>加入时间</th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="dto.hd121Model" var="m">
		<tr>
			<td>${m.email}</td>
			<td>${m.ip }</td>
			<td><s:if test='terminal=="P"'>手机客户端</s:if><s:elseif test='terminal=="C"'>PC客户端</s:elseif><s:elseif test='terminal=="A"'>PAD客户端</s:elseif></td>
			<td>${m.source }</td>
			<td><s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
	</s:iterator>
	</tbody>
</table>
<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>