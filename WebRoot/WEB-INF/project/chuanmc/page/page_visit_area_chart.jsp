<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
        $(document).ready(function() {  
             var visitchart = new Highcharts.Chart({  
          chart: {  
              renderTo: 'vistiareapie',
              plotBackgroundColor: null,
              plotBorderWidth: null,
              plotShadow: false
           }, 
          title: {
                text: '访问区域分析',
                x: -20 //center
            },
            plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.2f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
                type: 'pie',
                name: '人数',
                data: ${pageDto.data}
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
	    <a class="chosen" href="">访问区域分析</a>/ 
	    <s:if test='src=="s"'>
	    	<a href="/data/page_visit_fans.action?pid=${pid}">访问粉丝分析</a> / 
	    </s:if>
	    <a href="/data/page_visit_gender.action?src=${src}&pid=${pid}">访问性别比例分析</a> 
	</div>
	<div class="pt10">
		<div id="vistiareapie" class="chart-mod-line"></div>
	</div>
	<div>
	    <iframe src="/data/page_visit_list.action?src=${src}&pid=${pid}"  width="100%" height="100%" scrolling="no" frameborder="0" marginheight="0" marginwidth="0" onload="this.height=this.contentWindow.document.documentElement.scrollHeight">
	    </iframe>
	</div>
</div>