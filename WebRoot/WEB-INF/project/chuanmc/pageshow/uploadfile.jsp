<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<script type="text/javascript" src="/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<script>
	function ajaxFileUpload(imgDomain){
		var result =/\.[^\.]+/.exec($("#upd").val());
		if(result != ".jpg" && result != ".JPG" && result != ".jpeg" && result != ".JPEG" && result != ".bmp" && result != ".BMP" && result != ".png" && result != ".PNG"){
			alert("文件格式不支持！（仅支持jpg/jpeg/bmp/png等格式）");
			return;
		}
		$.ajaxFileUpload({
				url:'/user/shareUploadPic.action?pageid=774&fid=64&featureid=123', 
				secureuri:false,
				fileElementId:"upd",
				dataType: 'json',
				success: function (data, status){
					if(status == "success"){
						$("#img").attr("src",imgDomain+data.picUrl);
					}
				},
				error: function (data, status, e){
					alert(e);
				}
			})
		return false;
	}
</script>
</head>
<body>
	<div>
 		<input type="file" id="upd" name="pic" onchange="ajaxFileUpload('${imgDomain }')">
 		<img id="img" height="100" src="">
	</div>
</body>
</html>

