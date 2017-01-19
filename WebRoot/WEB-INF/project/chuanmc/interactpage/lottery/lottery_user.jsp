<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="toolbar pb10">
  <ul class="c_switch">
  <li <s:if test="media==0">class="selected"</s:if>><a href="lottery_user_sina.action?lid=${lid }&type=${type }&mid=${mid }&redirectXc=${redirectXc}">微博数据</a></li>
  <li <s:if test="media==1">class="selected"</s:if>><a href="lottery_user_wx.action?lid=${lid }&type=${type }&mid=${mid }&media=1&redirectXc=${redirectXc}">微信数据</a></li>
  <li <s:if test="media==2">class="selected"</s:if>><a href="lottery_user_wx.action?lid=${lid }&type=${type }&mid=${mid }&media=2&redirectXc=${redirectXc}">用户数据</a></li>
  <li <s:if test="media==-1">class="selected"</s:if>><a href="lottery_user_wx.action?lid=${lid }&type=${type }&mid=${mid }&media=-1&redirectXc=${redirectXc}">匿名数据</a></li>
  <li><a href="lottery_winner.action?lid=${lid }&type=${type }&mid=${mid }&redirectXc=${redirectXc}">中奖数据</a></li>
  </ul>
   <s:if test='media==1'>
	  	<input type="button" onclick="moveToGroup('${oname}',${lid},'${type}')" value="移动到组">
	  </s:if>
  <s:if test="redirectXc!=0">
	<a href="/${oname }/interact/edit_xcLottery.action?xcid=${redirectXc}" class="return" title="返回"></a>
  </s:if>
  <s:elseif test='type=="Z"'><a href="index.action?mid=10003" class="return" ></a></s:elseif>
  <s:elseif test='type=="L"'><a href="index.action?mid=10004" class="return"></a></s:elseif>
  <s:elseif test='type=="G"'><a href="index.action?mid=10005" class="return" ></a></s:elseif>
  <s:elseif test='type=="Y"'><a href="index.action?mid=10011" class="return" ></a></s:elseif>
</div>
<!-- 
	<div style="width: 80%; height: 10%; float: left">
		<form>
			<input type="hidden" name="lid" value="${lid }"/>
			<input type="text" name="nickName" value="${nickName }" placeholder="请输入昵称搜索"/>
			<input type="hidden" name="mid" value="${mid }">
			<input type="hidden" name="type" value="${type }">
			<input type="submit" value="搜索"/>
		</form>
		<s:if test='dto.pager.totalCount==0&&nickName!=""&&nickName!=null'>用户:${nickName}。不存在</s:if><s:else>共${dto.pager.totalCount }用户</s:else>
	</div>
 -->
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
		<thead>
			<tr>
				<th>用户名</th>
				<s:if test="media == 0">
					<th>微博昵称</th>
				</s:if>
				<s:elseif test="media == 1">
					<th>微信昵称</th>
				</s:elseif>
				<s:elseif test="media == 2">
					<th>微博昵称</th>
					<th>微信昵称</th>
				</s:elseif>
				<th>
					已抽奖次数
				</th>
				<th>
					剩余抽奖次数
				</th>
				<th>
					抽奖详情
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.user" var="u">
				<tr align="center">
					<td align="center">
						 ${u.username }
					</td>
					<s:if test="media == 0 || media == 1">
						<td align="center">
							 ${u.nickName }
						</td>
					</s:if>
					<s:elseif test="media == 2">
						<td align="center">
							 ${u.nickName }
						</td>
						<td align="center">
							${u.wxnickname }
						</td>  
					</s:elseif>
					<td align="center">
						${u.usednum }
					</td>
					<td align="center">
						${u.totalnum-usednum }
					</td>
					<td align="center">
						<a href="javascript:lud(${u.wbuid },${lid },${media },'${oname }')">详情</a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
