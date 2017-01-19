<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function cssub(){
		var title=$("#title").val();
		if(title!=""){
			return true;
		}else{
			alert("未填写标题.");
			return false;
		}
	}
</script>
<a name="maodian"></a> 
<div  class="wrap_content">
<div class="wrap_content left_module">
  	<div class="toolbar pb10">
  	  <ul class="c_switch">
	  <li class="selected"><a href="">添加传播内容</a></li>
	  </ul>
	  <a href="csContent.action?id=${id }&mid=${mid }" class="return" title="返回"></a>
	</div>
<form action="csContentSub.action" method="post" enctype="multipart/form-data" class="jNice" id="form1" onsubmit="return cssub()">
<input type="hidden" name="id" value="${id }"/>
<p>
 	名称：<input type="text"  class="text-medium" name="name" id="title"/><span id="title_" class="must">*</span>
</p>
<p>
	图片：<input type="file" name="img"/>
</p>
<p>
	开始时间:<input id="starttime" type="text" name="starttime" class="Wdate"	onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
</p>
<p>
	署名：<input type="text" class="text-medium" name="sign">
</p>
<p>
	内容：<textarea rows="8" cols="20"  name="content"></textarea>
</p>
<p>
	<input type="submit" value="确定"/>
</p>
</form>
</div>