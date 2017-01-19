<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function tiquCheck(id,name){
		var layerid=layer.confirm('确定将['+name+']的申请审核通过吗?',{title:"审核"},function(){
			$.post("/${oname}/cd-rmb/tiquCheck.action",{"rmbGet.id":id},function(data){
			  	if(data==0){
					layer.msg('操作失败！请重试……', {icon: 5, time: 2000});
				}else{
					layer.msg('操作中！请稍等……', {icon: 6, time: 1500}, function(){
							window.location.reload();
					}); 
				}
		 	 });
		});		
	}
	
	function tiquCheckFail(id,name){
		var layerid=layer.confirm('确定驳回['+name+']的提现申请吗?',{title:"审核"},function(){
			$.post("/${oname}/cd-rmb/tiquCheckFail.action",{"rmbGet.id":id},function(data){
			  	if(data==0){
					layer.msg('操作失败！请重试……', {icon: 5, time: 2000});
				}else{
					layer.msg('操作中！请稍等……', {icon: 6, time: 1500}, function(){
							window.location.reload();
					}); 
				}
		 	 });
		});		
	}
	
	function xiazai(){
		url="/${oname}/cd-rmb/export.action?"+$('#form1').serialize();
		window.location=url;
	}
</script>
<div class="wrap_content">
	<div class="toolbar pb20">
	<form action="" method="post" id="form1">
	<div class="left">
	查看时间段:
	<input type="text" id="start" name="starttime" value="${starttime }" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'end\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" class="Wdate">
	至
	<input type="text" id="end" name="endtime" value="${endtime }" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'start\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" class="Wdate">
	<input type="submit" value="搜索" class="btn btn-primary btn-sm"/>
	</div>
	<input type="button" value="导出数据" class="btn btn-primary" onclick="xiazai()"/>
	</form>
	</div>
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
			<tr>
				<th>昵称</th>
				<th>金钱</th>
				<th>交易流水号</th>
				<th>创建时间</th>
				<th>说明</th>
				<th>状态</th>
			</tr>
		<s:iterator value="dto.rmbGetList" var="a" status="st">
			<tr>
				<td>
					${a.nickname }
				</td>
				<td>￥${a.rmb/100 }元</td>
				<td>
					${a.mch_billno }
				</td>
				<td>
					<s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<th>${a.errreason }</th>
				<td>
					<s:if test='#a.status == "NTP" '>
						<a href="javascript:void(0);" onclick="tiquCheck(${a.id},'${a.nickname}')">审核通过</a>
						<a href="javascript:void(0);" onclick="tiquCheckFail(${a.id},'${a.nickname}')">审核不通过</a>
					</s:if>
					<s:elseif test='#a.status == "SND"'>
						审核通过待发送
					</s:elseif>
					<s:elseif test='#a.status == "CMP"'>
						已经发放完毕
					</s:elseif>
					<s:elseif test='#a.status == "SNN"'>
						审核不通过
					</s:elseif>
				</td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
