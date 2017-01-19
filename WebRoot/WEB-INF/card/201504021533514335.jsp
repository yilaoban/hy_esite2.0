<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 新闻列表10 -->
<link href="/css/liebiao/xw10/index.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="top_150313_menu block" hyblk="S" hydata="${blocks[0].rid}" class_data="hy20150507109743">
  <header class="ui-header_150313" style="background:rgba(${blocks[0].col},${blocks[0].tm});">
    <h1 hyvar="title" style="color:${blocks[0].fontcol}" class_data="hy20150507109893">${blocks[0].title}</h1>
    <div class="ui-header-left" class_data="hy20150507109166"><a class="ui-header-ico-home" href="${blocks[0].link6}" target="_blank" hyvar="link6"><img hyvar="img1" src="${blocks[0].img1}" width="30" height="30" /></a></div>
    <div class="ui-header-right" class_data="hy20150507109745"><a id="menu_150313${pcid}" class="ui-header-ico-menu"><img hyvar="img2" src="${blocks[0].img2}" width="30" height="30" /></a> </div>
  </header>
  <ul class="menu_150313" id="menu_150313${pcid}" class_data="hy20150507109953">
    <li class="icon" class_data="hy20150507109414"><a href="${blocks[0].link1}" target="_blank" hyvar="link1"><span hyvar="title1" class_data="hy20150507109970">${blocks[0].title1}</span></a></li>
    <li class="icon" class_data="hy20150507109335"><a href="${blocks[0].link2}" target="_blank" hyvar="link2"><span hyvar="title2" class_data="hy20150507109562">${blocks[0].title2}</span></a></li>
    <li class="icon" class_data="hy20150507109860"><a href="${blocks[0].link3}" target="_blank" hyvar="link3"><span hyvar="title3" class_data="hy20150507109820">${blocks[0].title3}</span></a></li>
    <li class="icon" class_data="hy20150507109730"><a href="${blocks[0].link4}" target="_blank" hyvar="link4"><span hyvar="title4" class_data="hy20150507109332">${blocks[0].title4}</span></a></li>
    <li class="icon" class_data="hy20150507109721"><a href="${blocks[0].link5}" target="_blank" hyvar="link5"><span hyvar="title5" class_data="hy20150507109640">${blocks[0].title5}</span></a></li>
  </ul>
</div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="gywsm_150313_top block" hyblk="S" hydata="${blocks[1].rid}" class_data="hy20150507109696"><img hyvar="img" src="${blocks[1].img}" hydesc="640*440"/></div>
</s:if>
<div class="shzp_center_150313" class_data="hy20150507109519">
  <div class="shzp_box_150316" class_data="hy20150507109646">
  <s:if test='blocks[2].display=="Y"'>
    <div class="shzp_box_150316_img block" hyblk="S" hydata="${blocks[2].rid}" class_data="hy20150507109261">
      <ul class_data="hy20150507109803">
        <li class_data="hy20150507109555"><a href="${blocks[2].link1}" target="_blank" hyvar="link1"><img src="${blocks[2].img1}" hyvar="img1" hydesc="200*100" /></a></li>
        <li style="border-right:none;" class_data="hy20150507109890"><a href="${blocks[2].link2}" target="_blank" hyvar="link2"><img src="${blocks[2].img2}" hyvar="img2" hydesc="200*100" /></a></li>
      </ul>
    </div>
    </s:if>
    <s:if test='blocks[3].display=="Y"'>
    <div class="shzp_b_150316_c block" hyblk="I" hydata="${blocks[3].rid}" class_data="hy20150507109303">
      <ul id="page${pcid}" class_data="hy20150507109536">
      	<s:if test='blocks[3].list.size == 0'>
      		<li class_data="hy20150507109293">
        <a href="#" target="_blank">
          <div class="shzp_b_150316_c_l" class_data="hy20150507109964">华为产品区域销售（分销）</div>
          <div class="shzp_b_150316_c_r" class_data="hy20150507109683"><img src="/images/liebiao/xw10/c_07.png"/></div>
          </a>
        </li>
        <li class_data="hy20150507109654">
        <a href="#" target="_blank">
          <div class="shzp_b_150316_c_l" class_data="hy20150507109296">支票管理岗慧城市云时代</div>
          <div class="shzp_b_150316_c_r" class_data="hy20150507109955"><img src="/images/liebiao/xw10/c_10.png"/></div>
          </a>
        </li>
        <li class_data="hy20150507109143">
        <a href="#" target="_blank">
          <div class="shzp_b_150316_c_l" class_data="hy20150507109445">行业销售（高教行业）</div>
          <div class="shzp_b_150316_c_r" class_data="hy20150507109590"><img src="/images/liebiao/xw10/c_12.png"/></div>
          </a>
        </li>
        <li class_data="hy20150507109663">
        <a href="#" target="_blank">
          <div class="shzp_b_150316_c_l" class_data="hy20150507109241">华为数通产品渠道销售</div>
          <div class="shzp_b_150316_c_r" class_data="hy20150507109833"><img src="/images/liebiao/xw10/c_07.png"/></div>
          </a>
        </li>   
      	</s:if>
      	<s:else>
		<s:iterator status='st' value='blocks[3].list' var='item'>
			 <li class_data="hy20150507109907">
			 	<a <s:if test='#item.islink =="N"'> href="/${oname}/user/show/${blocks[3].pageid}/${sessionScope.visitUser.source}/n-${item.id}.html" </s:if><s:else>href="${item.linkurl}"</s:else> target="_blank" hyvar="link">
		          <div class="shzp_b_150316_c_l" class_data="hy20150507109182">${item.shortdesc}</div>
		          <div class="shzp_b_150316_c_r" class_data="hy20150507109347"><img src="${imgDomain}${item.simgurl}"/></div>
		          </a>
		        </li>   
	    </s:iterator>
      	</s:else>
      </ul>
    </div>
<div class="pagnation" id="innerpage${pcid}"></div>
<div style="width:100%; float:left; height:20px;"></div>
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
