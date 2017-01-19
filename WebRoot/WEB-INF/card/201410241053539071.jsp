<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 新闻列表1 -->
<link href="/css/liebiao/1/list.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
  <div class="liebiao_1022_1_neirong block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct}" hyblk="I" hydata="${blocks[0].rid}" class_data="hy2015050712574">
    <ul class_data="hy2015050712360">
	 <s:if test='blocks[0].list.size == 0'>
    	  <a href="#" target="_blank" hyvar="link">
      <li class_data="hy2015050712687">
        <p class="liebiao_1022_1_neirong-title" hyvar="title" class_data="hy2015050712831">中秋创意伴手礼，台湾蔡家手作包子</p>
        <dl class_data="hy2015050712172">
          <dt class_data="hy2015050712260"> 
           <span hyvar="desc" class_data="hy2015050712203"> 今年中秋献上不同的伴手礼选择,往年的中秋都送月饼,今年可以来点不...</span></dt>
          <dd class_data="hy2015050712272"><img src="/images/liebiao/1/1406106326318_thumb.jpg" width="52" hyvar="img" hydesc="52*65"/></dd>
        </dl>
      </li>
      </a> 
	  <a href="#" target="_blank" hyvar="link">
      <li class_data="hy2015050712772">
        <p class="liebiao_1022_1_neirong-title" hyvar="title" class_data="hy2015050712859">上帝的鱼缸，彩虹的故乡——帕劳</p>
        <dl class_data="hy2015050712520">
          <dt class_data="hy2015050712954"> 
            <span hyvar="desc" class_data="hy2015050712190">上帝的鱼缸，彩虹的故乡，世界公认潜水渡假圣地，人间仙境世外桃源...</span></dt>
		    <dd class_data="hy2015050712233"><img src="/images/liebiao/1/1406106198801_thumb.jpg" width="52" hyvar="img" hydesc="52*65"/></dd>
        </dl>
      </li>
      </a> 
	  <a href="#" target="_blank" hyvar="link">
      <li class_data="hy2015050712459">
        <p class="liebiao_1022_1_neirong-title" hyvar="title" class_data="hy2015050712932">旅行中女生要懂的几个小常识，get...</p>
        <dl class_data="hy2015050712686">
          <dt class_data="hy2015050712812"> 
            <span hyvar="desc" class_data="hy2015050712904">虽然在旅行，但女生们都希望自己保持干干净净漂漂亮亮的，行天下美...</span></dt>
		    <dd class_data="hy2015050712344"><img src="/images/liebiao/1/1406102787534_thumb.jpg" width="52" hyvar="img" hydesc="52*65"/></dd>
        </dl>
      </li>
      </a> 
	  <a href="#" target="_blank" hyvar="link">
      <li class_data="hy2015050712771">
        <p class="liebiao_1022_1_neirong-title" hyvar="title" class_data="hy2015050712616">中国驻悉尼总领馆提醒中国公民携超量...</p>
        <dl class_data="hy2015050712671">
          <dt class_data="hy2015050712539">
           <span hyvar="desc" class_data="hy2015050712238"> 中国驻悉尼总领馆提醒中国公民携超量现金入出境如实申报...</span></dt>
		  <dd class_data="hy2015050712450"><img src="/images/liebiao/1/1406106198801_thumb.jpg" width="52" hyvar="img" hydesc="52*65"/></dd>
        </dl>
      </li>
      </a>
    </s:if>
    <s:else>
		<s:iterator status='st' value='blocks[0].list' var='item'>
		      <a <s:if test='#item.islink =="N"'> href="/${oname}/user/show/${blocks[0].pageid}/${sessionScope.visitUser.source}/n-${item.id}.html" </s:if><s:else>href="${item.linkurl}"</s:else> target="_blank" hyvar="link">
		      <li class_data="hy2015050712765">
		        <p class="liebiao_1022_1_neirong-title" hyvar="title" class_data="hy2015050712387">${item.title}</p>
		        <dl class_data="hy2015050712211">
		          <dt class_data="hy2015050712178"> 
		           <span hyvar="desc" class_data="hy2015050712541">${item.shortdesc}</span></dt>
		          <dd class_data="hy2015050712404"><img <s:if test='#item.simgurl ==""'>src="/images/xw.jpg"</s:if><s:else>src="${imgDomain}${item.simgurl}"</s:else> width="52" hyvar="img" hydesc="52*65"/></dd>
		        </dl>
		      </li>
		      </a> 
		 </s:iterator>
    </s:else>
	</ul>
    <div style="clear:both"></div>
</div>
</s:if>


<%@include file="/WEB-INF/card/cardfile.jsp"%>