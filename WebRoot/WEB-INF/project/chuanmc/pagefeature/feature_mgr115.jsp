<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/WEB-INF/pagechip/iframe_header.jsp" %>
<body class="iframe_body">
<form  id="myform">
<input type="hidden" name="dto.snl.id" value="${dto.snl.id}" />
<div>
	<p>
	选择分享表单:<select name="dto.snl.shareid" onchange="showuploadrecord()">
	 				<option value="0">-请选择-</option>
	 				<s:iterator value="dto.checklist" var="f">
	 					<option value="${f.id }" <s:if test='dto.snl.shareid==id'>selected="selected"</s:if>>${f.name }</option>
	 				</s:iterator>
	 			</select>
	 </p>
	<p>列表中微博数量:<input type="text" name="dto.snl.size" value="${dto.snl.size }"/></p>
	<p>备注：<input type="text" value="${dto.snl.name}" name="dto.snl.name"/></p>
	<p><input  type="button" id="save115" value="确定"/>&nbsp;<input  type="button" value="取消" onclick="closeFrame()"/></p>
</div>
</form>
<div id="newsData" style="display: none">
</div>
</body>
<script type="text/javascript">
		$("#save115").click(function(){
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
