<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="/WEB-INF/card/background.jsp"%>

<s:if test='blocks[0].display=="Y"'>
<div class="block" hyblk="S" hydata="${blocks[0].rid}" class_data="hy20150507124713">
	<a href="${blocks[0].link}" hyvar="link">
		<img class="startpic" src="${blocks[0].img}" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="640*960"/>
	</a>
</div>
</s:if>
<script type="text/javascript">
		$(document).ready(function() {  
			 var bdH = $(window).height();
			 var bdW = $(window).width();
			 $(".startpic").css("height",bdH).css("width",bdW);
		})
</script>


<%@include file="/WEB-INF/card/cardfile.jsp"%>
