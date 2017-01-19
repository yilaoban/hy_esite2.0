<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:if test="dto.vistLogList.size>0">
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th width="20%">访问用户</th>
			<th width="20%">访问时间</th>
			<th width="20%">ip</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.vistLogList" >
			<tr align="center">
				<td>
				<s:if test="uid==null">匿名</s:if><s:else><a href="http://www.weibo.com/${uid }" target="_blank">${nickname}</a></s:else>
				</td>
				<td>
				${createtime}
				</td>
				<td>
				${ip}
				</td>
			</tr>
		</s:iterator>
	</tbody>
</table>
<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</s:if>
<s:else>
	没有访问记录
</s:else>
<div id="feature_div">
</div>
