<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
function yuYueDaySSTime(){
		var dateday = $('#dateday').val();
		var srcString = '/${oname}/interact/addYuYueDaySSTime.action?catid=${catid }&swtype=${swtype}&dateday='+dateday;
		layer.open({
				type : 2,
				area : ['600px','500px'],
				title : '配置',
				content: srcString
			});
	}
	
	function selectRiqi(){
			var dateday = $('#dateday').val();
			window.location.href = "/${oname}/interact/yuYueDaySSTime.action?catid=${catid }&swtype=P&dateday=" + dateday;
	}
	
	function editYuYueSSTime(timeid,ssid){
	    var dateday = $('#dateday').val();
		var srcString = '/${oname}/interact/editYuYueSSTime.action?timeid='+timeid +'&ssid='+ssid + '&catid=${catid }&swtype=${swtype}&dateday='+dateday;
		layer.open({
				type : 2,
				area : ['500px','500px'],
				title : '配置',
				content: srcString
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
	<a href="/${oname}/interact/yuYueSSTime.action?catid=${catid }&swtype=P&weekday=1" >按周</a>|<a href="/${oname}/interact/yuYueDaySSTime.action?catid=${catid }&swtype=P" style="color: red">按日</a>
 	|<a href="/${oname}/interact/yuYueSSTimeToTime.action?catid=${catid }&swtype=P">按时间段</a>
 		<ul class="c_switch">
		    <input id="dateday" type="text" value="${dto.dateday}" name="yuYueSSTime.dateday" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyyMMdd'})" readonly="readonly" />
	   		 <a href="javascript:void(0);" class="button" onclick="selectRiqi()" >查看</a>
	    </ul>
	 	 <div class="toolbar pb10">
			<a href="javascript:void(0);" class="btn btn-primary" onclick="yuYueDaySSTime()" >配置</a>
		</div>
	<table width="100%" class="tb_normal" border="1" cellspacing="1"cellpadding="1">
			<tr>
				<td>
					时间段
				</td>
				<td>
					${dto.dateday}
				</td>
			</tr>
		<s:iterator begin="0" end="23" status="st">
			<tr>
				<td width="10%">${st.index}</td>
				<td width="90%">
					<s:iterator value="dto.ssTimeList" var="p">
						<s:if test="#p.ehoure == #st.index"><a href="javascript:void(0);" onclick="editYuYueSSTime(${p.id},${p.ssid })">${p.servicename }(${sername })</a>
							<s:if test="#p.sminute < 10">
								${p.shoure }:0${p.sminute }~
							</s:if>
							<s:else>
								${p.shoure }:${p.sminute }~
							</s:else>
							<s:if test="#p.eminute < 10">
								${p.ehoure }:0${p.eminute}
							</s:if>
							<s:else>
								${p.ehoure }:${p.eminute}
							</s:else>
						&nbsp&nbsp
						</s:if>
					</s:iterator>
				</td>
			</tr>
		</s:iterator> 
	</table>
</div>
