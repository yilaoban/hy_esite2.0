<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
        $(document).ready(function() {  
             var ownervisitchart = new Highcharts.Chart({  
                       chart: {  
                            type: 'line',
                            renderTo: 'ownervisitline'  
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
                          categories: ${ownerData.dates},
                          labels: {
                             style: {
                               fontSize:'10px'
                             }
                         }
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
                       data:  ${ownerData.hdaynums}       
                    }]  
             });
        });
</script>
<div class="wrap_content">
	<div class="tac">
	    <a href="/data/owner_visit_data.action">一个月内的访问分析</a>/ 
	    <a href="" class="chosen">一个月内的互动分析</a>
	</div>
	<div class="pt10">
		<div id="ownervisitline" class="chart-mod-line"></div>
	</div>
</div>