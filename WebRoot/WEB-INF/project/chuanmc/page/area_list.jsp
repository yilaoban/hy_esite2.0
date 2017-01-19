<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="wrap_content">
    <p>共${dto.pager.totalCount}条</p>
	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>地区</th>
			<th>互动数</th>
			<th>最近一次的互动时间</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.list" var="c">
			<tr align="center" >
			     <td >${c.area }</td>
			      <td >${c.num }</td>
			      <td >${c.lasttime }</td>
			</tr>
		</s:iterator>
	</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>