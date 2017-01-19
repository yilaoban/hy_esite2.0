<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/WEB-INF/pagechip/iframe_header.jsp" %>

<body class="iframe_body">
<form  id="myform">
	<input type="hidden" name="dto.fid" value="${fid}" />
	请选择需要调研的选项：<br/>
	  <s:iterator value="dto.researchList" var="apt">
           <label class="forradio"><input type="radio" value="${apt.id}" name="dto.researchid" <s:if test="dto.researchid == #apt.id"> checked="checked" </s:if> /> ${apt.title} </label><br />
      </s:iterator>
	<input type="submit" value="确定" id="save124"/>
	<input type="button" value="取消" onclick="closeFrame()"/>
</form>
<script type="text/javascript">
		$("#save124").click(function(){
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
