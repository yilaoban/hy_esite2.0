<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="wrap_content left_module">
   <div class="toolbar pb10">
  <a href="/${oname }/interact/spread_option.action?id=${sp.spreadid }&mid=10010" class="return" title="返回"></a>
   <ul class="c_switch">
	  <li class="selected"><a href="">编辑</a></li>
	  </ul>
	</div>
   <form action="update_spread_option.action" method="post" name="myform" enctype="multipart/form-data" class="formview jNice">
   <input type="hidden" name="sp.spreadid" value="${spreadid }">
   <input type="hidden" name="spreadid" value="${sp.spreadid }">
   <dl>
		<dt>标题</dt>
		<dd>
			<input type="text" name="sp.title" value="${sp.title }" id="title">
		</dd>
	</dl>
	<dl>
		<dt>文本内容</dt>
		<dd>
			<textarea name="sp.content" id="content">${sp.content }</textarea>
		</dd>
	</dl>
	<dl>
		<dt>图片上传</dt>
		<dd>
			<s:if test='sp.pic==""'><img src="/images/error.gif" width="100" height="80"><input type="file" name="img"><input type="hidden" value="${sp.pic }" name="sp.img"></s:if><s:else><img src="${imgDomain }${sp.pic }" width="100" height="80"><input type="file" name="img"><input type="hidden" value="${sp.pic }" name="sp.img"></s:else>
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
			return false;
		}
		if($("#content").val()==""){
			layer.alert("请填写内容！");
			return false;
		}else{
			document.myform.submit();
		}
	}
</script>
