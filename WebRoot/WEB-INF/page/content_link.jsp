<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<script type="text/javascript" src="/js/qrcode.js"></script> 
<div class="wrap_content">
	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
	<tr>
			<th width="10%">渠道</th>
			<th width="78%">链接地址</th>
			<th width="12%">二维码</th>
	</tr>
	</thead>
	<tbody>
		<tr>
			<td>默认</td>
			<td><a href="${pageDomain}/${sessionScope.account.owner.entity }/user/show/${pageid }/pcn/n-${contentId }.html" >${pageDomain}/${sessionScope.account.owner.entity }/user/show/${pageid }/pcn/n-${contentId }.html</a></td>
			<td><div style="position:relative;"><span class="showQr" style="cursor:pointer">扫一扫</span><div id="qrcode1" style="display:none;position:absolute;top:30px;left:-50px;background:#fff"></div><div></td>
		</tr>
			<tr>
				<td>微信</td>
				<td><a href="${pageDomain}/${sessionScope.account.owner.entity }/user/wxshow/${pageid }/wxn/n-${contentId }.html" >${pageDomain}/${sessionScope.account.owner.entity }/user/wxshow/${pageid }/wxn/n-${contentId }.html</a></td>
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
					var domain='${pageDomain}';
					var entity ='${sessionScope.account.owner.entity }';
					var elText ='${pageDomain}/${oname}/user/show/${pageid}/pcn/n-${contentId}.html';
					var elText2 ='${pageDomain}/${oname}/user/wxshow/${pageid}/wxn/n-${contentId}.html';
					qrcode1.makeCode(elText);
					qrcode2.makeCode(elText2);
				}

				
</script>
