<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test='result != null && result != "" '>
	<s:if test='result == "Y"'>
		<script>
			parent.layer.load(2,2);
			setTimeout('parent.layer.msg("操作成功!",2,1)',2000);
			var i = parent.layer.getFrameIndex();
			setTimeout('window.top.location.reload()',4000);
		</script>
	</s:if>
	<s:else>
		<script>parent.layer.msg('${result}',2,3);</script>
	</s:else>
</s:if>
<div id="updateEmailPHtml">
	<form action="update_emailPublish.action"  enctype="multipart/form-data" id="form1" method="post" class="formview jNice">
		<dl>
			<dt>标题</dt>
			<dd>
				<input type="hidden" name="id" value="${id }"/>
				<input id="utitle" type="text" class="text-medium" name="title" value="${dto.emailPeriodical.title }"/>
				<span id="tm_utitle" style="color:red; display:none;" >标题不能为空!</span>
			</dd>
		</dl>
		<dl>
			<dt>链接地址</dt>
			<dd>
				<input id="uurl" type="text" class="text-medium" name="url" value="${dto.emailPeriodical.url }"/>
				<span id="tm_uurl" style="color:red; display:none;" >链接不能为空!</span>
			</dd>
		</dl>
		<dl>
			<dt></dt>
			<dd>
				<input type="submit" value="确定">
				<input type="button" value="取消" onclick="closeFrame()">
			</dd>
		</dl>
	</form>
</div>

<script type="text/javascript">
<!--

$("#utitle").blur(function(){
	if($("#utitle").val() == null || $("#utitle").val() == ""){
		$("#tm_utitle").show();
	}else{
		$("#tm_utitle").hide();
	}
});
$("#uurl").blur(function(){
	if($("#uurl").val() == null || $("#uurl").val() == ""){
		$("#tm_uurl").show();
	}else{
		$("#tm_uurl").hide();
	}
});
//-->
</script>