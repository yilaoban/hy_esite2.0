<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 首页4 -->

<link href="/css/shouye/4/list.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%><s:if test='blocks[0].display=="Y"'>
<div class="wdy_top2 block col0" hyblk="S" status="0" style="${blocks[0].hyct};" hydata="${blocks[0].rid}" class_data="hy201505074580">
<img src="${blocks[0].img}" style="width:100%;height:100%;" hyvar="img" hydesc="955*383" />
</div>
</s:if>

<s:if test='blocks[1].display=="Y"'>
<div class="weiba-content block col1" hyblk="C" status="0" style="${blocks[1].hyct};" hydata="${blocks[1].rid}" class_data="hy201505074140">
        <div class="mainconbox tpl-catelist" class_data="hy201505074483">
			<s:iterator status='st' value='blocks[1].list' var='item'>
            <div class="navbox tpl-catelist-item add_qq_more" class_data="hy201505074711">
                <a href="${item.link}" class="nav${st.index%2}" hyvar="link" style="background:rgba(${item.col},${item.tm});">
                    <div class_data="hy201505074552">
                        <img class="item-image m_pic" src="${item.img}" hyvar="img" hydesc="128*128">
                        <h5 class="tpl-cate-title m_text" hyvar="title" class_data="hy201505074876">${item.title}</h5>
                        <p class="tpl-cate-summary m_desc" hyvar="introduction" class_data="hy201505074558">${item.introduction}</p>
                    </div>
                </a>
			</div>
    		</s:iterator>
        </div>
        <div class="clear"></div>
</div>
</s:if>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
