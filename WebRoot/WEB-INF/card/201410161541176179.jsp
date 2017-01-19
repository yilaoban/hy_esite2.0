<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<link href="/css/shouye/1/list.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%><s:if test='blocks[0].display=="Y"'>
<div class="mainmenu1 block col0" hyblk="C" hydata="${blocks[0].rid}" class_data="hy201505071569">
	<ul class_data="hy201505071629">
		<s:iterator status='st' value='blocks[0].list' var='item'>
			<li class="add_qq_more" class_data="hy201505071542">
				<div class="menubtn" class_data="hy201505071137">
					<a href="${item.link}" target="_blank" hyvar="link">
						<div class="menuimg" class_data="hy201505071592">
							<img src="${item.img}" class="m_pic" hyvar="img" hydesc="300*300">
						</div>
						<div class="menutitle m_text" hyvar="title" class_data="hy201505071498">
							${item.title}
						</div></a>
				</div>
			</li>
		</s:iterator>
		<div class="clear" style="clear: both;"></div>
	</ul>
</div>
</s:if>

<%@include file="/WEB-INF/card/cardfile.jsp"%>
