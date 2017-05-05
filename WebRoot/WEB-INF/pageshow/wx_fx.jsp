<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript">
	var title = $("title").html();
	var desc = $("meta[name='description']").attr("content");
	var link = 'http://'+window.location.host + '/${oname}/user/wxshow/' + pageid + '/fx/kv.html';
	var imgUrl = '';
	var o = $('body img');
	if ($(o[0]).attr("src") != "") {
		imgUrl = $(o[0]).attr("src");
	}
	var pageid = '${pageid}';
	var spageid = 0;
         title = title || "www.huiyee.com";
		var source = '${source}';
		if (source == '') {
			source = 'wxn';
		}
		var hysource = source;
		var patt_p = new RegExp("-p-\\d*").exec(hysource);
		if (patt_p) {
			hysource = hysource.replace(patt_p, "-p-" + pageid);
		} else {
			hysource = hysource + "-p-" + pageid;
		}
		var patt_s = new RegExp("-s-\\d*").exec(hysource);
		if (patt_s) {
			hysource = hysource.replace(patt_s, "-s-${sessionScope.visitUser.wxUser.id}");
		} else {
			hysource = hysource + "-s-${sessionScope.visitUser.wxUser.id}";
		}
		var kv = '${kv}';
		if (kv == '') {
			kv = 'kv';
		}
		link ='http://'+ window.location.host + '/${oname}/user/wxshow/' + pageid + '/' + hysource + '/' + kv + '.html';
	$(document).ready(function() {
		$.ajax({
			url : "/${oname}/weixin/jssdk.action",
			type : "get",
			data : {
				"url" : window.location.href.split('#')[0],
				"pageid" : '${pageid}'
			},
			success : function(data) {
				if (data) {
					if (data.wps) {
						title = data.wps.title;
						desc = data.wps.description;
						imgUrl = data.wps.pic;
						pageid = data.wps.pageid;
						spageid = data.wps.spageid;
					}
					wx.config({
						debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
						appId : data.appid, // 必填，公众号的唯一标识
						timestamp : data.timestamp, // 必填，生成签名的时间戳
						nonceStr : data.noncestr, // 必填，生成签名的随机串
						signature : data.signature,// 必填，签名，见附录1
						jsApiList : [ // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
						'onMenuShareAppMessage', //
						'onMenuShareTimeline'//
						]
					});
				}
			}
		});
	});


	wx.ready(function() {
		wx.onMenuShareAppMessage({
			title : title, // 分享标题
			desc : desc, // 分享描述
			link : link, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
			imgUrl : imgUrl, // 分享图标
			type : 'link', // 分享类型,music、video或link，不填默认为link
			dataUrl : '', // 如果type是music或video，则要提供数据链接，默认为空
			success : function() {
				// 用户确认分享后执行的回调函数
				wxs();
				if (spageid > 0) {
					window.location.href = '/${oname}/user/wxshow/' + spageid + '/' + source + '/' + kv + '.html';
				}
			},
			cancel : function() {
				// 用户取消分享后执行的回调函数
			}
		});
		wx.onMenuShareAppMessage({
			title : title, // 分享标题
			desc : desc, // 分享描述
			link : link, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
			imgUrl : imgUrl, // 分享图标
			type : 'link', // 分享类型,music、video或link，不填默认为link
			dataUrl : '', // 如果type是music或video，则要提供数据链接，默认为空
			success : function() {
				// 用户确认分享后执行的回调函数
				wxs();
				if (spageid > 0) {
					window.location.href = '/${oname}/user/wxshow/' + spageid + '/' + source + '/' + kv + '.html';
				}
			},
			cancel : function() {
				// 用户取消分享后执行的回调函数
			}
		});

	});

	function wxs() {
		$.ajax({
			url : "/${oname}/user/wxs.action?aoid=" + pageid,
			type : "get",
			success : function(res) {
				//console.log(res);
			}
		});
	}
	
	function wxshare(title, desc, img, link) {
		if (title)
			title = title;
		if (desc)
			desc = desc;
		if (img)
			imgUrl = img;
		if (link)
			link = link;
		wx.ready(function() {

			// 2.1 监听“分享给朋友”，按钮点击、自定义分享内容及分享结果接口
			wx.onMenuShareAppMessage({
				title : title,
				desc : desc,
				link : link,
				imgUrl : imgUrl,
				success : function(res) {
					wxs();
				},
				cancel : function(res) {
				}
			});

			// 2.2 监听“分享到朋友圈”按钮点击、自定义分享内容及分享结果接口
			wx.onMenuShareTimeline({
				title : title,
				link : link,
				imgUrl : imgUrl,
				success : function(res) {
					wxs();
				},
				cancel : function(res) {
				}
			});

		});
	}

	function hideMenu() {
		wx.ready(function() {
			wx.hideAllNonBaseMenuItem();
		});
	}

	
</script>