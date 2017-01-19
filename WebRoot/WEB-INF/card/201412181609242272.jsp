<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 指纹 -->
<link href="/css/tuwen/zhiwen/list.css" rel="stylesheet" type="text/css">
<%@include file="/WEB-INF/card/background.jsp"%>
<div class="dh_141217_box_3" class_data="hy2015050750384">


   <div class="zhiwen_141217 ANI" class_data="hy2015050750472">
   <s:if test='blocks[0].display=="Y"'>
    <div class="elem5 block" hyblk="S" hydata="${blocks[0].rid}" class_data="hy2015050750826"><span hyvar="title" class_data="hy2015050750343">${blocks[0].title}</span></div>
	</s:if>
	<div style="clear:both"></div>
	<div class="elem6" class_data="hy2015050750304">请扫描</div>
	
	
	<div class="elem2" class_data="hy2015050750778"><img src="/images/tuwen/zhiwen/x.png"></div>
	<div class="elem3" class_data="hy2015050750508"><b></b></div>
	<div class="elem4"></div>
   </div>

</div>
<script type="text/javascript" src="/js/zepto.js" ></script>
<script>
	$(document).ready(function(){
	  $(".elem2").hide();
	  $('.elem3').bind("touchstart",function(){
			$(".elem2").show();
	  });
	})
</script>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
