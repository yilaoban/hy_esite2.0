<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<link rel="stylesheet" href="/css/tuwen/10/effect.css" type="text/css">
<%@include file="/WEB-INF/card/background.jsp"%>
    <div class="menupage" class_data="hy2015050740689">
    <s:if test='blocks[0].display=="Y"'>
      	  <div class="menupage_left animated_2 fadeInLeft block" hyblk="S" hydata="${blocks[0].rid}" class_data="hy2015050740883"> <img src="${blocks[0].img}" id="menupage_left" width="100%" style="height: 605px;" hyvar="img" hydesc="445*1300"></div>
    </s:if> 
    <s:if test='blocks[1].display=="Y"'>
          <div class="menupage_right" class_data="hy2015050740369">
          
                <div class="menupage_menu block" hyblk="C" hydata="${blocks[1].rid}" class_data="hy2015050740944">
                  <ul class_data="hy2015050740425">
					<s:iterator status='st' value='blocks[1].list' var='item'>
                    <li class="animated_2 fadeInRight" class_data="hy2015050740771"><a href="${item.link}" hyvar="link"><span hyvar="title" class_data="hy2015050740533">${item.title}</span></a></li>
                	</s:iterator>
                  </ul>
				</div>  
                    
          </div>
    </s:if>
    </div>
 
<%@include file="/WEB-INF/card/cardfile.jsp"%>
