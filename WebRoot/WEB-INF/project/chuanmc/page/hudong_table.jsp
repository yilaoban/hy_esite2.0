<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
        var sitegroupid =<s:property value="%{sgid}"/>;
        function getForm(){  
            jQuery.getJSON('/page/hudonghighchart_report.action',{"sgid": sitegroupid,"inajax":1}, function(data) { 
        var interact = new Highcharts.Chart({  
           chart: {  
              renderTo: 'areapie1',
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
                text: '互动区域分析',
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
	<span><a href="/page/sitegroupList.action">${sitegroupname}</a> <i class="gt">&gt;&gt;</i> 用户信息<i class="gt">&gt;&gt;</i>互动信息</span>
	<p class="clearfix"></p>
</div>
	<div class="tac">
	    <a href="/page/hd_view_report.action?sgid=${sgid}">互动分析</a> / 
	    <a href="/page/hd_hour_report.action?sgid=${sgid}">小时段分析</a> / 
	    <a class="chosen" href="/page/hudong_table.action?sgid=${sgid }">区域分析</a> /
	     <a href="/page/hd_fans_level.action?sgid=${sgid }">访客粉丝分析</a> /  
	    <a href="/page/hd_fans_report.action?sgid=${sgid}">访客关注度分析</a> / 
	    <a href="/page/hd_gender_report.action?sgid=${sgid}">性别比例</a> /
	    <a href="/page/hd_terminal_report.action?sgid=${sgid}">互动终端分析</a> 
	</div>
<div id="content" class="normal_content notab">
	<div id="areapie1" class="chart-mod-line" ></div>
	<div class="clearfix"></div>
	<p>共${dto.hdnum}个互动</p>
	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>地区</th>
			<th>互动数</th>
			<th>最近一次的互动时间</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.area" var="c">
			<tr align="center" >
			     <td >${c.area }</td>
			      <td >${c.num }</td>
			      <td >${c.lasttime }</td>
			</tr>
		</s:iterator>
	</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
</div>
