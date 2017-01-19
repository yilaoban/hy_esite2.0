<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content left_module">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li <s:if test="utype==0">class="selected"</s:if>><a href="/${oname }/interact/renQiData.action?rqid=${rqid }&mid=10012&utype=0">微博数据</a></li>
	  <li <s:if test="utype==1">class="selected"</s:if>><a href="/${oname }/interact/renQiData.action?rqid=${rqid }&mid=10012&utype=1">微信数据</a></li>
	  </ul>
	  <a href="/${oname }/interact/index.action?mid=10012" class="return" title="返回"></a>
	</div>
  <div >
 	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
		<tr>
			<th>IP</th>
			<th>来源(昵称)</th>
			<th>发起时间</th>
			<th>已集人气</th>
			<th>是否集满</th>
			<th>是否抽奖</th>
			<th>奖品类别</th>
			<th>操作</th>
		</tr>
		<s:iterator value="dto.list" var="l">
			<tr>
				<td>${l.ip }</td>
				<td>
					<s:if test="utype==0">
							微博<s:if test="name!=null">(${l.name })</s:if>
						</s:if>
						<s:elseif test="utype==1">
							微信<s:if test="name!=null">(${l.name })</s:if>
					</s:elseif>
				</td>
				<td><s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${l.addjf }</td>
				<td><s:if test="addjf>cnum">已集满</s:if><s:else>未集满</s:else></td>
				<td><s:if test="lrnum>0">已抽奖</s:if><s:else>未抽奖</s:else></td>
				<td>
					<s:if test="lrnum==0">
						/
					</s:if>
					<s:else>
						<s:if test="lrStatus==0">未中奖</s:if>
						<s:if test="lrStatus==1">积分</s:if>
						<s:if test="lrStatus==2">优惠券</s:if>
						<s:if test="lrStatus==3">实物</s:if>
					</s:else>
				</td>
				<td><a href="javascript:rqdetail('${l.fuid }','${rqid }')">查看详情</a></td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>