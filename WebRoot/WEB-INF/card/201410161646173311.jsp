<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>

<!-- 首页3 -->

<link href="/css/shouye/3/list.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="wdy_top2 block " status="0" hyblk="S" style="${blocks[0].hyct};" hydata="${blocks[0].rid}" class_data="hy201505073539">
<img src="${blocks[0].img}" style="width: 100%;height:100%;" hyvar="img" hydesc="640*280"/>
</div>
</s:if>

<s:if test='blocks[1].display=="Y"'>
<div id="mainmenu20" hyblk="C" status="0" style="${blocks[1].hyct};" class="f3201505073217 block" hydata="${blocks[1].rid}" class_data="hy201505073217">
	<ul class_data="hy201505073171">
		<s:iterator status='st' value='blocks[1].list' var='item'>
            <li class="menu-1 add_qq_more" class_data="hy201505073218">
                <a href="${item.link}" target="_blank" hyvar="link">
                    <i class="icon icon-1" style="background:rgba(${item.col},${item.tm});"><img src="${item.img}" class="m_pic" hyvar="img" hydesc="39*45"></i>
                    <p class="title m_text" hyvar="title" class_data="hy201505073774">${item.title}</p>
                    <div class="right-arrow"></div>
                </a>
            </li>
  		 	</s:iterator>
           <div class="clear" style="clear:both;"></div>
       </ul>
</div>
</s:if>

<%@include file="/WEB-INF/card/cardfile.jsp"%>
