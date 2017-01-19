<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
function addJifenRule(){
	var entity = '${entity}';
	var srcString = '/' + entity + '/bbs/addJifenRule.action';
	$('#wideModal').removeData("bs.modal");
	$('#wideModal').modal({
		remote:srcString
	});
}

function editJfRule(id){
	var entity = '${entity}';
	var srcString = '/' + entity + '/bbs/editJfRule.action?ruleid=' + id;
	$('#wideModal1').removeData("bs.modal");
	$('#wideModal1').modal({
		remote:srcString
	});
}

function deleteJfRule(id){
	var entity = '${entity}';
	var srcString = '/' + entity + '/bbs/deleteJfRule.action';
	var layerid=layer.confirm('确定删除?',function(){
		$.post(srcString,{"ruleid":id},function(data){
			if(data == "Y"){
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.location.reload();
				}); 
			}else{
				layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});
}

</script>
<div class="wrap_content">
	<div class="toolbar pb10">
		<ul class="c_switch">
			<li <s:if test="type==0">class="selected"</s:if>><a href="/${sessionScope.account.owner.entity}/bbs/user_index.action" >积分规则设置</a></li>
			<li <s:if test="type==1">class="selected"</s:if>><a href="/${sessionScope.account.owner.entity}/bbs/userLevel.action?type=1">会员等级分配</a></li>
			<li <s:if test="type==2">class="selected"</s:if>><a href="/${sessionScope.account.owner.entity}/bbs/userInfo.action?type=2">会员查看</a></li>
		</ul>
		<a href="javascript:void(0)" class="button" onclick="addJifenRule()">添加积分规则</a>
	</div>
	<table width="100%" cellspacing="1" cellpadding="1" border="1"
		class="tb_normal">
		<thead>
			<tr>
				<th>
					动作名称
				</th>
				<th>
					增加积分
				</th>
				<th>
					操作
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.bbsJfRuleList" var="r">
				<tr align="center">
					<td>
						<s:if test="#r.action == 1">发表主题</s:if>
						<s:elseif test="#r.action == 2">发表回复</s:elseif>
						<s:elseif test="#r.action == 3">顶</s:elseif>
						<s:elseif test="#r.action == 4">踩</s:elseif>
						<s:elseif test="#r.action == 5">主题被置顶</s:elseif>
					</td>
					<td>
					${r.jifen }
					</td>
					<td>
						<a href="javascript:void(0)" onclick="editJfRule(${r.id })">修改</a>
						<i class="split">|<i class="split">
						<a href="javascript:void(0)" onclick="deleteJfRule(${r.id })">删除</a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</div>
<div id="wideModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog" style="width:400px;">
    <div class="modal-content">
    </div>
  </div>
</div>
<div id="wideModal1" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog" style="width:400px;">
    <div class="modal-content">
    </div>
  </div>
</div>