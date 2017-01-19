<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/qrcode.js"></script>
<script type="text/javascript" >
function showShare(){
	$("#newshare").show();
}
</script>
	<div class="switch_tab_div pb10">
	<ul class="nav nav-tabs">
		<li><a href="javascript:pageconfig('/${oname}/page/pageconfig_iframe.action?siteid=${siteid }')">页面配置</a></li>
		<li class="active"><a href="javascript:wxpublish('/${oname}/page/publish_to_wx_iframe.action?siteid=${siteid }')">微信分享设置</a></li>
		<li><a href="javascript:void(0);" onclick="publish_sina('${oname }',${siteid })">发布到微博</a>	</li>
	</ul>
	</div>
		<div class="listlinkcontent">
			<div  class="towxcontent">
				<div class="towxcontent2">
					已绑定微信公众号： ${pdto.wxMp.nick_name }<br/>
				</div>
			</div>
			<div class="towxcontent">
				<div class="towxcontent2">
					微网站所有页面均可单独分享，若有特殊设置，<a href="javascript:void(0);" onclick="showShare()" class="button btn-primary">点击这里</a>
				</div>
			</div>
			<div id="newshare" class="towxcontent" style="display: none">
				<div class="towxcontent2">
					<div style="float:left;overflow:hidden;"><img src="/images/error.gif" width="200" height="200" /></div>
					<div style="float:left;overflow:hidden;width:50%;">
						<ul>
							<li>分享标题：未设置<li>
							<li>分享描述：未设置</li>
						</ul>
					</div>
					<div class="towxcontentCode">
						<a href="javascript:void(0);" onclick="editWxShareWebSite(0,${siteid},'${oname}')" class="button btn-primary">立即设置</a>
					</div>
				</div>
			</div>
			<s:if test="pdto.wxPageShowlist.size>0">
				<s:iterator value="pdto.wxPageShowlist" var="p" status="st"> 
					<div class="towxcontent">
						<div class="towxcontent2">
							<div style="float:left;overflow:hidden;"><img <s:if test='#p.pic != ""'>src="${p.pic}"</s:if><s:else>src="/images/error.gif"</s:else> width="200" height="200" /></div>
							<div style="float:left;overflow:hidden;;width:50%;">
								<ul>
									<li>分享标题：${p.title }<li>
									<li>分享描述：${p.description }</li>
								</ul>
							</div>
							<div class="towxcontentCode">
								<div id="code${p.id}" linkurl=" ${pageDomain }/${oname}/user/wxshow/${p.pageid}/wxn.html"></div>
							</div>
						</div>
						<div class="towxcontentbutton">
							<span>链接地址：   ${pageDomain }/${oname}/user/wxshow/${p.pageid}/wxn.html</span>
							<span style="float:right;margin-right:20px;"><a href="javascript:void(0);" onclick="editWxShareWebSite(${p.id},${siteid},'${oname}')" class="button">修改</a> <a href="javascript:void(0);" onclick="deleteWxShare(${p.id},${siteid},'${p.title }','${oname }')" class="button">删除</a></span>
						</div>
					</div>
					<script type="text/javascript">
			 			var qrcode = new QRCode(document.getElementById("code${p.id }"), {
							width : 150,
							height : 150
						});
						var str = $('#code${p.id}').attr("linkurl");
						qrcode.makeCode(str);
					</script>
					
				</s:iterator>
			</s:if>
		</div>