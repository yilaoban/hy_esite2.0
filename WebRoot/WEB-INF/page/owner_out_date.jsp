<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab">
	<ul>
		<li <s:if test="sitetype==1">class="select"</s:if>><a href="/page/sitegroupList.action">账号过期</a></li>
	</ul>
</div>