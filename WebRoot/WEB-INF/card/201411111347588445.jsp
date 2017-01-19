<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 新闻列表5 -->
<link href="/css/liebiao/chanpin3/list.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
	<div class="liebiao_141111_2_box clearfix block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct}" hyblk="I"  hydata="${blocks[0].rid}" class_data="hy2015050732406">
		<ul class_data="hy2015050732907">
			<s:if test='blocks[0].list.size == 0'>
			<li class_data="hy2015050732131">
		      <a href="#" target="_blank" hyvar="link">
		      <img src="/images/liebiao/chanpin3/tu1.jpg" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="640*300"/>
		      <span hyvar="introduction" class_data="hy2015050732498">本商品已加入双十一狂欢节活动，将以全年超低活动价开始抢购，备货数量有限，亲们还等什么？现在立马【领取优惠券+加入购物车+收藏宝贝】吧，千万不要让机会溜走了...</span>
		      </a>
		      </li>
		        <li class_data="hy2015050732107">
		        <a href="#" target="_blank" hyvar="link">
		      <img src="/images/liebiao/chanpin3/tu2.jpg" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="640*300"/>
		      <span hyvar="introduction" class_data="hy2015050732527">亲子装一家三口2014款秋冬装棉服母子母女装羽绒棉衣加厚棉袄外套即日起，童装159元，爸爸妈妈装为198元！买两件以上包邮，加购物车一起拍下免邮...</span>
		      </a>
		       </li>
			</s:if>
			<s:else>
				<s:iterator status='st' value='blocks[0].list' var='item'>
					<li class_data="hy2015050732458">
					<a <s:if test='#item.islink =="N"'> href="/${oname}/user/show/${blocks[0].pageid}/${sessionScope.visitUser.source}/n-${item.id}.html" </s:if><s:else>href="${item.linkurl}"</s:else> target="_blank" hyvar="link">
				      <img <s:if test='#item.simgurl ==""'>src="/images/xw.jpg"</s:if><s:else>src="${imgDomain}${item.simgurl}"</s:else> width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="640*300"/>
				      <span hyvar="introduction" class_data="hy2015050732510">${item.shortdesc}</span>
				      </a>
				      </li>
				</s:iterator>
			</s:else>
		</ul>
</div>
</s:if>



<%@include file="/WEB-INF/card/cardfile.jsp"%>
