<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="wrap_content">
	<input type="hidden" value="${wxurl}" id="wxurl">
	<input type="hidden" value="${pageid }" id="pageid">
	${nickname }
	<div id="qrcode" style="width:100px; height:100px; margin-top:15px;"></div>
</div>
<script type="text/javascript" src="/js/qrcode.js"></script> 
<script type="text/javascript">
	 var qrcode = new QRCode(document.getElementById("qrcode"), {
					width : 150,
					height : 150
				});

				function makeCode () {
					var wxurl=$("#wxurl").val();
					var elText = wxurl;
					qrcode.makeCode(elText);
				}

				makeCode();

</script>