<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
<form action="shenhe_picture_sub.action" method="post">
<input type="hidden" value="${dto.picture.id }" name="contentId"/>
<input type="hidden" value="${dto.picture.catid }" name="ccid"/>
<a href="javascript:window.history.back()" class="return">返回</a>
<p>
		分类:
		<s:iterator value="dto.contentcategory" var="c">
			<s:if test='id==dto.picture.catid'><input type="text" value="${c.name }" disabled="disabled"/></s:if>
		</s:iterator>
</p>
<p>
	名称:<input type="text" value="${dto.picture.title }" disabled="disabled"/>
</p>
<p>
		图片:<img id="img" src="${imgDomain }${dto.picture.imgurl }" width="120" height="100">
</p>
<p>
	审核发布:<input type="radio" name="status" value="CMP" checked="checked">通过并发布&nbsp;<input type="radio" name="status" value="INP">审核通过&nbsp;<input type="radio" name="status" value="EDT" >审核不通过
</p>
<p><input type="submit" value="确定"/></p>
</form>
</div>