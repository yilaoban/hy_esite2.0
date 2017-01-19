<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/WEB-INF/pagechip/iframe_header.jsp" %>

<body class="iframe_body">
<form  id="myform">
	<input type="hidden" name="dto.fid" value="${fid}" />
	  <s:iterator value="dto.csList" var="cs">
           <label class="forradio"><input type="radio" value="${cs.id}" name="dto.csid" <s:if test="dto.csid == #cs.id"> checked="checked" </s:if> /> ${cs.title} </label><br />
      </s:iterator>
	<input type="button" id="save145" value="确定"/>
	<input type="button" value="取消" onclick="closeFrame()"/>
</form>
<script type="text/javascript">
		$("#save145").click(function(){
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
