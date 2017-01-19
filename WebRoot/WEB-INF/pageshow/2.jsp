<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:wb="http://open.weibo.com/wb">
	<head>
		<title>产品系列</title>
		<s:if test='userAgent =="C"'>
			<script src="http://tjs.sjs.sinajs.cn/open/thirdpart/js/frame/appclient.js" charset="utf-8"></script>
		</s:if>
		<s:else>
			<script src="http://tjs.sjs.sinajs.cn/open/thirdpart/js/pageapp/mobile/jsapi.js" charset="utf-8"></script>
		</s:else>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="" />
		<meta http-equiv="description" content="" />
		<link href="/css/esite.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="/js/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="/js/esite.js"></script>
		<script type="text/javascript" src="/js/jquery.popImage.js"></script>
		<script type="text/javascript" src="/js/jcarousellite.min.js"></script>
	</head>
	<body>
		<div class="imgblock" id="video">
			<embed src="${result['1'].flist[0].videourl}" allowFullScreen="true" quality="high" width="872" height="413" align="middle" allowScriptAccess="always" type="application/x-shockwave-flash" wmode="transparent"></embed>
		</div>
		<div class="imgblock">
			<div style="position:relative;padding:0 27px;text-align:center;margin-top:30px;">
				<a href="javascript:void(0);" class="prev2"></a>
				<a href="javascript:void(0);" class="next2"></a>
				<div class="jCarousel">
				    <ul>
				    	<s:iterator value='result["1"].flist' var="p">
				        <li><a href="javascript:void(0);" onclick="changeVideo('${p.videourl}');"><img src="${imgDomain }${p.picurl }" width="183" height="115" /><span class="shade">${p.title }</span></a></li>
						</s:iterator>
				    </ul>
				</div>
			</div>
		</div>
		<div class="imgblock" style="margin-top: 30px;">
			<div>
				<ul class="lst">
					<s:iterator value='result["2"].prolist' var="prolist" status="proliststatus">
						<li class="tabs <s:if test='#proliststatus.first'>active</s:if>">
						<a href="javascript:void(0);" onclick="showTab(${proliststatus.index+1 })">${prolist.title }</a> <%--showTab()里面的数字从1开始 --%>
						</li>
					</s:iterator>
					<p style="clear: both"></p>
				</ul>
			</div>
			<div style="height: 12px; background: #000"></div>
			<div
				style="border-width: 0 1px 1px 1px; border-style: solid; border-color: #ccc">
				<s:iterator value='result["2"].prolist' var="prolist" status="proliststatus">
					<div id="tab${proliststatus.index+1 }" class="tab_content" <s:if test='!#proliststatus.first'>style="display:none;"</s:if>>
						<s:if test='#prolist.content != ""'><p style="padding-bottom: 20px; line-height: 24px; margin: 0px auto; text-indent: 30px; width: 750px; font-size: 14px;">${prolist.content }</p></s:if>
						<ul class="<s:if test='#proliststatus.index == 0 || #proliststatus.index == 2'>kst</s:if><s:else>mst</s:else>">
							<s:iterator value='result["2"].product[#proliststatus.index]' var="p" status="listatus">
								<li <s:if test='(#proliststatus.index == 0 || #proliststatus.index == 2) && #listatus.count > 8'>style="display:none"</s:if><s:elseif test='#proliststatus.index != 2 && #listatus.count > 9'>style="display:none"</s:elseif>>
									<a href="${imgDomain }${p.product.bimgurl }" rel="gallery"  class="image_gall"><img src="" data-original="${imgDomain }${p.product.simgurl }" <s:if test='#proliststatus.index == 0 || #proliststatus.index == 2'>width="200px" height="300px"</s:if><s:else>width="255px" height="255px"</s:else>/></a>
									<p class="share">
										<a href="javascript:void(0);" onclick="zan(${pageid },103,${p.id},this)"><img src="/images/test1/zan.png" /><span id="lab_zan_${p.id }">(${p.zantotal })</span></a>
										<a target="_blank" href="http://service.weibo.com/share/share.php?title=SELECTED+2014%B4%BA%BC%BE%CF%B5%C1%D0%CE%A7%C8%C6%D7%C5+%A1%B0%B8%B4%B9%C5%BB%B7%C7%F2%C2%C3%D0%D0%A1%B1%B5%C4%D6%F7%CC%E2%D5%B9%BF%AA%A3%AC%D5%FB%CC%E5%B8%F8%C8%CB%D2%D4%D3%C5%D1%C5%A1%A2%C7%E1%D3%AF%B5%C4%D7%BF%D4%BD%D6%CA%B8%D0%A3%AC%D6%D8%D0%C2%CC%BD%CB%F7%C9%DD%BB%AA%E8%AD%E8%B2%B5%C420%CA%C0%BC%CD30%C4%EA%B4%FA%A1%A3&appkey=Eiexx&pic=${imgDomain }${p.product.simgurl }&ralateUid=2012583242&language=zh_cn&url=http%3A%2F%2Fweibo.com%2Fp%2F1006062012583242%2Fapp901702703"><img src="/images/test1/wb.png" /><span>分享</span></a>
										<input type="hidden" value="${p.id }" name="zantotal"/>
									</p>
								</li>
							</s:iterator>
							<p style="clear: both"></p>
						</ul>
						<s:if test='#proliststatus.index == 0 || #proliststatus.index == 2'>
							<p style="text-align:center;"><a class="t_prev" href="javascript:void(0);" onclick="fanye(8,'p',this)">上一页</a><a class="t_next" href="javascript:void(0);" onclick="fanye(8,'n',this)">下一页</a></p>
						</s:if>
						<s:else>
							<p style="text-align:center;"><a class="t_prev" href="javascript:void(0);" onclick="fanye(9,'p',this)">上一页</a><a class="t_next" href="javascript:void(0);" onclick="fanye(9,'n',this)">下一页</a></p>
						</s:else>
					</div>
				</s:iterator>
			</div>
		</div>
		<div class="support">
			技术支持：<a target="_blank" href="http://www.huiyee.com/">会易科技</a>
		</div>
	<script>
		$(function(){
		    $(".jCarousel").jCarouselLite({
		        btnNext: ".next2",
		        btnPrev: ".prev2",
		        visible: 4,
		        circular: true,
				scroll: 1
		    });
			$("a.image_gall").popImage();
		});
		
		$(document).ready(function(){
			lazyload("tab1");
		});
	</script>
	</body>
</html>
