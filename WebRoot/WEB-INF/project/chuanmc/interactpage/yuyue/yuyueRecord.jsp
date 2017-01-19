<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
	<div class="toolbar mt20">
	<form action="/${oname}/interact/yuyueRecord.action" method="post" id="myform">
		状态：<select name="status">
			  <option value ="ALL">全部</option>
			  <option value ="EDT" <s:if test='status == "EDT"'>selected="selected"</s:if>>未确认</option>
			  <option value ="CMP" <s:if test='status == "CMP"'>selected="selected"</s:if>>已确认</option>
		</select>
		预约时间：
		<input id="startTime" type="text" value="<s:date name="startTime" format="yyyy-MM-dd HH:mm:ss"/>" name="starttime" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
	 	至：
		<input id="endTime" type="text" value="<s:date name="endTime" format="yyyy-MM-dd HH:mm:ss"/>" name="endtime" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
		<input type="submit" value="搜索" class="btn btn-info btn-sm">
	</form>
	</div>

	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
			<tr>
				<th>项目</th>
				<th>昵称</th>
				<th>预约服务</th>
				<th>预约人员</th>
				<th>预约时间</th>
				<th>说明</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		<s:iterator value="dto.recordList" var="p">
			<tr>
				<td>${p.catname }</td>
				<td><a href="javascript:editAptDetail('${oname }','${dto.yuyue.aptid }','${p.wxuid }')">${p.nickname }</a></td>
				<td>${p.servicename }</td>
				<td>${p.sername }</td>
				<td>
					<s:date name="yytime" format="yyyy-MM-dd HH:mm:ss" />
				</td>
				<td>
					${p.hydesc }
				</td>
				<td>
					<s:if test='#p.status == "EDT"'>未确认</s:if>
					<s:if test='#p.status == "CMP"'>已确认</s:if>
				</td>
				<td>
					<a href="javascript:editYYRecord(${p.id})">确认</a>
					<a href="javascript:delYYRecord(${p.id},'${p.nickname }')">删除</a>
				</td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
<script type="text/javascript">
	function editYYRecord(id){
		var srcString = '/${oname}/interact/editYYRecord.action?recordid='+id;
		var	title="确认";
		layer.open({
				type : 2,
				area : ['500px','350px'],
				title : '设置',
				content: srcString
			});
	}

function delYYRecord(id,name){
		var layerid=layer.confirm('确定将['+name+']删除吗?',{title:"确认删除"},function(){
			$.post("/${oname}/interact/delYYRecord.action",{inajax:1,recordid:id},function(data){
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

</script>