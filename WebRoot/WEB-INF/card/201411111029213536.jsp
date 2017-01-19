<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 新闻列表6 -->
<link href="/css/liebiao/xw6_new/list.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="wdy_top2_cp block ${blocks[0].ctname}" status="0" hyblk="S" hyct="Y" hydata="${blocks[0].rid}" style="${blocks[0].hyct};" class_data="hy2015050728122">
	<img src="${blocks[0].img}" width="100%" height="100%" style="max-width: 100%;" hyvar="img" hydesc="720*300"/>
</div>
</s:if>

<s:if test='blocks[1].display=="Y"'>
<div  hyblk="I" class="liebiao_141110_1_box block clearfix ${blocks[1].ctname}" status="0" hyct="Y" hydata="${blocks[1].rid}" style="${blocks[1].hyct};" class_data="hy2015050728306">
	<ul class_data="hy2015050728835">
		<s:if test='blocks[1].list.size == 0'>
			<li class_data="hy2015050728911"><a href="#" target="_blank" hyvar="link">
		       <div class="liebiao_141110_1_b_img" class_data="hy2015050728326"><img src="/images/liebiao/xw6_new/tu1.jpg" width="100%" height="100%" hyvar="img" hydesc="200*200"/></div>
		       <span class="liebiao_141110_1_b_tiaoti" hyvar="name" class_data="hy2015050728386">腐竹白果薏米竹白果薏米竹白果薏米糖水</span>
		       <span class="liebiao_141110_1_b_j" hyvar="desc" class_data="hy2015050728201">白果和薏米都有清热去薏米都有清热去薏米都有清热去薏米都有清热去薏米都有清热去薏米都有清热去薏米都有清热去湿的功效，天气燥热或胸中烦闷时，煲些白果薏米糖水吃... </span> </a>
		     </li>
		     <li class_data="hy2015050728570"><a href="#" target="_blank" hyvar="link">
		       <div class="liebiao_141110_1_b_img" class_data="hy2015050728621"><img src="/images/liebiao/xw6_new/tu2.jpg" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="200*200"/></div>
		       <span class="liebiao_141110_1_b_tiaoti" hyvar="name" class_data="hy2015050728746">莲子百合红豆沙</span>
		       <span class="liebiao_141110_1_b_j" hyvar="desc" class_data="hy2015050728441">百合能补中益气，温肺止咳;鲜品有镇咳、止慢性支气管炎、疗肺病止咯血。 </span> </a>
		     </li>
		     <li class_data="hy2015050728214"><a href="#" target="_blank" hyvar="link">
		       <div class="liebiao_141110_1_b_img" class_data="hy2015050728746"><img src="/images/liebiao/xw6_new/tu3.jpg" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="200*200"/></div>
		       <span class="liebiao_141110_1_b_tiaoti" hyvar="name" class_data="hy2015050728128">水果西米露</span>
		       <span class="liebiao_141110_1_b_j" hyvar="desc" class_data="hy2015050728396">西米、牛奶(或者椰奶)、水果(草莓、西瓜、苹果或者你喜欢的水果)。 </span> </a>
		     </li>
	    </s:if>
	    <s:else>
			<s:iterator status='st' value='blocks[1].list' var='item'>
				<li class_data="hy2015050728759">
					<a <s:if test='#item.islink =="N"'> href="/${oname}/user/show/${blocks[1].pageid}/${sessionScope.visitUser.source}/n-${item.id}.html" </s:if><s:else>href="${item.linkurl}"</s:else> target="_blank" hyvar="link">
			       <div class="liebiao_141110_1_b_img" class_data="hy2015050728793"><img <s:if test='#item.simgurl ==""'>src="/images/xw.jpg"</s:if><s:else>src="${imgDomain}${item.simgurl}"</s:else> width="125" height="125" style="max-width: 100%;" hyvar="img" hydesc="200*200"/></div>
			       <span class="liebiao_141110_1_b_tiaoti" hyvar="name" class_data="hy2015050728763">${item.title}</span>
			       <span class="liebiao_141110_1_b_j" hyvar="desc" class_data="hy2015050728154">${item.shortdesc}</span> 
			       </a>
			     </li>
		     </s:iterator>
	    </s:else>
	</ul>
</div>
</s:if>


<%@include file="/WEB-INF/card/cardfile.jsp"%>