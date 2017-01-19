<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
 <s:if test="#attr.indexDto.viewall.size()>1">
<table class="tb_normal mb20" width="100%" border="1" cellspacing="0" cellpadding="0">
				<tr>
					<td align="left" colspan="4">
						<b>所有站点</b>
					</td>
				</tr>
				<tr>
					<td align="left">
						总访问数：<b>${indexDto.totalvisitnum}</b>次
					</td>
					<td align="left">
						匿名访问数：<b>${indexDto.nvisitnum}</b>次
					</td>
					<td align="left">
						非匿名访问数：<b>${indexDto.visitnum}</b>次
					</td>
					<td align="left">
						今日新增：<b>${indexDto.todayadd}</b>次
					</td>
				</tr>
				<tr>
					<td align="left" colspan="4">
					总互动数：<b>${indexDto.totalhdnum}</b>次
					</td>
				</tr>
				<tr>
					<td align="left">
						活动参与人数：<b>${indexDto.joinnum}</b>人
					</td>
					<td align="left">
						跳出人数：<b>${indexDto.outnum}</b>人
					</td>
					<td align="left">
						成功参与人数：<b>${indexDto.sucjoinnum}</b>人
					</td>
					<td align="left">
					</td>
				</tr>
				<tr>
					<td align="left" colspan="4">
					    互动城市TOP5：<s:iterator value="#attr.indexDto.arealist" var="a">
	                    <span class="mr10">${a.area}|<b>${a.num}</b></span>
	               		</s:iterator>
					</td>
				</tr>
				<tr>
					<td align="left" colspan="4">
					   互动用户质量：
					   <s:iterator value="#attr.indexDto.levellist" var="s">
					   <span class="mr10">
                       <s:if test="#attr.s.level==1">
		                 粉丝数<100
		               </s:if>
				       <s:elseif test="#attr.s.level==2">
				         100≤粉丝数<500
				       </s:elseif>
				        <s:elseif test="#attr.s.level==3">
				        500≤粉丝数<1000
				       </s:elseif>
				        <s:elseif test="#attr.s.level==4">
				         粉丝数>=1000
				       </s:elseif>
	                    |<b>${s.dnum}</b>人
	                    </span>
		               </s:iterator>
					</td>
				</tr>
		</table>
	</s:if>
<s:iterator value="indexDto.viewall" var="v">
<table class="tb_normal mb20" width="100%" border="1" cellspacing="0" cellpadding="0">
				<tr>
					<td align="left" colspan="4">
						站点:<b>${v.groupname}</b>
					</td>
				</tr>
				<tr>
					<td align="left">
						总访问数：<b>${v.totalvisitnum}</b>次
					</td>
					<td align="left">
						匿名访问数：<b>${v.nvisitnum}</b>次
					</td>
					<td align="left">
						非匿名访问数：<b>${v.visitnum}</b>次
					</td>
					<td align="left">
						今日新增：<b>${v.todayadd}</b>次
					</td>
				</tr>
				<tr>
					<td align="left" colspan="4">
					总互动数：<b>${v.totalhdnum}</b>次
					</td>
				</tr>
				<tr>
					<td align="left">
						活动参与人数：<b>${v.joinnum}</b>人
					</td>
					<td align="left">
						跳出人数：<b>${v.outnum}</b>人
					</td>
					<td align="left">
						成功参与人数：<b>${v.sucjoinnum}</b>人
					</td>
					<td align="left">
					</td>
				</tr>
				<tr>
					<td align="left" colspan="4">
					    互动城市TOP5：<s:iterator value="#attr.v.arealist" var="a">
	                    <span class="mr10">${a.area}|<b>${a.num}</b></span>
	               		</s:iterator>
					</td>
				</tr>
				<tr>
					<td align="left" colspan="4">
					   互动用户质量：
					   <s:iterator value="#attr.v.levellist" var="s">
					   <span class="mr10">
                       <s:if test="#attr.s.level==1">
		                 粉丝数<100
		               </s:if>
				       <s:elseif test="#attr.s.level==2">
				         100≤粉丝数<500
				       </s:elseif>
				        <s:elseif test="#attr.s.level==3">
				        500≤粉丝数<1000
				       </s:elseif>
				        <s:elseif test="#attr.s.level==4">
				         粉丝数>=1000
				       </s:elseif>
	                    |<b>${s.dnum}</b>人
	                    </span>
		               </s:iterator>
					</td>
				</tr>
				<tr>
					<td align="left" colspan="4">了解更多用户信息及报告下载，点击
					<a href="/page/activity_report.action?sitegroupid=${v.sgid}"><span style="color:red">活动报告</span></a>
			        <a href="/page/hd_report.action?sgid=${v.sgid}"><span style="color:red">互动报告</span></a>
			        <a href="/page/visit_report.action?sgid=${v.sgid}"><span style="color:red">用户报告</span></a>
					</td>
				</tr>
		</table>
</s:iterator>
</div>