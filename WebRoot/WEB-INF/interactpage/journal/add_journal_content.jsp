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
	<s:form action="journal_content_save" method="post" name="myform" enctype="multipart/form-data" onsubmit="return checkmyfrom();">
		<input type="hidden" name="jid" value="${jid}">
		<p>标题：<input type="text" name="dto.journal.title" id="title" onblur="checkTitle1()" value="${dto.journal.title}"><a name="title_"><span id="title_"></span></a></p>
		<p>小图片：<input type="file" name="simg"></p>
		<p>大图片：<input type="file" name="bimg"></p>
		<p>链接地址：<input type="text" name="dto.journal.url"></p>
		<p>标签：<textarea name="dto.journal.tag"></textarea></p>
		<p>详情：
		<script type="text/plain" id="myEditor" name="dto.journal.content" style="width:100%;height:200px;"></script>
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