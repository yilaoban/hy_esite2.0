<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE >
<html>
  <head>
    <title>${page.bgJson.title}</title>
	<meta name="description" content="${page.bgJson.description}" />  
	<meta name="keywords" content="${page.bgJson.keywords}" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<link href="/css/reset.css" rel="stylesheet" type="text/css" />
	<link href="/css/animation.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
	function adjust(obj){  
	   var w  = document.body.clientWidth;  
	   var h  = document.body.clientHeight;  
	   $("body").css("zoom",(h/720*100)-2+"%");
	}  
	window.onload=function(){  
	  window.onresize = adjust;  
	  adjust();  
	}  
	</script>
  </head>
  <body style="zoom:100%;background:${page.bgJson.background};background-size:100% 100%;overflow:hidden;">
  	<div id="mainDiv">
		<s:iterator value="dto.pc" var="c" status="st" end="1">
			<div id="a_${c.id}" class="single_page" <s:if test="page.bgJson.pageheight > 0">style="height:${page.bgJson.pageheight}px"</s:if>>
			<script language="javascript">
				var id = '${c.id}';
				if(id > 0){
					$("#a_"+id).load('/${oname}/user/showCard.action?pcid='+id+"&pageid="+'${pageid}'+"&jspstyle="+'${page.jspstyle}');
				}
			</script>
			</div>
		</s:iterator>
		<input id="hy_pageid" type="hidden"  value="${pageid}">
		<input id="hy_visitUser" type="hidden" value="${sessionScope.visitUser }">
		<input id="hy_cd" type="hidden" value="${cd}">
		<input id="hy_userAgent" type="hidden" value="${userAgent}">
		<input id="hy_cid" type="hidden" value="${cid}">
		<input id="hy_imgUrl" type="hidden" value="${page.pic}">
		<input id="hy_source" type="hidden" value="${source}">
		<input id="hy_entity" type="hidden" value="${page.entityid}">
	</div>
  </body>
  <%@include file="/WEB-INF/pageshow/page_visit_time.jsp" %>
</html>

