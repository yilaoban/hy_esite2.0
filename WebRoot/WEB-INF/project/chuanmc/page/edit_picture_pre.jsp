<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	//实例化编辑器

	 $(document).ready(function() {  
           $("#category").change();
			var um = UE.getEditor('myEditor');
        }); 
        
     function checkSub(){
     	var title = $.trim($('#title').val());
     	if(title == ""){
     		$("#title_").html("请输入标题").css("color", "RED");
     		window.location.hash="maodian";
			return false;
     	}
		$("#form1").submit();
	} 
</script>
<a name="maodian"></a>    
<div  class="wrap_content">
<form action="edit_picture_sub.action" method="post" enctype="multipart/form-data" class="formview jNice" id="form1">
<input type="hidden" value="${dto.picture.id }" name="contentId"/>
<input type="hidden" value="${dto.picture.catid }" name="ccid"/>

  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">编辑图片</a></li>
	  </ul>
	  <a href="javascript:window.history.back()" class="return" title="返回"></a>
	</div>
	<span id="errrSpan" style="color:red"><strong style="color:red"><s:fielderror theme="" fieldName="error"/></strong> </span>

	<dl>
	 	<dt>分类</dt>
		<dd>
			<s:iterator value="dto.ccList" var="c" status="s">
				<s:iterator value="c" var="cc">
					<s:if test='use=="Y"'><input type="text" disabled="disabled" value="${cc.name }" class="text-long"/></s:if>
				</s:iterator>
				<s:if test="!#s.last">-</s:if>
			</s:iterator>
		</dd>
	</dl>
	<div id="contentTag">
		<script type="text/javascript">
			$("#contentTag").load('/${oname}/page/contentTag.action?ccid=${dto.picture.catid}&pid=${dto.picture.id}');	
		</script>
	</div>
	<dl>
	 	<dt>名称</dt>
		<dd>
			<input type="text" name="title" class="text-long" value="${dto.picture.title }" id="title"/><span id="title_" class="must">*</span>
		</dd>
	</dl>
	<dl>
	 	<dt>图片</dt>
		<dd>
			<span id="himg" style="display: none"><input type="file" name="img" id="fsimg" /></span>
			<img id="img" src="${imgDomain }${dto.picture.imgurl }" width="120" height="100">
			<input type="hidden" name="imgurl" value="${dto.picture.imgurl }">
		</dd>
		<dd>
			<a id="imgtext" href='javascript:imgchange("img")'>我要上传</a>
		</dd>
	</dl>
	<dl>
	 	<dt>摘要</dt>
		<dd>
			<script type="text/plain" id="myEditor" name="tag" style="width:100%;height:200px;">${dto.picture.tag }</script>
		</dd>
	</dl>
	<dl>
	 	<dt>链接地址</dt>
		<dd>
			<input type="text" class="text-long" name="linkurl" value="${dto.picture.linkurl}"/>
		</dd>
	</dl>
	<dl style="display: none">
	 	<dt>提交审核</dt>
		<dd>
			<input type="radio" name="status" value="DSH" <s:if test='dto.picture.status=="DSH"'>checked="checked"</s:if>>是&nbsp;
			<input type="radio" name="status" value="EDT" <s:if test='dto.picture.status=="EDT"'>checked="checked"</s:if>>下次编辑&nbsp;
			<input type="radio" name="status" value="CMP" <s:if test='dto.picture.status=="CMP"'>checked="checked"</s:if>>直接发布
		</dd>
	</dl>
	<dl>
	 	<dt></dt>
		<dd>
			<input type="button" value="确定" onclick="checkSub()" class="btn btn-primary"/>
		</dd>
	</dl>
</form>
</div>