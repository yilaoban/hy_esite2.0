<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 微预约 -->
<link href="/css/wyy_ht/index.css" rel="stylesheet" type="text/css" />
<script src="/js/wyy_ht/mobiscroll_002.js" type="text/javascript"></script>
<script src="/js/wyy_ht/mobiscroll_004.js" type="text/javascript"></script>
<link href="/css/wyy_ht/mobiscroll_002.css" rel="stylesheet" type="text/css">
<link href="/css/wyy_ht/mobiscroll.css" rel="stylesheet" type="text/css">
<script src="/js/wyy_ht/mobiscroll.js" type="text/javascript"></script>
<script src="/js/wyy_ht/mobiscroll_003.js" type="text/javascript"></script>
<script src="/js/wyy_ht/mobiscroll_005.js" type="text/javascript"></script>
<link href="/css/wyy_ht/mobiscroll_003.css" rel="stylesheet" type="text/css">
<%@include file="/WEB-INF/card/background.jsp"%>

<div class="main_160229_top"> 
<img src="/images/wyy/tu_03.png" hyvar="img" hydesc="195*140"/> 
<span>玻尿酸注射</span>
<p>伊美尔玻尿酸，轻轻一针，除皱，塑性，面部填充</p>
</div>

<form action="" method="get">
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="yuyue_box">
    <tr>
      <td>姓名：<font style="color:#F00">*</font></td>
    </tr>
    <tr>
      <td><input name="" type="text"  class="yuyue_input"/></td>
    </tr>
    <tr>
      <td>电话：<font style="color:#F00">*</font></td>
    </tr>
    <tr>
      <td><input name="" type="text"  class="yuyue_input"/></td>
    </tr>
    <tr>
      <td>时间：<font style="color:#F00">*</font></td>
    </tr>
    <tr>
      <td><input value="2015-05-01 15:42:02" class="yuyue_input" readonly name="appDateTime" id="appDateTime" type="text"></td>
    </tr>
    <tr>
      <td>对象：<font style="color:#F00">*</font></td>
    </tr>
    <tr>
      <td><input name="" type="text"  class="yuyue_input"/></td>
    </tr>
  </table>
  <div style="clear:both"></div>
  <div class="yuyue_bd_160229">
    <ul>
      <li><a href="#" hyvar="link">确定</a></li>
      <li><a href="#" hyvar="link">修改</a></li>
    </ul>
  </div>
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
		    $("#appDateTime").mobiscroll(optDateTime).datetime(optDateTime);
		    $("#appTime").mobiscroll(optTime).time(optTime);
        });
    </script>
</form>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
