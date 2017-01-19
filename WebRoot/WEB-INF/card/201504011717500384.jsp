<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 智慧神舟 -->
<link href="/css/tuwen/zhsz/index.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="top_150313_menu block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct};" hyblk="S" hydata="${blocks[0].rid}">
  <header class="ui-header_150313" style="background:rgba(${blocks[0].col},${blocks[0].tm});">
    <h1 style="color:${blocks[0].fontcol}">${blocks[0].title}</h1>
    <div class="ui-header-left"> <a class="ui-header-ico-home" href="${blocks[0].link6}" target="_blank" ><img src="${blocks[0].img1}" width="30" height="30" /></a> </div>
    <div class="ui-header-right"> <a id="openMenu${pcid}" class="ui-header-ico-menu"><img src="${blocks[0].img2}" width="30" height="30" /></a> </div>
  </header>
  <ul class="menu_150313" id="menu_150313${pcid}">
    <li class="icon"><a href="${blocks[0].link1}" target="_blank">${blocks[0].title1}</a></li>
    <li class="icon"><a href="${blocks[0].link2}" target="_blank">${blocks[0].title2}</a></li>
    <li class="icon"><a href="${blocks[0].link3}" target="_blank">${blocks[0].title3}</a></li>
    <li class="icon"><a href="${blocks[0].link4}" target="_blank">${blocks[0].title4}</a></li>
    <li class="icon"><a href="${blocks[0].link5}" target="_blank">${blocks[0].title5}</a></li>
  </ul>
</div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="gywsm_150313_top block ${blocks[1].ctname}" status="0" hyct="Y" style="${blocks[1].hyct};" hyblk="S" hydata="${blocks[1].rid}"><img src="${blocks[1].img}"/></div>
</s:if>
<s:if test='blocks[2].display=="Y"'>
<div class="gysm_banner_150313 block ${blocks[2].ctname}" status="0" hyct="Y" style="${blocks[2].hyct};" hyblk="S" hydata="${blocks[2].rid}">
  <ul>
    <li style="border-bottom:3px ${blocks[2].borcol1} solid;"><a style="color:${blocks[2].fontcol1};" hyvar="link1" href="${blocks[2].link1}"><span hyvar="title1">${blocks[2].title1}</span></a></li>
    <li style="border-bottom:3px ${blocks[2].borcol2} solid;"><a style="color:${blocks[2].fontcol2};" hyvar="link2" href="${blocks[2].link2}"><span hyvar="title2">${blocks[2].title2}</span></a></li>
    <li style="border-bottom:3px ${blocks[2].borcol3} solid;"><a style="color:${blocks[2].fontcol3};" hyvar="link3" href="${blocks[2].link3}"><span hyvar="title3">${blocks[2].title3}</span></a></li>
  </ul>
</div>
</s:if>
<div style="clear:both"></div>
<div class="ybj_150316_box">
  <div class="station_bx_150317"><img src="/images/tuwen/zhsz/BG_bx.png"/></div>
  <div class="station_bj_150317"><img src="/images/tuwen/zhsz/BG_bj.png"/></div>
  <div class="station_qhd_150317"><img src="/images/tuwen/zhsz/BG_qhd.png"/></div>
  <div class="station_cz_150317"><img src="/images/tuwen/zhsz/BG_cz.png"/></div>
  <div class="station_wh_150317"><img src="/images/tuwen/zhsz/BG_wh.png"/></div>
  <div class="station_lh_150317"><img src="/images/tuwen/zhsz/BG_lh.png"/></div>
  <div class="station_xz_150317"><img src="/images/tuwen/zhsz/BG_xz.png"/></div>
  <div class="station_zjg_150317"><img src="/images/tuwen/zhsz/BG_zjg.png"/></div>
  <div class="station_chs_150317"><img src="/images/tuwen/zhsz/BG_chs.png"/></div>
  <div class="station_jm_150317"><img src="/images/tuwen/zhsz/BG_jm.png"/></div>
  <div class="station_cd_150317"><img src="/images/tuwen/zhsz/BG_cd.png"/></div>
  <div class="station_cq_150317"><img src="/images/tuwen/zhsz/BG_cq.png"/></div>
  <div class="station_nn_150317"><img src="/images/tuwen/zhsz/BG_nn.png"/></div>
  <div class="station_fs_150317"><img src="/images/tuwen/zhsz/BG_fs.png"/></div>
  <div class="station_hz_150317"><img src="/images/tuwen/zhsz/BG_hz.png"/></div>
  <div class="station_ly_150317"><img src="/images/tuwen/zhsz/BG_ly.png"/></div>
  <div class="station_fz_150317"><img src="/images/tuwen/zhsz/BG_fz.png"/></div>
</div>

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
	
</script>
<%@include file="/WEB-INF/card/cardfile.jsp"%>