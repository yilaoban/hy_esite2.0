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
                text: '终端分析',
                x: -20 //center
            },
        series: [{
                type: 'pie',
                name: '人数',
                data: ${terminalstr}
            }]
       });  
      
        });
</script>
<div class="wrap_content">
	<div class="switch_tab_div pb10">
		<span><a href="/page/sitegroupList.action">${terminalDto.name}</a> <i class="gt">&gt;&gt;</i>  用户信息<i class="gt">&gt;&gt;</i>访问信息</span>
		<p class="clearfix"></p>
	</div> 
	<div class="tac">
	    <a href="/page/visit_report.action?siteid=${siteid}">到访分析</a> / 
	    <a href="/page/visit_hour_report.action?siteid=${siteid}">小时段分析</a> /
	    <a href="/page/rg_analysis.action?siteid=${siteid }">区域分析</a> / 
	    <a href="/page/visit_fans_level.action?siteid=${siteid}">访客粉丝分析</a> /
	    <a href="/page/fans_report.action?siteid=${siteid}">访客关注度分析</a> / 
	    <a href="/page/visit_gender_report.action?siteid=${siteid}">性别比例</a>/
	    <a class="chosen" href="/page/visit_terminal_report.action?siteid=${siteid}">访问终端分析</a>
	</div>
	<div class="pt10">
		<div id="visitpie" class="chart-mod-line"></div>
	    <table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
		<thead>
			<tr>
				<th>终端</th>
				<th>访问数</th>
				<th>最后一次访问时间</th>
			</tr>
		</thead>
		<tbody>
		  <s:iterator value="terminalDto.list" var="s">
			<tr align="center" >
			   <td><s:if test='#s.terminal=="A"'>Pad</s:if><s:elseif test='#s.terminal=="C"'>PC</s:elseif><s:elseif test='#s.terminal=="P"'>Phone</s:elseif></td>
			   <td>${s.vnum}</td>
			    <td><s:date name="lastvtime" format="yyyy-MM-dd HH:mm"/></td>
			</tr>
		</s:iterator>
		</tbody>
	</table>
	</div>
</div>
