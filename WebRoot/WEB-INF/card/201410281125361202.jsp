<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 新闻列表2 -->
<link href="/css/liebiao/3/list.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="wdy_top2 block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct}" hyblk="S" hydata="${blocks[0].rid}">
	<img src="${blocks[0].img}" width="100%" height="100%" hyvar="img" hydesc="720*300"/>
</div>
</s:if>

<s:if test='blocks[1].display=="Y"'>
  <div class="block ${blocks[1].ctname}" status="0" hyct="Y" style="top:138px;${blocks[1].hyct};position:absolute;" hyblk="I" hydata="${blocks[1].rid}">
    <ul class="todayList" style="margin-left:0px;" class_data="hy2015050713272">
    <s:if test='blocks[1].list.size == 0'>
    	 <li class="only4" class_data="hy2015050713476"> 
	  <a href="http://www.baidu.com" target="_blank" hyvar="link">
        <div class="img" class_data="hy2015050713109"> <img src="/images/liebiao/3/logo1.jpg" hyvar="img" hydesc="75*75"> </div>
        <h2 class="m_text" hyvar="title" class_data="hy2015050713919">欢乐谷至12日园区活动取消</h2>
        <p class="onlyheight" hyvar="desc" class_data="hy2015050713209">9日，记者从北京欢乐谷了解到，为了给游客提供良好的游玩体验，</p>
	  </a> 
	  </li>
      <li class="only4" class_data="hy2015050713499"> 
	  <a href="http://www.baidu.com" target="_blank" hyvar="link">
        <div class="img" class_data="hy2015050713229"> <img src="/images/liebiao/3/logo2.jpg" hyvar="img" hydesc="75*75"> </div>
        <h2 class="m_text" hyvar="title" class_data="hy2015050713510">中国移动一家独大是真实的谎言</h2>
        <p class="onlyheight" hyvar="desc" class_data="hy2015050713765">我们不希望中国移动在4G时代绝尘而去，因为那确实不是中国通信市场的福音。</p>
	  </a> 
	  </li>
      <li class="only4" class_data="hy2015050713749"> 
	  <a href="http://www.baidu.com" target="_blank" hyvar="link">
        <div class="img" class_data="hy2015050713852"> <img src="/images/liebiao/3/logo3.jpg" hyvar="img" hydesc="75*75"> </div>
        <h2 class="m_text" hyvar="title" class_data="hy2015050713741">拔管死亡风险：企鹅物联网的命门！</h2>
        <p class="onlyheight" hyvar="desc" class_data="hy2015050713205">企鹅物联网的可靠度，是否值得人们信任呢?一旦企鹅连接出现故障，</p>
	  </a> 
	  </li>
      <li class="only4" class_data="hy2015050713631"> 
	  <a href="http://www.baidu.com" target="_blank" hyvar="link">
        <div class="img" class_data="hy2015050713454"> <img src="/images/liebiao/3/logo4.jpg" hyvar="img" hydesc="75*75"> </div>
        <h2 class="m_text" hyvar="title" class_data="hy2015050713224">股价暴跌：联想规模与利润转化阵痛</h2>
        <p class="onlyheight" hyvar="desc" class_data="hy2015050713439">近日，联想发布了其2014 财年中期业绩财报，从2014年 6 月底到 9 月底，联想营收达到 104.75 亿美元，</p>
	  </a> 
	  </li>
    </s:if>
    <s:else>
		<s:iterator status='st' value='blocks[1].list' var='item'>
			<li class="only4" class_data="hy2015050713421">
				<a <s:if test='#item.islink =="N"'> href="/${oname}/user/show/${blocks[1].pageid}/${sessionScope.visitUser.source}/n-${item.id}.html" </s:if><s:else>href="${item.linkurl}"</s:else> target="_blank" hyvar="link">
					<div class="img" class_data="hy2015050713428"> <img <s:if test='#item.simgurl ==""'>src="/images/xw.jpg"</s:if><s:else>src="${imgDomain}${item.simgurl}"</s:else> hyvar="img" hydesc="75*75"> </div>
					<span class="m_text">${item.title}</span>
					<span class="onlyheight">${item.shortdesc}</span>
				</a> 
			</li>
		</s:iterator>
    </s:else>
</ul>
	 <div class="clear" style="clear:both;"></div>
</div>
</s:if>

<%@include file="/WEB-INF/card/cardfile.jsp"%>