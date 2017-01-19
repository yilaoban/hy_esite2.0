<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
	<div class="toolbar pb10">
		<ul class="c_switch">
			<li class="selected"><a href="/${oname}/data/share.action">趋势</a></li>
			<li><a href="/${oname}/data/share_user.action">用户</a></li>
			<li><a href="/${oname}/data/share_page.action">页面</a></li>
		</ul>
	</div>

	<div class="tac">
		<a <s:if test="dtype=='today'">class="chosen"</s:if> href="/${oname}/data/share.action?dtype=today&gtype=HOUR">今天</a>
		/
		<a <s:if test="dtype=='yesterday'">class="chosen"</s:if> href="/${oname}/data/share.action?dtype=yesterday&gtype=HOUR">昨天</a>
		/
		<a <s:if test="dtype=='week'">class="chosen"</s:if> href="/${oname}/data/share.action?dtype=week&gtype=DAY">最近7天</a>
		/
		<a <s:if test="dtype=='month'">class="chosen"</s:if> href="/${oname}/data/share.action?dtype=month&gtype=DAY">最近30天</a>
	</div>
	<div class="toolbar mt10">
		<form id="dform" action="/${oname}/data/share.action">
			<input name="dtype" type="hidden" value="${dtype}">
			<input name="gtype" type="hidden" value="${gtype}">
			<input name="starttime" id="startTime" value="${starttime}" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd'})" readonly="readonly" />
			-
			<input name="endtime" id="endTime" value="${endtime}" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})" readonly="readonly" />
			<a href="javascript:void(0);" onclick="submit('SEARCH')">搜索</a>
			/
			<a href="javascript:void(0);" onclick="submit('HOUR')" <s:if test="gtype=='HOUR'">class="chosen"</s:if>>按时</a>
			/
			<a href="javascript:void(0);" onclick="submit('DAY')" <s:if test="gtype=='DAY'">class="chosen"</s:if>>按天</a>
		</form>
	</div>

	<div class="pt10">
		<div id="shareLine" class="chart-mod-line"></div>
	</div>
	<div class="pt10">
		<div id="clickLine" class="chart-mod-line"></div>
	</div>
	<div class="pt10">
		<div id="subscribeLine" class="chart-mod-line"></div>
	</div>
	<div class="pt10">
		<div id="interactLine" class="chart-mod-line"></div>
	</div>
	<div class="pt10">
		<div id="outerLine" class="chart-mod-line"></div>
	</div>
</div>

<script src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var shareChart = new Highcharts.Chart({
			chart : {
				type : 'line',
				renderTo : 'shareLine'
			},
			title : {
				text : '分享',
				x : -20
			},
			xAxis : {
				title : {
					text : '时间'
				},
				categories : ${dto.xcategory}
			},
			yAxis : {
				title : {
					text : '次数'
				},
				min : 0,
				allowDecimals : false
			},
			tooltip : {
				headerFormat : '<b>时间</b> : {point.x}<br>',
				pointFormat : '<b>次数</b> : {point.y}'
			},
			series : [ {
				name : '分享',
				data : ${dto.share}
			} ]
		});

		var clickChart = new Highcharts.Chart({
			chart : {
				type : 'line',
				renderTo : 'clickLine'
			},
			title : {
				text : '点击',
				x : -20
			},
			xAxis : {
				title : {
					text : '时间'
				},
				categories : ${dto.xcategory}
			},
			yAxis : {
				title : {
					text : '次数'
				},
				min : 0,
				allowDecimals : false
			},
			tooltip : {
				headerFormat : '<b>时间</b> : {point.x}<br>',
				pointFormat : '<b>次数</b> : {point.y}'
			},
			series : [ {
				name : '点击',
				data : ${dto.click}
			} ]
		});

		var subscribeChart = new Highcharts.Chart({
			chart : {
				type : 'line',
				renderTo : 'subscribeLine'
			},
			title : {
				text : '关注',
				x : -20
			},
			xAxis : {
				title : {
					text : '时间'
				},
				categories : ${dto.xcategory}
			},
			yAxis : {
				title : {
					text : '次数'
				},
				min : 0,
				allowDecimals : false
			},
			tooltip : {
				headerFormat : '<b>时间</b> : {point.x}<br>',
				pointFormat : '<b>次数</b> : {point.y}'
			},
			series : [ {
				name : '关注',
				data : ${dto.subscribe}
			} ]
		});

		var interactChart = new Highcharts.Chart({
			chart : {
				type : 'line',
				renderTo : 'interactLine'
			},
			title : {
				text : '互动',
				x : -20
			},
			xAxis : {
				title : {
					text : '时间'
				},
				categories : ${dto.xcategory}
			},
			yAxis : {
				title : {
					text : '次数'
				},
				min : 0,
				allowDecimals : false
			},
			tooltip : {
				headerFormat : '<b>时间</b> : {point.x}<br>',
				pointFormat : '<b>次数</b> : {point.y}'
			},
			series : [ {
				name : '互动',
				data : ${dto.interact}
			} ]
		});

		var outerChart = new Highcharts.Chart({
			chart : {
				type : 'line',
				renderTo : 'outerLine'
			},
			title : {
				text : '外部',
				x : -20
			},
			xAxis : {
				title : {
					text : '时间'
				},
				categories : ${dto.xcategory}
			},
			yAxis : {
				title : {
					text : '次数'
				},
				min : 0,
				allowDecimals : false
			},
			tooltip : {
				headerFormat : '<b>时间</b> : {point.x}<br>',
				pointFormat : '<b>次数</b> : {point.y}'
			},
			series : [ {
				name : '外部',
				data : ${dto.outer}
			} ]
		});
	});

	function submit(gtype) {
		if (gtype == 'SEARCH') {
			if ($("#startTime").val() == "") {
				WdatePicker({
					el : 'startTime',
					maxDate : '#F{$dp.$D(\'endTime\')}',
					dateFmt : 'yyyy-MM-dd HH:mm:ss'
				});
				return;
			}
			if ($("#endTime").val() == "") {
				WdatePicker({
					el : 'endTime',
					minDate : '#F{$dp.$D(\'startTime\')}',
					dateFmt : 'yyyy-MM-dd HH:mm:ss'
				});
				return;
			}

			$("#dform").find("input[name='dtype']").val("");
			gtype = 'DAY';
		}
		$("#dform").find("input[name='gtype']").val(gtype);
		$("#dform").submit();
	}

	
</script>