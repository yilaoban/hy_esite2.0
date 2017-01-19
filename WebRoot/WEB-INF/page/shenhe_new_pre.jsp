<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	//实例化编辑器
	var um = UE.getEditor('myEditor');

	$(function(){
		$("#radio1").click(function(){
			$("#p1").show();
			$("#p2").hide();
		})
		$("#radio2").click(function(){
			$("#p2").show();
			$("#p1").hide();
		})
	})
</script>
<div class="wrap_content">
<form action="shenhe_new_sub.action" id="form1">
<input type="hidden" name="contentId" value="${dto.cn.id }"/>
<input type="hidden" name="ccid" value="${dto.cn.catid }"/>
<a href="javascript:window.history.back()" class="return">返回</a>
<p>
		分类:
		<s:iterator value="dto.contentcategory" var="c">
			<s:if test='id==dto.cn.catid'><input type="text" value="${c.name }" disabled="disabled"/></s:if>
		</s:iterator>
</p>
<p>
	标题:<input type="text" value="${dto.cn.title }" disabled="disabled"/>
</p>
<p>
	摘要：<textarea  name="shortdesc"  readonly="readonly">${dto.cn.shortdesc}</textarea>
</p>
<p>
	小图片：<img id="simg" src="${imgDomain }${dto.cn.simgurl }" width="80" height="80">
</p>
<p>
	大图片：<img id="bimg" src="${imgDomain }${dto.cn.bimgurl }" width="160" height="160">
</p>
<p>
	<input type="radio" id="radio1" name="islink" <s:if test='dto.cn.islink=="Y"'>checked="checked"</s:if>>链接地址<input type="radio" id="radio2" name="islink" onclick="changevalue('N')" <s:if test='dto.cn.islink=="N"'>checked="checked"</s:if>>内容
</p>
<p id="p1" <s:if test='dto.cn.islink=="N"'>style="display:none"</s:if>>
	<input type="text" class="text-long" id="linkurl" name="linkurl" value="${dto.cn.linkurl}" />
</p>
<p id="p2" <s:if test='dto.cn.islink=="Y"'>style="display:none"</s:if>>
	<script type="text/plain" id="myEditor" name="content" style="width:100%;height:200px;">${dto.cn.content }</script>
</p>
<p>
	审核发布：<input type="radio" name="status" value="CMP" checked="checked">通过并发布&nbsp;<input type="radio" name="status" value="INP">审核通过&nbsp;<input type="radio" name="status" value="EDT" >审核不通过
</p>
<p>
	<input type="submit" value="确定"/>
</p>
</form>
</div>