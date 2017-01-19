<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab">
	<ul>
		<li <s:if test='type != "C" '> class="select" </s:if> ><a href="/${oname }/page/sitegroupList.action">我的活动</a></li>	
		<li <s:if test='type == "C" '>class="select" </s:if> ><a href="/${oname}/template/index.action?type=C">活动模板</a></li>	
	</ul>
</div>