<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
//实例化编辑器
var um = UE.getEditor('myEditor');
function checkTitle1(){
		if($("#title").val()==""){
			$("#title_").html("请输入标题").css("color", "RED");
			return false;
		}else{
			$("#title_").html("");
			return true;
		}
	}

 function checkmyfrom(){
	 	if($("#title").val()==""){
			$("#title_").html("请输入标题").css("color", "RED");
			window.location.hash="title_";
			return false;
		}
 }
</script>  
<div class="wrap_content">
	<s:form action="update_journal_content" method="post" name="myform" enctype="multipart/form-data" onsubmit="return checkmyfrom();">
		<input type="hidden" name="id" value="${id}">
		<input type="hidden" name="jid" value="${jid}">
		<p>标题：<input type="text" name="dto.journal.title" value="${dto.journal.title}" id="title" onblur="checkTitle1()"><span id="title_"></span></p>
		<p>小图片：
			<s:if test="dto.journal.simg != null">
 				<img src="${imgDomain }${dto.journal.simg}" width="80" height="80">
 			</s:if>
 			<input type="file" name="simg">
 			<input type="hidden" name="oldsimg" value="${dto.journal.simg }">
		</p>
		<p>大图片：
			<s:if test="dto.journal.bimg != null">
 				<img src="${imgDomain }${dto.journal.bimg}" width="160" height="160">
 			</s:if>
 			<input type="file" name="bimg">
 			<input type="hidden" name="oldbimg" value="${dto.journal.bimg }">
		</p>
		<p>链接地址：<input type="text" name="dto.journal.url" value="${dto.journal.url}"></p>
		<p>标签：<textarea name="dto.journal.tag">${dto.journal.tag}</textarea></p>
		<p>详情：
			<script type="text/plain" id="myEditor" name="dto.journal.content" style="width:100%;height:200px;">${dto.journal.content }</script>
		</p>
		<input type="submit" value="提交">
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