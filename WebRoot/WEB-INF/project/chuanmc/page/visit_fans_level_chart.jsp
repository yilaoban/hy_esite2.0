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
                   ['小于100人=${levelAnalyseDto.vlevelpercent1}',${levelAnalyseDto.vlevelnum1}],
                   ['介于100到500人之间=${levelAnalyseDto.vlevelpercent2}',${levelAnalyseDto.vlevelnum2}],
                   ['介于500到1000人之间=${levelAnalyseDto.vlevelpercent3}',${levelAnalyseDto.vlevelnum3}],
                   ['大于等于1000人=${levelAnalyseDto.vlevelpercent4}',${levelAnalyseDto.vlevelnum4}]
                ]
            }]
       });        
      
   });
</script>
<div class="wrap_content">
	<div class="switch_tab_div pb10">
		<span><a href="/page/sitegroupList.action">${levelAnalyseDto.sitename}</a><i class="gt">&gt;&gt;</i>  用户信息<i class="gt">&gt;&gt;</i>访问信息 </span>
		<p class="clearfix"></p>
	</div> 
	<div class="tac">
	    <a href="/page/visit_report.action?siteid=${siteid}">到访分析</a> / 
	    <a href="/page/visit_hour_report.action?siteid=${siteid}">小时段分析</a> /
	    <a href="/page/rg_analysis.action?siteid=${siteid }">区域分析</a> / 
	    <a class="chosen" href="/page/visit_fans_level.action?siteid=${siteid}">访客粉丝分析</a> /
	    <a href="/page/fans_report.action?siteid=${siteid}">访客关注度分析</a> / 
	    <a href="/page/visit_gender_report.action?siteid=${siteid}">性别比例</a>/
	    <a href="/page/visit_terminal_report.action?siteid=${siteid}">访问终端分析</a>
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
			          <td>${s.vnum}</td>
			          <td>${levelAnalyseDto.vlevelpercent1}</td>
			       </s:if>
			       <s:elseif test="#attr.s.level==2">
			          <td>100≤粉丝数<500人</td>
			          <td>${s.vnum}</td>
			          <td>${levelAnalyseDto.vlevelpercent2}</td>
			       </s:elseif>
			       <s:elseif test="#attr.s.level==3">
			           <td>500≤粉丝数<1000人</td>
			           <td>${s.vnum}</td>
			           <td>${levelAnalyseDto.vlevelpercent3}</td> 
			       </s:elseif>
			       <s:elseif test="#attr.s.level==4">
			           <td>粉丝数>=1000人</td>
			           <td>${s.vnum}</td>
			           <td>${levelAnalyseDto.vlevelpercent4}</td>
			       </s:elseif>
			</tr>
		</s:iterator>
		</tbody>
	</table>
	</div>
</div>
