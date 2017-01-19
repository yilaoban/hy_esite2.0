<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
<h4 >扫一扫</h4>
	<div id="qrcode"></div>
</div>
<script type="text/javascript" src="/js/qrcode.js"></script> 
<script type="text/javascript">
    var qrcode = new QRCode(document.getElementById("qrcode"), {
		width : 200,
		height : 200
	});

	function makeCode () {
		var elText = "${wx_url}";
		qrcode.makeCode(elText);
	}
	makeCode();
</script>
