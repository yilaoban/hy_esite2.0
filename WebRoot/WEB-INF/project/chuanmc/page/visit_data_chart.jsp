<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
        $(document).ready(function() {  
             var nologvisitchart = new Highcharts.Chart({  
                       chart: {  
                            renderTo: 'nologvisitline'  
                        }, 
                       title: {
                            text: '到访分析',
                            x: -20 //center
                       },
                      subtitle: {
                           text: '',
                           x: -20
                      },
                      xAxis: {
                          categories: ${viewDto.datelist}
                      },
                     yAxis: [{ 
                       title: {
                       text: '总访问数',
                      style: {
                        color: '#89A54E'
                       }
                    }
                 },{ 
                title: {
                    text: '非匿名访问数',
                    style: {
                        color: '#4572A7'
                    }
                  },
                  opposite: true
                },{ 
                title: {
                    text: '新增访问数',
                    style: {
                        color: '#4572A7'
                    }
                  },
                  opposite: true
                }],
                   
                  series: [{  
                       type: 'column',
                        name:"总访问数",
                       yAxis:0,
                       data: ${viewDto.hvisitnum }         
                    },{  
                         type: 'column',
                         name:"非匿名访问数",
                         yAxis:1,
                       data: ${viewDto.nhvisitnum }         
                    },{  
                         type: 'line',
                         name:"新增访问数",
                         yAxis:1,
                       data: ${viewDto.nhvisitadd }         
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
	    <a class="chosen" href="/page/visit_report.action?siteid=${siteid}"></a> 到访分析/ 
	    <a href="/page/visit_hour_report.action?siteid=${siteid}">小时段分析</a> / 
	    <a href="/page/rg_analysis.action?siteid=${siteid }">区域分析</a> / 
	    <a href="/page/visit_fans_level.action?siteid=${siteid}">访客粉丝分析</a> /
	    <a href="/page/fans_report.action?siteid=${siteid}">访客关注度分析</a> / 
	    <a href="/page/visit_gender_report.action?siteid=${siteid}">性别比例</a>/
	    <a href="/page/visit_terminal_report.action?siteid=${siteid}">访问终端分析</a>
	</div>
	<div class="pt10">
		<div id="nologvisitline" class="chart-mod-line"></div>
	</div>
	<div>
	     <iframe src="/page/custom_visitreport.action?siteid=${siteid}"  width="100%" height="100%" scrolling="no" frameborder="0" marginheight="0" marginwidth="0" onload="this.height=this.contentWindow.document.documentElement.scrollHeight">
	    </iframe>
	</div>
</div>