<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
<div class="switch_tab_div pb10">
	<span><a href="pageconfig_new.action?siteid=${siteid}&stype=P">移动站点</a> <i class="gt">&gt;&gt;</i> 资源列表</span>
	<p class="clearfix"></p>
</div>
<div>
<div class="toolbar pb10">
		<input value="上传资源" type="button" class="btn btn-primary" onclick="addZjsource(${siteid})" />
</div>
<table width="100%" cellspacing="1" cellpadding="1" border="1" class="tb_normal">
	<tr>
		<td>标题</td>
		<td>操作</td>
	</tr>
	<s:iterator value="map" id="entry">
		<tr id="ss_${value.id }">
			<td>${value.title }</td>
			<td><a href="javascript:void(0);" onclick="editSource(${value.id})">编辑</a><i class="split">|</i><a href="javascript:void(0)" onclick="del_Source(${value.id})">删除</a></td>
		</tr>
	</s:iterator>
</table>
</div>
</div>
<script type="text/javascript">
	function del_Source(sourceid){
		$.post("find_source_page.action",{"sourceid":sourceid},function(data){
			if(data > 0){
				layer.msg('有'+data+'个页面正在使用此资源,无法删除！', {icon: 2});
			}else{
				layer.confirm('确定删除么？', {icon: 3}, function(index){
					$.post("del_source.action",{"sourceid":sourceid},function(data){
						if(data == 1){
							$("#ss_"+sourceid).remove();
						}else{
							layer.msg('删除失败！', {icon: 2});
						}
					})
				    layer.close(index);
				});
				
			}
		});
	}
</script>