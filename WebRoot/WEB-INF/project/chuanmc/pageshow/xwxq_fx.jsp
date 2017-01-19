<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	$("title").html("${dto.news.title}");
	$("meta[name='description']").attr("content","${dto.news.shortdesc}");
	title = "${dto.news.title}";
	desc = "${dto.news.shortdesc}";
	desc = desc.replace(/<br\/?>/g,'\r\n'); //br换成\r\n
	desc = desc.replace(/<\/?[^>]*>/g,''); //去除HTML tag
	desc.value = desc.replace(/[ | ]*\n/g,'\n'); //去除行尾空白
	desc = desc.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
	imgUrl = '${imgDomain}${dto.news.simgurl}';
	
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

		// 2.3 监听“分享到QQ”按钮点击、自定义分享内容及分享结果接口
		wx.onMenuShareQQ({
			title : title,
			desc : desc,
			link : link,
			imgUrl : imgUrl,
			success : function(res) {
			},
			cancel : function(res) {
			}
		});

		// 2.4 监听“分享到微博”按钮点击、自定义分享内容及分享结果接口
		wx.onMenuShareWeibo({
			title : title,
			desc : desc,
			link : link,
			imgUrl : imgUrl,
			success : function(res) {
			},
			cancel : function(res) {
			}
		});
	});

	
</script>