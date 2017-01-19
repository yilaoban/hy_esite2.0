<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
	var sitegroupid =<s:property value="%{sitegroupid}"/>;
	var activityid = <s:property value="%{activityid}"/>;
	   function getForm(){  
            jQuery.getJSON('/page/ajax_find_participate_success.action',{"sitegroupid": sitegroupid,"activityid":activityid,"inajax":1}, function(data) { 
		  var visitTerminal = new Highcharts.Chart({
					        chart: {
					            plotBackgroundColor: null,
					            plotBorderWidth: null,
					            plotShadow: false,
					            renderTo: 'teiminaldata'  
					        },
					        title: {
					            text: '成功参与人数终端详情'
					        },
					        tooltip: {
					    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
					        },
					        plotOptions: {
					            pie: {
					                allowPointSelect: true,
					                cursor: 'pointer',
					                dataLabels: {
					                    enabled: true,
					                    color: '#000000',
					                    connectorColor: '#000000',
					                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
					                }
					            }
					        },
					       series: [{ type: 'pie',
                                      name: 'Browser share',
                                     data: data.terminalList
                                   }]
					    });
  		});  
 	}
 	
	function getForm1(){  
            jQuery.getJSON('/page/ajax_find_participatesource_success.action',{"sitegroupid": sitegroupid,"activityid":activityid,"inajax":1}, function(data) { 
		    var visitTerminal = new Highcharts.Chart({
					        chart: {
					            plotBackgroundColor: null,
					            plotBorderWidth: null,
					            plotShadow: false,
					            renderTo: 'sourcedata'  
					        },
					        title: {
					            text: '成功参与人数来源详情'
					        },
					        tooltip: {
					    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
					        },
					        plotOptions: {
					            pie: {
					                allowPointSelect: true,
					                cursor: 'pointer',
					                dataLabels: {
					                    enabled: true,
					                    color: '#000000',
					                    connectorColor: '#000000',
					                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
					                }
					            }
					        },
					        series: [{ type: 'pie',
                                      name: 'Browser share',
                                     data: data.sourceList
                                   }]
					    });
  		});  
 	}
	
	 $(document).ready(function() {  
           getForm();
           getForm1();
        });	
</script>
<div class="wrap_content">
<div class="switch_tab_div pt10">
	<span><a href="/page/sitegroupList.action">${dto.sitegroup.groupname}</a> <i class="gt">&gt;&gt;</i> 活动报告</span>
	<p class="clearfix"></p>
</div>
<s:if test="dto.activityList.size>0">
<div>
	<ul class="insTab">
	<s:iterator value="dto.activityList" var="s" status='st'>
		<s:if test="activityid==0">
			<li <s:if test="#st.index==0">class="active"</s:if>><a href="/page/activity_report.action?sitegroupid=${dto.sitegroup.id }&activityid=${s.id }">${s.name}</a></li>
		</s:if>
		<s:else>
			<li <s:if test="activityid==id">class="active"</s:if>><a href="/page/activity_report.action?sitegroupid=${dto.sitegroup.id }&activityid=${s.id }">${s.name}</a></li>
		</s:else>	</s:iterator>
	</ul>
</div>
<div class="insTabCon">
<div style="padding-left:40px;padding-bottom:40px;" class="mt20">
    <div class="level-chart"><div class="level-circle-blue"><span id="totalsent">${dto.countSum}</span></div><div class="level-label"><a href="/page/activity_report.action?sitegroupid=<s:property value="%{sitegroupid}"/>&activityid=<s:property value="%{activityid}"/>">访问人数</a></div></div>
    <div class="level-next"></div>
    <div class="level-chart"><div class="level-circle-green"><span id="totalarrived">${dto.participateCount}</span></div><div class="level-label"><a href="/page/activity_participate.action?sitegroupid=<s:property value="%{sitegroupid}"/>&activityid=<s:property value="%{activityid}"/>">参与人数</a></div></div>
    <div class="level-next"></div>
    <div class="level-chart"><div class="level-circle-yellow"><span id="totalopen">${dto.participateFailCount}</span></div><div class="level-label"><a href="/page/activity_participate_fail.action?sitegroupid=<s:property value="%{sitegroupid}"/>&activityid=<s:property value="%{activityid}"/>">跳出人数</a></div></div>
    <div class="level-next"></div>
    <div class="level-chart"><div class="level-circle-red"><span id="totalclick">${dto.participateSuccessCount}</span></div><div class="level-label"><a href="/page/activity_participate_success.action?sitegroupid=<s:property value="%{sitegroupid}"/>&activityid=<s:property value="%{activityid}"/>">成功参与人数</a></div></div>
    <div class="clearfix"></div>
</div>
<div id="teiminaldata" style="float:left;width:58%;"></div>
<table width="40%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" style="float:right;margin-top:150px;">
	<thead>
		<tr>
			<th>终端</th>
			<th>参与人数</th>
			<th>参与人次数</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.terminal" var="s">
			<tr align="center" >
			    <td><s:if test='#s.name=="A"'>PAD</s:if><s:elseif test='#s.name=="C"'>PC</s:elseif><s:elseif test='#s.name=="P"'>PHONE</s:elseif></td>
			    <td>${s.count}</td>
			    <td>${s.countSum}</td>
			     <th><a href="javascript:void(0)" onclick="activity_success_details('${s.name }',<s:property value="%{activityid}"/>)">查看详情</a></th>
			</tr>	
		</s:iterator>
	</tbody>
</table>
<p class="clearfix pt10"></p>
<div id="sourcedata" style="float:left;width:58%;"></div>
<table width="40%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" style="float:right;margin-top:150px;">
	<thead>
		<tr>
			<th>来源</th>
			<th>参与人数</th>
			<th>参与人次数</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.source" var="s">
			<tr align="center" >
			    <td>${s.name}</td>
			    <td>${s.count}</td>
			    <td>${s.countSum}</td>
			</tr>	
		</s:iterator>
	</tbody>
</table>
<p class="clearfix pt10"></p>
</div>
</s:if>
<s:else>
	该站点下没有活动
</s:else>
</div>