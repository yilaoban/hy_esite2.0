<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="wrap_content">
<div class="toolbar pb10">
	 <span><a href="/data/page_hd_type.action?pid=${pid}">互动列表</a>  <i class="gt">&gt;&gt;</i> 赞</span><br>
</div>
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>赞对象</th>
			<th>互动总数</th>
			<th>最近一次互动时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="dto.hd103Model" var ="m">
		<tr>
			<td>${m.productname}</td>
			<td>${m.total }</td>
			<td><s:date name="lasttime" format="yyyy-MM-dd HH:mm:ss"/></td>
			<td><a href="javascript:void(0);" onclick="zanDetail(${m.productid})">详情</a></td>
		</tr>
	</s:iterator>
	</tbody>
</table>
<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>