<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 首页6 -->
<link href="/css/shouye/10/list.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%>
		<s:if test='blocks[0].display=="Y"'>
		<div class="telbtn1 block ${blocks[0].ctname}" status="0" hyblk="S" hyct="Y" style="${blocks[0].hyct};background:rgba(${blocks[0].col},${blocks[0].tm});" hydata="${blocks[0].rid}" class_data="hy20150507104925">
			<a href="${blocks[0].link}" target="_blank" hyvar="link">
				<div class="menuimg" class_data="hy20150507104865"><img src="${blocks[0].img}" width="65px" hyvar="img" hydesc="256*256"></div>
				<div class="menutitle" hyvar="title" class_data="hy20150507104136">${blocks[0].title}</div>
			</a>
		</div>         
		</s:if>
		
		<s:if test='blocks[1].display=="Y"'>
		<div class="telbtn2 block ${blocks[1].ctname}" status="0" hyblk="S" hyct="Y" style="${blocks[1].hyct};background:rgba(${blocks[1].col},${blocks[1].tm});" hydata="${blocks[1].rid}" class_data="hy20150507104408">
			<a href="${blocks[1].link}" target="_blank" hyvar="link">
				<div class="menuimg" class_data="hy20150507104889"><img src="${blocks[1].img}" width="65px" hyvar="img" hydesc="256*256"></div>
				<div class="menutitle" hyvar="title" class_data="hy20150507104244">${blocks[1].title}</div>
			</a>
		</div>         
		</s:if>
		
		<s:if test='blocks[2].display=="Y"'>
		<div class="telbtn3 block ${blocks[2].ctname}" status="0" hyblk="S" hyct="Y" style="${blocks[2].hyct};background:rgba(${blocks[2].col},${blocks[2].tm});" hydata="${blocks[2].rid}" class_data="hy20150507104903">
			<a href="${blocks[2].link}" target="_blank" hyvar="link">
				<div class="menuimg" class_data="hy20150507104779"><img src="${blocks[2].img}" width="65px" hyvar="img" hydesc="256*256"></div>
				<div class="menutitle" hyvar="title" class_data="hy20150507104276">${blocks[2].title}</div>
			</a>
		</div>         
		</s:if>
		
		<s:if test='blocks[3].display=="Y"'>
		<div class="telbtn4 block ${blocks[3].ctname}" status="0" hyblk="S" hyct="Y" style="${blocks[3].hyct};background:rgba(${blocks[3].col},${blocks[3].tm});" hydata="${blocks[3].rid}" class_data="hy20150507104540">
			<a href="${blocks[3].link}" target="_blank" hyvar="link">
				<div class="menuimg" class_data="hy20150507104482"><img src="${blocks[3].img}" width="65px" hyvar="img" hydesc="256*256"></div>
				<div class="menutitle" hyvar="title" class_data="hy20150507104118">${blocks[3].title}</div>
			</a>
		</div>         
		</s:if>

<%@include file="/WEB-INF/card/cardfile.jsp"%>