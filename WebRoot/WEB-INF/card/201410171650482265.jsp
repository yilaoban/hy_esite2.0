<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 首页5 -->
<link href="/css/shouye/5/list.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
	<div class="banner block" hyblk="S" status="0" style="${blocks[0].hyct};" hydata="${blocks[0].rid}" class_data="hy201505075454">
		<img src="${blocks[0].img}" alt="" style="width:100%;height:100%;" hyvar="img"  hydesc="640*264">
	</div>
</s:if>

<s:if test='blocks[1].display=="Y"'>
	<div id="wrap_shouye5" class="block clr" hyblk="C" status="0" style="${blocks[1].hyct};" hydata="${blocks[1].rid}" class_data="hy201505075132">
		<ul class="telnav" class_data="hy201505075969">
			<s:iterator status='st' value='blocks[1].list' var='item'>
				<a href="${item.link}" onClick="tongji(178229,0);" class="nav${st.count }" hyvar="link">	    	    
				    <li style="background:rgba(${item.col},${item.tm});" class_data="hy201505075863">
					     <div class="icon_w" class_data="hy201505075994">
					     	<div class="icon" class_data="hy201505075511">
					          	<img src="${item.img}" hyvar="img" hydesc="176*176">
							</div>
					     </div>
					     <span hyvar="title" class_data="hy201505075133">${item.title}</span>
					     <p hyvar="introduction" class_data="hy201505075917">${item.introduction}</p>
				    </li>
			      </a>
			</s:iterator>
		</ul>
	</div>
</s:if>


<%@include file="/WEB-INF/card/cardfile.jsp"%>
