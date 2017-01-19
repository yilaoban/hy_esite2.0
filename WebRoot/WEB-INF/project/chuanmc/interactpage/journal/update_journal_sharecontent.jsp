<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
	<s:form action="journal_sharecontent" method="post" name="myform" enctype="multipart/form-data" onsubmit="return checkmyfrom();">
		<input type="hidden" name="id" value="${id}">
		<input type="hidden" name="jid" value="${jid}">
		<p><textarea name="dto.journal.sharecontent">${dto.journal.sharecontent}</textarea></p>
		<p>分享图片：
			<s:if test="dto.journal.sharepic != null">
 				<img src="${imgDomain }${dto.journal.sharepic}" width="100" height="80">
 			</s:if>
 			<input type="file" name="sharepic">
 			<input type="hidden" name="sharepicimg" value="${dto.journal.sharepic }">
		</p>
		<input type="submit" value="保存">
	</s:form>
</div>
<s:if test='result != null && result != "" '>
	<s:if test='result == "Y"'>
		<script>
			parent.layer.load(2,2);
			setTimeout('parent.layer.msg("操作成功!",2,1)',2000);
			var i = parent.layer.getFrameIndex();
			//parent.layer.close(i); 
			setTimeout("window.parent.location.reload()",3000);
		</script>
	</s:if>
	<s:else>
		<script>parent.layer.msg('${result}',2,3);</script>
	</s:else>
</s:if>