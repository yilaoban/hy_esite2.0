<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
       
    $(document).ready(function() {  
         var activechart = new Highcharts.Chart({  
          chart: {  
              renderTo: 'fanlevelpie',
              plotBackgroundColor: null,
              plotBorderWidth: null,
              plotShadow: false
           }, 
          title: {
                text: '访客粉丝分析',
                x: -20 //center
            },
        series: [{
                type: 'pie',
                name: '人数',
               data: [
                   ['小于100人=${levelAnalyseDto.dlevelpercent1}',${levelAnalyseDto.dlevelnum1}],
                   ['介于100到500人之间=${levelAnalyseDto.dlevelpercent2}',${levelAnalyseDto.dlevelnum2}],
                   ['介于500到1000人之间=${levelAnalyseDto.dlevelpercent3}',${levelAnalyseDto.dlevelnum3}],
                   ['大于等于1000人=${levelAnalyseDto.dlevelpercent4}',${levelAnalyseDto.dlevelnum4}]
                ]
            }]
       });        
      
   });
</script>
<div class="wrap_content">
	<div class="switch_tab_div pb10">
		<span><a href="/page/sitegroupList.action">${levelAnalyseDto.sitegroupname}</a><i class="gt">&gt;&gt;</i>  用户信息<i class="gt">&gt;&gt;</i>互动信息 </span>
		<p class="clearfix"></p>
	</div> 
	<div class="tac">
	    <a href="/page/hd_view_report.action?sgid=${sgid}">互动分析</a> / 
	    <a href="/page/hd_hour_report.action?sgid=${sgid}">小时段分析</a> / 
	    <a href="/page/hudong_table.action?sgid=${sgid }">区域分析</a> /
	    <a class="chosen" href="/page/hd_fans_level.action?sgid=${sgid }">访客粉丝分析</a> /  
	    <a href="/page/hd_fans_report.action?sgid=${sgid}">访客关注度分析</a> / 
	    <a href="/page/hd_gender_report.action?sgid=${sgid}">性别比例</a> /
	    <a href="/page/hd_terminal_report.action?sgid=${sgid}">互动终端分析</a> 
	</div>
	<div class="pt10">
		<div id="fanlevelpie" class="chart-mod-line"></div>
	    <table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
		<thead>
			<tr>
				<th>粉丝分布</th>
				<th>符合该分布访问人数</th>
				<th>占比</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="levelAnalyseDto.list" var="s">
			<tr align="center" >
			
			    <s:if test="#attr.s.level==1">
			          <td>粉丝数<100人</td>
			          <td>${s.dnum}</td>
			          <td>${levelAnalyseDto.dlevelpercent1}</td>
			       </s:if>
			       <s:elseif test="#attr.s.level==2">
			          <td>100≤粉丝数<500人</td>
			          <td>${s.dnum}</td>
			          <td>${levelAnalyseDto.dlevelpercent2}</td>
			       </s:elseif>
			       <s:elseif test="#attr.s.level==3">
			           <td>500≤粉丝数<1000人</td>
			           <td>${s.dnum}</td>
			           <td>${levelAnalyseDto.dlevelpercent3}</td> 
			       </s:elseif>
			       <s:elseif test="#attr.s.level==4">
			           <td>粉丝数>=1000人</td>
			           <td>${s.dnum}</td>
			           <td>${levelAnalyseDto.dlevelpercent4}</td>
			       </s:elseif>
			</tr>
		</s:iterator>
		</tbody>
	</table>
	</div>
</div>
