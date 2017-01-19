<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 新闻列表6  -->
<link href="/css/liebiao/xw6/index.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="top_150313_menu block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct};" hyblk="S" hydata="${blocks[0].rid}" class_data="hy2015050798812">
  <header class="ui-header_150313" style="background:rgba(${blocks[0].col},${blocks[0].tm});">
    <h1 hyvar="title" style="color:${blocks[0].fontcol}" class_data="hy2015050798585">${blocks[0].title}</h1>
    <div class="ui-header-left" class_data="hy2015050798499"><a class="ui-header-ico-home" href="${blocks[0].link6}" target="_blank" hyvar="link6"><img hyvar="img1" src="${blocks[0].img1}" width="30" height="30" /></a></div>
    <div class="ui-header-right" class_data="hy2015050798262"><a id="openMenu${pcid}" class="ui-header-ico-menu"><img hyvar="img2" src="${blocks[0].img2}" width="30" height="30" /></a> </div>
  </header>
<ul class="menu_150313" id="menu_150313${pcid}" class_data="hy2015050798845">
    <li class="icon" class_data="hy2015050798448"><a href="${blocks[0].link1}" target="_blank" hyvar="link1"><span hyvar="title1" class_data="hy2015050798780">${blocks[0].title1}</span></a></li>
    <li class="icon" class_data="hy2015050798310"><a href="${blocks[0].link2}" target="_blank" hyvar="link2"><span hyvar="title2" class_data="hy2015050798332">${blocks[0].title2}</span></a></li>
    <li class="icon" class_data="hy2015050798810"><a href="${blocks[0].link3}" target="_blank" hyvar="link3"><span hyvar="title3" class_data="hy2015050798495">${blocks[0].title3}</span></a></li>
    <li class="icon" class_data="hy2015050798554"><a href="${blocks[0].link4}" target="_blank" hyvar="link4"><span hyvar="title4" class_data="hy2015050798904">${blocks[0].title4}</span></a></li>
    <li class="icon" class_data="hy2015050798288"><a href="${blocks[0].link5}" target="_blank" hyvar="link5"><span hyvar="title5" class_data="hy2015050798951">${blocks[0].title5}</span></a></li>
  </ul>
</div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="gywsm_150313_top block ${blocks[1].ctname}" status="0" hyct="Y" style="${blocks[1].hyct};" hyblk="S" hydata="${blocks[1].rid}" class_data="hy2015050798600"><img hyvar="img" src="${blocks[1].img}"/></div>
</s:if>
<s:if test='blocks[2].display=="Y"'>
<div class="gysm_banner_150313 block ${blocks[2].ctname}" status="0" hyct="Y" style="${blocks[2].hyct};" hyblk="S" hydata="${blocks[2].rid}" class_data="hy2015050798675">
  <ul class_data="hy2015050798574">
    <li style="border-bottom:3px ${blocks[2].borcol1} solid;" class_data="hy2015050798785"><a style="color:${blocks[2].fontcol1};" hyvar="link1" href="${blocks[2].link1}"><span hyvar="title1" class_data="hy2015050798375">${blocks[2].title1}</span></a></li>
    <li style="border-bottom:3px ${blocks[2].borcol2} solid;" class_data="hy2015050798258"><a style="color:${blocks[2].fontcol2};" hyvar="link2" href="${blocks[2].link2}"><span hyvar="title2" class_data="hy2015050798523">${blocks[2].title2}</span></a></li>
    <li style="border-bottom:3px ${blocks[2].borcol3} solid; border-right:none" class_data="hy2015050798230"><a style="color:${blocks[2].fontcol3};" hyvar="link3" href="${blocks[2].link3}"><span hyvar="title3" class_data="hy2015050798446">${blocks[2].title3}</span></a></li>
  </ul>
