<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div>
	<font color="red">备注:嘉宾姓名与嘉宾微信昵称之间以英文分号";"分隔。多条数据以换行分隔;<br>注意:如果有图片或特殊字符请将其删除</font><br>
	<!-- <font color="red">方法2备注:支持Excel上传,格式为:序号,嘉宾姓名,嘉宾微信昵称</font> -->
	<form action="xcfileSub.action" method="post" enctype="multipart/form-data">
		<input type="hidden" name="xcid" value="${xcid }"/>
		方法1:<textarea rows="" cols="" placeholder="姓名,微信昵称" name="content"></textarea><br>
		<!-- 方法2:<input type="file" name="userfile"/> -->
		<input type="submit" value="确定"/>
	</form>
</div>
<s:if test='result != null && result != "" '>
	<s:if test='result == "Y"'>
		<script>
			parent.layer.load(2,2);
			setTimeout('parent.layer.msg("操作成功!",2,1)',2000);
			setTimeout('top.window.location.reload();',3000);
		</script>
	</s:if>
	<s:else>
		<script>parent.layer.msg('${result}',2,3);</script>
	</s:else>
</s:if>
