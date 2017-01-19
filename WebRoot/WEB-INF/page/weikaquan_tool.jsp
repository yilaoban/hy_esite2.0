<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div id="tab">
	<ul>
		<li <s:if test="tool==1">class="select"</s:if>><a href="/${oname}/content/weikaquan.action">店长设置</a></li>	
		<li <s:if test="tool==2">class="select"</s:if>><a href="/${oname}/content/weiKaQuanList.action?tool=2">微卡券</a></li>
		<li <s:if test="tool==3">class="select"</s:if>><a href="/${oname}/content/weiKaQuanPage.action?tool=3">页面设置</a></li>
	</ul>
</div>