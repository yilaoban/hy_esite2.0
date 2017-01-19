<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab" >
<ul>
	<s:if test='type == "C" '><li class="select" ><a href="/${oname }/template/index.action?type=C">场景</a></li></s:if>
	<s:if test='type == "W" '><li class="select" ><a  href="/${oname }/template/index.action?type=W">网站</a></li></s:if>
	<s:if test='type == null'><li class="select" ><a  href="/${oname }/template/createIndex.action">应用</a></li></s:if>
</ul>
</div>
