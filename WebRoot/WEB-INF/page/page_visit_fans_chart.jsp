<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
        $(document).ready(function() {  
              var fanschart = new Highcharts.Chart({  
                   chart: {  
                        type: 'column',
                        renderTo: 'fanscou'  
                    }, 
                   title: {
                       text: '访问粉丝数分析',
                      x: -20 //center
                    },
                   subtitle: {
                       text: '',
                       x: -20
                   },
                   xAxis: {
                       categories: [
                    '0~500',
                    '500~1000',
                    '1000~3000',
                    '3000~5000',
                    '5000~10000',
                    '10000以上'
                ]
                   },
                 yAxis: {
                min: 0,
                allowDecimals:false,
                title: {
                    text: '访问数(次)'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.0f} 次</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{  
                name: '访问次数',
                 data: [ ${pageDto.fansnum1}, ${pageDto.fansnum2},  ${pageDto.fansnum3}, ${pageDto.fansnum4},  ${pageDto.fansnum5}, ${pageDto.fansnum6}]        
             }]  
          });
        });
</script>
<div class="wrap_content">
     <div class="switch_tab_div pb10">
	<span><a href="/data/site_page_list.action">分项报告</a><i class="gt">&gt;&gt;</i>  ${pageDto.sitePage.pagename}</span>
	<p class="clearfix"></p>
     </div>
	<div class="tac">
	    <a href="/data/page_visit_area.action?src=${src}&pid=${pid}">访问区域分析</a>/ 
	    <a href="" class="chosen">访问粉丝分析</a> / 
	     <a href="/data/page_visit_gender.action?src=${src}&pid=${pid}">访问性别比例分析</a> 
	</div>
	<div class="pt10">
		<div id="fanscou" class="chart-mod-line"></div>
	</div>
	<div>
	    <iframe src="/data/page_visit_list.action?src=${src}&pid=${pid}"  width="100%" height="100%" scrolling="no" frameborder="0" marginheight="0" marginwidth="0" onload="this.height=this.contentWindow.document.documentElement.scrollHeight">
	    </iframe>
	</div>
</div>