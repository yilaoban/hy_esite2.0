<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 邀请伙伴 -->
<link href="/css/cb/partner_1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/qrcode.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var kv = $('#hy_kv').val();
	var hyuid = ${sessionScope.visitUser.hyUser.id};
	var href = "${pageDomain}/${oname}/user/show/"+kv+"/wxn/"+hyuid+".html";
	qrcode.makeCode(href);
});
</script>
<%@include file="/WEB-INF/card/background.jsp"%>

<div class="zhishi"></div>
<div class="main0527">
	  <p><i class="tx_7"><img src="${sessionScope.visitUser.wxUser.headimgurl}" alt=""></i></p>
	  <p class="p_1">${sessionScope.visitUser.wxUser.nickname}</p>
	  <p class="p_2">长按保存下图【二维码】发送给好友</p>
	  <p>
	  	<i class="ewm" id="qrcode"></i>
	  	</p>
	</div>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
<script type="text/javascript">
var qrcode = new QRCode(document.getElementById("qrcode"), {
	width : 200,
	height : 200
});
</script>