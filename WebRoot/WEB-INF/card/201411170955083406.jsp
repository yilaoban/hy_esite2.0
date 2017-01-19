<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 刮刮卡 -->
<link href="/css/hudong/guaguaka/index.css" rel="stylesheet" type="text/css" />

<div class="box_ggk_141113_1" class_data="hy2015050735935">

<%@include file="/WEB-INF/card/background.jsp"%>

<s:if test='blocks[0].display=="Y"'>
   <div class="wdy_top block" hyblk="S" hydata="${blocks[0].rid}" class_data="hy2015050735154"><img src="${blocks[0].img}" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="640*350"/></div>
</s:if>  
   
   <div class="wdy_center_ggk_141113_1" class_data="hy2015050735807">
   <s:if test='blocks[1].display=="Y"'>
         <div class="zhuanpan_ggk_141113_1 block" hyblk="I" hydata="${blocks[1].rid}" class_data="hy2015050735198">
         <s:if test='blocks[2].obj.id > 0'>
         </s:if>
         <s:else>
         	<img src="/images/hudong/guaguaka/ggl_03.png" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="320*210"/>
         </s:else>
         </div>
    </s:if>   
    <s:if test='blocks[2].display=="Y"'>  
		 <div class="jx_zhan_ggk_141113_1" class_data="hy2015050735247">
		      <div class="jx_zhuan_zi_ggk_141113_1 block" hyblk="S" hydata="${blocks[2].rid}" class_data="hy2015050735148">
			     <span hyvar="desc" class_data="hy2015050735242">${blocks[2].desc}</span>
			  
			  </div>
		 </div>
	</s:if>	 
		 
		
   
   
   </div>

   
   
</div>

<%@include file="/WEB-INF/card/cardfile.jsp"%>
