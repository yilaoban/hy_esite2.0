<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
        $(document).ready(function() {
             var ownervisitchart = new Highcharts.Chart({  
                       chart: {  
                            type: 'line',
                            renderTo: 'sitehdline'  
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
                          categories: ${siteDto.dates}
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
                  series:  ${siteDto.data}
             });
        });
</script>
<div class="wrap_content">
    <div class="switch_tab_div pb10">
	<span><a href="/data/site_page_list.action">分项报告</a><i class="gt">&gt;&gt;</i>  ${siteDto.site.name}</span>
	<p class="clearfix"></p>
     </div>
	<div class="pt10">
		<div id="sitehdline" class="chart-mod-line"></div>
	</div>
</div>