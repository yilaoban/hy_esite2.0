<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>

<link href="/css/shouye/9/list.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>
<div class="shouye_1022_2" class_data="hy201505079173">
<s:if test='blocks[0].display=="Y"'>
  <ul class="list_ul block" hyblk="C" hydata="${blocks[0].rid}" class_data="hy201505079613">
<s:iterator status='st' value='blocks[0].list' var='item'>
    <li class_data="hy201505079557"> <a href="${item.link}" target="_blank" hyvar="link">
      <dl class="tbox" class_data="hy201505079251">
        <dd class_data="hy201505079797"><span class_data="hy201505079537"><img src="${item.img}" hyvar="img" hydesc="26*26"></span></dd>
        <dd class_data="hy201505079483">
          <p hyvar="title" class_data="hy201505079703">${item.title}</p>
        </dd>
      </dl>
      </a> </li>
  </s:iterator>
</ul>
</s:if>
  <div class="clear" style="clear:both;"></div>
</div>


<%@include file="/WEB-INF/card/cardfile.jsp"%>
