<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 新闻列表9 -->
<link href="/css/liebiao/xw9/index.css" rel="stylesheet" type="text/css" />

<div style="top:0px;left:0px;width:100%;height:100%;position:fixed;z-index:-999;background:${dto.tc.bg };background-size: cover; background-position: 50% 50%;"></div>
<s:if test='blocks[0].display=="Y"'>
<div class="top_150313_menu block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct};" hyblk="S" hydata="${blocks[0].rid}" class_data="hy20150507108354">
  <header class="ui-header_150313" style="background:rgba(${blocks[0].col},${blocks[0].tm});">
    <h1 hyvar="title" style="color:${blocks[0].fontcol}" class_data="hy20150507108878">${blocks[0].title}</h1>
    <div class="ui-header-left" class_data="hy20150507108351"><a class="ui-header-ico-home" href="${blocks[0].link6}" target="_blank" hyvar="link6"><img hyvar="img1" src="${blocks[0].img1}" width="30" height="30" /></a></div>
    <div class="ui-header-right" class_data="hy20150507108931"><a id="openMenu${pcid}" class="ui-header-ico-menu"><img hyvar="img2" src="${blocks[0].img2}" width="30" height="30" /></a> </div>
  </header>
  <ul class="menu_150313" id="menu_150313${pcid}" class_data="hy20150507108653">
    <li class="icon" class_data="hy20150507108406"><a href="${blocks[0].link1}" target="_blank" hyvar="link1"><span hyvar="title1" class_data="hy20150507108894">${blocks[0].title1}</span></a></li>
    <li class="icon" class_data="hy20150507108800"><a href="${blocks[0].link2}" target="_blank" hyvar="link2"><span hyvar="title2" class_data="hy20150507108232">${blocks[0].title2}</span></a></li>
    <li class="icon" class_data="hy20150507108866"><a href="${blocks[0].link3}" target="_blank" hyvar="link3"><span hyvar="title3" class_data="hy20150507108455">${blocks[0].title3}</span></a></li>
    <li class="icon" class_data="hy20150507108149"><a href="${blocks[0].link4}" target="_blank" hyvar="link4"><span hyvar="title4" class_data="hy20150507108136">${blocks[0].title4}</span></a></li>
    <li class="icon" class_data="hy20150507108786"><a href="${blocks[0].link5}" target="_blank" hyvar="link5"><span hyvar="title5" class_data="hy20150507108247">${blocks[0].title5}</span></a></li>
  </ul>
