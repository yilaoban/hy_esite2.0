<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/WEB-INF/pagechip/iframe_header.jsp" %>
<body class="iframe_body">
<form id="myform">
<div class="frame_content">
<p>上传描述:<input type="text" name="dto.userUpload.content" class="text-medium" value="${dto.userUpload.content}"/></p>
<p>中图宽度：<input type="text" name="dto.userUpload.mwidth" class="text-medium" value="${dto.userUpload.mwidth }" /></p>
<p>中图高度：<input type="text" name="dto.userUpload.mheight" class="text-medium" value="${dto.userUpload.mheight }" /></p>
<p>小图宽度：<input type="text" name="dto.userUpload.swidth" class="text-medium" value="${dto.userUpload.swidth }" /></p>
<p>小图高度：<input type="text" name="dto.userUpload.sheight" class="text-medium" value="${dto.userUpload.sheight }" /></p>
<input type="hidden" name="dto.userUpload.id" value="${dto.userUpload.id}">
<input type="button" class="btn btn-primary" value="保存" id="save105"/>
<input type="button" class="btn" value="取消" onclick="closeFrame()"/>
</div>
</form>
<script type="text/javascript">
		$("#save105").click(function(){
			$.ajax({
		        cache: true,
		        type: "POST",
		        url:"/${oname}/page/config_sub.action?featureid=${featureid}",
		        data:$('#myform').serialize(),
		        async: false,
		        success: function(data) {
		        	if(data == "Y"){
						layer.load(2);
						setTimeout('layer.closeAll("loading");parent.layer.msg("操作成功!",{icon: 6, time: 1500},function(){closeFrame()})',2000);
		        	}else{
						parent.layer.msg("保存失败！请重试！",{icon: 6, time: 1500},3);
		        	}
		        }
		    });
		});
</script>
</body>
</html>