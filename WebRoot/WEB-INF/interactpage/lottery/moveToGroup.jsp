<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
		$(document).ready(function() {
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
					},
					events: {
	           			click: function(e) {
	           				var area = e.point.name;
	           				var fensi = ${gz_i};
					        var srcString = "/${oname}/interact/findWxGroupList.action?dto.id=${lid}&dto.type=${type}&dto.area="+area+"&dto.fensi="+fensi;
							var	title="移动到组";
							layer.open({
									type : 2,
									area : ['400px','400px'],
									title : [title,true],
									content: srcString
							});
					    }
		   			},
				}
			},
			series : [ {
				type : 'pie',
				name : '比例',
				data : ${areaList}
			} ]
		});
		})
		
</script>
<div>
	<form action="">
		<div class="toolbar pb10">
	  	  <ul class="c_switch">
			  <li <s:if test="gz_i == 1">class="selected"</s:if>><a href="/${oname}/interact/lotteryMoveToGroup.action?lid=${lid}&type=${type}&gz_i=1">粉丝</a></li>
			  <li <s:if test="gz_i == 0">class="selected"</s:if>><a href="/${oname}/interact/lotteryMoveToGroup.action?lid=${lid}&type=${type}&gz_i=0" >非粉丝</a></li>
			  <li <s:if test="gz_i == -1">class="selected"</s:if>><a href="/${oname}/interact/lotteryMoveToGroup.action?lid=${lid}&type=${type}&gz_i=-1" >全部</a></li>
		  </ul>
		</div>
		<input type="hidden" name="fensi" value="1" id="fensi">
		<div class="pt10">
			<div id="areavisitpie" class=""></div>
		</div>
		
	</form>
	
</div>
