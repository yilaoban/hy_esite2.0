<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>

<!-- 首页12 -->

<link href="/css/shouye/12/list.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="wdy_top2 block" hyblk="S" hydata="${blocks[0].rid}" class_data="hy2015050722798">
	<img src="${blocks[0].img}" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="720*300"/>
</div>
</s:if>



<div id="shouye_1022_5" style="margin-top:10px;" class_data="hy2015050722927">
<s:if test='blocks[1].display=="Y"'>
	<div class="cont-box block" hyblk="C" hydata="${blocks[1].rid}" class_data="hy2015050722794">
		<s:iterator status='st' value='blocks[1].list' var='item'> 
			<a href="${item.link}" target="_blank" hyvar="link">
				<div class="telbtn01" class_data="hy2015050722114"> <img src="${item.img}" hyvar="img" hydesc="188*188">
				  <p hyvar="title" class_data="hy2015050722267">${item.title}</p>
				</div>
			</a> 
		</s:iterator>
</div>
</s:if>
<s:if test='blocks[2].display=="Y"'>
	<div class="cont-box block" hyblk="C" hydata="${blocks[2].rid}" class_data="hy2015050722706">
		<s:iterator status='st' value='blocks[2].list' var='item'> 
			<a href="${item.link}" target="_blank" hyvar="link">
				<div class="telbtn04" class_data="hy2015050722336"><img src="${item.img}" hyvar="img" hydesc="188*188">
				  <p hyvar="title" class_data="hy2015050722483">${item.title}</p>
				</div>
			</a> 
		</s:iterator>
</div>
</s:if>
</div>


<%@include file="/WEB-INF/card/cardfile.jsp"%>