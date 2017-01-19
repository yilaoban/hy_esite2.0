<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
<input type="hidden" value="${dto.cp.id }" name="contentId"/>
<a href="javascript:window.history.back()" class="return">返回</a>
<p>
		分类:
		<s:iterator value="dto.contentcategory" var="c">
			<s:if test='id==dto.picture.catid'><input type="text" value="${c.name }" disabled="disabled"/></s:if>
		</s:iterator>
</p>
<p>
	名称:<input type="text" name="name" value="${dto.picture.title }" disabled="disabled"/>
</p>
<p>
	图片:<img id="simg" src="${imgDomain }${dto.picture.imgurl }" width="120" height="100">
</p>
<p>
</p>
</div>