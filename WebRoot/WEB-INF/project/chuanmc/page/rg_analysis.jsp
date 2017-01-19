<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
        var sitegroupid =<s:property value="%{sgid}"/>;
        function getForm(){  
            jQuery.getJSON('/page/visithighchart_report.action',{"sgid": sitegroupid,"inajax":1}, function(data) { 
       var visit = new Highcharts.Chart({ 
      
           chart: {  
              renderTo: 'areapie2',
              plotBackgroundColor: null,
              plotBorderWidth: null,
              plotShadow: false
           }, 
         
           plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: 'grey',
                    connectorColor: 'grey',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
             }
       	   },
          title: {
                text: '访问区域分析',
                x: -10 //center
            },
          series: [{
                type: 'pie',
                name: '人数',
                data:data.areas
            }]
       });  
 	});
 }  
 
        $(document).ready(function() {  
           getForm();   
        });
       
</script>
<div class="wrap_content">
	<div class="switch_tab_div pb10">
		<span><a href="/page/sitegroupList.action">${sitename}</a> <i class="gt">&gt;&gt;</i>  用户信息<i class="gt">&gt;&gt;</i>访问信息</span>
		<p class="clearfix"></p>
	</div> 
	<div class="tac">
	    <a href="/page/visit_report.action?siteid=${siteid}">到访分析</a> / 
	    <a href="/page/visit_hour_report.action?siteid=${siteid}">小时段分析</a> / 
	    <a class="chosen" href="/page/rg_analysis.action?siteid=${siteid }">区域分析</a> / 
	    <a href="/page/visit_fans_level.action?siteid=${siteid}">访客粉丝分析</a> /
	    <a href="/page/fans_report.action?siteid=${siteid}">访客关注度分析</a> / 
	    <a href="/page/visit_gender_report.action?siteid=${siteid}">性别比例</a>/
	    <a href="/page/visit_terminal_report.action?siteid=${siteid}">访问终端分析</a>
	</div>
	<div class="pt10">
		<div id="areapie2" class="chart-mod-line" ></div>
		<div class="clearfix"></div>
		<p>共${dto.visitnum}个访问</p>
		<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
		<thead>
			<tr>
				<th>地区</th>
				<th>访问数</th>
				<th>最近一次的访问时间</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.areas" var="s">
				<tr align="center" >
				     <td >${s.area }</td>
				      <td >${s.num}</td>
				      <td >${s.lasttime }</td>
				</tr>
			</s:iterator>
		</tbody>
		</table>
		<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
	</div>
</div>

