<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab">
	<ul>
		<li <s:if test="report==1">class="select"</s:if>><a href="/data/site_visit_data.action?siteid=${siteid}">访问分析</a></li>
		<li <s:if test="report==2">class="select"</s:if>><a href="/data/site_hd_data.action?siteid=${siteid}">互动分析</a></li>
	</ul>
</div>
