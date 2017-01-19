<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="/css/shop/ydyx.css" rel="stylesheet" type="text/css">
	<div class="top"><a href="javascript:void(0);" onclick="window.history.back()" class="left"><img src="/images/shop/back.png" />&nbsp;返回</a>订单详情　<a href="javascript:void()" class="right"></a></div>
	<div style="width:100%;height:44px;"></div>
	<div class="main">
	        <ul class="one">
			  	<li>
			  		<p class="seventhn"><em>订单编号：${dto.payOrder.id }</em></p>
			  	</li>
			  	<li>
			  		<p class="seventhn"><em>订单状态：
		            <s:if test='dto.payOrder.status == "DFK"'>
		            	待付款
	              	</s:if>
	              	<s:if test='dto.payOrder.status == "YQX"'>
	              		已取消
					</s:if>
			 		<s:if test='dto.payOrder.status == "CMP"'>
			 			已完成
			 		</s:if>
			 		<s:if test='dto.payOrder.status == "DQR" || dto.payOrder.status == "DFH"'>
			 			已付款
			 		</s:if>
			 		</em></p>
	             </li>
             	 <s:if test="dto.address.id >0">
	             <li>
	             	 	<input type="radio" name="aid" id="radio-choice" value="${dto.address.id }" checked="checked" style="display:none;">
	             	 	<p class="seventhn"><em>${dto.address.name }</em>${ dto.address.telphone}</p>
        	 		    <p class="eighthn">${dto.address.province }${dto.address.city }${dto.address.address }</p>
			  	</li>
			  		</s:if>
		         <s:iterator value="dto.payOrder.goods" var="sc" status="st">
			         <li>
	             		<em class="firm">
	          			<s:if  test="#st.payApt != null">
							<img src="${imgDomain }${st.payApt.img}" class="pic" width="60" height="60"/>
						</s:if>
						<s:else>
							<img src="${imgDomain }${sc.productsimg}" class="pic" width="60" height="60"/>
						</s:else>
			             <p class="ninth">${sc.productname }</p><br>
			             <p class="tenth">
			             	<s:if test='#sc.type == 1'>
								<i>￥${sc.price/100 }</i>
							</s:if>
							<s:else>
								<i>${sc.price }积分</i>
							</s:else><em>x</em><span>${sc.num }</span>
							<input type="hidden" name="id" value="${sc.id }">
			             </p><br>
			            </em>
		             </li>
	  			</s:iterator>
	  			
	  			<li>
		           <p class="total">共<i>${dto.payOrder.goodscount}</i>件商品&nbsp;&nbsp;
		           	<s:if test="dto.payOrder.subtype == 1">
		           		总价：<i>￥${dto.payOrder.realprice/100 }</i>
		           	</s:if>
		           	<s:else>
		           		总价：<i>${dto.payOrder.realprice}积分</i>
		           	</s:else>
		           </p>
		          </li>
		          <li>
		          	<p class="seventhn"><em>下单时间：<s:date name="dto.payOrder.createtime" format="yyyy-MM-dd HH:mm:ss"/></em></p>
		          	<p class="seventhn"><em>
		          	<s:if test="dto.record != null">
		          		交易单号：${dto.payOrder.id}<br/>
			 			付款时间：<s:date name="dto.record.createtime" format="yyyy-MM-dd HH:mm:ss"/><br/>
			 		</s:if>
			 		</em></p>
		          	<s:if test="dto.payOrder.expressid != null">
			 			<p class="seventhn"><em>物流编号：${dto.payOrder.expressid }<br/></em></p>
			 		</s:if>
			 		
		          </li>
		             
	        </ul>
	</div>
