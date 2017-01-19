<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
<form action="shenhe_product_sub.action" method="post">
<input type="hidden" value="${dto.cp.id }" name="contentId"/>
<input type="hidden" value="${dto.cp.catid }" name="ccid">
<a href="javascript:window.history.back()" class="return">返回</a>
<p>
		分类:
		<s:iterator value="dto.contentcategory" var="c">
			<s:if test='id==dto.cp.catid'><input type="text" value="${c.name }" disabled="disabled"/></s:if>
		</s:iterator>
</p>
<p>
	名称:<input type="text" value="${dto.cp.name }" disabled="disabled"/>
</p>
<p>
		<img id="simg" src="${imgDomain }${dto.cp.simgurl }" width="120" height="100">
</p>
<p>
		<img id="bimg" src="${imgDomain }${dto.cp.bimgurl }" width="120" height="100">
</p>
<p>
	链接地址:<input type="text" value="${dto.cp.linkurl }" disabled="disabled"/>
</p>
<p>
	原价:<input type="text" value="${dto.cp.price }" disabled="disabled">
</p>
<p>
	促销价:<input type="text" value="${dto.cp.salesprice }" disabled="disabled">
</p>
<p>
	摘要:<textarea rows="8" cols="20" disabled="disabled">${dto.cp.tag }</textarea>
</p>
<p>
	审核发布:<input type="radio" name="status" value="CMP" checked="checked">通过并发布&nbsp;<input type="radio" name="status" value="INP">审核通过&nbsp;<input type="radio" name="status" value="EDT" >审核不通过
</p>
<p><input type="submit" value="确定"/></p>
</form>
</div>