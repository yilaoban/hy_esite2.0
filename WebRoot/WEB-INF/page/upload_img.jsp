<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<form action="imgSave.action" method="post"  enctype="multipart/form-data">
<div>
	<div><font color="red">${errmsg}</font></div>
	<input type="hidden" value="ZIP" name="uploadType">
	<div id="tab1" class="stabs">	
		上传文件:
		<div id="div1">
			<input id="file" name='file' type='file' />
		</div>
	</div>
	<div>
	<input type="button" value="确认上传" onclick="upload_img(this.form);"> <input type="button" value="关闭" onclick="closeFrame()">
	<input type="hidden" name="ccid" value="${ccid}">
	<input type="hidden" name="typeid" value="${typeid}">
	</div>
	<div>
		<p>注：zip形式上传，内部文件名尽量使用英文</p>
	</div>
</div>
</form>
<script type="text/javascript">
function upload_img(form){
	if(form.file.value==""){
		top.layer.alert("还没有选择文件!");
		return;
	}
	form.submit();
}
</script>

<s:if test='result != null && result != "" '>
	<s:if test='result == "Y"'>
		<script>
			parent.layer.load(2,2);
			setTimeout('parent.layer.msg("创建成功!",2,1)',2000);
			var i = parent.layer.getFrameIndex();
			//parent.layer.close(i); 
			setTimeout("window.parent.location.reload()",3000);
		</script>
	</s:if>
	<s:else>
		<script>parent.layer.msg('${errmsg}',2,3);</script>
	</s:else>
</s:if>
