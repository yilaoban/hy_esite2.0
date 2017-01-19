<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab">
	<ul>
		<li <s:if test="reportPoint==1">class="select"</s:if>><a href="/page/visit_report.action?sgid=${sgid}">访问信息</a></li>
		<li <s:if test="reportPoint==2">class="select"</s:if>><a href="/page/hd_view_report.action?sgid=${sgid}">互动信息</a></li>
	</ul>
</div>
