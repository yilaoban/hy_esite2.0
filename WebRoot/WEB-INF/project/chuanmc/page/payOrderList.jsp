<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="/js/qrcode.js"></script>
<div class="wrap_content">
	<a href="/${oname}/content/weiKaQuanList.action?tool=2" class="return"></a>
	<table width="100%" class="tb_normal" border="1" cellspacing="1" cellpadding="1">
		<thead>
			<tr>
				<th>订单号</th>
				<th>价格</th>
				<th>商品数量</th>
				<th>状态</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.orderGoodsList" var="p">
			<tr>
				<td>${p.orderid }</td>
				<td>${p.price }</td>
				<td>${p.num }</td>
				<td>
					<s:if test='#p.status == "DFK" '>
						待付款
					</s:if>
					<s:elseif test='#p.status == "CMP"'>
						已验证
					</s:elseif>
					<s:else>
						<a href="javascript:void(0);" onclick="showOrderUrl(${p.id })">查看二维码</a>
					</s:else>
				</td>
			</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
	
</div>
<script type="text/javascript">
	function showOrderUrl(poid){
  		$.post("/${oname}/user/showOrderUrl.action",{"poid":poid},function(data){
  			if(data.status == 1){
  				$('#myModal').modal('show');
  				qrcode.makeCode(data.url);
  			}else{
  				alert(data.hydesc);
  			}
  		});
  	
  	}
</script>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
         </div>
         <div class="modal-body">
            	<div id="qrcode"></div>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
<script type="text/javascript">
var qrcode = new QRCode(document.getElementById("qrcode"), {
	width : 200,
	height : 200
});
</script>