<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var ownervisitchart = new Highcharts.Chart({
			chart : {
				type : 'line',
				renderTo : 'ownervisitline'
			},
			title : {
				text : '访问统计',
				x : -20
			//center
			},
			subtitle : {
				text : '',
				x : -20
			},
			xAxis : {
				categories : ${dto.xcategory}
			},
			yAxis : {
				title : {
					text : ''
				},
				min : 0,
				allowDecimals : false
			},
			tooltip : {
				valueSuffix : ''
			},
			series : [ {
				name : '访问量(pv)',
				data : ${dto.pv}
			}, {
				name : '访客数(uv)',
				data : ${dto.uv}
			} ]
		});

		$('#areavisitpie').highcharts({
			chart : {
				plotBackgroundColor : null,
				plotBorderWidth : null,
				plotShadow : false
			},
			title : {
				text : '地域分布'
			},
			tooltip : {
				pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
			},
			plotOptions : {
				pie : {
					allowPointSelect : true,
					cursor : 'pointer',
					dataLabels : {
						enabled : true,
						color : '#000000',
						connectorColor : '#000000',
						format : '<b>{point.name}</b>: {point.y}'
					}
				}
			},
			series : [ {
				type : 'pie',
				name : '比例',
				data : ${dto.area}
			} ]
		});

		$('#terminalvisitpie').highcharts({
			chart : {
				plotBackgroundColor : null,
				plotBorderWidth : null,
				plotShadow : false
			},
			title : {
				text : '终端分布'
			},
			tooltip : {
				pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
			},
			plotOptions : {
				pie : {
					allowPointSelect : true,
					cursor : 'pointer',
					dataLabels : {
						enabled : true,
						color : '#000000',
						connectorColor : '#000000',
						format : '<b>{point.name}</b>: {point.y}'
					}
				}
			},
			series : [ {
				type : 'pie',
				name : '比例',
				data : ${dto.terminal}
			} ]
		});

	});

	function submit(gtype) {
		if (gtype == 'SEARCH') {
			if($("#startTime").val()!="" && $("#endTime").val()!=""){
				$("#dform").find("input[name='dtype']").val("");
				gtype = 'DAY';
			}
		}else{
			$("#dform").find("input[name='gtype']").val(gtype);
		}
		$("#dform").submit();
	}
</script>

<div class="wrap_content">
	<div class="tac">
		<a <s:if test="dtype=='today'">class="chosen"</s:if> href="/${oname}/data/iframe_data_index.action?mtype=${mtype}&dtype=today&gtype=HOUR">今天</a>/ <a
			<s:if test="dtype=='yesterday'">class="chosen"</s:if> href="/${oname}/data/iframe_data_index.action?mtype=${mtype}&dtype=yesterday&gtype=HOUR">昨天</a>/ <a
			<s:if test="dtype=='week'">class="chosen"</s:if> href="/${oname}/data/iframe_data_index.action?mtype=${mtype}&dtype=week&gtype=DAY">最近7天</a>/ <a
			<s:if test="dtype=='month'">class="chosen"</s:if> href="/${oname}/data/iframe_data_index.action?mtype=${mtype}&dtype=month&gtype=DAY">最近30天</a>
	</div>
	<div class="toolbar">
		<form id="dform" action="/${oname}/data/iframe_data_index.action">
			<input name="mtype" type="hidden" value="${mtype}"> <input name="dtype" type="hidden" value="${dtype}"> <input name="gtype" type="hidden" value="${gtype}">
			<input name="starttime" id="startTime" value="${starttime}" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd'})" readonly="readonly" />-
			<input name="endtime" id="endTime" value="${endtime}" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})" readonly="readonly" /> <a
				href="javascript:void(0);" onclick="submit('SEARCH')">搜索</a> / <a href="javascript:void(0);" onclick="submit('HOUR')" <s:if test="gtype=='HOUR'">class="chosen"</s:if>>按时</a>/ <a
				href="javascript:void(0);" onclick="submit('DAY')" <s:if test="gtype=='DAY'">class="chosen"</s:if>>按天</a>
		</form>
	</div>

	<div class="pt10">
		<div id="ownervisitline" class="chart-mod-line"></div>
	</div>
	<div class="pt10">
		<div id="areavisitpie" class=""></div>
	</div>
	<div class="pt10">
		<div id="terminalvisitpie" class=""></div>
	</div>
</div>