</div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="gywsm_150313_top block ${blocks[1].ctname}" status="0" hyct="Y" style="${blocks[1].hyct};" hyblk="S" hydata="${blocks[1].rid}" class_data="hy20150507108428"><img hyvar="img" src="${blocks[1].img}"/></div>
</s:if>
<s:if test='blocks[2].display=="Y"'>
<div class="fxcs_150316_banner block ${blocks[2].ctname}" status="0" hyct="Y" style="${blocks[2].hyct};" hyblk="S" hydata="${blocks[2].rid}" style="background:rgba(${blocks[2].col},${blocks[2].tm});border-bottom:3px ${blocks[2].borcol} solid;" class_data="hy20150507108692"><span hyvar="title" style="color:${blocks[2].fontcol}" class_data="hy20150507108284">${blocks[2].title}</span></div>
</s:if>
<div class="jlly_center_150313" class_data="hy20150507108552">

  <s:if test='blocks[3].display=="Y"'>
    <div class="jlly_box_150316_img block ${blocks[3].ctname}" status="0" hyct="Y" style="${blocks[3].hyct};" hyblk="S" hydata="${blocks[3].rid}" class_data="hy20150507108609"><a hyvar="link" href="${blocks[3].link}" target="_blank"><img hyvar="img" src="${blocks[3].img}"/></a></div>
    </s:if>
    <s:if test='blocks[4].display=="Y"'>
    <div class="jlly_b_150316_c block ${blocks[4].ctname}" status="0" hyct="Y" style="${blocks[4].hyct};" hyblk="C" hydata="${blocks[4].rid}">
      <ul id="page${pcid}" class_data="hy20150507108423">
      	<s:if test='blocks[4].list.size == 0'>
      		<li class_data="hy20150507108600">
	         <a href="#" target="_blank">
	          <div class="jlly_b_150316_c_l" class_data="hy20150507108939">神州数码携手阿里云 领跑中国智慧城市云时代</div>
	          <div class="jlly_b_150316_c_r" class_data="hy20150507108103"><img src="/images/liebiao/xw9/c_07.png"/></div>
	          </a>
	        </li>
	        <li class_data="hy20150507108597">
	        <a href="#" target="_blank">
	          <div class="jlly_b_150316_c_l" class_data="hy20150507108113">珠海市与神州数码签署智慧城市战略合作协议</div>
	          <div class="jlly_b_150316_c_r" class_data="hy20150507108331"><img src="/images/liebiao/xw9/c_10.png"/></div>
	          </a>
	        </li>
	        <li class_data="hy20150507108757">
	        <a href="#" target="_blank">
	          <div class="jlly_b_150316_c_l" class_data="hy20150507108802">荆门市与神州数码签署智慧城市战略合作协议</div>
	          <div class="jlly_b_150316_c_r" class_data="hy20150507108107"><img src="/images/liebiao/xw9/c_12.png"/></div>
	          </a>
	        </li>
      	</s:if>
      	<s:else>
      	
		<s:iterator status='st' value='blocks[4].list' var='item'>
        <li class_data="hy20150507108719">
        <a <s:if test='#item.islink =="N"'> href="/${oname}/user/show/${blocks[4].pageid}/${sessionScope.visitUser.source}/n-${item.id}.html" </s:if><s:else>href="${item.linkurl}"</s:else> target="_blank" hyvar="link">
          <div class="jlly_b_150316_c_l" class_data="hy20150507108747">${item.shortdesc}</div>
          <div class="jlly_b_150316_c_r" class_data="hy20150507108670"><img src="${imgDomain}${item.simgurl}" /></div>
          </a>
        </li>
	    </s:iterator>
      	</s:else>
      </ul>
	<div class="pagnation" id="innerpage${pcid}"></div>
	<div style="width:100%; float:left; height:20px;"></div>
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
	var obj,j;
	var page=0;
	var nowPage=0;
	var listNum=3;
	var PagesLen;
	var PageNum=10;
	$(document).ready(function(){
		obj=$("#page${pcid} li");
		j=obj.length;
		PagesLen=Math.ceil(j/listNum);
		upPage(0);
	});
	function upPage(p){
		nowPage=p
		for (var i=0;i<j;i++){
			obj[i].style.display="none";
		}
		for (var i=p*listNum;i<(p+1)*listNum;i++){
			if(obj[i])obj[i].style.display="block";
		}
		strS='<a href="JavaScript:FirstPage()" onclick="upPage(0)">首页</a>  ';
		var PageNum_2=PageNum%2==0?Math.ceil(PageNum/2)+1:Math.ceil(PageNum/2);
		var PageNum_3=PageNum%2==0?Math.ceil(PageNum/2):Math.ceil(PageNum/2)+1;
		var strC="",startPage,endPage;
		if (PageNum>=PagesLen){
			startPage=0;endPage=PagesLen-1
		}
		else if(nowPage<PageNum_2){
			startPage=0;
			endPage=PagesLen-1>PageNum?PageNum:PagesLen-1;
		}
		else{
			startPage=nowPage+PageNum_3>=PagesLen?PagesLen-PageNum-1: nowPage-PageNum_2+1;
			var t=startPage+PageNum;
			endPage=t>PagesLen?PagesLen-1:t
		}
		for(var i=startPage;i<=endPage;i++){
			if(i==nowPage){
				strC+='<a href="JavaScript:curPage()" class="curpage current" onclick="upPage('+i+')">'+(i+1)+'</a> ';
			}
		 	else 
		 	strC+='<a href="#JavaScript:Page()" onclick="upPage('+i+')">'+(i+1)+'</a> ';
		}
		strE=' <a href="JavaScript:LastPage()" onclick="upPage('+(PagesLen-1)+')">尾页</a>  ';
		strE2="  <a>"+PagesLen+"页</a>";
		$("#innerpage${pcid}").html(strS+strC+strE+strE2);
	}
	
	
</script>
  

</div>

<%@include file="/WEB-INF/card/cardfile.jsp"%>
