<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div id="button${pcid }" hyct="Y" status="0" hydata="${blocks[0].rid}" class="block ${blocks[0].ctname} qrcj" style="line-height:44px;font-size:16px;text-align:center;${blocks[0].hyct};background:rgba(${blocks[0].col},${blocks[0].tm});border-radius:${blocks[0].bdradius};color:${blocks[0].fontcol};">
	<a href="${blocks[0].link}">${blocks[0].title}</a>
</div>
</s:if>

<%@include file="/WEB-INF/card/cardfile.jsp"%>

<script language="javascript">
	$(document).ready(function(){
		$("#button${pcid}").click(function(){
			var xcid= '${page.entityid}';
			var pageid=${pageid};
			$.post("/${oname}/user/checkTeyue.action",{"hypage":pageid,"xcid":xcid},function(data){
			});
		})
	});
</script>
