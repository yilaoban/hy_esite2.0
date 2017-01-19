<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文13 -->
<link href="/css/tuwen/13/index.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/css/tuwen/13/TXJcon.css" type="text/css">

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="top_150313_menu block" hyblk="S" hydata="${blocks[0].rid}" class_data="hy20150507105944">
  <header class="ui-header_150313" style="background:rgba(${blocks[0].col},${blocks[0].tm});">
    <h1 hyvar="title" style="color:${blocks[0].fontcol}" class_data="hy20150507105201">${blocks[0].title}</h1>
    <div class="ui-header-left" class_data="hy20150507105528"><a class="ui-header-ico-home" href="${blocks[0].link6}" target="_blank" hyvar="link6"><img hyvar="img1" src="${blocks[0].img1}" width="30" height="30" /></a></div>
    <div class="ui-header-right" class_data="hy20150507105504"><a id="openMenu${pcid}" class="ui-header-ico-menu"><img hyvar="img2" src="${blocks[0].img2}" width="30" height="30" /></a> </div>
  </header>
  <ul class="menu_150313" id="menu_150313${pcid}" class_data="hy20150507105911">
    <li class="icon" class_data="hy20150507105656"><a href="${blocks[0].link1}" target="_blank" hyvar="link1"><span hyvar="title1" class_data="hy20150507105478">${blocks[0].title1}</span></a></li>
    <li class="icon" class_data="hy20150507105980"><a href="${blocks[0].link2}" target="_blank" hyvar="link2"><span hyvar="title2" class_data="hy20150507105360">${blocks[0].title2}</span></a></li>
    <li class="icon" class_data="hy20150507105474"><a href="${blocks[0].link3}" target="_blank" hyvar="link3"><span hyvar="title3" class_data="hy20150507105629">${blocks[0].title3}</span></a></li>
    <li class="icon" class_data="hy20150507105180"><a href="${blocks[0].link4}" target="_blank" hyvar="link4"><span hyvar="title4" class_data="hy20150507105704">${blocks[0].title4}</span></a></li>
    <li class="icon" class_data="hy20150507105417"><a href="${blocks[0].link5}" target="_blank" hyvar="link5"><span hyvar="title5" class_data="hy20150507105298">${blocks[0].title5}</span></a></li>
  </ul>
</div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="gywsm_150313_top block" hyblk="S" hydata="${blocks[1].rid}" class_data="hy20150507105607"><img hyvar="img" src="${blocks[1].img}"/></div>
</s:if>
<s:if test='blocks[2].display=="Y"'>
<div class="gysm_banner_150313 block" hyblk="S" hydata="${blocks[2].rid}" class_data="hy20150507105533">
  <ul class_data="hy20150507105320">
    <li style="border-bottom:3px ${blocks[2].borcol1} solid;" class_data="hy20150507105291"><a style="color:${blocks[2].fontcol1};" hyvar="link1" href="${blocks[2].link1}"><span hyvar="title1" class_data="hy20150507105844">${blocks[2].title1}</span></a></li>
    <li style="border-bottom:3px ${blocks[2].borcol2} solid;" class_data="hy20150507105973"><a style="color:${blocks[2].fontcol2};" hyvar="link2" href="${blocks[2].link2}"><span hyvar="title2" class_data="hy20150507105324">${blocks[2].title2}</span></a></li>
    <li style="border-bottom:3px ${blocks[2].borcol3} solid;" class_data="hy20150507105764"><a style="color:${blocks[2].fontcol3};" hyvar="link3" href="${blocks[2].link3}"><span hyvar="title3" class_data="hy20150507105662">${blocks[2].title3}</span></a></li>
    <li style="border-bottom:3px ${blocks[2].borcol4} solid; border-right:none" class_data="hy20150507105435"><a style="color:${blocks[2].fontcol4};" hyvar="link4" href="${blocks[2].link4}"><span hyvar="title4" class_data="hy20150507105664">${blocks[2].title4}</span></a></li>
  </ul>
</div>
</s:if>
<s:if test='blocks[3].display=="Y"'>
<div class="ybj_150316_box" class_data="hy20150507105715">   
<div hyblk="C" class="ad block" hydata="${blocks[3].rid}" class_data="hy20150507105605">
	<div class="pd_slide-image" class_data="hy20150507105967"> 
		<img class="pd_slide-image-placeholder" src="/images/tuwen/13/134.png" width="100%" height="100%;">
		<div class="pd_slide-image-lists" style="-webkit-transform: translate3d(-300%, 0px, 0px); " class_data="hy20150507105257">
			<s:iterator status='st' value='blocks[3].list' var='item'> 
			<a hyvar="link" class="pd_slide-image-item" href="${item.link}"><img src="${item.img}" hyvar="img" hydesc="453*487" /></a> 
			</s:iterator>
		</div>

		<div class="pd_slide-image-left" class_data="hy20150507105710"> <img src="/images/tuwen/13/left.png"></div>
		<div class="pd_slide-image-right" class_data="hy20150507105323"><img src="/images/tuwen/13/right.png"></div>
	</div>
</div>
</div>
</s:if>
<script type="text/javascript">
	var timer = null;
	$('#openMenu${pcid}').click(function(){
		var iH = $(window).height();
		if($('#menu_150313${pcid}').css('display')=='none'){
			var msk = $('<div id="msk${pcid}"></div>');
			
			$('body').append(msk);			
			$('#menu_150313${pcid}').show().animate({'right':0},500);
		}else{
			$('#menu_150313${pcid}').animate({'right':'-200px'},500);
			
			clearTimeout(timer);
			timer = setTimeout(function(){
				$('#msk${pcid}').remove();
				$('#menu_150313${pcid}').hide();
			},500);
		}
	});
	
	$('#menu_150313${pcid}').click(function(){
		$('#menu_150313${pcid}').animate({'right':'-200px'},500);
			
		clearTimeout(timer);
		timer = setTimeout(function(){
			$('#msk${pcid}').remove();
			$('#menu_150313${pcid}').hide();
		},500);
	});
	
	var detailparams={
		pmId:790,
		productId:2009,
		merchantId:237,
		isYiHaoDian:0,
		isSeriesProduct:1,
		productType:2,
		isGift:0,
	    canBuy:true,
		provinceId:20,
		landpageId:0
	
	};
	var detailPath = {
		idcCtxDomain:"",
		ctxDomain:"",
		busystockDomain:"",
		h5Passport:""
	};
	

</script>


<%@include file="/WEB-INF/card/cardfile.jsp"%>
