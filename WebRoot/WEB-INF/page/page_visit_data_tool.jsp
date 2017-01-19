<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab">
	<ul>
		<li <s:if test="report==1">class="select"</s:if>><a href="/data/page_visit_area.action?src=${src}&pid=${pid}">访问信息</a></li>
		<li <s:if test="report==2">class="select"</s:if>><a href="/data/page_hd_area.action?src=${src}&pid=${pid}">互动信息</a></li>
		<li <s:if test="report==3">class="select"</s:if>><a href="/data/page_hd_type.action?src=${src}&pid=${pid}">互动类型</a></li>
	</ul>
</div>
