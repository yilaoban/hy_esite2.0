<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 微预约后台 -->
<link href="/css/wyy_ht/index.css" rel="stylesheet" type="text/css" />
<script src="/js/wyy_ht/index.js"></script>
<script src="/js/wyy_ht/mobiscroll_002.js" type="text/javascript"></script>
<script src="/js/wyy_ht/mobiscroll_004.js" type="text/javascript"></script>
<link href="/css/wyy_ht/mobiscroll_002.css" rel="stylesheet" type="text/css">
<link href="/css/wyy_ht/mobiscroll.css" rel="stylesheet" type="text/css">
<script src="/js/wyy_ht/mobiscroll.js" type="text/javascript"></script>
<script src="/js/wyy_ht/mobiscroll_003.js" type="text/javascript"></script>
<script src="/js/wyy_ht/mobiscroll_005.js" type="text/javascript"></script>
<link href="/css/wyy_ht/mobiscroll_003.css" rel="stylesheet" type="text/css">
<%@include file="/WEB-INF/card/background.jsp"%>
<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$.post("/${oname}/user/yuyueRecordShow.action",{"status":"EDT"},function(data){
		   	if(data){
		   		$("#tb1").html("");
				var aptid = data.yuyue.aptid;
				$.each( data.recordList, function(i, p){
					var html = '<tr>';
					var oname = "${oname}";
					html += '<td>'+p.nickname+'</td>';
					html +='<td>'+p.servicename+'</td>';
					html += '<td>'+p.sername+'</td>';
					html += '<td>'+p.yytimeStr+'';
					html += '</td>';
					html += '<td>';
					if(p.status == "EDT"){
						html += '<a href="javascript:editAptDetail('+aptid+','+p.wxuid+','+p.id+')"><span style="color: red;">未确认</span></a>';
					}else if(p.status == "CMP"){
						html += '已确认';
					}
					html += '</td>';
					html += '</tr>';
					$("#tb1").append(html);
					$("#tb1").show();
				});
		   	}else{
		   		$("#tb1").show();
		   	}
		  });
		   
		   
		   $("#daiqueding").click(function(){
				$.post("/${oname}/user/yuyueRecordShow.action",{"status":"EDT"}, function(data){
					if(data){
						$("#tb1").html("");
						var aptid = data.yuyue.aptid;
						$.each( data.recordList, function(i, p){
							var html = '<tr>';
							var oname = "${oname}";
							html += '<td>'+p.nickname+'</td>';
							html +='<td>'+p.servicename+'</td>';
							html += '<td>'+p.sername+'</td>';
							html += '<td>'+p.yytimeStr+'';
							html += '</td>';
							html += '<td>';
							if(p.status == "EDT"){
								html += '<a href="javascript:editAptDetail('+aptid+','+p.wxuid+','+p.id+')"><span style="color: red;">未确认</span></a>';
							}else if(p.status == "CMP"){
								html += '已确认';
							}
							html += '</td>';
							html += '</tr>';
							$("#tb1").append(html);
							$("#tb1").show();
						});
					}else{
						$("#tb1").show();
					}
				
		   	});
		});
		   
		  $("#queding").click(function(){
				$.post("/${oname}/user/yuyueRecordShow.action",{"status":"CMP"}, function(data){
					if(data){
						$("#tb2").html("");
						var aptid = data.yuyue.aptid;
						$.each( data.recordList, function(i, p){
							var html = '<tr>';
							var oname = "${oname}";
							html += '<td>'+p.nickname+'</td>';
							html +='<td>'+p.servicename+'</td>';
							html += '<td>'+p.sername+'</td>';
							html += '<td>'+p.yytimeStr+'';
							html += '</td>';
							html += '<td>';
							if(p.status == "EDT"){
								html += '<a href="javascript:editAptDetail('+aptid+','+p.wxuid+','+p.id+')"><span style="color: red;">未确认</span></a>';
							}else if(p.status == "CMP"){
								html += '已确认';
							}
							html += '</td>';
							html += '</tr>';
							$("#tb2").append(html);
							$("#tb2").show();
						});
					}else{
						$("#tb2").show();
					}
		   	});
		});
		 
		   
		   $("#guoqi").click(function(){
				$.post("/${oname}/user/yuyueRecordShowByNow.action", function(data){
					if(data){
						$("#tb3").html("");
						var aptid = data.yuyue.aptid;
						$.each( data.recordList, function(i, p){
							var html = '<tr>';
							var oname = "${oname}";
							html += '<td>'+p.nickname+'</td>';
							html +='<td>'+p.servicename+'</td>';
							html += '<td>'+p.sername+'</td>';
							html += '<td>'+p.yytimeStr+'';
							html += '</td>';
							html += '<td>';
							if(p.status == "EDT"){
								html += '<a href="javascript:editAptDetail('+aptid+','+p.wxuid+','+p.id+')"><span style="color: red;">未确认</span></a>';
							}else if(p.status == "CMP"){
								html += '已确认';
							}
							html += '</td>';
							html += '</tr>';
							$("#tb3").append(html);
							$("#tb3").show();
						});
					}else{
						$("#tb3").show();
					}
		   	});
		});
		
		 $("#sousuo").click(function(){
		 		var yytime = $('#yytime').val();
				$.post("/${oname}/user/yuyueRecordShow.action",{"yytime":yytime}, function(data){
					var index = $('#li_index').val();
					$("#tb" + index).html("");
					var aptid = data.yuyue.aptid;
					$.each( data.recordList, function(i, p){
						var html = '<tr>';
						var oname = "${oname}";
						html += '<td>'+p.nickname+'</td>';
						html +='<td>'+p.servicename+'</td>';
						html += '<td>'+p.sername+'</td>';
						html += '<td>'+p.yytimeStr+'';
						html += '</td>';
						html += '<td>';
						if(p.status == "EDT"){
							html += '<a href="javascript:editAptDetail('+aptid+','+p.wxuid+','+p.id+')"><span style="color: red;">未确认</span></a>';
						}else if(p.status == "CMP"){
							html += '已确认';
						}
						html += '</td>';
						html += '</tr>';
						$("#tb" + index).append(html);
					});
		   	});
		});
		
});
</script>

