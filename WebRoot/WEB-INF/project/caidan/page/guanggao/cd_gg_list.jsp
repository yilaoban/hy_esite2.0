<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/qrcode.js"></script> 
<script type="text/javascript">
function delGG(id,name){
		var layerid=layer.confirm('确定将['+name+']删除吗?',{title:"确认删除"},function(){
			$.post("/${oname}/cd-guanggao/delGG.action",{inajax:1,ggid:id},function(data){
			if(data==0){
				layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
			}else{
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
						window.location.reload();
				}); 
			}
			});
		});
}


	function quanxuanthispage(name){
		$('input:checkbox[name="'+name+'"]').each(function () {  
            $(this).attr("checked", true); 
         });
	}
	
	function fanxuan(name){
	 $('input:checkbox[name="'+name+'"]').each(function () {  
            $(this).attr("checked", !$(this).attr("checked")); 
        });
	}
	
	function tfgg(checkbox,type){
		var s = document.getElementsByName(checkbox);
		var ids="";
		for(i= 0;i<s.length;i++){
			if(s[i].checked){
				ids+=s[i].value+";";
			}
		}
		if(ids==""){
			layer.msg('您还没选择要投放的广告!',1,8);
			return;
		}
		var url = '/${oname}/cd-guanggao/tfgg.action';
		$.post(url,{"ids":ids,"type":type},function(data){
				if(data == "N"){
					layer.alert("操作失败,请重试!",0);
				}else{
					layer.alert("操作成功",1);
					window.location.reload();
				}
			});
	}
</script>
<div class="wrap_content">
	<div class="toolbar pb10">
		<div class="left">
			<a href="javascript:quanxuanthispage('usercheck')" class="ml20">全选本页</a> <i class='vline'>|</i> <a href="javascript:fanxuan('usercheck')">反选</a>
		</div>
		<a href="/${oname }/cd-guanggao/addGG.action" class="btn btn-primary">新增广告</a>
		<div class="btn-group">
	   	<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" >投放广告<span class="caret"></span></button>
	   	 <ul class="dropdown-menu">
		    <li><a href="javascript:tfgg('usercheck',0)">首页广告</a></li>
		    <li><a href="javascript:tfgg('usercheck',1)">试手气广告</a></li>
		    <li><a href="javascript:tfgg('usercheck',2)">拼才华广告</a></li>
		  </ul>
		</div>
		
		
	</div>
	
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
			<tr>
				<th></th>
				<th>标题</th>
				<th>图片</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>操作</th>
			</tr>
		<s:iterator value="dto.adGGList" var="a" status="st">
			<tr>
				<td align="center">
					<input type="checkbox" name="usercheck" value="${a.id }" >
				</td>
				<td>${a.title }</td>
				<td>
					<s:if test="#a.img != null && #a.img != ''">
						<img src="${a.img }" height="30" width="30">
					</s:if>
				</td>
				<td>
					<s:date name="starttime" format="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<s:date name="endtime" format="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<a href="/${oname }/cd-guanggao/editGG.action?ggid=${a.id}">编辑</a>
					<a href="javascript:delGG(${a.id},'${a.title }')">删除</a>
				</td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
