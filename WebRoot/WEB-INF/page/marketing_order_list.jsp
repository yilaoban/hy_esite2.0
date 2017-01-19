<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<style>
.orderDetail td{border: 0px}
</style>
<script type="text/javascript" src="/js/qrcode.js"></script> 
<script type="text/javascript">
	function msg(msg,title){
		layer.alert(msg,{title:title});
	}
    function saveexpress(id){
		var url = "/${oname}/page/saveExpress.action";
		layer.prompt({
			title : '填写快递单号'
			},function(value){
				if(value==null||value==''){
					   return false;
				}
				$.post(url,{"orderid":id,"express":value},function(data){
					if(data == 1){
						layer.msg('快递单号提交完成!', {icon: 6, time: 3000}); 
					}else{
						layer.msg('快递单号提交失败！', {icon: 5, time: 3000});
					}
				});	             
	    });
	}

    function showWkq(id){
    	$.post("/${oname}/user/wkq_findwkq.action",{"poid":id},function(data){
			if(data.status == 1){
				qrcode.clear();
				qrcode.makeCode(data.url);
				layer.msg('正在生成二维码,请稍等...', {icon: 16,time:2000},function(){
					layer.alert($("#qrcode").html());
				});
			}else{
				layer.msg(data.hydesc, {icon: 5, time: 2000});
			}
		});	
    }
    
    $(document).ready(function(){
    	$(".showDetail").click(function(){
    		$(this).parents('tr').eq(0).next().toggle(); 
        });
    });
    
</script>
<div class="wrap_content">
	<div id="qrcode" style="display: none"></div>
	<form action="/${oname}/page/marketingOrderList.action" id="form1" method="post">
		<input type="checkbox" name="sift.status" value="DFK" <s:if test='sift.status.contains("DFK")'>checked="checked"</s:if>/>待付款
		<input type="checkbox" name="sift.status" value="DQR" <s:if test='sift.status.contains("DQR")'>checked="checked"</s:if>/>待确认
		<input type="checkbox" name="sift.status" value="DFH" <s:if test='sift.status.contains("DFH")'>checked="checked"</s:if>/>待发货
		<input type="checkbox" name="sift.status" value="DSH" <s:if test='sift.status.contains("DSH")'>checked="checked"</s:if>/>待收货
		<input type="checkbox" name="sift.status" value="CMP" <s:if test='sift.status.contains("CMP")'>checked="checked"</s:if>/>已完成
		<input type="hidden" name="subtype" value="${subtype }"/>
		<p class="clearfix"></p>
	订单号:<input type="text" name="sift.orderid" value="${sift.orderid[0] }"/><br>	
	购买时间：
	<input id="startTime" type="text" value="<s:date name="startTime" format="yyyy-MM-dd HH:mm:ss"/>" name="startTime" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
 	至：
	<input id="endTime" type="text" value="<s:date name="endTime" format="yyyy-MM-dd HH:mm:ss"/>" name="endTime" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
	<input type="submit" value="搜索" class="btn btn-info btn-sm"/>
	</form>
	<p class="clearfix"></p>
		<table width="100%" class="tb_normal"  cellspacing="1" cellpadding="1">
			<tr>
				<th>订单号</th> 
				<th>金额</th> 
				<th>购买者</th> 
				<th>购买时间</th> 
				<th>状态</th>
				<th>发货地址</th>
				<th>操作</th>
			</tr>
		<s:iterator value="dto.list" var="o">
			<tr>
				<td>${o.id }</td>
				<td>
					<s:if test="#o.subtype==0">${o.realprice }(积分)</s:if>
					<s:elseif test="#o.subtype==1">${o.realprice/100 }(元)</s:elseif>
				</td>
				<td>
					<s:if test="#o.nameTag==1">${o.hyuser.username }</s:if>
					<s:elseif test="#o.nameTag==2">${o.wxuser.nickname }</s:elseif>
					<s:elseif test="#o.nameTag==3">${o.sinauser.nickname }</s:elseif>
					<s:elseif test="#o.nameTag==4">${o.address.name }</s:elseif>
					<s:else>匿名_${o.hyuser.id }</s:else>
				</td>
				<td>
					<s:date name="#o.createtime" format="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<s:if test='#o.status=="DFK"'>待付款</s:if>
					<s:elseif test='#o.status=="DFH"'>待发货</s:elseif>
					<s:elseif test='#o.status=="DSH"'>待收货</s:elseif>
					<s:elseif test='#o.status=="DQR"'>待确认</s:elseif>
					<s:elseif test='#o.status=="CMP"'>已完成</s:elseif>
					<s:elseif test='#o.status=="YQX"'>已取消</s:elseif>
				</td>
				<td>
					<a href="javascript:msg('${o.address.province }${o.address.city }${o.address.address }','收货地址');">地址</a>
					<a href="javascript:msg('${o.address.telphone }','手机号');">手机</a>
				</td>
				<td>
					<a href="javascript:void(0)" class="showDetail">查看详情</a>
					<s:if test='#o.status=="DFH"'><a href="javascript:saveexpress('${o.id }')">填写快递单号</a></s:if>
				</td>
			</tr>
			<tr class="orderDetail" style="display: none">
				<td colspan="7">
					<table width="80%"  style="border: 0;margin: auto">
						<tr>
							<td>商品名称</td>
							<td>单价</td>
							<td>购买数量</td>
							<td>金额小计</td>
						</tr>
						<s:iterator value="#o.orderGoods" var="g">
						<tr>
								<td>${g.productname }<s:if test='#g.productsubtype=="K"'><a href="javascript:showWkq('${g.id }')">(查看卡券二维码)</a></s:if></td>
								<s:if test='#g.type==0'>
									<td>${g.price }(积分)</td>
								</s:if>
								<s:else>
									<td>${g.price/100 }(元)</td>
								</s:else>
								<td>${g.num }</td>
								<s:if test='#g.type==0'>
									<td>${g.price*g.num }(积分)</td>
								</s:if>
								<s:else>
									<td>${g.price*g.num/100 }(元)</td>
								</s:else>
						</tr>
						</s:iterator>
						<s:iterator value="#o.kqGoods" var="g">
						<tr>
								<td>${g.productname }<s:if test='#g.productsubtype=="K"'><a href="javascript:showWkq('${g.id }')">(查看卡券二维码)</a></s:if></td>
								<s:if test='#g.type==0'>
									<td>${g.price }(积分)</td>
								</s:if>
								<s:else>
									<td>${g.price/100 }(元)</td>
								</s:else>
								<td>${g.num }</td>
								<s:if test='#g.type==0'>
									<td>${g.price*g.num }(积分)</td>
								</s:if>
								<s:else>
									<td>${g.price*g.num/100 }(元)</td>
								</s:else>
						</tr>
						</s:iterator>	
					</table>
				</td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>

</div>

<script type="text/javascript">
var qrcode = new QRCode(document.getElementById("qrcode"), {
	width : 200,
	height : 200
});
</script>