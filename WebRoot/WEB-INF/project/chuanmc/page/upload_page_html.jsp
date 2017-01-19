<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<form action="htmlSave.action" method="post"  enctype="multipart/form-data">
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
	<input type="button" value="创建页面" onclick="upload_page_html_sub(this.form);"> <input type="button" value="关闭" onclick="closeFrame()">
	<input type="hidden" name="siteid" value="${siteid}">
	<input type="hidden" name="stype" value="${stype}">
	</div>
	<div>
		<p>注：</p>
		<p>1、zip形式上传，内部文件名尽量使用英文</p>
		<p>2、文件夹结构如图：<img src="http://img.huiyee.com/17.png"/></p>
		<p>3、内部仅支持 js/css/image/images/mp文件夹 以及 html文件 </p>
	</div>
</div>
</form>
<script type="text/javascript">
function upload_page_html_sub(form){
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
