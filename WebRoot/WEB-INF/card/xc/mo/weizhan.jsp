<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!-- xc微站 -->

<link href="/css/xc/mo/wz/list.css" rel="stylesheet" type="text/css">
<%@include file="/WEB-INF/card/background.jsp"%>

<s:if test='blocks[0].display=="Y"'>
<div class="sy_Favicon block" hydata="${blocks[0].rid}" hyblk="S">
	<div class="sy_Favicon_left"><s:if test='%{#session.visitUser !=null}'><img src="${sessionScope.visitUser.wxUser.headimgurl }"></s:if><s:else><img src="/res/lenovo/weizhan/images/shouye2_logo4.png"></s:else></div>
	<div class="sy_Favicon_right"><img hydesc="img" src="${blocks[0].img}"></div>
</div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="sy_button block" hydata="${blocks[1].rid}" hyblk="S"> 
	<a href="${blocks[1].link1}">
  		<div class="sy_button1">
  			<img src="${blocks[1].img1}">
  		</div>
	</a> 
	<a href="${blocks[1].link2}">
		<div class="sy_button2">
			<img src="${blocks[1].img2}">
		</div>
	</a> 
	<a href="${blocks[1].link3}">
		<div class="sy_button3">
			<img src="${blocks[1].img3}">
		</div>
	</a> 
	<a href="${blocks[1].link4}">
  		<div class="sy_button4">
  			<img src="${blocks[1].img4}">
  		</div>
  	</a> 
  	<a href="${blocks[1].link5}">
  		<div class="sy_button5">
  			<img src="${blocks[1].img5}">
  		</div>
  	</a> 
  	<a href="${blocks[1].link6}">
  		<div class="sy_button6">
  			<img src="${blocks[1].img6}">
  		</div>
  	</a> 
</div>
</s:if> 
<s:if test='blocks[2].display=="Y"'>
  <div class="bottom block" hydata="${blocks[2].rid}" hyblk="S">${blocks[2].footer}</div>
</s:if>
  <script>
  	$(document).ready(function(){
  		var abc = $(window).height();
  		if(abc < 420){
  			$(".bgbg").css("background-image","url(/res/lenovo/weizhan/images/sy_bj44.png)")
  			$(".sy_button").addClass("iphone4");
  			$(".bottom").hide();
  		}
  	})
  </script>

<%@include file="/WEB-INF/card/cardfile.jsp"%>