<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
    $(document).ready(function() { 
        var interact = new Highcharts.Chart({  
            chart: {
                renderTo: 'visitnumcolum',
                type: 'column'
            },
            title: {
                text: '访问分析'
            },
            xAxis: {
                categories:  ${indexDto.gnames}
            },
            yAxis: {
                min: 0,
                title: {
                    text: ''
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
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
                name: '访问人次数',
                data: ${indexDto.numlist}
    
            }, {
                name: '访问人数',
                data: ${indexDto.pernumlist}
    
            }]
        });
        
        new Highcharts.Chart({  
           chart: {  
              renderTo: 'visitnumpie',
              plotBackgroundColor: null,
              plotBorderWidth: null,
              plotShadow: false
           }, 
          title: {
                text: '访问次数分析',
                x: -10 //center
            },
                     colors:[
                       '#00FF7F',
                       'blue',
                       '#BCEE68',
                       'green', 
                       '#8E2323'
                      ],
            plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }, events: {
                  click: function(e) {
                  var ter=e.point.x;
                   $("#vnumrow0").css("color","");
                   $("#vnumrow1").css("color","");
                   $("#vnumrow2").css("color","");
                   $("#vnumrow3").css("color","");
                   $("#vnumrow4").css("color","");
                   $("#vnumrow"+ter).css("color",e.point.color);
                  }
              }
            }
        },
          series: [{
                type: 'pie',
                name: '人数',
               data: [
                ['第1次访问', ${indexDto.vnum1}],
                ['第2次访问', ${indexDto.vnum2}],
                ['第3次访问', ${indexDto.vnum3}],
                ['第4次访问', ${indexDto.vnum4}],
                ['第5次访问及以上', ${indexDto.vnum5}]
            ]
            }]
       }); 
       
         new Highcharts.Chart({  
           chart: {  
              renderTo: 'hdnumpie',
              plotBackgroundColor: null,
              plotBorderWidth: null,
              plotShadow: false
           }, 
          title: {
                text: '互动次数分析',
                x: -10 //center
            },
                     colors:[
                       '#00FF7F',
                       'blue',
                       '#BCEE68',
                       'green', 
                       '#8E2323'
                      ],
            plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }, events: {
                  click: function(e) {
                  var ter=e.point.x;
                   $("#hnumrow0").css("color","");
                   $("#hnumrow1").css("color","");
                   $("#hnumrow2").css("color","");
                   $("#hnumrow3").css("color","");
                   $("#hnumrow4").css("color","");
                   $("#hnumrow"+ter).css("color",e.point.color);
                  }
              }
            }
        },
          series: [{
                type: 'pie',
                name: '人数',
               data: [
                ['第1次互动', ${indexDto.hnum1}],
                ['第2次互动', ${indexDto.hnum2}],
                ['第3次互动', ${indexDto.hnum3}],
                ['第4次互动', ${indexDto.hnum4}],
                ['第5次互动及以上', ${indexDto.hnum5}]
            ]
            }]
       }); 
       
         new Highcharts.Chart({  
          chart: {  
              renderTo: 'terminalpie',
              plotBackgroundColor: null,
              plotBorderWidth: null,
              plotShadow: false
           }, 
          title: {
                text: '终端分析',
                x: -20 //center
            },
               colors:[
                       '#00FF7F',
                       'blue',
                       '#BCEE68'
                      ],
            plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }, events: {
                  click: function(e) {
                  var len=<s:property value="indexDto.terminallist.size()"/>
                  for(var i=0;i<len;i++){
                     $("#terrow"+i).css("color","");
                  }
                  var ter=e.point.x;
                  $("#terrow"+ter).css("color",e.point.color);
                  }
              }
            }
        },
        series: [{
                type: 'pie',
                name: '人数',
                data: ${indexDto.hdterminaldata}
            }]
       });  
       new Highcharts.Chart({  
           chart: {  
              renderTo: 'areapie',
              plotBackgroundColor: null,
              plotBorderWidth: null,
              plotShadow: false
           }, 
          title: {
                text: '互动区域分析',
                x: -10 //center
            },
                     colors:[
                       '#00FF7F',
                       'blue',
                       '#5F7834',
                       'green', 
                       '#8E2323',
                       'purple', 
                       '#CA6DA6', 
                       '#7093DB', 
                       '#FF00FF',
                       '#FF0000',
                       '#008B8B'
                      ],
            plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }, events: {
                  click: function(e) {
                  var ter=e.point.x;
                 var len=<s:property value="indexDto.arealist.size()"/>
                  for(var i=0;i<len;i++){
                     $("#arearow"+i).css("color","");
                  }
                   $("#arearow"+ter).css("color",e.point.color);
                  }
              }
            }
        },
          series: [{
                type: 'pie',
                name: '人数',
                data:${indexDto.hdareadata}
            }]
       });  
    });
