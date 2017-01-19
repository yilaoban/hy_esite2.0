<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
function delRule(id){
		var layerid=layer.confirm('确定删除吗?',{title:"确认删除"},function(){
			$.post("/${oname}/setting/delRmbRule.action",{inajax:1,ruleid:id},function(data){
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

function czsm(){
	var html=$("#smdiv").html();
	$("#smdiv").empty();
	layer.confirm(html,{"title":"储值说明",end:function(){$("#smdiv").html(html);}}, function(){
		var rule=$("#sm").val();
		$.post("/${oname}/setting/AjUpdateRmbRule.action",{"balanceSet.rmbrule":rule},function(data){
			if(data>0){
				layer.msg('设置成功!', {icon: 6, time: 1500}, function(){
					window.location.reload();
				}); 
			}else{
				layer.msg('设置失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});
}
</script>

<div class="wrap_content">
	<ul class="c_switch">
	    <li class="selected">
	       <a href="/${oname}/setting/rmbRule.action">充值规则</a>
	    </li>
	    <li>
	      <a href="/${oname}/setting/userLevel.action">会员等级</a>
	    </li>
	    <li>
	      <a href="/${oname}/setting/userRmb.action" >用户</a>
	    </li>
        <li>
          <a href="/${oname}/setting/user_print.action">打印模板</a>
        </li>
        <li>
          <a href="/${oname}/setting/user_xf_desc.action">收银系统</a>
        </li>
        <li>
          <a href="/${oname}/setting/user_oc_xf.action">消费积分</a>
        </li>
        
	 </ul>
    <p class="clearfix"></p>   

	<div class="toolbar mt20">
		<div class="toolbar pb10" >
		<a href="javascript:czsm()" class="btn btn-primary">储值说明</a>
		<a href="/${oname }/setting/addRmbRule.action" class="btn btn-primary">新增规则</a>
	</div>
	</div>
	<table width="100%" class="tb_normal" border="1" cellspacing="1" cellpadding="1">
		<thead>
			<tr>
				<th>充值</th>
				<th>赠送积分</th>
				<th>赠送金额</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="ruleList" var="r">
			<tr>
				<td>${r.rmb/100}元</td>
				<td>${r.getjf}积分</td>
				<td>${r.getrmb/100}元</td>
				<td>
					<a href="/${oname }/setting/editRmbRule.action?ruleid=${r.id}">编辑</a>
					<a href="javascript:delRule(${r.id})">删除</a>
				</td>
			</tr>
			</s:iterator>
		</tbody>
	</table>
	<div id="smdiv" style="display: none">
		<textarea id="sm">${balanceSet.rmbrule}</textarea>
	</div>
</div>