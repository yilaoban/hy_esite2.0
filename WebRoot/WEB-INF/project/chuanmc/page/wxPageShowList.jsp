<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/qrcode.js"></script> 
<script type="text/javascript">
</script>
<div class="wrap_content">
<div class="toolbar pb10">
	<a href="/${oname}/page/addWxPageShow.action?siteid=${siteid }" class="button">新增分享设置</a>
</div>
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<s:iterator value="pdto.wxPageShowlist" var="p" status="st"> 
		<tr>
			<td>
				<img src="${p.pic}" height="67" width="67" />
				${p.title }${p.description }
			</td>
			<td>
				<div id="code${p.id}" linkurl=" ${pageDomain }/${oname}/user/wxshowpage/${p.id}/wxn.html"></div>
			</td>
		</tr>
		<tr>
			<td>链接地址： ${pageDomain }/${oname}/user/wxshowpage/${p.id}/wxn.html</td>
			<td><a href="/${oname}/page/editWxPageShow.action?shareid=${p.id }&siteid=${siteid }" class="button">修改分享设置</a></td>
		</tr>
		<script type="text/javascript">
 			var qrcode = new QRCode(document.getElementById("code${p.id }"), {
				width : 150,
				height : 150
			});
			var str = $('#code${p.id}').attr("linkurl");
			qrcode.makeCode(str);
		</script>
		
	</s:iterator>
</table>
</div>