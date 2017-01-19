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
     	var img = $('#img').val();
     	if(img == ""){
     		$("#img_").html("请上传图片").css("color", "RED");
     		return false;
     	}
		$("#form1").submit();
	} 
</script>
<a name="maodian"></a>      
<div class="wrap_content">
<form action="add_picture_sub.action" method="post" enctype="multipart/form-data" class="formview jNice" id="form1">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">新增图片</a></li>
	  </ul>
	  <a href="/${oname }/content/content_index.action?ccid=${ccid }" class="return" title="返回"></a>
	</div>

	<span id="errrSpan" style="color:red"><strong style="color:red"><s:fielderror theme="" fieldName="error"/></strong> </span>
 	<dl>
	 	<dt>分类</dt>
		<dd>
			<s:iterator value="dto.contentcategory" var="c">
			<input type="hidden" name="ccid" value="${c.id }"/>
			<input type="text" class="text-medium" value="${c.name }" readonly="readonly"/>
		</s:iterator>
		</dd>
	</dl>
	<dl>
	 	<dt>标题</dt>
		<dd>
			<input type="text" class="text-long" name="title" id="title"/><span id="title_" class="must">*</span>
		</dd>
	</dl>
	<div id="contentTag">
		<script type="text/javascript">
			$("#contentTag").load('/${oname}/page/contentTag.action?ccid=${ccid}');	
		</script>
	</div>
	<dl>
	 	<dt>图片</dt>
		<dd>
			<input type="file" name="img" id="img"/><span id="img_" class="must"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>摘要</dt>
		<dd>
			<script type="text/plain" id="myEditor" name="tag" style="width:100%;height:200px;"></script>
		</dd>
	</dl>
	<dl>
	 	<dt>链接地址</dt>
		<dd>
			<input type="text" class="text-long" name="linkurl" placeholder="地址请以http://开头"/>
		</dd>
	</dl>
	<dl style="display: none">
	 	<dt>提交审核</dt>
		<dd>
			<label class="forradio"><input type="radio" name="status" value="DSH">是</label>
			<label class="forradio"><input type="radio" name="status" value="EDT">下次编辑</label>
			<label class="forradio"><input type="radio" name="status" value="CMP" checked="checked">直接发布</label>
		</dd>
	</dl>
	<dl>
	 	<dt></dt>
		<dd>
			<input type="button" value="确定" class="btn btn-primary" onclick="checkSub()"/>
		</dd>
	</dl>
</form>
</div>