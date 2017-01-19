<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content left_module">
  	<div class="toolbar pb10">
  	  <ul class="c_switch">
	  <li class="selected"><a href="">奖品管理</a></li>
	  </ul>
	  <s:if test="redirectXc!=0">
		  <a href="/${oname }/interact/edit_xcLottery.action?xcid=${redirectXc }" class="return" title="返回"></a>
	  </s:if>
	  <s:elseif test='returnType=="Z"'><a href="index.action?mid=10003" class="return" title="返回"></a></s:elseif>
      <s:elseif test='returnType=="L"'><a href="index.action?mid=10004" class="return" title="返回"></a></s:elseif>
	  <s:elseif test='returnType=="G"'><a href="index.action?mid=10005" class="return" title="返回"></a></s:elseif>
	  <s:elseif test='returnType=="Y"'><a href="index.action?mid=10011" class="return" title="返回"></a></s:elseif>
	  <s:elseif test='returnType=="N"'><a href="index.action?mid=10016" class="return" title="返回"></a></s:elseif>
	</div>
<div id="lottery">
	<table border="0" cellpadding="0" cellspacing="0">
		<tr class="lottery-group">
			<td class="lottery-unit td_1 active" ><img src="/images/nine/1.png" /></td>
			<td class="lottery-unit td_2"><img src="/images/nine/2.png" /></td>
			<td class="lottery-unit td_3"><img src="/images/nine/4.png" /></td>
		</tr>
		<tr class="lottery-group">
			<td class="lottery-unit td_4"><img src="/images/nine/7.png" /></td>
			<td class="td_5"><a href="Javascript:void(0);"><img src="/images/nine/start.jpg" /></a></td>
			<td class="lottery-unit td_6"><img src="/images/nine/5.png" /></td>
		</tr>
        <tr class="lottery-group">
			<td class="lottery-unit td_7"><img src="/images/nine/3.png" /></td>
			<td class="lottery-unit td_8"><img src="/images/nine/6.png" /></td>
			<td class="lottery-unit td_9"><img src="/images/nine/8.png" /></td>
		</tr>
	</table>
</div>
<div><img alt="" src="/images/nine/t_order_1.png"> </div>
<div>
	<table width="100%" class="tb_normal" border="1" cellspacing="1" cellpadding="1">
	<thead>
		<tr>
			<td>位置</td>
			<td>图片</td>
			<td>颜色</td>
			<td>奖品名称</td>
			<td>是否中奖</td>
			<td>奖品数量</td>
			<td>操作</td>
		</tr>
	</thead>
		<s:iterator begin="0" end="8" status="st">
			<tr>
				<td>${st.count }</td>
				<td>${st.count }</td>
				<td>${st.count }</td>
				<td>${st.count }</td>
				<td>${st.count }</td>
				<td>${st.count }</td>
				<td><a onclick="updatePrize(1)">修改</a></td>
			</tr>
		</s:iterator>
	</table>
</div>
</div>


<style type="text/css">
	#lottery{width:200px;height:426px;background-size:cover; background-color:#000;}
	#lottery table td{width:142px;height:142px;text-align:center;vertical-align:middle;font-size:24px;color:#333;font-index:-999}
	#lottery table td a{width:142px;height:142px;line-height:150px;display:block;text-decoration:none;}
	#lottery table td.td_1{background-color:#8670ff;}
	#lottery table td.td_2{background-color:#df88cf;}
	#lottery table td.td_3{background-color:#8ed9f9;}
	#lottery table td.td_4{background-color:#fef9b9;}
	#lottery table td.td_5{background-color:#ff745b;}
	#lottery table td.td_6{background-color:#21d9cf;}
	#lottery table td.td_7{background-color:#fdfacf;}
	#lottery table td.td_8{background-color:#fa598f;}
	#lottery table td.td_9{background-color:#ea0000;}
    #lottery table td.active{background-color:yellow;}
</style>

<script type="text/javascript">
	function updatePrize(productid){
		if(productid > 0){
			layer.open({
				type : 2,
				area : ['450px','490px'],
				title : ["编辑奖品",true],
				content: "edit_nine_prize.action?productid="+productid
			});
		} 
	}
</script>