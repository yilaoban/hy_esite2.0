<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
$(document).ready(function(){
	$("#saveBtn").click(function(){
		var name=$("#name").val();
		if(name==""){
			layer.msg('请填写分类名称！', {icon: 5, time: 2000});
			return ;
		}
		$("#form1").submit();
	});
	
	
});
</script>
<div class="wrap_content left_module">
	<form action="/${oname }/cd-way/saveCatalog.action" id="form1" class="formview jNice" method="post" id="form1">
	<div class="toolbar pb10">
	  	<ul class="c_switch">
			<li class="selected"><a href="#">新增渠道分类</a></li>
		</ul>
		<a href="javascript:window.history.back()" class="return" title="返回"></a>
	</div>
	<div>
		<dl>
		 	<dt>名称</dt>
			<dd>
				<input type="text" name="catalog.name" id="name"/>
			</dd>
		</dl>
		<dl>
		 	<dt>简介</dt>
			<dd>
				<textarea  maxlength="200" name="catalog.hydesc" id="hydesc"></textarea>
			</dd>
		</dl>
	</div>
	<dl>
		<dt></dt>
		<dd>
			<button type="button" class="btn btn-primary" id="saveBtn">保存</button>
		</dd>
	</dl>
	</form>
</div>
