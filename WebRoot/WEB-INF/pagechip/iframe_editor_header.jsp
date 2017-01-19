<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>页面编辑器-${dto.page.name}</title>
		<meta name="description" content="${dto.page.bgJson.description}" />  
		<meta name="keywords" content="${dto.page.bgJson.keywords}" />
		<link rel="shortcut icon"  href="/favicon.ico" type="image/x-icon"/>
		<link href="/css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="/js/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="/js/functions.js"></script>
		<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
		
		<link rel="stylesheet" href="/js/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="/css/animation.css" type="text/css"/>
		<script type="text/javascript" src="/js/bootstrap/js/bootstrap.min.js"></script> 
		<link rel="stylesheet" href="/js/font-awesome/font-awesome.min.css" />
		<script type="text/javascript" src="/js/bootbox.min.js"></script>
		<script type="text/javascript" src='/js/My97DatePicker/WdatePicker.js'></script>
		
		<script type="text/javascript" src="/js/jquery-ui.js"></script>
		
		<script type="text/javascript" src="/js/card.js"></script>
		<script type="text/javascript" src="/js/block.js"></script>
		<link href="/js/slick/slick.css" rel="stylesheet" type="text/css" />
		<script src="/js/slick/slick.min.js" type="text/javascript"></script>
		<link rel="stylesheet" href="/js/mCustomScrollbar/jquery.mCustomScrollbar.css">
		<script src="/js/mCustomScrollbar/jquery.mCustomScrollbar.concat.min.js"></script>
		<script type="text/javascript" src="/js/hudong/zajindan/tabbedContent.js"></script>
		
		<script type="text/javascript" src="/js/kindeditor/kindeditor.js"></script>
		<script type="text/javascript" charset="utf-8" src="/js/kindeditor/lang/zh_CN.js"></script>
		<script type="text/javascript" src="/js/pano2vr_player.js"></script>
		<link href="/diyUpload/css/diyUpload.css" rel="stylesheet" type="text/css" />
		<link href="/diyUpload/css/webuploader.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="/diyUpload/js/webuploader.js"></script>
		<script type="text/javascript" src="/diyUpload/js/diyUpload.js"></script>
		<script type="text/javascript" src="/js/cb/jquery.hDialog.min.js"></script> 
		<script type="text/javascript" src="/js/jQueryContextMenu/jquery.contextmenu.js"></script>
		<script type="text/javascript" src="/js/swiper/swipe.js"></script>
		<link href="/js/jQueryContextMenu/jquery.contextmenu.css" rel="stylesheet" type="text/css" />
		<link href="https://res.wx.qq.com/open/libs/weui/0.4.0/weui.css" rel="stylesheet"/>
		<link href="/js/jquery-weui/css/jquery-weui.css" rel="stylesheet"/>
	</head>