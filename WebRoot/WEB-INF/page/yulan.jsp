<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div style="margin:0 auto;width:800px;">
	<div style="width:320px;height:500px;margin-top: -190px;padding: 186px 41px 17px 30px;float:left;" >
		<iframe src=" ${pageDomain }/${oname}/user/look.action?link=${link}" height="550px" width="450px" frameborder="0" scrolling="no"></iframe>
	</div>
	<div class="preright">
	<div id="qrcode${cid }" class="preqrcode" align="center"></div>
	<div style="font-weight:bold;text-align:center;font-size:18px;margin-top:8px;">扫描二维码在手机上预览</div>
 	<div style="margin-top:30px;"><input type="button" onclick="useMb(${cid },'${stype}')" value="使用此模板" style="width:100%;border:0;background:#3BAFFD;color:#fff;padding:10px;font-size:16px;"/></div>
 	</div>
	<script type="text/javascript" src="/js/qrcode.js"></script> 
	<script type="text/javascript">
	    var qrcode = new QRCode(document.getElementById("qrcode${cid }"), {
			width : 200,
			height : 200
		});
		qrcode.makeCode("${link}");
	</script>
</div>
