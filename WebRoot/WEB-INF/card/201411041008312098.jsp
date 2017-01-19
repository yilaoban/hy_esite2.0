<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>

<link href="/css/shouye/11/list.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%>
 

<s:if test='blocks[0].display=="Y"'>
<div id="shouye_1022_4" hyblk="C" class="block" hydata="${blocks[0].rid}" class_data="hy2015050721595">
<s:iterator status='st' value='blocks[0].list' var='item'>
  <div class="cont-box icon01" class_data="hy2015050721703"> 
	<a href="${item.link}" target="_blank" hyvar="link">
	    <div class="bgbox" class_data="hy2015050721593"><img src="${item.img}" hyvar="img" hydesc="60*60"></div>
	    <div class="bdr"></div>
	    <div class="text" hyvar="title" class_data="hy2015050721462">${item.title}</div>
	</a> 
  </div>
</s:iterator>
</div>
</s:if>




<s:if test='blocks[1].display=="Y"'>
<div class="shouye_1022_4_dibu block" hyblk="S" hydata="${blocks[1].rid}" class_data="hy2015050721855">
<footer><span hyvar="footer" class_data="hy2015050721798">${blocks[1].footer}</span></footer>
<div style="height: 0px;"></div>
</div>
</s:if>


<%@include file="/WEB-INF/card/cardfile.jsp"%>