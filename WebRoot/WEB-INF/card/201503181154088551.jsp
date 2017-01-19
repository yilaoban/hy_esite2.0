<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 新闻列表7 -->
<link href="/css/liebiao/xw7/index.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="top_150313_menu block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct};" hyblk="S" hydata="${blocks[0].rid}" class_data="hy20150507103616">
  <header class="ui-header_150313" style="background:rgba(${blocks[0].col},${blocks[0].tm});">
    <h1 hyvar="title" style="color:${blocks[0].fontcol}" class_data="hy20150507103262">${blocks[0].title}</h1>
    <div class="ui-header-left" class_data="hy20150507103301"><a class="ui-header-ico-home" href="${blocks[0].link6}" target="_blank" hyvar="link6"><img hyvar="img1" src="${blocks[0].img1}" width="30" height="30" /></a></div>
    <div class="ui-header-right" class_data="hy20150507103832"><a id="openMenu${pcid}" class="ui-header-ico-menu"><img hyvar="img2" src="${blocks[0].img2}" width="30" height="30" /></a> </div>
  </header>
  <ul class="menu_150313" id="menu_150313${pcid}" class_data="hy20150507103735">
    <li class="icon" class_data="hy20150507103599"><a href="${blocks[0].link1}" target="_blank" hyvar="link1"><span hyvar="title1" class_data="hy20150507103902">${blocks[0].title1}</span></a></li>
    <li class="icon" class_data="hy20150507103760"><a href="${blocks[0].link2}" target="_blank" hyvar="link2"><span hyvar="title2" class_data="hy20150507103515">${blocks[0].title2}</span></a></li>
    <li class="icon" class_data="hy20150507103494"><a href="${blocks[0].link3}" target="_blank" hyvar="link3"><span hyvar="title3" class_data="hy20150507103912">${blocks[0].title3}</span></a></li>
    <li class="icon" class_data="hy20150507103497"><a href="${blocks[0].link4}" target="_blank" hyvar="link4"><span hyvar="title4" class_data="hy20150507103116">${blocks[0].title4}</span></a></li>
    <li class="icon" class_data="hy20150507103942"><a href="${blocks[0].link5}" target="_blank" hyvar="link5"><span hyvar="title5" class_data="hy20150507103748">${blocks[0].title5}</span></a></li>
  </ul>
</div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="gywsm_150313_top block ${blocks[1].ctname}" status="0" hyct="Y" style="${blocks[1].hyct};" hyblk="S" hydata="${blocks[1].rid}" class_data="hy20150507103852"><img hyvar="img" src="${blocks[1].img}"/></div>
</s:if>
<s:if test='blocks[2].display=="Y"'>
<div class="gysm_banner_150313 block ${blocks[2].ctname}" status="0" hyct="Y" style="${blocks[2].hyct};" hyblk="S" hydata="${blocks[2].rid}" class_data="hy20150507103315">
  <ul class_data="hy20150507103511">
    <li style="border-bottom:3px ${blocks[2].borcol1} solid;" class_data="hy20150507103755"><a style="color:${blocks[2].fontcol1};" hyvar="link1" href="${blocks[2].link1}"><span hyvar="title1" class_data="hy20150507103139">${blocks[2].title1}</span></a></li>
    <li style="border-bottom:3px ${blocks[2].borcol2} solid;" class_data="hy20150507103963"><a style="color:${blocks[2].fontcol2};" hyvar="link2" href="${blocks[2].link2}"><span hyvar="title2" class_data="hy20150507103135">${blocks[2].title2}</span></a></li>
    <li style="border-bottom:3px ${blocks[2].borcol3} solid;" class_data="hy20150507103975"><a style="color:${blocks[2].fontcol3};" hyvar="link3" href="${blocks[2].link3}"><span hyvar="title3" class_data="hy20150507103654">${blocks[2].title3}</span></a></li>
    <li style="border-bottom:3px ${blocks[2].borcol4} solid; border-right:none" class_data="hy20150507103843"><a style="color:${blocks[2].fontcol4};" hyvar="link4" href="${blocks[2].link4}"><span hyvar="title4" class_data="hy20150507103480">${blocks[2].title4}</span></a></li>
  </ul>
</div>
</s:if>
<div class="jlly_center_150313" class_data="hy20150507103466">

  <div class="jlly_box_150316" class_data="hy20150507103374">
  <s:if test='blocks[3].display=="Y"'>
    <div class="jlly_box_150316_img block ${blocks[3].ctname}" status="0" hyct="Y" style="${blocks[3].hyct};" hyblk="S" hydata="${blocks[3].rid}" class_data="hy20150507103547"><a hyvar="link" href="${blocks[3].link}" target="_blank"><img hyvar="img" src="${blocks[3].img}"/></a></div>
    </s:if>
    <s:if test='blocks[4].display=="Y"'>
    <div class="jlly_b_150316_c block clearfix ${blocks[4].ctname}" status="0" hyct="Y" style="${blocks[4].hyct};" hyblk="C" hydata="${blocks[4].rid}" class_data="hy20150507103147">
      <ul id="page${pcid}" class_data="hy20150507103263">
      	<s:if test='blocks[4].list.size == 0'>
      		<li class_data="hy20150507103531">
	         <a href="#" target="_blank">
	          <div class="jlly_b_150316_c_l" class_data="hy20150507103439">神州数码携手阿里云 领跑中国智慧城市云时代</div>
	          <div class="jlly_b_150316_c_r" class_data="hy20150507103310"><img src="/images/liebiao/xw7/c_07.png"/></div>
	          </a>
	        </li>
	        <li class_data="hy20150507103839">
	        <a href="#" target="_blank">
	          <div class="jlly_b_150316_c_l" class_data="hy20150507103956">珠海市与神州数码签署智慧城市战略合作协议</div>
	          <div class="jlly_b_150316_c_r" class_data="hy20150507103649"><img src="/images/liebiao/xw7/c_10.png"/></div>
	          </a>
	        </li>
	        <li class_data="hy20150507103307">
	        <a href="#" target="_blank">
	          <div class="jlly_b_150316_c_l" class_data="hy20150507103350">荆门市与神州数码签署智慧城市战略合作协议</div>
	          <div class="jlly_b_150316_c_r" class_data="hy20150507103726"><img src="/images/liebiao/xw7/c_12.png"/></div>
	          </a>
	        </li>
      	</s:if>
      	<s:else>
      	
		<s:iterator status='st' value='blocks[4].list' var='item'>
        <li class_data="hy20150507103124">
        <a <s:if test='#item.islink =="N"'> href="/${oname}/user/show/${blocks[4].pageid}/${sessionScope.visitUser.source}/n-${item.id}.html" </s:if><s:else>href="${item.linkurl}"</s:else> target="_blank" hyvar="link">
          <div class="jlly_b_150316_c_l" class_data="hy20150507103329">${item.shortdesc}</div>
          <div class="jlly_b_150316_c_r" class_data="hy20150507103776"><img src="${imgDomain}${item.simgurl}" /></div>
          </a>
        </li>
	    </s:iterator>
      	</s:else>
      </ul>
		<div class="pagnation" id="innerpage${pcid}"></div>
		<div style="width:100%; float:left; height:20px;"></div>
</div>
</s:if>
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
