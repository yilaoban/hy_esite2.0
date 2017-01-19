<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文3 -->
<link href="/css/tuwen/3/list.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="wdy_top2 block" hyblk="S" status="0" style="${blocks[0].hyct}" hydata="${blocks[0].rid}" class_data="hy2015050711195">
	<img src="${blocks[0].img}" style="width: 100%;height:100%" hyvar="img" hydesc="720*300"/>
</div>
</s:if>

<s:if test='blocks[1].display=="Y"'>
<div class="msg_page block" hyblk="C" status="0" style="${blocks[1].hyct}" hydata="${blocks[1].rid}" class_data="hy2015050711877">
	<div class="msg_list" class_data="hy2015050711466">
		<s:iterator status='st' value='blocks[1].list' var='item'>
			<div class="msg_list_bd" class_data="hy2015050711320">
				<div class="msg_inner_wrapper news_box" class_data="hy2015050711356"> 
					<a class="msg_item" href="${item.link}" target="_blank" hyvar="link">
						<h4 class="msg_title m_text" hyvar="title" class_data="hy2015050711100">${item.title}</h4>
						<div hyvar="desc" class="tuwen_1023_1_wenzi" class_data="hy2015050711973">${item.desc}</div>
					</a> 
				</div>
			</div>
		</s:iterator>
	</div>
</div>
</s:if>


<%@include file="/WEB-INF/card/cardfile.jsp"%>
