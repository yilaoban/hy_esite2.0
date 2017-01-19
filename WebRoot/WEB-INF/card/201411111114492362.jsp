<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- ΢产品列表2 -->
<link href="/css/liebiao/chanpin/list.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="wdy_top2_cp block ${blocks[0].ctname}" status="0" hyblk="S" hyct="Y" hydata="${blocks[0].rid}" style="${blocks[0].hyct};" class_data="hy2015050730543">
	<img src="${blocks[0].img}" width="100%" height="100%" hyvar="img" hydesc="720*300"/>
</div>
</s:if>

<s:if test='blocks[1].display=="Y"'>
<div class="wcp_c_dh_cp block ${blocks[1].ctname}" status="0" hyblk="S" hyct="Y" hydata="${blocks[1].rid}" style="${blocks[1].hyct};" class_data="hy2015050730379">
	<span class_data="hy2015050730961"><img src="${blocks[1].img}" width="20" height="40" hyvar="img" hydesc="20*40"/></span><p hyvar="title" class_data="hy2015050730257">${blocks[1].title}</p>
</div>
</s:if>
		
<s:if test='blocks[2].display=="Y"'>
<div class="wcp_c_box_cp block ${blocks[2].ctname}" status="0" hyblk="I" hyct="Y" hydata="${blocks[2].rid}" style="${blocks[2].hyct};" class_data="hy2015050730887">
<ul class="todayList_cp" style="margin-left:0px;" class_data="hy2015050730873">
	<s:if test='blocks[2].list.size == 0'>
		<li class="only4_cp" class_data="hy2015050730683"> 
			<a href="http://www.baidu.com" target="_blank" hyvar="link">
				<div class="img" class_data="hy2015050730686">
					<img src="/images/liebiao/chanpin/cp_1.png" width="261" height="258" hyvar="img" hydesc="200*300"/>
				</div>
				<h2 class="m_text" hyvar="name" class_data="hy2015050730791">8杯水凝蔻八杯水</h2>
				<p class="onlyheight" hyvar="desc" class_data="hy2015050730942">专柜正品 支持专柜验货 7天无理由退换</p>
				<p class="onlyheight hengxian_1107" hyvar="price1" class_data="hy2015050730217">原价：￥152元</p>
				<p class="onlyheight" hyvar="price2" class_data="hy2015050730127">
					<b>促销价：</b>
					<strong>￥100元</strong>
				</p>
				<div class="goumai_anniu_1107" class_data="hy2015050730491">
					<img src="/images/liebiao/chanpin/yanniu.png" width="100" height="28" hyvar="img" hydesc="100*28"/>
				</div>
			</a> 
		</li>
		<li class="only4_cp" class_data="hy2015050730349"> 
			<a href="http://www.baidu.com" target="_blank" hyvar="link">
					<div class="img" class_data="hy2015050730629"> <img src="/images/liebiao/chanpin/cp_2.png" width="261" height="258" hyvar="img" hydesc="200*300"/></div>
					<h2 class="m_text" hyvar="name" class_data="hy2015050730647">抗皱护肤官方</h2>
					<p class="onlyheight" hyvar="desc" class_data="hy2015050730695">正品女美白补水淡斑 巴黎欧莱雅复颜套装</p>
					<p class="onlyheight hengxian_1107" hyvar="price1" class_data="hy2015050730677">原价：￥352元</p>
					<p class="onlyheight" hyvar="price2" class_data="hy2015050730360">
						<b>促销价：</b>
						<strong>￥300元</strong>
					</p>
					<div class="goumai_anniu_1107" class_data="hy2015050730996"><img src="/images/liebiao/chanpin/yanniu.png" width="100" height="28" hyvar="img" hydesc="100*28"/></div>
				</a> 
			</li>
		</s:if>
		<s:else>
		<s:iterator status='st' value='blocks[2].list' var='item'>
			<li class="only4_cp" class_data="hy2015050730956"> 
				<a href="<s:if test='item.linkurl != ""'>${item.linkurl }</s:if><s:else>/${oname}/user/show/${blocks[2].pageid}/${sessionScope.visitUser.source}/ctt-hy-${item.id}.html</s:else>" target="_blank" hyvar="link">
					<div class="img" class_data="hy2015050730912"> <img src="${imgDomain}${item.simgurl}" width="261" height="258" hyvar="img" hydesc="200*300"/></div>
					<h2 class="m_text" hyvar="name" class_data="hy2015050730813">${item.name}</h2>
					<p class="onlyheight" hyvar="desc" class_data="hy2015050730939">${item.tag}</p>
					<p class="onlyheight hengxian_1107" hyvar="price1" class_data="hy2015050730742">原价：￥${item.price }元</p>
					<p class="onlyheight" hyvar="price2" class_data="hy2015050730840">
						<b>促销价：</b>	
						<strong>￥${item.salesprice }元</strong>
					</p>
					<div class="goumai_anniu_1107" class_data="hy2015050730943"><img src="/images/liebiao/chanpin/yanniu.png" width="100" height="28" hyvar="img" hydesc="100*28"/></div>
				</a> 
			</li>
		</s:iterator>
	</s:else>
	</ul>
</div>
</s:if>



<%@include file="/WEB-INF/card/cardfile.jsp"%>
