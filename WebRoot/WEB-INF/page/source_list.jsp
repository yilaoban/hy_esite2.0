<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
	<div class="toolbar pb10">
		<ul class="c_switch">
			<li class="selected"><a href="#">站点导航</a></li>
		</ul>
	  <a href="javascript:addDH();" class="btn btn-primary">新增导航</a>
	</div>
	<div class="template-list clearfix">
		<s:if test="dto.list.size()>0">
        	<s:iterator value="dto.list" var="t" status="st">
				<div class="iphone-frame" style="width:200px;">
			   	<div class="rel" id="${t.id }">
			      <img class="template-pic" src="${t.vm.img }" style="width:200px;height:200px;"/>
			      <div class="template-txt">${t.title }</div>
				  <div class="template-btn">
			 	 <a href="javascript:void(0);" onclick="editOS(${t.id})"><span class="use" >编辑</span></a>
			 	 <a href="javascript:void(0);" onclick="removeOS(${t.id},'${t.pageUsed }')"><span class="pre remove">删除</span></a>
				  </div>
			      </div>
			    </div>
			</s:iterator>
	  </s:if>
	</div>
</div>
<script type="text/javascript">
function addDH(){
	layer.open({
	    type: 2,
	    title: '新增导航',
	    shadeClose: true,
	    shade: 0.8,
	    area: ['50%', '90%'],
	    content: '/${oname}/page/add_owner_source.action'
	}); 
}
$(".iphone-frame").hover(
 function(){
    $(this).addClass("hover");
  },
 function(){
 	$(this).removeClass("hover");
  }
 );
 
 function editOS(id){
	 layer.open({
	    type: 2,
	    title: '新增导航',
	    shadeClose: true,
	    shade: 0.8,
	    area: ['70%', '90%'],
	    content: '/${oname}/page/edit_owner_source.action?id='+id
	}); 
 }
function removeOS(id,count){
	var title="确定要删除此导航吗？";
	if(count>0){
		title="此导航正在被使用,确定要删除吗?";
	}
	 layer.confirm(title, {
		    btn: ['删除','暂时不'] //按钮
		}, function(index){
		    $.post("/${oname}/page/del_owner_source.action",{"id":id},function(data){
		    	if(data>0){
			    	layer.msg('删除成功！', {icon: 1, time: 1500}, function(){
						window.location.reload();
					}); 
		    	}else{
		    		layer.msg('删除失败,请重试!', {time: 1000,icon: 5});
		    	}
		    });
		});
 }
</script>