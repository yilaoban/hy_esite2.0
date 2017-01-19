<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab">
	<ul>
		<li <s:if test="pagePosition==1">class="select"</s:if>><a href="/page/site.action?sitegroupid=${dto.sitegroup.id}">页面</a></li>
		<li <s:if test="pagePosition==2">class="select"</s:if>><a href="/page/interaction.action?sitegroupid=${dto.sitegroup.id}">互动</a></li>
		<li <s:if test="pagePosition==3">class="select"</s:if>><a href="/page/sitecount.action?sitegroupid=${dto.sitegroup.id}">统计</a></li>
		<li><a href="/page/site.action?sitegroupid=${dto.sitegroup.id}">配置</a></li>
	</ul>
</div>