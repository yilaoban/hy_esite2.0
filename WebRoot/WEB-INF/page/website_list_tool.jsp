<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab">
	<ul>
		<li <s:if test='lightType == 1 '> class="select" </s:if> ><a href="/${oname }/page/website.action">我的网站</a></li>	
		<li <s:if test='lightType == 2 '>class="select" </s:if> ><a href="/${oname}/template/index.action?type=W">网站模板</a></li>	
		<li <s:if test='lightType == 3 '>class="select" </s:if> ><a href="/${oname}/page/owner_source.action">站点资源</a></li>
	</ul>
</div>