<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	 $(document).ready(function() {  
           $("#category").change();
        }); 
</script>
<div class="wrap_content">
<form action="add_video_sub.action" method="post" enctype="multipart/form-data" class="formview jNice">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">新增视频</a></li>
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
			<input type="text" class="text-long" name="title"/><span class="must">*</span>
			<span id="errrSpan" style="color:red"><strong style="color:red"><s:fielderror theme="" fieldName="error1"/></strong> </span>
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
			<input type="file" name="img"/>
		</dd>
	</dl>
	<dl>
	 	<dt>视频flash地址</dt>
		<dd>
			<input type="text" class="text-long" name="videourl"/><span class="must">*</span>
			<span id="errrSpan" style="color:red"><strong style="color:red"><s:fielderror theme="" fieldName="error2"/></strong> </span>
		</dd>
	</dl>
	<dl>
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
			<input type="submit" class="btn btn-primary" value="确定"/>
		</dd>
	</dl>
	
</form>
</div>