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
                          categories: ${siteData.datelist}
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
                       data: ${siteData.hvisitnum }         
                    },{  
                         type: 'column',
                         name:"非匿名访问数",
                         yAxis:1,
                       data: ${siteData.nhvisitnum }         
                    },{  
                         type: 'line',
                         name:"新增访问数",
                         yAxis:1,
                       data: ${siteData.nhvisitadd }         
                    }]  
             });
             
        new Highcharts.Chart({  
           chart: {  
              renderTo: 'hdtypepie',
              plotBackgroundColor: null,
              plotBorderWidth: null,
              plotShadow: false
           }, 
          title: {
                text: '互动分析',
                x: -10 //center
            },
                     colors:[
                       '#00FF7F',
                       'blue',
                       '#BCEE68',
                       'green', 
                       '#8E2323',
                       'purple', 
                       '#FF6EC7', 
                       '#7093DB', 
                       '#FF00FF',
                       '#00EEEE'
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
                  var len=<s:property value="siteData.hdlist.size()"/>
                  for(var i=0;i<len;i++){
                     $("#typerow"+i).css("color","");
                  }
                  $("#typerow"+ter).css("color",e.point.color);
                  }
              }
            }
        },
          series: [{
                type: 'pie',
                name: '人数',
                data:${siteData.hdtypedata}
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
                  var ter=e.point.x;
                  var len=<s:property value="siteData.terminallist.size()"/>
                  for(var i=0;i<len;i++){
                     $("#terrow"+i).css("color","");
                  }
                   $("#terrow"+ter).css("color",e.point.color);
                  }
              }
            }
        },
        series: [{
                type: 'pie',
                name: '人数',
                data: ${siteData.hdterminaldata}
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
                   $("#numrow0").css("color","");
                   $("#numrow1").css("color","");
                   $("#numrow2").css("color","");
                   $("#numrow3").css("color","");
                   $("#numrow4").css("color","");
                   $("#numrow"+ter).css("color",e.point.color);
                  }
              }
            }
        },
          series: [{
                type: 'pie',
                name: '人数',
               data: [
                ['第1次互动', ${siteData.hnum1}],
                ['第2次互动', ${siteData.hnum2}],
                ['第3次互动', ${siteData.hnum3}],
                ['第4次互动', ${siteData.hnum4}],
                ['第5次互动及以上',${siteData.hnum5}]
            ]
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
                   var len=<s:property value="siteData.arealist.size()"/>
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
                data:${siteData.hdareadata}
            }]
       });  
        });
</script>
<div class="wrap_content">
    <p class="clearfix"></p>
     <div class="toolbar pb10">
	   <a class="button" href="/page/activity_report.action?siteid=${siteid}">活动报告</a>
	   <a class="button" href="/page/hd_report.action?siteid=${siteid}">互动报告</a>
	   <a class="button" href="/page/visit_report.action?siteid=${siteid}">用户报告</a>
	</div>
	<div class="pt10">
	<div id="nologvisitline" class="chart-mod-line"></div>
	<div id="visitnumcolum" class="chart-mod-line"  style="width:70%;float:left"></div>
	<div class="clearfix"></div>
	<div id="hdtypepie" class="chart-mod-line" style="width:60%;float:left"></div>
	<div style="margin-top:100px;">
	   <table width="40%" border="0" cellspacing="0" cellpadding="0" style="line-height:25px;">
	    <thead>
		 <tr>
		    <th><font style=" font-weight:bold">互动类型  </font></th>
		    <th><font style=" font-weight:bold">互动数</font></th>
		    <th><font style=" font-weight:bold">占比</font></th>
	      </tr>
	   </thead>
	   <tbody>
		<s:iterator value="siteData.hdlist" var="c" status="u">
			<tr align="center" id="typerow<s:property value="#u.index"/>">
			      <td >${c.type }</td>
			      <td >${c.total}</td>
			      <td >${c.totalstr}</td>
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
		<s:iterator value="siteData.terminallist" var="c" status="v">
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
	<div id="hdnumpie" class="chart-mod-line"  style="width:70%;float:left"></div>
	<div style="margin-top:100px;">
	   <table width="30%" border="0" cellspacing="0" cellpadding="0" style="line-height:25px;">
	    <thead>
		 <tr>
		    <th><font style=" font-weight:bold">互动次数</font></th>
		    <th><font style=" font-weight:bold">互动人数</font></th>
		    <th><font style=" font-weight:bold">占比</font></th>
	      </tr>
	   </thead>
	   <tbody>
		<s:iterator value="siteData.hdnumlist" var="c" status="v">
			<tr align="center" id="numrow<s:property value="#v.index"/>">
			      <td >第${c.num }次互动<s:if test="#c.num==5">及以上</s:if></td>
			      <td >${c.pernum }</td>
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
		<s:iterator value="siteData.arealist" var="c" status="u">
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
</div>