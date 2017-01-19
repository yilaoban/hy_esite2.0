<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
$(document).ready(function() {  
        var hdanalyse = new Highcharts.Chart({
					        chart: {
					            plotBackgroundColor: null,
					            plotBorderWidth: null,
					            plotShadow: false,
					            renderTo: 'hdpie'  
					        },
					        title: {
					            text: '互动访客关注度分析'
					        },
					        series: [{ type: 'pie',
                                      name: '人数',
                            		data: [
                                          ['已关注='+${dto.hfansY},  ${dto.hfansY}],
                                          ['未关注='+${dto.hfansN}, ${dto.hfansN} ],
                                     ]
                                   }]
					    });
})
</script>
<div class="wrap_content">
<div class="switch_tab_div pb10">
	<span><a href="/page/sitegroupList.action">${dto.sitegroup.groupname}</a> <i class="gt">&gt;&gt;</i>  用户信息<i class="gt">&gt;&gt;</i>互动信息</span>
	<p class="clearfix"></p>
</div> 
<div class="tac">
    <a href="/page/hd_view_report.action?sgid=${sgid}">互动分析</a> / 
    <a href="/page/hd_hour_report.action?sgid=${sgid}">小时段分析</a> / 
    <a href="/page/hudong_table.action?sgid=${sgid }">区域分析</a> / 
    <a href="/page/hd_fans_level.action?sgid=${sgid }">访客粉丝分析</a> / 
    <a class="chosen" href="/page/hd_fans_report.action?sgid=${sgid}">访客关注度分析</a> / 
    <a href="/page/hd_gender_report.action?sgid=${sgid}">性别比例</a> /
    <a href="/page/hd_terminal_report.action?sgid=${sgid}">互动终端分析</a> 
</div>
<div id="hdpie" class="chart-mod-line"></div>
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1">
	<thead>
		<tr>
			<th>微博昵称</th>
			<th>是否关注</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.fansList" var="f">
			<tr>
				<th>${f.nickname }</th>
				<td><s:if test='followed=="Y"'>已关注</s:if><s:else>未关注</s:else> </td>
			</tr>
		</s:iterator>
	</tbody>
</table>
<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>