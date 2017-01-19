<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
       
     $(document).ready(function() {  
        var visitchart = new Highcharts.Chart({  
          chart: {  
              renderTo: 'hdpie',
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
		<span><a href="/page/sitegroupList.action">${terminalDto.groupname}</a> <i class="gt">&gt;&gt;</i>  用户信息<i class="gt">&gt;&gt;</i>互动信息</span>
		<p class="clearfix"></p>
	</div> 
	<div class="tac">
	     <a href="/page/hd_view_report.action?sgid=${sgid}">互动分析</a> / 
         <a href="/page/hd_hour_report.action?sgid=${sgid}">小时段分析</a> / 
         <a href="/page/hudong_table.action?sgid=${sgid }">区域分析</a> / 
         <a href="/page/hd_fans_level.action?sgid=${sgid }">访客粉丝分析</a> / 
         <a href="/page/hd_fans_report.action?sgid=${sgid}">访客关注度分析</a> / 
         <a href="/page/hd_gender_report.action?sgid=${sgid}">性别比例</a> /
         <a class="chosen" href="/page/hd_terminal_report.action?sgid=${sgid}">互动终端分析</a> 
	</div>
	<div class="pt10">
		<div id="hdpie" class="chart-mod-line"></div>
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
			   <td>${s.hnum}</td>
			   <td><s:date name="lasthdtime" format="yyyy-MM-dd HH:mm"/></td>
			</tr>
		</s:iterator>
		</tbody>
	</table>
	</div>
</div>