</script>
<div class="wrap_content">
<div id="visitnumcolum" class="chart-mod-line"  style="width:70%;float:left"></div>
	<div style="margin-top:80px;">
	   <table width="30%" border="0" cellspacing="0" cellpadding="0" style="line-height:25px;">
	   <thead>
		 <tr>
		    <th><font style=" font-weight:bold">站点名称</font></th>
		    <th><font style=" font-weight:bold">访问人次数</font></th>
		    <th><font style=" font-weight:bold">访问人数</font></th>
	      </tr>
	   </thead>
	   <tbody>
	     <s:iterator value="indexDto.siteList" var="g">
			<tr align="center">
			      <td >${g.name}</td>
			      <td >${g.vistinum}</td>
			      <td >${g.vpernum}</td>
			</tr>
		  </s:iterator>
			<tr align="center">
			      <td >${indexDto.allsitegroup.groupname}</td>
			      <td >${indexDto.allsitegroup.vistinum}</td>
			      <td >${indexDto.allsitegroup.vpernum}</td>
			</tr>
		</tbody>
	</table>
	</div>
	<div class="clearfix"></div>
	<div id="visitnumpie" class="chart-mod-line"  style="width:70%;float:left"></div>
	<div style="margin-top:100px;">
	   <table width="30%" border="0" cellspacing="0" cellpadding="0" style="line-height:25px;">
	     <thead>
		 <tr>
		    <th><font style=" font-weight:bold">访问次数</font></th>
		    <th><font style=" font-weight:bold">访问量</font></th>
		    <th><font style=" font-weight:bold">访问比率</font></th>
	      </tr>
	   </thead>
	   <tbody>
		<s:iterator value="indexDto.vistinumlist" var="c" status="v">
			<tr align="center" id="vnumrow<s:property value="#v.index"/>">
			      <td >第${c.num }次访问<s:if test="#c.num==5">及以上</s:if></td>
			      <td >${c.pernum }</td>
			      <td >${c.percent}</td>
			</tr>
		</s:iterator>
	  </tbody>
	</table>
	</div>
	<div class="clearfix"></div>
	<div id="hdnumpie" class="chart-mod-line"  style="width:70%;float:left"></div>
	<div style="margin-top:100px;">
	   <table width="30%" border="0" cellspacing="0" cellpadding="0" style="line-height:25px;">
	     <thead>
		 <tr>
		    <th><font style=" font-weight:bold">互动次数</font></th>
		    <th><font style=" font-weight:bold">互动量</font></th>
		    <th><font style=" font-weight:bold">互动比率</font></th>
	      </tr>
	   </thead>
	   <tbody>
		<s:iterator value="indexDto.hdnumlist" var="c" status="v">
			<tr align="center" id="hnumrow<s:property value="#v.index"/>">
			      <td >第${c.num }次互动<s:if test="#c.num==5">及以上</s:if></td>
			      <td >${c.pernum }</td>
			      <td >${c.percent}</td>
			</tr>
		</s:iterator>
	</tbody>
	</table>
	</div>
	<div class="clearfix"></div>
	<div id="terminalpie" class="chart-mod-line"  style="width:70%;float:left"></div>
	<div style="margin-top:100px;">
	   <table width="30%" border="0" cellspacing="0" cellpadding="0" style="line-height:25px;">
	    <thead>
		 <tr>
		    <th><font style=" font-weight:bold">终端类型</font></th>
		    <th><font style=" font-weight:bold">互动数</font></th>
		    <th><font style=" font-weight:bold">占比</font></th>
	      </tr>
	   </thead>
	   <tbody>
		<s:iterator value="indexDto.terminallist" var="c" status="v">
			<tr align="center" id="terrow<s:property value="#v.index"/>">
			      <td >${c.terminal}</td>
			      <td >${c.hnum}</td>
			      <td >${c.percent}</td>
			</tr>
		</s:iterator>
	</tbody>
	</table>
	</div>
	<div class="clearfix"></div>
	<div id="areapie" class="chart-mod-line" style="width:60%;float:left"></div>
	<div style="margin-top:50px;">
	   <table width="40%" border="0" cellspacing="0" cellpadding="0" style="line-height:25px;">
	    <thead>
		 <tr>
		    <th><font style=" font-weight:bold">区域</font></th>
		    <th><font style=" font-weight:bold">互动数</font></th>
		    <th><font style=" font-weight:bold">占比</font></th>
	      </tr>
	   </thead>
	   <tbody>
		<s:iterator value="indexDto.arealist" var="c" status="u">
			<tr align="center" id="arearow<s:property value="#u.index"/>">
			      <td >${c.area }</td>
			      <td >${c.num }</td>
			      <td >${c.numpercent}</td>
			</tr>
		</s:iterator>
	</tbody>
	</table>
	</div>
	<div class="clearfix"></div>
</div>