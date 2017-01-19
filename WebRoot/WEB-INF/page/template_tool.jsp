<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab" >
<ul>
	<s:iterator value="dto.categorylist" var="c" status="st" begin="0" end="0">
		<li <s:if test='categoryid == #c.id && type!=null'> class="select"</s:if> ><a href="/${oname }/template/index.action?categoryid=${c.id}&type=F">${c.name}</a></li>
	</s:iterator>
		<li <s:if test='type == null '>class="select" </s:if>><a  href="/${oname }/template/findPageTemplate.action">套装页面</a></li>
</ul>
</div>
