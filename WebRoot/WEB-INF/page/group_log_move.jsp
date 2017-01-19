<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	$(document).ready(function() {
		var fansList = ${dto.fansList};
		var areaList = ${dto.areaList};

		$('#guanzhupie').highcharts({
			chart : {
				plotBackgroundColor : null,
				plotBorderWidth : null,
				plotShadow : false
			},
			title : {
				text : '粉丝'
			},
			tooltip : {
				pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
			},
			plotOptions : {
				pie : {
					size : '30%',
					allowPointSelect : true,
					cursor : 'pointer',
					dataLabels : {
						enabled : true,
						color : '#000000',
						connectorColor : '#000000',
						format : '<b>{point.name}</b>: {point.y}'
					},
					events : {
						click : function(e) {
							var gz_i = e.point.name == "粉丝" ? 1 : 0;
							var oldgz_i = $('#gz_i').val();
							if (gz_i == oldgz_i) {
								$('#gz_i').val(-1);
							} else {
								$('#gz_i').val(gz_i);
							}
						}
					}
				}
			},
			series : [ {
				type : 'pie',
				name : '比例',
				data : fansList
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
					size : '70%',
					allowPointSelect : true,
					cursor : 'pointer',
					dataLabels : {
						enabled : true,
						color : '#000000',
						connectorColor : '#000000',
						format : '<b>{point.name}</b>: {point.y}'
					},
					events : {
						click : function(e) {
							var area = e.point.name;
							if ('${dto.area}' == "") {
								var oldarea = $('#area').val();
								if (area == oldarea) {
									$('#area').val("");
								} else {
									$('#area').val(area);
								}
							}
						}
					},
				}
			},
			series : [ {
				type : 'pie',
				name : '比例',
				data : areaList
			} ]
		});

	});
	function saveWxGroupJob() {
		var groupid = 0;
		var groupname = $(".tab-content .active").find(".form-control").val().trim();
		if (groupname.indexOf(",") != -1) {
			var group = groupname.split(",");
			groupid = group[0];
			groupname = group[1];
		}
		if (groupname == "") {
			alert("组名不能为空");
			return;
		}
		$("#groupid").val(groupid);
		$("#groupname").val(groupname);
		$.ajax({
			url : "/${oname}/data/group_job.action",
			type : "post",
			data : $("#myform").serialize(),
			cache : false,
			success : function(data) {
				if (data > 0) {
					alert("操作成功");
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			}
		});
	}
</script>
<div id="guanzhupie" style="width: 40%; float: left;"></div>
<div id="areavisitpie" style="width: 60%; float: right;"></div>
<div class="bs-example bs-example-tabs" data-example-id="togglable-tabs">
	<ul class="nav nav-tabs" role="tablist">
		<li role="presentation" class="active">
			<a href="#old" id="home-tab" role="tab" data-toggle="tab" aria-controls="home" aria-expanded="true">移至已存在组</a>
		</li>
		<li role="presentation" class="">
			<a href="#new" role="tab" id="profile-tab" data-toggle="tab" aria-controls="profile" aria-expanded="false">移至新建组</a>
		</li>
	</ul>
	<div class="tab-content">
		<div role="tabpanel" class="tab-pane fade active in" id="old" aria-labelledby="home-tab">
			<div class="form-group">
				<select name="group" class="form-control">
					<s:iterator value="wxGroupList" var="g">
						<option value="${g.id},${g.name}">${g.name}</option>
					</s:iterator>
				</select>
			</div>
		</div>
		<div role="tabpanel" class="tab-pane fade" id="new" aria-labelledby="profile-tab">
			<div class="form-group">
				<input class="form-control" placeholder="新建组名">
			</div>
		</div>
		<input type="button" class="btn btn-primary" value="确定" onclick="saveWxGroupJob()">
	</div>
</div>
<form id="myform">
	<input type="hidden" name="dto.owner" value="${dto.owner}" />
	<input type="hidden" name="dto.mtype" value="${dto.mtype }" />
	<input type="hidden" name="dto.atype" value="${dto.atype }" />
	<input type="hidden" name="dto.day_i" value="${dto.day_i }" />
	<input type="hidden" name="dto.hour" value="${dto.hour }" />
	<input type="hidden" name="dto.new_i" value="${dto.new_i }" />
	<input type="hidden" name="dto.gz_i" value="-1" id="gz_i" />
	<input type="hidden" name="dto.area" value="${dto.area }" id="area" />
	<input type="hidden" name="dto.spage" value="${dto.spage}" />

	<input type="hidden" name="mpid" value="${mpid}" />
	<input type="hidden" name="dto.groupid" id="groupid" />
	<input type="hidden" name="dto.groupname" id="groupname" />
</form>
