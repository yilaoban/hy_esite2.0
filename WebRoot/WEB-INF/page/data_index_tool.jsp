<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab">
	<ul>
		<li <s:if test="lightType==1">class="select"</s:if>><a href="/${oname}/data/index.action">统计</a></li>
		<li <s:if test="lightType==4">class="select"</s:if>><a href="/${oname}/data/index_user.action">用户</a></li>
		<li <s:if test="lightType==3">class="select"</s:if>><a href="/${oname}/data/site.action">站点</a></li>
		<li <s:if test="lightType==2">class="select"</s:if>><a href="/${oname}/data/page.action">页面</a></li>
		<li <s:if test="lightType==5">class="select"</s:if>><a href="/${oname}/data/share.action">传播分析</a></li>
	</ul>
</div>
