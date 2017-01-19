<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
	<div class="toolbar pb10" style="text-align:left;">
	<form action="/${oname}/interact/leaderIndex.action" method="post" id="myform" class="formview jNice">
		潜客状态：
		<select name="status">
			  <option value ="ALL">全部</option>
			  <option value ="LED" <s:if test='status == "LED"'>selected="selected"</s:if>>潜客</option>
			  <option value ="NOL" <s:if test='status == "NOL"'>selected="selected"</s:if>>有效潜客</option>
		</select>
		最近加入时间：
		<input id="startTime" type="text" value="<s:date name="startTime" format="yyyy-MM-dd HH:mm:ss"/>" name="startTime" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
	 	至
		<input id="endTime" type="text" value="<s:date name="endTime" format="yyyy-MM-dd HH:mm:ss"/>" name="endTime" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
		<input type="submit" class="btn btn-info btn-sm" value="搜索">
	</form>
	</div>
	<table width="100%" cellspacing="1" cellpadding="1" border="1" class="tb_normal">
		<thead>
			<tr>
				<th>昵称</th>
				<th>性别</th>
				<th>电话</th>
				<th>区域</th>
				<th>加入次数</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.leaderList" var="p">
				<tr align="center">
					<td>
						${p.nickname}
					</td>
					<td>
						<s:if test="#p.gender ==0">未知</s:if>
						<s:if test="#p.gender ==1">男</s:if>
						<s:if test="#p.gender ==2">女</s:if>
					</td>
					<td>
						${p.telphone }
					</td>
					<td>
						${p.area}
					</td>
					<td>
						${p.joinnum}
					</td>
					<td>
						<a href="javascript:void(0);" onclick="editLeader(${p.id})">编辑</a>|
						<a href="javascript:void(0);" onclick="showSource(${p.hyuid})">来源</a>|
						<a href="javascript:void(0);" onclick="delLeader(${p.id})">删除</a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
<div id="wideModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog" style="width:400px;">
    <div class="modal-content">
    </div>
  </div>
</div>
<script type="text/javascript">

	function editLeader(id) {
		var srcString = '/${oname}/interact/editLeader.action?id='+ id;
		$('#wideModal').removeData("bs.modal");
		$('#wideModal').modal({
			remote:srcString
		});
	}
	
	function showSource(hyuid) {
		var srcString = '/${oname}/interact/showSource.action?hyuid='+ hyuid;
		layer.open({
			type : 2,
			area : [ '600px', '420px' ],
			title : [ '查看来源', true ],
			content : srcString
		});
	}
	
	function delLeader(id){
		var srcString = '/${oname}/interact/delLeader.action';
		var layerid=layer.confirm('确定删除?',{icon: 2},function(){
			$.post(srcString,{"id":id},function(data){
				if(data == "Y"){
					layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
						window.parent.location.reload();
					}); 
				}else{
					layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
				}
			});
		});
	}
</script>