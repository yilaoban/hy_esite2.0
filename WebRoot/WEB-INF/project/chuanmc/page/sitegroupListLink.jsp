<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript" src="/js/qrcode.js"></script>
<div class="listlinkcontent">
	<div class="listlinkcontentmenu"><a href="/${oname }/page/sitegroupList.action">我的活动</a> >> <b>${site.groupName }</b></div>
<s:if test="pgid == 0">
	<div style="height:500px;"><h1>此站点未上线或者未发布!</h1></div>
</s:if>
<s:else>
	<p>1、默认链接推广  <img src="/images/u484.png" title="页面上线后的链接,以该链接推广可获取用户IP、	区域、移动端设备、访问时间、互动时间信息." /></p>
	<div class="linkcontentbody">
		<ul>
			<li>链接地址:</li>
			<li><span> ${pageDomain }/${oname }/user/show/${pgid}.html</span></li>
			<li class="listlinkcode">二维码：</li>
			<li>
				<div id="mr-link"></div>
			</li>
		</ul>
	</div>
	<p>2、微信链接推广  <img src="/images/u484.png" title="页面设置微信分享后的链接,以该链接推广可获取粉丝微信昵称、区域、移动端设备、访问时间、互动时间信息." /></p>
	<div class="linkcontentbody">
		<ul>
			<li>链接地址:</li>
			<li><span> ${pageDomain }/${oname}/user/wxshow/${pgid}/wxn.html</span></li>
			<li class="listlinkcode">二维码：</li>
			<li>
				<div id="wx-link"></div>
			</li>
		</ul>
	</div>
	<script type="text/javascript">
		var qrcode1 = new QRCode(document.getElementById("mr-link"), {
			width : 150,
			height : 150
		});
		qrcode1.makeCode(" ${pageDomain }/${oname }/user/show/${pgid}.html");
		
		var qrcode2 = new QRCode(document.getElementById("wx-link"), {
			width : 150,
			height : 150
		});
		qrcode2.makeCode(" ${pageDomain }/${oname}/user/wxshow/${pgid}/wxn.html");
	</script>
</s:else>
</div>