<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图片列表2 -->
<link href="/css/liebiao/9/list.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%><s:if test='blocks[0].display=="Y"'>
	<div class="liebiao_1023_5_main block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct}"  hyblk="I" hydata="${blocks[0].rid}" class_data="hy2015050724911">
		<s:if test='blocks[0].list.size == 0'>
			<a href="#" target="_blank" hyvar="link">
				<p class="menu28_1" class_data="hy2015050724640"> <img src="/images/liebiao/9/tu1.jpg" hyvar="img" hydesc="300*100" /> <span class="menu_text" hyvar="introduction" class_data="hy2015050724825"> <b hyvar="title">烟雨江南</b> 碧玉妆成一树高，万条垂下绿丝绦。不知细叶谁裁出，二月春风似剪刀。</span> </p>
			</a> 
			<a href="#" target="_blank" hyvar="link">
				<p class="menu28_2" class_data="hy2015050724110"> <img src="/images/liebiao/9/tu2.jpg" hyvar="img" hydesc="300*100" /> <span class="menu_text" hyvar="introduction" class_data="hy2015050724723"> <b hyvar="title">印象江南</b> 人间四月芳菲尽，山寺桃花始盛开。长恨春归无觅处，不知转入此中来。</span> </p>
			</a>
			<a href="#" target="_blank" hyvar="link">
				<p class="menu28_3" class_data="hy2015050724481"> <img src="/images/liebiao/9/tu3.jpg" hyvar="img" hydesc="300*100" /> <span class="menu_text" hyvar="introduction" class_data="hy2015050724945"> <b hyvar="title">温情江南</b> 泉眼无声惜细流，树荫照水爱晴柔。小荷才露尖尖角，早有蜻蜓立上头。</span></p>
			</a>
	    </s:if>
	    <s:else>
			<s:iterator status='st' value='blocks[0].list' var='item'>
				<a href="${linkurl}" target="_blank" hyvar="link">
				<div class="menu28_1" class_data="hy2015050724800"> 
					<img src="${imgDomain}${item.imgurl}" hyvar="img" hydesc="300*100"> 
					<span class="menu_text" class_data="hy2015050724376"> 
						<b hyvar="title">${item.title}</b> 
						<span hyvar="introduction" class_data="hy2015050724535">${item.tag}</span> 
					</span> 
				</div>
				</a>
			</s:iterator>
	    </s:else>
</div>
</s:if>

<%@include file="/WEB-INF/card/cardfile.jsp"%>
