<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
       
        $(document).ready(function() {  
             var hourchart = new Highcharts.Chart({  
                       chart: {  
                            type: 'line',
                            renderTo: 'hourreportline'  
                        }, 
                       title: {
                            text: '小时段分析',
                            x: -20 //center
                       },
                      subtitle: {
                           text: '',
                           x: -20
                      },
                      xAxis: {
                          categories: ['0~1','1~2','2~3','3~4','4~5','5~6','6~7','7~8','8~9','9~10','10~11','11~12','12~13','13~14','14~15','15~16','16~17','17~18','18~19','19~20','20~21','21~22','22~23','23~24']  
                      },
                     yAxis: {
                          title: {
                          text: ''
                      },
                    plotLines: [{
                          value: 0,
                          width: 1,
                          color: '#808080'
                      }]
                    },
                   tooltip: {
                         valueSuffix: ''
                   },
                  series: [{  
                         name: '访问量',
                       data: ${reportDto.vnum}        
                    }]  
             });
        });
</script>
<div class="wrap_content">
	<div class="switch_tab_div pb10">
		<span><a href="/page/sitegroupList.action">${reportDto.name}</a> <i class="gt">&gt;&gt;</i>  用户信息<i class="gt">&gt;&gt;</i>访问信息</span>
		<p class="clearfix"></p>
	</div> 
	<div class="tac">
	    <a href="/page/visit_report.action?siteid=${siteid}">到访分析</a> / 
	    <a class="chosen" href="/page/visit_hour_report.action?siteid=${siteid}">小时段分析</a> /
	    <a href="/page/rg_analysis.action?siteid=${siteid }">区域分析</a> / 
	    <a href="/page/visit_fans_level.action?siteid=${siteid}">访客粉丝分析</a> /
	    <a href="/page/fans_report.action?siteid=${siteid}">访客关注度分析</a> / 
	    <a href="/page/visit_gender_report.action?siteid=${siteid}">性别比例</a>/
	    <a href="/page/visit_terminal_report.action?siteid=${siteid}">访问终端分析</a>
	</div>
	<div class="pt10">
		<div id="hourreportline" class="chart-mod-line"></div>
	    <table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
		<thead>
			<tr>
				<th>小时段</th>
				<th>访问数</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="reportDto.list" var="s">
			<tr align="center" >
			   <td>${s.hour}:00~${s.hour}:59</td>
			   <td>${s.visitNum}</td>
			</tr>
		</s:iterator>
		</tbody>
	</table>
	</div>
</div>
