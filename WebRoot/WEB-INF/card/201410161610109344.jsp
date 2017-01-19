<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 首页2 -->
<link href="/css/shouye/2/list.css" rel="stylesheet" type="text/css" />
        
<%@include file="/WEB-INF/card/background.jsp"%><s:if test='blocks[0].display=="Y"'>
<div class="wdy_top2 block" hyblk="S" status="0" style="${blocks[0].hyct};" hydata="${blocks[0].rid}" class_data="hy201505072185">
<img src="${blocks[0].img}" style="width: 100%;height:100%;" hyvar="img" hydesc="720*300" />
</div>
</s:if>

<s:if test='blocks[1].display=="Y"'>
<div id="cate2" class="f2201505072648 block" hyblk="C" status="0" style="${blocks[1].hyct};" hydata="${blocks[1].rid}" class_data="hy201505072648">
<ul class="mainmenu2" class_data="hy201505072750">
<s:iterator status='st' value='blocks[1].list' var='item'>
            <li class="add_qq_more" class_data="hy201505072358">
                <div class="menubtn2" style="background:rgba(${item.col},${item.tm});" class_data="hy201505072386">
                    <a href="${item.link}" target="_blank" hyvar="link">
                        <div class="menumesg2" class_data="hy201505072474">
                        	<div class="menuimg2" class_data="hy201505072666"><img src="${item.img}" class="m_pic" hyvar="img" hydesc="150*150"></div>
                            <div class="menutitle2 m_text" hyvar="title" class_data="hy201505072828">${item.title}</div>
                        </div>
                    </a>
                </div>
            </li>
        </s:iterator>
		<div class="clear" style="clear:both;"></div>
</ul>
</div>
</s:if>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
