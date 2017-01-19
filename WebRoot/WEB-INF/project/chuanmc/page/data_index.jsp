<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
	<div class="toolbar pb10">
		<ul class="c_switch">
		    <li <s:if test="mtype=='wx'">class="selected"</s:if>><a href="/${oname}/data/index.action?mtype=wx">微信数据</a></li>
			<li <s:if test="mtype=='wb'">class="selected"</s:if>><a href="/${oname}/data/index.action?mtype=wb">微博数据</a></li>
			<li <s:if test="mtype=='dl'">class="selected"</s:if>><a href="/${oname}/data/index.action?mtype=dl">移动端数据</a></li>
		</ul>
		<!-- <a class="button" href="/${oname}/data/index_user.action?mtype=wx" >查看访问用户</a> -->
	</div>
		<iframe src="/${oname}/data/iframe_data_index.action?mtype=${mtype}&dtype=today&gtype=HOUR" width="100%" height="100%" scrolling="no" frameborder="0" marginheight="0"
			marginwidth="0" onload="this.height=this.contentWindow.document.documentElement.scrollHeight"> </iframe>
</div>