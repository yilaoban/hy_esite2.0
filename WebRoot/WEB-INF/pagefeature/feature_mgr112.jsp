<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/WEB-INF/pagechip/iframe_header.jsp" %>
<body class="iframe_body">
<form id="myfrom">
<input type="hidden" name="dto.share.id" value="${dto.share.id}" />
<div>
	<p>分享描述：<input type="text" value="${dto.share.name}" name="dto.share.name"/> (此处描述适用于页面分享按钮的文字表述)</p>
	<p>授权转向链接：<input type="text" value="${dto.share.soauthurl}" name="dto.share.soauthurl">(必填，否则授权功能无效)</p>
	<p>授权取消转向链接：<input type="text" value="${dto.share.coauthurl}" name="dto.share.coauthurl">(可以不填，默认回原始链接)</p>
	<p> <input type="button" id="save112" value="确定"/><input  type="button" value="取消" onclick="closeFrame()"/></p>
</div>
</form>
<div id="newsData" style="display: none">
</div>
<script type="text/javascript">
		$("#save112").click(function(){
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
