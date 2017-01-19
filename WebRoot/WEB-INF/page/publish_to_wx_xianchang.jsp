<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/qrcode.js"></script>
<div class="wrap_content">
		<div class="listlinkcontent">
			<div  class="linkcontentbody">
				<dl class="bindview">
					已绑定微信公众号： ${pdto.wxMp.nick_name }<br/>
				</dl>
			</div>
			<s:if test="pdto.wxPageShowlist.size>0">
				<s:iterator value="pdto.wxPageShowlist" var="p" status="st"> 
					<div class="towxcontent">
						<div class="towxcontent2">
							<div style="float:left;overflow:hidden;"><img <s:if test='#p.pic != ""'>src="${p.pic}"</s:if><s:else>src="/images/error.gif"</s:else> width="200" height="200" /></div>
							<div style="float:left;overflow:hidden;width:50%;">
								<ul>
									<li>分享标题：${p.title }<li>
									<li>分享描述：${p.description }</li>
								</ul>
							</div>
							<div class="towxcontentCode">
								<div id="code${p.id}" linkurl=" ${pageDomain }/${oname}/user/wxshowpage/${p.id}/wxn.html"></div>
							</div>
						</div>
						<div class="towxcontentbutton">
							<span>链接地址：  ${pageDomain }/${oname}/user/wxshow/${p.id}/wxn.html</span>
							<span style="float:right;margin-right:20px;"><a href="javascript:void(0);" onclick="editWxShare(${p.id},${siteid},'${oname}')" class="button">修改设置</a></span>
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
			<s:else>
				<div class="towxcontent">
					<div class="towxcontent2">
						<div style="float:left;overflow:hidden;"><img src="/images/error.gif" width="200" height="200" /></div>
						<div style="float:left;overflow:hidden;">
							<ul>
								<li>分享标题：未设置<li>
								<li>分享描述：未设置</li>
							</ul>
						</div>
						<div class="towxcontentCode">
							<a href="javascript:void(0);" onclick="editWxShare(0,${siteid},'${oname}')" class="button">立即设置</a>
						</div>
					</div>
				</div>
			</s:else>
		</div>
</div>