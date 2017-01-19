<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
function addLevelRule(){
	var entity = '${entity}';
	var srcString = '/' + entity + '/bbs/addLevelRule.action';
	$('#wideModal').removeData("bs.modal");
	$('#wideModal').modal({
		remote:srcString
	});
}

function editJfLevel(id){
	var entity = '${entity}';
	var srcString = '/' + entity + '/bbs/editJfLevel.action?levelid=' + id;
	$('#wideModal1').removeData("bs.modal");
	$('#wideModal1').modal({
		remote:srcString
	});
}

function deleteJfLevel(id){
	var entity = '${entity}';
	var srcString = '/' + entity + '/bbs/deleteJfLevel.action';
	var layerid=layer.confirm('确定删除?',function(){
		$.post(srcString,{"levelid":id},function(data){
			if(data == "Y"){
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.location.reload();
				}); 
			}else{
				layer.msg("操作失败!请重试……", {icon: 5, time: 2000});
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
		<a href="javascript:void(0)" class="button" onclick="addLevelRule()">添加等级分配</a>
	</div>
	<table width="100%" cellspacing="1" cellpadding="1" border="1"
		class="tb_normal">
		<thead>
			<tr>
				<th>
					等级名称
				</th>
				<th>
					所需积分
				</th>
				<th>
					操作
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.bbsJfLevelList" var="r" status="st">
				<tr align="center">
					<td>
						${r.level_name }
					</td>
					<td>
						${r.require_jf }
					</td>
					<td>
						<a href="javascript:void(0)" onclick="editJfLevel(${r.id })">修改</a>
						<s:if test="#st.last">
							<i class="split">|<i class="split">
							<a href="javascript:void(0)" onclick="deleteJfLevel(${r.id })">删除</a>
						</s:if>
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