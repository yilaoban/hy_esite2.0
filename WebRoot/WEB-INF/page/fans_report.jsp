<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
$(document).ready(function() {  
          var visitreport = new Highcharts.Chart({
					        chart: {
					            plotBackgroundColor: null,
					            plotBorderWidth: null,
					            plotShadow: false,
					            renderTo: 'visitpie'  
					        },
					        title: {
					            text: '访客关注度分析'
					        },
					        series: [{ type: 'pie',
                                      name: '人数',
                                     data: [
                                          ['已关注='+${dto.vfansY},  ${dto.vfansY}],
                                          ['未关注='+${dto.vfansN}, ${dto.vfansN}],
                                     ]
                                   }]
					    });  
})
</script>
<div class="wrap_content">
<div class="switch_tab_div pb10">
	<span><a href="/page/sitegroupList.action">${dto.site.name}</a> <i class="gt">&gt;&gt;</i>  用户信息<i class="gt">&gt;&gt;</i>访问信息</span>
	<p class="clearfix"></p>
</div> 
<div class="tac">
    <a href="/page/visit_report.action?siteid=${siteid}">到访分析</a> / 
    <a href="/page/visit_hour_report.action?siteid=${siteid}">小时段分析</a> / 
    <a href="/page/rg_analysis.action?siteid=${siteid }">区域分析</a> / 
    <a href="/page/visit_fans_level.action?siteid=${siteid}">访客粉丝分析</a> /
    <a class="chosen" href="/page/fans_report.action?siteid=${siteid}">访客关注度分析</a> / 
    <a href="/page/visit_gender_report.action?siteid=${siteid}">性别比例</a> /
    <a href="/page/visit_terminal_report.action?siteid=${siteid}">访问终端分析</a>
</div>
<div id="visitpie" class="chart-mod-line"></div>
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