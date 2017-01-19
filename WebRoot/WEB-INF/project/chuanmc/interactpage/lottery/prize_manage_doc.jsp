<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<style type="text/css">  
.indent{text-indent: 2em;}   
</style>
<div class="wrap_content">
   <dl>
   	 <dt><font size="3px">1、何为安慰奖品？什么情况下需要设置安慰奖品？</font></dt>
   	 	<dd class="indent">案例：活动宣传的口号为“大转盘活动100%中奖”，活动参与人数约1000人，奖品设置为：一等奖：iphone6 1部；二等奖：10积分 4个；三等奖：优惠券 5个。其他未中奖用户均可获得钥匙扣1个。</dd>
   	 	<dd class="indent">上述案例中，“钥匙扣”就是安慰奖品，也就是说安慰奖品是针对未中奖用户设置的。针对此类案例，新增大转盘时活动的中奖率仍然设置为1%，奖品设置时一、二、三等奖依然按照上述的方法设置，将钥匙扣设置为四等奖，是否为安慰奖设置为“是”。</dd>
   	 	<dd class="indent"><img src="/images/prize_manage_pic6.png"></dd>
   	 <dt><font size="3px">奖品设置的结果如下：</font> </dt>
   	 	<dd><img src="/images/prize_manage_pic7.png"></dd>
   	 	<dd class="indent">用户在抽奖时，系统首先判断该用户是否中奖，若中奖，再判断用户中的是一等奖、二等奖还是三等奖，若不中奖，则提示用户获得了“钥匙扣”。</dd>
   </dl>
</div>
