<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function create_contenttype(type){
	
	var html="<div>名称：<input class='text-medium' id='typename'/></div>";
	var title="";
	if(type=="N"){
		title="新增新闻分类";
	}else if(type=="T"){
		title="新增产品分类";
	}else if(type=="P"){
		title="新增图片分类";
	}else if(type=="V"){
		title="新增视频分类";
	}else{
		title="新增自定义表单分类";
	}
	layer.prompt({
		title:title, 
	},function(value){
				if(type=="F"){
					$.post("/content/addcfType.action",{"typeName":value},function(data){
					if(data == "Y"){
						layer.msg('创建中！请稍等……', {icon: 6, time: 1500}, function(){
							window.location.reload();
						}); 
					}else{
						layer.msg('创建失败！请重试……', {icon: 5, time: 2000});
					}
					});
				}else{
						$.post("/content/add_content_type.action",{"ccname":value,"cctype":type},function(data){
							if(data == 1){
								layer.msg('创建中！请稍等……', {icon: 6, time: 1500}, function(){
									window.location.reload();
								}); 
							}else{
								layer.msg('创建失败！请重试……', {icon: 5, time: 2000});
							}
						});
				}
	});
	
}

	function formSub(){
			$.ajax({
		        cache: true,
		        type: "POST",
		        url:"config_type_sub.action",
		        data:$('#form1').serialize(),// 你的formid
		        async: false,
		        error: function(request) {
		            layer.msg('Connection error', {icon: 5, time: 2000});
		        },
		        success: function(data) {
		            if(data=="Y"){
		            	layer.msg('操作成功！请稍等……', {icon: 6, time: 1500}, function(){
							window.parent.location.reload();
						}); 
		            }else{
		            	layer.msg('至少选择一个分类', {icon: 5, time: 2000});
		            }
		        }
		    });
		}
	
	function rename(obj){
		$(obj).children().each(function(){
			$(this).toggle();
		});
		$(obj).removeAttr("onclick");
	}
	
	function ireset(obj){
		var i=$(obj).val();
		if(i!=""){
			$(obj).next().text(i);
		}
		$(obj).hide();
		$(obj).next().show();
		$(obj).parent().attr("onclick","rename(this)")
	}
</script>
<div>
	<div class="toolbar pb10">
	<input type="button" class="btn btn-primary" onclick="create_contenttype('T')" value="新增产品"/>
	<input type="button" class="btn btn-primary" onclick="create_contenttype('N')" value="新增新闻"/>
	<input type="button" class="btn btn-primary" onclick="create_contenttype('P')" value="新增图片"/>
	<!-- <input type="button" class="btn btn-primary" onclick="create_contenttype('V')" value="新增视频"/> -->
	<input type="button" class="btn btn-primary" onclick="create_contenttype('F')" value="新增自定义表单"/>
	</div>
	<form id="form1">
		<table width="100%" class="tb_normal" border="1" cellspacing="1" cellpadding="1">
			<tbody>
				<tr>
					<td>名称</td>
					<td>类别</td>
					<td>是否显示</td>
					<td>操作</td>
				</tr>
			<s:iterator value="dto.typeList" var="c" status="st">
				<tr>
					<td>
					<span onclick="rename(this)">
						<s:if test="#c.id<=4">
							<input type="text" id="abcse" style="display: none" readonly="readonly" placeholder="${c.name }(系统分类不可改名)" onblur="ireset(this)"/><span>${c.name }</span>
							<input type="hidden" name="subList[.index }].name" value="${c.name }"/>
						</s:if>
						<s:else>
							<input type="text" style="display: none" name="subList[${st.index }].name" placeholder="${c.name }" onblur="ireset(this)"/><span>${c.name }</span>
						</s:else>
					</span>
					</td>
					<td>
						<s:if test='#c.type=="T"'>产品</s:if><s:if test='#c.type=="P"'>图片</s:if><s:if test='#c.type=="N"'>新闻</s:if><s:if test='#c.type=="V"'>视频</s:if><s:if test='#c.type=="F"'>自定义表单</s:if>
					</td>
					<td>
					<input type="hidden" name="subList[${st.index }].id" value="${c.id }">
					<input type="checkbox" name="subList[${st.index }].status" value="CMP" <s:if test='status=="CMP"'>checked="checked"</s:if>/>
					</td>
					<td>
							<a href="javascript:void(0);" onclick="del_contenttype('${c.id }','${oname }')"/>删除</a>
					</td>
				</tr>
			</s:iterator>
			</tbody>
			</table>
		</div>		
		<div class="mt10 tac" >
			<input type="button" class="btn btn-primary" value="保存" onclick="formSub()"/>
		</div>
	</form>
</div>
