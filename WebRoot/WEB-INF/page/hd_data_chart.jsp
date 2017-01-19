<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
        $(document).ready(function() {  
             var hdchart = new Highcharts.Chart({  
                       chart: {  
                            type: 'line',
                            renderTo: 'hdline'  
                        }, 
                       title: {
                            text: '互动分析',
                            x: -20 //center
                       },
                      subtitle: {
                           text: '',
                           x: -20
                      },
                      xAxis: {
                          categories: ${viewDto.datelist}
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
                         name: '互动数',
                       data: ${viewDto.hdynamicnum}        
                    }]  
             });
        });
</script>
<div class="wrap_content">
	<div class="switch_tab_div pb10">
		<span><a href="/page/sitegroupList.action">${viewDto.groupname}</a> <i class="gt">&gt;&gt;</i>  用户信息<i class="gt">&gt;&gt;</i>互动信息</span>
		<p class="clearfix"></p>
	</div> 
	<div class="tac">
	    <a class="chosen" href="/page/hd_view_report.action?sgid=${sgid}"></a>互动分析/ 
	    <a href="/page/hd_hour_report.action?sgid=${sgid}">小时段分析</a> / 
	    <a href="/page/hudong_table.action?sgid=${sgid }">区域分析</a> / 
	    <a href="/page/hd_fans_level.action?sgid=${sgid }">访客粉丝分析</a> / 
	    <a href="/page/hd_fans_report.action?sgid=${sgid}">访客关注度分析</a> / 
	    <a href="/page/hd_gender_report.action?sgid=${sgid}">性别比例</a> /
	    <a href="/page/hd_terminal_report.action?sgid=${sgid}">互动终端分析</a> 
	</div>
	<div class="pt10">
		<div id="hdline" class="chart-mod-line"></div>
	</div>
	<div>
	    <iframe src="/page/danymic_record.action?sgid=${sgid}"  width="100%" height="100%" scrolling="no" frameborder="0" marginheight="0" marginwidth="0" onload="this.height=this.contentWindow.document.documentElement.scrollHeight">
	    </iframe>
	</div>
</div>