<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
function yuYueDaySSTime(){
		var srcString = '/${oname}/interact/addYuYueSSTimeToTime.action?catid=${catid }&swtype=${swtype}';
		layer.open({
				type : 2,
				area : ['600px','500px'],
				title : '配置',
				content: srcString
			});
	}
	
	
	function editYuYueSSTimeToTime(timeid,ssid){
		var srcString = '/${oname}/interact/editYuYueSSTimeToTime.action?timeid='+timeid +'&ssid='+ssid + '&catid=${catid }&swtype=${swtype}';
		layer.open({
				type : 2,
				area : ['600px','600px'],
				title : '配置',
				content: srcString
			});
	}
	
	function delYuYueSSTime(timeid,ssid){
		layer.confirm('确定删除？', function(index){
			$.post("/${oname}/interact/delYuYueSSTime.action",{"timeid":timeid,"ssid":ssid},function(data){
				if(data > 0){
					window.parent.location.reload();
				}else{
	            	layer.alert("删除失败!",0);
	            }		
			});
		}); 
	}
</script>
<div class="wrap_content">
	<div class="switch_tab_div pb10">
	<span><a href="/${oname}/interact/yuyueCatalog.action">项目设置</a><i class="gt">&gt;&gt;</i>${dto.catalog.name}</span>
		<p class="clearfix"></p>
	</div>
	<ul class="c_switch">
      <li <s:if test='swtype=="S"'>class="selected"</s:if>><a href="/${oname}/interact/yuyueService.action?catid=${catid }">服务项目</a></li>
	  <li <s:if test='swtype=="R"'>class="selected"</s:if>><a href="/${oname}/interact/yuYueServicer.action?catid=${catid }&swtype=R">提供者</a></li>
      <li <s:if test='swtype=="P"'>class="selected"</s:if>><a href="/${oname}/interact/yuYueSSTime.action?catid=${catid }&swtype=P&weekday=1">预约配置</a></li>
	  <li <s:if test='swtype=="D"'>class="selected"</s:if>><a href="/${oname}/interact/yuyueCatelogRecord.action?catid=${catid}&day=1&swtype=D">当天预约</a></li>
   	  <li <s:if test='swtype=="Y"'>class="selected"</s:if>><a href="/${oname}/interact/yuyueCatelogRecord.action?catid=${catid}&swtype=Y">预约记录</a></li>
    </ul>
	<p class="clearfix"></p>
	<a href="/${oname}/interact/yuYueSSTime.action?catid=${catid }&swtype=P&weekday=1" >按周</a>|<a href="/${oname}/interact/yuYueDaySSTime.action?catid=${catid }&swtype=P" >按日</a>
 	|<a href="/${oname}/interact/yuYueSSTimeToTime.action?catid=${catid }&swtype=P" style="color: red">按时间段</a>
	 	 <div class="toolbar pb10">
			<a href="javascript:void(0);" class="btn btn-primary" onclick="yuYueDaySSTime()" >配置</a>
		</div>
	<table width="100%" class="tb_normal" border="1" cellspacing="1"cellpadding="1">
		<tr>
			<td>
				预约服务
			</td>
			<td>
				提供者
			</td>
			<td>
				时间段
			</td>
			<td>
				操作
			</td>
		</tr>
		<s:iterator value="dto.ssTimeList" var="p">
			<tr>
				<td>
					${p.servicename }
				</td>
				<td>
					${p.sername }
				</td>
				<td>
					<s:date name="starttime" format="yyyy-MM-dd HH:mm:ss"/>~
					<s:date name="endtime" format="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<a href="javascript:void(0);" onclick="editYuYueSSTimeToTime(${p.id},${p.ssid })">编辑</a>
					<a href="javascript:void(0);" onclick="delYuYueSSTime(${p.id},${p.ssid })">删除</a>
				</td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
