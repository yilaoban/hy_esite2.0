<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
//实例化编辑器
var um = UE.getEditor('myEditor');

</script>
<div class="wrap_content">
<a href="javascript:window.history.back()" class="return">返回</a>
<p>
		分类:
		<s:iterator value="dto.contentcategory" var="c">
			<s:if test='id==dto.cn.catid'><input type="text" value="${c.name }" disabled="disabled"/></s:if>
		</s:iterator>
</p>
<p>
	名称:<input type="text" name="name" value="${dto.cn.title }" disabled="disabled"/>
</p>
<p>
	内容:	
	<script type="text/plain" id="myEditor" name="content" style="width:100%;height:200px;">${dto.cn.content }</script>
</p>
<p>
</p>
</div>