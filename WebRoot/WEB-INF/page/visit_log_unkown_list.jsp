<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
   <div >
	    <a href="/page/custom_visitreport.action?sgid=${sgid}">非匿名用户</a> /
	    <a class="chosen" href="/page/visti_log_unkown.action?sgid=${sgid}">匿名用户</a>
   </div>
  <div>
    共${dto.pager.totalCount }条
  	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>访问用户</th>
			<th>访问时间</th>
			<th>访问ip</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.reports" var="s">
			<tr align="center" >
			   <td>匿名访问</td>
			   <td><s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"></s:date></td>
			   <td>${s.ip}</td>
			</tr>
		</s:iterator>
	</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
</div>
