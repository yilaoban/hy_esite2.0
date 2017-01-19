<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图片列表1 -->
<link href="/css/liebiao/4/list.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="wdy_top2 block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct};" hyblk="S" hydata="${blocks[0].rid}" class_data="hy2015050714708">
	<img src="${blocks[0].img}" width="100%" height="100%" hyvar="img" hydesc="720*300"/>
</div>
</s:if>

<s:if test='blocks[1].display=="Y"'>
<div class="liebiao_1022_4_cate_list block ${blocks[1].ctname}" status="0" hyct="Y" style="${blocks[1].hyct};" hyblk="I" hydata="${blocks[1].rid}" class_data="hy2015050714903">
	<ul class="mainmenu" class_data="hy2015050714347">
 	<s:if test='blocks[1].list.size == 0'>
    	<li class_data="hy2015050714743"> 
	<a href="#" target="_blank" hyvar="link">
      <div class="menubtn" class_data="hy2015050714243">
        <div class="menumesg" class_data="hy2015050714557">
          <div class="menuimg" class_data="hy2015050714342">
            <div class="menuimg2" class_data="hy2015050714793"> <img src="/images/liebiao/4/moren4.jpg" hyvar="img" hydesc="116*123"> </div>
          </div>
          <div class="menutitle" hyvar="title" class_data="hy2015050714886">郁金香</div>
          <div class="menutext" hyvar="desc" class_data="hy2015050714112">爱的表白、荣誉、祝福、永恒 </div>
        </div>
      </div>
      </a> 
	  </li>
    <li class_data="hy2015050714630"> 
	<a href="#" target="_blank" hyvar="link">
      <div class="menubtn" class_data="hy2015050714436">
        <div class="menumesg" class_data="hy2015050714690">
          <div class="menuimg" class_data="hy2015050714401">
            <div class="menuimg2" class_data="hy2015050714377"> <img src="/images/liebiao/4/moren3.jpg" hyvar="img" hydesc="116*123"> </div>
          </div>
          <div class="menutitle" hyvar="title" class_data="hy2015050714555">香水百合</div>
          <div class="menutext" hyvar="desc" class_data="hy2015050714407">纯洁、富贵、婚礼的祝福</div>
        </div>
      </div>
      </a> 
	  </li>
        <li class_data="hy2015050714810"> 
	<a href="#" target="_blank" hyvar="link">
      <div class="menubtn" class_data="hy2015050714811">
        <div class="menumesg" class_data="hy2015050714793">
          <div class="menuimg" class_data="hy2015050714875">
            <div class="menuimg2" class_data="hy2015050714501"> <img src="/images/liebiao/4/moren2.jpg" hyvar="img" hydesc="116*123"> </div>
          </div>
          <div class="menutitle" hyvar="title" class_data="hy2015050714567">白康乃馨</div>
          <div class="menutext" hyvar="desc" class_data="hy2015050714810">吾爱永在、真情、纯洁</div>
        </div>
      </div>
      </a> 
	  </li>
    <li class_data="hy2015050714383"> 
	<a href="#" target="_blank" hyvar="link">
      <div class="menubtn" class_data="hy2015050714157">
        <div class="menumesg" class_data="hy2015050714504">
          <div class="menuimg" class_data="hy2015050714459">
            <div class="menuimg2" class_data="hy2015050714525"> <img src="/images/liebiao/4/moren1.jpg" hyvar="img" hydesc="116*123"> </div>
          </div>
          <div class="menutitle" hyvar="title" class_data="hy2015050714151">剑兰</div>
          <div class="menutext" hyvar="desc" class_data="hy2015050714374">用心、长寿、福禄、康宁</div>
        </div>
      </div>
      </a> 
	  </li>
    </s:if>
    <s:else>
		 <s:iterator status='st' value='blocks[1].list' var='item'>
		    <li class="add_qq_more" class_data="hy2015050714149"> 
			<a href="${linkurl}" target="_blank" hyvar="link">
		      <div class="menubtn" class_data="hy2015050714332">
		        <div class="menumesg" class_data="hy2015050714807">
		          <div class="menuimg" class_data="hy2015050714838">
		            <div class="menuimg2" class_data="hy2015050714712"> <img src="${imgDomain}${item.imgurl}" hyvar="img" hydesc="116*123"> </div>
		          </div>
		          <div class="menutitle" hyvar="title" class_data="hy2015050714106">${item.title}</div>
		          <div class="menutext" hyvar="desc" class_data="hy2015050714624">${item.tag}</div>
		        </div>
		      </div>
		      </a> 
			  </li>
		  </s:iterator>
    </s:else>
	</ul>
</div>
</s:if>


<%@include file="/WEB-INF/card/cardfile.jsp"%>
