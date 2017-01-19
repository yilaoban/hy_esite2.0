<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 积分二维码 -->
<link rel="stylesheet" type="text/css" href="/css/shouye/jf/index.css" />
<%@include file="/WEB-INF/card/background.jsp"%>
<div class="grey_bg">
  <div class="name_space">
    <div class="white_card">
      <div class="nameLogo block ${blocks[0].ctname}" status="0" hyct="Y" hydata="${blocks[0].rid}">
        <img class="logo" src="${blocks[0].img}" />
        <p class="name">${blocks[0].title}</p>
        <div style="clear: both"></div>
      </div>
      <div class="qr_content">
        <div id="qrcode" class="qr"></div>
      </div>
      <p class="p_desc">扫一扫上面的二维码，获得消费积分</p>
    </div>
  </div>
</div>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
<script type="text/javascript" src="/js/qrcode.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var dzway = $("#hy_kv").val();
		if (!dzway) {
			return;
		}
		var kk = dzway.split("-");
		var type = kk[0];
		var url = "";
		var dzwayid = kk[1];
		if(type == "X"){
			 url = "${pageDomain}/${oname}/user/wxshowp/uidt/" + dzwayid + ".html";
		}else{
			 url = "${pageDomain}/${oname}/user/wxshowp/uiht/" + dzwayid + ".html";
		}
		var qrcode = new QRCode(document.getElementById("qrcode"), {
			width : 200,
			height : 200
		});
		qrcode.clear();
		qrcode.makeCode(url);
	});

	
</script>