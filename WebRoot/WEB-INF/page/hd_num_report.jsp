<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
    $(document).ready(function() {  
        var interact = new Highcharts.Chart({  
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
                   $("tr").css("color","");
                   $("#row"+ter).css("color",e.point.color);
                  }
              }
            }
        },
          series: [{
                type: 'pie',
                name: '次数',
                data:${numDataDto.nicknames}
            }]
       }); 
 	});
</script>
<div class="wrap_content">
<div class="switch_tab_div pb10">
	<span><a href="/page/sitegroupList.action">${numDataDto.sitegroup.groupname}</a> <i class="gt">&gt;&gt;</i>  互动报告</span>
	<p class="clearfix"></p>
</div>
<div class="tac">
	    <a href="/page/hd_report.action?sgid=${sgid}">互动类型/</a> 
	    <a class="chosen" href="/page/hd_num_report.action?sgid=${sgid}"></a>互动次数/
	    <a href="/page/hd_area_report.action?sgid=${sgid}">互动区域</a>  
</div> 
<div id="hdnumpie" class="chart-mod-line" ></div>
	<div >
	   <table width="100%" border="0" cellspacing="0" cellpadding="0" >
	
	
		<s:iterator value="numDataDto.list" var="c" status="u">
			<tr align="center" id="row<s:property value="#u.index"/>">
			      <td >${c.nickname }</td>
			      <td >${c.hdnum}</td>
			      <td >${c.hdnumpercent}</td>
			</tr>
		</s:iterator>
	
	</table>
	</div>
<div>
<div>
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
		<th>互动类型</th>
		<th>互动名称</th>
		<th>最近一次互动时间</th>
		<th>互动总数</th>
		<th>操作</th>
	</tr>
	</thead>
	<tbody>
		<s:iterator value="numDataDto.models" var="m">
			<tr>
				<td>${m.type}</td>
				<td>${m.name}</td>
				<td><s:date name="lasttime" format="yyyy-MM-dd HH:mm:ss"/> </td>
				<td>${m.total}</td>
				<td>
				     <s:if test="#attr.m.hdid==103||#attr.m.hdid==106||#attr.m.hdid==111||#attr.m.hdid==113||#attr.m.hdid==117||#attr.m.hdid==120||#attr.m.hdid==121"><a href="/page/hdDetail.action?sgid=${numDataDto.sitegroup.id}&hdid=${m.hdid}&hdfid=${m.hdfid}">查看</a></s:if>
				     <s:elseif test="#attr.m.hdid==127"><a href="/${oname }/interact/appointment_data.action?id=${m.hdfid}">查看</a></s:elseif>
				     <s:elseif test="#attr.m.hdid==128"><a href="/${oname }/interact/vote_record_list.action?voteid=${m.hdfid}&vtitle=${m.name}">查看</a></s:elseif>
				     <s:elseif test="#attr.m.hdid==129"><a href="/${oname }/interact/research_record_list.action?searchid=${m.hdfid}">查看</a></s:elseif>
				     <s:elseif test="#attr.m.hdid==130"><a href="/${oname }/interact/lottery_user.action?lid=${m.hdfid}&type='L'">查看</a></s:elseif>
				     <s:elseif test="#attr.m.hdid==131"><a href="/${oname }/interact/auctionJoinList.action?auid=${m.hdfid}">查看</a></s:elseif>
				     <s:elseif test="#attr.m.hdid==133"><a href="/${oname }/interact/checkin_data.action?ownerwbuid=${m.hdfid}">查看</a></s:elseif>
				     <s:elseif test="#attr.m.hdid==135"><a href="/${oname }/interact/spreadRecord.action?spreadid=${m.hdfid}">查看</a></s:elseif>
				     <s:elseif test="#attr.m.hdid==137"><a href="/${oname }/interact/lottery_user.action?lid=${m.hdfid}&type='Z'">查看</a></s:elseif>
				     <s:elseif test="#attr.m.hdid==139"><a href="/${oname }/interact/lottery_user.action?lid=${m.hdfid}&type='G'">查看</a></s:elseif>
				     <s:elseif test="#attr.m.hdid==141"><a href="/${oname }/interact//periodicalRecord.action?jid=${m.hdfid}">查看</a></s:elseif>
				</td>
			</tr>
		</s:iterator>
	</tbody>
</table>
</div>
</div>
</div>