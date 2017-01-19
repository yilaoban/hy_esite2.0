<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
       
        $(document).ready(function() {  
             var visitchart = new Highcharts.Chart({  
          chart: {  
              renderTo: 'visitpie',
              plotBackgroundColor: null,
              plotBorderWidth: null,
              plotShadow: false
           }, 
          title: {
                text: '访问性别比例分析',
                x: -20 //center
            },
        series: [{
                type: 'pie',
                name: '人数',
                data: [
                   ['${genderAnalyse.vmnumstr}',${genderAnalyse.vmnum}],
                   ['${genderAnalyse.vfnumstr}',${genderAnalyse.vfnum}],
                   ['${genderAnalyse.vothernumstr}',${genderAnalyse.vothernum}]
                ]
            }]
       });  
      
        });
</script>
<div class="wrap_content">
	<div class="switch_tab_div pb10">
		<span><a href="/page/sitegroupList.action">${siteName}</a> <i class="gt">&gt;&gt;</i>  用户信息<i class="gt">&gt;&gt;</i>访问信息</span>
		<p class="clearfix"></p>
	</div> 
	<div class="tac">
	    <a href="/page/visit_report.action?siteid=${siteid}">到访分析</a> / 
	    <a href="/page/visit_hour_report.action?siteid=${siteid}">小时段分析</a> /
	    <a href="/page/rg_analysis.action?siteid=${siteid }">区域分析</a> / 
	    <a href="/page/visit_fans_level.action?siteid=${siteid}">访客粉丝分析</a> /
	    <a href="/page/fans_report.action?siteid=${siteid}">访客关注度分析</a> / 
	    <a class="chosen" href="/page/visit_gender_report.action?siteid=${siteid}">性别比例</a>/
	    <a href="/page/visit_terminal_report.action?siteid=${siteid}">访问终端分析</a>
	</div>
	<div class="pt10">
		<div id="visitpie" class="chart-mod-line"></div>
	    <table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
		<thead>
			<tr>
				<th>性别</th>
				<th>访问数</th>
			</tr>
		</thead>
		<tbody>
				<tr align="center">
					<td>男</td>
					<td>${genderAnalyse.vmnum}</td>
				</tr>
				<tr align="center">
					<td>女</td>
					<td>${genderAnalyse.vfnum}</td>
				</tr>
				<tr align="center">
					<td>其他</td>
					<td>${genderAnalyse.vothernum}</td>
				</tr>
		</tbody>
	</table>
	</div>
</div>
