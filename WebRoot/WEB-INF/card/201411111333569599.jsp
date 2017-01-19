<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 产品列表3 -->
<link href="/css/liebiao/chanpin2/list.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="wdy_top2_cp block ${blocks[0].ctname}" status="0" hyblk="S" hyct="Y" style="${blocks[0].hyct};" hydata="${blocks[0].rid}" class_data="hy2015050731120">
	<img src="${blocks[0].img}" width="100%" height="100%" hyvar="img" hydesc="720*300"/>
</div>
</s:if>

<s:if test='blocks[1].display=="Y"'>
	<div class="liebiao_141111_1_box block clearfix ${blocks[1].ctname}" status="0" hyct="Y" style="${blocks[1].hyct}" hyblk="I" hydata="${blocks[1].rid}" class_data="hy2015050731720">
		<ul class_data="hy2015050731602">
		<s:if test='blocks[1].list.size == 0'>
			<li class_data="hy2015050731430"><a href="#" target="_blank" hyvar="link">
		      <img src="/images/liebiao/chanpin2/tu1.jpg" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="300*200"/>
		        <p hyvar="name" class_data="hy2015050731430"> 【包邮】北极光裸钻50分F色VS1一钻双证</p>
		        <span class_data="hy2015050731108"><strong hyvar="price1">￥2194元</strong> | <b hyvar="price2">原价:￥3546元</b></span>
		        </a>
		      </li>
		      <li class_data="hy2015050731974"><a href="#" target="_blank" hyvar="link">
		      <img src="/images/liebiao/chanpin2/tu2.jpg" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="300*200"/>
		        <p hyvar="name" class_data="hy2015050731719"> 【包邮】钻石谷 白18K金钻石耳环 正品 群镶钻石70</p>
		        <span class_data="hy2015050731902"><strong hyvar="price1">￥2345元</strong> | <b hyvar="price2">原价:￥8657元</b></span>
		        </a>
		      </li>
		      <li class_data="hy2015050731899"><a href="#" target="_blank" hyvar="link">
		      <img src="/images/liebiao/chanpin2/tu3.jpg" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="300*200"/>
		        <p hyvar="name" class_data="hy2015050731862"> 【包邮】钻石谷 独特心形白18K金 耳钉 结婚耳钉 </p>
		        <span class_data="hy2015050731471"><strong hyvar="price1">￥7495元</strong> | <b hyvar="price2">原价:￥8862元</b></span>
		        </a>
		      </li>
		      <li class_data="hy2015050731297"><a href="#" target="_blank" hyvar="link">
		      <img src="/images/liebiao/chanpin2/tu4.jpg" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="300*200"/>
		        <p hyvar="name" class_data="hy2015050731983"> 【包邮钻石谷 白18K金钻石耳环 正品 豪华群镶30分</p>
		        <span class_data="hy2015050731469"><strong hyvar="price1">￥1534元</strong> | <b hyvar="price2">原价:￥5432元</b></span>
		        </a>
		      </li>
		</s:if>
		<s:else>
			<s:iterator status='st' value='blocks[1].list' var='item'>
				<li class_data="hy2015050731359">
				<a href="<s:if test='item.linkurl != ""'>${item.linkurl }</s:if><s:else>/${oname}/user/show/${blocks[1].pageid}/${sessionScope.visitUser.source}/ctt-hy-${item.id}.html</s:else>" target="_blank" hyvar="link">
			      <img src="${imgDomain}${item.simgurl}" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="300*200"/>
			        <p hyvar="name" class_data="hy2015050731420">${item.name}</p>
			        <span class_data="hy2015050731721"><strong hyvar="price1">￥${item.salesprice }元</strong> | <b hyvar="price2">原价:￥${item.price }元</b></span>
			        </a>
			      </li>
			</s:iterator>
		</s:else>
		</ul>
</div>
</s:if>



<%@include file="/WEB-INF/card/cardfile.jsp"%>
