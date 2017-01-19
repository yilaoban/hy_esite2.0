<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
       
      $(document).ready(function() {  
         var activechart = new Highcharts.Chart({  
          chart: {  
              renderTo: 'activepie',
              plotBackgroundColor: null,
              plotBorderWidth: null,
              plotShadow: false
           }, 
          title: {
                text: '互动性别比例分析',
                x: -20 //center
            },
        series: [{
                type: 'pie',
                name: '人数',
               data: [
                   ['${genderAnalyse.hmnumstr}',${genderAnalyse.hmnum}],
                   ['${genderAnalyse.hfnumstr}',${genderAnalyse.hfnum}],
                   ['${genderAnalyse.hothernumstr}',${genderAnalyse.hothernum}]
                ]
            }]
       });        
      
        });
</script>
<div class="wrap_content">
	<div class="switch_tab_div pb10">
		<span><a href="/page/sitegroupList.action">${genderAnalyse.groupname}</a>  <i class="gt">&gt;&gt;</i>  用户信息 <i class="gt">&gt;&gt;</i>互动信息</span>
		<p class="clearfix"></p>
	</div> 
	<div class="tac">
	    <a href="/page/hd_view_report.action?sgid=${sgid}">互动分析</a> / 
	    <a href="/page/hd_hour_report.action?sgid=${sgid}">小时段分析</a> /
	    <a href="/page/hudong_table.action?sgid=${sgid }">区域分析</a> / 
	    <a href="/page/hd_fans_level.action?sgid=${sgid }">访客粉丝分析</a> / 
	    <a href="/page/hd_fans_report.action?sgid=${sgid}">访客关注度分析</a> / 
	    <a class="chosen" href="/page/hd_gender_report.action?sgid=${sgid}">性别比例</a> /
	    <a href="/page/hd_terminal_report.action?sgid=${sgid}">互动终端分析</a> 
	</div>
	<div class="pt10">
		<div id="activepie" class="chart-mod-line"></div>
	    <table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
		<thead>
			<tr>
				<th>性别</th>
				<th>互动数</th>
			</tr>
		</thead>
		<tbody>
				<tr align="center">
					<td>男</td>
					<td>${genderAnalyse.hmnum}</td>
				</tr>
				<tr align="center">
					<td>女</td>
					<td>${genderAnalyse.hfnum}</td>
				</tr>
				<tr align="center">
					<td>其他</td>
					<td>${genderAnalyse.hothernum}</td>
				</tr>
		</tbody>
	</table>
	</div>
</div>
