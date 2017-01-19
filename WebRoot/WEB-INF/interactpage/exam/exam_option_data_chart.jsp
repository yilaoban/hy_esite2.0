<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<script src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
       $(document).ready(function() {
            var length=<s:property value="#attr.list.size()"/>;
            for(var i=0;i<length;i++)
            {
              var dataobj= ${data};
              var type=dataobj[i].type;
              if(type=="ORD"){
	              var cao =dataobj[i].optionDto.list.length;
	              var ordlist =dataobj[i].optionDto.ordListsStr;
	              var category=new Array();
	              for(var n = 0;n<cao;n++){//处理横坐标
	              	category.push(dataobj[i].optionDto.list[n].content);
	              }
               $('#searchoptionpie'+i).highcharts({
		        title: {
		            text: null,
		            x: -20 //center
		        },
		        subtitle: {
		            text: null,
		            x: -20
		        },
		        xAxis: {
		            categories: category
		        },
		        yAxis: {
		            title: {
		                text: null
		            },
		            plotLines: [{
		                value: 0,
		                width: 1,
		                color: '#808080'
		            }]
		        },
		        tooltip: {
		            valueSuffix: null
		        },
		        legend: {
		            layout: 'vertical',
		            align: 'right',
		            verticalAlign: 'middle',
		            borderWidth: 0
		        },
		        series:  eval(ordlist)
		    	});
              }else{
	              new Highcharts.Chart({  
	                  chart: {  
	                      renderTo: 'searchoptionpie'+i,
	                      plotBackgroundColor: null,
	                      plotBorderWidth: null,
	                      plotShadow: false
	                  }, 
	                   title: {
	                     text: dataobj[i].title+'<br>答题人数'+ dataobj[i].optionDto.total,
	                     x: -20 //center
	                  },
	                series: [{
	                  type: 'pie',
	                  name: '人数',
	                  data: dataobj[i].optionDto.examdata
	                }]
	              });  
	            }
              }
   });
</script>
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li ><a href="/${oname }/interact/exam_record_list.action?searchid=${searchid}&mid=${mid }&mtype=0">微博数据</a></li>
	  <li ><a href="/${oname }/interact/exam_record_list.action?searchid=${searchid}&mid=${mid }&mtype=1">微信数据</a></li>
	  <li ><a href="/${oname }/interact/exam_record_list.action?searchid=${searchid}&mid=${mid }&mtype=2">用户数据</a></li>
	  <li ><a href="/${oname }/interact/exam_record_list.action?searchid=${searchid}&mid=${mid }&mtype=-1">匿名数据</a></li>
	  <li class="selected"><a href="/${oname }/interact/exam_record_data.action?searchid=${searchid}&mid=${mid }">测评结果</a></li>
	  </ul>
	  <a href="/${oname }/interact/index.action?mid=10015" class="return"></a>
	</div>
  <s:iterator value="list" var="l" status="all">
	<div class="pt10" <s:if test='#attr.type=="FIL"'>style="display: none"</s:if>>
	   <div>Q<s:property value='#all.getIndex()+1'/>:<s:if test='#attr.type=="SGL"'>单选择</s:if><s:if test='#attr.type=="ORD"'>排序题</s:if><s:elseif test="#attr.type=='MUP'">多选择</s:elseif> <s:elseif test="#attr.type=='GAD'">打分题</s:elseif><s:elseif test="#attr.type=='FIL'">填空题</s:elseif> </div>
	   <div id="searchoptionpie<s:property value='#all.getIndex()'/>" class="chart-mod-line"></div>
	   <s:if test='#attr.type=="ORD"'>
	   	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
			<thead>
				<tr>
					<th></th>
					<s:iterator begin="1" end = "optionDto.list.size" var="s" status="st">
					<th>第${st.count}位</th>
					</s:iterator>
				</tr>
			</thead>
			<tbody>
			<s:iterator value="optionDto.list" var="s" status="st">
				<tr align="center" >
					<td>${s.content}</td>
					<s:iterator begin="1" end="optionDto.list.size" var="o" status='st1'>
					<td>${optionDto.lists[st.index][st1.count] }</td>
					</s:iterator>
				</tr>
			</s:iterator>
			</tbody>
		</table>
	   </s:if>
	   <s:else>
	    <table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
		<thead>
			<tr>
				<th>答题选项</th>
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
	   </s:else>
	</div>
	</s:iterator>
