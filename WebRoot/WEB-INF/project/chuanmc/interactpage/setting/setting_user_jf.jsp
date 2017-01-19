<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function doSearchUser(){
		$('#myform').submit();
	}
	
	
	function showJfDetail(hyuid) {
		var url = "/${oname}/setting/jfUserDetail.action?hyuid=" + hyuid;
		layer.open({
			type : 2,
			area : [ '800px', '550px' ],
			title : '用户积分详情',
			content : url
		});
	}
	
	function addUserJf(hyuid){
		var url = "/${oname}/setting/addUserJf.action";
		layer.prompt({
			title : '充值'
			},function(value){
				if(value==null||value==''){
					   return false;
				}
				$.post(url,{"hyuid":hyuid,"jf":value},function(data){
					if(data > 0){
						layer.msg('充值成功!', {icon: 6, time: 1500}, function(){
							window.parent.location.reload();
						}); 
					}
				});	             
	    });
	}
	
</script>

<div class="wrap_content">
 <ul class="c_switch">
	    <li>
	       <a href="/${oname}/setting/jfSetting.action">积分规则</a>
	    </li>
	    <li class="selected">
	      <a href="/${oname}/setting/jfUser.action">用户积分</a>
	    </li>
	    <li>
	      <a href="/${oname}/setting/jfCheckinRecord.action" >签到记录</a>
	    </li>
	 </ul>
    <p class="clearfix"></p>   

<div class="toolbar mt20">
	<form action="/${oname}/setting/jfUser.action" method="post" id="myform">
		用户昵称:<input type="text" name="nickname" value="${nickname }">
	 	时间段：
		<input id="startTime" type="text" value="<s:date name="startTime" format="yyyy-MM-dd HH:mm:ss"/>" name="startStr" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
	 	至：
		<input id="endTime" type="text" value="<s:date name="endTime" format="yyyy-MM-dd HH:mm:ss"/>" name="endStr" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
		<select name="type">
			 <option value="0" <s:if test='type == 0'>selected="selected"</s:if>>选择类型</option>
			 <option value="1" <s:if test='type == 1'>selected="selected"</s:if>>增加积分用户</option>
			 <option value="2" <s:if test='type == 2'>selected="selected"</s:if>>消耗积分用户</option>
			 <option value="3" <s:if test='type == 3'>selected="selected"</s:if>>未增加积分用户</option>
			 <option value="4" <s:if test='type == 4'>selected="selected"</s:if>>未消耗积分用户</option>
		</select>
		<input type="button" onclick="doSearchUser()" value="搜索" class="btn btn-info btn-sm">
	</form>
</div>
	<table width="100%" class="tb_normal" border="1" cellspacing="1" cellpadding="1">
		<thead>
			<tr>
				<th>昵称</th>
				<th>可用积分</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.userBalanceList" var="r">
			<tr>
				<td>${r.nickname }</td>
				<td>
					<a href="javascript:void(0)" onclick="showJfDetail(${r.hyuid })">${r.total - r.used}</a>
				</td>
				<td>
					<a href="javascript:void(0)" onclick="addUserJf(${r.hyuid })">充值</a>
				</td>
			</tr>
			</s:iterator>
		</tbody>
	</table>
		<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>

</div>