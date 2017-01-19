<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab">
	<ul>
		<li <s:if test="checked == 1"> class="select"</s:if>><a href="/${oname}/data/site_chart.action?ticket=${sessionScope.ticket}&siteid=${siteid}&source=${source}">推广效果</a></li>	
		<li <s:if test="checked == 2"> class="select"</s:if>><a href="/${oname}/data/site_user.action?ticket=${sessionScope.ticket}&siteid=${siteid}&source=${source}">用户访问</a></li>	
	</ul>
</div>