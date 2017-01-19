<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
        $(document).ready(function() {  
             var visitchart = new Highcharts.Chart({  
          chart: {  
              renderTo: 'hdtypepie',
              plotBackgroundColor: null,
              plotBorderWidth: null,
              plotShadow: false
           }, 
          title: {
                text: '互动类型分析',
                x: -20 //center
            },
            plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.2f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
                type: 'pie',
                name: '人数',
                data: ${pageDto.data}
            }]
       });  
      
        });
</script>
<div class="wrap_content">
     <div class="switch_tab_div pb10">
	<span><a href="/data/site_page_list.action">分项报告</a><i class="gt">&gt;&gt;</i>  ${pageDto.sitePage.pagename}</span>
	<p class="clearfix"></p>
     </div>
	<div class="pt10">
		<div id="hdtypepie" class="chart-mod-line"></div>
	</div>
	<div>
	   <table class="tb_normal" width="100%" border="1" cellspacing="0"
			cellpadding="0">
			<tr>
				<th>
					互动类型
				</th>
				<th>
					最后一次互动时间
				</th>
				<th>
					互动总数
				</th>
			    <th>
					操作
				</th>
				
			</tr>
			<s:iterator value="pageDto.typelist" var="l" status="s">
				<tr>
					<td align="center">
						${l.type}
					</td>
					<td align="center">
						<s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"></s:date>
					</td>
					<td align="center">
						${l.count }
					</td>
			
					<td align="center">
					 <s:if test="#attr.l.act==103||#attr.l.act==106||#attr.l.act==111||#attr.l.act==113||#attr.l.act==117||#attr.l.act==120||#attr.l.act==121"><a href="/page/hdDetail.action?pid=${pageDto.sitePage.pageid}&hdid=${l.act}&hdfid=${l.hdid}">查看</a></s:if>
				     <s:elseif test="#attr.l.act==127"><a href="/${oname }/interact/appointment_data.action?aptid=${l.hdid}">查看</a></s:elseif>
				     <s:elseif test="#attr.l.act==128"><a href="/${oname }/interact/vote_record_list.action?voteid=${l.hdid}">查看</a></s:elseif>
				     <s:elseif test="#attr.l.act==129"><a href="/${oname }/interact/research_record_list.action?searchid=${l.hdid}">查看</a></s:elseif>
				     <s:elseif test="#attr.l.act==130"><a href="/${oname }/interact/lottery_user_<s:if test="src=='s'">sina</s:if><s:else>wx</s:else>.action?lid=${l.hdid}&type=L">查看</a></s:elseif>
				     <s:elseif test="#attr.l.act==131"><a href="/${oname }/interact/auctionJoinList.action?auid=${l.hdid}">查看</a></s:elseif>
				     <s:elseif test="#attr.l.act==133"><a href="/${oname }/interact/checkin_data.action?ownerwbuid=${l.hdid}">查看</a></s:elseif>
				     <s:elseif test="#attr.l.act==135"><a href="/${oname }/interact/spreadRecord.action?spreadid=${l.hdid}">查看</a></s:elseif>
				     <s:elseif test="#attr.l.act==137"><a href="/${oname }/interact/lottery_user_<s:if test="src=='s'">sina</s:if><s:else>wx</s:else>.action?lid=${l.hdid}&type=Z">查看</a></s:elseif>
				     <s:elseif test="#attr.l.act==139"><a href="/${oname }/interact/lottery_user_<s:if test="src=='s'">sina</s:if><s:else>wx</s:else>.action?lid=${l.hdid}&type=G">查看</a></s:elseif>
				     <s:elseif test="#attr.l.act==141"><a href="/${oname }/interact/periodicalRecord.action?jid=${l.hdid}">查看</a></s:elseif>
					</td> 
				</tr>
			</s:iterator>
		</table>
	</div>
</div>