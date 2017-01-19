<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<script type="text/javascript" src="/js/qrcode.js"></script> 
<div class="wrap_content">
	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
	<tr>
			<th>渠道名称</th>
			<th>链接地址</th>
			<th>二维码</th>
	</tr>
	</thead>
	<tbody>
		<tr>
			<td>默认</td>
			<td><a href="${pageDomain}/${sessionScope.account.owner.entity }/user/show/${pageid }/pcn.html" >${pageDomain}/${sessionScope.account.owner.entity }/user/show/${pageid }/pcn.html</a></td>
			<td><div style="position:relative;"><span class="showQr" style="cursor:pointer">扫一扫</span><div id="qrcode1" style="display:none;position:absolute;top:30px;left:-50px;background:#fff"></div><div></td>
		</tr>
		<tr>
			<td>微信</td>
			<td><a href="${pageDomain}/${sessionScope.account.owner.entity }/user/wxshow/${pageid }/wxn.html" >${pageDomain}/${sessionScope.account.owner.entity }/user/wxshow/${pageid }/wxn.html</a></td>
			<td><div style="position:relative;"><span class="showQr" style="cursor:pointer">扫一扫</span><div id="qrcode2" style="display:none;position:absolute;top:30px;left:-50px;background:#fff"></div><div></td>
		</tr>
	</tbody>
</table>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$(".showQr").hover(function(){
			$(this).next().show();
		}, function() {
			$(this).next().hide();
		})
		
		makeCode();
	})
	 var qrcode1 = new QRCode(document.getElementById("qrcode1"), {
					width : 150,
					height : 150
				});
	 var qrcode2 = new QRCode(document.getElementById("qrcode2"), {
			width : 150,
			height : 150
		});
				function makeCode () {
					var pageid='${pageid }';
					var domain='${pageDomain}';
					var entity ='${sessionScope.account.owner.entity }';
					var elText = domain+"/"+entity+"/user/show/"+pageid+"/pcn.html";
					var elText2 = domain+"/"+entity+"/user/wxshow/"+pageid+"/wxn.html";
					qrcode1.makeCode(elText);
					qrcode2.makeCode(elText2);
				}

				
</script>
