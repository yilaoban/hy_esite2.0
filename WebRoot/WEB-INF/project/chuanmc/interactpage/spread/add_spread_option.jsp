<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="wrap_content left_module">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">添加内容</a></li>
	  <a href="/${oname }/interact/spread_option.action?id=${id }&mid=10010" class="return" title="返回"></a>
	  </ul>
	</div>
   <form action="save_spread_option.action" method="post" name="myform" enctype="multipart/form-data" class="formview jNice">
   <input type="hidden" name="sp.spreadid" value="${id }">
   <dl>
		<dt>标题</dt>
		<dd>
			<input type="text" name="sp.title" id="title" >
		</dd>
	</dl>
	<dl>
		<dt>文本内容</dt>
		<dd>
			<textarea name="sp.content" id="content" ></textarea>
		</dd>
	</dl>
	<dl>
		<dt>图片上传</dt>
		<dd>
			<input type="file" name="img"><input type="hidden" name="sp.img">
		</dd>
	</dl>
	<dl>
		<dt></dt>
		<dd>
			<input type="button" value="确定" onclick="checkbutton()">
			<input type="reset" value="重置">	
		</dd>
	</dl>
  </form>
</div>
<script type="text/javascript">
	function checkbutton(){
		if($("#title").val()==""){
			layer.alert("请填写标题！");
		}
		if($("#content").val()==""){
			layer.alert("请填写内容！");
		}else{
			document.myform.submit();
		}
	}
</script>
