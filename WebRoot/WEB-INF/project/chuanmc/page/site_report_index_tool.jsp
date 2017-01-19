<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab">
	<ul>
		<li <s:if test="report==1">class="select"</s:if>><a href="/data/report_index.action">数据总览</a></li>
		<li <s:if test="report==2">class="select"</s:if>><a href="/data/site_page_list.action">分项报告</a></li>
	</ul>
</div>
