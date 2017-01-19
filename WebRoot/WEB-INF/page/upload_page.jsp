<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>


<form action="/page/fsave.action" method="post"  enctype="multipart/form-data">
<div>
	<div><font color="red">${errmsg}</font></div>
	<p>页面名称:<input type="text" id="tname" name="pagename" class="text-medium" placeholder="必填"><img src="/images/help.jpg" height="20" width="20" onclick="layer.tips('上传的文件类型为zip格式,后缀为.zip.系统支持两种规范:一层目录结构和二层目录结构',this,{maxWidth:150,guide:1,time: 6});" title="文件格式说明" style="cursor:pointer"></p>
	<input type="hidden" value="ZIP" name="uploadType">
	<div id="tab1" class="stabs"<s:if test='uploadType==null || uploadType=="" || uploadType=="ZIP"'>style="display:block;"</s:if><s:else> style="display:none;"</s:else>>	
			上传文件:
				<div id="div1">
					<input id="file" name='file' type='file' />
				</div>
	</div>
	<div>
	<input type="button" value="创建页面" onclick="upload_page_sub(this.form);"> <input type="button" value="关闭" onclick="closeFrame()">
	<input type="hidden" name="siteid" value="${siteid}">
	<input type="hidden" name="stype" value="${stype}">
	</div>
</div>
</form>
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