<figure class="tabBlock">
  <ul class="tabBlock-tabs">
    <li class="tabBlock-tab is-active" style="width:33.3%;" id="daiqueding" onclick="$('#li_index').val(1)">待确定</li>
    <li class="tabBlock-tab" style="width:33.3%;" id="queding" onclick="$('#li_index').val(2)">已确定</li>
    <li class="tabBlock-tab" style="width:33.3%;" id="guoqi" onclick="$('#li_index').val(3)">已过期</li>
  </ul>
  <input type="hidden" id="li_index" value="1">
  <div class="searchtime">
    <form action="" method="get" id="myform">
      <input class="qz_search" readonly name="yytime" value="<s:date name="yytime" format="yyyy-MM-dd HH:mm:ss"/>" id="yytime" type="text" placeholder="按时间搜索">
      <span id="sousuo"><img src="/images/search.png" /></span>
    </form>
    <script type="text/javascript">
        $(function () {
			var currYear = (new Date()).getFullYear();	
			var opt={};
			opt.date = {preset : 'date'};
			opt.datetime = {preset : 'datetime'};
			opt.time = {preset : 'time'};
			opt.default = {
				theme: 'android-ics light', //皮肤样式
		        display: 'modal', //显示方式 
		        mode: 'scroller', //日期选择模式
				dateFormat: 'yyyy-mm-dd',
				lang: 'zh',
				showNow: true,
				nowText: "今天",
		        startYear: currYear - 10, //开始年份
		        endYear: currYear + 10 //结束年份
			};

		  	$("#appDate").mobiscroll($.extend(opt['date'], opt['default']));
		  	var optDateTime = $.extend(opt['datetime'], opt['default']);
		  	var optTime = $.extend(opt['time'], opt['default']);
		    $("#yytime").mobiscroll(optDateTime).datetime(optDateTime);
		    $("#appTime").mobiscroll(optTime).time(optTime);
        });
    </script> 
  </div>
  
  <div class="tabBlock-content">
    <div class="tabBlock-pane">
      <table width="100%" cellpadding="0" cellspacing="0" align="center" class="yy_con" >
       <thead>
        <tr>
          <td style="font-weight:bold;">昵称</td>
          <td style="font-weight:bold;">预约服务</td>
          <td style="font-weight:bold;">服务人员</td>
          <td style="font-weight:bold;">预约时间</td>
          <td style="font-weight:bold;">状态</td>
        </tr>
        </thead>
        <tbody id="tb1" style="display: none;">
	        <tr>
	          <td>彤彤</td>
	          <td>2016/02/29</td>
	          <td>玻尿酸整容</td>
	          <td>莎莎老师</td>
	          <td>待确定</td>
	        </tr>
	        <tr>
	          <td>彤彤</td>
	          <td>2016/02/29</td>
	          <td>玻尿酸...</td>
	          <td>莎莎老师</td>
	          <td>待确定</td>
	        </tr>
	        <tr>
	          <td>彤彤</td>
	          <td>2016/02/29</td>
	          <td>玻尿酸...</td>
	          <td>莎莎老师</td>
	          <td>待确定</td>
	        </tr>
	        <tr>
	          <td>彤彤</td>
	          <td>2016/02/29</td>
	          <td>玻尿酸...</td>
	          <td>莎莎老师</td>
	          <td>待确定</td>
	        </tr>
	        <tr>
	          <td>彤彤</td>
	          <td>2016/02/29</td>
	          <td>玻尿酸...</td>
	          <td>莎莎老师</td>
	          <td>待确定</td>
	        </tr>
	        <tr>
	          <td>彤彤</td>
	          <td>2016/02/29</td>
	          <td>玻尿酸...</td>
	          <td>莎莎老师</td>
	          <td>待确定</td>
	        </tr>
        </tbody>
      </table>
    </div>
    <div class="tabBlock-pane">
      <table width="100%" cellpadding="0" cellspacing="0" align="center" class="yy_con">
      <thead>
	        <tr>
	          <td>昵称</td>
	          <td>预约服务</td>
	          <td>服务人员</td>
	          <td>预约时间</td>
	          <td>状态</td>
	        </tr>
      </thead>
      <tbody id="tb2" style="display: none;">
        <tr>
          <td>彤彤</td>
          <td>2016/02/29</td>
          <td>玻尿酸...</td>
          <td>莎莎老师</td>
          <td>已确定</td>
        </tr>
        <tr>
          <td>彤彤</td>
          <td>2016/02/29</td>
          <td>玻尿酸...</td>
          <td>莎莎老师</td>
          <td>已确定</td>
        </tr>
        <tr>
          <td>彤彤</td>
          <td>2016/02/29</td>
          <td>玻尿酸...</td>
          <td>莎莎老师</td>
          <td>已确定</td>
        </tr>
        <tr>
          <td>彤彤</td>
          <td>2016/02/29</td>
          <td>玻尿酸...</td>
          <td>莎莎老师</td>
          <td>已确定</td>
        </tr>
        <tr>
          <td>彤彤</td>
          <td>2016/02/29</td>
          <td>玻尿酸...</td>
          <td>莎莎老师</td>
          <td>已确定</td>
        </tr>
        <tr>
          <td>彤彤</td>
          <td>2016/02/29</td>
          <td>玻尿酸...</td>
          <td>莎莎老师</td>
          <td>已确定</td>
        </tr>
      </tbody>
      </table>
    </div>
    <div class="tabBlock-pane">
      <table width="100%" cellpadding="0" cellspacing="0" align="center" class="yy_con">
      <thead>
        <tr>
          <td>昵称</td>
          <td>预约服务</td>
          <td>服务人员</td>
          <td>预约时间</td>
          <td>状态</td>
        </tr>
      </thead>
      <tbody id="tb3" style="display: none;">
        <tr>
          <td>彤彤</td>
          <td>2016/02/29</td>
          <td>玻尿酸...</td>
          <td>莎莎老师</td>
          <td>已过期</td>
        </tr>
        <tr>
          <td>彤彤</td>
          <td>2016/02/29</td>
          <td>玻尿酸...</td>
          <td>莎莎老师</td>
          <td>已过期</td>
        </tr>
        <tr>
          <td>彤彤</td>
          <td>2016/02/29</td>
          <td>玻尿酸...</td>
          <td>莎莎老师</td>
          <td>已过期</td>
        </tr>
        <tr>
          <td>彤彤</td>
          <td>2016/02/29</td>
          <td>玻尿酸...</td>
          <td>莎莎老师</td>
          <td>已过期</td>
        </tr>
        <tr>
          <td>彤彤</td>
          <td>2016/02/29</td>
          <td>玻尿酸...</td>
          <td>莎莎老师</td>
          <td>已过期</td>
        </tr>
        <tr>
          <td>彤彤</td>
          <td>2016/02/29</td>
          <td>玻尿酸...</td>
          <td>莎莎老师</td>
          <td>已过期</td>
        </tr>
      </tbody>
      </table>
    </div>
  </div>
</figure>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
<script type="text/javascript">
function editAptDetail(aptid,wxuid,id){
	window.location.href = "/${oname}/user/yuyue_apt_record.action?aptid="+aptid + "&wxuid=" + wxuid + "&recordid=" + id;
}

</script>
