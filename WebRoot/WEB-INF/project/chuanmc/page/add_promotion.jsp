<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="wrap_content">
<form action="savePageAddress.action" method="post" id="myform" name="myform">
<input type="hidden" value="${pageid }" name="pageid" id="pageid">
<p>渠道：<input type="text" name="name" id="name"></p>	
<p>助记符：<input type="text" name="source" id="source" maxlength="3" onkeyup="value=value.replace(/[^a-zA-Z]/g,'')"  onafterpaste="value=value.replace(/[^a-zA-Z]/g,'')"><input type="button" value="检查" onclick="checkSource()"></p>
<span>(*助记符只能为3个字符的英文字母)</span>
<p><input type="hidden" name="weixin" id="flag" value="N"><s:if test='page.subweixin=="Y"'><input type="checkbox" onclick="changeValue(this,'flag')">是否用于微信(若要此操作必须先绑定到微信)</s:if><s:else><input type="checkbox" onclick="changeValue(this,'flag')" disabled="disabled">是否用于微信(若要此操作必须先绑定到微信)</s:else></p>
<p><input type="button" value="提交" onclick="checkform()"></p>
</form>
</div>
<s:if test='result != null && result != "" '>
	<s:if test='result == "Y"'>
		<script>
			parent.layer.load(2,2);
			setTimeout('parent.layer.msg("操作成功!",2,1)',2000);
			var i = parent.layer.getFrameIndex();
			setTimeout("window.parent.location.reload()",3000);
		</script>
	</s:if>
	<s:else>
		<script>parent.layer.msg('${result}',2,3);</script>
	</s:else>
</s:if>
<script type="text/javascript">
	 function changeValue(arg,req){
		if(arg.checked==false){
			$("#"+req).val("N");
		}else{
			$("#"+req).val("Y");
		}
}
</script>