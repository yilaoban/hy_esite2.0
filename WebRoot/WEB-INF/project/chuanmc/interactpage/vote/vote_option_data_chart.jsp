<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
       
        $(document).ready(function() {  
            var voteoptionchart = new Highcharts.Chart({  
                  chart: {  
                      renderTo: 'voteoptionpie',
                      plotBackgroundColor: null,
                      plotBorderWidth: null,
                      plotShadow: false
           }, 
          title: {
                text: '${vtitle}<br>答题人数${optionDto.total}',
                x: -20 //center
            },
          series: [{
                type: 'pie',
                name: '人数',
                data: ${optionDto.votedata}
            }]
        });  
   });
</script>
  	<div class="toolbar pb10">
  	  <ul class="c_switch">
		  <li ><a href="/${oname }/interact/vote_record_list.action?voteid=${voteid}&mid=${mid }&type=0&redirectXc=${redirectXc}">微博数据</a></li>
		  <li ><a href="/${oname }/interact/vote_record_list.action?voteid=${voteid}&mid=${mid }&type=1&redirectXc=${redirectXc}">微信数据</a></li>
		  <li ><a href="/${oname }/interact/vote_record_list.action?voteid=${voteid}&mid=${mid }&type=2&redirectXc=${redirectXc}">用户数据</a></li>
		  <!-- 
		  <li ><a href="/${oname }/interact/vote_record_list.action?voteid=${voteid}&mid=${mid }&type=-1&redirectXc=${redirectXc}">匿名数据</a></li>
		   -->
		  <li class="selected"><a href="/${oname }/interact/vote_option_data.action?voteid=${voteid}&mid=${mid }&redirectXc=${redirectXc}">投票结果</a></li>
	  </ul>
	   	<a onclick="method1('vote_result')">导出结果</a>
	   <s:if test="redirectXc!=0">
		  <a href="/${oname }/interact/edit_xcLottery.action?xcid=${redirectXc}" class="return" title="返回"></a>
	   </s:if>
	   <s:else>
		   <a href="/${oname }/interact/index.action?mid=${mid }" class="return" title="返回"></a>
	   </s:else>
	</div>
	<div class="pt10">
		<div id="voteoptionpie" class="chart-mod-line"></div>
		<a href="/${oname }/interact/export_option_data.action?voteid=${voteid}">导出表格</a>
	    <table width="100%" id="vote_result" class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
		<thead>
			<tr>
				<th>候选项</th>
				<th>投票人数</th>
				<th>占比</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="optionDto.list" var="s">
			<tr align="center" >
			   <td>${s.content}</td>
			   <td>${s.count}</td>
			   <td>${s.percent}</td>
			</tr>
		</s:iterator>
		</tbody>
	</table>
	</div>