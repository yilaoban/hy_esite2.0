<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
<form action="edit_product_sub.action" method="post" enctype="multipart/form-data">
<input type="hidden" value="${dto.cp.id }" name="contentId"/>
<a href="javascript:window.history.back()" class="return">返回</a>
<p>
		分类:
		<s:iterator value="dto.contentcategory" var="c">
			<s:if test='id==dto.cp.catid'><input type="text" value="${c.name }" disabled="disabled"/></s:if>
		</s:iterator>
</p>
<p>
	名称:<input type="text" name="name" value="${dto.cp.name }" disabled="disabled"/>
</p>
<p>
		小图片：<img id="simg" src="${imgDomain }${dto.cp.simgurl }" width="80" height="80">
</p>
<p>
		大图片：<img id="bimg" src="${imgDomain }${dto.cp.bimgurl }" width="160" height="160">
</p>
<p>
	链接地址:<input type="text" name="linkurl" value="${dto.cp.linkurl }" disabled="disabled"/>
</p>
<p>
	价格:<input type="text" name="price" value="${dto.cp.price }" disabled="disabled">
</p>
<p>
	标签:<textarea rows="8" cols="20" name="detail" disabled="disabled">${dto.cp.tag }</textarea>
</p>
<p>
	详情:<textarea rows="8" cols="20" name="detail" disabled="disabled">${dto.cp.detail }</textarea>
</p>
<p>
</p>
</form>
</div>