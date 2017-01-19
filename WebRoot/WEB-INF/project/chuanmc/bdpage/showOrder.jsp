<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="/css/shop/ydyx.css" rel="stylesheet" type="text/css">
<s:set name="url_param" value="#request.queryString()"/>
	<script type="text/javascript">
  		function submitDD(){
  			var needAddress = parseInt('${dto.needAddress}');
  			if(needAddress == 1){
  				$("#orderform").submit();
  			}else{
		  		var aid = $('input:radio[name="aid"]:checked').val();//地址
	 			if(aid > 0){
	 				$("#orderform").submit();
	 			}else{
	 				$.alert("请选择收货地址");
	 			}
  			}
	  	}
  		
  		$(document).ready(function(){
	  		var param = '';
  			var ids = $("input[name='id']");
  			for(i=0;i<ids.length;i++){
  				param+="id="+ids[i].value+"&";
  			}
  			param=param.substring(0,param.length-1);
	  		$.cookie('showOrder_cookie', param);
  		});
	  	
	</script>
<s:set name="totalprice" value="0"/>
<s:set name="totalnum" value="0"/>
<s:set name="maxjf" value="0" />
	<div class="top"><a href="javascript:void(0);" onclick="window.history.back()" class="left"><img src="/images/shop/back.png" />&nbsp;返回</a>确认订单　<a href="javascript:void()" class="right"></a></div>
	<div style="width:100%;height:44px;"></div>
	<form method="post" action="/${oname}/user/confirmOrder.action" id="orderform">
	<div class="main">
	        <ul class="one">
	             <li>
	             <s:if test="dto.needAddress != 1">
             	 <s:if test="dto.address.id >0">
		             <a href="/${oname}/user/showAddress.action?aid=${dto.address.id}&type=${type}" class="check">
			             <img src="/images/shop/right2.png" class="right">
			             <p class="title">收货地址</p>
	             	 	<input type="radio" name="aid" id="radio-choice" value="${dto.address.id }" checked="checked" style="display:none;">
	             	 	<p class="seventhn"><em>${dto.address.name }</em>${ dto.address.telphone}</p>
        	 		    <p class="eighthn">${dto.address.province }${dto.address.city }${dto.address.address }</p>
        	 		  </a>
			  		</s:if>
			  		<s:else>
					  		<s:if test="dto.payAddressList.size() > 0 ">
						  		<s:iterator value="dto.payAddressList" var="ua" status="st">
						  			<s:if test="#st.first">
						  			<a href="/${oname}/user/showAddress.action?aid=${ua.id}&type=${type}" class="check">
						  			 <img src="/images/shop/right2.png" class="right">
						  				<p class="title">收货地址</p>
										<input type="radio" name="aid" id="radio-choice-${st.count }" value="${ua.id }" checked="checked" style="display:none;" o>
										<p class="seventhn"><em>${ua.name }</em>${ ua.telphone}</p>
       	 		    					<p class="eighthn">${ua.province }${ua.city }${ua.address }</p>
						             </a>
						  			</s:if>
						  		</s:iterator>
					  		</s:if>
					  		<s:else>
					  			 <p class="dizhi"><a href="/${oname}/user/addPayAddress.action"><em>+</em>添加收货地址</a></p>
					  		</s:else>
			  		</s:else>
			  		</s:if>
		         <s:iterator value="dto.shopCartList" var="sc" status="st">
			         <li>
	             		<em class="firm">
	          			<s:if  test="#st.payApt != null">
							<img src="${imgDomain }${st.payApt.img}" class="pic" width="60" height="60"/>
						</s:if>
						<s:else>
							<img src="${imgDomain }${sc.product.simgurl}" class="pic" width="60" height="60"/>
						</s:else>
			             <p class="ninth">${sc.product.name }</p><br>
			             <p class="tenth">
			             	<s:if test='#sc.product.type == "W"'>
								<i>￥${sc.product.salesprice }</i>
				  				<s:set name="totalprice" value="#totalprice+(#sc.product.salesprice*#sc.num)"/>
							</s:if>
							<s:else>
								<i>${sc.product.salesjifen }积分</i><span>${sc.product.price}积分</span>
								<s:set name="totalprice" value="#totalprice+(#sc.product.salesjifen*#sc.num)"/>
							</s:else><em>x</em><span>${sc.num }</span>
							<input type="hidden" name="id" value="${sc.id }">
			             </p><br>
			            </em>
		             </li>
	  					<s:set name="totalnum" value="#totalnum + #sc.num"/>
	  					<s:set name="maxjf" value="#maxjf +(#sc.product.maxjf*#sc.num)"/>
	  			</s:iterator>
	        </ul>
	</div>
  	<div class="cart-content" style="display: none">
  		<div class="chooseAddr">
			<s:if test='type == "W"'>
				<p>可使用<input type="number" id="usejf" name="usejf" max="${dto.totalJf}" min="0" value="0">积分</p>  
				<p>您有${dto.totalJf }个积分</p>
				<p>最多可使用${maxjf}个积分</p>
			</s:if>
		</div>	
		<div class="chooseAddr">
			<s:if test='type == "W"'>
				<p><label style="color:red">${totalnum}</label>件商品，总金额：<label style="color:red">${totalprice}</label></p>
				<p>抵扣：-<label id="lab_discount" style="color:red">0</label></p>
				<p>应付总额：<label id="lab_realprice" style="color:red">${totalprice}</label></p> 
			</s:if>
			<s:else>
				<p>${totalnum}件商品，总价：${totalprice}积分</p>
			</s:else>
		</div>	
	</div>
	<s:if test='type == "W"'>
		<div class="bottom2"><a href="javascript:submitDD();" class="zengjia">确认支付<span>￥${totalprice}</span></a></div>
	</s:if>
	<s:else>
		<div class="bottom2"><a href="javascript:submitDD();" class="zengjia">确认支付<span>${totalprice}积分</span></a></div>
	</s:else>
</form>
<script type="text/javascript">
	$("#usejf").change(function(){
		var maxjf = '${maxjf}';//最多可使用积分
		var totaljf = '${dto.totalJf}';//拥有积分
		var count = $(this).val();//想使用积分
		if(count > totaljf){
			count = totaljf;
		}
		if(count >= maxjf){
			count = maxjf;
		}
		$(this).val(count);
		var total = '${totalprice}';//总价
		var bili ='${dto.wkq.bili}';//比例
		bili=bili>0?bili:100;
		var discount = (1/bili * count).toFixed(2);//计算折扣
		$("#lab_discount").text(discount);
		$("#lab_realprice").text(total-discount);
	})
</script>