</div>
</s:if>
<s:if test='blocks[3].display=="Y"'>
<div class="smzj_150313_box block ${blocks[3].ctname}" status="0" hyct="Y" style="${blocks[3].hyct};" hyblk="I" hydata="${blocks[3].rid}" class_data="hy2015050798311">
  <ul class_data="hy2015050798905">
  	<s:if test='blocks[3].list.size == 0'>
  		<li class_data="hy2015050798145">
	      <div class="smzj_150313_zj_left" class_data="hy2015050798408">2015年<br />
	        11月12日</div>
	      <div class="smzj_150313_zj_img" class_data="hy2015050798590"><img src="/images/liebiao/xw6/zj_03.png"/></div>
	      <div class="smzj_150313_zj_right" class_data="hy2015050798740">
	        <div class="zj_right_img" class_data="hy2015050798902"><img src="/images/liebiao/xw6/zj_t_12.png"/></div>
	        <div class="zj_right_desc" class_data="hy2015050798537">沧州市政府与神州数码签署“智慧城市”战略合作协议</div>
	      </div>
	    </li>
	    <li class_data="hy2015050798831">
	      <div class="smzj_150313_zj_left" class_data="hy2015050798251">2015年<br />
	        11月21日</div>
	      <div class="smzj_150313_zj_img" class_data="hy2015050798530"><img src="/images/liebiao/xw6/zj_06.png" /></div>
	      <div class="smzj_150313_zj_right" class_data="hy2015050798209">
	        <div class="zj_right_img" class_data="hy2015050798488"><img src="/images/liebiao/xw6/zj_t_12.png"/></div>
	        <div class="zj_right_desc" class_data="hy2015050798528">“新起点、新蓝海、新征程——神州信息智慧农村战略发布会”</div>
	      </div>
	    </li>
	    <li class_data="hy2015050798709">
	      <div class="smzj_150313_zj_left" class_data="hy2015050798708">2015年<br />
	        12月5日</div>
	      <div class="smzj_150313_zj_img" class_data="hy2015050798962"><img src="/images/liebiao/xw6/zj_06.png" /></div>
	      <div class="smzj_150313_zj_right" class_data="hy2015050798325">
	        <div class="zj_right_img" class_data="hy2015050798172"><img src="/images/liebiao/xw6/zj_t_12.png"/></div>
	        <div class="zj_right_desc" class_data="hy2015050798777">神州数码承建的中国杨凌农业大数据中心在第二十一届杨凌农高会上正式揭牌启用</div>
	      </div>
	    </li>
	</s:if>
	<s:else>
		<s:iterator status='st' value='blocks[3].list' var='item'>
		      <li class_data="hy2015050798787">
		      <a <s:if test='#item.islink =="N"'> href="/${oname}/user/show/${blocks[3].pageid}/${sessionScope.visitUser.source}/n-${item.id}.html" </s:if><s:else>href="${item.linkurl}"</s:else> target="_blank" hyvar="link">
		      <div class="smzj_150313_zj_left" class_data="hy2015050798142"><s:if test='#st.count > "6"'><s:property value="#item.createtimeStr.substring(0,5)"/></s:if><s:else><s:property value="#item.createtimeStr.substring(0,8)"/></s:else></div>
		      <div class="smzj_150313_zj_img" class_data="hy2015050798331"><s:if test='#st.count == "1"'><img src="/images/liebiao/xw6/zj_03.png"/></s:if><s:else><img src="/images/liebiao/xw6/zj_06.png" /></s:else></div>
		      <div class="smzj_150313_zj_right" class_data="hy2015050798516">
		        <div class="zj_right_img" class_data="hy2015050798752"><img <s:if test='#item.simgurl ==""'>src="/images/xw.jpg"</s:if><s:else>src="${imgDomain}${item.simgurl}"</s:else>/></div>
		        <div class="zj_right_desc" class_data="hy2015050798428">${item.shortdesc}</div>
		      </div>
		      </a>
		    </li>
		</s:iterator>
	</s:else>
  </ul>
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
	
</script>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
